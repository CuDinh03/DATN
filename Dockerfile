# Sử dụng hình ảnh Maven với JDK 17
FROM maven:3.9.3-openjdk-17 as build

# Sao chép mã nguồn vào hình ảnh
COPY . /app
WORKDIR /app

# Kiểm tra rằng tệp pom.xml có mặt trong thư mục làm việc
RUN ls -l /app


# Xây dựng ứng dụng bằng Maven
RUN mvn clean package

# Tạo hình ảnh mới từ hình ảnh OpenJDK slim để chạy ứng dụng
FROM openjdk:17-jdk-slim

# Expose cổng mà ứng dụng sẽ sử dụng
EXPOSE 8080

# Sao chép file JAR từ bước build
COPY --from=build /app/target/DATN-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9091


# Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
