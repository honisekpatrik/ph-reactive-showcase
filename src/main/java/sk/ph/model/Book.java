package sk.ph.model;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "book")
public class Book extends PanacheEntity {
    public String title;
    @Column(name = "release_date")
    public LocalDate releaseDate;
    public String genre;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    public Author author;

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", genre='" + genre + '\'' +
                ", author=" + author +
                ", id=" + id +
                '}';
    }
}
