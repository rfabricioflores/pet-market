# Pet Market

A safe website for pet advertisement with authentication.
The backend api is written in Java with Springboot and the website with Angular.

## App demo

Frontend:
<a href="https://pet-market.fabricioflores.se" target="_blank">https://pet-market.fabricioflores.se</a>
<br>
Backend:
<a href="https://pet-market-api.fabricioflores.se" target="_blank">https://pet-market-api.fabricioflores.se</a>

## Requirements

- docker
- node with npm
- jdk 21 (java development kit)
- maven
- Set the `JAVA_HOME` environment variable in your machine matching your jdk installation e.g `C:\Program Files\Java\jdk-21`

## Recomendations

- Use vscode as your code editor
- Install the Nx tool globally with npm `npm i -g nx`

## Start the project

- Start the development database with docker, run `docker compose up -d`
- Install the depencencies for the project with `npm install`
- To start the development server run `nx serve <app>` or `npx nx serve <app>` if you don't have the Nx tool installed globally.
  Apps: `backend` `frontend`

## Running tasks

To execute tasks with Nx use the following syntax:

```
nx <target> <app> <...options>
```

e.g

```
nx build frontend --configuration=production
```

You can also run multiple targets:

```
nx run-many -t serve -p frontend backend
```

**Targets**: `serve` `build` `test` `clean`
**Apps:** `backend` `frontend`
