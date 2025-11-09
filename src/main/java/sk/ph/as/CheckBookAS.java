package sk.ph.as;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import sk.ph.repository.CheckBookRepository;

import java.util.List;

@ApplicationScoped
public class CheckBookAS {
    @Inject
    CheckBookRepository checkBookRepository;
    @Inject
    CheckInefficientAS checkInefficientAS;

    public Uni<Void> fillCheckBooks(Uni<List<Long>> idsToFill) {
        return idsToFill
                .chain(ids -> Multi.createFrom().iterable(ids)
                        .onItem()
                        .transformToUniAndConcatenate(this::fillCheckBook)
                        .collect().asList()
                ).replaceWithVoid();
    }

    private Uni<Void> fillCheckBook(Long id) {
        return checkBookRepository.findById(id)
                .onItem()
                .ifNull().failWith(new NotFoundException())
                .chain(checkBook -> checkInefficientAS.assignChecks(checkBook));
    }
}
