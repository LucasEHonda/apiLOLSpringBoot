FROM openjdk:11
WORKDIR /app
ADD /target/*.jar /
RUN mv /*.jar api-unikode.jar
ENTRYPOINT java -jar api-unikode.jar