package org.example.airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddCustomer extends JFrame implements ActionListener {

    // Khai báo các biến toàn cục (global variables)
    JTextField txtName, txtNationality, txtCMND, txtAddress, txtPhone;
    JRadioButton rbtnMale, rbtnFemale;
    JButton btnSave;

    public AddCustomer() {
        // Thiết lập khung hình (Frame)
        setTitle("Thêm khách hàng");
        setSize(900, 600);
        setLocation(300, 500);
        getContentPane().setBackground(Color.white);
        setLayout(null);

        // Thêm tiêu đề chính
        JLabel lblTitle = new JLabel("THÊM THÔNG TIN KHÁCH HÀNG");
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 32));
        lblTitle.setForeground(Color.blue);
        lblTitle.setBounds(220, 20, 500, 35);
        add(lblTitle);

        // Họ tên (Name)
        JLabel lblName = new JLabel("Họ tên:");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblName.setBounds(60, 80, 150, 25);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(220, 80, 200, 25);
        add(txtName);

        // Quốc tịch (Nationality)
        JLabel lblNationality = new JLabel("Quốc tịch:");
        lblNationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNationality.setBounds(60, 130, 150, 25);
        add(lblNationality);

        txtNationality = new JTextField();
        txtNationality.setBounds(220, 130, 200, 25);
        add(txtNationality);

        // Số CMND/CCCD
        JLabel lblAadhar = new JLabel("Số CMND/CCCD:");
        lblAadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblAadhar.setBounds(60, 180, 150, 25);
        add(lblAadhar);

        txtCMND = new JTextField();
        txtCMND.setBounds(220, 180, 200, 25);
        add(txtCMND);

        // Địa chỉ (Address)
        JLabel lblAddress = new JLabel("Địa chỉ:");
        lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblAddress.setBounds(60, 230, 150, 25);
        add(lblAddress);

        txtAddress = new JTextField();
        txtAddress.setBounds(220, 230, 200, 25);
        add(txtAddress);

        // Số điện thoại (Phone)
        JLabel lblPhone = new JLabel("Điện thoại:");
        lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPhone.setBounds(60, 280, 150, 25);
        add(lblPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(220, 280, 200, 25);
        add(txtPhone);

        // Giới tính (Gender - Radio Buttons)
        JLabel lblGender = new JLabel("Giới tính:");
        lblGender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblGender.setBounds(60, 330, 150, 25);
        add(lblGender);

        rbtnMale = new JRadioButton("Nam");
        rbtnMale.setBackground(Color.white);
        rbtnMale.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rbtnMale.setBounds(220, 330, 80, 25);
        add(rbtnMale);

        rbtnFemale = new JRadioButton("Nữ");
        rbtnFemale.setBackground(Color.white);
        rbtnFemale.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rbtnFemale.setBounds(310, 330, 80, 25);
        add(rbtnFemale);

        // Nhóm các nút radio để chỉ chọn được một
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbtnMale);
        genderGroup.add(rbtnFemale);

        // Nút Lưu (Save)
        btnSave = new JButton("LƯU");
        btnSave.setBackground(Color.black);
        btnSave.setForeground(Color.white);
        btnSave.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnSave.setBounds(220, 380, 120, 35);
        btnSave.addActionListener(this); // Đăng ký sự kiện cho nút Lưu
        add(btnSave);

        // Hình ảnh minh họa (ImageIcon)
        ImageIcon imgIcon = new ImageIcon("src/main/resources/icons/emp.png");
        JLabel lblImage = new JLabel(imgIcon);
        lblImage.setBounds(450, 80, 280, 400);
        add(lblImage);

        // Hiển thị cửa sổ
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // Triển khai phương thức actionPerformed để xử lý sự kiện
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnSave) {
            // Lấy dữ liệu từ các trường nhập liệu
            String name = txtName.getText();
            String nationality = txtNationality.getText();
            String aadhar = txtCMND.getText();
            String address = txtAddress.getText();
            String phone = txtPhone.getText();

            // Xử lý logic giới tính
            String gender = null;
            if (rbtnMale.isSelected()) {
                gender = "Nam";
            } else if (rbtnFemale.isSelected()) {
                gender = "Nữ";
            }

            // In thông tin ra console để kiểm tra (sẽ được thay thế bằng SQL sau)
            System.out.println("Thông tin khách hàng:");
            System.out.println("Họ tên: " + name);
            System.out.println("Quốc tịch: " + nationality);
            System.out.println("Số CMND/CCCD: " + aadhar);
            System.out.println("Địa chỉ: " + address);
            System.out.println("Điện thoại: " + phone);
            System.out.println("Giới tính: " + gender);

            // Dữ liệu đã sẵn sàng để đưa vào câu lệnh SQL
            // Ví dụ: INSERT INTO customer VALUES (..., ..., ...)
        }
    }

    public static void main(String[] args) {
        new AddCustomer();
    }
}
