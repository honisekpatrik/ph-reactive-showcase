package sk.ph.model;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "author")
public class Author extends PanacheEntity {
    public String name;
    public String surname;
    @Column(name = "birth_date")
    public LocalDate birthDate;
    @Column(name = "death_date")
    public LocalDate deathDate;
    public String nationality;

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", deathDate=" + deathDate +
                ", nationality='" + nationality + '\'' +
                ", id=" + id +
                '}';
    }
}
