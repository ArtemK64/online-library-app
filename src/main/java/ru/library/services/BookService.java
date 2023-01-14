package ru.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.library.models.Book;
import ru.library.repositories.BookRepository;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public int getPriceByTitle(String title) {
        List<Book> books = bookRepository.findAll();
        for (Book book: books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book.getFineForLoss();
            }
        }
        return 0;
    }

    public boolean isExist(String title) {
        List<Book> books = bookRepository.findAll();
        for (Book book: books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return true;
            }
        }
        return false;
    }
}