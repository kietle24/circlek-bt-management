# Quản Lý Siêu Thị Tiện Lợi (Circle K Management)

Ứng dụng Java Desktop quản lý bán hàng cho cửa hàng tiện lợi, xây dựng bằng **Java Swing** và kết nối **SQL Server**. Hỗ trợ quản lý sản phẩm, khách hàng, nhân viên, hóa đơn và thống kê doanh thu.

---


| Chức năng | Mô tả |
|---|---|
| Đăng nhập | Xác thực tài khoản nhân viên |
| Bán hàng | Tạo đơn hàng, chọn sản phẩm, tính tiền |
| Sản phẩm | Thêm, sửa, xóa, tìm kiếm sản phẩm |
| Khách hàng | Quản lý thông tin khách hàng |
| Nhân viên | Quản lý nhân viên (chỉ Quản lý) |
| Hóa đơn | Xem lịch sử hóa đơn, chi tiết đơn hàng |
| Thống kê | Báo cáo doanh thu, sản phẩm bán chạy |

---

## Yêu cầu hệ thống

| Thành phần | Phiên bản tối thiểu |
|---|---|
| Java JDK | 8 trở lên |
| SQL Server | 2017 trở lên |
| IDE | Eclipse IDE for Java Developers |
| RAM | 4 GB trở lên |

---

## Cài đặt & Chạy dự án

### Bước 1 — Clone dự án

```bash
git clone <url-repository>
cd circlek-bt-management
```

Hoặc tải ZIP về và giải nén.

---

### Bước 2 — Cài đặt cơ sở dữ liệu (SQL Server)

> **Yêu cầu:** SQL Server đã cài đặt và đang chạy trên cổng `1433`.  
> Tài khoản SQL Server mặc định được dùng trong app: `sa` / `123`

1. Mở **SQL Server Management Studio (SSMS)**
2. Kết nối tới server `localhost`
3. Mở file `data/BangSieuThi.sql`
4. Chọn **Execute (F5)** để tạo database và nạp dữ liệu mẫu

Script sẽ tự động tạo database `QLSieuThi` với đầy đủ bảng và dữ liệu mẫu:
- **15 sản phẩm** (mì, bánh, nước uống, kem, snack, ...)
- **10 khách hàng**
- **5 nhân viên** (1 Quản lý + 4 Nhân viên)
- **23 hóa đơn** mẫu

---

### Bước 3 — Cấu hình kết nối database

Mở file `src/connectDB/ConnectDB.java` và kiểm tra thông tin kết nối:

```java
private static final String URL  = "jdbc:sqlserver://localhost:1433;databaseName=QLSieuThi;encrypt=true;trustServerCertificate=true;";
private static final String USER = "sa";
private static final String PASS = "123";
```

> ⚠️ Nếu SQL Server của bạn dùng tài khoản hoặc cổng khác, hãy chỉnh sửa `USER` và `PASS` tương ứng.

---

### Bước 4 — Mở dự án trong Eclipse

1. Mở **Eclipse IDE**
2. Vào **File → Import → Existing Projects into Workspace**
3. Chọn thư mục gốc của dự án (nơi chứa file `.project`)
4. Nhấn **Finish**

---

### Bước 5 — Thêm thư viện JDBC (nếu chưa có)

Driver JDBC đã có sẵn trong thư mục `lib/sqljdbc42.jar`. Cần thêm vào Build Path:

1. Chuột phải vào project → **Properties**
2. Vào **Java Build Path → Libraries**
3. Nhấn **Add JARs...**
4. Chọn `lib/sqljdbc42.jar` → **OK**

---

### Bước 6 — Chạy ứng dụng

Trong Eclipse:
- Tìm file `src/app/UIDangNhap.java`
- Chuột phải → **Run As → Java Application**

---

## Cấu trúc dự án

### Thư mục gốc

| Thư mục / File | Mô tả |
|---|---|
| `src/` | Toàn bộ mã nguồn Java |
| `lib/` | Thư viện ngoài (JDBC driver) |
| `data/` | Script SQL để tạo database |
| `imgs/` | Ảnh logo, icon menu, ảnh sản phẩm |
| `bin/` | File `.class` sau khi biên dịch (tự sinh) |

---

### `src/app/` — Giao diện người dùng (UI)

| File | Chức năng |
|---|---|
| `UIDangNhap.java` | Màn hình đăng nhập — điểm khởi chạy của ứng dụng |
| `UIQuanLyBanHang.java` | Cửa sổ chính, chứa menu điều hướng và khung nội dung |
| `PanelTrangChu.java` | Trang chủ, hiển thị thống kê nhanh (số đơn, doanh thu...) |
| `PanelBanHang.java` | Giao diện tạo đơn hàng, chọn sản phẩm và thanh toán |
| `PanelSanPham.java` | Thêm, sửa, xóa và tìm kiếm sản phẩm |
| `PanelKhachHang.java` | Quản lý danh sách khách hàng |
| `PanelNhanVien.java` | Quản lý nhân viên (chỉ tài khoản Quản lý mới thấy) |
| `PanelHoaDon.java` | Xem lịch sử và chi tiết hóa đơn |
| `PanelThongKe.java` | Biểu đồ thống kê doanh thu, sản phẩm bán chạy |

---

### `src/entity/` — Các lớp thực thể (Model)

| File | Tương ứng bảng DB |
|---|---|
| `NhanVien.java` | Bảng `NhanVien` |
| `SanPham.java` | Bảng `SanPham` |
| `KhachHang.java` | Bảng `KhachHang` |
| `HoaDon.java` | Bảng `HoaDon` |
| `CTHoaDon.java` | Bảng `ChiTietHoaDon` |

---

### `src/dao/` — Truy vấn cơ sở dữ liệu (DAO)

| File | Chức năng |
|---|---|
| `NhanVien_DAO.java` | Truy vấn dữ liệu nhân viên (đăng nhập, CRUD) |
| `SanPham_DAO.java` | Truy vấn dữ liệu sản phẩm |
| `KhachHang_DAO.java` | Truy vấn dữ liệu khách hàng |
| `HoaDon_DAO.java` | Truy vấn dữ liệu hóa đơn |
| `CTHoaDon_DAO.java` | Truy vấn chi tiết từng hóa đơn |

---

### `src/bus/` — Xử lý nghiệp vụ (Business Logic)

| File | Chức năng |
|---|---|
| `NhanVien_BUS.java` | Kiểm tra logic nghiệp vụ nhân viên |
| `SanPham_BUS.java` | Kiểm tra logic nghiệp vụ sản phẩm |
| `KhachHang_BUS.java` | Kiểm tra logic nghiệp vụ khách hàng |
| `HoaDon_BUS.java` | Kiểm tra logic nghiệp vụ hóa đơn |
| `CTHoaDon_BUS.java` | Kiểm tra logic nghiệp vụ chi tiết hóa đơn |

---

### `src/connectDB/`

| File | Chức năng |
|---|---|
| `ConnectDB.java` | Singleton kết nối SQL Server, dùng chung toàn ứng dụng |

---

### `lib/`

| File | Chức năng |
|---|---|
| `sqljdbc42.jar` | Driver JDBC của Microsoft, cho phép Java giao tiếp với SQL Server |

---

### `data/`

| File | Chức năng |
|---|---|
| `BangSieuThi.sql` | Script tạo database `QLSieuThi`, các bảng và toàn bộ dữ liệu mẫu |

---

##  Tài khoản mặc định

| Username | Mật khẩu | Vai trò |
|---|---|---|
| `admin` | `123` | Quản lý |
| `chi123` | `123` | Nhân viên |
| `nam456` | `123` | Nhân viên |
| `lan789` | `123` | Nhân viên |
| `hung12` | `123` | Nhân viên |

>  **Quản lý** có quyền truy cập tất cả chức năng, bao gồm quản lý nhân viên.  
> **Nhân viên** chỉ thao tác được bán hàng, sản phẩm, khách hàng và hóa đơn.

---
 
---

##  Xử lý lỗi thường gặp

**Lỗi kết nối database:**
```
com.microsoft.sqlserver.jdbc.SQLServerException: ...
```
→ Kiểm tra SQL Server đang chạy, đúng cổng 1433, đúng username/password trong `ConnectDB.java`.

**Lỗi thiếu driver JDBC:**
```
ClassNotFoundException: com.microsoft.sqlserver.jdbc.SQLServerDriver
```
→ Thêm `lib/sqljdbc42.jar` vào Build Path của Eclipse (xem Bước 5).

**Ảnh sản phẩm không hiển thị:**
→ Đảm bảo thư mục `imgs/` nằm cùng cấp với thư mục `src/` và không bị xóa.  
→ Kiểm tra **Working Directory** trong Run Configuration của Eclipse phải trỏ đúng vào thư mục gốc dự án.

---

## Công nghệ sử dụng

- **Java SE 8+** — Ngôn ngữ lập trình chính
- **Java Swing** — Xây dựng giao diện desktop
- **Microsoft SQL Server** — Cơ sở dữ liệu
- **JDBC (sqljdbc42)** — Kết nối Java với SQL Server
- **Eclipse IDE** — Môi trường phát triển
