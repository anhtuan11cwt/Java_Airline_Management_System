package org.example.airlinemanagementsystem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class Login extends JFrame implements ActionListener {

    // Các thành phần giao diện
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnReset;
    private JButton btnSubmit;
    private JButton btnClose;
    private JToggleButton btnShowPassword;

    // Hàm khởi tạo (Constructor)
    public Login() {
        // Thiết lập tiêu đề cửa sổ
        setTitle("Đăng nhập hệ thống");

        // Thiết lập kích thước cửa sổ: 400 chiều dài, 300 chiều cao
        setSize(400, 300);

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
        txtPassword.setBounds(100, 60, 180, 25);
        add(txtPassword);

        // Tạo nút hiển thị/ẩn mật khẩu
        btnShowPassword = new JToggleButton("👁");
        btnShowPassword.setBounds(285, 60, 35, 25);
        btnShowPassword.addActionListener(this);
        add(btnShowPassword);

        // Tạo nút Reset
        btnReset = new JButton("Xóa");
        btnReset.setBounds(20, 120, 100, 30);
        btnReset.addActionListener(this);
        add(btnReset);

        // Tạo nút Submit
        btnSubmit = new JButton("Đăng nhập");
        btnSubmit.setBounds(130, 120, 100, 30);
        btnSubmit.addActionListener(this);
        add(btnSubmit);

        // Tạo nút Close
        btnClose = new JButton("Đóng");
        btnClose.setBounds(240, 120, 100, 30);
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
            // Nút Submit: Xử lý đăng nhập
            try {
                // Bước 1: Lấy dữ liệu từ giao diện
                String user = txtUsername.getText();
                String pass = txtPassword.getText();

                // Kiểm tra nếu người dùng chưa nhập thông tin
                if (user.isEmpty() || pass.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập tên đăng nhập và mật khẩu!", "Thông báo",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Bước 2: Tạo kết nối CSDL
                Conn c = new Conn();

                // Bước 3: Xây dựng truy vấn SQL
                String query = "select * from login where username = '" + user + "' and password = '" + pass + "'";

                // Bước 4: Thực thi truy vấn và xử lý kết quả
                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()) {
                    // Tìm thấy tài khoản - đăng nhập hợp lệ
                    System.out.println("Valid");
                    JOptionPane.showMessageDialog(this, "Đăng nhập thành công!", "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);

                    // Đóng cửa sổ đăng nhập
                    setVisible(false);

                    // TODO: Chuyển sang khung hình tiếp theo (Main Frame)
                    System.out.println("Chuyển sang giao diện chính...");
                } else {
                    // Không tìm thấy tài khoản - đăng nhập không hợp lệ
                    System.out.println("Invalid Username or Password");
                    JOptionPane.showMessageDialog(this, "Tên đăng nhập hoặc mật khẩu không hợp lệ!", "Lỗi đăng nhập",
                            JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi kết nối cơ sở dữ liệu: " + ex.getMessage(), "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnShowPassword) {
            // Nút hiển thị/ẩn mật khẩu
            if (btnShowPassword.isSelected()) {
                // Hiển thị mật khẩu dạng văn bản
                txtPassword.setEchoChar((char) 0);
            } else {
                // Ẩn mật khẩu bằng dấu chấm
                txtPassword.setEchoChar('•');
            }
        }
    }
}
