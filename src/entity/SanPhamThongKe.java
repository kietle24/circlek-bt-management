package entity;

public class SanPhamThongKe {
	private SanPham sanPham;
	private int soLuongBan;
	private double tongTienBan;
	public SanPhamThongKe(SanPham sanPham, int soLuongBan, double tongTienBan) {
		super();
		this.sanPham = sanPham;
		this.soLuongBan = soLuongBan;
		this.tongTienBan = tongTienBan;
	}
	public SanPham getSanPham() {
		return sanPham;
	}
	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}
	public int getSoLuongBan() {
		return soLuongBan;
	}
	public void setSoLuongBan(int soLuongBan) {
		this.soLuongBan = soLuongBan;
	}
	public double getTongTienBan() {
		return tongTienBan;
	}
	public void setTongTienBan(double tongTienBan) {
		this.tongTienBan = tongTienBan;
	}
}
