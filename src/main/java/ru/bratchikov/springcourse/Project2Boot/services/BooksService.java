package ru.bratchikov.springcourse.Project2Boot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bratchikov.springcourse.Project2Boot.models.Book;
import ru.bratchikov.springcourse.Project2Boot.models.Comment;
import ru.bratchikov.springcourse.Project2Boot.models.Person;
import ru.bratchikov.springcourse.Project2Boot.repositories.BooksRepository;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll(boolean sortByYear) {
        if (sortByYear) {
            return booksRepository.findAll(Sort.by("year"));
        } else {
            return booksRepository.findAll();
        }
    }

    public List<Book> findWithPagination(Integer page, Integer booksPerPage, boolean sortByYear) {
        if (sortByYear) {
            return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
        } else {
            return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
        }

    }

    public Book findOne(int id) {
        Optional<Book> foundBook = booksRepository.findById(id);
        return foundBook.orElse(null);
    }

    public List<Book> searchByTitle(String query) {
        return booksRepository.findByTitleStartingWith(query);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setBookId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void giveBook(int bookId, Person selectedPerson) {
        booksRepository.findById(bookId).ifPresent(
                book -> {
                    book.setPerson(selectedPerson);
                    book.setCreatedAt(new Date());
                }
        );
    }

    @Transactional
    public void freeBook(int bookId) {
        booksRepository.findById(bookId).ifPresent(
                book -> {
                    book.setPerson(null);
                    book.setCreatedAt(null);
                }
        );
    }

    public Person getBookOwner(int bookId) {
        return booksRepository.findById(bookId).map(Book::getPerson).orElse(null);
    }

    public List<Comment> getComments(int bookId) {
        return booksRepository.findById(bookId).map(Book::getComments).orElse(null);
    }
}
