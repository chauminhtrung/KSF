/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.DichVu;
import Model.KSF;

import Utils.JdbcHelper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DaoDichVu extends KSF<DichVu, String> {

    final String INSERT_SQL = "INSERT INTO DichVu(MaLoaiDichVu,MaDichVu,DonGia,Mota) values(?,?,?,?)";
    final String INSERTPDV_SQL = "{call InsertDichVu(?,?,?)}";
    final String UPDATE_SQL = "UPDATE DichVu set MaLoaiDichVu = ?, MaDichVu = ?, DonGia = ?, Mota = ? WHERE MaDichVu = ?";
    final String DELETE_SQL = "DELETE FROM DichVu WHERE MaDichVU = ?";
    final String SELECT_ALL_SQL = "SELECT LoaiDichVu.MaLoaiDichVu,MaDichVu,TenLoaiDichVu,DonGia,Mota FROM DichVu INNER JOIN LoaiDichVu\n"
            + "ON DichVu.MaLoaiDichVu = LoaiDichVu.MaLoaiDichVU";

    final String SELECT_ByID_SQL = "SELECT LoaiDichVu.MaLoaiDichVu,MaDichVu,TenLoaiDichVu,DonGia,Mota FROM DichVu INNER JOIN LoaiDichVu\n"
            + "ON DichVu.MaLoaiDichVu = LoaiDichVu.MaLoaiDichVu WHERE MaDichVU = ?";
    final String SELECT_ByName_SQL = "SELECT LoaiDichVu.MaLoaiDichVu,MaDichVu,TenLoaiDichVu,DonGia,Mota FROM DichVu INNER JOIN LoaiDichVu\n"
            + "ON DichVu.MaLoaiDichVu = LoaiDichVu.MaLoaiDichVu WHERE TenLoaiDichVU = ?";

    final String DelDV = "{call DeleteDichVu(?)}";

    final String InDV = "{call InDichVu(?,?,?,?)}";

    public void Indv(String MaPhong, String MaDV, int SoLuong, Date ThoiGianGoi) {
        try {
            PreparedStatement stt = JdbcHelper.prepareStatement(InDV,
                    MaPhong,
                    MaDV,
                    SoLuong,
                    ThoiGianGoi
            );
            stt.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void Deltedv(String MaPhong) {
        try {
            PreparedStatement stt = JdbcHelper.prepareStatement(DelDV,
                    MaPhong
            );
            stt.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void insert(DichVu Entity) {
        JdbcHelper.executeUpdate(INSERT_SQL, Entity.getMaLoaiDichVu(), Entity.getMaDichVu(), Entity.getDonGia(), Entity.getMoTa());
    }

    public void insertpdv(String MaDV, int SoLuong, Date ThoiGianGoi) {
        try {
            PreparedStatement stt = JdbcHelper.prepareStatement(INSERTPDV_SQL,
                    MaDV,
                    SoLuong,
                    ThoiGianGoi);
            stt.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(DichVu entity) {
        JdbcHelper.executeUpdate(UPDATE_SQL, entity.getMaLoaiDichVu(), entity.getMaDichVu(), entity.getDonGia(), entity.getMoTa());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<DichVu> selectAll() {
        return select(SELECT_ALL_SQL);
    }

    @Override
    public DichVu selectbyID(String id) {
        List<DichVu> list = select(SELECT_ByID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public DichVu selectbyName(String name) {
        List<DichVu> list = select(SELECT_ByName_SQL, name);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public int countAll() {
        String sql = "select count(*) from DichVu";
        try ( ResultSet rs = JdbcHelper.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public List<DichVu> selectByAll(String key) {
        try {
            String sql = "SELECT LoaiDichVu.MaLoaiDichVu,MaDichVu,TenLoaiDichVu,DonGia,Mota FROM DichVu INNER JOIN LoaiDichVu\n"
                    + "ON DichVu.MaLoaiDichVu = LoaiDichVu.MaLoaiDichVU WHERE MaDichVu like ? ";
            return this.select(sql, "%" + key + "%");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<DichVu> select(String sql, Object... args) {
        List<DichVu> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                DichVu entity = new DichVu();
                entity.setMaLoaiDichVu(rs.getInt("MaLoaiDichVu"));
                entity.setMaDichVu(rs.getString("MaDichVu"));
                entity.setTenDichVu(rs.getString("TenLoaiDichVu"));
                entity.setDonGia(rs.getFloat("DonGia"));
                entity.setMoTa(rs.getString("Mota"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<DichVu> selectbyID(String sql, Object... agrs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
