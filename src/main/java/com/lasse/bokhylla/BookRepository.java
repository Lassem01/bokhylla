package com.lasse.bokhylla;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// Repository er laget som snakker med databasen.
// <Book, Long> betyr: "dette repositoryet jobber med Book-objekter,
// og id-en deres er av typen Long".
public interface BookRepository extends JpaRepository<Book, Long> {

    // Trenger bare å DEKLARERE metoden,
    // så skriver Spring selve databasespørringen automatisk basert på navnet.
    // "findByStatus" betyr "finn alle bøker der status-feltet matcher".
    // Eksempel: findByStatus(READ) gir deg alle bøker du har lest.
    List<Book> findByStatus(ReadingStatus status);
}