package com.gesta.documentos.gestaoDocumentos.models.events;

import com.gesta.documentos.gestaoDocumentos.models.enums.StatusDocumento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GestaoDocumentoEvent {
    private Long idDocumento;
    private Long idFornecedor;
    private String nomeDocumento;
    private StatusDocumento statusDocumento;

    @Override
    public String toString() {
        return "idDocumento:" + idDocumento + ",idFornecedor:" + idFornecedor + ",nome:" + nomeDocumento + ",status:" + statusDocumento;
    }
}
