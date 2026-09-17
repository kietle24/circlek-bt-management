package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.NhanVien_DAO;
import entity.NhanVien;

public class UIDangNhap extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	Dimension kichThuocMan = Toolkit.getDefaultToolkit().getScreenSize(); // Toolkit lay chi so kich thuoc hien tai cua
																			// man hinh
	int width = (int) (kichThuocMan.getWidth() * 0.8);
	int height = (int) (kichThuocMan.getHeight() * 0.85);
	JTextField txtTaiKhoan, txtMatKhau;
	JPanel pnlDangNhap;
	JLabel lblDangNhap;
	JLayeredPane phanLop;
	JButton btnDangNhap;
	Color mauChu;
	Color mautxt;
	Dimension khoangCach = new Dimension(100, 0);

	public JTextField getTxtTaiKhoan() {
		return txtTaiKhoan;
	}

	public void setTxtTaiKhoan(JTextField txtTaiKhoan) {
		this.txtTaiKhoan = txtTaiKhoan;
	}

	public JTextField getTxtMatKhau() {
		return txtMatKhau;
	}

	public void setTxtMatKhau(JTextField txtMatKhau) {
		this.txtMatKhau = txtMatKhau;
	}

	public UIDangNhap() {

		String imgPath = "imgs/login.png";
		ImageIcon anh = new ImageIcon(imgPath);
		Image anhSua = anh.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		JLabel background = new JLabel(new ImageIcon(anhSua));

		mauChu = Color.WHITE;
		mautxt = Color.WHITE;

		lblDangNhap = new JLabel("Đăng Nhập");
		lblDangNhap.setForeground(Color.WHITE);
		lblDangNhap.setFont(new Font("Tohoma", Font.BOLD, 36));

		txtTaiKhoan = new JTextField("admin");
		txtMatKhau = new JPasswordField("123");
		txtTaiKhoan.setOpaque(false);
		txtMatKhau.setOpaque(false);
		txtTaiKhoan.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		txtTaiKhoan.setFont(new Font("Tohoma", Font.BOLD, 18));
		txtTaiKhoan.setForeground(Color.WHITE);
		txtTaiKhoan.setCaretColor(Color.WHITE);
		txtMatKhau.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		txtMatKhau.setFont(new Font("Tohoma", Font.BOLD, 18));
		txtMatKhau.setForeground(Color.WHITE);
		txtMatKhau.setCaretColor(Color.WHITE);

		btnDangNhap = new JButton("Đăng Nhập");
		btnDangNhap.setBackground(new Color(218, 41, 28));
		btnDangNhap.setForeground(Color.WHITE);
		btnDangNhap.setFont(new Font("Tohoma", Font.BOLD, 18));
		btnDangNhap.setPreferredSize(new Dimension(150, 60));
		btnDangNhap.addActionListener(this);

		JPanel pnlTieuDe = new JPanel();
		pnlTieuDe.add(lblDangNhap);
		pnlTieuDe.setOpaque(false);

		pnlDangNhap = new JPanel();
		pnlDangNhap.setLayout(new BoxLayout(pnlDangNhap, BoxLayout.Y_AXIS));
		pnlDangNhap.setOpaque(false); // tắt độ mờ đục
		int widthDangNhap = 550;
		int heightDangNhap = 400;

		JPanel pnlTaiKhoan = new JPanel();
		JPanel pnlMatKhau = new JPanel();
		JPanel pnlNut = new JPanel();
		pnlTaiKhoan.setLayout(new BoxLayout(pnlTaiKhoan, BoxLayout.X_AXIS));
		pnlMatKhau.setLayout(new BoxLayout(pnlMatKhau, BoxLayout.X_AXIS));
		JLabel lblTK = new JLabel("Tài khoản");
		lblTK.setPreferredSize(khoangCach);
		lblTK.setForeground(Color.WHITE);
		lblTK.setFont(new Font("Tohoma", Font.BOLD, 18));
		JLabel lblMK = new JLabel("Mật khẩu");
		lblMK.setForeground(Color.WHITE);
		lblMK.setPreferredSize(khoangCach);
		lblMK.setFont(new Font("Tohoma", Font.BOLD, 18));

		pnlTaiKhoan.add(lblTK);
		pnlTaiKhoan.add(txtTaiKhoan);
		pnlTaiKhoan.add(Box.createHorizontalStrut(80));
		pnlTaiKhoan.setOpaque(false);

		pnlMatKhau.add(lblMK);
		pnlMatKhau.add(txtMatKhau);
		pnlMatKhau.add(Box.createHorizontalStrut(80));
		pnlMatKhau.setOpaque(false);

		pnlNut.add(btnDangNhap);
		pnlNut.setOpaque(false);

		pnlDangNhap.add(pnlTieuDe);
		pnlDangNhap.add(Box.createVerticalStrut(50));
		pnlDangNhap.add(pnlTaiKhoan);
		pnlDangNhap.add(Box.createVerticalStrut(60));
		pnlDangNhap.add(pnlMatKhau);
		pnlDangNhap.add(Box.createVerticalStrut(80));
		pnlDangNhap.add(pnlNut);

		phanLop = new JLayeredPane();
		background.setSize(width, height);
		pnlDangNhap.setBounds((width - widthDangNhap) / 2, (height - heightDangNhap) / 2, widthDangNhap,
				heightDangNhap);
		phanLop.add(background, JLayeredPane.DEFAULT_LAYER);
		phanLop.add(pnlDangNhap, JLayeredPane.PALETTE_LAYER);
		phanLop.setSize(width, height);

		// add(background);
		// add(pnlDangNhap);
		setTitle("Đăng Nhập - Siêu Thị Tiện Lợi");
		setContentPane(phanLop);
		setSize(width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == btnDangNhap) {
			NhanVien nv = new NhanVien_DAO().timTheoUser(txtTaiKhoan.getText(), txtMatKhau.getText());
			if (nv != null) {
				JOptionPane.showMessageDialog(this, "Đăng nhập thành công");
				new UIQuanLyBanHang(txtTaiKhoan.getText(), txtMatKhau.getText());
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Sai tài khoản mật khẩu", "ERROR", JOptionPane.ERROR_MESSAGE);
				txtTaiKhoan.selectAll();
				txtTaiKhoan.requestFocus();
			}
		}

	}

	public static void main(String[] args) {
		new UIDangNhap().setVisible(true);
	}

}
