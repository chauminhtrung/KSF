/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Tuple<E, F,G> {
    public E MaDV;
    public F SoLuong;
    public G ThoiGianGoi;

    public E getMaDV() {
        return MaDV;
    }

    public G getThoiGianGoi() {
        return ThoiGianGoi;
    }

    public void setThoiGianGoi(G ThoiGianGoi) {
        this.ThoiGianGoi = ThoiGianGoi;
    }

    public void setMaDV(E MaDV) {
        this.MaDV = MaDV;
    }

    public F getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(F SoLuong) {
        this.SoLuong = SoLuong;
    }
    
    
    
}