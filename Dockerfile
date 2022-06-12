FROM openjdk:11
ENV APP_HOME=/user/app
WORKDIR $APP_HOME
COPY ./build/libs/foodtruck-notification-0.0.1-SNAPSHOT.jar Notification.jar

CMD ["java", "-jar", "Notification.jar"]
