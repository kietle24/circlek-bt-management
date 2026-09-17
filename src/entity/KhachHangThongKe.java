package entity;

public class KhachHangThongKe {
	private KhachHang khachHang;
	private int soDonHang;
	private double tongChiTieu;
	public KhachHangThongKe(KhachHang khachHang, int soDonHang, double tongChiTieu) {
		super();
		this.khachHang = khachHang;
		this.soDonHang = soDonHang;
		this.tongChiTieu = tongChiTieu;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public int getSoDonHang() {
		return soDonHang;
	}
	public void setSoDonHang(int soDonHang) {
		this.soDonHang = soDonHang;
	}
	public double getTongChiTieu() {
		return tongChiTieu;
	}
	public void setTongChiTieu(double tongChiTieu) {
		this.tongChiTieu = tongChiTieu;
	}
	
}
