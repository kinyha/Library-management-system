package ru.bratchikov.springcourse.Project2Boot.controllers;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.bratchikov.springcourse.Project2Boot.models.Book;
import ru.bratchikov.springcourse.Project2Boot.models.Comment;
import ru.bratchikov.springcourse.Project2Boot.models.Person;
import ru.bratchikov.springcourse.Project2Boot.services.CommentService;
import ru.bratchikov.springcourse.Project2Boot.services.PeopleService;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentsController {
    private final CommentService commentService;
    private final PeopleService peopleService;

    public CommentsController(CommentService commentService, PeopleService peopleService) {
        this.commentService = commentService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String getComment() {
        return "redirect:books";
    }

    @PostMapping()
    public String createComment(@ModelAttribute("comment") Comment comment,
                                @RequestParam("content") String content,
                                @ModelAttribute("book") Book book) {

        comment.setContent(content);
        comment.setCreatedAt(new Date());
        commentService.save(comment);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<Person> person = peopleService.getPersonByName(username);
        person.ifPresent(comment::setPerson);
        commentService.giveComment(comment.getCommentId(), book);
        return "redirect:books";
    }

}
