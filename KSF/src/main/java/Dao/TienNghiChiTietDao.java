/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Model.KSF;
import Model.TienNghiChiTiet;
import Utils.JdbcHelper;

/**
 *
 * @author ngomi
 */
public class TienNghiChiTietDao extends KSF<TienNghiChiTiet, Integer> {

	final String INSERT = "INSERT INTO TienNghiChitiet (MaTienNghi,MaPhong,SoLuong) VALUES (?,?,?)";
	final String UPDATE = "UPDATE TienNghiChitiet set SoLuong=? WHERE MaTienNghi=? and MaPhong=?";
	final String DELETE = "DELETE FROM TienNghiChitiet where MaTienNghi=? and MaPhong=?";
	final String GETALL = "SELECT * FROM TienNghiChitiet";
	final String GETBY = "SELECT * FROM TienNghiChitiet where MaTienNghi=? and MaPhong=?";

	@Override
	public void insert(TienNghiChiTiet entity) {
		JdbcHelper.executeUpdate(INSERT, entity.getMaTienNghi(), entity.getMaPhong(), entity.getSoLuong());
	}

	@Override
	public void update(TienNghiChiTiet entity) {
		JdbcHelper.executeUpdate(UPDATE, entity.getSoLuong(), entity.getMaTienNghi(), entity.getMaPhong());
	}

	@Override
	public void delete(Integer key) {
		JdbcHelper.executeUpdate(DELETE, key);
	}

	@Override
	public List<TienNghiChiTiet> selectAll() {
		return selectBySql(GETALL);
	}

	public List<TienNghiChiTiet> selectBySql(String sql, Object... arg) {
		List<TienNghiChiTiet> list = new ArrayList<>();
		try {
			ResultSet rs = JdbcHelper.executeQuery(sql, arg);
			while (rs.next()) {
				TienNghiChiTiet entity = new TienNghiChiTiet();
				entity.setMaTienNghi(rs.getInt("MaTienNghi"));
				entity.setMaPhong(rs.getString("MaPhong"));
				entity.setSoLuong(rs.getInt("SoLuong"));
				list.add(entity);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	public TienNghiChiTiet selectByTwo(Integer key, String MaPhong) {
		List<TienNghiChiTiet> list = selectBySql(GETBY, key, MaPhong);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public List<TienNghiChiTiet> selectByAll(String key) {
		try {
			String sql = "select * from TienNghiChitiet where MaTienNghi=? or MaPhong=?";
			return this.selectBySql(sql, key, key);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public TienNghiChiTiet checkInsert(int key, String key1) {
		try {
			String sql = "select * from TienNghiChiTiet where MaTienNghi=? and MaPhong=?";
			List<TienNghiChiTiet> list = selectBySql(sql, key, key1);
			if (list.isEmpty()) {
				return null;
			} else {
				return list.get(0);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void deleteTwo(Integer key, String key1) {
		JdbcHelper.executeUpdate(DELETE, key, key1);
	}

	@Override
	public TienNghiChiTiet selectbyID(Integer id) {
		List<TienNghiChiTiet> list = selectBySql(GETBY, id);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public List<TienNghiChiTiet> selectbyID(String sql, Object... agrs) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

}
