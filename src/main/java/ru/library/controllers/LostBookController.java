package ru.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.library.models.Book;
import ru.library.services.BookService;

import javax.validation.Valid;

@Controller
public class LostBookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/lost")
    public String lost(@ModelAttribute Book book) {
        return "lost";
    }

    @GetMapping("/lost_results")
    public String lostResults() {
        return "lost_results";
    }

    @PostMapping("/lost")
    public String lostBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "lost";
        }

        if (bookService.isExist(book.getTitle())) {
            long fineForLoss = bookService.getPriceByTitle(book.getTitle());
            model.addAttribute("fineForLoss", "The fine for loss for book: " + book.getTitle() + " is: " + fineForLoss + "$");

            return "lost_results";
        }

        return "get_err";
    }
}