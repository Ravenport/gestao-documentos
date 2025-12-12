package com.gesta.documentos.gestaoDocumentos;

import com.gesta.documentos.gestaoDocumentos.config.ConfiguracaoArmazenamentoArquivos;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
@EnableConfigurationProperties({ConfiguracaoArmazenamentoArquivos.class})
public class GestaoDocumentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoDocumentosApplication.class, args);
	}

}
