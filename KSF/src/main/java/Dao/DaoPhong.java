/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.KSF;
import Model.Phong;
import Utils.JdbcHelper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DaoPhong extends KSF<Phong, String> {

    final String INSERT_SQL = "INSERT INTO PHONG(MaLoaiPhong,MaPhong,Gia,TrangThai,Mota,MaTang) values(?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE PHONG set Gia = ?, TrangThai = ?, Mota = ?, MaTang = ? WHERE MaPhong = ?";
    final String DELETE_SQL = "DELETE FROM Phong WHERE MaPhong = ?";
//    final String SELECT_ALL_SQL = "SELECT * FROM PHONG" ;
    final String SELECT_ALL_SQL = "SELECT PHONG.MaPhong, Gia, TrangThai, MoTa, MaTang, TenLoaiPhong,Phong.MaloaiPhong\n"
            + "            FROM PHONG \n"
            + "            Join LoaiPhong on PHONG.MaLoaiPhong = LoaiPhong.MaLoaiPhong\n"
            + "            order by Phong.MaPhong asc";
    final String SELECT_BY_TT_SQL
            = "SELECT PHONG.MaPhong, Gia, TrangThai, MoTa, MaTang, TenLoaiPhong,Phong.MaloaiPhong\n"
            + "            FROM PHONG \n"
            + "            Join LoaiPhong on PHONG.MaLoaiPhong = LoaiPhong.MaLoaiPhong\n"
            + "            where Phong.MaPhong = ?";

    final String COUT_Phong = "select COUNT(*) from Phong where MaTang = ?";
    final String setDoiPhong = "{call Update2Phong(?,?)}";
    final String SELECTdukiendoiphong = "{call DuKienDoiPhong(?)}";
    public int CountALLPhong() {
        int Tong = 0;
        try {
            String sql = "{call SelectALLPhong()}";
            PreparedStatement stt = JdbcHelper.prepareStatement(sql);
            ResultSet rts = stt.executeQuery();
            while (rts.next()) {
                Tong = rts.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        return Tong;
    }
    
    public List<Phong> DukienPhongTrong(Date NgayTim) {
        List<Phong> list = new ArrayList<>();
        try {
            PreparedStatement stt = JdbcHelper.prepareStatement(SELECTdukiendoiphong, NgayTim);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                Phong entity = new Phong();
                entity.setMaPhong(rs.getString("MaPhong"));
                entity.setGia(rs.getDouble("Gia"));
                entity.setTrangThai(rs.getString("TrangThai"));
                entity.setMoTa(rs.getString("MoTa"));
                entity.setMaTang(rs.getInt("MaTang"));
                entity.setTenPhong(rs.getString("TenLoaiPhong"));
                entity.setMaLoaiPhong(rs.getInt("MaLoaiPhong"));
                list.add(entity);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }
    
    public void setDoiPhong(String maphong1, String maphong2) {
        try {

            PreparedStatement stt = JdbcHelper.prepareStatement(setDoiPhong, maphong1, maphong2);
            stt.executeQuery();

        } catch (Exception e) {

        }
    }
    
    public int CountDatPhong() {
        int Tong = 0;
        try {
            String sql = "{call CountAllDP()}";
            PreparedStatement stt = JdbcHelper.prepareStatement(sql);
            ResultSet rts = stt.executeQuery();
            while (rts.next()) {
                Tong = rts.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        return Tong;
    }

    public int CoutId_Tang() {
        int TP = 0;
        try {
            String sql = "select COUNT(MaPhong) from Phong ";
            ResultSet rs = JdbcHelper.executeQuery(sql);
            while (rs.next()) {
                TP = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return TP;
    }

    public void UpdateTrangThai(String TrangThai, String MaPhong) {
        try {
            PreparedStatement stt = JdbcHelper.prepareStatement("{call UpdateTrangThai(?,?)}",
                    TrangThai,
                    MaPhong
            );
            stt.executeQuery();

        } catch (Exception e) {

        }

    }
    public int CoutId_DaDat() {
        int TP = 0;
        try {
            String sql = "select Count(MaPhong) from Phong where TrangThai = N'Đã Đặt' ";
            ResultSet rs = JdbcHelper.executeQuery(sql);
            while (rs.next()) {
                TP = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return TP;

    }
    public int CoutId_Trong() {
        int TP = 0;
        try {
            String sql = "select Count(MaPhong) from Phong where TrangThai = N'Trống' ";
            ResultSet rs = JdbcHelper.executeQuery(sql);
            while (rs.next()) {
                TP = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return TP;

    }

    public int CoutId_DangDon() {
        int TP = 0;
        try {
            String sql = "select Count(MaPhong) from Phong where TrangThai = N'Đang Dọn Phòng' ";
            ResultSet rs = JdbcHelper.executeQuery(sql);
            while (rs.next()) {
                TP = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return TP;

    }

    public int CoutId_DangSua() {
        int TP = 0;
        try {
            String sql = "select Count(MaPhong) from Phong where TrangThai = N'Đang Sửa Chữa' ";
            ResultSet rs = JdbcHelper.executeQuery(sql);
            while (rs.next()) {
                TP = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return TP;

    }

    @Override
    public void insert(Phong entity) {
        JdbcHelper.executeUpdate(INSERT_SQL, entity.getMaLoaiPhong(), entity.getMaPhong(), entity.getGia(), entity.getTrangThai(), entity.getMoTa(), entity.getMaTang());
    }

    @Override
    public void update(Phong entity) {
        JdbcHelper.executeUpdate(UPDATE_SQL, entity.getGia(), entity.getTrangThai(), entity.getMoTa(), entity.getMaTang(), entity.getMaPhong());
    }

    @Override
    public void delete(String ID) {
        JdbcHelper.executeUpdate(DELETE_SQL, ID);
    }

    @Override
    public List<Phong> selectAll() {
        return select(SELECT_ALL_SQL);
    }

    @Override
    public Phong selectbyID(String ID) {
        List<Phong> list = select(SELECT_BY_TT_SQL, ID);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<Phong> select(String sql, Object... args) {
        List<Phong> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                Phong entity = new Phong();
                entity.setMaPhong(rs.getString("MaPhong"));
                entity.setGia(rs.getDouble("Gia"));
                entity.setTrangThai(rs.getString("TrangThai"));
                entity.setMoTa(rs.getString("MoTa"));
                entity.setMaTang(rs.getInt("MaTang"));
                entity.setTenPhong(rs.getString("TenLoaiPhong"));
                entity.setMaLoaiPhong(rs.getInt("MaLoaiPhong"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Phong> selectbyID(String sql, Object... agrs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
