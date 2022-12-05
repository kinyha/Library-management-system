package ru.bratchikov.springcourse.Project2Boot.services;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bratchikov.springcourse.Project2Boot.models.Book;
import ru.bratchikov.springcourse.Project2Boot.models.Comment;
import ru.bratchikov.springcourse.Project2Boot.repositories.CommentRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> findAll(boolean sortByData) {
        if (sortByData) {
            return commentRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
        } else {
            return commentRepository.findAll();
        }
    }

    public Comment findOne(int id) {
        Optional<Comment> foundBook = commentRepository.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Comment comment) {
       commentRepository.save(comment);
    }

    @Transactional
    public void delete(int id) {
        commentRepository.deleteById(id);
    }

    @Transactional
    public void giveComment(int commentId, Book selectedBook) {
        commentRepository.findById(commentId).ifPresent(
                comment -> {
                    comment.setCreatedAt(new Date());
                    commentRepository.save(comment);
                    if (selectedBook.getComments() == null) {
                        selectedBook.setComments(new ArrayList<>());
                    }
                    selectedBook.getComments().add(comment);
                    System.out.println(selectedBook.getComments());
                }
                );
    }


}
