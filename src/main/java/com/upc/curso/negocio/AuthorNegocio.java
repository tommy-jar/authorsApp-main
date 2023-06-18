package com.upc.curso.negocio;

import com.upc.curso.entidades.Author;
import com.upc.curso.repositorio.AuthorRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service //no agnostica
public class AuthorNegocio {
    @Autowired
    private AuthorRepositorio authorRepositorio;

    @Transactional
    public Author registrar(Author author){
       return authorRepositorio.save(author);
    }
    public Author buscar(Long codigo) throws Exception {
        return authorRepositorio.findById(codigo).
                orElseThrow(() -> new Exception("No se encontró entidad"));
    }
    public List<Author> listado(){
        return authorRepositorio.findAll();
    }

    public List<Author> obtenerReportePorDescripcion(String prefijo){
        //return authorRepositorio.obtenerReportePorNombre(prefijo);
        return authorRepositorio.findByNameAuthorStartingWith(prefijo);
    }

    public Author actualizarAuthor(Author author) throws Exception {
        authorRepositorio.findById(author.getId()).orElseThrow(() -> new Exception("No se encontró entidad"));
        return authorRepositorio.save(author);
    }

    public Author borrarProducto(Long codigo) throws Exception{
        Author author = authorRepositorio.findById(codigo).orElseThrow(() -> new Exception("No se encontró entidad"));
        authorRepositorio.delete(author);
        return author;
    }
}
