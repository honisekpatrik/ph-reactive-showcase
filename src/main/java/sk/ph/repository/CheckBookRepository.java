package sk.ph.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import sk.ph.model.check.CheckBook;

@ApplicationScoped
public class CheckBookRepository implements PanacheRepository<CheckBook> {
}
