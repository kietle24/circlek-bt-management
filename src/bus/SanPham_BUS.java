package bus;

import java.util.ArrayList;
import java.util.List;

import dao.SanPham_DAO;
import entity.SanPham;

public class SanPham_BUS {
	SanPham_DAO sanPham_DAO=new SanPham_DAO();
	public List<SanPham> danhSachSanPham(){
		return sanPham_DAO.danhSachSanPham();
	}
	public List<SanPham> timKiemSanPham(String masp,String tensp){
		if((masp==null||masp.isBlank())&&(tensp==null||tensp.isBlank())) { 
			return sanPham_DAO.danhSachSanPham();
		}
		if(masp!=null &&!masp.isBlank()) {
			List<SanPham> list =new ArrayList<>();
			SanPham sp=sanPham_DAO.timTheoMaSP(masp);
			if(sp!=null) list.add(sp);
			return list;
		}
		return sanPham_DAO.timTheoTenSP(tensp);
	}
	public SanPham timKiemTheoMa(String maSanPham){
		return sanPham_DAO.timTheoMaSP(maSanPham);
	}
	public boolean themSanPham(SanPham sp) throws Exception{
		if(sanPham_DAO.timTheoMaSP(sp.getMaSanPham())!=null) {
			throw new Exception("Mã sản phẩm \""+sp.getMaSanPham()+"\" đã tồn tại");
		}
		return sanPham_DAO.themSanPham(sp);
	}
	public boolean chinhSuaSanPham(SanPham sp) throws Exception{
		if(sanPham_DAO.timTheoMaSP(sp.getMaSanPham())==null) {
			throw new Exception("Mã sản phẩm \""+sp.getMaSanPham()+"\" không tồn tại");
		}
		return sanPham_DAO.chinhSuaSanPham(sp);
	}
	public boolean xoaSanPham(String masp) throws Exception{
		if(sanPham_DAO.timTheoMaSP(masp)==null)
			throw new Exception("Mã sản phẩm \""+masp+"\" không tồn tại");
		return sanPham_DAO.xoaSanPham(masp);
	}
	
}
