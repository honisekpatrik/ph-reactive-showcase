package sk.ph.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import sk.ph.model.Book;

@ApplicationScoped
public class BookRepository implements PanacheRepository<Book> {
}
