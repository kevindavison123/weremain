# weremain
Social Progress fund website

Important developer notes: 
Create new branches for every change. 
Do not commit into master
Always Create a Merge Request when branch is ready

We use something called Maven, we do not have a main() for our app
to use build the project run `mvn clean install`
later to test the rest api we will use `mvn run:jetty` 

## docker information ##
1. dockerfiles are stored in <root>/.docker, along with a handy readme
2. the development environment is equipped with nodemon so you can change
files in src and it should auto-magically pick them up and rebuild
3. docker compose will default to **development** environment
4. for now docker basically just takes everything you see in this git repo
stuffs it into a MEAN stack (minus the Mongo) and runs it.
5. once launched goto 127.0.0.1:3000 to see the application!

## launch docker files ##
development:
[sudo] docker-compose up --build -d

production:
[sudo] docker-compose -f docker-compose.yml -f docker-compose.prod.yml up --build -d
