package com.upc.curso.controller;

import com.upc.curso.dtos.AuthorDTO;
import com.upc.curso.entidades.Author;
import com.upc.curso.negocio.AuthorNegocio;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;
//@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class AuthorController {
   @Autowired
   public AuthorNegocio negocio;
   Logger logger = LoggerFactory.getLogger(AuthorController.class);

   @GetMapping("/authors")
   public ResponseEntity<List<AuthorDTO>>  obtenerAutores(){
       List<Author> list = negocio.listado();
       List<AuthorDTO> listDto = convertToLisDto(list);
	   return new ResponseEntity<List<AuthorDTO>>(listDto,HttpStatus.OK);
   }

    @GetMapping("/authors/{nombre}")
    public ResponseEntity<List<AuthorDTO>>  obtenerNombreAutores(@PathVariable(value = "nombre") String nombre){
        List<Author> list = negocio.obtenerReportePorDescripcion(nombre);
        List<AuthorDTO> listDto = convertToLisDto(list);
        return new ResponseEntity<List<AuthorDTO>>(listDto,HttpStatus.OK);
    }

   @PostMapping("/author")
   public ResponseEntity<AuthorDTO> crearAutor(@RequestBody AuthorDTO authorDTO) {
       Author author;
       try {
           logger.debug("Creando objeto");
           author = convertToEntity(authorDTO);
           authorDTO = convertToDto(negocio.registrar(author));
       }catch(Exception e){
           logger.error("Error de creación",e);
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);
       }
	   return new ResponseEntity<AuthorDTO>(authorDTO, HttpStatus.OK);
   }

   @PutMapping("/author")
   public ResponseEntity<AuthorDTO> actualizarAutor(@RequestBody AuthorDTO authorDetalle) {
       AuthorDTO authorDTO;
       Author author;
       try {
           author = convertToEntity(authorDetalle);
           logger.debug("Actualizando producto");
           author = negocio.actualizarAuthor(author);
           logger.debug("Producto Actualizado");
           authorDTO = convertToDto(author);
           return new ResponseEntity<AuthorDTO>(authorDTO, HttpStatus.OK);
       } catch (Exception e) {
           logger.error("Error de Actualización ", e);
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo actualizar, sorry");
       }
   }
   
   @DeleteMapping("/author/{codigo}")
   public ResponseEntity<AuthorDTO> borrarAutor(@PathVariable(value = "codigo") Long codigo){
       Author author;
       AuthorDTO authorDTO;
       try {
           author = negocio.borrarProducto(codigo);
           logger.debug("Eliminando objeto");
           authorDTO = convertToDto(author);
           return new ResponseEntity<AuthorDTO>(authorDTO, HttpStatus.OK);
       } catch (Exception e) {
           logger.error("Error de Eliminación ", e);
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo eliminar, sorry");
       }
   }


    @GetMapping("/author/{codigo}")
    public ResponseEntity<AuthorDTO> obtenerEntidad(@PathVariable(value = "codigo") Long codigo){
        Author author;
        AuthorDTO authorDTO;
        try {
            logger.debug("Buscando entidad");
            author = negocio.buscar(codigo);
            authorDTO = convertToDto(author);
        }catch(Exception e){
            logger.error("Error de Obtener Entidad");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mi mensaje");
        }
        return new ResponseEntity<AuthorDTO>(authorDTO, HttpStatus.OK);
    }

    private AuthorDTO convertToDto(Author author) {
        ModelMapper modelMapper = new ModelMapper();
        AuthorDTO authorDTO = modelMapper.map(author, AuthorDTO.class);
        return authorDTO;
    }

    private Author convertToEntity(AuthorDTO authorDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Author post = modelMapper.map(authorDTO, Author.class);
        return post;
    }

    private List<AuthorDTO> convertToLisDto(List<Author> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
   
}
