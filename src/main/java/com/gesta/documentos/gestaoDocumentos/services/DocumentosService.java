package com.gesta.documentos.gestaoDocumentos.services;

import com.gesta.documentos.gestaoDocumentos.models.Documentos;
import com.gesta.documentos.gestaoDocumentos.repositories.DocumentosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentosService {
    private final DocumentosRepository documentosRepository;

    public DocumentosService(DocumentosRepository documentosRepository) {
        this.documentosRepository = documentosRepository;
    }

    public Documentos update(Documentos documentosAtualizado) {
        Documentos documentos = documentosRepository.findById(documentosAtualizado.getId()).get();

        documentos.setNome(documentosAtualizado.getNome());
        documentos.setDescricao(documentosAtualizado.getDescricao());
        documentos.setMimeType(documentosAtualizado.getMimeType());
        documentos.setCaminho(documentosAtualizado.getCaminho());

        return documentosRepository.save(documentos);
    }

    public Documentos get(long id) {
        return documentosRepository.findById(id).get();
    }

    public List<Documentos> getAll() {
        return (List<Documentos>) documentosRepository.findAll();
    }

    public void delete(long id) {
        documentosRepository.deleteById(id);
    }

    public void create(Documentos documentos) {
        documentosRepository.save(documentos);
    }
}
