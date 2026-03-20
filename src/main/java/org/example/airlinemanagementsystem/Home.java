package org.example.airlinemanagementsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

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

        // Thiết lập Tiêu đề Chào mừng
        // Tạo nhãn tiêu đề với nội dung chào mừng
        JLabel heading = new JLabel("Hệ thống Quản lý Hàng không Chào mừng Bạn");

        // Định vị và thiết lập kích thước cho tiêu đề
        heading.setBounds(500, 40, 1000, 40);

        // Thay đổi màu chữ sang màu xanh dương
        heading.setForeground(Color.BLUE);

        // Thiết lập font chữ: Tahoma, kiểu chữ thường (PLAIN), kích thước 36
        heading.setFont(new Font("Inter", Font.PLAIN, 36));

        // Thêm tiêu đề vào nhãn hình ảnh nền (không thêm trực tiếp vào frame)
        // để hiển thị đè lên hình ảnh
        l1.add(heading);

        // ========================================
        // Xây dựng Thanh Menu (Menu Bar)
        // ========================================

        // Khởi tạo thanh menu chính (JMenuBar)
        JMenuBar menuBar = new JMenuBar();

        // Tạo Menu "Details"
        JMenu details = new JMenu("Chi tiết");

        // Tạo các Menu Item cho menu "Details"
        JMenuItem flightDetails = new JMenuItem("Chi tiết chuyến bay");
        JMenuItem customerDetails = new JMenuItem("Thông tin khách hàng");
        JMenuItem reservationDetails = new JMenuItem("Chi tiết đặt chỗ");
        JMenuItem bookFlight = new JMenuItem("Đặt chuyến bay");
        JMenuItem journeyDetails = new JMenuItem("Chi tiết hành trình");
        JMenuItem ticketCancellation = new JMenuItem("Hủy vé");

        // Thêm các Menu Item vào menu "Details"
        details.add(flightDetails);
        details.add(customerDetails);
        details.add(reservationDetails);
        details.add(bookFlight);
        details.add(journeyDetails);
        details.add(ticketCancellation);

        // ========================================
        // THÊM ACTION LISTENER CHO CÁC MENU ITEMS
        // ========================================

        // Thêm ActionListener cho "Chi tiết chuyến bay"
        flightDetails.addActionListener(this);

        // Thêm ActionListener cho "Thông tin khách hàng"
        customerDetails.addActionListener(this);

        // Thêm ActionListener cho các menu items khác (nếu cần)
        reservationDetails.addActionListener(this);
        bookFlight.addActionListener(this);
        journeyDetails.addActionListener(this);
        ticketCancellation.addActionListener(this);

        // Tạo Menu "Ticket"
        JMenu ticket = new JMenu("Vé máy bay");

        // Tạo Menu Item "Boarding Pass" cho menu "Ticket"
        JMenuItem boardingPass = new JMenuItem("Thẻ lên máy bay");

        // Thêm "Boarding Pass" vào menu "Ticket" (KHÔNG phải vào menu "Details")
        ticket.add(boardingPass);

        // Thêm ActionListener cho "Thẻ lên máy bay"
        boardingPass.addActionListener(this);

        // Thêm các menu vào thanh menu chính
        menuBar.add(details);
        menuBar.add(ticket);

        // Thiết lập thanh menu cho khung hình bằng hàm setJMenuBar
        setJMenuBar(menuBar);
    }

    // Hàm main - điểm bắt đầu của chương trình
    public static void main(String[] args) {
        // Tạo đối tượng ẩn danh để khởi tạo cửa sổ chính
        new Home();
    }

    // Ghi đè phương thức actionPerformed để xử lý sự kiện
    @Override
    public void actionPerformed(ActionEvent e) {
        // Lấy lệnh action từ sự kiện
        String actionCommand = e.getActionCommand();

        System.out.println("Menu được chọn: " + actionCommand);

        // Xử lý điều kiện: Kiểm tra mục nào được người dùng nhấp
        // Sử dụng getActionCommand() để so sánh

        if (actionCommand.equals("Chi tiết chuyến bay")) {
            // Kích hoạt Frame: Khi người dùng chọn "Flight Details",
            // một đối tượng của lớp FlightInfo sẽ được khởi tạo
            // để mở cửa sổ thông tin chuyến bay
            new FlightInfo();

        } else if (actionCommand.equals("Thông tin khách hàng")) {
            // Kích hoạt cửa sổ thêm thông tin khách hàng
            new AddCustomer();

        } else if (actionCommand.equals("Chi tiết đặt chỗ")) {
            // Chức năng đang được phát triển
            JOptionPane.showMessageDialog(this,
                    "Chức năng Chi tiết đặt chỗ đang được phát triển!",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);

        } else if (actionCommand.equals("Đặt chuyến bay")) {
            // Mở khung đặt vé (BookFlight)
            new BookFlight();

        } else if (actionCommand.equals("Chi tiết hành trình")) {
            // Mở khung chi tiết hành trình (JourneyDetails)
            new JourneyDetails();

        } else if (actionCommand.equals("Hủy vé")) {
            // Mở cửa sổ hủy vé máy bay
            new Cancel();

        } else if (actionCommand.equals("Thẻ lên máy bay")) {
            // Chức năng đang được phát triển
            JOptionPane.showMessageDialog(this,
                    "Chức năng Thẻ lên máy bay đang được phát triển!",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
