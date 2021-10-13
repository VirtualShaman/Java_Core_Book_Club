package com.coding_dojo.book_club.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.coding_dojo.book_club.models.Book;
import com.coding_dojo.book_club.repositories.BookRepository;

@Service
public class BookService {
	private final BookRepository bookRepository;
	 
	 public BookService(BookRepository bookRepository) {
	     this.bookRepository = bookRepository;
	 }
	 
	 public Book oneBook(Long id) {
	    Optional<Book> optionalBook = bookRepository.findById(id);
	    if(optionalBook.isPresent()) {
	    	return optionalBook.get();
	    } else {
	    	return null;
	    }
	 }
	 public List<Book> allBooks() {
	     return bookRepository.findAll();
	 }
	 
	 public Book createBook(Book book) {
	     return bookRepository.save(book);
	 }
	 public Book updateBook(Book book) {
	     return bookRepository.save(book);
	 }
	 public void delete(Long id) {
		 bookRepository.deleteById(id);
	 }
	 
}
