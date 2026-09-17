package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.HoaDon_DAO;
import dao.NhanVien_DAO;
import entity.NhanVien;

public class PanelHoaDon implements ActionListener {
    private JPanel pnlCenter;
    private JTable tblHoaDon, tblChiTiet;
    private DefaultTableModel modelHoaDon, modelChiTiet;
    
    private JTextField txtTuNgay, txtDenNgay;
    private JComboBox<String> cmbNhanVien;
    private JButton btnSearch;
    
    private List<NhanVien> listNV = new ArrayList<>();
    
    private DecimalFormat df = new DecimalFormat("#,### VNĐ");
    private SimpleDateFormat sdfOut = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private SimpleDateFormat sdfIn = new SimpleDateFormat("dd/MM/yyyy");

    public JPanel getPanelInvoice() {
        pnlCenter = new JPanel(new BorderLayout(10, 10));
        pnlCenter.setBackground(new Color(248, 249, 250));
        pnlCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel pnlSearch = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        pnlSearch.setOpaque(false);
        pnlSearch.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(218, 41, 28), 2),
                "Tìm Kiếm Nâng Cao (Định dạng: dd/MM/yyyy)", TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Tahoma", Font.BOLD, 15), new Color(218, 41, 28)));

        txtTuNgay = new JTextField(10);
        txtDenNgay = new JTextField(10);
        cmbNhanVien = new JComboBox<>();
        
        pnlSearch.add(new JLabel("Từ ngày:"));
        pnlSearch.add(txtTuNgay);
        pnlSearch.add(new JLabel("Đến ngày:"));
        pnlSearch.add(txtDenNgay);
        
        pnlSearch.add(new JLabel("Nhân viên:"));
        pnlSearch.add(cmbNhanVien);

        btnSearch = new JButton("Tìm Kiếm");
        btnSearch.setBackground(new Color(218, 41, 28));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.addActionListener(this); 
        pnlSearch.add(btnSearch);

        JPanel pnlMaster = new JPanel(new BorderLayout());
        pnlMaster.setOpaque(false);
        pnlMaster.setBorder(BorderFactory.createTitledBorder(null, "Danh sách Hóa Đơn", TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 14)));
        
        String[] colsMaster = {"Mã HĐ", "Ngày Tạo", "Nhân Viên", "Khách Hàng", "Tổng Tiền"};
        modelHoaDon = new DefaultTableModel(colsMaster, 0){
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override public boolean isCellEditable(int row, int column) { return false; }
        };
        tblHoaDon = new JTable(modelHoaDon);
        styleTable(tblHoaDon);
        tblHoaDon.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = tblHoaDon.getSelectedRow();
                if (row >= 0) {
                    String maHD = tblHoaDon.getValueAt(row, 0).toString();
                    loadChiTietHoaDon(maHD); 
                }
            }
        });
        pnlMaster.add(new JScrollPane(tblHoaDon), BorderLayout.CENTER);

        JPanel pnlDetail = new JPanel(new BorderLayout());
        pnlDetail.setOpaque(false);
        pnlDetail.setBorder(BorderFactory.createTitledBorder(null, "Chi tiết Hóa Đơn đã chọn", TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 14)));
        
        String[] colsDetail = {"Mã SP", "Tên Sản Phẩm", "Số Lượng", "Đơn Giá", "Thành Tiền"};
        modelChiTiet = new DefaultTableModel(colsDetail, 0){
			private static final long serialVersionUID = 1L;

			@Override public boolean isCellEditable(int row, int column) { return false; }
        };
        tblChiTiet = new JTable(modelChiTiet);
        styleTable(tblChiTiet);
        pnlDetail.add(new JScrollPane(tblChiTiet), BorderLayout.CENTER);

        JSplitPane splitTable = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pnlMaster, pnlDetail);
        splitTable.setResizeWeight(0.6);
        splitTable.setOpaque(false);
        splitTable.setBorder(null);

        pnlCenter.add(pnlSearch, BorderLayout.NORTH);
        pnlCenter.add(splitTable, BorderLayout.CENTER);

        loadComboBoxNhanVien();
        btnSearch.doClick(); 

        return pnlCenter;
    }

    private void styleTable(JTable table) {
        table.setRowHeight(28);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(218, 41, 28));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(254, 226, 226));
        table.setSelectionForeground(Color.BLACK);
    }

    private void loadComboBoxNhanVien() {
        cmbNhanVien.addItem("Tất cả"); 
        NhanVien_DAO nvDao = new NhanVien_DAO();
        listNV = nvDao.getAllNhanVien();
        
        for (NhanVien nv : listNV) {
            cmbNhanVien.addItem(nv.getHoTen());
        }
    }
    private void loadChiTietHoaDon(String maHoaDon) {
        modelChiTiet.setRowCount(0); 
        dao.CTHoaDon_DAO ctDao = new dao.CTHoaDon_DAO();
        List<Object[]> listCT = ctDao.getChiTietByMaHD(maHoaDon);
        
        for (Object[] row : listCT) {
            String maSP = row[0].toString();
            String tenSP = row[1].toString();
            int soLuong = (int) row[2];
            double donGia = (double) row[3];
            double thanhTien = soLuong * donGia;
            
            modelChiTiet.addRow(new Object[]{
                maSP, 
                tenSP, 
                soLuong, 
                df.format(donGia), 
                df.format(thanhTien)
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        
        if (o == btnSearch) {
            java.sql.Date sqlTuNgay = null;
            java.sql.Date sqlDenNgay = null;
            
            try {
                if (!txtTuNgay.getText().trim().isEmpty()) {
                    java.util.Date parsedDate = sdfIn.parse(txtTuNgay.getText().trim());
                    sqlTuNgay = new java.sql.Date(parsedDate.getTime());
                }
                if (!txtDenNgay.getText().trim().isEmpty()) {
                    java.util.Date parsedDate = sdfIn.parse(txtDenNgay.getText().trim());
                    sqlDenNgay = new java.sql.Date(parsedDate.getTime());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(pnlCenter, "Vui lòng nhập ngày đúng định dạng dd/MM/yyyy", "Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String maNV = "ALL";
            int selectedIndex = cmbNhanVien.getSelectedIndex();
            if (selectedIndex > 0) {
                maNV = listNV.get(selectedIndex - 1).getMaNhanVien(); 
            }

            HoaDon_DAO hdDao = new HoaDon_DAO();
            List<Object[]> result = hdDao.timKiemHoaDon(sqlTuNgay, sqlDenNgay, maNV);
            
            modelHoaDon.setRowCount(0); 
            for (Object[] row : result) {
                String ngayTaoStr = sdfOut.format((java.util.Date) row[1]);
                String tongTienStr = df.format(row[4]);
                
                modelHoaDon.addRow(new Object[]{
                    row[0], ngayTaoStr, row[2], row[3], tongTienStr
                });
            }
        }
    }
}