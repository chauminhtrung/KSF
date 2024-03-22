/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class DichVu {

    private int MaLoaiDichVu;
    private String MaDichVu;
    private String TenDichVu;
    private float DonGia;
    private String MoTa;

    public DichVu() {
    }

    public DichVu(int MaLoaiDichVu, String MaDichVu, String TenDichVu, float DonGia, String MoTa) {
        this.MaLoaiDichVu = MaLoaiDichVu;
        this.MaDichVu = MaDichVu;
        this.TenDichVu = TenDichVu;
        this.DonGia = DonGia;
        this.MoTa = MoTa;
    }

    public int getMaLoaiDichVu() {
        return MaLoaiDichVu;
    }

    public void setMaLoaiDichVu(int MaLoaiDichVu) {
        this.MaLoaiDichVu = MaLoaiDichVu;
    }

    public String getMaDichVu() {
        return MaDichVu;
    }

    public void setMaDichVu(String MaDichVu) {
        this.MaDichVu = MaDichVu;
    }

    public String getTenDichVu() {
        return TenDichVu;
    }

    public void setTenDichVu(String TenDichVu) {
        this.TenDichVu = TenDichVu;
    }

    public float getDonGia() {
        return DonGia;
    }

    public void setDonGia(float DonGia) {
        this.DonGia = DonGia;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

}
