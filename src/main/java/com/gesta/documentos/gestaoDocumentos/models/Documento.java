package com.gesta.documentos.gestaoDocumentos.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gesta.documentos.gestaoDocumentos.models.enums.StatusDocumento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "documentos_fornecedores")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Long idPortador;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String mimeType;

    @Column(nullable = false)
    private String caminho;

    @Column(nullable = false)
    private StatusDocumento status;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime dataVencimento;

    public Documento(Long idPortador, String nome, String descricao, String mimeType, String caminho, StatusDocumento status, LocalDateTime dataVencimento) {
        this.idPortador = idPortador;
        this.nome = nome;
        this.descricao = descricao;
        this.mimeType = mimeType;
        this.caminho = caminho;
        this.status = status;
        this.dataVencimento = dataVencimento;
    }
}
