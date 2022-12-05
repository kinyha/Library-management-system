package ru.bratchikov.springcourse.Project2Boot.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.bratchikov.springcourse.Project2Boot.models.Book;
import ru.bratchikov.springcourse.Project2Boot.repositories.BooksRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BooksServiceTest {
    @Autowired
    private BooksService booksService;

    //@Autowired
//    @MockBean
//    private BooksRepository booksRepository;

    @Test
    void findOne() {
        booksService.findOne(1);
        booksService.findOne(2);
        assertEquals(1, booksService.findOne(1).getBookId());
    }

//    @Test
//    void findAll() {
//        booksRepository.save(new Book("Title1", "TestAuthor1", 1));
//        booksRepository.save(new Book("Title2", "TestAuthor2", 2));
//        List<Book> books = booksService.findAll(true);
//
//        assertEquals(2, books.size());
//    }

}