/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Model.KSF;
import Model.LoaiPhong;
import Utils.JdbcHelper;

/**
 *
 * @author ngomi
 */
public class LoaiPhongDao extends KSF<LoaiPhong, Integer> {

	final String INSERT = "";
	final String UPDATE = "";
	final String DELETE = "";
	final String GETALL = "select * from LoaiPhong";
	final String GETBYID = "";

	@Override
	public void insert(LoaiPhong entity) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public void update(LoaiPhong entity) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public void delete(Integer key) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public List<LoaiPhong> selectAll() {
		return selectBySql(GETALL);
	}

	public List<LoaiPhong> selectBySql(String sql, Object... arg) {
		List<LoaiPhong> list = new ArrayList<>();
		try {
			ResultSet rs = JdbcHelper.executeQuery(sql, arg);
			while (rs.next()) {
				LoaiPhong entity = new LoaiPhong();
				entity.setMaLoaiPhong(rs.getInt("MaLoaiPhong"));
				entity.setTenLoaiPhong(rs.getString("TenLoaiPhong"));
				list.add(entity);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	@Override
	public LoaiPhong selectbyID(Integer id) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public List<LoaiPhong> selectbyID(String sql, Object... agrs) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

}
