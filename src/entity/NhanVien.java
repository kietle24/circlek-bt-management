package entity;

import java.time.LocalDate;

public class NhanVien {
	private String maNhanVien;
    private String hoTen;
    private String username;
    private String password;
    private String role;
    private String soDienThoai;
    private String diaChi;
    private String email;
    private double tienLuong;
    private LocalDate ngaySinh;
    private String gioiTinh;
    
	public NhanVien(String maNhanVien, String hoTen, String username, String password, String role, String soDienThoai,
			String diaChi, String email, double tienLuong, LocalDate ngaySinh, String gioiTinh) {
		super();
		this.maNhanVien = maNhanVien;
		this.hoTen = hoTen;
		this.username = username;
		this.password = password;
		this.role = role;
		this.soDienThoai = soDienThoai;
		this.diaChi = diaChi;
		this.email = email;
		this.tienLuong = tienLuong;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
	}
	public NhanVien() {
		this("", "", "", "", "", "", "", "", 0, null, "");
	}
	public NhanVien(String maNhanVien) {
		super();
		this.maNhanVien = maNhanVien;
	}
	public String getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getTienLuong() {
		return tienLuong;
	}
	public void setTienLuong(double tienLuong) {
		this.tienLuong = tienLuong;
	}
	public LocalDate getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
    
    
}
