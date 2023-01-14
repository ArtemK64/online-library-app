package ru.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.library.repositories.GetBookRepository;

@Service
public class GetBookService {
    private final GetBookRepository getBookRepository;

    @Autowired
    public GetBookService(GetBookRepository getBookRepository) {
        this.getBookRepository = getBookRepository;
    }

    // methods
}