package bus;


import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.HoaDon_DAO;
import entity.HoaDon;

public class HoaDon_BUS {
	private static ArrayList<HoaDon> listHoaDon;
	public double getDoanhThu(LocalDate ngay) {
		double doanhThu = 0;
		listHoaDon = new HoaDon_DAO().getHoaDon(ngay);
		for (HoaDon hd : listHoaDon) {
			doanhThu += hd.getTongTien();
		}
		return doanhThu;
	}
	public int getSoLuongHoaDon(int tu, int den, int nam) {
		return new HoaDon_DAO().getSoLuongHoaDon(tu, den, nam);
	}
	public int getSoLuongHoaDon() {
		return new HoaDon_DAO().getSoLuongHoaDon();
	}
	public String getDoanhThu(int tu,int den, int nam) {
		DecimalFormat decimalFormat = new DecimalFormat("#,### VNĐ");
		Double doanhThuSo = new HoaDon_DAO().getDoanhThu(tu, den, nam);
		return decimalFormat.format(doanhThuSo);
	}
	public String getDoanhThu() {
		DecimalFormat decimalFormat = new DecimalFormat("#,### VNĐ");
		Double doanhThuSo = new HoaDon_DAO().getDoanhThu();
		return decimalFormat.format(doanhThuSo);
	}
	
	
}
