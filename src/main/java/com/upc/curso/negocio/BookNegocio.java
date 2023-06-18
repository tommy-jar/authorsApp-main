package com.upc.curso.negocio;

import com.upc.curso.entidades.Book;
import com.upc.curso.repositorio.BookRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookNegocio {
    @Autowired
    private BookRepositorio bookRepositorio;
    @Transactional
    public Book insertar(Book book){
        return bookRepositorio.save(book);
    }
    public List<Book> list(){
        return bookRepositorio.findAll();
    }


}
