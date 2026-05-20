package com.lasse.bokhylla;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// @RestController forteller Spring at denne klassen håndterer
// web-forespørsler (HTTP) og svarer med data (JSON).
@RestController
// @RequestMapping("/books") betyr at alle endepunktene under
// starter med /books. Altså: http://localhost:8080/books
@RequestMapping("/books")
public class BookController {

    // Trenger repositoryet for å kunne lagre og hente bøker.
    private final BookRepository repository;


    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    // GET /books henter ALLE bøker i bokhylla.
    // @GetMapping betyr at metoden svarer på GET-forespørsler.
    @GetMapping
    public List<Book> getAllBooks() {
        return repository.findAll();  // ferdig metode fra JpaRepository
    }

    // GET /books/status/READ henter alle bøker med en bestemt status.
    // {status} i URL-en fanges opp av @PathVariable og sendes inn i metoden.
    // Slik kan du f.eks. hente bare ønskelista: /books/status/WISHLIST
    @GetMapping("/status/{status}")
    public List<Book> getByStatus(@PathVariable ReadingStatus status) {
        return repository.findByStatus(status);
    }

    // POST /books legger til en ny bok.
    // @RequestBody gjør at JSON-en du sender inn blir omgjort til et Book-objekt.
    // @Valid sørger for at valideringsreglene (f.eks. @NotBlank på tittel) sjekkes
    // FØR boka lagres. Mangler tittel, blir den avvist automatisk.
    @PostMapping
    public Book addBook(@Valid @RequestBody Book book) {
        return repository.save(book);  // lagrer og returnerer boka (nå med id)
    }

    // PUT /books/1  → oppdaterer boka med id = 1.
    // Vi henter den eksisterende boka, endrer feltene, og lagrer på nytt.
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @Valid @RequestBody Book updatedBook) {
        // Finn boka som skal endres. Finnes den ikke, kaster vi en feil.
        Book book = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fant ingen bok med id " + id));

        // Oppdater feltene med de nye verdiene
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setPages(updatedBook.getPages());
        book.setStatus(updatedBook.getStatus());

        // Lagre den endrede boka (samme id beholdes)
        return repository.save(book);
    }

    // DELETE /books/1 sletter boka med id = 1.
    // {id} i URL-en fanges opp av @PathVariable.
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
    }
}