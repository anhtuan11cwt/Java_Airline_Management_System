package org.example.airlinemanagementsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Cancel extends JFrame implements ActionListener {

    // Khai báo các biến toàn cục (global variables)
    JTextField TFPNR;
    JButton btnFetch;
    JLabel lblNameValue, lblFlightNameValue, lblFlightCodeValue, lblDateValue, lblCancellationNumber;
    JButton btnCancel;
    Random random;
    int cancellationNumber;

    public Cancel() {
        // Thiết lập khung hình (Frame)
        setTitle("Hủy vé máy bay");
        setSize(800, 400); // Kích thước frame theo yêu cầu

        // Đặt frame tại vị trí (300, 150)
        setLocation(300, 150);

        getContentPane().setBackground(Color.white);
        setLayout(null);

        // Thêm tiêu đề chính - "CANCELLATION" tại vị trí x=180, y=20 với màu đen
        JLabel lblTitle = new JLabel("HỦY VÉ MÁY BAY");
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 32));
        lblTitle.setForeground(Color.black); // Màu đen theo yêu cầu
        lblTitle.setBounds(180, 20, 500, 35);
        add(lblTitle);

        // =====================================================
        // PHẦN THÔNG TIN ĐẶT VÉ (Reservation Info Section)
        // =====================================================

        // Tiêu đề phần thông tin đặt vé
        JLabel lblReservationInfo = new JLabel("THÔNG TIN ĐẶT VÉ");
        lblReservationInfo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblReservationInfo.setForeground(Color.darkGray);
        lblReservationInfo.setBounds(60, 80, 250, 25);
        add(lblReservationInfo);

        // Số PNR (Ticket ID) - Đây là trường nhập liệu để tìm kiếm vé cần hủy
        JLabel lblPNR = new JLabel("Mã PNR:");
        lblPNR.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPNR.setBounds(60, 130, 150, 25);
        add(lblPNR);

        TFPNR = new JTextField();
        TFPNR.setBounds(220, 130, 150, 25);
        add(TFPNR);

        // Nút Fetch để truy vấn thông tin đặt vé
        btnFetch = new JButton("Tìm kiếm");
        btnFetch.setBackground(Color.black);
        btnFetch.setForeground(Color.white);
        btnFetch.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnFetch.setBounds(380, 130, 125, 25);
        btnFetch.addActionListener(this);
        add(btnFetch);

        // Họ tên (Name)
        JLabel lblName = new JLabel("Họ tên:");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblName.setBounds(60, 180, 150, 25);
        add(lblName);

        lblNameValue = new JLabel();
        lblNameValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNameValue.setBounds(220, 180, 200, 25);
        lblNameValue.setForeground(Color.black);
        add(lblNameValue);

        // Tên chuyến bay (Flight Name)
        JLabel lblFlightName = new JLabel("Chuyến bay:");
        lblFlightName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblFlightName.setBounds(60, 220, 150, 25);
        add(lblFlightName);

        lblFlightNameValue = new JLabel();
        lblFlightNameValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblFlightNameValue.setBounds(220, 220, 300, 25);
        lblFlightNameValue.setForeground(Color.black);
        add(lblFlightNameValue);

        // Mã chuyến bay (Flight Code)
        JLabel lblFlightCode = new JLabel("Mã chuyến bay:");
        lblFlightCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblFlightCode.setBounds(60, 250, 150, 25);
        add(lblFlightCode);

        lblFlightCodeValue = new JLabel();
        lblFlightCodeValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblFlightCodeValue.setBounds(220, 250, 200, 25);
        lblFlightCodeValue.setForeground(Color.black);
        add(lblFlightCodeValue);

        // Ngày khởi hành (Date of Travel)
        JLabel lblDate = new JLabel("Ngày khởi hành:");
        lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblDate.setBounds(60, 280, 150, 25);
        add(lblDate);

        lblDateValue = new JLabel();
        lblDateValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblDateValue.setBounds(220, 280, 200, 25);
        lblDateValue.setForeground(Color.black);
        add(lblDateValue);

        // =====================================================
        // SỐ HỦY VÉ (Cancellation Number) - Tạo số ngẫu nhiên 6 chữ số
        // =====================================================

        // Khởi tạo Random và tạo số hủy vé 6 chữ số
        random = new Random();
        cancellationNumber = random.nextInt(900000) + 100000; // Tạo số từ 100000 đến 999999

        JLabel lblCancellation = new JLabel("Số hủy vé:");
        lblCancellation.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblCancellation.setBounds(60, 320, 150, 25);
        add(lblCancellation);

        lblCancellationNumber = new JLabel("" + cancellationNumber);
        lblCancellationNumber.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblCancellationNumber.setBounds(220, 320, 200, 25);
        lblCancellationNumber.setForeground(Color.red);
        add(lblCancellationNumber);

        // =====================================================
        // NÚT HỦY VÉ (Cancel Button)
        // =====================================================

        btnCancel = new JButton("Hủy vé");
        btnCancel.setBackground(Color.red);
        btnCancel.setForeground(Color.white);
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnCancel.setBounds(450, 130, 150, 35);
        btnCancel.addActionListener(this);
        add(btnCancel);

        // =====================================================
        // Hình ảnh minh họa (ImageIcon) - cancel.jpg
        // =====================================================

        ImageIcon imgIcon = new ImageIcon("src/main/resources/icons/cancel.jpg");
        // Thu nhỏ hình ảnh xuống kích thước 250x250 theo yêu cầu
        Image img = imgIcon.getImage();
        Image scaledImg = img.getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        JLabel lblImage = new JLabel(scaledIcon);
        // Đặt hình ảnh tại vị trí (470, 120) theo yêu cầu
        lblImage.setBounds(470, 120, 250, 250);
        add(lblImage);

        // Hiển thị cửa sổ
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // Triển khai phương thức actionPerformed để xử lý sự kiện
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnFetch) {
            // Lấy mã PNR từ trường nhập liệu TFPNR
            String pnr = TFPNR.getText();

            if (pnr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập mã PNR/Ticket");
                return;
            }

            try {
                // Khởi tạo đối tượng kết nối database
                Conn conn = new Conn();

                // Truy vấn thông tin đặt vé dựa trên mã PNR
                String query = "SELECT * FROM reservation WHERE PNR = '" + pnr + "' OR Ticket = '" + pnr + "'";
                ResultSet rs = conn.s.executeQuery(query);

                if (rs.next()) {
                    // Hiển thị thông tin đặt vé lên các JLabel
                    lblNameValue.setText(rs.getString("Name"));
                    lblFlightNameValue.setText(rs.getString("Flight_Name"));
                    lblFlightCodeValue.setText(rs.getString("Flight_Code"));
                    lblDateValue.setText(rs.getString("D_Date"));
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin đặt vé với mã này");
                    // Xóa các nhãn nếu không tìm thấy
                    lblNameValue.setText("");
                    lblFlightNameValue.setText("");
                    lblFlightCodeValue.setText("");
                    lblDateValue.setText("");
                }

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi truy vấn dữ liệu: " + e.getMessage());
            }
        } else if (ae.getSource() == btnCancel) {
            // Xử lý hủy vé máy bay

            // Kiểm tra mã PNR
            String pnr = TFPNR.getText();
            if (pnr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập mã PNR và tìm kiếm thông tin");
                return;
            }

            // Kiểm tra thông tin đặt vé đã được tìm kiếm chưa
            if (lblNameValue.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng tìm kiếm thông tin đặt vé trước");
                return;
            }

            // Hiển thị hộp thoại xác nhận hủy vé
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Bạn có chắc chắn muốn hủy vé này không?",
                    "Xác nhận hủy vé",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    Conn conn = new Conn();

                    // Xóa thông tin đặt vé khỏi cơ sở dữ liệu
                    String deleteQuery = "DELETE FROM reservation WHERE PNR = '" + pnr + "' OR Ticket = '" + pnr + "'";
                    conn.s.executeUpdate(deleteQuery);

                    // Tạo số hủy vé mới cho giao dịch tiếp theo
                    cancellationNumber = random.nextInt(900000) + 100000;
                    lblCancellationNumber.setText("" + cancellationNumber);

                    // Hiển thị thông báo thành công với số hủy vé
                    JOptionPane.showMessageDialog(null,
                            "Hủy vé thành công!\n\n" +
                                    "Mã PNR: " + pnr + "\n" +
                                    "Số hủy vé: " + cancellationNumber + "\n" +
                                    "Họ tên: " + lblNameValue.getText() + "\n\n" +
                                    "Vui lòng lưu lại số hủy vé để đối chiếu.",
                            "Hủy vé thành công",
                            JOptionPane.INFORMATION_MESSAGE);

                    // Xóa các trường thông tin
                    TFPNR.setText("");
                    lblNameValue.setText("");
                    lblFlightNameValue.setText("");
                    lblFlightCodeValue.setText("");
                    lblDateValue.setText("");

                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Lỗi khi hủy vé: " + e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        new Cancel();
    }
}
