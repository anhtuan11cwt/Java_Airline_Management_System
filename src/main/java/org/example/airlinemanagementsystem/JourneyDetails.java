package org.example.airlinemanagementsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class JourneyDetails extends JFrame implements ActionListener {

    // Khai báo biến toàn cục pnr để lưu trữ JTextField nhập PNR
    public JTextField pnr;

    // Khai báo biến JTable để hiển thị dữ liệu
    public JTable table;

    // Khai báo DefaultTableModel để quản lý dữ liệu bảng
    public DefaultTableModel model;

    public JourneyDetails() {
        // Thiết lập kích thước frame là 800x500
        setSize(800, 500);

        // Thiết lập vị trí frame tại 400x200
        setLocation(400, 200);

        // Thiết lập màu nền là trắng
        getContentPane().setBackground(Color.WHITE);

        // Thiết lập tiêu đề cho frame
        setTitle("Chi tiết hành trình");

        // Thiết lập layout null để có thể sử dụng setBounds
        setLayout(null);

        // Tạo nhãn "Nhập PNR:"
        JLabel lblPnr = new JLabel("Nhập PNR:");
        lblPnr.setBounds(50, 20, 100, 25);
        lblPnr.setFont(new Font("Arial", Font.PLAIN, 14));
        add(lblPnr);

        // Tạo JTextField để nhập PNR (biến toàn cục)
        pnr = new JTextField();
        pnr.setBounds(160, 20, 150, 25);
        add(pnr);

        // Tạo nút "Tìm kiếm"
        JButton btnSearch = new JButton("Tìm kiếm");
        btnSearch.setBounds(320, 20, 100, 25);
        btnSearch.setBackground(new Color(0, 123, 255));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFocusPainted(false);
        btnSearch.addActionListener(this);
        add(btnSearch);

        // Tạo nút "Thoát"
        JButton btnExit = new JButton("Thoát");
        btnExit.setBounds(430, 20, 80, 25);
        btnExit.setBackground(new Color(220, 53, 69));
        btnExit.setForeground(Color.WHITE);
        btnExit.setFocusPainted(false);
        btnExit.addActionListener(this);
        add(btnExit);

        // Tạo JTable để hiển thị dữ liệu
        table = new JTable();

        // Tạo DefaultTableModel với mảng cột trống ban đầu
        model = new DefaultTableModel();
        table.setModel(model);

        // Thiết lập vị trí và kích thước cho bảng
        table.setBounds(0, 60, 800, 400);

        // Tạo JScrollPane để thêm thanh cuộn khi dữ liệu quá lớn
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 60, 800, 400);

        // Thêm scrollPane (chứa bảng) vào frame
        add(scrollPane);

        // Cho phép hiển thị frame
        setVisible(true);

        // Thiết lập hoạt động đóng cửa sổ
        // Sử dụng DISPOSE_ON_CLOSE để chỉ đóng cửa sổ này, không đóng toàn bộ ứng dụng
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Phương thức xử lý sự kiện khi người dùng nhấn nút
     * Override từ interface ActionListener
     * 
     * @param e đối tượng ActionEvent chứa thông tin về sự kiện
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Xử lý sự kiện khi nhấn nút "Tìm kiếm"
        if (e.getActionCommand().equals("Tìm kiếm")) {
            // Lấy giá trị PNR từ text field
            String pnrText = pnr.getText().trim();

            // Kiểm tra nếu PNR trống
            if (pnrText.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Vui lòng nhập PNR!",
                        "Thông báo",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Truy vấn dữ liệu từ database dựa trên PNR
            try {
                // Tạo kết nối đến database
                Conn conn = new Conn();
                Statement s = conn.s;

                // Thực thi truy vấn SQL SELECT với điều kiện PNR
                String query = "SELECT * FROM reservation WHERE pnr = '" + pnrText + "'";
                ResultSet rs = s.executeQuery(query);

                // Chuyển đổi ResultSet sang TableModel
                model = resultSetToTableModel(rs);
                table.setModel(model);

                // Kiểm tra nếu không có dữ liệu
                if (!rs.isBeforeFirst() && !rs.isAfterLast()) {
                    JOptionPane.showMessageDialog(this,
                            "Không tìm thấy dữ liệu với PNR: " + pnrText,
                            "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (Exception ex) {
                // Xử lý ngoại lệ khi truy vấn thất bại
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Lỗi truy vấn dữ liệu: " + ex.getMessage(),
                        "Lỗi Database",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        // Xử lý sự kiện khi nhấn nút "Thoát"
        else if (e.getActionCommand().equals("Thoát")) {
            // Đóng cửa sổ hiện tại
            setVisible(false);
            dispose();
        }
    }

    /**
     * Hàm chuyển đổi ResultSet sang DefaultTableModel
     * Tương tự như DbUtils.resultSetToTableModel(rs)
     * Thay thế cho thư viện rs2xml.jar
     * 
     * @param rs ResultSet chứa dữ liệu từ database
     * @return DefaultTableModel để hiển thị trong JTable
     */
    private DefaultTableModel resultSetToTableModel(ResultSet rs) throws Exception {
        // Tạo đối tượng ResultSetMetaData để lấy thông tin cột
        ResultSetMetaData metaData = rs.getMetaData();

        // Lấy số lượng cột
        int columnCount = metaData.getColumnCount();

        // Tạo mảng tên cột
        String[] columnNames = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
            columnNames[i] = metaData.getColumnLabel(i + 1);
        }

        // Tạo DefaultTableModel với tên cột
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Duyệt qua từng hàng trong ResultSet và thêm vào TableModel
        while (rs.next()) {
            Object[] row = new Object[columnCount];
            for (int i = 0; i < columnCount; i++) {
                row[i] = rs.getObject(i + 1);
            }
            tableModel.addRow(row);
        }

        return tableModel;
    }

    public static void main(String[] args) {
        // Khởi tạo đối tượng ẩn danh để chạy ứng dụng
        new JourneyDetails();
    }
}
