/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class HoaDonPhong {

    private String MaHD;
    private Date NgayNhanPhong;
    private Date NgayTraPhong;
    private String GhiChu;
    private String MaNV;

    private String MaPhong;
    private int MaHDCT;
    private Date NgayXuatHoaDon;
    private String HinhThucThue;
    private int GiamGia;
    private float PhuThu;
    private String ThanhToan;
    private float TraTruoc;

    public HoaDonPhong() {
    }

    public HoaDonPhong(String MaHD, Date NgayNhanPhong, Date NgayTraPhong, String GhiChu, String MaNV, String MaPhong, int MaHDCT, Date NgayXuatHoaDon, String HinhThucThue, int GiamGia, float PhuThu, String ThanhToan, float TraTruoc) {
        this.MaHD = MaHD;
        this.NgayNhanPhong = NgayNhanPhong;
        this.NgayTraPhong = NgayTraPhong;
        this.GhiChu = GhiChu;
        this.MaNV = MaNV;
  
        this.MaPhong = MaPhong;
        this.MaHDCT = MaHDCT;
        this.NgayXuatHoaDon = NgayXuatHoaDon;
        this.HinhThucThue = HinhThucThue;
        this.GiamGia = GiamGia;
        this.PhuThu = PhuThu;
        this.ThanhToan = ThanhToan;
        this.TraTruoc = TraTruoc;
    }

    public String getHinhThucThue() {
        return HinhThucThue;
    }

    public void setHinhThucThue(String HinhThucThue) {
        this.HinhThucThue = HinhThucThue;
    }

    public int getGiamGia() {
        return GiamGia;
    }

    public void setGiamGia(int GiamGia) {
        this.GiamGia = GiamGia;
    }

    public float getPhuThu() {
        return PhuThu;
    }

    public void setPhuThu(float PhuThu) {
        this.PhuThu = PhuThu;
    }

    public String getThanhToan() {
        return ThanhToan;
    }

    public void setThanhToan(String ThanhToan) {
        this.ThanhToan = ThanhToan;
    }

    public float getTraTruoc() {
        return TraTruoc;
    }

    public void setTraTruoc(float TraTruoc) {
        this.TraTruoc = TraTruoc;
    }

    public Date getNgayXuatHoaDon() {
        return NgayXuatHoaDon;
    }

    public void setNgayXuatHoaDon(Date NgayXuatHoaDon) {
        this.NgayXuatHoaDon = NgayXuatHoaDon;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
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



    public String getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(String MaPhong) {
        this.MaPhong = MaPhong;
    }

    public int getMaHDCT() {
        return MaHDCT;
    }

    public void setMaHDCT(int MaHDCT) {
        this.MaHDCT = MaHDCT;
    }

}
