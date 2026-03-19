package org.example.airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;

public class BookFlight extends JFrame implements ActionListener {

    // Khai báo các biến toàn cục (global variables)
    JTextField txtAadhar;
    JButton btnFetch;
    JLabel lblNameValue, lblNationalityValue, lblAddressValue, lblGenderValue;

    // Các biến cho phần chọn chuyến bay
    Choice choiceSource, choiceDestination;
    JButton btnFetchFlights;
    JLabel lblFlightNameValue, lblFlightCodeValue;
    JLabel lblFlightNotFound;

    // Biến cho JDateChooser và nút đặt vé
    JDateChooser dateChooser;
    JButton btnBookFlight;

    public BookFlight() {
        // Thiết lập khung hình (Frame)
        setTitle("Đặt vé máy bay");
        setSize(1100, 700); // Mở rộng kích thước frame

        // Đặt frame ở trung tâm màn hình
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        setLocation((screenWidth - 1100) / 2, (screenHeight - 700) / 2);

        getContentPane().setBackground(Color.white);
        setLayout(null);

        // Thêm tiêu đề chính
        JLabel lblTitle = new JLabel("ĐẶT VÉ MÁY BAY");
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 32));
        lblTitle.setForeground(Color.blue);
        lblTitle.setBounds(420, 20, 500, 35);
        add(lblTitle);

        // =====================================================
        // PHẦN THÔNG TIN KHÁCH HÀNG (Customer Info Section)
        // =====================================================

        // Tiêu đề phần thông tin khách hàng
        JLabel lblCustomerInfo = new JLabel("THÔNG TIN KHÁCH HÀNG");
        lblCustomerInfo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblCustomerInfo.setForeground(Color.darkGray);
        lblCustomerInfo.setBounds(60, 80, 250, 25);
        add(lblCustomerInfo);

        // Số CMND/CCCD (Aadhaar) - Đây là trường nhập liệu để tìm kiếm
        JLabel lblAadhar = new JLabel("Số CMND/CCCD:");
        lblAadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblAadhar.setBounds(60, 130, 150, 25);
        add(lblAadhar);

        txtAadhar = new JTextField();
        txtAadhar.setBounds(220, 130, 150, 25);
        add(txtAadhar);

        // Nút Fetch để truy vấn thông tin khách hàng
        btnFetch = new JButton("Tìm kiếm");
        btnFetch.setBackground(Color.black);
        btnFetch.setForeground(Color.white);
        btnFetch.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnFetch.setBounds(380, 130, 125, 25);
        btnFetch.addActionListener(this);
        add(btnFetch);

        // Họ tên (Name) - Chuyển từ JTextField sang JLabel
        JLabel lblName = new JLabel("Họ tên:");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblName.setBounds(60, 180, 150, 25);
        add(lblName);

        lblNameValue = new JLabel();
        lblNameValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNameValue.setBounds(220, 180, 200, 25);
        lblNameValue.setForeground(Color.black);
        add(lblNameValue);

        // Quốc tịch (Nationality) - Chuyển từ JTextField sang JLabel
        JLabel lblNationality = new JLabel("Quốc tịch:");
        lblNationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNationality.setBounds(60, 220, 150, 25);
        add(lblNationality);

        lblNationalityValue = new JLabel();
        lblNationalityValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNationalityValue.setBounds(220, 220, 200, 25);
        lblNationalityValue.setForeground(Color.black);
        add(lblNationalityValue);

        // Địa chỉ (Address) - Chuyển từ JTextField sang JLabel
        JLabel lblAddress = new JLabel("Địa chỉ:");
        lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblAddress.setBounds(60, 260, 150, 25);
        add(lblAddress);

        lblAddressValue = new JLabel();
        lblAddressValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblAddressValue.setBounds(220, 260, 300, 25);
        lblAddressValue.setForeground(Color.black);
        add(lblAddressValue);

        // Giới tính (Gender) - Chuyển từ Radio Buttons sang JLabel
        JLabel lblGender = new JLabel("Giới tính:");
        lblGender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblGender.setBounds(60, 300, 150, 25);
        add(lblGender);

        lblGenderValue = new JLabel();
        lblGenderValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblGenderValue.setBounds(220, 300, 200, 25);
        lblGenderValue.setForeground(Color.black);
        add(lblGenderValue);

        // =====================================================
        // PHẦN THÔNG TIN CHUYẾN BAY (Flight Info Section)
        // =====================================================

        // Tiêu đề phần thông tin chuyến bay
        JLabel lblFlightInfo = new JLabel("THÔNG TIN CHUYẾN BAY");
        lblFlightInfo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblFlightInfo.setForeground(Color.darkGray);
        lblFlightInfo.setBounds(60, 360, 250, 25);
        add(lblFlightInfo);

        // =====================================================
        // PHẦN CHỌN ĐIỂM ĐI VÀ ĐIỂM ĐẾN (Source & Destination)
        // =====================================================

        // Nhãn Điểm đi (Source)
        JLabel lblSource = new JLabel("Điểm đi:");
        lblSource.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblSource.setBounds(60, 410, 150, 25);
        add(lblSource);

        // Choice dropdown cho Điểm đi
        choiceSource = new Choice();
        choiceSource.setBounds(220, 410, 150, 25);
        add(choiceSource);

        // Nhãn Điểm đến (Destination)
        JLabel lblDestination = new JLabel("Điểm đến:");
        lblDestination.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblDestination.setBounds(60, 450, 150, 25);
        add(lblDestination);

        // Choice dropdown cho Điểm đến
        choiceDestination = new Choice();
        choiceDestination.setBounds(220, 450, 150, 25);
        add(choiceDestination);

        // Nút Fetch Flights để tìm kiếm chuyến bay
        btnFetchFlights = new JButton("Tìm chuyến bay");
        btnFetchFlights.setBackground(Color.black);
        btnFetchFlights.setForeground(Color.white);
        btnFetchFlights.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnFetchFlights.setBounds(380, 430, 140, 25);
        btnFetchFlights.addActionListener(this);
        add(btnFetchFlights);

        // =====================================================
        // PHẦN HIỂN THỊ KẾT QUẢ CHUYẾN BAY
        // =====================================================

        // Nhãn Tên chuyến bay
        JLabel lblFlightName = new JLabel("Tên chuyến bay:");
        lblFlightName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblFlightName.setBounds(60, 490, 150, 25);
        add(lblFlightName);

        lblFlightNameValue = new JLabel();
        lblFlightNameValue.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblFlightNameValue.setBounds(220, 490, 450, 25);
        lblFlightNameValue.setForeground(Color.black);
        add(lblFlightNameValue);

        // Nhãn Mã chuyến bay
        JLabel lblFlightCode = new JLabel("Mã chuyến bay:");
        lblFlightCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblFlightCode.setBounds(60, 520, 150, 25);
        add(lblFlightCode);

        lblFlightCodeValue = new JLabel();
        lblFlightCodeValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblFlightCodeValue.setBounds(220, 520, 200, 25);
        lblFlightCodeValue.setForeground(Color.black);
        add(lblFlightCodeValue);

        // Nhãn thông báo không tìm thấy chuyến bay
        lblFlightNotFound = new JLabel();
        lblFlightNotFound.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblFlightNotFound.setForeground(Color.red);
        lblFlightNotFound.setBounds(220, 550, 300, 25);
        add(lblFlightNotFound);

        // =====================================================
        // PHẦN CHỌN NGÀY KHỞI HÀNH (Date of Travel - JCalendar)
        // =====================================================

        // Nhãn Date of Travel
        JLabel lblDateOfTravel = new JLabel("Ngày khởi hành:");
        lblDateOfTravel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblDateOfTravel.setBounds(60, 580, 150, 25);
        add(lblDateOfTravel);

        // JDateChooser để chọn ngày
        dateChooser = new JDateChooser();
        dateChooser.setBounds(220, 580, 200, 25);
        dateChooser.setDateFormatString("yyyy-MM-dd");
        add(dateChooser);

        // =====================================================
        // NÚT ĐẶT VÉ (Book Flight Button)
        // =====================================================

        // Nút Book Flight
        btnBookFlight = new JButton("Đặt vé");
        btnBookFlight.setBackground(Color.black);
        btnBookFlight.setForeground(Color.white);
        btnBookFlight.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnBookFlight.setBounds(450, 575, 150, 35);
        btnBookFlight.addActionListener(this);
        add(btnBookFlight);

        // =====================================================
        // ĐỔ DỮ LIỆU VÀO DROPDOWN TỪ CƠ SỞ DỮ LIỆU
        // =====================================================
        loadFlightData();

        // Hình ảnh minh họa (ImageIcon)
        ImageIcon imgIcon = new ImageIcon("src/main/resources/icons/details.jpg");
        // Thu nhỏ hình ảnh xuống kích thước phù hợp
        Image img = imgIcon.getImage();
        Image scaledImg = img.getScaledInstance(450, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        JLabel lblImage = new JLabel(scaledIcon);
        lblImage.setBounds(600, 80, 450, 300);
        add(lblImage);

        // Hiển thị cửa sổ
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // Triển khai phương thức actionPerformed để xử lý sự kiện
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnFetch) {
            // Lấy số CMND/CCCD từ trường nhập liệu
            String aadhar = txtAadhar.getText();

            if (aadhar.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập số CMND/CCCD");
                return;
            }

            try {
                // Khởi tạo đối tượng kết nối database
                Conn conn = new Conn();

                // Truy vấn thông tin khách hàng dựa trên số CMND/CCCD
                String query = "SELECT * FROM passenger WHERE aadhar = '" + aadhar + "'";
                ResultSet rs = conn.s.executeQuery(query);

                if (rs.next()) {
                    // Hiển thị thông tin khách hàng lên các JLabel
                    lblNameValue.setText(rs.getString("name"));
                    lblNationalityValue.setText(rs.getString("nationality"));
                    lblAddressValue.setText(rs.getString("address"));
                    lblGenderValue.setText(rs.getString("gender"));
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng với số CMND/CCCD này");
                    // Xóa các nhãn nếu không tìm thấy
                    lblNameValue.setText("");
                    lblNationalityValue.setText("");
                    lblAddressValue.setText("");
                    lblGenderValue.setText("");
                }

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi truy vấn dữ liệu: " + e.getMessage());
            }
        } else if (ae.getSource() == btnFetchFlights) {
            // Xử lý tìm kiếm chuyến bay
            String source = choiceSource.getSelectedItem();
            String destination = choiceDestination.getSelectedItem();

            // Kiểm tra nếu chưa chọn điểm đi và điểm đến
            if (source == null || source.isEmpty() || destination == null || destination.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn điểm đi và điểm đến");
                return;
            }

            // Kiểm tra nếu điểm đi và điểm đến giống nhau
            if (source.equals(destination)) {
                JOptionPane.showMessageDialog(null, "Điểm đi và điểm đến không được trùng nhau");
                return;
            }

            try {
                // Khởi tạo đối tượng kết nối database
                Conn conn = new Conn();

                // Truy vấn chuyến bay theo điểm đi và điểm đến
                String query = "SELECT * FROM flight WHERE source = '" + source + "' AND destination = '" + destination
                        + "'";
                ResultSet rs = conn.s.executeQuery(query);

                if (rs.next()) {
                    // Hiển thị thông tin chuyến bay
                    lblFlightCodeValue.setText(rs.getString("f_code"));

                    // Lấy các thông tin chuyến bay
                    String departure = rs.getString("departure");
                    String arrival = rs.getString("arrival");
                    String duration = rs.getString("duration");
                    String price = rs.getString("price");

                    // Hiển thị thông tin chi tiết chuyến bay (Giờ khởi hành - Giờ đến | Thời gian
                    // bay | Giá)
                    lblFlightNameValue.setText(departure + " → " + arrival + " | " + duration + " | " + price + " VNĐ");

                    // Xóa thông báo không tìm thấy
                    lblFlightNotFound.setText("");
                } else {
                    // Xóa thông tin chuyến bay cũ
                    lblFlightCodeValue.setText("");
                    lblFlightNameValue.setText("");
                    // Hiển thị thông báo không tìm thấy
                    lblFlightNotFound.setText("Không tìm thấy chuyến bay");
                }

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi truy vấn chuyến bay: " + e.getMessage());
            }
        } else if (ae.getSource() == btnBookFlight) {
            // Xử lý đặt vé máy bay

            // Kiểm tra thông tin khách hàng
            String aadhar = txtAadhar.getText();
            if (aadhar.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập số CMND/CCCD và tìm kiếm thông tin khách hàng");
                return;
            }

            // Kiểm tra thông tin chuyến bay
            if (lblFlightCodeValue.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn chuyến bay");
                return;
            }

            // Kiểm tra ngày khởi hành
            if (dateChooser.getDate() == null) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày khởi hành");
                return;
            }

            // Kiểm tra ngày khởi hành phải lớn hơn ngày hiện tại
            java.util.Date selectedDate = dateChooser.getDate();
            java.util.Date today = new java.util.Date();
            today.setHours(0);
            today.setMinutes(0);
            today.setSeconds(0);

            if (selectedDate.before(today) || selectedDate.equals(today)) {
                JOptionPane.showMessageDialog(null, "Ngày khởi hành phải lớn hơn ngày hiện tại");
                return;
            }

            // Lấy thông tin
            String flightCode = lblFlightCodeValue.getText();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            String travelDate = sdf.format(selectedDate);

            // Hiển thị thông báo đặt vé thành công (logic lưu database sẽ được thêm sau)
            JOptionPane.showMessageDialog(null,
                    "Đặt vé thành công!\n" +
                            "Mã chuyến bay: " + flightCode + "\n" +
                            "Ngày khởi hành: " + travelDate);
        }
    }

    /**
     * Phương thức đổ dữ liệu từ bảng flight vào các dropdown
     * Thực hiện truy vấn SQL để lấy danh sách các điểm đi và điểm đến
     */
    private void loadFlightData() {
        try {
            // Khởi tạo kết nối database
            Conn conn = new Conn();

            // Truy vấn lấy tất cả dữ liệu từ bảng flight
            String query = "SELECT * FROM flight";
            ResultSet rs = conn.s.executeQuery(query);

            // Sử dụng HashSet để lưu trữ các giá trị duy nhất (tránh trùng lặp)
            java.util.Set<String> sources = new java.util.HashSet<>();
            java.util.Set<String> destinations = new java.util.HashSet<>();

            // Duyệt qua từng dòng dữ liệu
            while (rs.next()) {
                String source = rs.getString("source");
                String destination = rs.getString("destination");

                // Thêm vào HashSet để tránh trùng lặp
                if (source != null && !source.isEmpty()) {
                    sources.add(source);
                }
                if (destination != null && !destination.isEmpty()) {
                    destinations.add(destination);
                }
            }

            // Thêm dữ liệu vào dropdown Source
            for (String source : sources) {
                choiceSource.add(source);
            }

            // Thêm dữ liệu vào dropdown Destination
            for (String destination : destinations) {
                choiceDestination.add(destination);
            }

            System.out.println("Đổ dữ liệu chuyến bay thành công!");
            System.out.println("Số điểm đi: " + sources.size());
            System.out.println("Số điểm đến: " + destinations.size());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi khi đổ dữ liệu chuyến bay: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new BookFlight();
    }
}
