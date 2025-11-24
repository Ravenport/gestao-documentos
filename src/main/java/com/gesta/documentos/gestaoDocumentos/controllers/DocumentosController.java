package com.gesta.documentos.gestaoDocumentos.controllers;

import com.gesta.documentos.gestaoDocumentos.exceptions.ArmazenamentoArquivoException;
import com.gesta.documentos.gestaoDocumentos.models.Documento;
import com.gesta.documentos.gestaoDocumentos.models.enums.StatusDocumento;
import com.gesta.documentos.gestaoDocumentos.models.events.GestaoDocumentoEvent;
import com.gesta.documentos.gestaoDocumentos.vo.DocumentoFormVO;
import com.gesta.documentos.gestaoDocumentos.services.DocumentosService;
import com.gesta.documentos.gestaoDocumentos.vo.DocumentoResponseVO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/documentos")
public class DocumentosController {
    private final DocumentosService documentoService;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public DocumentosController(DocumentosService documentoService, KafkaTemplate<String, String> kafkaTemplate) {
        this.documentoService = documentoService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> createDocumento(@ModelAttribute DocumentoFormVO documento) {
        try {
            Documento documentoSalvo = documentoService.create(documento);

            GestaoDocumentoEvent evento = new GestaoDocumentoEvent(documentoSalvo.getId(), documentoSalvo.getIdPortador(), documentoSalvo.getNome(), documentoSalvo.getStatus());
            kafkaTemplate.send("gestaoDocumentos", evento.toString());

            return ResponseEntity.ok("Documento criado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar/todos/portador/{portadorId}")
    public ResponseEntity<List<DocumentoResponseVO>> readDocumentosPorPortador(@PathVariable Long portadorId) {
        try {
            List<DocumentoResponseVO> documentos = documentoService.getAll(portadorId);
            return ResponseEntity.ok(documentos);
        } catch (Exception e) {
            throw new ArmazenamentoArquivoException("Erro ao tentar carregar documentos!", e);
        }
    }

    @GetMapping("/buscar/todos/documentos")
    public ResponseEntity<List<DocumentoResponseVO>> readDocumentos() {
        try {
            List<DocumentoResponseVO> documentos = documentoService.getAll();
            return ResponseEntity.ok(documentos);
        } catch (Exception e) {
            throw new ArmazenamentoArquivoException("Erro ao tentar carregar documentos!", e);
        }
    }

    @GetMapping("/buscar/documento/{documentosId}")
    public ResponseEntity<DocumentoResponseVO> readDocumento(@PathVariable Long documentosId) {
        DocumentoResponseVO documento = documentoService.get(documentosId);
        return ResponseEntity.ok(documento);
    }

    @PutMapping("/update/status/{documentoId}")
    public ResponseEntity<String> updateDocumento(@PathVariable Long documentoId) {
        Documento documentoAtualizado = documentoService.updateStatusDocumento(documentoId);

        GestaoDocumentoEvent evento = new GestaoDocumentoEvent(documentoAtualizado.getId(), documentoAtualizado.getIdPortador(), documentoAtualizado.getNome(), documentoAtualizado.getStatus());
        kafkaTemplate.send("gestaoDocumentos", evento.toString());

        return ResponseEntity.ok("Documento atualizado para o status: " + documentoAtualizado.getStatus().toString());
    }

    @DeleteMapping("/{documentosId}")
    public ResponseEntity<String> deleteDocumento(@PathVariable long documentosId) {
        Documento documentoDeletado = documentoService.delete(documentosId);

        GestaoDocumentoEvent evento = new GestaoDocumentoEvent(documentoDeletado.getId(), documentoDeletado.getIdPortador(), documentoDeletado.getNome(), documentoDeletado.getStatus());
        kafkaTemplate.send("gestaoDocumentos", evento.toString());

        return ResponseEntity.ok("Documento deletado com sucesso!");
    }
}
