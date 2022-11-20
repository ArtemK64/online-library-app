package ru.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.library.models.Book;
import ru.library.models.GetBook;
import ru.library.repositories.BookRepository;
import ru.library.repositories.GetBookRepository;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class GetBookController {
    @Autowired
    private GetBookRepository getBookRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/get")
    public String getBook(@ModelAttribute GetBook getBook) {
        return "get";
    }

    @GetMapping("/get_results")
    public String getBookResults() {
        return "get_results";
    }

    @GetMapping("get_err")
    public String getErr() {
        return "get_err";
    }

    @PostMapping("/get")
    public String findBook(@ModelAttribute("getBook") @Valid GetBook getBook, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "get";
        }

        List<Book> books = bookRepository.findAll();
        for (Book book: books) {
            if (book.getTitle().equalsIgnoreCase(getBook.getNameOfBook())) {
                String currentDate = String.valueOf(LocalDate.now());
                getBook.setWasTaken(currentDate);
                getBookRepository.save(getBook);
                return "redirect:/get_results";
            }
        }

        return "redirect:/get_err";
    }
}