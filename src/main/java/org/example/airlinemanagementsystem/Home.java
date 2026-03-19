package org.example.airlinemanagementsystem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Home extends JFrame implements ActionListener {

    // Hàm khởi tạo (Constructor)
    public Home() {
        // Thiết lập tiêu đề cửa sổ
        setTitle("Hệ thống quản lý hàng không");

        // Thiết lập chế độ toàn màn hình
        setExtendedState(JFrame.MAXIMIZED_BOTH);

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
        // Trang chủ sẽ được thiết kế với:
        // - Hình ảnh nền
        // - Thanh menu
        // - Các chức năng quản lý hàng không

        // TODO: Thêm hình ảnh nền cho trang chủ
        // TODO: Thêm thanh menu (JMenuBar) với các mục: Quản lý chuyến bay, Quản lý
        // khách hàng, Báo cáo, Thoát
    }

    // Hàm main - điểm bắt đầu của chương trình
    public static void main(String[] args) {
        // Tạo đối tượng ẩn danh để khởi tạo cửa sổ chính
        new Home();
    }

    // Ghi đè phương thức actionPerformed để xử lý sự kiện
    @Override
    public void actionPerformed(ActionEvent e) {
        // Xử lý sự kiện cho trang chủ sẽ được thêm sau
        // Các sự kiện menu và nút bấm sẽ được thiết lập ở đây
    }
}
