## Beer App

A spring boot app which uses postgress/elastic search on the backend and 
react and grommet on the front end. 

### Development Instructions

#### Prerequisites
You must have the following installed to run this application

1. Java 10
2. Maven 3
3. yarn & node (latest)
4. docker (latest)

### Build & Run Instructions 

````
# in one window, run this. It starts the postgresql db and the elastic search server
docker-compose up

# In another window run this.
cd ./src/main/webapp
yarn start

# In a 3rd window (or an IDE), run this.
mvn clean install
java -jar ./target/BeerApp-*-SNAPSHOT.jar


````