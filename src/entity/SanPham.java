package entity;

public class SanPham {
	private String maSanPham;
    private String tenSanPham;
    private double giaTien;
    private boolean trangThai;
    private String anh;
	public String getAnh() {
		return anh;
	}
	public void setAnh(String anh) {
		this.anh = anh;
	}
	public SanPham(String maSanPham, String tenSanPham, double giaTien, boolean trangThai) {
		super();
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.giaTien = giaTien;
		this.trangThai = trangThai;
	}
	public SanPham(String maSanPham) {
		super();
		this.maSanPham = maSanPham;
	}
	public SanPham(String maSanPham, String tenSanPham) {
		super();
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
	}
	public String getMaSanPham() {
		return maSanPham;
	}
	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}
	public String getTenSanPham() {
		return tenSanPham;
	}
	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}
	public double getGiaTien() {
		return giaTien;
	}
	public void setGiaTien(double giaTien) {
		this.giaTien = giaTien;
	}
	public boolean isTrangThai() {
		return trangThai;
	}
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
    
}
