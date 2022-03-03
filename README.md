# Pokedex API
## Getting started (running the app locally)
## Design principles
## Code organisation

## Documentation
You can access the API documentation at [/swagger-ui.html](http://localhost:8080/swagger-ui.html).

## To change for production
## Storage
- There is realistically not that many Pokemons out there, it would be more
efficient to just download the full list and store it locally in a file in
the project. Gotta cach'em all!
- Same thing for the descriptions transformations, this will never change at
runtime either, so it could be computed in advance and stored in a file.
## Security
- Disable all management endpoints but the *health* one. Set up a different
profile to have different configurations in prod and dev, or use DotEnv.
- Add rate limiting, even more considering we are hitting external downstream
services; it would be considerate
