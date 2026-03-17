package org.example.airlinemanagementsystem;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame {

    // Các thành phần giao diện
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnReset;
    private JButton btnSubmit;
    private JButton btnClose;

    // Hàm khởi tạo (Constructor)
    public Login() {
        // Thiết lập tiêu đề cửa sổ
        setTitle("Đăng nhập hệ thống");

        // Thiết lập kích thước cửa sổ: 400 chiều dài, 250 chiều cao
        setSize(400, 250);

        // Thiết lập vị trí xuất hiện: cách lề trái 600px, cách lề trên 250px
        setLocation(600, 250);

        // Thiết lập hành động khi đóng cửa sổ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Vô hiệu hóa bố cục mặc định để tự do đặt thành phần theo tọa độ
        setLayout(null);

        // Đặt màu nền trắng
        getContentPane().setBackground(Color.WHITE);

        // Khởi tạo các thành phần giao diện
        initComponents();

        // Hiển thị cửa sổ
        setVisible(true);
    }

    // Hàm khởi tạo các thành phần giao diện
    private void initComponents() {
        // Tạo nhãn Username
        lblUsername = new JLabel("Tên đăng nhập");
        lblUsername.setBounds(20, 20, 120, 25);
        add(lblUsername);

        // Tạo ô nhập liệu Username
        txtUsername = new JTextField();
        txtUsername.setBounds(100, 20, 250, 25);
        add(txtUsername);

        // Tạo nhãn Password
        lblPassword = new JLabel("Mật khẩu");
        lblPassword.setBounds(20, 60, 80, 25);
        add(lblPassword);

        // Tạo ô nhập liệu Password (ẩn ký tự nhập)
        txtPassword = new JPasswordField();
        txtPassword.setBounds(100, 60, 250, 25);
        add(txtPassword);

        // Tạo nút Reset
        btnReset = new JButton("Đặt lại");
        btnReset.setBounds(20, 110, 80, 30);
        add(btnReset);

        // Tạo nút Submit
        btnSubmit = new JButton("Đăng nhập");
        btnSubmit.setBounds(120, 110, 80, 30);
        add(btnSubmit);

        // Tạo nút Close
        btnClose = new JButton("Đóng");
        btnClose.setBounds(220, 110, 80, 30);
        add(btnClose);
    }

    // Hàm main - điểm bắt đầu của chương trình
    public static void main(String[] args) {
        // Tạo đối tượng ẩn danh để khởi tạo cửa sổ đăng nhập
        new Login();
    }
}
