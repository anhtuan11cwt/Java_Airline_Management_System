package org.example.airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;

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

        // Tạo tiêu đề cột cho bảng
        String[] columnNames = { "Mã chuyến bay", "Từ", "Đến", "Giờ khởi hành", "Giờ đến", "Thời gian bay", "Giá vé" };

        // Tạo dữ liệu mẫu cho bảng (có thể thay đổi sau)
        String[][] data = {
                { "VN101", "Hà Nội", "TP. Hồ Chí Minh", "08:00", "10:30", "2h 30p", "1.500.000" },
                { "VN102", "TP. Hồ Chí Minh", "Đà Nẵng", "11:00", "13:00", "2h 00p", "1.200.000" },
                { "VN103", "Đà Nẵng", "Nha Trang", "14:00", "15:30", "1h 30p", "900.000" },
                { "VN104", "Nha Trang", "Hà Nội", "16:00", "19:00", "3h 00p", "1.800.000" },
                { "VN105", "Hà Nội", "Phú Quốc", "20:00", "22:30", "2h 30p", "1.600.000" }
        };

        // Tạo JTable với dữ liệu và tiêu đề cột
        JTable table = new JTable(data, columnNames);

        // Thiết lập vị trí và kích thước cho bảng
        table.setBounds(0, 0, 800, 500);

        // Tạo JScrollPane để thêm thanh cuộn khi dữ liệu quá lớn
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 800, 500);

        // Thêm bảng vào frame
        add(scrollPane);

        // Cho phép hiển thị frame
        setVisible(true);

        // Thiết lập hoạt động đóng cửa sổ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        // Khởi tạo đối tượng ẩn danh để chạy ứng dụng
        new FlightInfo();
    }
}
