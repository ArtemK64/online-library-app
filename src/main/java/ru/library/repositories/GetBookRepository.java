package ru.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.library.models.GetBook;

@Repository
public interface GetBookRepository extends JpaRepository<GetBook, Long> { }