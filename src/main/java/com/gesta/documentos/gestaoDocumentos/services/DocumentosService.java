package com.gesta.documentos.gestaoDocumentos.services;

import com.gesta.documentos.gestaoDocumentos.config.ConfiguracaoArmazenamentoArquivos;
import com.gesta.documentos.gestaoDocumentos.exceptions.ArmazenamentoArquivoException;
import com.gesta.documentos.gestaoDocumentos.exceptions.ArquivoNaoEncontradoException;
import com.gesta.documentos.gestaoDocumentos.models.Documento;
import com.gesta.documentos.gestaoDocumentos.models.enums.StatusDocumento;
import com.gesta.documentos.gestaoDocumentos.repositories.DocumentosRepository;
import com.gesta.documentos.gestaoDocumentos.vo.DocumentoFormVO;
import com.gesta.documentos.gestaoDocumentos.vo.DocumentoResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DocumentosService {
    private final DocumentosRepository documentosRepository;
    private final Path localDeArmazenamento;

    @Autowired
    public DocumentosService(DocumentosRepository documentosRepository, ConfiguracaoArmazenamentoArquivos configuracao) {
        this.documentosRepository = documentosRepository;
        this.localDeArmazenamento = Paths.get(configuracao.getUploadDir()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(localDeArmazenamento);
        } catch (Exception e) {
            throw new ArmazenamentoArquivoException("Nao foi possível criar o diretorio de armazenamento", e);
        }
    }

    public Documento create(DocumentoFormVO documento) {
        String nomeArquivo = StringUtils.cleanPath(documento.getFile().getOriginalFilename());

        try {
            if(nomeArquivo.contains("..")) {
                throw new ArmazenamentoArquivoException("Nome de arquivo invalido: " + nomeArquivo);
            }

            Path localDoArquivo = this.localDeArmazenamento.resolve(nomeArquivo);
            Files.copy(documento.getFile().getInputStream(), localDoArquivo, StandardCopyOption.REPLACE_EXISTING);

            Documento documentoParaSalvarBanco = new Documento(
                    documento.getIdPortador(),
                    nomeArquivo,
                    documento.getDescricao(),
                    documento.getMimeType(),
                    localDoArquivo.toString(),
                    StatusDocumento.ATIVO,
                    documento.getVencimento()
            );

            documentoParaSalvarBanco.setStatus(validarDocumento(documentoParaSalvarBanco));

            documentosRepository.saveAndFlush(documentoParaSalvarBanco);
            return documentoParaSalvarBanco;
        } catch (Exception e) {
            throw new ArmazenamentoArquivoException("Impossivel armazenar o arquivo: " + nomeArquivo, e);
        }
    }

    public DocumentoResponseVO get(long id) {
        Documento documento = documentosRepository.findById(id).orElse(null);

        if(documento == null) {
            throw new ArquivoNaoEncontradoException("O arquivo pesquisado nao existe. Por favor, tente novamente!");
        }

        return new DocumentoResponseVO(
                documento.getIdPortador(),
                documento.getNome(),
                documento.getDescricao(),
                documento.getMimeType(),
                documento.getCaminho(),
                documento.getStatus(),
                documento.getDataVencimento()
        );
    }

    public List<DocumentoResponseVO> getAll(Long idPortador) {
        List<DocumentoResponseVO> documentos = new ArrayList<>();

        documentosRepository.findAllByIdPortador(idPortador).forEach(documento -> documentos.add(
            new DocumentoResponseVO(
                    documento.getIdPortador(),
                    documento.getNome(),
                    documento.getDescricao(),
                    documento.getMimeType(),
                    documento.getCaminho(),
                    documento.getStatus(),
                    documento.getDataVencimento()
            )
        ));

        return documentos;
    }

    public List<DocumentoResponseVO> getAll() {
        List<DocumentoResponseVO> documentos = new ArrayList<>();

        documentosRepository.findAll().forEach(documento -> documentos.add(
                new DocumentoResponseVO(
                        documento.getIdPortador(),
                        documento.getNome(),
                        documento.getDescricao(),
                        documento.getMimeType(),
                        documento.getCaminho(),
                        documento.getStatus(),
                        documento.getDataVencimento()
                )
        ));

        return documentos;
    }

    public Documento delete(long id) {
        Documento documento = documentosRepository.findById(id).orElse(null);

        if(documento == null) {
            throw new ArquivoNaoEncontradoException("O arquivo selecionado para exclusao, nao existe. Por favor, tente novamente!");
        }

        Objects.requireNonNull(documento).setStatus(StatusDocumento.DESATIVADO);
        documentosRepository.save(documento);
        return documento;
    }

    public Documento updateStatusDocumento(Long documentoId) {
        Documento documento = documentosRepository.findById(documentoId).orElse(null);

        if(documento == null) {
            throw new ArquivoNaoEncontradoException("O arquivo nao pode ser atualizado, pois, nao existe!");
        }

        documento.setStatus(validarDocumento(documento));
        documentosRepository.save(documento);

        return documento;
    }

    public StatusDocumento validarDocumento(Documento documento) {
        if(LocalDateTime.now().isAfter(documento.getDataVencimento())) {
            return StatusDocumento.VENCIDO;
        }

        return documento.getStatus();
    }
}
