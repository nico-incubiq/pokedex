# Pokedex API
## Don't want to install anything?
Try using the online OpenApi documentation [https://fun-pokedex.herokuapp.com/swagger-ui.html](https://fun-pokedex.herokuapp.com/swagger-ui.html).

## Getting started running the app locally
The easiest way to get started is to use Docker, as you won't have to configure your
local Java environment. Download and install Docker here [https://docs.docker.com/desktop/](https://docs.docker.com/desktop/)
if you do not have it already.

Also make sure you have git installed. If you don't, follow the instructions for your platform
at [https://git-scm.com/download](https://git-scm.com/download).

[![asciicast](https://asciinema.org/a/GGy0xfnDb925mcYqpbqBPxQt8.png)](https://asciinema.org/a/GGy0xfnDb925mcYqpbqBPxQt8)

### Check out the codebase
- From the terminal, cd to your favorite project directory, and run the following
```bash
git clone git@github.com:nico-incubiq/pokedex.git
```

### Building the app image
- From the directory where you checked-out the codebase, run the following command in the terminal
```bash
docker build --tag fun-pokedex .
```

### Starting the app
- Run the following command in the terminal to run the app locally on **port 80**
```bash
docker run --detach --publish 80:8080 --name running-fun-pokedex fun-pokedex
```
- You can check the app is running and healthy using the below command
```bash
docker container ls
```
- As soon as it displays `(healthy)`, you know the app is running
- Open your favorite browser at [http://localhost/swagger-ui.html](http://localhost/swagger-ui.html) to start using the app
- Or test from the terminal with `curl` and `jq` (if you have them)
```bash
curl -s http://localhost/pokemon/mewtwo | jq
```

### Winding down
- Stop and remove the container
```bash
docker container rm --force running-fun-pokedex
```

## Design principles
## Code organisation

## Documentation
You can access the API documentation at [/swagger-ui.html](http://localhost:8080/swagger-ui.html).

## To change for production
## Storage
- There is realistically not that many Pokemons out there, it would be more
efficient to just download the full list and store it locally in a file in
the project. Gotta Cach'Em All!
- Same thing for the descriptions transformations, this is a static operation
that will never change at runtime either, so it could be computed in advance
and stored in a file.

## Security
- Disable all management endpoints but the *health* one. Set up a different
profile to have different configurations in prod and dev, or use DotEnv.
- Add rate limiting, even more considering we are hitting external downstream
services; it would be considerate

# TODO
- Dockerfile
- Negative test cases + error handling
  - API errors
  - pokemon not found 404, which currently 500 the server
  - funtranslations rate limiting 429, which currently 500 the server,
    this is what TL meant in the guidelines; return non-translated string!
- Decide what to do if API return null, rather than having all the Optionals
- Code comments where necessary
- OpenApi comments
