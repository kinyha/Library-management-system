package ru.bratchikov.springcourse.Project2Boot.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @NotEmpty(message = "Title is empty")
    @Size(min = 1, max = 30, message = "Title must be between 1 and 30 characters")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Author is empty")
    @Size(min = 1, max = 30, message = "Author must be between 1 and 30 characters")
    @Column(name = "author")
    private String author;

    @Min(value = 0, message = "Year should be greater than 0")
    @Column(name = "year")
    private int year;

    @Column(name = "created_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    //Hiber не видит
    @Transient
    private boolean expired;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Person person;

    @OneToMany(mappedBy = "book")
    private List<Comment> comments;


    public Book() {
    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> sortByDate(){
        comments.sort((c1, c2) -> c2.getCreatedAt().compareTo(c1.getCreatedAt()));
        return comments;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", createdAt=" + createdAt +
                ", person=" + person +
                '}';
    }
}
