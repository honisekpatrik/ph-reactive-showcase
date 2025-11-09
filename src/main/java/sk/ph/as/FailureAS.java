package sk.ph.as;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import org.jboss.logging.Logger;

import java.time.Duration;
import java.util.List;

@ApplicationScoped
public class FailureAS {
    @Inject
    CheckBookAS checkBookAS;
    @Inject
    Logger logger;

    public Uni<Void> tryFilling() {
        return checkBookAS.fillCheckBooks(Uni.createFrom().item(List.of(1L, 2L, 3L)))
                .onFailure(PersistenceException.class).invoke(e -> logger.error(e.getMessage(), e))
                .onFailure().invoke(e -> logger.error(e.getMessage()));
    }

    public Uni<Void> retryFilling() {
        return checkBookAS.fillCheckBooks(Uni.createFrom().item(List.of(1L, 2L, 3L)))
                .onFailure().retry().withBackOff(Duration.ofSeconds(3)).atMost(5);
    }
}
