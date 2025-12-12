package com.gesta.documentos.gestaoDocumentos.models.events;

import com.gesta.documentos.gestaoDocumentos.models.enums.DocumentosTipoEvento;
import com.gesta.documentos.gestaoDocumentos.models.enums.StatusDocumento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GestaoDocumentoEvent {
    private DocumentosTipoEvento tipoEvento;
    private Long idDocumento;
    private Long idFornecedor;
    private String nomeDocumento;
    private StatusDocumento statusDocumento;
}
