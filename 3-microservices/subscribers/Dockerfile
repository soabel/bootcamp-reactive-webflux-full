FROM openjdk:11-jre
COPY target/subscribers-*SNAPSHOT.jar /opt/app.jar
#ENTRYPOINT ["java","-Dspring.profiles.active=prd","-jar","/opt/app.jar"]
ENTRYPOINT ["java","-jar","/opt/app.jar"]