package app;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import entity.SanPham;
import dao.SanPham_DAO;
import dao.KhachHang_DAO;

public class PanelBanHang implements ActionListener {
    private JPanel pnlCenter;
    
    // Bảng Sản Phẩm
    private DefaultTableModel modelSanPham;
    private JTable tblSanPham;
    
    // Bảng Giỏ Hàng
    private DefaultTableModel modelCart;
    private JTable tblCart;
    
    private JLabel lblTongTien;
    private JButton btnXoaMon, btnThanhToan;
    
    private DecimalFormat df = new DecimalFormat("#,### VNĐ");
    private double tongTien = 0;
    private SanPham_DAO sanPhamDAO = new SanPham_DAO();
    private KhachHang_DAO khDao = new KhachHang_DAO();

    public JPanel getPanelOrder() {
        pnlCenter = new JPanel(new BorderLayout(10, 10));
        pnlCenter.setBackground(new Color(248, 249, 250));
        pnlCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel pnlRight = new JPanel(new BorderLayout(0, 10));
        pnlRight.setOpaque(false);

        // 2.1. Bảng Sản Phẩm 
        JPanel pnlMenu = new JPanel(new BorderLayout());
        pnlMenu.setOpaque(false);
        pnlMenu.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(218, 41, 28), 2),
                "Danh Sách Sản Phẩm (Click đúp để thêm vào order)", TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Tahoma", Font.BOLD, 14), new Color(218, 41, 28)));

        String[] colsSP = {"Mã SP", "Tên Sản Phẩm", "Giá Tiền"};
        modelSanPham = new DefaultTableModel(colsSP, 0) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) { return false; } 
        };
        tblSanPham = new JTable(modelSanPham);
        styleTable(tblSanPham);
        pnlMenu.add(new JScrollPane(tblSanPham), BorderLayout.CENTER);

        // 2.2. Bảng Giỏ Hàng 
        JPanel pnlCart = new JPanel(new BorderLayout());
        pnlCart.setOpaque(false);
        
        JLabel lblTitleCart = new JLabel("Giỏ Hàng");
        lblTitleCart.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTitleCart.setForeground(new Color(218, 41, 28));
        
        JPanel pnlTitleCart = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlTitleCart.setOpaque(false);
        pnlTitleCart.add(lblTitleCart);
        pnlCart.add(pnlTitleCart, BorderLayout.NORTH);

        String[] colsCart = {"Mã SP", "Tên Món", "Số Lượng", "Đơn Giá", "Thành Tiền"};
        modelCart = new DefaultTableModel(colsCart, 0) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblCart = new JTable(modelCart);
        styleTable(tblCart);
        pnlCart.add(new JScrollPane(tblCart), BorderLayout.CENTER);

        // 2.3. Khu vực tính tiền & Nút chức năng
        JPanel pnlActions = new JPanel(new BorderLayout());
        pnlActions.setOpaque(false);
        pnlActions.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        lblTongTien = new JLabel("Tổng Tiền: 0 VNĐ");
        lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTongTien.setForeground(Color.RED);
        pnlActions.add(lblTongTien, BorderLayout.WEST);

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlButtons.setOpaque(false);
        
        btnXoaMon = new JButton("Xóa Món Chọn");
        btnXoaMon.setBackground(new Color(200, 50, 50));
        btnXoaMon.setForeground(Color.WHITE);
        btnXoaMon.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnXoaMon.addActionListener(this);

        btnThanhToan = new JButton("Thanh Toán");
        btnThanhToan.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnThanhToan.setBackground(new Color(218, 41, 28));
        btnThanhToan.setForeground(Color.WHITE);
        btnThanhToan.addActionListener(this);

        pnlButtons.add(btnXoaMon);
        pnlButtons.add(btnThanhToan);
        pnlActions.add(pnlButtons, BorderLayout.EAST);
        pnlCart.add(pnlActions, BorderLayout.SOUTH);

        JSplitPane splitRight = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pnlMenu, pnlCart);
        splitRight.setResizeWeight(0.5);
        splitRight.setOpaque(false);
        splitRight.setBorder(null);
        pnlRight.add(splitRight, BorderLayout.CENTER);

        // Không còn panel bàn bên trái — sản phẩm + giỏ hàng chiếm toàn bộ
        pnlCenter.add(pnlRight, BorderLayout.CENTER);
        
        addEvents();
        
        loadDataSanPham();

        return pnlCenter;
    }

    private void addEvents() {
        tblSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { 
                    int row = tblSanPham.getSelectedRow();
                    String maSP = modelSanPham.getValueAt(row, 0).toString();
                    String tenSP = modelSanPham.getValueAt(row, 1).toString();
                    double giaSP = Double.parseDouble(modelSanPham.getValueAt(row, 2).toString());
                    
                    themVaoGioHang(maSP, tenSP, giaSP);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        
        if (o == btnXoaMon) {
            int row = tblCart.getSelectedRow();
            if (row >= 0) {
                modelCart.removeRow(row);
                capNhatTongTien();
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn món cần xóa trong giỏ hàng!");
            }
        } 
        else if (o == btnThanhToan) {
            if (modelCart.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Chưa có món nào để thanh toán!");
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(null, 
                    "Thanh toán với số tiền " + df.format(tongTien) + "?", 
                    "Xác nhận", JOptionPane.YES_NO_OPTION);
                    
            if (confirm == JOptionPane.YES_OPTION) {
                // --- HIỂN THỊ POPUP NHẬP THÔNG TIN KHÁCH HÀNG ---
                JTextField txtTenKH = new JTextField();
                JTextField txtSdtKH = new JTextField();
                Object[] message = {
                    "Tên Khách Hàng (Bỏ trống nếu khách không muốn):", txtTenKH,
                    "Số Điện Thoại:", txtSdtKH
                };

                int option = JOptionPane.showConfirmDialog(null, message, "Nhập thông tin khách hàng", JOptionPane.OK_CANCEL_OPTION);
                
                // Nếu người dùng bấm OK (Xác nhận tạo bill)
                if (option == JOptionPane.OK_OPTION) {
                    String tenKH = txtTenKH.getText().trim();
                    String sdtKH = txtSdtKH.getText().trim();
                    
                    // 1. Gọi KhachHang_DAO xử lý logic khách mới/cũ
                    String maKHCuaBill = khDao.getOrInsertKhachHang(tenKH, sdtKH); // Sẽ trả về null nếu tên rỗng
                    
                    // 2. Lấy mã nhân viên đang đăng nhập
                    String maNhanVienHienTai = "NV001"; // Default backup
                    if (UIQuanLyBanHang.nhanVien != null) {
                        maNhanVienHienTai = UIQuanLyBanHang.nhanVien.getMaNhanVien();
                    }
                    
                    // 3. Tạo Hóa Đơn Mới
                    dao.HoaDon_DAO hdDao = new dao.HoaDon_DAO();
                    String maHoaDonMoi = hdDao.getNextMaHoaDon();
                    
                    // Gọi hàm thanh toán
                    boolean thanhCong = hdDao.thanhToan(maHoaDonMoi, maNhanVienHienTai, tongTien, modelCart, maKHCuaBill);
                    
                    if (thanhCong) {
                        JOptionPane.showMessageDialog(null, "Thanh toán thành công! Mã hóa đơn: " + maHoaDonMoi);
                        
                        // Reset giao diện sau khi thanh toán
                        modelCart.setRowCount(0);
                        capNhatTongTien();
                    } else {
                        JOptionPane.showMessageDialog(null, "Thanh toán thất bại! Vui lòng kiểm tra lại CSDL.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    private void themVaoGioHang(String maSP, String tenSP, double giaSP) {
        boolean daCo = false;
        for (int i = 0; i < modelCart.getRowCount(); i++) {
            if (modelCart.getValueAt(i, 0).toString().equals(maSP)) {
                int soLuongCu = Integer.parseInt(modelCart.getValueAt(i, 2).toString());
                int soLuongMoi = soLuongCu + 1;
                double thanhTienMoi = soLuongMoi * giaSP;
                
                modelCart.setValueAt(soLuongMoi, i, 2);
                modelCart.setValueAt(thanhTienMoi, i, 4);
                daCo = true;
                break;
            }
        }
        if (!daCo) {
            modelCart.addRow(new Object[]{maSP, tenSP, 1, giaSP, giaSP});
        }
        capNhatTongTien();
    }

    private void capNhatTongTien() {
        tongTien = 0;
        for (int i = 0; i < modelCart.getRowCount(); i++) {
            tongTien += Double.parseDouble(modelCart.getValueAt(i, 4).toString());
        }
        lblTongTien.setText("Tổng Tiền: " + df.format(tongTien));
    }

    private void styleTable(JTable table) {
        table.setRowHeight(28);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(218, 41, 28));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(254, 226, 226));
        table.setSelectionForeground(Color.BLACK);
    }

    private void loadDataSanPham() {
        modelSanPham.setRowCount(0); 
        List<SanPham> listSP = sanPhamDAO.getAllSanPhamBan(); 
        
        for (SanPham sp : listSP) {
            modelSanPham.addRow(new Object[]{
                sp.getMaSanPham(), 
                sp.getTenSanPham(), 
                sp.getGiaTien()
            });
        }
    }
   
}