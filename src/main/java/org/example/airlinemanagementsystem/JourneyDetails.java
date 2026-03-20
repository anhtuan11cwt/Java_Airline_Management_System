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

        // Tạo nhãn "Mã PNR" với font kích thước 16
        JLabel lblPnr = new JLabel("Mã PNR");
        lblPnr.setBounds(50, 100, 120, 25);
        lblPnr.setFont(new Font("Arial", Font.PLAIN, 16));
        add(lblPnr);

        // Tạo JTextField để nhập PNR (biến toàn cục)
        pnr = new JTextField();
        pnr.setBounds(190, 100, 150, 25);
        add(pnr);

        // Tạo nút "Hiển thị" với nền đen và chữ trắng
        JButton btnSearch = new JButton("Hiển thị");
        btnSearch.setBounds(350, 100, 120, 25);
        btnSearch.setBackground(Color.BLACK);
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFocusPainted(false);
        btnSearch.addActionListener(this);
        add(btnSearch);

        // Tạo JTable để hiển thị dữ liệu
        table = new JTable();

        // Tạo DefaultTableModel với mảng cột trống ban đầu
        model = new DefaultTableModel();
        table.setModel(model);

        // Thiết lập màu nền bảng
        table.setBackground(Color.WHITE);

        // Tạo JScrollPane để thêm thanh cuộn khi dữ liệu quá lớn
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 150, 800, 320);

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
        // Xử lý sự kiện khi nhấn nút "Hiển thị"
        if (e.getActionCommand().equals("Hiển thị")) {
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

                // Kiểm tra tính hợp lệ của PNR - sử dụng isBeforeFirst() để kiểm tra xem có dữ
                // liệu trả về không
                // isBeforeFirst() trả về true nếu có ít nhất một hàng dữ liệu
                if (!rs.isBeforeFirst()) {
                    // Không tìm thấy thông tin với PNR đã nhập
                    JOptionPane.showMessageDialog(this,
                            "PNR không hợp lệ - Không tìm thấy thông tin hành trình!",
                            "Lỗi PNR",
                            JOptionPane.ERROR_MESSAGE);
                    return; // Dừng thực thi, không hiển thị bảng trống
                }

                // Nếu PNR hợp lệ, chuyển đổi ResultSet sang TableModel và hiển thị dữ liệu
                model = resultSetToTableModel(rs);
                table.setModel(model);

            } catch (Exception ex) {
                // Xử lý ngoại lệ khi truy vấn thất bại
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Lỗi truy vấn dữ liệu: " + ex.getMessage(),
                        "Lỗi Cơ sở dữ liệu",
                        JOptionPane.ERROR_MESSAGE);
            }
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
