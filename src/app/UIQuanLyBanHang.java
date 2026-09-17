package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import dao.NhanVien_DAO;
import entity.NhanVien;

public class UIQuanLyBanHang extends JFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Dimension kichThuocMan = Toolkit.getDefaultToolkit().getScreenSize();
	int width = (int) (kichThuocMan.getWidth() * 0.85);
	int height = (int) (kichThuocMan.getHeight() * 0.9);
	JPanel pnlNorth, pnlWest, pnlTrangChu, pnlVungChua;
	ImageIcon logo;
	Image imgLogo;
	JLabel lblLogo, lblTieuDe;
	JButton btnTrangChu, btnBanHang, btnSanPham, btnKhachHang, btnNhanVien, btnThongKe, btnHoaDon;
	JTextField txtUsername, txtPassword;
	static NhanVien nhanVien;
	NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();

	// JButton btnTrangChu,btnBanHang,btnSanPham;
	// Color w,n;
	public UIQuanLyBanHang(String txtUsername, String txtPassword) {
		nhanVien = nhanVien_DAO.timTheoUser(txtUsername);
		new UIQuanLyBanHang().setVisible(true);
	}

	public UIQuanLyBanHang() {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout());

		pnlNorth = new JPanel();
		pnlNorth.setLayout(new BorderLayout());
		pnlNorth.setBackground(new Color(255, 255, 255));
		pnlNorth.setPreferredSize(new Dimension(width, 160));

		pnlWest = new JPanel();
		pnlWest.setLayout(new FlowLayout(FlowLayout.LEFT, 10, (int) (height * 0.042)));
		pnlWest.setBackground(new Color(218, 41, 28)); // Màu chủ đề siêu thị tiện lợi
		pnlWest.setPreferredSize(new Dimension(200, height));

		JPanel pnlLogo = new JPanel();
		pnlLogo.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 7));
		pnlLogo.setPreferredSize(new Dimension(200, height));
		pnlLogo.setOpaque(false);

		JPanel pnlTen = new JPanel();
		pnlTen.setLayout(new java.awt.GridLayout(2, 1, 0, 5));
		pnlTen.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 30));

		logo = new ImageIcon("imgs/LOGO.png");
		imgLogo = logo.getImage().getScaledInstance(145, 145, Image.SCALE_SMOOTH);
		lblLogo = new JLabel(new ImageIcon(imgLogo));
		pnlLogo.add(lblLogo);

		lblTieuDe = new JLabel("TRANG CHỦ", JLabel.CENTER);
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblTieuDe.setForeground(new Color(218, 41, 28));

		String tenNV = nhanVien != null ? nhanVien.getHoTen() : "";
		JLabel lblTen;
		lblTen = new JLabel("Nhân viên: " + tenNV, JLabel.RIGHT);
		lblTen.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTen.setForeground(new Color(60, 60, 60));

		String chucVu = nhanVien != null ? nhanVien.getRole() : "";
		JLabel lblChucVu;
		lblChucVu = new JLabel("Chức vụ: " + chucVu, JLabel.RIGHT);
		lblChucVu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblChucVu.setForeground(new Color(218, 41, 28));

		btnTrangChu = new JButton("Trang Chủ", layIconMenu("icons8-home-25.png"));
		btnTrangChu.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnTrangChu.setPreferredSize(new Dimension(180, 45));
		btnTrangChu.setBackground(new Color(165, 20, 15));
		btnTrangChu.setForeground(Color.WHITE);
		btnTrangChu.setContentAreaFilled(false); // Xóa nền mặc định
		btnTrangChu.setBorderPainted(false); // Xóa viền nút
		btnTrangChu.setFocusPainted(false); // Xóa khung focus khi click
		btnTrangChu.addActionListener(this);
		pnlWest.add(btnTrangChu);

		PanelTrangChu trangChu = new PanelTrangChu();
		pnlTrangChu = trangChu.getTrangChu();
		pnlTrangChu.setMaximumSize(new Dimension(trangChu.getWidth(), 100));

		pnlVungChua = new JPanel(new BorderLayout()); // Khung chứa cố định
		pnlVungChua.add(new PanelTrangChu().getTrangChu(), BorderLayout.CENTER); // Mặc định hiện Trang Chủ
		add(pnlVungChua, BorderLayout.CENTER);

		btnBanHang = new JButton("Bán Hàng", layIconMenu("icons8-product-25.png"));
		btnBanHang.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnBanHang.setPreferredSize(new Dimension(180, 45));
		btnBanHang.setBackground(new Color(165, 20, 15));
		btnBanHang.setForeground(Color.WHITE);
		btnBanHang.setContentAreaFilled(false); // Xóa nền mặc định
		btnBanHang.setBorderPainted(false); // Xóa viền nút
		btnBanHang.setFocusPainted(false); // Xóa khung focus khi click
		btnBanHang.addActionListener(this);
		pnlWest.add(btnBanHang);

		btnSanPham = new JButton("Sản Phẩm", layIconMenu("icons8-product-25.png"));
		btnSanPham.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnSanPham.setPreferredSize(new Dimension(180, 45));
		btnSanPham.setBackground(new Color(165, 20, 15));
		btnSanPham.setForeground(Color.WHITE);
		btnSanPham.setContentAreaFilled(false); // Xóa nền mặc định
		btnSanPham.setBorderPainted(false); // Xóa viền nút
		btnSanPham.setFocusPainted(false); // Xóa khung focus khi click
		btnSanPham.addActionListener(this);
		pnlWest.add(btnSanPham);

		btnKhachHang = new JButton("Khách Hàng", layIconMenu("icons8-customer-25.png"));
		btnKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnKhachHang.setPreferredSize(new Dimension(180, 45));
		btnKhachHang.setBackground(new Color(165, 20, 15));
		btnKhachHang.setForeground(Color.WHITE);
		btnKhachHang.setContentAreaFilled(false); // Xóa nền mặc định
		btnKhachHang.setBorderPainted(false); // Xóa viền nút
		btnKhachHang.setFocusPainted(false); // Xóa khung focus khi click
		btnKhachHang.addActionListener(this);
		pnlWest.add(btnKhachHang);

		btnNhanVien = new JButton("Nhân Viên", layIconMenu("icons8-employees-24.png"));
		btnNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnNhanVien.setPreferredSize(new Dimension(180, 45));
		btnNhanVien.setBackground(new Color(165, 20, 15));
		btnNhanVien.setForeground(Color.WHITE);
		btnNhanVien.setContentAreaFilled(false); // Xóa nền mặc định
		btnNhanVien.setBorderPainted(false); // Xóa viền nút
		btnNhanVien.setFocusPainted(false); // Xóa khung focus khi click
		btnNhanVien.addActionListener(this);
		pnlWest.add(btnNhanVien);

		btnHoaDon = new JButton("Hóa Đơn", layIconMenu("INCOME.png"));
		btnHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnHoaDon.setPreferredSize(new Dimension(180, 45));
		btnHoaDon.setBackground(new Color(165, 20, 15));
		btnHoaDon.setForeground(Color.WHITE);
		btnHoaDon.setContentAreaFilled(false); // Xóa nền mặc định
		btnHoaDon.setBorderPainted(false); // Xóa viền nút
		btnHoaDon.setFocusPainted(false); // Xóa khung focus khi click
		btnHoaDon.addActionListener(this);
		pnlWest.add(btnHoaDon);

		btnThongKe = new JButton("Thống Kê", layIconMenu("statistics.png"));
		btnThongKe.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnThongKe.setPreferredSize(new Dimension(180, 45));
		btnThongKe.setBackground(new Color(165, 20, 15));
		btnThongKe.setForeground(Color.WHITE);
		btnThongKe.setContentAreaFilled(false); // Xóa nền mặc định
		btnThongKe.setBorderPainted(false); // Xóa viền nút
		btnThongKe.setFocusPainted(false); // Xóa khung focus khi click
		btnThongKe.addActionListener(this);
		pnlWest.add(btnThongKe);

		pnlTen.add(lblTen);
		pnlTen.add(lblChucVu);
		pnlTen.setOpaque(false);

		pnlNorth.add(pnlLogo, BorderLayout.WEST);
		pnlNorth.add(pnlTen, BorderLayout.EAST);
		pnlNorth.add(lblTieuDe);

		add(pnlNorth, BorderLayout.NORTH);

		add(pnlVungChua, BorderLayout.CENTER);

		add(pnlWest, BorderLayout.WEST);

		setSize(width, height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Quản Lý Siêu Thị Tiện Lợi");
		btnTrangChu.doClick(); // Tự động click vào nút Trang Chủ khi khởi tạo giao diện

	}

	public static void main(String[] args) {
		new UIQuanLyBanHang().setVisible(true);
		// new UIQuanLyBanHang("admin", "123");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	private void updateVungChua(JPanel newPanel) {
		pnlVungChua.removeAll(); // Xóa nội dung bên trong khung chứa
		pnlVungChua.add(newPanel); // Thêm panel mới vào khung đó
		pnlVungChua.revalidate(); // Tính toán lại layout cho khung
		pnlVungChua.repaint(); // Vẽ lại khung
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		btnTrangChu.setOpaque(false);
		btnBanHang.setOpaque(false);
		btnSanPham.setOpaque(false);
		btnKhachHang.setOpaque(false);
		btnHoaDon.setOpaque(false);
		btnNhanVien.setOpaque(false);
		btnThongKe.setOpaque(false);
		if (o == btnTrangChu) {

			btnTrangChu.setOpaque(true);
			updateVungChua(new PanelTrangChu().getTrangChu());
			// pnlVungChua.removeAll();
			// pnlVungChua = new PanelTrangChu().TrangChu();
			// add(pnlVungChua, BorderLayout.CENTER);
			// revalidate();
			// repaint();
			lblTieuDe.setText(btnTrangChu.getText().toUpperCase());

			// add(pnlTrangChu, BorderLayout.CENTER);
		} else if (o == btnBanHang) {
			btnBanHang.setOpaque(true);
			lblTieuDe.setText(btnBanHang.getText().toUpperCase());
			updateVungChua(new PanelBanHang().getPanelOrder());
		} else if (o == btnSanPham) {
			btnSanPham.setOpaque(true);
			updateVungChua(new PanelSanPham().SanPham(nhanVien));
			lblTieuDe.setText(btnSanPham.getText().toUpperCase());
		} else if (o == btnKhachHang) {
			btnKhachHang.setOpaque(true);
			// add(new PanelCustomer().KhachHang(), BorderLayout.CENTER);
			// pnlVungChua.removeAll();
			// pnlVungChua = new PanelCustomer().KhachHang();
			// add(pnlVungChua, BorderLayout.CENTER);
			// revalidate();
			// repaint();
			updateVungChua(new PanelKhachHang().getKhachHang());

			// new CustomerUI().setVisible(true);

			lblTieuDe.setText(btnKhachHang.getText().toUpperCase());
		} else if (o == btnHoaDon) {
			btnHoaDon.setOpaque(true);
			updateVungChua(new PanelHoaDon().getPanelInvoice());
			lblTieuDe.setText(btnHoaDon.getText().toUpperCase());
		} else if (o == btnNhanVien) {
			btnNhanVien.setOpaque(true);
			updateVungChua(new PanelNhanVien().getNhanVienPanel(nhanVien, this));
			lblTieuDe.setText(btnNhanVien.getText().toUpperCase());
		} else if (o == btnThongKe) {
			btnThongKe.setOpaque(true);
			updateVungChua(new PanelThongKe().thongKe());
			lblTieuDe.setText(btnThongKe.getText().toUpperCase());
		}
		// pnlWest.revalidate();
		pnlWest.repaint(); // sẽ gửi một yêu cầu "vẽ lại" vào hàng đợi
		revalidate();
		repaint();
		// this.revalidate();
		// this.repaint();
	}

	private ImageIcon layIconMenu(String iconName) {
		String path = "imgs/" + iconName;
		if (new java.io.File(path).exists()) {
			Image img = new ImageIcon(path).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
			return new ImageIcon(img);
		}
		return null;
	}

}
