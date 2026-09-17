package entity;

public class KhachHang {
	private String maKhachHang; // Khóa chính (NVARCHAR 100)
    private String hoTen;       // Họ tên (NVARCHAR 100)
    private String soDienThoai; // Số điện thoại (NVARCHAR 15)
	public String getMaKhachHang() {
		return maKhachHang;
	}
	public KhachHang(String maKhachHang, String hoTen, String soDienThoai) {
		super();
		this.maKhachHang = maKhachHang;
		this.hoTen = hoTen;
		this.soDienThoai = soDienThoai;
	}
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
    
}
