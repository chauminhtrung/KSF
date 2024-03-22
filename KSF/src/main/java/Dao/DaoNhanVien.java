/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.KSF;
import Model.KhachHang;
import Model.KhachHangGanDay;
import Model.NhanVien;
import Utils.JdbcHelper;
import View.FormNhanVien;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class DaoNhanVien extends KSF<NhanVien, String> {

    private String INSERT_SQL_NHANVIEN = "INSERT INTO NhanVien VALUES (?,?,?,?,?,?,?,?,?,?)";
    private String INSERT_SQL_TAIKHOAN = "INSERT INTO TaiKhoan VALUES (?,?)";
    private String UPDATE_SQL_NHANVIEN = "UPDATE NhanVien SET HoTen = ?, GioiTinh = ?, SoDT = ?, Email = ?"
            + ", SoCCCD = ?, DiaChi = ?, VaiTro = ?, Hinh = ? , NgaySinh = ? WHERE MaNV = ?";
    private String UPDATE_SQL_TAIKHOAN = "UPDATE TaiKhoan SET MatKhau = ? WHERE MaNV = ?";
    private String DELETE_SQL_NHANVIEN = "DELETE FROM NhanVien WHERE MaNV = ?";
    private String DELETE_SQL_TAIKHOAN = "DELETE FROM TaiKhoan WHERE MaNV = ?";
    private String DELETE_SQL_HOADONPHONG = "DELETE FROM HoaDonPhong WHERE MaNV = ?";
    private String SELECT_ALL_SQL = "SELECT * FROM NhanVien";
    private String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien WHERE MaNV = ?";
    private NhanVien nhanvien = null;
    private FormNhanVien formNhanVien;
    String user = "sa";
    String pas = "nhan0944340821";
    PreparedStatement pstDetails = null;
    PreparedStatement pstUpdate = null;
    PreparedStatement pstDelete = null;
    String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyKhachSan;encrypt=false;trustServerCertificate=true";
    Connection con = null;
    Statement st = null;
    ResultSet rts;
    int select;

    public List<NhanVien> loadList() {
        List<NhanVien> List = new ArrayList<>();
        try {

            String sql = "SELECT TaiKhoan.MaNV,HoTen,GioiTinh,SoDT,Email,SoCCCD,DiaChi,VaiTro,MatKhau,Hinh,NgaySinh FROM NhanVien INNER JOIN TaiKhoan\n"
                    + "ON NhanVien.MaNV = TaiKhoan.MaNV";
            List.removeAll(List);

            rts = JdbcHelper.executeQuery(sql);
            while (rts.next()) {
                String MaNH = rts.getString(1);
                String HoVaTen = rts.getString(2);
                int GioiTinh = rts.getInt(3);
                String SDT = rts.getString(4);
                String Email = rts.getString(5);
                String SCCD = rts.getString(6);
                String DiaChi = rts.getString(7);
                int VaiTro = rts.getInt(8);
                String Matkhau = rts.getString(9);
                String Hinh = rts.getString(10);
                Date NgaySinh = rts.getDate(11);
                NhanVien nhanVien = new NhanVien(MaNH, Matkhau, HoVaTen, VaiTro, GioiTinh, Email, SDT, SCCD, DiaChi, Hinh, NgaySinh);
                List.add(nhanVien);
            }
            return List;
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
            throw new RuntimeException(e);
        }
    }

    public List<NhanVien> loadListNV() {
        List<NhanVien> List = new ArrayList<>();
        try {

            String sql = "SELECT TaiKhoan.MaNV,HoTen,GioiTinh,SoDT,Email,SoCCCD,DiaChi,VaiTro,MatKhau,Hinh,NgaySinh FROM NhanVien INNER JOIN TaiKhoan\n"
                    + "ON NhanVien.MaNV = TaiKhoan.MaNV WHERE VaiTro = 0";
            List.removeAll(List);
            rts = JdbcHelper.executeQuery(sql);
            while (rts.next()) {
                String MaNH = rts.getString(1);
                String HoVaTen = rts.getString(2);
                int GioiTinh = rts.getInt(3);
                String SDT = rts.getString(4);
                String Email = rts.getString(5);
                String SCCD = rts.getString(6);
                String DiaChi = rts.getString(7);
                int VaiTro = rts.getInt(8);
                String Matkhau = rts.getString(9);
                String Hinh = rts.getString(10);
                Date NgaySinh = rts.getDate(11);
                NhanVien nhanVien = new NhanVien(MaNH, Matkhau, HoVaTen, VaiTro, GioiTinh, Email, SDT, SCCD, DiaChi, Hinh, NgaySinh);
                List.add(nhanVien);
            }
            return List;
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
            throw new RuntimeException(e);
        }
    }

    public List<NhanVien> loadListQL() {
        List<NhanVien> List = new ArrayList<>();
        try {

            String sql = "SELECT TaiKhoan.MaNV,HoTen,GioiTinh,SoDT,Email,SoCCCD,DiaChi,VaiTro,MatKhau,Hinh,NgaySinh FROM NhanVien INNER JOIN TaiKhoan\n"
                    + "ON NhanVien.MaNV = TaiKhoan.MaNV WHERE VaiTro = 1";
            List.removeAll(List);
            rts = JdbcHelper.executeQuery(sql);
            while (rts.next()) {
                String MaNH = rts.getString(1);
                String HoVaTen = rts.getString(2);
                int GioiTinh = rts.getInt(3);
                String SDT = rts.getString(4);
                String Email = rts.getString(5);
                String SCCD = rts.getString(6);
                String DiaChi = rts.getString(7);
                int VaiTro = rts.getInt(8);
                String Matkhau = rts.getString(9);
                String Hinh = rts.getString(10);
                Date NgaySinh = rts.getDate(11);
                NhanVien nhanVien = new NhanVien(MaNH, Matkhau, HoVaTen, VaiTro, GioiTinh, Email, SDT, SCCD, DiaChi, Hinh, NgaySinh);
                List.add(nhanVien);
            }
            return List;
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
            throw new RuntimeException(e);
        }
    }

    //Update mật khẩu cho TaiKhoan
    public void updatePassword() {

    }

    //load 
    public void ConnecData() {

    }

    public NhanVien FindByMa(String MaNV) {
        NhanVien nhanvien = null;
        try {
            String sql = "SELECT TaiKhoan.MaNV,HoTen,GioiTinh,SoDT,Email,SoCCCD,DiaChi,VaiTro,MatKhau,Hinh,NgaySinh FROM NhanVien INNER JOIN TaiKhoan\n"
                    + "ON NhanVien.MaNV = TaiKhoan.MaNV Where TaiKhoan.MaNV = ? ";
            PreparedStatement stt = JdbcHelper.prepareStatement(sql);
            stt.setString(1, MaNV);
            rts = stt.executeQuery();
            while (rts.next()) {
                String MaNH = rts.getString(1);
                String HoVaTen = rts.getString(2);
                int GioiTinh = rts.getInt(3);
                String SDT = rts.getString(4);
                String Email = rts.getString(5);
                String SCCD = rts.getString(6);
                String DiaChi = rts.getString(7);
                int VaiTro = rts.getInt(8);
                String Matkhau = rts.getString(9);
                String Hinh = rts.getString(10);
                Date NgaySinh = rts.getDate(11);
                nhanvien = new NhanVien(MaNH, Matkhau, HoVaTen, VaiTro, GioiTinh, Email, SDT, SCCD, DiaChi, Hinh, NgaySinh);
            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        return nhanvien;

    }

    @Override
    public void insert(NhanVien entity) {
        JdbcHelper.executeUpdate(INSERT_SQL_NHANVIEN, entity.getMaNV(), entity.getHoVaTen(), entity.getGioiTinh(), entity.getSdt(), entity.getEmail(), entity.getCCCD(),
                entity.getDiaChi(), entity.getVaiTro(), entity.getHinh(), entity.getNgaySinh());
        JdbcHelper.executeUpdate(INSERT_SQL_TAIKHOAN, entity.getMaNV(), entity.getMatKhau());
    }

    @Override
    public void update(NhanVien entity) {
        JdbcHelper.executeUpdate(UPDATE_SQL_NHANVIEN, entity.getHoVaTen(), entity.getGioiTinh(), entity.getSdt(), entity.getEmail(), entity.getCCCD(),
                entity.getDiaChi(), entity.getVaiTro(), entity.getHinh(), entity.getNgaySinh(), entity.getMaNV());
        JdbcHelper.executeUpdate(UPDATE_SQL_TAIKHOAN, entity.getMatKhau(), entity.getMaNV());
    }

    @Override
    public List<NhanVien> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public NhanVien selectbyID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        JdbcHelper.executeUpdate(DELETE_SQL_NHANVIEN, id);
    }

    @Override
    public List<NhanVien> selectbyID(String sql, Object... agrs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
