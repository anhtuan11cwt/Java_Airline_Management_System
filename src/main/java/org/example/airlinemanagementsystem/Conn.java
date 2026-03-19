package org.example.airlinemanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {

    // Biến thành viên để lưu trữ đối tượng Connection và Statement
    Connection c;
    Statement s;

    // Constructor của lớp Conn
    public Conn() {
        try {
            // Bước 1: Đăng ký Driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Bước 2: Tạo chuỗi kết nối (Connection String)
            // Định dạng: jdbc:mysql://localhost:3306/tên_cơ_sở_dữ_liệu
            c = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/airline-management-system",
                    "root",
                    "123456");

            // Bước 3: Tạo Statement để thực thi các truy vấn SQL
            s = c.createStatement();

            System.out.println("Kết nối cơ sở dữ liệu thành công!");

        } catch (Exception e) {
            // Xử lý ngoại lệ khi kết nối thất bại
            e.printStackTrace();
            System.out.println("Kết nối cơ sở dữ liệu thất bại!");
        }
    }
}
