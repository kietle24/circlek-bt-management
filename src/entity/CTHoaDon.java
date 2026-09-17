package entity;

public class CTHoaDon {
	private HoaDon hoaDon;   // Liên kết ngược về hóa đơn
    private SanPham sanPham; // Sản phẩm được mua
    private int soLuong;
    private double giaTien;  // Giá tại thời điểm bán
    public CTHoaDon(HoaDon hoaDon, SanPham sanPham, int soLuong, double giaTien) {
    	super();
    	this.hoaDon = hoaDon;
    	this.sanPham = sanPham;
    	this.soLuong = soLuong;
    }
	public HoaDon getHoaDon() {
		return hoaDon;
	}
	
	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}
	public SanPham getSanPham() {
		return sanPham;
	}
	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public double getGiaTien() {
		return giaTien;
	}
	public void setGiaTien(double giaTien) {
		this.giaTien = giaTien;
	}
}
