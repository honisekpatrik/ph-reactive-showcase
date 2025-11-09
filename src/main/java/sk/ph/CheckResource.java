package sk.ph;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sk.ph.as.CheckBookAS;

import java.util.List;

@Path("check")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CheckResource {
    @Inject
    CheckBookAS checkBookAS;

    @GET
    @WithSession
    public Uni<Response> fillChecks() {
        return checkBookAS.fillCheckBooks(Uni.createFrom().item(List.of(1L)))
                .onItem().transform(_v -> Response.ok().build());
    }
}
