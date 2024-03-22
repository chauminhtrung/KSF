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
public class NhanVien {

    private String MaNV;
    private String MatKhau;
    private String HoVaTen;
    private int VaiTro;
    private int GioiTinh;
    private String Email;
    private String Sdt;
    private String CCCD;
    private String DiaChi;
    private String Hinh;
    private Date NgaySinh;

    public NhanVien() {
    }

    public NhanVien(String MaNV, String MatKhau, String HoVaTen, int VaiTro, int GioiTinh, String Email, String Sdt, String CCCD, String DiaChi, String Hinh,Date NgaySinh) {
        this.MaNV = MaNV;
        this.MatKhau = MatKhau;
        this.HoVaTen = HoVaTen;
        this.VaiTro = VaiTro;
        this.GioiTinh = GioiTinh;
        this.Email = Email;
        this.Sdt = Sdt;
        this.CCCD = CCCD;
        this.DiaChi = DiaChi;
        this.Hinh = Hinh;
        this.NgaySinh = NgaySinh;
    }

    public String getMaNV() {
        return MaNV;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getHoVaTen() {
        return HoVaTen;
    }

    public void setHoVaTen(String HoVaTen) {
        this.HoVaTen = HoVaTen;
    }

    public int getVaiTro() {
        return VaiTro;
    }

    public void setVaiTro(int VaiTro) {
        this.VaiTro = VaiTro;
    }

    public int getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(int GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String Sdt) {
        this.Sdt = Sdt;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String Hinh) {
        this.Hinh = Hinh;
    }
}
