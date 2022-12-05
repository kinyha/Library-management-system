package ru.bratchikov.springcourse.Project2Boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bratchikov.springcourse.Project2Boot.models.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
