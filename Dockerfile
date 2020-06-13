FROM openjdk:11
EXPOSE 8080

ARG TELEGRAM_API_ID
ARG TELEGRAM_API_HASH
ARG TELEGRAM_DB_ENCRYPTION_KEY
ARG TELEGRAM_DB_DIR
ARG DB_HOST
ARG DB_USER
ARG DB_PASSWORD

ENV TELEGRAM_API_ID ${TELEGRAM_API_ID}
ENV TELEGRAM_API_HASH ${TELEGRAM_API_HASH}
ENV TELEGRAM_DB_ENCRYPTION_KEY ${TELEGRAM_DB_ENCRYPTION_KEY}
ENV TELEGRAM_DB_DIR ${TELEGRAM_DB_DIR}
ENV DB_HOST ${DB_HOST}
ENV DB_USER ${DB_USER}
ENV DB_PASSWORD ${DB_PASSWORD}

ARG JAR_FILE=cf-app/target/cf-app-1.0.0-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
