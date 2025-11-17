package com.gesta.documentos.gestaoDocumentos.models;

import com.gesta.documentos.gestaoDocumentos.models.enums.StatusDocumento;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "documentos_fornecedores")
@Data
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Long idPortadorDocumento;

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

    @Column(nullable = false)
    private LocalDateTime dataVencimento;

    public Documento(Long idPortadorDocumento, String nome, String descricao, String mimeType, String caminho, StatusDocumento status, LocalDateTime dataVencimento) {
        this.idPortadorDocumento = idPortadorDocumento;
        this.nome = nome;
        this.descricao = descricao;
        this.mimeType = mimeType;
        this.caminho = caminho;
        this.status = status;
        this.dataVencimento = dataVencimento;
    }
}
