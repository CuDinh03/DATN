# Sử dụng Ubuntu làm hình ảnh cơ sở
FROM ubuntu:latest as build

# Cập nhật và cài đặt OpenJDK và Maven
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

# Sao chép mã nguồn vào hình ảnh
COPY . /app
WORKDIR /app

# Xây dựng ứng dụng bằng Maven
RUN mvn clean package

# Tạo hình ảnh mới từ hình ảnh OpenJDK slim
FROM openjdk:17-jdk-slim

# Expose cổng mà ứng dụng sẽ sử dụng
EXPOSE 8080

# Sao chép file JAR từ bước build
COPY --from=build /app/target/DATN.jar app.jar

# Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
