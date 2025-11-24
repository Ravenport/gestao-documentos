package com.gesta.documentos.gestaoDocumentos;

import com.gesta.documentos.gestaoDocumentos.config.ConfiguracaoArmazenamentoArquivos;
import com.gesta.documentos.gestaoDocumentos.models.enums.StatusDocumento;
import com.gesta.documentos.gestaoDocumentos.models.events.GestaoDocumentoEvent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
@EnableConfigurationProperties({ConfiguracaoArmazenamentoArquivos.class})
public class GestaoDocumentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoDocumentosApplication.class, args);
	}

}
