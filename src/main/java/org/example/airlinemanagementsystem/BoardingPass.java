package org.example.airlinemanagementsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class BoardingPass extends JFrame implements ActionListener {

    // Khai báo các biến toàn cục
    JTextField txtPNR;
    JButton btnEnter;
    JLabel lblNameValue, lblNationalityValue, lblFlightNameValue, lblFlightCodeValue;
    JLabel lblSourceValue, lblDestinationValue, lblDateValue;

    public BoardingPass() {
        // Thiết lập khung hình (Frame)
        setTitle("Thẻ lên máy Bay - Boarding Pass");
        setSize(1000, 500); // Kích thước giống như một chiếc vé máy bay thực tế

        // Đặt frame tại vị trí (300, 150)
        setLocation(300, 150);

        getContentPane().setBackground(Color.white);
        setLayout(null);

        // =====================================================
        // TIÊU ĐỀ (Title Section)
        // =====================================================

        // Tiêu đề chính - AIR INDIA
        JLabel lblAirIndia = new JLabel("AIR INDIA");
        lblAirIndia.setFont(new Font("Tahoma", Font.BOLD, 32));
        lblAirIndia.setBounds(380, 20, 300, 35);
        add(lblAirIndia);

        // Tiêu đề phụ - THẺ LÊN MÁY BAY
        JLabel lblBoardingPass = new JLabel("THẺ LÊN MÁY BAY");
        lblBoardingPass.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblBoardingPass.setBounds(360, 60, 300, 30);
        add(lblBoardingPass);

        // =====================================================
        // PHẦN NHẬP LIỆU PNR (PNR Input Section)
        // =====================================================

        // Nhãn PNR
        JLabel lblPNR = new JLabel("Nhập mã PNR:");
        lblPNR.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPNR.setBounds(60, 120, 150, 25);
        add(lblPNR);

        // Trường nhập liệu PNR
        txtPNR = new JTextField();
        txtPNR.setBounds(220, 120, 150, 25);
        add(txtPNR);

        // Nút Nhập
        btnEnter = new JButton("Nhập");
        btnEnter.setBackground(Color.black);
        btnEnter.setForeground(Color.white);
        btnEnter.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnEnter.setBounds(380, 120, 100, 25);
        btnEnter.addActionListener(this);
        add(btnEnter);

        // =====================================================
        // PHẦN THÔNG TIN HÀNH KHÁCH (Passenger Info Section)
        // =====================================================

        // Tiêu đề phần thông tin hành khách
        JLabel lblPassengerInfo = new JLabel("THÔNG TIN HÀNH KHÁCH");
        lblPassengerInfo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblPassengerInfo.setForeground(Color.darkGray);
        lblPassengerInfo.setBounds(60, 170, 250, 25);
        add(lblPassengerInfo);

        // Họ tên
        JLabel lblName = new JLabel("HỌ TÊN:");
        lblName.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblName.setBounds(60, 210, 150, 25);
        add(lblName);

        lblNameValue = new JLabel();
        lblNameValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNameValue.setBounds(220, 210, 300, 25);
        lblNameValue.setForeground(Color.black);
        add(lblNameValue);

        // Quốc tịch
        JLabel lblNationality = new JLabel("QUỐC TỊCH:");
        lblNationality.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNationality.setBounds(60, 250, 150, 25);
        add(lblNationality);

        lblNationalityValue = new JLabel();
        lblNationalityValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNationalityValue.setBounds(220, 250, 200, 25);
        lblNationalityValue.setForeground(Color.black);
        add(lblNationalityValue);

        // =====================================================
        // PHẦN THÔNG TIN CHUYẾN BAY (Flight Info Section)
        // =====================================================

        // Tiêu đề phần thông tin chuyến bay
        JLabel lblFlightInfo = new JLabel("THÔNG TIN CHUYẾN BAY");
        lblFlightInfo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblFlightInfo.setForeground(Color.darkGray);
        lblFlightInfo.setBounds(550, 170, 250, 25);
        add(lblFlightInfo);

        // Tên chuyến bay
        JLabel lblFlightName = new JLabel("TÊN CHUYẾN BAY:");
        lblFlightName.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblFlightName.setBounds(550, 210, 150, 25);
        add(lblFlightName);

        lblFlightNameValue = new JLabel();
        lblFlightNameValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblFlightNameValue.setBounds(700, 210, 200, 25);
        lblFlightNameValue.setForeground(Color.black);
        add(lblFlightNameValue);

        // Mã chuyến bay
        JLabel lblFlightCode = new JLabel("MÃ CHUYẾN BAY:");
        lblFlightCode.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblFlightCode.setBounds(550, 250, 150, 25);
        add(lblFlightCode);

        lblFlightCodeValue = new JLabel();
        lblFlightCodeValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblFlightCodeValue.setBounds(700, 250, 200, 25);
        lblFlightCodeValue.setForeground(Color.black);
        add(lblFlightCodeValue);

        // Điểm đi
        JLabel lblSource = new JLabel("ĐIỂM ĐI:");
        lblSource.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblSource.setBounds(60, 300, 150, 25);
        add(lblSource);

        lblSourceValue = new JLabel();
        lblSourceValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblSourceValue.setBounds(220, 300, 150, 25);
        lblSourceValue.setForeground(Color.black);
        add(lblSourceValue);

        // Điểm đến
        JLabel lblDestination = new JLabel("ĐIỂM ĐẾN:");
        lblDestination.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblDestination.setBounds(380, 300, 150, 25);
        add(lblDestination);

        lblDestinationValue = new JLabel();
        lblDestinationValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblDestinationValue.setBounds(540, 300, 150, 25);
        lblDestinationValue.setForeground(Color.black);
        add(lblDestinationValue);

        // Ngày khởi hành
        JLabel lblDate = new JLabel("NGÀY KHỞI HÀNH:");
        lblDate.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblDate.setBounds(550, 350, 180, 25);
        add(lblDate);

        lblDateValue = new JLabel();
        lblDateValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblDateValue.setBounds(740, 350, 200, 25);
        lblDateValue.setForeground(Color.black);
        add(lblDateValue);

        // =====================================================
        // HÌNH ẢNH MINH HỌA (Image)
        // =====================================================

        // Hình ảnh logo Air India - Đặt ở phía bên phải (x=550), kích thước 300x300
        ImageIcon imgIcon = new ImageIcon("src/main/resources/icons/airindia.png");
        Image img = imgIcon.getImage();
        Image scaledImg = img.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        JLabel lblImage = new JLabel(scaledIcon);
        lblImage.setBounds(550, 50, 300, 300);
        add(lblImage);

        // Hiển thị cửa sổ
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // Triển khai phương thức actionPerformed để xử lý sự kiện
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnEnter) {
            // Lấy mã PNR từ trường nhập liệu
            String pnr = txtPNR.getText();

            if (pnr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập mã PNR");
                return;
            }

            try {
                // Khởi tạo đối tượng kết nối database
                Conn conn = new Conn();

                // Truy vấn thông tin đặt vé dựa trên mã PNR (sử dụng PreparedStatement để tránh
                // SQL Injection)
                String query = "SELECT * FROM reservation WHERE PNR = ?";
                PreparedStatement pstmt = conn.c.prepareStatement(query);
                pstmt.setString(1, pnr);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    // Hiển thị thông tin hành khách
                    lblNameValue.setText(rs.getString("Name"));
                    lblNationalityValue.setText(rs.getString("Nationality"));

                    // Hiển thị thông tin chuyến bay
                    lblFlightNameValue.setText(rs.getString("Flight_Name"));
                    lblFlightCodeValue.setText(rs.getString("Flight_Code"));
                    lblSourceValue.setText(rs.getString("Source"));
                    lblDestinationValue.setText(rs.getString("Destination"));
                    lblDateValue.setText(rs.getString("D_Date"));
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Không tìm thấy thông tin với mã PNR này.\nVui lòng kiểm tra lại mã PNR hoặc xác nhận vé chưa bị hủy.");
                    // Xóa các nhãn nếu không tìm thấy
                    lblNameValue.setText("");
                    lblNationalityValue.setText("");
                    lblFlightNameValue.setText("");
                    lblFlightCodeValue.setText("");
                    lblSourceValue.setText("");
                    lblDestinationValue.setText("");
                    lblDateValue.setText("");
                }

                // Đóng PreparedStatement sau khi sử dụng
                pstmt.close();
                rs.close();

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi truy vấn dữ liệu: " + e.getMessage());
            }
        }
    }

    // Phương thức main để chạy ứng dụng
    public static void main(String[] args) {
        new BoardingPass();
    }
}
