package com.gesta.documentos.gestaoDocumentos.controllers;

import com.gesta.documentos.gestaoDocumentos.exceptions.ArmazenamentoArquivoException;
import com.gesta.documentos.gestaoDocumentos.models.Documento;
import com.gesta.documentos.gestaoDocumentos.vo.DocumentoFormVO;
import com.gesta.documentos.gestaoDocumentos.services.DocumentosService;
import com.gesta.documentos.gestaoDocumentos.vo.DocumentoResponseVO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/documentos")
public class DocumentosController {
    private final DocumentosService documentoService;

    public DocumentosController(DocumentosService documentoService) {
        this.documentoService = documentoService;
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> createDocumento(@ModelAttribute DocumentoFormVO documento) {
        try {
            documentoService.create(documento);
            return ResponseEntity.ok("Documento criado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<DocumentoResponseVO>> readDocumentos() {
        try {
            List<DocumentoResponseVO> documentos = documentoService.getAll();
            return ResponseEntity.ok(documentos);
        } catch (Exception e) {
            throw new ArmazenamentoArquivoException("Erro ao tentar carregar documentos!", e);
        }
    }

    @GetMapping("/{documentosId}")
    public ResponseEntity<DocumentoResponseVO> readDocumento(@PathVariable long documentoId) {
        DocumentoResponseVO documento = documentoService.get(documentoId);
        return ResponseEntity.ok(documento);
    }

    @PutMapping("/update/status/")
    public ResponseEntity<DocumentoResponseVO> updateDocumento(@RequestBody Documento documento) {
        DocumentoResponseVO documentoAtualizado = documentoService.updateStatusDocumento(documento);
        return ResponseEntity.ok(documentoAtualizado);
    }

    @DeleteMapping("/{documentosId}")
    public ResponseEntity<String> deleteDocumento(@PathVariable long documentoId) {
        documentoService.delete(documentoId);
        return ResponseEntity.ok("Documento deletado com sucesso!");
    }
}
