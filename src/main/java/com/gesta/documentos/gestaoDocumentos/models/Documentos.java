package com.gesta.documentos.gestaoDocumentos.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "documentos_fornecedores")
@Data
public class Documentos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String mimeType;

    @Column(nullable = false)
    private String caminho;
}
