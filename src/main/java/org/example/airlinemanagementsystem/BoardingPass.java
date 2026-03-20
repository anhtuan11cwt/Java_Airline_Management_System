package org.example.airlinemanagementsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    JButton btnFetch;
    JLabel lblNameValue, lblFlightCodeValue, lblSourceValue, lblDestinationValue;
    JLabel lblDateValue, lblTicketIdValue;

    public BoardingPass() {
        // Thiết lập khung hình (Frame)
        setTitle("Thẻ lên máy Bay - Boarding Pass");
        setSize(1000, 450); // Kích thước giống như một chiếc vé máy bay thực tế

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

        // Tiêu đề phụ - BOARDING PASS
        JLabel lblBoardingPass = new JLabel("BOARDING PASS");
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

        // Nút Tìm kiếm
        btnFetch = new JButton("Tìm kiếm");
        btnFetch.setBackground(Color.black);
        btnFetch.setForeground(Color.white);
        btnFetch.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnFetch.setBounds(380, 120, 125, 25);
        btnFetch.addActionListener(this);
        add(btnFetch);

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
        JLabel lblName = new JLabel("Họ tên:");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblName.setBounds(60, 210, 150, 25);
        add(lblName);

        lblNameValue = new JLabel();
        lblNameValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNameValue.setBounds(220, 210, 300, 25);
        lblNameValue.setForeground(Color.black);
        add(lblNameValue);

        // Mã vé (Ticket ID)
        JLabel lblTicketId = new JLabel("Mã vé:");
        lblTicketId.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblTicketId.setBounds(60, 250, 150, 25);
        add(lblTicketId);

        lblTicketIdValue = new JLabel();
        lblTicketIdValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblTicketIdValue.setBounds(220, 250, 200, 25);
        lblTicketIdValue.setForeground(Color.black);
        add(lblTicketIdValue);

        // =====================================================
        // PHẦN THÔNG TIN CHUYẾN BAY (Flight Info Section)
        // =====================================================

        // Tiêu đề phần thông tin chuyến bay
        JLabel lblFlightInfo = new JLabel("THÔNG TIN CHUYẾN BAY");
        lblFlightInfo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblFlightInfo.setForeground(Color.darkGray);
        lblFlightInfo.setBounds(550, 170, 250, 25);
        add(lblFlightInfo);

        // Mã chuyến bay
        JLabel lblFlightCode = new JLabel("Mã chuyến bay:");
        lblFlightCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblFlightCode.setBounds(550, 210, 150, 25);
        add(lblFlightCode);

        lblFlightCodeValue = new JLabel();
        lblFlightCodeValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblFlightCodeValue.setBounds(720, 210, 200, 25);
        lblFlightCodeValue.setForeground(Color.black);
        add(lblFlightCodeValue);

        // Điểm đi
        JLabel lblSource = new JLabel("Điểm đi:");
        lblSource.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblSource.setBounds(550, 250, 150, 25);
        add(lblSource);

        lblSourceValue = new JLabel();
        lblSourceValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblSourceValue.setBounds(720, 250, 200, 25);
        lblSourceValue.setForeground(Color.black);
        add(lblSourceValue);

        // Điểm đến
        JLabel lblDestination = new JLabel("Điểm đến:");
        lblDestination.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblDestination.setBounds(550, 290, 150, 25);
        add(lblDestination);

        lblDestinationValue = new JLabel();
        lblDestinationValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblDestinationValue.setBounds(720, 290, 200, 25);
        lblDestinationValue.setForeground(Color.black);
        add(lblDestinationValue);

        // Ngày khởi hành
        JLabel lblDate = new JLabel("Ngày khởi hành:");
        lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblDate.setBounds(550, 330, 150, 25);
        add(lblDate);

        lblDateValue = new JLabel();
        lblDateValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblDateValue.setBounds(720, 330, 200, 25);
        lblDateValue.setForeground(Color.black);
        add(lblDateValue);

        // =====================================================
        // HÌNH ẢNH MINH HỌA (Image)
        // =====================================================

        // Hình ảnh minh họa
        ImageIcon imgIcon = new ImageIcon("src/main/resources/icons/airindia.png");
        Image img = imgIcon.getImage();
        Image scaledImg = img.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        JLabel lblImage = new JLabel(scaledIcon);
        lblImage.setBounds(60, 300, 200, 100);
        add(lblImage);

        // Hiển thị cửa sổ
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // Triển khai phương thức actionPerformed để xử lý sự kiện
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnFetch) {
            // Lấy mã PNR từ trường nhập liệu
            String pnr = txtPNR.getText();

            if (pnr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập mã PNR");
                return;
            }

            try {
                // Khởi tạo đối tượng kết nối database
                Conn conn = new Conn();

                // Truy vấn thông tin đặt vé dựa trên mã PNR
                String query = "SELECT * FROM reservation WHERE PNR = '" + pnr + "'";
                ResultSet rs = conn.s.executeQuery(query);

                if (rs.next()) {
                    // Hiển thị thông tin hành khách
                    lblNameValue.setText(rs.getString("Name"));
                    lblTicketIdValue.setText(rs.getString("Ticket"));

                    // Hiển thị thông tin chuyến bay
                    lblFlightCodeValue.setText(rs.getString("Flight_Code"));
                    lblSourceValue.setText(rs.getString("Source"));
                    lblDestinationValue.setText(rs.getString("Destination"));
                    lblDateValue.setText(rs.getString("D_Date"));
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin với mã PNR này");
                    // Xóa các nhãn nếu không tìm thấy
                    lblNameValue.setText("");
                    lblTicketIdValue.setText("");
                    lblFlightCodeValue.setText("");
                    lblSourceValue.setText("");
                    lblDestinationValue.setText("");
                    lblDateValue.setText("");
                }

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
