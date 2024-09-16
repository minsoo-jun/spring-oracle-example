# 基本イメージ
FROM openjdk:21-jdk-slim

# 作業フォルダ
WORKDIR /app

# JAR ファイルCopy
COPY build/libs/web-0.0.1-SNAPSHOT.jar web.jar

# 実行
CMD ["java", "-jar", "web.jar"]