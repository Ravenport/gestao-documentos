package com.gesta.documentos.gestaoDocumentos.repositories;

import com.gesta.documentos.gestaoDocumentos.models.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentosRepository extends JpaRepository<Documento, Long> {
    Iterable<Documento> findAllByIdPortador(Long idPortador);
}
