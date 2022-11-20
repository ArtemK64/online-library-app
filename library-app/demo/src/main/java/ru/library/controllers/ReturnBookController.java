package ru.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.library.dates.CalculateDays;
import ru.library.models.GetBook;
import ru.library.models.ReturnBook;
import ru.library.repositories.GetBookRepository;
import ru.library.repositories.ReturnBookRepository;

import javax.validation.Valid;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class ReturnBookController {

    @Autowired
    private GetBookRepository getBookRepository;
    @Autowired
    private ReturnBookRepository returnBookRepository;

    @GetMapping("/return")
    public String returnBook(@ModelAttribute ReturnBook returnBook) {
        return "return";
    }

    @GetMapping("/return_result")
    public String returnResult() {
        return "return_result";
    }

    @GetMapping("/return_err")
    public String returnErr() {
        return "return_err";
    }

    @PostMapping("/return")
    public String returnTakenBook(@ModelAttribute @Valid ReturnBook returnBook, BindingResult bindingResult, Model model) throws ParseException {
        if (bindingResult.hasErrors()) {
            return "return";
        }

        String currentDate = String.valueOf(LocalDate.now());
        returnBook.setWasReturned(currentDate);

        List<GetBook> getBooks = getBookRepository.findAll();
        for (GetBook getBook: getBooks) {
            if (getBook.getNameOfBook().equalsIgnoreCase(returnBook.getNameOfBook()) && getBook.getEmail().equalsIgnoreCase(returnBook.getEmail())) {
                returnBookRepository.save(returnBook);

                CalculateDays calculateDays = new CalculateDays(getBook.getWasTaken(), returnBook.getWasReturned());
                long difference = calculateDays.calcDays();
                StringBuilder stringBuilder = new StringBuilder();

                if (difference <= 30) {
                    stringBuilder.append("You took this book ").append(difference).append(" days ago. It is okay");
                } else {
                    stringBuilder.append("You took this book ").append(difference).append(" days ago. It is bad. You have a fine");
                }

                model.addAttribute("difference", stringBuilder);
                return "return_results";
            }
        }

        return "return_err";
    }
}