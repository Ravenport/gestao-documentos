package com.gesta.documentos.gestaoDocumentos.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gesta.documentos.gestaoDocumentos.exceptions.ArquivoSerializacaoException;
import com.gesta.documentos.gestaoDocumentos.models.events.GestaoDocumentoEvent;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class DocumentosSerializer implements Serializer<GestaoDocumentoEvent> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String s, GestaoDocumentoEvent documentoEvent) {
        if(documentoEvent == null) {
            return null;
        }

        try {
            return objectMapper.writeValueAsBytes(documentoEvent);
        } catch (Exception e) {
            throw new ArquivoSerializacaoException(e.getMessage());
        }
    }
}
