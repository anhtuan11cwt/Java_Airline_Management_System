package org.example.airlinemanagementsystem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {

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
        lblUsername.setBounds(20, 20, 100, 20);
        add(lblUsername);

        // Tạo ô nhập liệu Username
        txtUsername = new JTextField();
        txtUsername.setBounds(130, 20, 220, 25);
        add(txtUsername);

        // Tạo nhãn Password
        lblPassword = new JLabel("Mật khẩu");
        lblPassword.setBounds(20, 60, 100, 20);
        add(lblPassword);

        // Tạo ô nhập liệu Password (ẩn ký tự nhập)
        txtPassword = new JPasswordField();
        txtPassword.setBounds(100, 60, 250, 25);
        add(txtPassword);

        // Tạo nút Reset
        btnReset = new JButton("Xóa");
        btnReset.setBounds(20, 110, 100, 30);
        btnReset.addActionListener(this);
        add(btnReset);

        // Tạo nút Submit
        btnSubmit = new JButton("Đăng nhập");
        btnSubmit.setBounds(130, 110, 100, 30);
        btnSubmit.addActionListener(this);
        add(btnSubmit);

        // Tạo nút Close
        btnClose = new JButton("Đóng");
        btnClose.setBounds(240, 110, 100, 30);
        btnClose.addActionListener(this);
        add(btnClose);
    }

    // Hàm main - điểm bắt đầu của chương trình
    public static void main(String[] args) {
        // Tạo đối tượng ẩn danh để khởi tạo cửa sổ đăng nhập
        new Login();
    }

    // Ghi đè phương thức actionPerformed để xử lý sự kiện
    @Override
    public void actionPerformed(ActionEvent e) {
        // Kiểm tra xem nút nào đã được nhấn
        if (e.getSource() == btnClose) {
            // Nút Close: Đóng cửa sổ đăng nhập
            setVisible(false);
        } else if (e.getSource() == btnReset) {
            // Nút Reset: Xóa trắng dữ liệu trong các ô nhập liệu
            txtUsername.setText("");
            txtPassword.setText("");
        } else if (e.getSource() == btnSubmit) {
            // Nút Submit: Thiết lập kết nối cơ sở dữ liệu (JDBC)
            // TODO: Triển khai kết nối CSDL trong các bước tiếp theo
            System.out.println("Nút Submit đã được nhấn - Chuẩn bị kết nối JDBC");
        }
    }
}
