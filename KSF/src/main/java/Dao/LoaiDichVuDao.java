/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Model.KSF;
import Model.LoaiDichVu;
import Utils.JdbcHelper;

/**
 *
 * @author ngomi
 */
public class LoaiDichVuDao extends KSF<LoaiDichVu, Integer> {

	final String INSERT = "INSERT INTO LoaiDichVu (MaLoaiDichVu,TenLoaiDichVu) VALUES (?,?)";
	final String UPDATE = "UPDATE LoaiDichVu set TenLoaiDichVu=? WHERE MaLoaiDichVu=?";
	final String DELETE = "DELETE FROM LoaiDichVu where MaLoaiDichVu=?";
	final String GETALL = "SELECT * FROM LoaiDichVu";
	final String GETBY = "SELECT * FROM LoaiDichVu where MaLoaiDichVu=?";

	@Override
	public void insert(LoaiDichVu entity) {
		JdbcHelper.executeUpdate(INSERT, entity.getMaLoaiDichVu(), entity.getTenLoaiDichVu());
	}

	@Override
	public void update(LoaiDichVu entity) {
		JdbcHelper.executeUpdate(UPDATE, entity.getTenLoaiDichVu(), entity.getMaLoaiDichVu());
	}

	@Override
	public void delete(Integer key) {
		JdbcHelper.executeUpdate(DELETE, key);
	}

	@Override
	public List<LoaiDichVu> selectAll() {
		return selectBySql(GETALL);
	}

	public List<LoaiDichVu> selectBySql(String sql, Object... arg) {
		List<LoaiDichVu> list = new ArrayList<>();
		try {
			ResultSet rs = JdbcHelper.executeQuery(sql, arg);
			while (rs.next()) {
				LoaiDichVu entity = new LoaiDichVu();
				entity.setMaLoaiDichVu(rs.getInt("MaLoaiDichVu"));
				entity.setTenLoaiDichVu(rs.getString("TenLoaiDichVU"));
				list.add(entity);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	@Override
	public LoaiDichVu selectbyID(Integer id) {
		List<LoaiDichVu> list = selectBySql(GETBY, id);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public List<LoaiDichVu> selectbyID(String sql, Object... agrs) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

}
