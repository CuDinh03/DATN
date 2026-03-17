# DỰ ÁN TỐT NGHIỆP – Web bán quần áo (MT-Shirt)

Ứng dụng web bán hàng gồm **Backend** (Spring Boot) và **Frontend** (Angular). Tài liệu này hướng dẫn clone, khởi động và sử dụng cả hai phần.

---

## Tech stack

| Thành phần | Công nghệ |
|------------|-----------|
| Backend    | Java 17 || 21, Spring Boot 3.2.x, Maven ≥ 3.9.5 |
| Database   | MySQL 8.x / 9.x |
| Frontend   | Angular 18+, Node.js (LTS khuyến nghị) |

---

## Prerequisites

- **Java 17||21** (để chạy Backend)
- **Maven** ≥ 3.9.5 (hoặc dùng `./mvnw` trong repo)
- **MySQL** 8.x hoặc 9.x
- **Node.js** 18+ và **npm** (để chạy Frontend)

- Java 17 || 21
- MySQL (8.x hoặc 9.x; project dùng driver MySQL 8)

## 1. Clone dự án

Repo này chứa **Backend** (Spring Boot). Frontend (Angular) thường nằm ở https://github.com/CuDinh03/FE_DATN

```bash
# Clone Backend
git clone https://github.com/CuDinh03/DATN 
# Cline Frontend
git clone https://github.com/CuDinh03/FE_DATN

cd ../DATN
```

2. **Cấu hình kết nối** trong `env.local.sh` (sửa cho đúng máy bạn sử dụng):
   - `DB_URL=jdbc:mysql://localhost:3306/DATN?useUnicode=true&characterEncoding=utf8&connectionCollation=utf8mb4_unicode_ci&serverTimezone=UTC`
   - `DB_USERNAME=root` (hoặc user MySQL của bạn)
   - `DB_PASSWORD=<mật khẩu MySQL>`

---

## 2. Khởi động Backend (DATN)

### 2.1. Tạo database MySQL

Trong MySQL (client hoặc Workbench) chạy:

```sql
CREATE DATABASE IF NOT EXISTS DATN
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
```

### 2.2. Cấu hình biến môi trường

Tạo file `env.local.sh` tại thư mục gốc của Backend (cùng cấp với `pom.xml`), **không commit** file này:

```bash
# env.local.sh – Sửa cho đúng trên máy bạn đã clone 

# MySQL
export DB_URL="jdbc:mysql://localhost:3306/DATN?useUnicode=true&characterEncoding=utf8&connectionCollation=utf8mb4_unicode_ci&serverTimezone=UTC"
export DB_USERNAME="root"
export DB_PASSWORD="<mật-khẩu-MySQL>"

# JWT (bắt buộc cho đăng nhập; dev có thể dùng key mặc định trong application.properties)
export JWT_SIGNER_KEY="your-secret-key-at-least-32-characters-long"

# Tùy chọn
# export SERVER_PORT=9091
# export MAIL_USERNAME=""
# export MAIL_PASSWORD=""
```

Nếu MySQL chạy port khác (vd 3307), đổi `3306` trong `DB_URL`.

Secrets (DB, mail, JWT) lấy từ env.
Tạo file `env.local.sh` (không commit).

```bash
# Trong thư mục Backend (DATN)
source ./env.local.sh
./mvnw spring-boot:run
```

- Backend chạy tại: **http://localhost:9091**
- API base: **http://localhost:9091/api**

**Chạy với profile prod:**

# Lệnh chạy DATN:

```bash
cd ../DATN
source ./env.local.sh
./mvnw spring-boot:run
```

- Ứng dụng chạy tại: **http://localhost:4200**

Mở trình duyệt và truy cập: **http://localhost:4200**

---

## 4. Sử dụng ứng dụng

### 4.1. Trang chủ / Khách hàng

- **URL:** http://localhost:4200/trang-chu (hoặc http://localhost:4200)
- Xem sản phẩm, thêm giỏ hàng, đặt hàng, xem lịch sử đơn hàng sau khi đăng nhập.

### 4.2. Đăng nhập

- **URL:** http://localhost:4200/login
- **Tài khoản admin (mặc định sau khi seed):**  
  - Tên đăng nhập: `admin`  
  - Mật khẩu: (theo cấu hình seed trong Backend, thường `admin` hoặc đã đổi)
- Sau khi đăng nhập admin → chuyển tới **Trang quản trị**.

### 4.3. Trang quản trị (Admin)

- **URL:** http://localhost:4200/admin/dash-board
- Các mục chính:
  - **Thống kê:** Tổng quan bán hàng, doanh thu, đơn hàng, giao dịch gần đây.
  - **Bán hàng:** http://localhost:4200/admin/shopping
  - **Sản phẩm:** Quản lý sản phẩm, danh mục, màu sắc, chất liệu, hình ảnh, kích thước, thương hiệu.
  - **Tài khoản:** http://localhost:4200/admin/tai-khoan
  - **Hóa đơn:** http://localhost:4200/admin/hoa-don
  - **Voucher:** http://localhost:4200/admin/voucher
  - **Khách hàng:** http://localhost:4200/admin/khach-hang

---

## 5. Tóm tắt lệnh

| Việc | Lệnh |
|------|------|
| Chạy Backend | `cd DATN` → `source ./env.local.sh` → `./mvnw spring-boot:run` |
| Chạy Frontend | `cd <FE>` → `npm install` → `npm start` |
| Build Backend | `./mvnw clean package` |

**Thứ tự khuyến nghị:** Chạy Backend trước, sau đó chạy Frontend. Truy cập http://localhost:4200 để dùng ứng dụng.

---

## 6. Lưu ý

- **CORS:** Profile `dev` đã cấu hình CORS cho phép `*`; FE chạy ở port khác (vd 4200) gọi API bình thường.
- **JWT:** Nếu không set `JWT_SIGNER_KEY` trong env, Backend dùng key mặc định (chỉ cho dev). Production bắt buộc set key đủ mạnh trong env.
- **MySQL 9.x:** Có thể dùng driver MySQL 8 trong project. Nếu lỗi kết nối, có thể nâng version `mysql-connector-java` trong `pom.xml`.
