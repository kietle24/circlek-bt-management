package entity;

import java.time.LocalDate;

public class HoaDon {
	private String maHoaDon;
    private LocalDate ngayTao;
    
    // Quan hệ: Mỗi hóa đơn thuộc về 1 khách hàng, 1 nhân viên
    private KhachHang khachHang; 
    private NhanVien nhanVien;
    private double tongTien;

	public double getTongTien() {
		return tongTien;
	}

	public HoaDon(String maHoaDon, KhachHang khachHang, NhanVien nhanVien, LocalDate ngayTao, double tongTien) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngayTao = ngayTao;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.tongTien = tongTien;
	}

	public HoaDon(String maHoaDon) {
		super();
		this.maHoaDon = maHoaDon;
	}
	
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public LocalDate getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(LocalDate ngayTao) {
		this.ngayTao = ngayTao;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
}
