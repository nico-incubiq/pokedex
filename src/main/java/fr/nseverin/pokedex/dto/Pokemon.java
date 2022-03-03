package fr.nseverin.pokedex.dto;

public record Pokemon (
    String name,
    String description,
    String habitat,
    boolean isLegendary
) {};
