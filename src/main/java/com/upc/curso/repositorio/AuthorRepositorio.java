package com.upc.curso.repositorio;

import com.upc.curso.entidades.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepositorio extends JpaRepository<Author, Long> {
    @Query("SELECT p FROM Author p WHERE p.nameAuthor like %:prefijo%")
    public List<Author> obtenerReportePorNombre(@Param("prefijo") String prefijo);
    public List<Author> findByNameAuthorStartingWith(String prefix);
   public List<Author> findByEmailAuthorIs(String email);
}
