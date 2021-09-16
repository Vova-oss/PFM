# Образ, на основании которого я делаю свой образ
FROM openjdk:15

# копирую jar, который сгенерировал с помощью команды "mvn clean install -DskipTests=true"
# в контейнер
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar
COPY ./target/classes/ ./.

# запуск jar файла при помощи команды java -jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]