package com.gesta.documentos.gestaoDocumentos.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gesta.documentos.gestaoDocumentos.models.enums.StatusDocumento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoResponseVO {
    private Long idPortador;
    private String nome;
    private String descricao;
    private String mimeType;
    private String caminho;
    private StatusDocumento status;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataVencimento;
}
