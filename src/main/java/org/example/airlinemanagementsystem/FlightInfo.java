package org.example.airlinemanagementsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class FlightInfo extends JFrame {

    public FlightInfo() {
        // Thiết lập kích thước frame là 800x500
        setSize(800, 500);

        // Thiết lập vị trí frame tại 400x200
        setLocation(400, 200);

        // Thiết lập màu nền là trắng
        getContentPane().setBackground(Color.WHITE);

        // Thiết lập tiêu đề cho frame
        setTitle("Thông tin chuyến bay");

        // Thiết lập layout null để có thể sử dụng setBounds
        setLayout(null);

        // Tạo JTable để hiển thị dữ liệu
        JTable table = new JTable();

        // Xử lý ngoại lệ và truy vấn dữ liệu từ database
        try {
            // Bước 1: Tạo kết nối đến database
            Conn conn = new Conn();
            Connection c = conn.c;
            Statement s = conn.s;

            // Bước 2: Thực thi truy vấn SQL SELECT * FROM flight
            // Sử dụng executeQuery() để lấy dữ liệu từ bảng flight
            // Đối tượng ResultSet lưu trữ kết quả trả về từ câu lệnh SQL
            ResultSet rs = s.executeQuery("SELECT * FROM flight");

            // Bước 3: Chuyển đổi ResultSet sang TableModel (thay thế cho rs2xml.jar)
            // Sử dụng hàm DbUtils.resultSetToTableModel(rs) để tự động chuyển đổi
            // Trong trường hợp không có rs2xml.jar, sử dụng phương pháp thủ công:
            table.setModel(resultSetToTableModel(rs));

            System.out.println("Truy vấn dữ liệu thành công!");

        } catch (Exception e) {
            // Xử lý ngoại lệ khi truy vấn thất bại
            // Sử dụng khối try-catch để quản lý các lỗi liên quan đến cơ sở dữ liệu
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Lỗi truy vấn dữ liệu: " + e.getMessage(),
                    "Lỗi Database",
                    JOptionPane.ERROR_MESSAGE);
        }

        // Thiết lập vị trí và kích thước cho bảng
        // Sử dụng setBounds để xác định vị trí và kích thước của bảng
        table.setBounds(0, 0, 800, 500);

        // Tạo JScrollPane để thêm thanh cuộn khi dữ liệu quá lớn
        // Giải quyết vấn đề scroll bar khi dữ liệu nhiều hoặc cửa sổ bị thu nhỏ
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 800, 500);

        // Thêm scrollPane (chứa bảng) vào frame
        add(scrollPane);

        // Cho phép hiển thị frame
        setVisible(true);

        // Thiết lập hoạt động đóng cửa sổ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        new FlightInfo();
    }
}
