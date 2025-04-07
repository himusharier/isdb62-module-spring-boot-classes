package com.himu.isdb.relational_db_operations_hibernate.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.himu.isdb.relational_db_operations_hibernate.dto.BookDto;
import com.himu.isdb.relational_db_operations_hibernate.model.Book;
import com.himu.isdb.relational_db_operations_hibernate.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/book")
@Tag(name = "Book Controller", description = "API for book management")
public class BookController {
	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@PostMapping
	@Operation(summary = "Save book to DB", description = "Save book to DB description")
	public ResponseEntity<Book> saveBook(@RequestBody BookDto bookDto) {
		Book saved = bookService.saveBook(bookDto);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Book>> getAllBook() {
		List<Book> books = bookService.getAllBook();
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable Integer id) {
		bookService.deleteBook(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestBody Book book) {
		Book updated = bookService.updateBook(id, book);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> getBook(@PathVariable Integer id) {
		Book book = bookService.getBook(id);
		return new ResponseEntity<>(book, HttpStatus.OK);
	}

}
