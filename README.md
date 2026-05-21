# Bokhylla

Et personlig bokbibliotek for å holde styr på bøker jeg har lest, leser, eier men ikke har kommet til ennå, og ønsker meg. Bygget som et hobbyprosjekt for å lære Spring Boot.

<img width="1440" height="664" alt="Skjermbilde 2026-05-21 kl  12 19 45" src="https://github.com/user-attachments/assets/cb2fbd1d-b57b-4cd5-b446-268061de2c61" />

<img width="1285" height="577" alt="Skjermbilde 2026-05-21 kl  12 20 28" src="https://github.com/user-attachments/assets/79a651e3-d5da-4510-8652-f7b92b0a4268" />


## Om prosjektet

Bokhylla samler hele boksamlingen på ett sted. Hver bok har tittel, forfatter, sidetall og en status som sier hvor den er i leseprosessen. Fra nettlesergrensesnittet kan du legge til, redigere og slette bøker, søke i samlingen, filtrere på status og sortere listen.

En bok kan ha én av fire statuser:

- **Leser nå**: boken du er i gang med
- **I hylla, ulest**: bøker du eier, men ikke har lest
- **Lest**: ferdiglesste bøker
- **Ønskeliste**: bøker du ennå ikke har skaffet deg, men ønsker deg

## Teknologi

Backend er bygget med Java 21 og Spring Boot 4. Spring Web leverer REST-API-et, Spring Data JPA håndterer lagring, og dataene legges i en fil-basert H2-database. Lombok kutter ned på repeterende kode. Frontend er ren HTML, CSS og JavaScript. Prosjektet bygges med Gradle og kan kjøres i Docker.

## Komme i gang

Du trenger Java 21 installert (`java -version` for å sjekke).

Åpne prosjektet i IntelliJ IDEA, kjør `BokhyllaApplication`, og åpne `http://localhost:8080` i nettleseren når konsollen melder `Started BokhyllaApplication`. Appen kjører lokalt på din egen maskin.

### Med Docker

Hvis du har Docker Desktop, kan du kjøre alt med én kommando:

```bash
docker compose up --build
```

Appen blir tilgjengelig på `http://localhost:8080`. Databasen lagres i `data/`-mappa via et volume, så bøkene overlever omstart. Stopp med `docker compose down`.

## Hvordan det henger sammen

Prosjektet er delt i en backend som lagrer og henter data, og en frontend som er grensesnittet.

Backend består av fire klasser. `Book` er bokmodellen og blir til en tabell i databasen. `ReadingStatus` definerer de fire statusene. `BookRepository` snakker med databasen og gir metoder for å lagre, hente og slette. `BookController` er REST-API-et som tar imot forespørsler fra nettsiden.

Frontend er en enkelt `index.html` under `src/main/resources/static/`. Den viser skjema, liste og knapper, og kaller API-et i bakgrunnen når du legger til, redigerer eller sletter en bok.

## API

| Metode | URL | Beskrivelse |
|--------|-----|-------------|
| `GET` | `/books` | Henter alle bøker |
| `GET` | `/books/status/{status}` | Henter bøker med en bestemt status |
| `POST` | `/books` | Legger til en ny bok |
| `PUT` | `/books/{id}` | Oppdaterer en eksisterende bok |
| `DELETE` | `/books/{id}` | Sletter en bok |

## Database

Mens appen kjører kan databasen inspiseres på `http://localhost:8080/h2-console` med JDBC URL `jdbc:h2:file:./data/bokhylla` og brukernavn `sa`.

## Videre arbeid

Noen ting jeg vurderer å legge til:

- Statistikk over samlingen: antall lest og sider totalt
- Lesedato for fullførte bøker
- Bytte fra H2 til PostgreSQL
- Innlogging
