package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.NhanVien;

public class NhanVien_DAO {
	
	public NhanVien timTheoUser(String tenDangNhap) {
		NhanVien a=null ;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM NhanVien WHERE username = ?";
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.setString(1, tenDangNhap);
	        
	        ResultSet rs = stmt.executeQuery();
	        
	        // Vì username là UNIQUE, chỉ cần check 1 lần
	        if (rs.next()) {
	            a = new NhanVien(
	                rs.getString("maNhanVien"), 
	                rs.getString("hoTen"), 
	                rs.getString("username"), 
	                rs.getString("password"), 
	                rs.getString("role"), 
	                rs.getString("soDienThoai"), 
	                rs.getString("diaChi"), 
	                rs.getString("email"), 
	                rs.getDouble("tienLuong"), 
	                rs.getDate("ngaySinh").toLocalDate(), 
	                rs.getString("gioiTinh")
	            );
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
		
	}
	public NhanVien timTheoMa(String maNhanVien) {
		NhanVien a=null ;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM NhanVien WHERE maNhanVien = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maNhanVien);
			
			ResultSet rs = stmt.executeQuery();
			
			// Vì username là UNIQUE, chỉ cần check 1 lần
			if (rs.next()) {
				a = new NhanVien(
						rs.getString("maNhanVien"), 
						rs.getString("hoTen"), 
						rs.getString("username"), 
						rs.getString("password"), 
						rs.getString("role"), 
						rs.getString("soDienThoai"), 
						rs.getString("diaChi"), 
						rs.getString("email"), 
						rs.getDouble("tienLuong"), 
						rs.getDate("ngaySinh").toLocalDate(), 
						rs.getString("gioiTinh")
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
		
	}
	public NhanVien timTheoUser(String tenDangNhap,String matKhau) {
		NhanVien a=null ;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM NhanVien WHERE username = ? AND password = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, tenDangNhap);
			stmt.setString(2, matKhau);
			
			ResultSet rs = stmt.executeQuery();
			
			// Vì username là UNIQUE, chỉ cần check 1 lần
			if (rs.next()) {
				a = new NhanVien(
						rs.getString("maNhanVien"), 
						rs.getString("hoTen"), 
						rs.getString("username"), 
						rs.getString("password"), 
						rs.getString("role"), 
						rs.getString("soDienThoai"), 
						rs.getString("diaChi"), 
						rs.getString("email"), 
						rs.getDouble("tienLuong"), 
						rs.getDate("ngaySinh").toLocalDate(), 
						rs.getString("gioiTinh")
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
		
	}
	public static void main(String[] args) {
		NhanVien_DAO dao = new NhanVien_DAO();
		NhanVien nv = dao.timTheoUser("admin");
		if (nv != null) {
			System.out.println("Tìm thấy nhân viên: " + nv.getHoTen());
		} else {
			System.out.println("Không tìm thấy nhân viên với username đã cho.");
		}
	}
	 public ArrayList<NhanVien> danhSachNhanVien() {
	        ArrayList<NhanVien> ds = new ArrayList<>();
	        try {
	        	Connection con = ConnectDB.getConnection();
	            String sql = "SELECT * FROM NhanVien";
	            PreparedStatement stmt = con.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                ds.add(new NhanVien(
	                    rs.getString("maNhanVien"), 
	                    rs.getString("hoTen"), 
	                    rs.getString("username"), 
	                    rs.getString("password"), 
	                    rs.getString("role"), 
	                    rs.getString("soDienThoai"), 
	                    rs.getString("diaChi"), 
	                    rs.getString("email"), 
	                    rs.getDouble("tienLuong"), 
	                    rs.getDate("ngaySinh") != null ? rs.getDate("ngaySinh").toLocalDate() : null, 
	                    rs.getString("gioiTinh")
	                ));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return ds;
	    }
	 public List<NhanVien> getAllNhanVien() {
		    List<NhanVien> ds = new ArrayList<>();
		    String sql = "SELECT maNhanVien, hoTen FROM NhanVien";
		    try (Connection con = connectDB.ConnectDB.getConnection();
		         PreparedStatement pst = con.prepareStatement(sql);
		         ResultSet rs = pst.executeQuery()) {
		        while (rs.next()) {
		            NhanVien nv = new NhanVien();
		            nv.setMaNhanVien(rs.getString("maNhanVien"));
		            nv.setHoTen(rs.getString("hoTen"));
		            ds.add(nv);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return ds;
		}
	    public boolean themNhanVien(NhanVien nv) {
	        int n = 0;
	        try {
	            Connection con = ConnectDB.getConnection();
	            String sql = "INSERT INTO NhanVien VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	            PreparedStatement stmt = con.prepareStatement(sql);
	            stmt.setString(1, nv.getMaNhanVien());
	            stmt.setString(2, nv.getHoTen());
	            stmt.setString(3, nv.getUsername());
	            stmt.setString(4, nv.getPassword());
	            stmt.setString(5, nv.getRole());
	            stmt.setString(6, nv.getSoDienThoai());
	            stmt.setString(7, nv.getDiaChi());
	            stmt.setString(8, nv.getEmail());
	            stmt.setDouble(9, nv.getTienLuong());
	            stmt.setDate(10, nv.getNgaySinh() != null ? Date.valueOf(nv.getNgaySinh()) : null);
	            stmt.setString(11, nv.getGioiTinh());
	            
	            n = stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return n > 0;
	    }

	    public boolean chinhSuaNhanVien(NhanVien nv) {
	        int n = 0;
	        try {
	            Connection con = ConnectDB.getConnection();
	            String sql = "UPDATE NhanVien SET hoTen=?, username=?, password=?, role=?, soDienThoai=?, diaChi=?, email=?, tienLuong=?, ngaySinh=?, gioiTinh=? WHERE maNhanVien=?";
	            PreparedStatement stmt = con.prepareStatement(sql);
	            stmt.setString(1, nv.getHoTen());
	            stmt.setString(2, nv.getUsername());
	            stmt.setString(3, nv.getPassword());
	            stmt.setString(4, nv.getRole());
	            stmt.setString(5, nv.getSoDienThoai());
	            stmt.setString(6, nv.getDiaChi());
	            stmt.setString(7, nv.getEmail());
	            stmt.setDouble(8, nv.getTienLuong());
	            stmt.setDate(9, nv.getNgaySinh() != null ? Date.valueOf(nv.getNgaySinh()) : null);
	            stmt.setString(10, nv.getGioiTinh());
	            stmt.setString(11, nv.getMaNhanVien());
	            
	            n = stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return n > 0;
	    }

	    public boolean xoaNhanVien(String maNhanVien) {
	        int n = 0;
	        try {
	            Connection con = ConnectDB.getConnection();
	            String sql = "DELETE FROM NhanVien WHERE maNhanVien = ?";
	            PreparedStatement stmt = con.prepareStatement(sql);
	            stmt.setString(1, maNhanVien);
	            n = stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return n > 0;
	    }

	    public NhanVien timKiemTheoMa(String maNhanVien) {
	        NhanVien a = null;
	        try {
	            Connection con = ConnectDB.getConnection();
	            String sql = "SELECT * FROM NhanVien WHERE maNhanVien = ?";
	            PreparedStatement stmt = con.prepareStatement(sql);
	            stmt.setString(1, maNhanVien);
	            ResultSet rs = stmt.executeQuery();
	            
	            if (rs.next()) {
	                a = new NhanVien(
	                    rs.getString("maNhanVien"), 
	                    rs.getString("hoTen"), 
	                    rs.getString("username"), 
	                    rs.getString("password"), 
	                    rs.getString("role"), 
	                    rs.getString("soDienThoai"), 
	                    rs.getString("diaChi"), 
	                    rs.getString("email"), 
	                    rs.getDouble("tienLuong"), 
	                    rs.getDate("ngaySinh") != null ? rs.getDate("ngaySinh").toLocalDate() : null, 
	                    rs.getString("gioiTinh")
	                );
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return a;
	    }

	    public ArrayList<NhanVien> timKiemTheoTen(String hoTen) {
	        ArrayList<NhanVien> ds = new ArrayList<>();
	        try {
	            Connection con = ConnectDB.getConnection();
	            String sql = "SELECT * FROM NhanVien WHERE hoTen LIKE ?";
	            PreparedStatement stmt = con.prepareStatement(sql);
	            stmt.setString(1, "%" + hoTen + "%");
	            ResultSet rs = stmt.executeQuery();
	            
	            while (rs.next()) {
	                ds.add(new NhanVien(
	                    rs.getString("maNhanVien"), 
	                    rs.getString("hoTen"), 
	                    rs.getString("username"), 
	                    rs.getString("password"), 
	                    rs.getString("role"), 
	                    rs.getString("soDienThoai"), 
	                    rs.getString("diaChi"), 
	                    rs.getString("email"), 
	                    rs.getDouble("tienLuong"), 
	                    rs.getDate("ngaySinh") != null ? rs.getDate("ngaySinh").toLocalDate() : null, 
	                    rs.getString("gioiTinh")
	                ));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return ds;
	    }

	   
}
