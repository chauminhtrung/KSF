/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.HoaDon;
import Model.KSF;
import Utils.JdbcHelper;

import View.FromHoaDon;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author ACER
 */
public class DAOHoaDon extends KSF<HoaDon, String> {

    final String INSERT = "INSERT INTO HoaDonPhong(MaHoaDon,NgayNhanPhong,NgayTraPhong,GhiChu,MaNV,MaKH,MaPhong) values(?,?,?,?,?,?,?)";
    final String UPDATE = "UPDATE HoaDonPhong set NgayNhanPhong = ?, NgayTraPhong = ?, GhiChu = ?, MaNV = ?, MaKH = ?,MaPhong = ? WHERE MaHoaDon = ?";
    final String DELETE = "DELETE FROM HoaDonPhong WHERE MaHoaDon = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM HoaDonPhong";
    final String SELECT_ByID_SQL = "SELECT * FROM HoaDonPhong where MaHoaDon = ?";
    ResultSet rs = null;
    FromHoaDon FromHoaDon;

    public DAOHoaDon(FromHoaDon fhoadon) {
        this.FromHoaDon = fhoadon;
    }

    @Override
    public void insert(HoaDon Entity) {
        JdbcHelper.executeUpdate(INSERT, Entity.getMaHoaDon(), Entity.getNgayNhanPhong(), Entity.getNgayTraPhong(), Entity.getGhiChu(), Entity.getMaNV(), Entity.getMaKH(), Entity.getMaPhong());
    }

    @Override
    public void update(HoaDon Entity) {
        JdbcHelper.executeUpdate(UPDATE, Entity.getMaHoaDon(), Entity.getNgayNhanPhong(), Entity.getNgayTraPhong(), Entity.getGhiChu(), Entity.getMaNV(), Entity.getMaKH(), Entity.getMaPhong());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.executeUpdate(DELETE, id);
    }

    @Override
    public List<HoaDon> selectAll() {
        return select(SELECT_ALL_SQL);
    }

    public void selectbyMa(int id) {
        try {
            String sql = "select HoaDonPhong.MaHoaDon,HoaDonPhong.NgayNhanPhong,HoaDonPhong.NgayTraPhong,KhachHang.MaKH,KhachHang.SoDT,Phong.MaPhong,Tang.MaTang,Phong.MaLoaiPhong,HoaDonChiTiet.GhiChu,Phong.Gia from HoaDonPhong \n"
                    + "INNER JOIN KhachHang ON HoaDonPhong.MaKH = KhachHang.MaKH \n"
                    + "INNER JOIN Phong ON HoaDonPhong.MaPhong = Phong.MaPhong\n"
                    + "INNER JOIN HoaDonChiTiet ON HoaDonPhong.MaHoaDon =  HoaDonChiTiet.MaHoaDon\n"
                    + "INNER JOIN Tang ON Phong.MaTang = Tang.MaTang where HoaDonPhong.MaHoaDon = ?";
            int mahd = (int) FromHoaDon.tblHoaDon.getValueAt(id, 0);
            ResultSet rs = JdbcHelper.executeQuery(sql, mahd);

            while (rs.next()) {
                FromHoaDon.txtMaHoaDon.setText(rs.getString(1));
                FromHoaDon.jdateNgayNhan.setDate(rs.getDate(2));
                FromHoaDon.jdateNgayTra.setDate(rs.getDate(3));
                FromHoaDon.txtMaKhachHang.setText(rs.getString(4));
                FromHoaDon.txtSDT.setText(rs.getString(5));
                FromHoaDon.txtMaPhong.setText(rs.getString(6));
                FromHoaDon.txtMaTang.setText(rs.getString(7));
                FromHoaDon.txtMaLoaiPhong.setText(rs.getString(8));
                FromHoaDon.txtGhiChu.setText(rs.getString(9));
                FromHoaDon.txtThanhTien.setText(rs.getString(10));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public List<HoaDon> selectbyID(String sql, Object... agrs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<HoaDon> select(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                HoaDon entity = new HoaDon();
                entity.setMaHoaDon(rs.getInt("MaHoaDon"));
                entity.setNgayNhanPhong(rs.getDate("NgayNhanPhong"));
                entity.setNgayTraPhong(rs.getDate("NgayTraPhong"));
                entity.setMaKH(rs.getString("MaKH"));
                entity.setSDT(rs.getString("SoDT"));
                entity.setMaPhong(rs.getString("MaPhong"));
                entity.setMaTang(rs.getString("MaTang"));
                entity.setMaLoaiPhong(rs.getString("MaLoaiPhong"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setGia(rs.getString("Gia"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<HoaDon> selectByAll(String key) {
        String sql = "select HoaDonPhong.MaHoaDon,HoaDonPhong.NgayNhanPhong,HoaDonPhong.NgayTraPhong,KhachHang.MaKH,KhachHang.SoDT,Phong.MaPhong,Tang.MaTang,Phong.MaLoaiPhong,HoaDonChiTiet.GhiChu,Phong.Gia from HoaDonPhong \n"
                + "INNER JOIN KhachHang ON HoaDonPhong.MaKH = KhachHang.MaKH \n"
                + "INNER JOIN Phong ON HoaDonPhong.MaPhong = Phong.MaPhong\n"
                + "INNER JOIN HoaDonChiTiet ON HoaDonPhong.MaHoaDon =  HoaDonChiTiet.MaHoaDon\n"
                + "INNER JOIN Tang ON Phong.MaTang = Tang.MaTang where Phong.MaPhong like ?";
        return this.select(sql, "%" + key + "%");
    }


    @Override
    public HoaDon selectbyID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
