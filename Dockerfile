FROM adoptopenjdk/openjdk16
ADD target/quiz-1.0-SNAPSHOT.jar quiz-1.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "quiz-1.0-SNAPSHOT.jar"]