/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class LoaiDichVu {

	private int maLoaiDichVu;
	private String tenLoaiDichVu;

	public LoaiDichVu() {

	}

	public LoaiDichVu(int maLoaiDichVu, String tenLoaiDichVu) {
		super();
		this.maLoaiDichVu = maLoaiDichVu;
		this.tenLoaiDichVu = tenLoaiDichVu;
	}

	public int getMaLoaiDichVu() {
		return maLoaiDichVu;
	}

	public void setMaLoaiDichVu(int maLoaiDichVu) {
		this.maLoaiDichVu = maLoaiDichVu;
	}

	public String getTenLoaiDichVu() {
		return tenLoaiDichVu;
	}

	public void setTenLoaiDichVu(String tenLoaiDichVu) {
		this.tenLoaiDichVu = tenLoaiDichVu;
	}

	@Override
	public String toString() {
		return tenLoaiDichVu + " (" + maLoaiDichVu + ")";
	}

}
