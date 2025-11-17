package com.gesta.documentos.gestaoDocumentos.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoFormVO {
    private Long idPortadorDocumento;
    private String descricao;
    private String mimeType;
    private LocalDateTime vencimento;
    private MultipartFile file;
}
