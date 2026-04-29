FROM openjdk:21-ea-jdk-slim

# Установка локали
RUN apt-get update && apt-get install -y locales && rm -rf /var/lib/apt/lists/* \
    && localedef -i ru_RU -c -f UTF-8 -A /usr/share/locale/locale.alias ru_RU.UTF-8

# Настройка локали по умолчанию
ENV LANG=ru_RU.utf8

# Создание директории приложения
WORKDIR /app

# Копирование JAR-файла и keystore
COPY target/ExamPortal-0.0.1.jar /app/app.jar
COPY keystore.jks /app/keystore.jks

# Экспорт порта
EXPOSE 8080

# Запуск приложения
CMD ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]