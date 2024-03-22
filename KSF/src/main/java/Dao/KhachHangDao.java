/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Model.KSF;
import Model.KhachHang;
import Utils.JdbcHelper;

/**
 *
 * @author ngomi
 */
public class KhachHangDao extends KSF<KhachHang, String> {

	final String INSERT = "INSERT INTO KhachHang (MaKH, HoTen,GioiTinh,NgaySinh,SoDT,SoCCCD,DiaChi) VALUES (?,?,?,?,?,?,?)";
	final String UPDATE = "UPDATE KhachHang set HoTen=?, GioiTinh=?, NgaySinh=?, SoDT=?, SoCCCD=?, DiaChi=? WHERE MaKH=?";
	final String DELETE = "DELETE FROM KhachHang where MaKH=?";
	final String GETALL = "SELECT * FROM KhachHang";
	final String GETBY = "SELECT * FROM KhachHang where MaKH=?";

	@Override
	public void insert(KhachHang entity) {
		JdbcHelper.executeUpdate(INSERT, entity.getMaKH(), entity.getHoTen(), entity.getGioiTinh(),
				entity.getNgaySinh(), entity.getSoDT(), entity.getSoCCCD(), entity.getDiaChi());
	}

	@Override
	public void update(KhachHang entity) {
		JdbcHelper.executeUpdate(UPDATE, entity.getHoTen(), entity.getGioiTinh(), entity.getNgaySinh(),
				entity.getSoDT(), entity.getSoCCCD(), entity.getDiaChi(), entity.getMaKH());
	}

	@Override
	public void delete(String key) {
		JdbcHelper.executeUpdate(DELETE, key);
	}

	@Override
	public List<KhachHang> selectAll() {
		return selectBySql(GETALL);
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

	public List<KhachHang> selectByAll(String key) {
		try {
			String sql = "select * from KhachHang where MaKH=? or HoTen Like ? or SoDT=? or SoCCCD=?";
			return this.selectBySql(sql, key, "%" + key + "%", key, key);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int countAll() {
		String sql = "select count(*) from KhachHang";
		try (ResultSet rs = JdbcHelper.executeQuery(sql)) {
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return 0;
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

	@Override
	public List<KhachHang> selectbyID(String sql, Object... agrs) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}
}
