package bus;

import java.util.ArrayList;
import java.util.List;

import dao.NhanVien_DAO;
import entity.NhanVien;

public class NhanVien_BUS {
	NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
	
	public List<NhanVien> danhSachNhanVien() {
		return nhanVien_DAO.danhSachNhanVien();
	}
	
	public List<NhanVien> timKiemNhanVien(String manv, String hoten) {
		if ((manv == null || manv.isBlank()) && (hoten == null || hoten.isBlank())) { 
			return nhanVien_DAO.danhSachNhanVien();
		}
		if (manv != null && !manv.isBlank()) {
			List<NhanVien> list = new ArrayList<>();
			NhanVien nv = nhanVien_DAO.timKiemTheoMa(manv);
			if (nv != null) list.add(nv);
			return list;
		}
		return nhanVien_DAO.timKiemTheoTen(hoten);
	}
	
	public NhanVien timKiemTheoMa(String maNhanVien) {
		return nhanVien_DAO.timKiemTheoMa(maNhanVien);
	}
	
	public boolean themNhanVien(NhanVien nv) throws Exception {
		if (nhanVien_DAO.timKiemTheoMa(nv.getMaNhanVien()) != null) {
			throw new Exception("Mã nhân viên \"" + nv.getMaNhanVien() + "\" đã tồn tại");
		}
		return nhanVien_DAO.themNhanVien(nv);
	}
	
	public boolean chinhSuaNhanVien(NhanVien nv) throws Exception {
		if (nhanVien_DAO.timKiemTheoMa(nv.getMaNhanVien()) == null) {
			throw new Exception("Mã nhân viên \"" + nv.getMaNhanVien() + "\" không tồn tại");
		}
		return nhanVien_DAO.chinhSuaNhanVien(nv);
	}
	
	public boolean xoaNhanVien(String manv) throws Exception {
		if (nhanVien_DAO.timKiemTheoMa(manv) == null)
			throw new Exception("Mã nhân viên \"" + manv + "\" không tồn tại");
		return nhanVien_DAO.xoaNhanVien(manv);
	}
}