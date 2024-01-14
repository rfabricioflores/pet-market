# Pet Market

A safe website for pet advertisement with authentication.
The backend api is written in Java with Springboot and the website with Angular.

## Requirements

Recommended code editor: vscode

- node with npm
- jdk 21 (java development kit)
- maven

## Start the project

Install the depencencies for the project with `npm install`
To start the development server run `nx serve <app>` or `npx nx serve <app>` if you don't have the Nx tool installed globally.
Apps: `backend`, `frontend`

## Running tasks

To execute tasks with Nx use the following syntax:

```
nx <target> <app> <...options>
```

You can also run multiple targets:

```
nx run-many -t <target1> <target2>
```

..or add `-p` to filter specific apps

```
nx run-many -t <target1> <target2> -p <app1> <app2>
```

Targets can be defined in the `package.json` or `projects.json`. Learn more [in the docs](https://nx.dev/core-features/run-tasks).

**Targets**: `serve` `build`
