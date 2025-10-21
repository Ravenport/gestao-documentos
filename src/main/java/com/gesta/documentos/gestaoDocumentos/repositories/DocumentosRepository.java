package com.gesta.documentos.gestaoDocumentos.repositories;

import com.gesta.documentos.gestaoDocumentos.models.Documentos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentosRepository extends CrudRepository<Documentos, Long> {
}
