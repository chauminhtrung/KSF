/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Utils.DialogHelper;
import View.FormTrangChu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Admin
 */
public class ControllerTrangChu implements ActionListener {

    private FormTrangChu formTrangChu;

    public ControllerTrangChu(FormTrangChu formTrangChu) {
        this.formTrangChu = formTrangChu;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();

        switch (src) {
            case "In File":
                try {

                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("Thống Kê Doanh Thu");
                XSSFRow row = null;
                Cell cell = null;
                row = sheet.createRow(1);

                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("Họ Và Tên");

                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue("Ngày Nhận Phòng");

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue("Ngày Trả Phòng");

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue("Loại Phòng");

                for (int i = 0; i < formTrangChu.daoKhachHang.ListKHGD.size(); i++) {
                    row = sheet.createRow(2 + i);

                    cell = row.createCell(0, CellType.STRING);
                    cell.setCellValue(formTrangChu.daoKhachHang.ListKHGD.get(i).getHoten());

                    cell = row.createCell(1, CellType.STRING);
                    cell.setCellValue(formTrangChu.daoKhachHang.ListKHGD.get(i).getCheckIn());

                    cell = row.createCell(2, CellType.STRING);
                    cell.setCellValue(formTrangChu.daoKhachHang.ListKHGD.get(i).getCheckOut());

                    cell = row.createCell(3, CellType.STRING);
                    cell.setCellValue(formTrangChu.daoKhachHang.ListKHGD.get(i).getLoaiPhong());

                }
                try {
                    JFileChooser fileChooser = new JFileChooser();
                    int result = fileChooser.showOpenDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        try {
                            File f = fileChooser.getSelectedFile();
                            FileOutputStream fis = new FileOutputStream(f);
                            workbook.write(fis);
                            fis.close();
                            DialogHelper.alert(formTrangChu, "In Thành Công");
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    }

                } catch (Exception exx) {
                    System.out.println(exx);
                }

            } catch (Exception exxx) {
                System.out.println(exxx);
            }

            break;
            case "Tuần":

                break;
            case "Tháng":

                break;
            default:
                throw new AssertionError();
        }
    }

}
