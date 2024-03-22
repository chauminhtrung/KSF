/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import javax.validation.constraints.Min;
import javax.validation.constraints.Negative;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Admin
 */
public class Phong {
   
    private int MaLoaiPhong;
    private String MaPhong;
    private double Gia;
    private String TrangThai;
    private String MoTa;
    private int MaTang;
    private String TenPhong;

    public Phong() {
    }

    public Phong(int MaLoaiPhong, String MaPhong, double Gia, String TrangThai, String MoTa, int MaTang, String TenPhong) {
        this.MaLoaiPhong = MaLoaiPhong;
        this.MaPhong = MaPhong;
        this.Gia = Gia;
        this.TrangThai = TrangThai;
        this.MoTa = MoTa;
        this.MaTang = MaTang;
        this.TenPhong = TenPhong;
    }

    public String getTenPhong() {
        return TenPhong;
    }

    public void setTenPhong(String TenPhong) {
        this.TenPhong = TenPhong;
    }

    public int getMaLoaiPhong() {
        return MaLoaiPhong;
    }

    public void setMaLoaiPhong(int MaLoaiPhong) {
        this.MaLoaiPhong = MaLoaiPhong;
    }

    public String getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(String MaPhong) {
        this.MaPhong = MaPhong;
    }

    public double getGia() {
        return Gia;
    }

    public void setGia(double Gia) {
        this.Gia = Gia;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }




    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public int getMaTang() {
        return MaTang;
    }

    public void setMaTang(int MaTang) {
        this.MaTang = MaTang;
    }

}
