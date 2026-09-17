package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import bus.HoaDon_BUS;
import dao.CTHoaDon_DAO;
import entity.KhachHangThongKe;
import entity.SanPhamThongKe;

public class PanelThongKe extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnlThongKe,pnlTongQuan,pnlSanPhamBanChay,pnlKhachHang;
	private JLabel lblDoanhThu, lblSoLuongHoaDon,lblSanPhamBanChay,lblSoLuongDaBan;
	private JComboBox<String> cboTQ,cboSPBC,cboKH;
	
	private JPanel tongQuan(int so) {
		if(so ==1) {
			lblDoanhThu = new JLabel(new HoaDon_BUS().getDoanhThu(LocalDate.now().getMonthValue(),LocalDate.now().getMonthValue(), LocalDate.now().getYear())+"");
			lblDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 24));
			lblSoLuongHoaDon = new JLabel(new HoaDon_BUS().getSoLuongHoaDon(LocalDate.now().getMonthValue(),LocalDate.now().getMonthValue(), LocalDate.now().getYear())+"");
			lblSoLuongHoaDon.setFont(new Font("Tahoma", Font.BOLD, 24));
			lblSanPhamBanChay = new JLabel(new bus.CTHoaDon_BUS().getTenSanPhamBanChay(LocalDate.now().getMonthValue(),LocalDate.now().getMonthValue(), LocalDate.now().getYear()));
			lblSanPhamBanChay.setFont(new Font("Tahoma", Font.BOLD, 24));
			lblSoLuongDaBan = new JLabel(new bus.CTHoaDon_BUS().getSoLuongDaBan(LocalDate.now().getMonthValue(),LocalDate.now().getMonthValue(), LocalDate.now().getYear()));
			lblSoLuongDaBan.setFont(new Font("Tahoma", Font.BOLD, 24));
		}
		else if(so == 0) {
			lblDoanhThu = new JLabel(new HoaDon_BUS().getDoanhThu()+"");
			lblDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 24));
			lblSoLuongHoaDon = new JLabel(new HoaDon_BUS().getSoLuongHoaDon()+"");
			lblSoLuongHoaDon.setFont(new Font("Tahoma", Font.BOLD, 24));
			lblSanPhamBanChay = new JLabel(new bus.CTHoaDon_BUS().getTenSanPhamBanChay());
			lblSanPhamBanChay.setFont(new Font("Tahoma", Font.BOLD, 24));
			lblSoLuongDaBan = new JLabel(new bus.CTHoaDon_BUS().getSoLuongDaBan()+"");
			lblSoLuongDaBan.setFont(new Font("Tahoma", Font.BOLD, 24));
			
		}
		else if(so == 3) {
			
			lblDoanhThu = new JLabel(new HoaDon_BUS().getDoanhThu(LocalDate.now().minusMonths(so).getMonthValue(),LocalDate.now().getMonthValue(), LocalDate.now().getYear())+"");
			lblDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 24));
			lblSoLuongHoaDon = new JLabel(new HoaDon_BUS().getSoLuongHoaDon(LocalDate.now().minusMonths(so).getMonthValue(),LocalDate.now().getMonthValue(), LocalDate.now().getYear())+"");
			lblSoLuongHoaDon.setFont(new Font("Tahoma", Font.BOLD, 24));
			lblSanPhamBanChay = new JLabel(new bus.CTHoaDon_BUS().getTenSanPhamBanChay(LocalDate.now().minusMonths(so).getMonthValue(),LocalDate.now().getMonthValue(), LocalDate.now().getYear()));
			lblSanPhamBanChay.setFont(new Font("Tahoma", Font.BOLD, 24));
			lblSoLuongDaBan = new JLabel(new bus.CTHoaDon_BUS().getSoLuongDaBan(LocalDate.now().minusMonths(so).getMonthValue(),LocalDate.now().getMonthValue(), LocalDate.now().getYear())+"");
			lblSoLuongDaBan.setFont(new Font("Tahoma", Font.BOLD, 24));
		}
		else {
			return new JPanel();
		}
			
		GridLayout gridLayout = new GridLayout(2, 2);
		gridLayout.setHgap(20);
		gridLayout.setVgap(20);
		JPanel TongQuan = new JPanel(gridLayout);
		JPanel pnlBottom = new JPanel();
		JPanel pnlTop = new JPanel();
		pnlTop.setOpaque(false);
		pnlBottom.setOpaque(false);
		TongQuan.setOpaque(false);
//		TongQuan.setLayout(new BoxLayout(TongQuan, BoxLayout.Y_AXIS));
//		TongQuan.setLayout(new GridLayout(2, 2));
//		TongQuan.setHgap(20);
		pnlTop.setLayout(new BoxLayout(pnlTop, BoxLayout.X_AXIS));
		pnlBottom.setLayout(new BoxLayout(pnlBottom, BoxLayout.X_AXIS));
		
		JLabel lbla = new JLabel("Tổng doanh thu");
		lbla.setFont(new Font("Tahoma", Font.PLAIN, 16));
		JLabel lblb = new JLabel("Số lượng hóa đơn");
		lblb.setFont(new Font("Tahoma", Font.PLAIN, 16));
		JLabel lblc = new JLabel("Số lượng đã bán");
		lblc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		JLabel lbld = new JLabel("Sản phẩm bán chạy");
		lbld.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JPanel pnlTQ1 = new JPanel(new BorderLayout());
		JPanel pnlTQ2 = new JPanel(new BorderLayout());
		JPanel pnlTQ3 = new JPanel(new BorderLayout());
		JPanel pnlTQ4 = new JPanel(new BorderLayout());
		JPanel pnla = new JPanel(new FlowLayout(FlowLayout.CENTER,0,120));
		JPanel pnlb = new JPanel(new FlowLayout(FlowLayout.CENTER,0,120));
		JPanel pnlc = new JPanel(new FlowLayout(FlowLayout.CENTER,0,120));
		JPanel pnld = new JPanel(new FlowLayout(FlowLayout.CENTER,0,120));
		pnla.add(lblDoanhThu);
		pnlb.add(lblSoLuongHoaDon);
		pnlc.add(lblSoLuongDaBan);
		pnld.add(lblSanPhamBanChay);
		pnla.setOpaque(false);
		pnlb.setOpaque(false);
		pnlc.setOpaque(false);
		pnld.setOpaque(false);
		pnlTQ1.setBackground(Color.WHITE);
		pnlTQ1.add(new JPanel(new FlowLayout(FlowLayout.LEFT,50,10)).add(lbla), BorderLayout.NORTH);
		pnlTQ1.add(pnla, BorderLayout.CENTER);
		pnlTQ2.setBackground(Color.WHITE);
		pnlTQ2.add(new JPanel(new FlowLayout(FlowLayout.LEFT,50,10)).add(lblb), BorderLayout.NORTH);
		pnlTQ2.add(pnlb, BorderLayout.CENTER);
		pnlTQ3.setBackground(Color.WHITE);
		pnlTQ3.add(new JPanel(new FlowLayout(FlowLayout.LEFT,50,10)).add(lblc), BorderLayout.NORTH);
		pnlTQ3.add(pnlc, BorderLayout.CENTER);
		pnlTQ4.setBackground(Color.WHITE);
		pnlTQ4.add(new JPanel(new FlowLayout(FlowLayout.LEFT,50,10)).add(lbld), BorderLayout.NORTH);
		pnlTQ4.add(pnld, BorderLayout.CENTER);
		pnlTop.add(Box.createHorizontalStrut(20));
		pnlTop.add(pnlTQ1);
		pnlTop.add(Box.createHorizontalStrut(20));
		pnlTop.add(pnlTQ2);
		pnlTop.add(Box.createHorizontalStrut(20));
		pnlBottom.add(Box.createHorizontalStrut(20));
		pnlBottom.add(pnlTQ3);
		pnlBottom.add(Box.createHorizontalStrut(20));
		pnlBottom.add(pnlTQ4);
		pnlBottom.add(Box.createHorizontalStrut(20));
//		TongQuan.add(Box.createVerticalStrut(20));
//		TongQuan.add(pnlTop);
//		TongQuan.add(Box.createVerticalStrut(20));
//		TongQuan.add(pnlBottom);
//		TongQuan.add(Box.createVerticalStrut(20));
		TongQuan.add(pnlTQ1);
		TongQuan.add(pnlTQ2);
		TongQuan.add(pnlTQ3);
		TongQuan.add(pnlTQ4);
		TongQuan.setBorder(
			    BorderFactory.createTitledBorder(
				        BorderFactory.createLineBorder(new Color(218, 41, 28), 2),
				       "Thống kê tổng quan",
				        TitledBorder.CENTER,
				        TitledBorder.TOP,
				        new Font("Tahoma", Font.BOLD, 16),
				        new Color(218, 41, 28)
				    )
				);
		return TongQuan;
	}
	
	private JPanel sanPhamBanChay(int so) {
		ArrayList<SanPhamThongKe> listSanPhamBanChay = new ArrayList<SanPhamThongKe>();
		if(so ==1) {
			listSanPhamBanChay = new CTHoaDon_DAO().getdanhSachSanPham(LocalDate.now().getMonthValue(),LocalDate.now().getMonthValue(), LocalDate.now().getYear());
		}
		else if(so == 0) {
			listSanPhamBanChay = new CTHoaDon_DAO().getdanhSachSanPham();
		}
		else if(so == 3) {
			listSanPhamBanChay = new CTHoaDon_DAO().getdanhSachSanPham(LocalDate.now().minusMonths(so).getMonthValue(),LocalDate.now().getMonthValue(), LocalDate.now().getYear());
		}
		else {
			return new JPanel();
		}
		JPanel pnlSanPhamBanChay = new JPanel();
		pnlSanPhamBanChay.setOpaque(false);
		DefaultTableModel tableModelSanPhamBanChay = new DefaultTableModel();
		JTable tblSanPhamBanChay = new JTable(tableModelSanPhamBanChay);
		JScrollPane scrollPaneSanPhamBanChay = new JScrollPane(tblSanPhamBanChay);
		tableModelSanPhamBanChay.addColumn("STT");
		tableModelSanPhamBanChay.addColumn("Mã Sản Phẩm");
		tableModelSanPhamBanChay.addColumn("Tên Sản Phẩm");
		tableModelSanPhamBanChay.addColumn("Số Lượng Đã Bán");
		tableModelSanPhamBanChay.addColumn("Doanh Thu");
		tblSanPhamBanChay.setOpaque(false);
		tblSanPhamBanChay.setRowHeight(25);
		tblSanPhamBanChay.getTableHeader().setPreferredSize(new Dimension(0,32));
		tblSanPhamBanChay.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		tblSanPhamBanChay.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tblSanPhamBanChay.setGridColor(new Color(230, 230, 230));
		
		tblSanPhamBanChay.getTableHeader().setBackground(new Color(218, 41, 28));
		tblSanPhamBanChay.getTableHeader().setForeground(Color.WHITE);
		for (int i = 0; i < listSanPhamBanChay.size(); i++) {
			SanPhamThongKe sp = listSanPhamBanChay.get(i);
			tableModelSanPhamBanChay.addRow(new Object[] {
					i+1, sp.getSanPham().getMaSanPham(), sp.getSanPham().getTenSanPham(), sp.getSoLuongBan(), sp.getTongTienBan()
			});
		}
		pnlSanPhamBanChay.setBorder(
			    BorderFactory.createTitledBorder(
				        BorderFactory.createLineBorder(new Color(218, 41, 28), 2),
				       "Thống kê sản phẩm bán chạy",
				        TitledBorder.CENTER,
				        TitledBorder.TOP,
				        new Font("Tahoma", Font.BOLD, 16),
				        new Color(218, 41, 28)
				    )
				);
		pnlSanPhamBanChay.setLayout(new BoxLayout(pnlSanPhamBanChay, BoxLayout.Y_AXIS));
		pnlSanPhamBanChay.add(Box.createVerticalStrut(20));
		pnlSanPhamBanChay.add(scrollPaneSanPhamBanChay);
		pnlSanPhamBanChay.add(Box.createVerticalStrut(20));
		
		return pnlSanPhamBanChay;
	}
	private JPanel khachHang(int so) {
		ArrayList<KhachHangThongKe> listKhachHang = new ArrayList<entity.KhachHangThongKe>();
		if(so ==1) {
			listKhachHang = new dao.KhachHang_DAO().getThongKeKhachHang(LocalDate.now().getMonthValue(),LocalDate.now().getMonthValue(), LocalDate.now().getYear());
		}
		else if(so == 0) {
			listKhachHang = new dao.KhachHang_DAO().getThongKeKhachHang();
		}
		else if(so == 3) {
			listKhachHang = new dao.KhachHang_DAO().getThongKeKhachHang(LocalDate.now().minusMonths(so).getMonthValue(),LocalDate.now().getMonthValue(), LocalDate.now().getYear());
		}
		else {
			return new JPanel();
		}
		JPanel pnlKhachHang = new JPanel();
		pnlKhachHang.setOpaque(false);
		DefaultTableModel tableModelKhachHang = new DefaultTableModel();
		JTable tblKhachHang = new JTable(tableModelKhachHang);
		JScrollPane scrollPaneKhachHang = new JScrollPane(tblKhachHang);
		tableModelKhachHang.addColumn("STT");
		tableModelKhachHang.addColumn("Mã Khách Hàng");
		tableModelKhachHang.addColumn("Tên Khách Hàng");
		tableModelKhachHang.addColumn("Số Điện Thoại");
		tableModelKhachHang.addColumn("Số Đơn Hàng");
		tableModelKhachHang.addColumn("Tổng Chi Tiêu");
		tblKhachHang.setOpaque(false);
		tblKhachHang.setRowHeight(25);
		tblKhachHang.getTableHeader().setPreferredSize(new Dimension(0,32));
		tblKhachHang.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		tblKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tblKhachHang.setGridColor(new Color(230, 230, 230));
		tblKhachHang.getTableHeader().setBackground(new Color(218, 41, 28));
		tblKhachHang.getTableHeader().setForeground(Color.WHITE);
		for (int i = 0; i < listKhachHang.size(); i++) {
			KhachHangThongKe kh = listKhachHang.get(i);
			tableModelKhachHang.addRow(new Object[] {
					i+1, kh.getKhachHang().getMaKhachHang(), kh.getKhachHang().getHoTen(), kh.getKhachHang().getSoDienThoai(), kh.getSoDonHang(), kh.getTongChiTieu()
			});
		}
		pnlKhachHang.setBorder(
			    BorderFactory.createTitledBorder(
				        BorderFactory.createLineBorder(new Color(218, 41, 28), 2),
				       "Thống kê khách hàng mua hàng",
				        TitledBorder.CENTER,
				        TitledBorder.TOP,
				        new Font("Tahoma", Font.BOLD, 16),
				        new Color(218, 41, 28)
				    )
				);
		pnlKhachHang.setLayout(new BoxLayout(pnlKhachHang, BoxLayout.Y_AXIS));
		pnlKhachHang.add(Box.createVerticalStrut(20));
		pnlKhachHang.add(scrollPaneKhachHang);
		pnlKhachHang.add(Box.createVerticalStrut(20));
		return pnlKhachHang;
	}
	public JPanel thongKe() {
//		JFrame frame = new JFrame("Thống kê");
		
		pnlThongKe = new JPanel();
		pnlTongQuan = new JPanel();
		pnlSanPhamBanChay = new JPanel();
		pnlKhachHang = new JPanel();
		
		pnlThongKe.setLayout(new BorderLayout());
		
		pnlTongQuan.setLayout(new BorderLayout());
		pnlTongQuan.setBackground(new Color(248, 249, 250));
		JPanel pnlTQTop = new JPanel();
		pnlTQTop.setOpaque(false);
		pnlTQTop.setLayout(new FlowLayout(FlowLayout.LEFT));
		cboTQ = new JComboBox<>(new String[] {"1 Tháng", "Từ trước tới nay", "3 Tháng"});
		cboTQ.addActionListener(this);
		pnlTQTop.add(new JLabel("Chọn khoảng thời gian:"));
		pnlTQTop.add(cboTQ);
		pnlTongQuan.add(pnlTQTop, BorderLayout.NORTH);
		pnlTongQuan.add(tongQuan(1), BorderLayout.CENTER);
		
		pnlSanPhamBanChay.setLayout(new BorderLayout());
		pnlSanPhamBanChay.setBackground(new Color(248, 249, 250));
		JPanel pnlSPBCTop = new JPanel();
		pnlSPBCTop.setOpaque(false);
		pnlSPBCTop.setLayout(new FlowLayout(FlowLayout.LEFT));
		cboSPBC = new JComboBox<>(new String[] {"1 Tháng", "Từ trước tới nay", "3 Tháng"});
		cboSPBC.addActionListener(this);
		pnlSPBCTop.add(new JLabel("Chọn khoảng thời gian:"));
		pnlSPBCTop.add(cboSPBC);
		pnlSanPhamBanChay.add(pnlSPBCTop, BorderLayout.NORTH);
		pnlSanPhamBanChay.add(sanPhamBanChay(1), BorderLayout.CENTER);
		
		pnlKhachHang.setLayout(new BorderLayout());
		pnlKhachHang.setBackground(new Color(248, 249, 250));
		JPanel pnlKHTop = new JPanel();
		pnlKHTop.setOpaque(false);
		pnlKHTop.setLayout(new FlowLayout(FlowLayout.LEFT));
		cboKH = new JComboBox<>(new String[] {"1 Tháng", "Từ trước tới nay", "3 Tháng"});
		cboKH.addActionListener(this);
		pnlKHTop.add(new JLabel("Chọn khoảng thời gian:"));
		pnlKHTop.add(cboKH);
		pnlKhachHang.add(pnlKHTop, BorderLayout.NORTH);
		pnlKhachHang.add(khachHang(1), BorderLayout.CENTER);
		
		
		
		
		JTabbedPane tabbedPane = new JTabbedPane();
//		tabbedPane.setBackground(new Color(253, 243, 227));
//		tabbedPane.get
		tabbedPane.addTab("Tổng quan", pnlTongQuan);
		tabbedPane.addTab("Sản phẩm bán chạy", pnlSanPhamBanChay);
		tabbedPane.addTab("Khách hàng", pnlKhachHang);
		
		pnlThongKe.add(tabbedPane);
		
		return pnlThongKe;
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("Thống kê");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		PanelThongKe panelThongKe = new PanelThongKe();
		frame.add(panelThongKe.thongKe());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		
		if(o.equals(cboTQ)) {
			pnlTongQuan.removeAll();
			JPanel pnlTQTop = new JPanel();
			pnlTQTop.setOpaque(false);
			pnlTQTop.setLayout(new FlowLayout(FlowLayout.LEFT));
//			cboTQ = new JComboBox<>(new String[] {"1 Tháng", "Từ trước tới nay", "3 Tháng", "6 Tháng", "1 Năm"});
//			cboTQ.addActionListener(this);
			
			int selectedIndex = cboTQ.getSelectedIndex();
			
			switch (selectedIndex) {
			case 0:
				pnlTQTop.add(new JLabel("Chọn khoảng thời gian:"));
				cboTQ.setSelectedIndex(0);
				pnlTQTop.add(cboTQ);
				pnlTongQuan.add(pnlTQTop, BorderLayout.NORTH);
				pnlTongQuan.add(tongQuan(1), BorderLayout.CENTER);
				pnlTongQuan.revalidate();
				pnlTongQuan.repaint();
				break;
			case 1:
				pnlTQTop.add(new JLabel("Chọn khoảng thời gian:"));
				cboTQ.setSelectedIndex(1);
				pnlTQTop.add(cboTQ);
				pnlTongQuan.add(pnlTQTop, BorderLayout.NORTH);
				pnlTongQuan.add(tongQuan(0), BorderLayout.CENTER);
				pnlTongQuan.revalidate();
				pnlTongQuan.repaint();
				break;
			case 2:
				pnlTQTop.add(new JLabel("Chọn khoảng thời gian:"));
				cboTQ.setSelectedIndex(2);
				pnlTQTop.add(cboTQ);
				pnlTongQuan.add(pnlTQTop, BorderLayout.NORTH);
				pnlTongQuan.add(tongQuan(3), BorderLayout.CENTER);
				pnlTongQuan.revalidate();
				pnlTongQuan.repaint();
				break;
			
			default:
				break;
			}
		}
		else if(o.equals(cboSPBC)) {
			pnlSanPhamBanChay.removeAll();
			JPanel pnlSPBCTop = new JPanel();
			pnlSPBCTop.setOpaque(false);
			pnlSPBCTop.setLayout(new FlowLayout(FlowLayout.LEFT));
			
			int selectedIndex = cboSPBC.getSelectedIndex();
			
			switch (selectedIndex) {
			case 0:
				pnlSPBCTop.add(new JLabel("Chọn khoảng thời gian:"));
				cboSPBC.setSelectedIndex(0);
				pnlSPBCTop.add(cboSPBC);
				pnlSanPhamBanChay.add(pnlSPBCTop, BorderLayout.NORTH);
				pnlSanPhamBanChay.add(sanPhamBanChay(1), BorderLayout.CENTER);
				pnlSanPhamBanChay.revalidate();
				pnlSanPhamBanChay.repaint();
				break;
			case 1:
				pnlSPBCTop.add(new JLabel("Chọn khoảng thời gian:"));
				cboSPBC.setSelectedIndex(1);
				pnlSPBCTop.add(cboSPBC);
				pnlSanPhamBanChay.add(pnlSPBCTop, BorderLayout.NORTH);
				pnlSanPhamBanChay.add(sanPhamBanChay(0), BorderLayout.CENTER);
				pnlSanPhamBanChay.revalidate();
				pnlSanPhamBanChay.repaint();
				break;
			case 2:
				pnlSPBCTop.add(new JLabel("Chọn khoảng thời gian:"));
				cboSPBC.setSelectedIndex(2);
				pnlSPBCTop.add(cboSPBC);
				pnlSanPhamBanChay.add(pnlSPBCTop, BorderLayout.NORTH);
				pnlSanPhamBanChay.add(sanPhamBanChay(3), BorderLayout.CENTER);
				pnlSanPhamBanChay.revalidate();
				pnlSanPhamBanChay.repaint();
				break;
			
			default:
				break;
			}
		}
		else if(o.equals(cboKH)) {
			pnlKhachHang.removeAll();
			JPanel pnlKHTop = new JPanel();
			pnlKHTop.setOpaque(false);
			pnlKHTop.setLayout(new FlowLayout(FlowLayout.LEFT));
			
			int selectedIndex = cboKH.getSelectedIndex();
			
			switch (selectedIndex) {
			case 0:
				pnlKHTop.add(new JLabel("Chọn khoảng thời gian:"));
				cboKH.setSelectedIndex(0);
				pnlKHTop.add(cboKH);
				pnlKhachHang.add(pnlKHTop, BorderLayout.NORTH);
				pnlKhachHang.add(khachHang(1), BorderLayout.CENTER);
				pnlKhachHang.revalidate();
				pnlKhachHang.repaint();
				break;
			case 1:
				pnlKHTop.add(new JLabel("Chọn khoảng thời gian:"));
				cboKH.setSelectedIndex(1);
				pnlKHTop.add(cboKH);
				pnlKhachHang.add(pnlKHTop, BorderLayout.NORTH);
				pnlKhachHang.add(khachHang(0), BorderLayout.CENTER);
				pnlKhachHang.revalidate();
				pnlKhachHang.repaint();
				break;
			case 2:
				pnlKHTop.add(new JLabel("Chọn khoảng thời gian:"));
				cboKH.setSelectedIndex(2);
				pnlKHTop.add(cboKH);
				pnlKhachHang.add(pnlKHTop, BorderLayout.NORTH);
				pnlKhachHang.add(khachHang(3), BorderLayout.CENTER);
				pnlKhachHang.revalidate();
				pnlKhachHang.repaint();
				break;
			
			default:
				break;
			}
		}
	}
}
