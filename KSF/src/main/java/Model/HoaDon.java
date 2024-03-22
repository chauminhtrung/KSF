/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author ACER
 */
public class HoaDon {
    private int MaHoaDon;
    private Date NgayNhanPhong;
    private Date NgayTraPhong;
    private String MaKH;
    private String SDT;
    private String MaPhong;
    private String MaTang;
    private String MaLoaiPhong;
    private String Gia;
    private String GhiChu;
    private String MaNV;

    public HoaDon() {
    }

    public HoaDon(int MaHoaDon, Date NgayNhanPhong, Date NgayTraPhong, String MaKH, String SDT, String MaPhong, String MaTang, String MaLoaiPhong, String Gia, String GhiChu, String MaNV) {
        this.MaHoaDon = MaHoaDon;
        this.NgayNhanPhong = NgayNhanPhong;
        this.NgayTraPhong = NgayTraPhong;
        this.MaKH = MaKH;
        this.SDT = SDT;
        this.MaPhong = MaPhong;
        this.MaTang = MaTang;
        this.MaLoaiPhong = MaLoaiPhong;
        this.Gia = Gia;
        this.GhiChu = GhiChu;
        this.MaNV = MaNV;
    }



    
    public int getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(int MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public Date getNgayNhanPhong() {
        return NgayNhanPhong;
    }

    public void setNgayNhanPhong(Date NgayNhanPhong) {
        this.NgayNhanPhong = NgayNhanPhong;
    }

    public Date getNgayTraPhong() {
        return NgayTraPhong;
    }

    public void setNgayTraPhong(Date NgayTraPhong) {
        this.NgayTraPhong = NgayTraPhong;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(String MaPhong) {
        this.MaPhong = MaPhong;
    }

    public String getMaTang() {
        return MaTang;
    }

    public void setMaTang(String MaTang) {
        this.MaTang = MaTang;
    }

    public String getMaLoaiPhong() {
        return MaLoaiPhong;
    }

    public void setMaLoaiPhong(String MaLoaiPhong) {
        this.MaLoaiPhong = MaLoaiPhong;
    }

    public String getGia() {
        return Gia;
    }

    public void setGia(String Gia) {
        this.Gia = Gia;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    
    
}
