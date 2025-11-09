package sk.ph.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import sk.ph.model.check.Check;

import java.util.List;

@ApplicationScoped
public class CheckRepository implements PanacheRepository<Check> {
    public Uni<List<Check>> listBestAvailable() {
        return list("checkBook is null", Sort.descending("evaluation"));
    }
}
