/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author User
 */
public class DoanhThu {
	String maNV;
	String maKH;
	String Ngayxuat;
	Double TongTien;

	public DoanhThu() {

	}

	public DoanhThu(String maNV, String maKH, String Ngayxuat, Double TongTien) {
		this.maNV = maNV;
		this.maKH = maKH;
		this.Ngayxuat = Ngayxuat;
		this.TongTien = TongTien;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getNgayxuat() {
		return Ngayxuat;
	}

	public void setNgayxuat(String Ngayxuat) {
		this.Ngayxuat = Ngayxuat;
	}

	public Double getTongTien() {
		return TongTien;
	}

	public void setTongTien(Double TongTien) {
		this.TongTien = TongTien;
	}

}
