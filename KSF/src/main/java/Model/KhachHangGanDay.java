/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


/**
 *
 * @author Admin
 */
public class KhachHangGanDay {

    private String Hoten;
    private String CheckIn;
    private String CheckOut;
    private String LoaiPhong;

    public KhachHangGanDay(String Hoten, String CheckIn, String CheckOut, String LoaiPhong) {
        this.Hoten = Hoten;
        this.CheckIn = CheckIn;
        this.CheckOut = CheckOut;
        this.LoaiPhong = LoaiPhong;
    }

    public KhachHangGanDay() {
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String Hoten) {
        this.Hoten = Hoten;
    }

    public String getCheckIn() {
        return CheckIn;
    }

    public void setCheckIn(String CheckIn) {
        this.CheckIn = CheckIn;
    }

    public String getCheckOut() {
        return CheckOut;
    }

    public void setCheckOut(String CheckOut) {
        this.CheckOut = CheckOut;
    }

    public String getLoaiPhong() {
        return LoaiPhong;
    }

    public void setLoaiPhong(String LoaiPhong) {
        this.LoaiPhong = LoaiPhong;
    }

}
