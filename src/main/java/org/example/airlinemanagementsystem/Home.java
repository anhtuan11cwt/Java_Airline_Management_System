package org.example.airlinemanagementsystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Home extends JFrame implements ActionListener {

    // Hàm khởi tạo (Constructor)
    public Home() {
        // Thiết lập tiêu đề cửa sổ
        setTitle("Hệ thống quản lý hàng không");

        // Thiết lập chế độ toàn màn hình
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Thiết lập vị trí mặc định bắt đầu từ tọa độ (0,0)
        setLocation(0, 0);

        // Thiết lập hành động khi đóng cửa sổ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Vô hiệu hóa bố cục mặc định để tự do đặt thành phần theo tọa độ
        setLayout(null);

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

        // Tải hình ảnh nền từ thư mục icons
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/front.jpg"));

        // Tạo JLabel để chứa hình ảnh nền
        JLabel l1 = new JLabel(i1);

        // Thiết lập vị trí và kích thước cho hình ảnh nền
        l1.setBounds(0, 0, 1600, 800);

        // Thêm hình ảnh nền vào khung hình
        add(l1);

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
