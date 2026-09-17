package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.CTHoaDon;
import entity.HoaDon;
import entity.SanPham;
import entity.SanPhamThongKe;

public class CTHoaDon_DAO {
	public ArrayList<CTHoaDon> getAllCTHoaDon() {
		ArrayList<CTHoaDon> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM ChiTietHoaDon";
			Statement stmt = ConnectDB.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				CTHoaDon cthd = new CTHoaDon(
						new HoaDon(rs.getString("maHoaDon")),
						new SanPham(rs.getString("maSanPham")),
						rs.getInt("soLuong"),
						rs.getDouble("giaTien")
				);
				list.add(cthd);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
		}
		return list;
	}
	public SanPham getSanPhamBanNhieuNhat(int tu, int den,  int nam) {
		SanPham sp = null;
		String sql;
		PreparedStatement pstmt;		try {
			if(den-tu<0) {
				sql = "SELECT TOP 1 maSanPham, SUM(soLuong) AS tongSoLuong FROM ChiTietHoaDon cthd " +
						"JOIN HoaDon hd ON cthd.maHoaDon = hd.maHoaDon " +
						"WHERE MONTH(hd.ngayTao) BETWEEN ? AND ? AND YEAR(hd.ngayTao) BETWEEN ? AND ? " +
						"GROUP BY maSanPham " +
						"ORDER BY tongSoLuong DESC";
				pstmt = ConnectDB.getConnection().prepareStatement(sql);
				pstmt.setInt(1, tu);
				pstmt.setInt(2, den);
				pstmt.setInt(3, nam-1);
				pstmt.setInt(4, nam);
			}else {
				sql = "SELECT TOP 1 maSanPham, SUM(soLuong) AS tongSoLuong FROM ChiTietHoaDon cthd " +
						"JOIN HoaDon hd ON cthd.maHoaDon = hd.maHoaDon " +
						"WHERE MONTH(hd.ngayTao) BETWEEN ? AND ? AND YEAR(hd.ngayTao) = ? " +
						"GROUP BY maSanPham " +
						"ORDER BY tongSoLuong DESC";
				pstmt = ConnectDB.getConnection().prepareStatement(sql);
				pstmt.setInt(1, tu);
				pstmt.setInt(2, den);
				pstmt.setInt(3, nam);
			}
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				sp = new SanPham(rs.getString("maSanPham"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sp;
	}
	public SanPham getSanPhamBanNhieuNhat() {
		SanPham sp = null;
		String sql = "SELECT TOP 1 maSanPham, SUM(soLuong) AS tongSoLuong FROM ChiTietHoaDon " +
					 "GROUP BY maSanPham " +
					 "ORDER BY tongSoLuong DESC";
		try {
			Statement stmt = ConnectDB.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				sp = new SanPham(rs.getString("maSanPham"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sp;
	}
	public int getsoLuongSanPhamDaBan(int tu, int den, int nam) {
		int soLuong = 0;
		String sql;
		PreparedStatement pstmt;
		try {
			if(den-tu<0) {
				sql = "SELECT SUM(soLuong) AS tongSoLuong FROM ChiTietHoaDon cthd " +
						"JOIN HoaDon hd ON cthd.maHoaDon = hd.maHoaDon " +
						"WHERE MONTH(hd.ngayTao) BETWEEN ? AND ? AND YEAR(hd.ngayTao) BETWEEN ? AND ?";
				pstmt = ConnectDB.getConnection().prepareStatement(sql);
				pstmt.setInt(1, tu);
				pstmt.setInt(2, den);
				pstmt.setInt(3, nam-1);
				pstmt.setInt(4, nam);
			}else {
				sql = "SELECT SUM(soLuong) AS tongSoLuong FROM ChiTietHoaDon cthd " +
						"JOIN HoaDon hd ON cthd.maHoaDon = hd.maHoaDon " +
						"WHERE MONTH(hd.ngayTao) BETWEEN ? AND ? AND YEAR(hd.ngayTao) = ?";
				pstmt = ConnectDB.getConnection().prepareStatement(sql);
				pstmt.setInt(1, tu);
				pstmt.setInt(2, den);
				pstmt.setInt(3, nam);
			}
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				soLuong = rs.getInt("tongSoLuong");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return soLuong;
	}
	public int getsoLuongSanPhamDaBan() {
		int soLuong = 0;
		String sql = "SELECT SUM(soLuong) AS tongSoLuong FROM ChiTietHoaDon";
		try {
			Statement stmt = ConnectDB.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				soLuong = rs.getInt("tongSoLuong");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return soLuong;
	}
	public ArrayList<SanPhamThongKe> getdanhSachSanPham(int tu,int den, int nam) {
		ArrayList<SanPhamThongKe> list = new ArrayList<>();
		String sql;
		PreparedStatement pstmt;
		try {
			if(den-tu<0) {
				sql = "SELECT sp.maSanPham, sp.tenSanPham, " +
			             "SUM(cthd.soLuong) AS soLuongBan, " +
			             "SUM(CAST(cthd.soLuong AS FLOAT) * sp.giaTien) AS tongDoanhThu " + 
			             "FROM ChiTietHoaDon cthd " +
			             "JOIN HoaDon hd ON cthd.maHoaDon = hd.maHoaDon " +
			             "JOIN SanPham sp ON cthd.maSanPham = sp.maSanPham " +
			             "WHERE MONTH(hd.ngayTao) BETWEEN ? AND ? " + 
			             "AND YEAR(hd.ngayTao) BETWEEN ? AND ? " +
			             "GROUP BY sp.maSanPham, sp.tenSanPham " + 
			             "ORDER BY soLuongBan DESC";
			
				pstmt = ConnectDB.getConnection().prepareStatement(sql);
				pstmt.setInt(1, tu);
				pstmt.setInt(2, den);
				pstmt.setInt(3, nam-1);
				pstmt.setInt(4, nam);
			}else {
				sql = "SELECT sp.maSanPham, sp.tenSanPham, " +
			             "SUM(cthd.soLuong) AS soLuongBan, " +
			             "SUM(CAST(cthd.soLuong AS FLOAT) * sp.giaTien) AS tongDoanhThu " + 
			             "FROM ChiTietHoaDon cthd " +
			             "JOIN HoaDon hd ON cthd.maHoaDon = hd.maHoaDon " +
			             "JOIN SanPham sp ON cthd.maSanPham = sp.maSanPham " +
			             "WHERE MONTH(hd.ngayTao) BETWEEN ? AND ? " + 
			             "AND YEAR(hd.ngayTao) = ? " +
			             "GROUP BY sp.maSanPham, sp.tenSanPham " + 
			             "ORDER BY soLuongBan DESC";
				pstmt = ConnectDB.getConnection().prepareStatement(sql);
				pstmt.setInt(1, tu);
				pstmt.setInt(2, den);
				pstmt.setInt(3, nam);
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new SanPhamThongKe(
						new SanPham(rs.getString("maSanPham"), rs.getString("tenSanPham")),
						rs.getInt("soLuongBan"),
						rs.getDouble("tongDoanhThu")
				));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<SanPhamThongKe> getdanhSachSanPham() {
		ArrayList<SanPhamThongKe> list = new ArrayList<>();
		String sql = "SELECT sp.maSanPham, sp.tenSanPham, " +
		             "SUM(cthd.soLuong) AS soLuongBan, " +
		             "SUM(CAST(cthd.soLuong AS FLOAT) * sp.giaTien) AS tongDoanhThu " + 
		             "FROM ChiTietHoaDon cthd " +
		             "JOIN HoaDon hd ON cthd.maHoaDon = hd.maHoaDon " +
		             "JOIN SanPham sp ON cthd.maSanPham = sp.maSanPham " +
		             "GROUP BY sp.maSanPham, sp.tenSanPham " + 
		             "ORDER BY soLuongBan DESC";
		try {
			Statement stmt = ConnectDB.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(new SanPhamThongKe(
						new SanPham(rs.getString("maSanPham"), rs.getString("tenSanPham")),
						rs.getInt("soLuongBan"),
						rs.getDouble("tongDoanhThu")
				));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<Object[]> getChiTietByMaHD(String maHoaDon) {
	    List<Object[]> dsChiTiet = new ArrayList<>();
	    String sql = "SELECT ct.maSanPham, sp.tenSanPham, ct.soLuong, ct.giaTien " +
	                 "FROM ChiTietHoaDon ct " +
	                 "JOIN SanPham sp ON ct.maSanPham = sp.maSanPham " +
	                 "WHERE ct.maHoaDon = ?";
	                 
	    try (java.sql.Connection con = connectDB.ConnectDB.getConnection();
	         java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
	         
	        pst.setString(1, maHoaDon);
	        java.sql.ResultSet rs = pst.executeQuery();
	        
	        while (rs.next()) {
	            Object[] row = {
	                rs.getString("maSanPham"),
	                rs.getString("tenSanPham"),
	                rs.getInt("soLuong"),
	                rs.getDouble("giaTien")
	            };
	            dsChiTiet.add(row);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return dsChiTiet;
	}
}
