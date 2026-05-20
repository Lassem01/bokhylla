package com.lasse.bokhylla;

public enum ReadingStatus {
    READ,          // boka er ferdig lest
    READING,       // du leser boka akkurat nå
    WANT_TO_READ,  // boka du eier, men ikke har lest ennå
    WISHLIST       // boka du ønsker deg / vil kjøpe
}