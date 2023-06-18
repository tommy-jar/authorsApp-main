package com.upc.curso.entidades;

import javax.persistence.*;
import java.time.LocalDate;
@Entity(name = "books")
public class Book {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   @Column(name = "nameBook", length = 45, nullable = false)
   private String nameBook;
   @Column(name = "publicationDateBook", nullable = false)
   private LocalDate publicationDateBook;
   @Column(name = "nHojas", nullable = false)
   private int nHojas;
   @ManyToOne(targetEntity = Author.class)
   @JoinColumn(name = "author_id", referencedColumnName = "id")
   private Author author;

   public Book(Long id, String nameBook, LocalDate publicationDateBook, int nHojas, Author author) {
      this.id = id;
      this.nameBook = nameBook;
      this.publicationDateBook = publicationDateBook;
      this.nHojas = nHojas;
      this.author = author;
   }

   public Book() {
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getNameBook() {
      return nameBook;
   }

   public void setNameBook(String nameBook) {
      this.nameBook = nameBook;
   }

   public LocalDate getPublicationDateBook() {
      return publicationDateBook;
   }

   public void setPublicationDateBook(LocalDate publicationDateBook) {
      this.publicationDateBook = publicationDateBook;
   }

   public int getnHojas() {
      return nHojas;
   }

   public void setnHojas(int nHojas) {
      this.nHojas = nHojas;
   }

   public Author getAuthor() {
      return author;
   }

   public void setAuthor(Author author) {
      this.author = author;
   }

   @Override
   public String toString() {
      return "Book{" +
              "id=" + id +
              ", nameBook='" + nameBook + '\'' +
              ", publicationDateAuthor=" + publicationDateBook +
              ", nHojas=" + nHojas +
              ", author=" + author +
              '}';
   }
}
