package com.gesta.documentos.gestaoDocumentos.exceptions;

public class ArquivoSerializacaoException extends RuntimeException {
    public ArquivoSerializacaoException(String message) {
        super(message);
    }

    public ArquivoSerializacaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
