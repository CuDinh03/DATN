# DỰ ÁN TỐT NGHIỆP 
Web bán quần áo

# Tech stack:

Build tool: maven >= 3.9.5

Java: 17 || 21 

Framework: Spring boot 3.2.x

DBMS: MySQL


# Prerequisites:

- Java 17 || 21
- MySQL (8.x hoặc 9.x; project dùng driver MySQL 8)

# Chuẩn bị MySQL (máy đã cài MySQL 8 / 9):

1. **Tạo database** (MySQL client hoặc Workbench):
   ```sql
   CREATE DATABASE IF NOT EXISTS DATN
     CHARACTER SET utf8mb4
     COLLATE utf8mb4_unicode_ci;
   ```

2. **Cấu hình kết nối** trong `env.local.sh` (sửa cho đúng máy bạn sử dụng):
   - `DB_URL=jdbc:mysql://localhost:3306/DATN?useUnicode=true&characterEncoding=utf8&connectionCollation=utf8mb4_unicode_ci&serverTimezone=UTC`
   - `DB_USERNAME=root` (hoặc user MySQL của bạn)
   - `DB_PASSWORD=<mật khẩu MySQL>`

   Nếu MySQL chạy port khác (vd 3307), đổi `3306` trong URL.

# Start application:

## Chạy theo profile (dev / prod)

- **Dev** (mặc định): `ddl-auto=update`, hiện SQL, CORS cho phép tất cả.
- **Prod**: `ddl-auto=validate`, bật Flyway, CORS lấy từ env.

```bash
# Dev (mặc định)
./mvnw spring-boot:run

# Prod
SPRING_PROFILES_ACTIVE=prod ./mvnw spring-boot:run
```

## Dùng biến môi trường (env)

Secrets (DB, mail, JWT) lấy từ env.
Tạo file `env.local.sh` (không commit).

```bash
source ./env.local.sh
./mvnw spring-boot:run
```

Hoặc set từng biến: `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `MAIL_USERNAME`, `MAIL_PASSWORD`, `JWT_SIGNER_KEY`, `SERVER_PORT` (mặc định 9091). Prod nên thêm `CORS_ALLOWED_ORIGINS` (danh sách origin cách nhau bằng dấu phẩy).


# Lệnh chạy DATN:

```bash
cd ../DATN
source ./env.local.sh
./mvnw spring-boot:run
```

Backend chạy tại **http://localhost:9091**. API base: `http://localhost:9091/api`.

*Lưu ý: Máy dùng MySQL 9.x (vd 9.6) vẫn dùng được driver MySQL 8 trong project. Nếu gặp lỗi kết nối, có thể nâng `mysql-connector-java` trong `pom.xml` lên bản mới hơn (vd 8.4.x).*


# Build application:

./mvnw clean package






