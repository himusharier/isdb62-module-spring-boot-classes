package com.himu.isdb.relational_db_operations_hibernate.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.himu.isdb.relational_db_operations_hibernate.dto.BookDto;
import com.himu.isdb.relational_db_operations_hibernate.model.Book;
import com.himu.isdb.relational_db_operations_hibernate.model.Classroom;
import com.himu.isdb.relational_db_operations_hibernate.repository.BookRepository;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final ClassroomService classroomService;
    
    public BookService(BookRepository bookRepository, ClassroomService classroomService) {
        this.bookRepository = bookRepository;
        this.classroomService = classroomService;
    }

    public Book saveBook(BookDto bookDto) {
        Integer classId = bookDto.getClazzId();
        Classroom clazz = classroomService.getClassroom(classId);

        Book book = new Book();
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setPublisher(bookDto.getPublisher());
        if (clazz != null) {
            book.setClazz(clazz);
        }
        return bookRepository.save(book);
    }

    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }

    public Book updateBook(Integer id, Book book) {
        Optional<Book> bookById = bookRepository.findById(id);
        
        if (bookById.isPresent()) {
            Book aBook = new Book();
            if (book.getName() != null) {
                aBook.setName(book.getName());
            }
            if (book.getAuthor() != null) {
                aBook.setAuthor(book.getAuthor());
            }
            if (book.getPublisher() != null) {
                aBook.setPublisher(book.getPublisher());
            }
            if (book.getClazz() != null) {
                Integer classId = book.getClazz().getId();
                Classroom clazz = classroomService.getClassroom(classId);
                if (clazz == null) {
                    throw new IllegalArgumentException("class not found");
                }
                aBook.setClazz(clazz);
            }
            return bookRepository.save(aBook);

        } else {
            throw new IllegalArgumentException("book not found");
        }
    }

    public Book getBook(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }
    
}
