Bokhylla
Et personlig bokbibliotek der jeg kan holde styr på bøker jeg har lest, leser nå, eier men ikke har lest ennå, og ønsker meg. Bygget som et hobbyprosjekt for å lære Spring Boot.
Hva appen gjør
Bokhylla lar deg holde oversikt over hele boksamlingen din på ett sted. Hver bok har en tittel, forfatter, sidetall og en status som forteller hvor i leseprosessen den er. 
Du kan legge til, redigere og slette bøker, søke i samlingen, filtrere på status og sortere lista, alt fra et nettlesergrensesnitt som åpnes lokalt når du kjører programmet.
De fire statusene en bok kan ha:

Leser nå: boka du er i gang med
I hylla, ulest: bøker du eier, men ikke har lest ennå
Lest: ferdiglesste bøker
Ønskeliste: bøker du ennå ikke har skaffet deg

Teknologier:
Java 21 (LTS)
Spring Boot 4: rammeverket som binder alt sammen
Spring Web: gir REST-API-et
Spring Data JPA: håndterer lagring mot databasen
H2 Database: fil-basert database som lagrer data lokalt
Lombok: fjerner repeterende kode (getters/setters)
HTML/CSS/JavaScript: frontend som snakker med API-et
Gradle: byggeverktøy

Slik kjører du prosjektet

Sørg for at du har Java 21 installert.
Åpne prosjektet i IntelliJ IDEA.
Kjør BokhyllaApplication.
Når du ser Started BokhyllaApplication i konsollen, er appen i gang.
Åpne nettleseren på http://localhost:8080

Appen kjører lokalt på din egen maskin
Hvordan det henger sammen
Prosjektet er delt i to deler: en backend som lagrer og henter data, og en frontend som er det visuelle grensesnittet.
Backend består av fire Java-klasser. Book er selve bokmodellen, den blir til en tabell i databasen. ReadingStatus er de fire statusene en bok kan ha. 
BookRepository snakker med databasen og gir ferdige metoder for å lagre, hente og slette. BookController er REST-API-et som tar imot forespørsler fra nettsiden.
Frontend er en enkelt index.html-fil som ligger i src/main/resources/static/. 
Den viser skjema, liste og knapper, og sender forespørsler til API-et i bakgrunnen når du legger til, redigerer eller sletter bøker.

API-endepunkter
MetodeURLHva den gjørGET/booksHenter alle bøkerGET/books/status/{status}Henter bøker med en bestemt statusPOST/booksLegger til en ny bokPUT/books/{id}Oppdaterer en eksisterende bokDELETE/books/{id}Sletter en bok

Database
Bøkene lagres i en H2-database som skrives til fil under data/-mappa, slik at de overlever når appen startes på nytt.
Du kan inspisere databasen direkte mens appen kjører ved å åpne http://localhost:8080/h2-console (JDBC URL: jdbc:h2:file:./data/bokhylla, brukernavn: sa).

Mulige videreutviklinger:
Statistikk: antall bøker lest, sider totalt, fordeling per status
Terningkast / vurdering på hver bok
Lesedato: når en bok ble fullført
PostgreSQL
