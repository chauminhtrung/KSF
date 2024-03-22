/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import Dao.DaoNhanVien;
import Model.NhanVien;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author Administrator
 */
public class PDFGenerator {
    public static Document generateDoanhThuToPDF(JTable tblThongKe, FileOutputStream outFile)
            throws SQLException, DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, outFile);
        document.open();

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
        //Font chữ cho heading
        BaseFont baseFont = BaseFont.createFont("src\\Roboto\\Roboto-Black.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font headingFont = new Font(baseFont, 12);
        PdfPCell cell;       
        // Số thứ tự
        cell = new PdfPCell(new Phrase("Mã Nhân Viên", headingFont));
        table.addCell(cell);

        // Mã nhân viên
        cell = new PdfPCell(new Phrase("Mã Khách Hàng", headingFont));
        table.addCell(cell);

        // Họ tên
        cell = new PdfPCell(new Phrase("Ngày Xuất", headingFont));
        table.addCell(cell);

        table.addCell(cell);        
        //Font chữ cho các ô khác
        BaseFont baseFontCell = BaseFont.createFont("src\\Roboto\\Roboto-Light.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font cellFont = new Font(baseFontCell, 12);        

        for (int i = 0; i < tblThongKe.getRowCount(); i++) {
            // Số thứ tự
            table.addCell(new PdfPCell(new Phrase((String)tblThongKe.getValueAt(i, 0), cellFont)));
            // Mã nhân viên
            table.addCell(new PdfPCell(new Phrase((String) tblThongKe.getValueAt(i, 1), cellFont)));
            // Họ và tên
            table.addCell(new PdfPCell(new Phrase((String) tblThongKe.getValueAt(i, 2), cellFont)));
            // Giới tính
            table.addCell(new PdfPCell(new Phrase(String.valueOf(tblThongKe.getValueAt(i, 3)), cellFont)));
            //Email
            
        }
        document.add(table);
        return document;
    }
}
