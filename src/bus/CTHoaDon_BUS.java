package bus;

import java.util.ArrayList;

import dao.CTHoaDon_DAO;
import dao.SanPham_DAO;
import entity.CTHoaDon;
import entity.SanPham;

public class CTHoaDon_BUS {
	private ArrayList<CTHoaDon> listCTHoaDon;
	public int soLuongCTHoaDon() {
		return listCTHoaDon.size();
	}
	public ArrayList<CTHoaDon> getAllCTHoaDon() {
		return new CTHoaDon_DAO().getAllCTHoaDon();
	}
	public String getTenSanPhamBanChay(int tu, int den, int nam) {
		SanPham sanPhamBanChay = new CTHoaDon_DAO().getSanPhamBanNhieuNhat(tu, den, nam);
		if (sanPhamBanChay == null) return "Chưa có dữ liệu";
		ArrayList<SanPham> listSanPham = new SanPham_DAO().danhSachSanPham();
		for (SanPham sp : listSanPham) {
			if (sp.getMaSanPham().equals(sanPhamBanChay.getMaSanPham())) {
				sanPhamBanChay = sp;
				break;
			}
		}
		return sanPhamBanChay.getTenSanPham();
	}
	public String getTenSanPhamBanChay() {
		SanPham sanPhamBanChay = new CTHoaDon_DAO().getSanPhamBanNhieuNhat();
		if (sanPhamBanChay == null) return "Chưa có dữ liệu";
		ArrayList<SanPham> listSanPham = new SanPham_DAO().danhSachSanPham();
		for (SanPham sp : listSanPham) {
			if (sp.getMaSanPham().equals(sanPhamBanChay.getMaSanPham())) {
				sanPhamBanChay = sp;
				break;
			}
		}
		return sanPhamBanChay.getTenSanPham();
	}
	public String getSoLuongDaBan(int tu, int den, int nam) {
		int soLuong = new CTHoaDon_DAO().getsoLuongSanPhamDaBan(tu, den, nam);
		return String.valueOf(soLuong);
	}
	public String getSoLuongDaBan() {
		int soLuong = new CTHoaDon_DAO().getsoLuongSanPhamDaBan();
		return String.valueOf(soLuong);
	}
}
