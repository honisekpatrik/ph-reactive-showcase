package sk.ph.as;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import sk.ph.model.check.Check;
import sk.ph.model.check.CheckBook;
import sk.ph.repository.CheckRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CheckAS {
    @Inject
    CheckRepository checkRepository;

    @WithTransaction
    public Uni<Void> assignChecks(CheckBook checkBook) {
        return checkRepository
                .listBestAvailable()
                .chain(checks -> process(checks, checkBook));
    }

    private Uni<Void> process(List<Check> checks, CheckBook checkBook) {
        BigDecimal requiredValue = checkBook.amount.multiply(BigDecimal.valueOf(0.8));
        BigDecimal accumValue = BigDecimal.ZERO;
        List<Check> assignedChecks = new ArrayList<>();


        for (Check c : checks) {
            if (accumValue.compareTo(requiredValue) >= 0) break;

            c.checkBook = checkBook;
            assignedChecks.add(c);
            accumValue = accumValue.add(c.amount);
        }

        return Multi.createFrom().iterable(assignedChecks)
                .onItem()
                .transformToUniAndConcatenate(c -> checkRepository.persist(c))
                .toUni()
                .replaceWithVoid();
    }
}
