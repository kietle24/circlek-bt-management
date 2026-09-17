package app;

import bus.KhachHang_BUS;
import entity.KhachHang;
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
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PanelKhachHang extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnlCenter;
	private JTextField txtma;
	private JTextField txtten;
	private JTextField txtsdt;
	private DefaultTableModel tableModel;
	private JTable table;
	private JButton btnthem;
	private JButton btnsua;
	private JButton btnxoa;
	private JButton btnlammoi;
	private JButton btntimkiem;
	KhachHang_BUS kh_BUS = new KhachHang_BUS();

	public JPanel getKhachHang() {
		pnlCenter = new JPanel();
		pnlCenter.setPreferredSize(new Dimension(800, 160));
		pnlCenter.setLayout(new BorderLayout());
		pnlCenter.setBackground(new Color(248, 249, 250));
		pnlCenter.setBorder(BorderFactory.createTitledBorder(
		    BorderFactory.createLineBorder(new Color(218, 41, 28), 2),
		    "Thông tin khách hàng", 0, 0,
		    new Font("Tahoma", Font.BOLD, 15),
		    new Color(218, 41, 28)));

		Box b = Box.createVerticalBox();
		Box b1, b2, b3, b4;

		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b1.add(new JLabel("Mã KH:"));
		b1.add(Box.createHorizontalStrut(5));
		b1.add(txtma = new JTextField());
		b1.add(Box.createHorizontalStrut(20));
		b1.add(new JLabel("Tên KH:"));
		b1.add(Box.createHorizontalStrut(5));
		b1.add(txtten = new JTextField());
		b1.add(Box.createHorizontalStrut(20));

		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b2.add(new JLabel("Số điện thoại:"));
		b2.add(Box.createHorizontalStrut(5));
		b2.add(txtsdt = new JTextField());

		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b3.add(Box.createHorizontalGlue());
		b3.add(btnthem    = taoNut("Thêm",    new Color(218, 41, 28)));
		b3.add(Box.createHorizontalStrut(40));
		b3.add(btnsua     = taoNut("Sửa",     new Color(30, 100, 200)));
		b3.add(Box.createHorizontalStrut(40));
		b3.add(btnxoa     = taoNut("Xóa",     new Color(200, 50, 50)));
		b3.add(Box.createHorizontalStrut(40));
		b3.add(btnlammoi  = taoNut("Làm Mới", new Color(108, 117, 125)));
		b3.add(Box.createHorizontalStrut(40));
		b3.add(btntimkiem = taoNut("Tìm",     new Color(245, 130, 32)));
		b3.add(Box.createHorizontalGlue());

		b.add(b4 = Box.createHorizontalBox());
		String[] headers = "Mã;Họ Tên;Số Điện Thoại".split(";");
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		b4.add(scroll);
		b1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
		b2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
		b3.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
		Dimension fieldHeight = new Dimension(Integer.MAX_VALUE, 25);
		table.setDefaultEditor(Object.class, null);
		txtma.setMaximumSize(fieldHeight);
		txtsdt.setMaximumSize(fieldHeight);
		txtten.setMaximumSize(fieldHeight);

		pnlCenter.add(b);
		addTableListener();
		loadTable(kh_BUS.danhSachKhachHang());

		return pnlCenter;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o == btnthem) {
			KhachHang kh = layDuLieu();
			if (kh == null) return;
			try {
				if (kh_BUS.themKhachHang(kh)) {
					JOptionPane.showMessageDialog(null, "Thêm thành công!");
					xoaForm(); loadTable(kh_BUS.danhSachKhachHang());
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
			}

		} else if (o == btnsua) {
			if (txtma.getText().isEmpty()) { JOptionPane.showMessageDialog(null, "Chọn khách hàng cần sửa thông tin!"); return; }
			KhachHang kh = layDuLieu();
			if (kh == null) return;
			try {
				if (kh_BUS.chinhSuaKhachHang(kh)) {
					JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
					xoaForm(); loadTable(kh_BUS.danhSachKhachHang());
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
			}

		} else if (o == btnxoa) {
			if (txtma.getText().isEmpty()) { JOptionPane.showMessageDialog(null, "Chọn khách hàng cần xóa!"); return; }
			int cf = JOptionPane.showConfirmDialog(null, "Xác nhận xóa khách hàng này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
			if (cf == JOptionPane.YES_OPTION) {
				try {
					if (kh_BUS.xoaKhachHang(txtma.getText().trim())) {
						JOptionPane.showMessageDialog(null, "Xóa thành công!");
						xoaForm(); loadTable(kh_BUS.danhSachKhachHang());
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}

		} else if (o == btnlammoi) {
			xoaForm(); loadTable(kh_BUS.danhSachKhachHang());

		} else if (o == btntimkiem) {
			String makh = txtma.getText().trim();
			String hoten = txtten.getText().trim();
			String sdt = txtsdt.getText().trim();
			loadTable(kh_BUS.timKiemKhachHang(makh, hoten, sdt));
		}
	}

	private JButton taoNut(String ten, Color bg) {
		JButton btn = new JButton(ten);
		btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn.setBackground(bg);
		btn.setForeground(Color.WHITE);
		btn.setFocusPainted(false);
		btn.setPreferredSize(new Dimension(100, 35));
		btn.setMaximumSize(new Dimension(100, 35));
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn.addActionListener(this);
		return btn;
	}

	private void loadTable(List<KhachHang> list) {
		tableModel.setRowCount(0);
		for (KhachHang kh : list) {
			tableModel.addRow(new Object[]{
				kh.getMaKhachHang(),
				kh.getHoTen(),
				kh.getSoDienThoai()
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
				KhachHang kh = kh_BUS.timKiemTheoMa(ma);
				if (kh == null) return;
				txtma.setText(kh.getMaKhachHang());
				txtten.setText(kh.getHoTen());
				txtsdt.setText(String.valueOf(kh.getSoDienThoai()));
			}
		});

		table.setRowHeight(35);
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
		txtma.setText(""); txtten.setText("");
		txtsdt.setText("");
		table.clearSelection();
		txtma.requestFocus();
	}

	private KhachHang layDuLieu() {
		String ma  = txtma.getText().trim();
		String ten = txtten.getText().trim();
		String sdt = txtsdt.getText().trim();
		if (ma.isEmpty() || ten.isEmpty() || sdt.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!"); return null;
		}
		KhachHang kh = new KhachHang(ma, ten, sdt);
		return kh;
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 600);
		PanelKhachHang panel = new PanelKhachHang();
		frame.add(panel.getKhachHang());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
}