# Invictus -- Stronger Together 

This repo holds the code and materials for our CS 297P project.

## Frontend instructions

You need to have NodeJS installed (v14, latest LTS release). Yarn is recommended as the package manager, instead of NPM.

To install all prerequisites, navigate to `./frontend/react-ui/` and run `yarn install`.

Create the `.env` file in `./frontend/react-ui/` directory with the following contents:
```
REACT_APP_GOOGLE_MAPS_API_KEY=<>
REACT_APP_PROTOCOL=http
REACT_APP_DOMAIN=localhost:9091
```

Steps for obtaining the Google Maps key are explained later.
The other two variables together tell the frontend the domain name of the backend. The given example works when you are running in development mode (both frontend and backend in the same machine).

To to run in the local development server mode, use `yarn start`.
Set the environment variable `BROWSER=none` to suppress browser auto-launch. 

## Database setup

The easiest way to set up the Postgres database for development is via Docker.

Pull the required Docker image (first time only): `docker pull postgres:13`

To intialize the database container:
```
docker run -p 5432:5432 -e POSTGRES_PASSWORD=<password> -e POSTGRES_USER=invictusadmin -e POSTGRES_DB=invictus -d postgres
```

Once the container is up and running, find out the container's ID (`docker ps`) and then use it to start a shell in the container:
```
docker exec -it <container_ID> bash
```

You will now be in the container's shell. Connect to the psql CLI by running `psql -d invictus -U invictusadmin`.

Paste and run the contents of the schema (`./backend/src/resources/schema.sql`).
This will set up the schema as well as the admin user. This is required because currently there is no way to create an admin user from the application itself.

To stop the container: `docker stop <ID>`

To start the container again: `docker start <ID>`.

You can find out the IDs of stopped containers by running `docker ps -a`.

## Backend setup

The easiest way to be able to build the Maven project is via IntelliJ IDEA IDE. From within the IDE you can run the project directly, or produce a build artifact by running `mvn package`, which generates the executable WAR file.

Before running the backend, the Google Maps API key should be exported as an environmental variable named `GOOGLE_API_KEY` (see the next section).

To start the backend (database should already be up):
```
java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/invictus -Dspring.datasource.username=invictusadmin -Dspring.datasource.password=<db_password> -jar invictus-0.0.1.war
```
 
Here, replace the values for the parameters as needed (DB connection string should be the same, the username and password are the same used during database creation).

## Google Maps API

You need to sign up for [Google Cloud](https://cloud.google.com). You may be asked for credit card information, but the usage for development purposes should be well below the charging threshold.

Follow [these instructions](https://developers.google.com/maps/gmp-get-started) to set up the account and obtain an API key.

When producing builds for actual deployment, remember to use API keys that are restricted ([more info](https://developers.google.com/maps/api-security-best-practices)). Otherwise, your API key can be re-used by others, racking up your bills.

You will need the Geocoding API as well as the Maps Javascript API enabled.

# Misc.

The `./infra` directory contains a script that can be used to load dummy data in bulk into the backend. It reads data from `groups.txt` and `users.txt` and fires off API calls to the backend (whose URL is hardcoded) to create those users and groups. Requires Python 3.

## Future improvements

Use React Router instead of the weird conditional rendering used currently in `App.js`.

Use [Facebook Login](https://developers.facebook.com/docs/facebook-login/web) instead of our own authentication mechanism.

Keep the front-end dropdown options in sync with what is allowed by the backend.

Use an alternative Maps API solution such as Mapbox, since Google Maps might be too expensive for real-world usage.