package com.upc.curso.controller;

import com.upc.curso.dtos.AuthorDTO;
import com.upc.curso.dtos.BookDTO;
import com.upc.curso.entidades.Author;
import com.upc.curso.entidades.Book;
import com.upc.curso.negocio.BookNegocio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    private BookNegocio bookNegocio;

    @PostMapping("/book")
    public ResponseEntity<BookDTO> insertar(@RequestBody BookDTO bookDTO){
        Book book = convertToEntity(bookDTO);
        BookDTO bookDTO1;
        try {
            book = bookNegocio.insertar(book);
            bookDTO1 = convertToDto(book);
        }catch (Exception e){
            e.printStackTrace();
            throw new  ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo insertar, sorry");
        }
        return new ResponseEntity<BookDTO>(bookDTO1, HttpStatus.OK);
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookDTO>> list(){
        List<Book> list;
        List<BookDTO> listDto;
        try{
        list = bookNegocio.list();
        listDto = convertToLisDto(list);
        }catch (Exception e){
            throw new  ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo listar");
        }
        return new ResponseEntity<List<BookDTO>>(listDto,HttpStatus.OK);
    }

    private BookDTO convertToDto(Book book) {
        ModelMapper modelMapper = new ModelMapper();
        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
        return bookDTO;
    }

    private Book convertToEntity(BookDTO bookDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Book post = modelMapper.map(bookDTO, Book.class);
        return post;
    }

    private List<BookDTO> convertToLisDto(List<Book> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
