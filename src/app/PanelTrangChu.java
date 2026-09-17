package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.text.DecimalFormat;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import bus.HoaDon_BUS;
import dao.HoaDon_DAO;
import entity.HoaDon;

public class PanelTrangChu extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HoaDon_DAO hoaDon_DAO = new HoaDon_DAO();
	private HoaDon_BUS hoaDon_BUS = new HoaDon_BUS();
	private JTable tblHoaDon;
	private DefaultTableModel tableModelHoaDon;
	private JScrollPane scrollPaneHoaDon;
	private DecimalFormat decimalFormat = new DecimalFormat("#,###.## VNĐ");
	private static final LocalDate  NGAYHIENTAI = LocalDate.now();
	public void loadDanhSachHoaDon() {
		tableModelHoaDon.setRowCount(0);
		for (HoaDon hd : hoaDon_DAO.getHoaDon(NGAYHIENTAI)) {
			Object[] rowData = {
					hd.getMaHoaDon(),
					hd.getNgayTao(),
					hd.getNhanVien().getHoTen(),
					hd.getTongTien()
			};
			tableModelHoaDon.addRow(rowData);
		}
	}
	public JPanel getTrangChu() {
		JPanel pnlTrangChu = new JPanel();
		JPanel pnla = new JPanel();
		JPanel pnlb = new JPanel();
		JPanel pnlc = new JPanel();
		JPanel pnld = new JPanel();
//		JPanel pnle = new JPanel();
		
		pnla.setLayout(new BorderLayout());
		pnla.setPreferredSize(new Dimension(800, 250));
		pnla.setOpaque(false);
		
		
		pnlb.setOpaque(false);
		pnlb.setLayout(new BoxLayout(pnlb, BoxLayout.Y_AXIS));
		pnlb.setBorder(
			    BorderFactory.createTitledBorder(
			        BorderFactory.createLineBorder(new Color(218, 41, 28), 2),
			        LocalDate.now().toString(),
			        TitledBorder.CENTER,
			        TitledBorder.TOP,
			        new Font("Tahoma", Font.BOLD, 16),
			        new Color(218, 41, 28)
			    )
			);
		
		
		pnlTrangChu.setLayout(new BoxLayout(pnlTrangChu, BoxLayout.Y_AXIS));
		pnlTrangChu.setBackground(new Color(248, 249, 250));
		
		pnlc.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlc.setPreferredSize(new Dimension(200,200));
		pnlc.setOpaque(false);
		
		
		pnld.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnld.setPreferredSize(new Dimension(210,200));
		pnld.setOpaque(false);
		
		
//		pnle.setLayout(new BoxLayout(pnle, BoxLayout.X_AXIS));
////		pnle.setPreferredSize(new Dimension(210,200));
//		pnle.setOpaque(false);
		
		tableModelHoaDon = new DefaultTableModel();
		tblHoaDon = new JTable(tableModelHoaDon);
		tableModelHoaDon.addColumn("Mã Hóa Đơn");
		tableModelHoaDon.addColumn("Ngày Tạo");
		tableModelHoaDon.addColumn("Nhân Viên");
		tableModelHoaDon.addColumn("Tổng Tiền");
		
		
		
		scrollPaneHoaDon = new JScrollPane(tblHoaDon);
		scrollPaneHoaDon.setOpaque(false);
		
		tblHoaDon.setOpaque(false);
		tblHoaDon.setRowHeight(25);
		tblHoaDon.getTableHeader().setPreferredSize(new Dimension(0,32));
		tblHoaDon.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		tblHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tblHoaDon.setGridColor(new Color(230, 230, 230));
//		tblHoaDon.setShowGrid(false);
//		tblHoaDon.setShowVerticalLines(false);
//		tblHoaDon.setShowHorizontalLines(false);
		
		tblHoaDon.getTableHeader().setBackground(new Color(218, 41, 28));
		tblHoaDon.getTableHeader().setForeground(Color.WHITE);
		loadDanhSachHoaDon();
		scrollPaneHoaDon.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//		scrollPaneHoaDon.setPreferredSize(new Dimension(800, 300));
		
		JLabel lblSoDon = new JLabel("Số Đơn",JLabel.CENTER);
		lblSoDon.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		ImageIcon iconDon = new ImageIcon("imgs/soDon.png");
		Image imgDon = iconDon.getImage().getScaledInstance(180, 150, Image.SCALE_SMOOTH);
		JLabel lblDon = new JLabel(Integer.toString(tblHoaDon.getRowCount()),new ImageIcon(imgDon),JLabel.CENTER);
		lblDon.setHorizontalTextPosition(JLabel.CENTER);
		lblDon.setVerticalTextPosition(JLabel.CENTER);
		lblDon.setFont(new Font("Tahoma", Font.BOLD, 20));
//		lblDon.setForeground(new Color(198, 153, 88));
		
		JLabel lblDoanhThu = new JLabel("Doanh Thu",JLabel.CENTER);
		lblDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		ImageIcon iconDoanhThu = new ImageIcon("imgs/doanhThu.png");
		Image imgDoanhThu = iconDoanhThu.getImage().getScaledInstance(180, 150, Image.SCALE_SMOOTH);
		JLabel lblDT = new JLabel(decimalFormat.format(hoaDon_BUS.getDoanhThu(NGAYHIENTAI)),new ImageIcon(imgDoanhThu),JLabel.CENTER);
		lblDT.setHorizontalTextPosition(JLabel.CENTER);
		lblDT.setVerticalTextPosition(JLabel.CENTER);
		lblDT.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDT.setForeground(Color.WHITE);
		
		JLabel lblTitle = new JLabel("Lịch sử hoá đơn",JLabel.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblTitle.setForeground(new Color(218, 41, 28));
		
		
//		ImageIcon iconThanh = new ImageIcon("imgs/thanh.png");
//		Image imgThanh = iconThanh.getImage().getScaledInstance(5000, 50, Image.SCALE_SMOOTH);
//		JLabel lblWind = new JLabel(new ImageIcon(imgThanh));
	
		
		pnlc.add(lblSoDon);
		pnlc.add(lblDon);
		
		pnld.add(lblDoanhThu);
		pnld.add(lblDT);
		tblHoaDon.setDefaultEditor(Object.class, null);
//		pnle.add(lblWind);
		
		pnla.add(pnlc,BorderLayout.WEST);
		pnla.add(pnld,BorderLayout.EAST);
		pnla.add(lblTitle,BorderLayout.CENTER);
//		pnla.add(pnle,BorderLayout.SOUTH);
		pnlb.add(Box.createVerticalStrut(10));
		pnlb.add(scrollPaneHoaDon);
		pnlb.add(Box.createVerticalStrut(10));
		pnlTrangChu.add(Box.createVerticalStrut(50));
		pnlTrangChu.add(pnla);
		pnlTrangChu.add(pnlb);
		pnlTrangChu.add(Box.createVerticalStrut(50));
		
		return pnlTrangChu;
	}
	public static void main(String[] args) {
		PanelTrangChu panel = new PanelTrangChu();
		JFrame frame = new JFrame("Quản Lý Siêu Thị Tiện Lợi");
//		JPanel pnlMain = new JPanel();
//		pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
//		pnlMain.add(panel.TrangChu());
		frame.add(panel.getTrangChu());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
//		frame.add(panel.TrangChu());
		frame.setVisible(true);
	}
}
