# ---- Steg 1: Bygg appen ----
# jeg bruker et image som har både JDK 21 og Gradle, slik at jeg kan bygge prosjektet.
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Kopier inn Gradle-wrapper og byggefiler først.
#  slipper å laste dem ned på nytt ved hver bygging.)
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Gjør wrapper-scriptet kjørbart og last ned avhengigheter
RUN chmod +x gradlew && ./gradlew dependencies --no-daemon || true

# Kopier inn resten av kildekoden og bygg en kjørbar jar-fil.
# -x test hopper over testene under bygging
COPY src src
RUN ./gradlew bootJar --no-daemon -x test

# ---- Steg 2: Kjør appen ----
# Starter med et mye mindre image som kun har Java-runtime (ikke hele JDK-en).
# Dette gjør sluttbildet lettere.
FROM eclipse-temurin:21-jre AS runtime

WORKDIR /app

# Kopier KUN den ferdige jar-fila fra byggesteget over.
COPY --from=build /app/build/libs/*.jar app.jar

# Appen lytter på port 8080 inne i containeren
EXPOSE 8080

# Kommandoen som starter appen når containeren kjører
ENTRYPOINT ["java", "-jar", "app.jar"]
