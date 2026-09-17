package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.SanPham;

public class SanPham_DAO {
	public SanPham timTheoMaSP(String maSanPham) {
		SanPham a=null;
		try {
			Connection con=ConnectDB.getConnection();
			String sql="SELECT * FROM SanPham WHERE maSanPham = ?";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1,maSanPham);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()) {
				a=new SanPham(
						rs.getString("maSanPham"),
						rs.getString("tenSanPham"),
						rs.getDouble("giaTien"),
						rs.getBoolean("trangThai")
						);a.setAnh(rs.getString("anhSanPham"));
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}return a;
	}
	public List<SanPham> timTheoTenSP(String tenSanPham) {
		 List<SanPham> list = new ArrayList<>();
	
		try {
			Connection con=ConnectDB.getConnection();
			String sql="SELECT * FROM SanPham WHERE tenSanPham = ?";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1,tenSanPham);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				SanPham sp= new SanPham(
						rs.getString("maSanPham"),
						rs.getString("tenSanPham"),
						rs.getDouble("giaTien"),
						rs.getBoolean("trangThai")
						);sp.setAnh(rs.getString("anhSanPham"));
						list.add(sp);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}return list;
	}
	public boolean themSanPham(SanPham sp) {
		try {
			Connection con=ConnectDB.getConnection();
			String sql="INSERT INTO SanPham(maSanPham,anhSanPham, tenSanPham, giaTien, trangThai) VALUES (?,?,?,?,?)";
			PreparedStatement stmt =con.prepareStatement(sql);
			stmt.setString(1, sp.getMaSanPham());
			stmt.setString(2, sp.getAnh());
			stmt.setString(3, sp.getTenSanPham());
			stmt.setDouble(4, sp.getGiaTien());
			stmt.setBoolean(5, sp.isTrangThai());
			return stmt.executeUpdate()>0;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean chinhSuaSanPham(SanPham sp) {
		try {
			Connection con=ConnectDB.getConnection();
			String sql="UPDATE SanPham SET anhSanPham =?, tenSanPham=? , giaTien =?, trangThai=? WHERE maSanPham=?";
			PreparedStatement stmt =con.prepareStatement(sql);
			stmt.setString(1, sp.getAnh());
			stmt.setString(2, sp.getTenSanPham());
			stmt.setDouble(3, sp.getGiaTien());
			stmt.setBoolean(4, sp.isTrangThai());
			stmt.setString(5, sp.getMaSanPham());
			return stmt.executeUpdate()>0;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean xoaSanPham(String maSanPham) {
		try {
			Connection con=ConnectDB.getConnection();
			String sql="DELETE FROM SanPham WHERE maSanPham=?";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1, maSanPham);
			return stmt.executeUpdate()>0;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public ArrayList<SanPham> danhSachSanPham(){
		ArrayList<SanPham> list = new ArrayList<>();
		try {
			Connection con=ConnectDB.getConnection();
			String sql="SELECT * FROM SanPham ORDER BY maSanPham";
			PreparedStatement stmt=con.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				SanPham sp= new SanPham(
						rs.getString("maSanPham"),
						rs.getString("tenSanPham"),
						rs.getDouble("giaTien"),
						rs.getBoolean("trangThai")
						);sp.setAnh(rs.getString("anhSanPham"));
						list.add(sp);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<SanPham> getAllSanPhamBan() {
		List<SanPham> dsSanPham = new ArrayList<>();
		String sql = "SELECT maSanPham, tenSanPham, giaTien, trangThai FROM SanPham WHERE trangThai = 1";
		try (Connection con = ConnectDB.getConnection();
		     PreparedStatement pst = con.prepareStatement(sql);
		     ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				String ma = rs.getString("maSanPham");
				String ten = rs.getString("tenSanPham");
				double gia = rs.getDouble("giaTien");
				boolean trangThai = rs.getBoolean("trangThai");
				SanPham sp = new SanPham(ma, ten, gia, trangThai); 
				dsSanPham.add(sp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsSanPham;
	}
}
