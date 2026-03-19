package org.example.airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class BookFlight extends JFrame implements ActionListener {

    // Khai báo các biến toàn cục (global variables)
    JTextField txtAadhar;
    JButton btnFetch;
    JLabel lblNameValue, lblNationalityValue, lblAddressValue, lblGenderValue;

    public BookFlight() {
        // Thiết lập khung hình (Frame)
        setTitle("Đặt vé máy bay");
        setSize(900, 600);
        setLocation(300, 500);
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
        btnFetch = new JButton("Fetch");
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

        // Hình ảnh minh họa (ImageIcon)
        ImageIcon imgIcon = new ImageIcon("src/main/resources/icons/emp.png");
        JLabel lblImage = new JLabel(imgIcon);
        lblImage.setBounds(550, 80, 280, 400);
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
        }
    }

    public static void main(String[] args) {
        new BookFlight();
    }
}
