package com.gesta.documentos.gestaoDocumentos.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "file")
public class ConfiguracaoArmazenamentoArquivos {
    private String uploadDir;
}
