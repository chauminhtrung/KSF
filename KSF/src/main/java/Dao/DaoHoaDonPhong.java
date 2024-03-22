/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.HoaDonPhong;
import Model.KSF;
import Model.Tuple;
import Utils.JdbcHelper;
import View.FormDatPhong;

import View.FormHoaDon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class DaoHoaDonPhong extends KSF<HoaDonPhong, Integer> {

    private FormDatPhong datPhongJFrame;
    private FormHoaDon hoaDonJF;
    public int tongtien = 0;
    public String tienphong;
    public String ThoiGiannhan;
    public String ThoiGiantra;
    public String HinhThuc;
    public String TongGio;
    final String INSERT_SQL = "{call INSERTHDP(?,?,?,?,?,?,?,?,?,?,?)}";
    final String SelectHoaDonCT = "{call ThongTinDatPhong(?)}";
    final String setTien = "{call SETTIEN(?,?)}";
    final String setGhiChu = "{call UpdateGhiChu(?,?)}";

    final String UpdateHD = "{call UpdateHoaDon(?,?,?,?,?,?,?,?)}";

    public DaoHoaDonPhong(FormDatPhong datPhongJFrame) {
        this.datPhongJFrame = datPhongJFrame;
    }

    public DaoHoaDonPhong(FormHoaDon hoaDonJF) {
        this.hoaDonJF = hoaDonJF;
    }

    public void UpdateHD(String maphong, Date NgayNhanPhong, Date NgayTraPhong, String HinhThucThue, int phuthu, int GiamGia, String ThanhToan, int tratruoc) {
        try {

            PreparedStatement stt = JdbcHelper.prepareStatement(UpdateHD, maphong, NgayNhanPhong, NgayTraPhong, HinhThucThue, phuthu, GiamGia, ThanhToan, tratruoc);
            stt.executeQuery();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //trong bang dat phong
    public void fillHoaDon(String MaPhong) {

        try {
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            PreparedStatement stt = JdbcHelper.prepareStatement(SelectHoaDonCT, MaPhong);
            ResultSet rts = stt.executeQuery();
            while (rts.next()) {
                //khach hang
                datPhongJFrame.txtHoTenKH.setText(rts.getString(1));
                datPhongJFrame.jdateNgaySinh.setDate(rts.getDate(2));
                datPhongJFrame.txtCCCD.setText(rts.getString(3));
                datPhongJFrame.txtSDT.setText(rts.getString(4));
                int gender = rts.getInt(5);
                if (gender == 1) {
                    datPhongJFrame.rdoNam.setSelected(true);
                } else {
                    datPhongJFrame.rdoNu.setSelected(true);
                }
                datPhongJFrame.txtDiaChi.setText(rts.getString(6));

                //hoa don chi tiet
                String hinhthuc = rts.getString(7);
                if (hinhthuc.equals("Ngày")) {
                    datPhongJFrame.rdoNgay.setSelected(true);
                } else {
                    datPhongJFrame.rdoGio.setSelected(true);
                }

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String NgayNhan = rts.getString(8);
                String NgayTra = rts.getString(9);
                Date dateNgayNhan = format.parse(NgayNhan);
                Date dateNgayTra = format.parse(NgayTra);
                datPhongJFrame.jdateNgayNhanPhong.setDate(dateNgayNhan);
                datPhongJFrame.jdateNgayTraPhong.setDate(dateNgayTra);

                datPhongJFrame.txtPhuThu.setText(String.valueOf(rts.getInt(10)));
                datPhongJFrame.txtGiamGia.setText(String.valueOf(rts.getInt(11)));
                datPhongJFrame.txtTraTruoc.setText(String.valueOf(rts.getInt(12)));

                datPhongJFrame.cboThanhToan.setSelectedItem(rts.getString(13));
                // phong dich vu 

                Vector data1 = new Vector();
                data1.add(rts.getInt(14));
                data1.add(rts.getString(15));
                data1.add(rts.getString(16));
                data1.add(rts.getString(17));
                data1.add(formatter.format(rts.getFloat(18)) + " đ");
                data1.add(rts.getInt(19));

                data1.add(rts.getString(20));

                Tuple a = new Tuple();
                a.setMaDV(rts.getString(17));
                a.setSoLuong(rts.getInt(19));
                a.setThoiGianGoi(rts.getDate(20));
                datPhongJFrame.listOfTuple.add(a);

                datPhongJFrame.tblModel2.addRow(data1);

            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }

    }
    //trong bang hoa don

    public void fillHoaDon2(String MaPhong) {
        try {
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            PreparedStatement stt = JdbcHelper.prepareStatement(SelectHoaDonCT, MaPhong);
            ResultSet rts = stt.executeQuery();
            rts.next();
            //khach hang
            hoaDonJF.txtTenKhachHang.setText(rts.getString(1));
            hoaDonJF.jdateNgaySinh.setDate(rts.getDate(2));
            hoaDonJF.txtCCCD.setText(rts.getString(3));
            hoaDonJF.txtSDT.setText(rts.getString(4));
            int gender = rts.getInt(5);
            if (gender == 1) {
                hoaDonJF.cboGioiTinh.setSelectedItem("Nam");
            } else {
                hoaDonJF.cboGioiTinh.setSelectedItem("Nữ");
            }
            hoaDonJF.txtDiaChi.setText(rts.getString(6));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String NgayNhan = rts.getString(8);
            String NgayTra = rts.getString(9);
            ThoiGiannhan = NgayNhan;
            ThoiGiantra = NgayTra;
            tienphong = formatter.format(rts.getFloat(23)) + " đ";
            HinhThuc = rts.getString(7);
            TongGio = rts.getString(22);
            Date dateNgayNhan = format.parse(NgayNhan);
            Date dateNgayTra = format.parse(NgayTra);
            hoaDonJF.jdateNgayNhan.setDate(rts.getDate(8));
            hoaDonJF.jdateNgayTra.setDate(rts.getDate(9));
            hoaDonJF.txtGioChkIn.setText(String.valueOf(dateNgayNhan.getHours()));
            hoaDonJF.txtphutCkin.setText(String.valueOf(dateNgayNhan.getMinutes()));
            hoaDonJF.txtGioChkOut.setText(String.valueOf(dateNgayTra.getHours()));
            hoaDonJF.txtPhutChkOut.setText(String.valueOf(dateNgayTra.getMinutes()));

            //tinh gio check in som
            if (Integer.valueOf(hoaDonJF.txtGioChkIn.getText()) >= 5 && Integer.valueOf(hoaDonJF.txtGioChkIn.getText()) <= 9) {
                hoaDonJF.txtNhanPhongSom.setText(String.valueOf((rts.getInt(23) * 50) / 100));
            } else if (Integer.valueOf(hoaDonJF.txtGioChkIn.getText()) >= 9 && Integer.valueOf(hoaDonJF.txtGioChkIn.getText()) <= 14) {
                hoaDonJF.txtNhanPhongSom.setText(String.valueOf((rts.getInt(23) * 30) / 100));
            } else {
                hoaDonJF.txtNhanPhongSom.setText(String.valueOf(0));
            }

            //tinh gio check out muon
            if (Integer.valueOf(hoaDonJF.txtGioChkOut.getText()) >= 12 && Integer.valueOf(hoaDonJF.txtGioChkOut.getText()) <= 15) {
                hoaDonJF.txtTraMuon.setText(String.valueOf((rts.getInt(23) * 30) / 100));
            } else if (Integer.valueOf(hoaDonJF.txtGioChkOut.getText()) >= 15 && Integer.valueOf(hoaDonJF.txtGioChkOut.getText()) <= 18) {
                hoaDonJF.txtTraMuon.setText(String.valueOf((rts.getInt(23) * 50) / 100));
            } else if (Integer.valueOf(hoaDonJF.txtGioChkOut.getText()) >= 16) {
                hoaDonJF.txtTraMuon.setText(String.valueOf(rts.getInt(23)));
            } else {
                hoaDonJF.txtTraMuon.setText(String.valueOf(0));
            }

            //hoa don chi tiet
            hoaDonJF.txtTienNgay.setText(String.valueOf(rts.getInt(23)));
            hoaDonJF.txtTienGio.setText(String.valueOf((rts.getInt(23) * 5) / 100));
            if (rts.getString(7).equals("Ngày")) {
                hoaDonJF.txtSoGio.setText(String.valueOf(0));
                hoaDonJF.txtSoNgay.setText(rts.getString(24));
            } else {
                hoaDonJF.txtSoNgay.setText(String.valueOf(0));
                hoaDonJF.txtSoGio.setText(rts.getString(22));
            }

            hoaDonJF.txtThanhTien.setText(String.valueOf((Integer.parseInt(hoaDonJF.txtTienNgay.getText()) * Integer.parseInt(hoaDonJF.txtSoNgay.getText()))
                    + (Integer.parseInt(hoaDonJF.txtTienGio.getText()) * Integer.parseInt(hoaDonJF.txtSoGio.getText()))));

//                hoaDonJF.txtTienDV.setText(String.valueOf(rts.getFloat(12)));
//                hoaDonJF.cboTienNgayLe.setSelectedItem(rts.getString(5));
//                hoaDonJF.txtTraMuon.setText(rts.getString(5));
//                hoaDonJF.cboKhuyenMai.setSelectedItem(rts.getString(5));
//                hoaDonJF.txtNhanPhongSom.setText(rts.getString(5));
//                hoaDonJF.txtGhiChu.setText(rts.getString(5));
//
            hoaDonJF.txtPhuThu.setText(String.valueOf(rts.getInt(10)));
            hoaDonJF.txtGiamGia.setText(String.valueOf(rts.getInt(11)));
            hoaDonJF.txtTraTruoc.setText(String.valueOf(rts.getInt(12)));
            hoaDonJF.cboLoaiThanhToan.setSelectedItem(rts.getString(13));
//
            Vector data1 = new Vector();
            data1.add(rts.getInt(15));
            data1.add(rts.getString(14));
            data1.add(rts.getString(8));
            data1.add(rts.getString(9));
            data1.add(rts.getString(21));
            data1.add(rts.getString(7));
            data1.add(rts.getString(22));
            data1.add(formatter.format(rts.getFloat(23)) + " đ");
            hoaDonJF.tblModel1.addRow(data1);
            hoaDonJF.lblMaHoaDon.setText(rts.getString(25));
            filldichvu(MaPhong);

            try {
                tongtien = (Integer.parseInt(hoaDonJF.txtThanhTien.getText()) + Integer.parseInt(hoaDonJF.txtTienDV.getText())
                        + Integer.parseInt(hoaDonJF.txtPhuThu.getText())
                        + Integer.parseInt(hoaDonJF.txtTraMuon.getText())
                        + Integer.parseInt(hoaDonJF.txtNhanPhongSom.getText())) - (Integer.parseInt(hoaDonJF.txtTraTruoc.getText()) + Integer.parseInt(hoaDonJF.txtGiamGia.getText()));
            } catch (Exception e) {
                System.out.println(e);
            }

            hoaDonJF.txtTongTien.setText(String.valueOf(formatter.format((tongtien))));
//            setTien(tongtien,MaPhong);

        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    public void filldichvu(String MaPhong) {
        try {
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            PreparedStatement stt = JdbcHelper.prepareStatement(SelectHoaDonCT, MaPhong);
            ResultSet rts = stt.executeQuery();
            int tongtiendv = 0;
            while (rts.next()) {

                // phong dich vu 
                Vector data1 = new Vector();
                data1.add(rts.getString(16));
                data1.add(rts.getString(17));
                data1.add(formatter.format(rts.getFloat(18)) + " đ");
                tongtiendv += (rts.getInt(18) * rts.getInt(19));
                hoaDonJF.txtTienDV.setText(String.valueOf(tongtiendv));
                data1.add(rts.getString(19));
                data1.add(formatter.format(rts.getInt(18) * rts.getInt(19)) + " đ");
                data1.add(rts.getString(20));
                hoaDonJF.tblModel2.addRow(data1);

            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    public void setTien(int tien, String maphong) {
        try {

            PreparedStatement stt = JdbcHelper.prepareStatement(setTien, tien, maphong);
            stt.executeQuery();

        } catch (Exception e) {

        }
    }

    public void setGhiChu(String GhiChu, String maphong) {
        try {

            PreparedStatement stt = JdbcHelper.prepareStatement(setGhiChu, GhiChu, maphong);
            stt.executeQuery();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int CountALLPhong() {
        int Tong = 0;
        try {
            String sql = "{call CountSLDP()}";
            PreparedStatement stt = JdbcHelper.prepareStatement(sql);
            ResultSet rts = stt.executeQuery();
            while (rts.next()) {
                Tong = rts.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        return Tong;
    }

    public int CountSLDPW() {
        int Tong = 0;
        try {
            String sql = "{call CountSLDPW()}";
            PreparedStatement stt = JdbcHelper.prepareStatement(sql);
            ResultSet rts = stt.executeQuery();
            while (rts.next()) {
                Tong = rts.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        return Tong;
    }

    public int CountSLDD(int Thu) {
        int Tong = 0;
        try {
            String sql = "{call CountSLTheoNgayHT(?)}";
            PreparedStatement stt = JdbcHelper.prepareStatement(sql);
            stt.setInt(1, Thu);
            ResultSet rts = stt.executeQuery();
            while (rts.next()) {
                Tong = rts.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        return Tong;
    }

    public int CountSLDDTT(int Thu) {
        int Tong = 0;
        try {
            String sql = "{call CountSLTheoNgayTT(?)}";
            PreparedStatement stt = JdbcHelper.prepareStatement(sql);
            stt.setInt(1, Thu);
            ResultSet rts = stt.executeQuery();
            while (rts.next()) {
                Tong = rts.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        return Tong;
    }

    @Override
    public void insert(HoaDonPhong entity) {
        try {
            PreparedStatement stt = JdbcHelper.prepareStatement(INSERT_SQL,
                    entity.getNgayNhanPhong(),
                    entity.getNgayTraPhong(),
                    entity.getMaNV(),
                    entity.getMaPhong(),
                    entity.getNgayXuatHoaDon(),
                    entity.getHinhThucThue(),
                    entity.getPhuThu(),
                    entity.getGiamGia(),
                    entity.getThanhToan(),
                    entity.getTraTruoc(),
                    entity.getGhiChu()
            );
            stt.executeQuery();
        } catch (Exception e) {

        }
    }

    @Override
    public void update(HoaDonPhong Entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<HoaDonPhong> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HoaDonPhong selectbyID(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<HoaDonPhong> selectbyID(String sql, Object... agrs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
