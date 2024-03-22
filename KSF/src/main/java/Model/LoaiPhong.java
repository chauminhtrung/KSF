/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class LoaiPhong {

	private int maLoaiPhong;
	private String tenLoaiPhong;

	public LoaiPhong() {

	}

	public LoaiPhong(int maLoaiPhong, String tenLoaiPhong) {
		super();
		this.maLoaiPhong = maLoaiPhong;
		this.tenLoaiPhong = tenLoaiPhong;
	}

	public int getMaLoaiPhong() {
		return maLoaiPhong;
	}

	public void setMaLoaiPhong(int maLoaiPhong) {
		this.maLoaiPhong = maLoaiPhong;
	}

	public String getTenLoaiPhong() {
		return tenLoaiPhong;
	}

	public void setTenLoaiPhong(String tenLoaiPhong) {
		this.tenLoaiPhong = tenLoaiPhong;
	}

	@Override
	public String toString() {
		return this.tenLoaiPhong;
	}
}
