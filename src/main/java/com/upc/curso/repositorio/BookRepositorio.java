package com.upc.curso.repositorio;

import com.upc.curso.entidades.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookRepositorio extends JpaRepository<Book, Long> {
}
