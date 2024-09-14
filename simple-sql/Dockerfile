# 基本イメージ
FROM openjdk:21-jdk-slim

# 作業フォルダ
WORKDIR /app

# JAR ファイルCopy
COPY build/libs/api-0.0.1-SNAPSHOT.jar api-oracle.jar

# 実行
CMD ["java", "-jar", "api-oracle.jar"]