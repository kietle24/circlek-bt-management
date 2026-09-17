package bus;

import java.util.ArrayList;
import java.util.List;

import dao.KhachHang_DAO;
import entity.KhachHang;

public class KhachHang_BUS {
	KhachHang_DAO khachHang_DAO=new KhachHang_DAO();
	public List<KhachHang> danhSachKhachHang(){
		return khachHang_DAO.danhSachKhachHang();
	}
	public List<KhachHang> timKiemKhachHang(String makh,String hoten,String sdt){
		if((makh==null||makh.isBlank())&&(hoten==null||hoten.isBlank())&&(sdt==null||sdt.isBlank())) { 
			return khachHang_DAO.danhSachKhachHang();
		}
		if(makh!=null &&!makh.isBlank()) {
			List<KhachHang> list =new ArrayList<>();
			KhachHang kh=khachHang_DAO.timKiemTheoMa(makh);
			if(kh!=null) list.add(kh);
					return list;
		}
		if(hoten!=null&& !hoten.isBlank()) {
			return khachHang_DAO.timKiemTheoTen(hoten);
		}
		return khachHang_DAO.timKiemTheoSDT(sdt);
	}
	public KhachHang timKiemTheoMa(String makh){
		return khachHang_DAO.timKiemTheoMa(makh);
	}
	public boolean themKhachHang(KhachHang kh) throws Exception{
		if(khachHang_DAO.timKiemTheoMa(kh.getMaKhachHang())!=null) {
			throw new Exception("Mã khách hàng \""+kh.getMaKhachHang()+"\" đã tồn tại");
		}
		if(!kh.getSoDienThoai().matches("(^03|05|07|08|09)\\d{8}$"))
			throw new Exception("Số điện thoại phải bắt đầu bằng 02, 03, 05 , 07, 08, 09 và phải đủ 10 chữ số");
		if(!kh.getHoTen().matches("^[A-ZÀ-Ỹ][a-zà-ỹ]*(\\s[A-ZÀ-Ỹ][a-zà-ỹ]*)*"))
			throw new Exception("Họ tên phải viết hoa chữ cái đầu");
		if(!kh.getMaKhachHang().matches("^KH\\d{3,}$"))
			throw new Exception("Mã khách hàng phải đúng định dạng: KH + số(ít nhất 3 chữ số)");
		return khachHang_DAO.themKhachHang(kh);
	}
	public boolean chinhSuaKhachHang(KhachHang kh) throws Exception{
		if(khachHang_DAO.timKiemTheoMa(kh.getMaKhachHang())==null) {
			throw new Exception("Mã khách hàng \""+kh.getMaKhachHang()+"\" không tồn tại");
		}
		if(!kh.getSoDienThoai().matches("(^03|05|07|08|09)\\d{8}$"))
			throw new Exception("Số điện thoại phải bắt đầu bằng 03, 05 , 07, 08, 09 và phải đủ 10 chữ số");
		if(!kh.getHoTen().matches("^[A-ZÀ-Ỹ][a-zà-ỹ]*(\\s[A-ZÀ-Ỹ][a-zà-ỹ]*)*"))
			throw new Exception("Họ tên phải viết hoa chữ cái đầu");
		if(!kh.getMaKhachHang().matches("^KH\\d{3,}$"))
			throw new Exception("Mã khách hàng phải đúng định dạng: + số(ít nhất 3 chữ số)");	
		return khachHang_DAO.chinhSuaKhachHang(kh);
	}
	public boolean xoaKhachHang(String makh) throws Exception{
		if(khachHang_DAO.timKiemTheoMa(makh)==null)
			throw new Exception("Mã khách hàng \""+makh+"\" không tồn tại");
		return khachHang_DAO.xoaKhachHang(makh);
	}
}
