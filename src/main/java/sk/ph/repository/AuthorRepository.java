package sk.ph.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import sk.ph.model.Author;

@ApplicationScoped
public class AuthorRepository implements PanacheRepository<Author> {
}
