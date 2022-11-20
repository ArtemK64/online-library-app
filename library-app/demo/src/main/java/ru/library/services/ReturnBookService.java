package ru.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.library.repositories.ReturnBookRepository;

@Service
public class ReturnBookService {
    private final ReturnBookRepository returnBookRepository;

    @Autowired
    public ReturnBookService(ReturnBookRepository returnBookRepository) {
        this.returnBookRepository = returnBookRepository;
    }

    // methods
}