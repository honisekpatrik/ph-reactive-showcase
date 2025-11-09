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
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@ApplicationScoped
public class CheckInefficientAS {
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
        AtomicReference<BigDecimal> accumValue = new AtomicReference<>(BigDecimal.ZERO);

        return Multi.createFrom().iterable(checks)
                .onItem().transformToUni(check -> {
                    BigDecimal currentValue = accumValue.get();

                    if (currentValue.compareTo(requiredValue) >= 0) {
                        return Uni.createFrom().nullItem();
                    }

                    check.checkBook = checkBook;
                    accumValue.set(currentValue.add(check.amount));
                    return checkRepository.persist(check);
                })
                .concatenate()
                .collect().asList()
                .replaceWithVoid();
    }
}
