package com.gesta.documentos.gestaoDocumentos.vo;

import com.gesta.documentos.gestaoDocumentos.models.enums.StatusDocumento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoResponseVO {
    private Long idPortadorDocumento;
    private String nome;
    private String descricao;
    private String mimeType;
    private String caminho;
    private StatusDocumento status;
    private LocalDateTime dataVencimento;
}
