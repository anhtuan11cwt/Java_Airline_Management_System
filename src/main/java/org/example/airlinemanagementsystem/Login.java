package org.example.airlinemanagementsystem;

import javax.swing.*;

public class Login extends JFrame {

    // Hàm khởi tạo (Constructor)
    public Login() {
        // Thiết lập tiêu đề cửa sổ
        setTitle("Đăng nhập hệ thống");

        // Thiết lập kích thước cửa sổ
        setSize(400, 300);

        // Thiết lập vị trí hiển thị giữa màn hình
        setLocationRelativeTo(null);

        // Thiết lập hành động khi đóng cửa sổ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Hiển thị cửa sổ
        setVisible(true);
    }

    // Hàm main - điểm bắt đầu của chương trình
    public static void main(String[] args) {
        // Tạo đối tượng ẩn danh để khởi tạo cửa sổ đăng nhập
        new Login();
    }
}
