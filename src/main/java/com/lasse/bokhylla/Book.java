package com.lasse.bokhylla;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Entity forteller Spring/JPA at denne klassen tilsvarer en tabell i databasen.
// Hver Book-instans blir en rad i tabellen.
@Entity
// @Data er fra Lombok og lager automatisk getters, setters, toString,
// equals og hashCode for meg, så slipper jeg å skrive alt det selv.
@Data
// @NoArgsConstructor lager en tom konstruktør, som JPA krever for å bygge objekter.
@NoArgsConstructor
public class Book {

    // @Id markerer dette feltet som primærnøkkelen.
    @Id
    // @GeneratedValue gjør at databasen automatisk fyller inn en ny, unik id
    // hver gang jeg lagrer en bok. Man trenger altså aldri sette id-en selv.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @NotBlank er en valideringsregel: tittelen kan ikke være tom eller bare mellomrom.
    // Hvis noen prøver å lagre en bok uten tittel, får de feilmeldingen under.
    @NotBlank(message = "Tittel kan ikke være tom")
    private String title;

    // Forfatter, vanlig tekstfelt, ingen spesielle regler.
    private String author;

    // Antall sider. Integer (ikke int) slik at feltet kan være tomt/null hvis ukjent.
    private Integer pages;

    // @Enumerated(EnumType.STRING) gjør at lesestatusen lagres som lesbar tekst
    // i databasen (f.eks. "READ") i stedet for et tall (0, 1, 2...).
    // Det gjør databasen mye enklere å lese og forstå.
    @Enumerated(EnumType.STRING)
    private ReadingStatus status;
}