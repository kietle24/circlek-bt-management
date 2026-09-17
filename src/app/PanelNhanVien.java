package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import bus.NhanVien_BUS; // Đã mở comment
import entity.NhanVien;

public class PanelNhanVien extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	NhanVien_BUS nhanVien_BUS = new NhanVien_BUS(); // Đã mở comment
	
	private JPanel pnlCenter;
	private JTextField txtMaNV, txtHoTen, txtSDT, txtEmail, txtDiaChi, txtTienLuong, txtNgaySinh, txtUsername;
	private JPasswordField txtPassword;
	private JComboBox<String> cmbGioiTinh, cmbRole;
	
	private DefaultTableModel tableModel;
	private JTable table;
	private JFrame frame;
	private JButton btnThem, btnSua, btnXoa, btnLamMoi, btnTimKiem;
	private JButton btndangxuat;
	private JFrame owner;

//	public PanelEmployee(JFrame owner) {
//		this.owner = owner;
//	}
	

	public JPanel getNhanVienPanel(NhanVien nv,JFrame a) {
		this.owner = a;
		pnlCenter = new JPanel();
		pnlCenter.setPreferredSize(new Dimension(800, 250));
		pnlCenter.setLayout(new BorderLayout());
		pnlCenter.setBackground(new Color(248, 249, 250));
		pnlCenter.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createLineBorder(new Color(218, 41, 28), 2),
			"Thông tin nhân viên", 0, 0,
			new Font("Tahoma", Font.BOLD, 15),
			new Color(218, 41, 28)));

		Box b = Box.createVerticalBox();
		Box b1, b2, b3, b4, b5, b6;

		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b1.add(new JLabel("Mã NV:"));
		b1.add(Box.createHorizontalStrut(5));
		b1.add(txtMaNV = new JTextField());
		b1.add(Box.createHorizontalStrut(20));
		b1.add(new JLabel("Họ tên:"));
		b1.add(Box.createHorizontalStrut(5));
		b1.add(txtHoTen = new JTextField());
		b1.add(Box.createHorizontalStrut(20));
		b1.add(new JLabel("Giới tính:"));
		b1.add(Box.createHorizontalStrut(5));
		cmbGioiTinh = new JComboBox<>(new String[]{"Nam", "Nữ", "Khác"});
		b1.add(cmbGioiTinh);

		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b2.add(new JLabel("SĐT:"));
		b2.add(Box.createHorizontalStrut(5));
		b2.add(txtSDT = new JTextField());
		b2.add(Box.createHorizontalStrut(20));
		b2.add(new JLabel("Email:"));
		b2.add(Box.createHorizontalStrut(5));
		b2.add(txtEmail = new JTextField());
		b2.add(Box.createHorizontalStrut(20));
		b2.add(new JLabel("Ngày sinh:"));
		b2.add(Box.createHorizontalStrut(5));
		b2.add(txtNgaySinh = new JTextField());

		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b3.add(new JLabel("Địa chỉ:"));
		b3.add(Box.createHorizontalStrut(5));
		b3.add(txtDiaChi = new JTextField());
		b3.add(Box.createHorizontalStrut(20));
		b3.add(new JLabel("Lương:"));
		b3.add(Box.createHorizontalStrut(5));
		b3.add(txtTienLuong = new JTextField());
		b3.add(Box.createHorizontalStrut(20));
		b3.add(new JLabel("Vai trò:"));
		b3.add(Box.createHorizontalStrut(5));
		cmbRole = new JComboBox<>(new String[]{"Nhân viên", "Quản lý"});
		b3.add(cmbRole);

		// ===== ROW 4 =====
		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b4.add(new JLabel("Username:"));
		b4.add(Box.createHorizontalStrut(5));
		b4.add(txtUsername = new JTextField());
		b4.add(Box.createHorizontalStrut(20));
		b4.add(new JLabel("Password:"));
		b4.add(Box.createHorizontalStrut(5));
		b4.add(txtPassword = new JPasswordField());

		Dimension fieldHeight = new Dimension(Integer.MAX_VALUE, 25);

		txtMaNV.setMaximumSize(fieldHeight);
		txtHoTen.setMaximumSize(fieldHeight);
		txtSDT.setMaximumSize(fieldHeight);
		txtEmail.setMaximumSize(fieldHeight);
		txtNgaySinh.setMaximumSize(fieldHeight);
		txtDiaChi.setMaximumSize(fieldHeight);
		txtTienLuong.setMaximumSize(fieldHeight);
		txtUsername.setMaximumSize(fieldHeight);
		txtPassword.setMaximumSize(fieldHeight);

		cmbGioiTinh.setMaximumSize(fieldHeight);
		cmbRole.setMaximumSize(fieldHeight);

		b1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
		b2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
		b3.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
		b4.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));


		b.add(b5 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b5.add(Box.createHorizontalGlue());
		b5.add(btnThem    = taoNut("Thêm",    new Color(218, 41, 28)));
		b5.add(Box.createHorizontalStrut(40));
		b5.add(btnSua     = taoNut("Sửa",     new Color(30, 100, 200)));
		b5.add(Box.createHorizontalStrut(40));
		b5.add(btnXoa     = taoNut("Xóa",     new Color(200, 50, 50)));
		b5.add(Box.createHorizontalStrut(40));
		b5.add(btnLamMoi  = taoNut("Làm Mới", new Color(108, 117, 125)));
		b5.add(Box.createHorizontalStrut(40));
		b5.add(btnTimKiem = taoNut("Tìm",     new Color(245, 130, 32)));
		b5.add(Box.createHorizontalStrut(40));
		b5.add(btndangxuat = taoNut("Đăng Xuất", new Color(175, 25, 15)));
		b5.add(Box.createHorizontalGlue());
		b5.add(Box.createHorizontalStrut(40));
		Dimension lblSize = new Dimension(85, 25);
		((JLabel)b1.getComponent(0)).setPreferredSize(lblSize);
		((JLabel)b2.getComponent(0)).setPreferredSize(lblSize);
		((JLabel)b3.getComponent(0)).setPreferredSize(lblSize);
		((JLabel)b4.getComponent(0)).setPreferredSize(lblSize);

		b.add(b6 = Box.createHorizontalBox());
		String[] headers = "Mã NV;Họ Tên;Giới Tính;SĐT;Email;Vai Trò;Lương".split(";");
		tableModel = new DefaultTableModel(headers, 0) {
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false; 
		    }
		};
		
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		b6.add(scroll);
		
		frame = a;

		pnlCenter.add(b, BorderLayout.CENTER);

		addTableListener();
        
		// Đã mở comment loadTable khi khởi tạo giao diện
		loadTable(nhanVien_BUS.danhSachNhanVien()); 

		if(nv.getRole().equals("Nhân viên")) {
			btnThem.setEnabled(false);
			btnSua.setEnabled(false);
			btnXoa.setEnabled(false);
		}
		else if(nv.getRole().equals("Quản lý")) {
			btnThem.setEnabled(true);
			btnSua.setEnabled(true);
			btnXoa.setEnabled(true);
		}
		else {
			btnThem.setEnabled(false);
			btnSua.setEnabled(false);
			btnXoa.setEnabled(false);
		}

		
		return pnlCenter;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o == btnThem) {
			NhanVien nv = layDuLieu();
			if (nv == null) return;
			try {
				if (nhanVien_BUS.themNhanVien(nv)) {
					JOptionPane.showMessageDialog(owner, "Thêm thành công!");
					xoaForm(); 
					loadTable(nhanVien_BUS.danhSachNhanVien());
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(owner, e1.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
			}

		} else if (o == btnSua) {
			if (txtMaNV.getText().isEmpty()) { JOptionPane.showMessageDialog(owner, "Chọn nhân viên cần sửa!"); return; }
			NhanVien nv = layDuLieu();
			if (nv == null) return;
			try {
				if (nhanVien_BUS.chinhSuaNhanVien(nv)) {
					JOptionPane.showMessageDialog(owner, "Cập nhật thành công!");
					xoaForm(); 
					loadTable(nhanVien_BUS.danhSachNhanVien());
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(owner, e1.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
			}

		} else if (o == btnXoa) {
			if (txtMaNV.getText().isEmpty()) { JOptionPane.showMessageDialog(owner, "Chọn nhân viên cần xóa!"); return; }
			int cf = JOptionPane.showConfirmDialog(owner, "Xác nhận xóa nhân viên này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
			if (cf == JOptionPane.YES_OPTION) {
				try {
					if (nhanVien_BUS.xoaNhanVien(txtMaNV.getText().trim())) {
						JOptionPane.showMessageDialog(owner, "Xóa thành công!");
						xoaForm(); 
						loadTable(nhanVien_BUS.danhSachNhanVien());
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(owner, e1.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}

		} else if (o == btnLamMoi) {
			xoaForm(); 
			loadTable(nhanVien_BUS.danhSachNhanVien());

		} else if (o == btnTimKiem) {
			String ma  = txtMaNV.getText().trim();
			String ten = txtHoTen.getText().trim();
			loadTable(nhanVien_BUS.timKiemNhanVien(ma, ten));
		}
		else if (o == btndangxuat) {
		    int cf = JOptionPane.showConfirmDialog(this,
		        "Bạn có chắc muốn đăng xuất?",
		        "Xác nhận",
		        JOptionPane.YES_NO_OPTION);

		    if (cf == JOptionPane.YES_OPTION) {
		        frame.dispose(); 

		        UIDangNhap login = new UIDangNhap();
		        login.setVisible(true); 
		    }
//			int cf = JOptionPane.showConfirmDialog(owner,
//			        "Bạn có chắc muốn đăng xuất?",
//			        "Xác nhận",
//			        JOptionPane.YES_NO_OPTION);
//
//			    if (cf == JOptionPane.YES_OPTION) {
//			    	 Window w = SwingUtilities.getWindowAncestor(pnlCenter);
//			            if (w != null) {
//			                w.dispose();
//			            }
//
//			            LoginForm login = new LoginForm();
//			            login.setVisible(true);
//			    }
		}
	}

	private JButton taoNut(String ten, Color bg) {
		JButton btn = new JButton(ten);
		btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn.setBackground(bg);
		btn.setForeground(Color.WHITE);
		btn.setFocusPainted(false);
		btn.setPreferredSize(new Dimension(130, 35));
		btn.setMaximumSize(new Dimension(130, 35));
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn.addActionListener(this);
		return btn;
	}

	private void loadTable(List<NhanVien> list) {
		tableModel.setRowCount(0);
		for (NhanVien nv : list) {
			tableModel.addRow(new Object[]{
				nv.getMaNhanVien(),
				nv.getHoTen(),
				nv.getGioiTinh(),
				nv.getSoDienThoai(),
				nv.getEmail(),
				nv.getRole(),
				String.format("%,.0f đ", nv.getTienLuong())
			});
		}
	}

	private void addTableListener() {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				if (row < 0) return;
				String ma = tableModel.getValueAt(row, 0).toString();
				
				NhanVien nv = nhanVien_BUS.timKiemTheoMa(ma);
				if (nv == null) return;
				
				txtMaNV.setText(nv.getMaNhanVien());
				txtHoTen.setText(nv.getHoTen());
				cmbGioiTinh.setSelectedItem(nv.getGioiTinh());
				txtSDT.setText(nv.getSoDienThoai());
				txtEmail.setText(nv.getEmail());
				txtNgaySinh.setText(nv.getNgaySinh() != null ? nv.getNgaySinh().toString() : "");
				txtDiaChi.setText(nv.getDiaChi());
				txtTienLuong.setText(String.valueOf(nv.getTienLuong()));
				txtUsername.setText(nv.getUsername());
				txtPassword.setText(nv.getPassword());
				cmbRole.setSelectedItem(nv.getRole());
			}
		});

		table.setRowHeight(30);
		table.getTableHeader().setBackground(new Color(218, 41, 28));
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));

		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(
					JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(
						table, value, isSelected, hasFocus, row, column);
				if (isSelected) {
					c.setBackground(new Color(218, 41, 28));
					c.setForeground(Color.WHITE);
				} else if (row % 2 == 0) {
					c.setBackground(Color.WHITE);
					c.setForeground(new Color(33, 37, 41));
				} else {
					c.setBackground(new Color(250, 245, 245));
					c.setForeground(new Color(33, 37, 41));
				}
				return c;
			}
		});

		table.setGridColor(new Color(230, 230, 230));
		table.setShowGrid(true);
	}

	private void xoaForm() {
		txtMaNV.setText(""); txtHoTen.setText("");
		txtSDT.setText(""); txtEmail.setText("");
		txtNgaySinh.setText(""); txtDiaChi.setText("");
		txtTienLuong.setText(""); txtUsername.setText("");
		txtPassword.setText("");
		cmbGioiTinh.setSelectedIndex(0);
		cmbRole.setSelectedIndex(0);
		table.clearSelection();
		txtMaNV.requestFocus();
	}

	private NhanVien layDuLieu() {
		String ma = txtMaNV.getText().trim();
		String ten = txtHoTen.getText().trim();
		String sdt = txtSDT.getText().trim();
		String email = txtEmail.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String user = txtUsername.getText().trim();
		String pass = new String(txtPassword.getPassword());
		String gioiTinh = cmbGioiTinh.getSelectedItem().toString();
		String role = cmbRole.getSelectedItem().toString();
		
		if (ma.isEmpty() || ten.isEmpty()) {
			JOptionPane.showMessageDialog(owner, "Vui lòng nhập mã và tên nhân viên!"); return null;
		}
		if (!ten.matches("^([\\p{Lu}][\\p{Ll}]*)(\\s[\\p{Lu}][\\p{Ll}]*)*$")) {
			JOptionPane.showMessageDialog(owner, 
				"Tên nhân viên phải viết hoa chữ cái đầu mỗi từ!");
			return null;
		}
		double luong = 0;
		try {
			luong = Double.parseDouble(txtTienLuong.getText().trim());
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(owner, "Tiền lương phải là số hợp lệ!"); return null;
		}
		
		LocalDate ngaySinh = null;
		try {
			String strNgaySinh = txtNgaySinh.getText().trim();
			if (!strNgaySinh.isEmpty()) {
				ngaySinh = LocalDate.parse(strNgaySinh);
			}
		} catch (DateTimeParseException ex) {
			JOptionPane.showMessageDialog(owner, "Ngày sinh phải đúng định dạng YYYY-MM-DD!"); return null;
		}
		if (!sdt.matches("0\\d{9}")) {
		    JOptionPane.showMessageDialog(owner, "SĐT phải 10 số và bắt đầu bằng 0!");
		    return null;
		}
		if (!email.matches("^[A-Za-z0-9+_.-]+@gmail\\.com$")) {
		    JOptionPane.showMessageDialog(owner, "Email không hợp lệ!");
		    return null;
		}
		for(NhanVien nv : nhanVien_BUS.danhSachNhanVien()) {
		    if (nv.getUsername().equals(txtUsername.getText().trim()) 
		        && !nv.getMaNhanVien().equals(txtMaNV.getText().trim())) {
		        JOptionPane.showMessageDialog(owner, "Username đã tồn tại!");
		        return null;
		    }
		}
		return new NhanVien(ma, ten, user, pass, role, sdt, diaChi, email, luong, ngaySinh, gioiTinh);
	}
}