package com.gesta.documentos.gestaoDocumentos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ArmazenamentoArquivoException extends RuntimeException {
    public ArmazenamentoArquivoException(String mensagem) {
        super(mensagem);
    }

    public ArmazenamentoArquivoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
