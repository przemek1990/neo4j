Sample project that show how to access data from Neo4j using Spring Data

*Build an executable JAR*

If you are using Gradle, you can run the application using ./gradlew bootRun.

You can build a single executable JAR file that contains all the necessary dependencies, classes, and resources. This makes it easy to ship, version, and deploy the service as an application throughout the development lifecycle, across different environments, and so forth.

./gradlew build
Then you can run the JAR file:

java -jar build/libs/*.jar
If you are using Maven, you can run the application using mvn spring-boot:run. Or you can build the JAR file with mvn clean package and run the JAR by typing:

java -jar target/*.jar