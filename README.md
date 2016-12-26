# Weremain

## Using Git and Github

### Cloning

`git clone git@github.com:kevindavison123/weremain.git weremain`

This will copy the project into a folder called weremain in the current directory you are in. 

### Branching 

`git checkout -b my-branch-name` Will create a branch based off of the current branch you are using. **NOTE** Always use master to create a new branch.

`git branch` will tell you what branch you are in. 

`git checkout my-existing-branch` will switch to a branch that already exists. 

`git fetch origin [master]` will fetch any branches from the repo that you wish to work on.

### Committing and Pushing to your branch

`git add -A` will add all unversioned files to be committed. 

`git commit -m "Add a description about what changes you made"` This will save your changes in your local repo on your computer

`git push origin my-branch-name` This will push your commit(s) to the remote repo under your branch name.

**NOTE** DO NOT PUSH TO MASTER UNDER ANY CIRCUMSTANCES. 

## Project Structure 

Almost all HTML, TS, and CSS files will under the `src/app` folder directory. The exception is `index.html`, however that will be rarely touched. 

The `home` page will be under the name pattern `app.component.`, any auxilary pages will be under the `src/app/pages` directory. 

From here a single page will have three file types, a HTML, CSS, and TS (type script) file in the same hierarchy. For example:

`/src/app/pages`
  
  `-/about`
  
  `--about.component.html`
  
  `--about.component.css`
  
  `--about.component.ts`
  
  That is the structural pattern for all the pages in this project.
  
  **NOTE** Do not add work to the `/htmlwork/` directory as we are migrating that to the angular2 structure. Any and all work will be deleted. 
  

## NPM

Under the project directory install `npm` and the `npm angular2` in order to run and build the website. 
`npm start` will compile and start the project on `localhost:4200`. There you will be able to see the changes. 

For more info check out how to install and run using `npm` 






## Building and Running project with Angular2

This project was generated with [angular-cli](https://github.com/angular/angular-cli) version 1.0.0-beta.24.

## Development server
Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive/pipe/service/class/module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `-prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).
Before running the tests make sure you are serving the app via `ng serve`.

## Deploying to Github Pages

Run `ng github-pages:deploy` to deploy to Github Pages.

## Further help

To get more help on the `angular-cli` use `ng help` or go check out the [Angular-CLI README](https://github.com/angular/angular-cli/blob/master/README.md).
