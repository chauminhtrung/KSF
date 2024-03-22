/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.KSF;
import Model.KhachHang;
import Model.KhachHangGanDay;
import Utils.JdbcHelper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class DaoKhachHang extends KSF<KhachHang, String> {

    public KhachHang khachhang;

    public List<KhachHangGanDay> ListKHGD = new ArrayList<>();

    final String INSERT = "{call INSERTKH(?,?,?,?,?,?)}";
    final String UPDATE = "UPDATE KhachHang set HoTen=?, GioiTinh=?, NgaySinh=?, SoDT=?, SoCCCD=?, DiaChi=? WHERE MaKH=?";
    final String DELETE = "DELETE FROM KhachHang where MaKH=?";
    final String GETALL = "SELECT * FROM KhachHang";
    final String GETBY = "SELECT * FROM KhachHang where MaKH=?";
    final String UPDATEProc = "{call UPDATEKH(?,?,?,?,?,?,?)}";

    public void UpdateKH(String maphong, String Hoten, int GoiTinh, String SDT, String CCCD, Date NgaySinh, String DiaChi) {
        try {
            PreparedStatement stt = JdbcHelper.prepareStatement(UPDATEProc, maphong, Hoten, GoiTinh, SDT, CCCD, NgaySinh, DiaChi);
            stt.executeQuery();

        } catch (Exception e) {
            System.out.println(e);

        }
    }

    public void LoadKHGanday(DefaultTableModel ModelTable) {
        try {
            ModelTable.setRowCount(0);
            String sql = "SELECT HoTen,NgayNhanPhong,NgayTraPhong,TenLoaiPhong FROM KhachHang INNER JOIN HoaDonPhong \n"
                    + "on KhachHang.MaKH = HoaDonPhong.MaKH \n"
                    + "inner join Phong \n"
                    + "on HoaDonPhong.MaPhong = Phong.MaPhong\n"
                    + "inner join LoaiPhong\n"
                    + "on Phong.MaLoaiPhong = LoaiPhong.MaLoaiPhong  "
                    + " Order by NgayTraPhong desc";

            ResultSet rts = JdbcHelper.executeQuery(sql);
            while (rts.next()) {
                Vector data = new Vector();
                data.add(rts.getString(1));
                data.add(rts.getDate(2));
                data.add(rts.getDate(3));
                data.add(rts.getString(4));
                KhachHangGanDay khachHangGanDay = new KhachHangGanDay(rts.getString(1), rts.getString(2), rts.getString(3), rts.getString(4));
                ModelTable.addRow(data);
                ListKHGD.add(khachHangGanDay);

            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    @Override
    public void insert(KhachHang entity) {
        try {
            PreparedStatement stt = JdbcHelper.prepareStatement(INSERT,
                    entity.getHoTen(),
                    entity.getGioiTinh(),
                    entity.getSoDT(),
                    entity.getSoCCCD(),
                    entity.getNgaySinh(),
                    entity.getDiaChi());

            stt.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public void update(KhachHang entity) {
        JdbcHelper.executeUpdate(UPDATE, entity.getMaKH(),
                entity.getHoTen(),
                entity.getGioiTinh(),
                entity.getNgaySinh(),
                entity.getSoDT(),
                entity.getSoCCCD(),
                entity.getDiaChi());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.executeUpdate(DELETE, id);
    }

    @Override
    public List<KhachHang> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public KhachHang selectbyID(String id) {
        List<KhachHang> list = selectBySql(GETBY, id);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public List<KhachHang> selectBySql(String sql, Object... arg) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, arg);
            while (rs.next()) {
                KhachHang entity = new KhachHang();
                entity.setMaKH(rs.getString("MaKH"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setGioiTinh(rs.getInt("GioiTinh"));
                entity.setNgaySinh(rs.getDate("NgaySinh"));
                entity.setSoDT(rs.getString("SoDT"));
                entity.setSoCCCD(rs.getString("SoCCCD"));
                entity.setDiaChi(rs.getString("DiaChi"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<KhachHang> selectbyID(String sql, Object... agrs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
