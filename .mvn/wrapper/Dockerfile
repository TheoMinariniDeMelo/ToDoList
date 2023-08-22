# Use a imagem base do MySQL
FROM mysql:latest
# Configurações do banco de dados MySQL
ENV MYSQL_ROOT_PASSWORD=0000
ENV MYSQL_DATABASE=list
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=0000

# Copia o arquivo SQL para inicialização do banco de dados
COPY schema.sql /docker-entrypoint-initdb.d/

# Exponha a porta padrão do MySQL
EXPOSE 3306

# Use o arquivo de inicialização do Docker para configurar o aplicativo Spring Boot
CMD ["java", "-jar", "/app.jar"]

# Copie o arquivo JAR do seu aplicativo Spring Boot para o contêiner
COPY ../../target/maven-wrapper.jar /app.jar

