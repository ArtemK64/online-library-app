package ru.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.library.models.ReturnBook;

@Repository
public interface ReturnBookRepository extends JpaRepository<ReturnBook, Long> { }