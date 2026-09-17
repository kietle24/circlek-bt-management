package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.KhachHangThongKe;

public class KhachHang_DAO {
	public List<KhachHang> danhSachKhachHang(){
		 List<KhachHang> list = new ArrayList<>();
		try {
			Connection con=ConnectDB.getConnection();
			String sql="SELECT * FROM KhachHang ORDER BY maKhachHang";
			PreparedStatement stmt=con.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				list.add(new KhachHang(
						rs.getString("maKhachHang"),
						rs.getString("hoTen"),
						rs.getString("soDienThoai")));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<KhachHang> timKiemTheoTen(String kw){
		 List<KhachHang> list = new ArrayList<>();
		try {
			Connection con=ConnectDB.getConnection();
			String sql="SELECT * FROM KhachHang WHERE hoTen LIKE ?";
			PreparedStatement stmt=con.prepareStatement(sql);
				stmt.setString(1, kw);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				list.add(new KhachHang(
						rs.getString("maKhachHang"),
						rs.getString("hoTen"),
						rs.getString("soDienThoai")));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<KhachHang> timKiemTheoSDT(String kw){
		 List<KhachHang> list = new ArrayList<>();
		try {
			Connection con=ConnectDB.getConnection();
			String sql="SELECT * FROM KhachHang WHERE soDienThoai LIKE ?";
			PreparedStatement stmt=con.prepareStatement(sql);
				stmt.setString(1, kw);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				list.add(new KhachHang(
						rs.getString("maKhachHang"),
						rs.getString("hoTen"),
						rs.getString("soDienThoai")));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public boolean themKhachHang(KhachHang kh) {
		try {
			Connection con=ConnectDB.getConnection();
			String sql="INSERT INTO KhachHang(maKhachHang, hoTen, soDienThoai) VALUES (?,?,?)";
			PreparedStatement stmt =con.prepareStatement(sql);
			stmt.setString(1, kh.getMaKhachHang());
			stmt.setString(2, kh.getHoTen());
			stmt.setString(3, kh.getSoDienThoai());
			return stmt.executeUpdate()>0;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean chinhSuaKhachHang(KhachHang kh) {
		try {
			Connection con=ConnectDB.getConnection();
			String sql="UPDATE KhachHang SET hoTen=? , soDienThoai =? WHERE maKhachHang=?";
			PreparedStatement stmt =con.prepareStatement(sql);
			stmt.setString(3, kh.getMaKhachHang());
			stmt.setString(1, kh.getHoTen());
			stmt.setString(2, kh.getSoDienThoai());
			return stmt.executeUpdate()>0;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean xoaKhachHang(String maKhachHang) {
		try {
			Connection con=ConnectDB.getConnection();
			String sql="DELETE FROM KhachHang WHERE maKhachHang=?";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1, maKhachHang);
			return stmt.executeUpdate()>0;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public KhachHang timKiemTheoMa(String maKhachHang) {
		KhachHang a=null;
		try {
			Connection con=ConnectDB.getConnection();
			String sql="SELECT * FROM KhachHang WHERE maKhachHang = ?";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1,maKhachHang);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()) {
				a=new KhachHang(
						rs.getString("maKhachHang"),
						rs.getString("hoTen"),
						rs.getString("soDienThoai")
						);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}return a;
		
	}
	public ArrayList<KhachHangThongKe> getThongKeKhachHang(){
		ArrayList<KhachHangThongKe> list = new ArrayList<>();
		try {
			Connection con=ConnectDB.getConnection();
			String sql="SELECT kh.maKhachHang, kh.hoTen,soDienThoai,COUNT(dh.maHoaDon) AS soDonHang, SUM(dh.tongTien) AS tongChiTieu "
					+ "FROM KhachHang kh LEFT JOIN HoaDon dh ON kh.maKhachHang = dh.maKhachHang "
					+ "GROUP BY kh.maKhachHang, kh.hoTen,soDienThoai "
					+ "ORDER BY tongChiTieu DESC";
			PreparedStatement stmt=con.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				KhachHang kh = new KhachHang(
						rs.getString("maKhachHang"),
						rs.getString("hoTen"),
						rs.getString("soDienThoai")
						);
				int soDonHang = rs.getInt("soDonHang");
				double tongChiTieu = rs.getDouble("tongChiTieu");
				list.add(new KhachHangThongKe(kh, soDonHang, tongChiTieu));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<KhachHangThongKe> getThongKeKhachHang(int tu, int den,int nam){
		ArrayList<KhachHangThongKe> list = new ArrayList<KhachHangThongKe>();
		String sql;
		PreparedStatement stmt;
		
			try {
				if(den-tu<0) {
					sql="SELECT kh.maKhachHang, kh.hoTen,soDienThoai,COUNT(dh.maHoaDon) AS soDonHang, SUM(dh.tongTien) AS tongChiTieu "
							+ "FROM KhachHang kh LEFT JOIN HoaDon dh ON kh.maKhachHang = dh.maKhachHang "
							+ "WHERE MONTH(dh.ngayTao) BETWEEN ? AND ? AND YEAR(dh.ngayTao) BETWEEN ? AND ? "
							+ "GROUP BY kh.maKhachHang, kh.hoTen,soDienThoai "
							+ "ORDER BY tongChiTieu DESC";
					stmt=ConnectDB.getConnection().prepareStatement(sql);
					stmt.setInt(1, tu);
					stmt.setInt(2, den);
					stmt.setInt(3, nam-1);
					stmt.setInt(4, nam);
					
					}
				else {
					sql="SELECT kh.maKhachHang, kh.hoTen,soDienThoai,COUNT(dh.maHoaDon) AS soDonHang, SUM(dh.tongTien) AS tongChiTieu "
							+ "FROM KhachHang kh LEFT JOIN HoaDon dh ON kh.maKhachHang = dh.maKhachHang "
							+ "WHERE MONTH(dh.ngayTao) BETWEEN ? AND ? AND YEAR(dh.ngayTao) = ? "
							+ "GROUP BY kh.maKhachHang, kh.hoTen,soDienThoai "
							+ "ORDER BY tongChiTieu DESC";
					stmt=ConnectDB.getConnection().prepareStatement(sql);
					stmt.setInt(1, tu);
					stmt.setInt(2, den);
					stmt.setInt(3, nam);
				}
				ResultSet rs=stmt.executeQuery();
				while(rs.next()) {
					KhachHang kh = new KhachHang(
							rs.getString("maKhachHang"),
							rs.getString("hoTen"),
							rs.getString("soDienThoai")
							);
					int soDonHang = rs.getInt("soDonHang");
					double tongChiTieu = rs.getDouble("tongChiTieu");
					list.add(new KhachHangThongKe(kh, soDonHang, tongChiTieu));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
	}
	public String getOrInsertKhachHang(String hoTen, String sdt) {
        if (hoTen == null || hoTen.trim().isEmpty()) {
            return null; // Nếu không nhập tên -> Khách vãng lai
        }

//        String maKH = null;
        
        // 1. Kiểm tra khách hàng đã tồn tại chưa (Dựa vào Tên và SĐT)
        String sqlCheck = "SELECT maKhachHang FROM KhachHang WHERE hoTen = ? AND soDienThoai = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pst = con.prepareStatement(sqlCheck)) {
            pst.setString(1, hoTen);
            pst.setString(2, sdt);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getString("maKhachHang"); // Nếu đã có, trả về mã cũ
            }
        } catch (Exception e) { e.printStackTrace(); }

        // 2. Nếu chưa có -> Phát sinh mã khách hàng mới (KH + 3 số)
        String nextMa = "KH001";
        String sqlGetMa = "SELECT TOP 1 maKhachHang FROM KhachHang ORDER BY LEN(maKhachHang) DESC, maKhachHang DESC";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pst = con.prepareStatement(sqlGetMa);
             ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                String lastMa = rs.getString("maKhachHang");
                int so = Integer.parseInt(lastMa.substring(2));
                nextMa = String.format("KH%03d", so + 1);
            }
        } catch (Exception e) { e.printStackTrace(); }

        // 3. Thêm khách hàng mới vào CSDL
        String sqlInsert = "INSERT INTO KhachHang (maKhachHang, hoTen, soDienThoai) VALUES (?, ?, ?)";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pst = con.prepareStatement(sqlInsert)) {
            pst.setString(1, nextMa);
            pst.setString(2, hoTen);
            pst.setString(3, sdt);
            pst.executeUpdate(); // Lưu xuống DB
            return nextMa; // Trả về mã mới tạo
        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }
}
