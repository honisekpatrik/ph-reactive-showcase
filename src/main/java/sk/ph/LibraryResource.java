package sk.ph;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import sk.ph.repository.AuthorRepository;
import sk.ph.repository.BookRepository;

@Path("/lib")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LibraryResource {
    @Inject
    AuthorRepository authorRepository;
    @Inject
    BookRepository bookRepository;
    @Inject
    Logger logger;

    @GET
    @Path("author")
    public Uni<Response> listAllAuthors() {
        return authorRepository
                .listAll()
                .onItem()
                .transform(authors -> Response.ok(authors).build());
    }

    @DELETE
    @Path("author/{id}")
    public Uni<Response> deleteAuthor(@PathParam("id") Long id) {
        return authorRepository
                .deleteById(id)
                .replaceWith(Uni.createFrom().item(Response.ok().build()));
    }

    @GET
    @Path("book")
    public Uni<Response> listAllBooks() {
        return bookRepository
                .listAll()
                .onItem()
                .transform(books -> Response.ok(books).build());
    }
}
