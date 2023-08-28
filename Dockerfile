FROM openjdk:20
LABEL authors="theo"

WORKDIR /appSpring
COPY ./target .

# Defina o comando de entrada diretamente, sem usar "&&"
ENTRYPOINT ["java", "-jar", "target/classes/application/ListApplication.class", "ping", "mysql:3306"]
