FROM openjdk:8-alpine

COPY target/uberjar/chulsooboardv2.jar /chulsooboardv2/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/chulsooboardv2/app.jar"]
