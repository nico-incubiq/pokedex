package fr.nseverin.pokedex.model;

public record Pokemon (
    String name,
    String description,
    String habitat,
    boolean isLegendary
) {};
