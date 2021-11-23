FROM openjdk:11
ADD target/AcceptedAssesment-0.0.1-SNAPSHOT.war AcceptedAssesment-0.0.1-SNAPSHOT.war
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "AcceptedAssesment-0.0.1-SNAPSHOT.war"]
CMD java com.jetbrains.Main


