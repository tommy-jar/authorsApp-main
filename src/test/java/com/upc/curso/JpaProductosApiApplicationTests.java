package com.upc.curso;

import com.upc.curso.entidades.Author;
import com.upc.curso.negocio.AuthorNegocio;
import com.upc.curso.repositorio.AuthorRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
class JpaProductosApiApplicationTests {
	@MockBean
	private AuthorRepositorio authorRepositorio;
	@Autowired
	private AuthorNegocio authorNegocio;

	@Test
	void contextLoads() {
	}

	@Test
		void testTest(){
		}

}
