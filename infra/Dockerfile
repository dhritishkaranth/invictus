FROM amazoncorretto:11
COPY "backend/target/invictus-*.war" "/app/app.war"

CMD java -Dspring.datasource.url=jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME} -Dspring.datasource.username=${DB_USER} -Dspring.datasource.password=${DB_PASS} -jar /app/app.war
