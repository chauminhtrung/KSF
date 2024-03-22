/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Model.KSF;
import Model.TienNghi;
import Utils.JdbcHelper;

/**
 *
 * @author ngomi
 */
public class TienNghiDao extends KSF<TienNghi, Integer> {

	final String INSERT = "INSERT INTO TienNghi (MaTienNghi,TenTienNghi) VALUES (?,?)";
	final String UPDATE = "UPDATE TienNghi set TenTienNghi=? WHERE MaTienNghi=?";
	final String DELETE = "DELETE FROM TienNghi where MaTienNghi=?";
	final String GETALL = "SELECT * FROM TienNghi";
	final String GETBY = "SELECT * FROM TienNghi where MaTienNghi=?";

	@Override
	public void insert(TienNghi entity) {
		JdbcHelper.executeUpdate(INSERT, entity.getMaTienNghi(), entity.getTenTienNghi());
	}

	@Override
	public void update(TienNghi entity) {
		JdbcHelper.executeUpdate(UPDATE, entity.getTenTienNghi(), entity.getMaTienNghi());
	}

	@Override
	public void delete(Integer key) {
		JdbcHelper.executeUpdate(DELETE, key);
	}

	@Override
	public List<TienNghi> selectAll() {
		return selectBySql(GETALL);
	}

	public List<TienNghi> selectBySql(String sql, Object... arg) {
		List<TienNghi> list = new ArrayList<>();
		try {
			ResultSet rs = JdbcHelper.executeQuery(sql, arg);
			while (rs.next()) {
				TienNghi entity = new TienNghi();
				entity.setMaTienNghi(rs.getInt("MaTienNghi"));
				entity.setTenTienNghi(rs.getString("TenTienNghi"));
				list.add(entity);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	public int countAll() {
		String sql = "select count(*) from TienNghi";
		try (ResultSet rs = JdbcHelper.executeQuery(sql)) {
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return 0;
	}

	public List<TienNghi> selectByAll(String key) {
		try {
			String sql = "select * from TienNghi where TenTienNghi like ?";
			return selectBySql(sql, "%" + key + "%");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public TienNghi selectbyID(Integer id) {
		List<TienNghi> list = selectBySql(GETBY, id);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public List<TienNghi> selectbyID(String sql, Object... agrs) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}
}
