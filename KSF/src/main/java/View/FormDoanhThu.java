/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.PropertyConfigurator;

import com.itextpdf.text.Document;

import Model.NhanVien;
import PanelChart.ModelChart.chart.ModelChart;
import Utils.DialogHelper;
import Utils.JdbcHelper;
import Utils.Log4j;
import Utils.PDFGenerator;

/**
 *
 * @author Admin
 */
public class FormDoanhThu extends javax.swing.JPanel {

	String user = "sa";
	String pas = "123";
	String url = "jdbc:sqlserver://localhost;databaseName=Dulieu;encrypt=false";
	Connection con = null;
	Statement st = null;
	ResultSet rts;
	DecimalFormat formatter = new DecimalFormat("###,###,###");
	private String column[] = { "Mã Nhân Viên", "Mã Khách Hàng", "Ngày Xuất", "Tổng Tiền" };
	private DefaultTableModel tblModel = new DefaultTableModel(column, 0);
	NhanVien nhanVien = new NhanVien();

	public FormDoanhThu() {
		initComponents();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, user, pas);
		} catch (Exception e) {
			System.out.println(e);
		}
		init();
	}

	private void init() {
		chart1.addLegend("Năm trước", new Color(12, 84, 175), new Color(0, 108, 247));
		chart1.addLegend("Năm nay", new Color(54, 4, 143), new Color(104, 49, 200));
		DatacboYear();
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);

		chart1.addData(new ModelChart("Tháng 1", new double[] { DoanhThuThang(1, year), DoanhThuThang(1, year - 1) }));
		chart1.addData(new ModelChart("Tháng 2", new double[] { DoanhThuThang(2, year), DoanhThuThang(2, year - 1) }));
		chart1.addData(new ModelChart("Tháng 3", new double[] { DoanhThuThang(3, year), DoanhThuThang(3, year - 1) }));
		chart1.addData(new ModelChart("Tháng 4", new double[] { DoanhThuThang(4, year), DoanhThuThang(4, year - 1) }));
		chart1.addData(new ModelChart("Tháng 5", new double[] { DoanhThuThang(5, year), DoanhThuThang(5, year - 1) }));
		chart1.addData(new ModelChart("Tháng 6", new double[] { DoanhThuThang(6, year), DoanhThuThang(6, year - 1) }));
		chart1.addData(new ModelChart("Tháng 7", new double[] { DoanhThuThang(7, year), DoanhThuThang(7, year - 1) }));
		chart1.addData(new ModelChart("Tháng 8", new double[] { DoanhThuThang(8, year), DoanhThuThang(8, year - 1) }));
		chart1.addData(new ModelChart("Tháng 9", new double[] { DoanhThuThang(9, year), DoanhThuThang(9, year - 1) }));
		chart1.addData(
				new ModelChart("Tháng 10", new double[] { DoanhThuThang(10, year), DoanhThuThang(10, year - 1) }));
		chart1.addData(
				new ModelChart("Tháng 11", new double[] { DoanhThuThang(11, year), DoanhThuThang(11, year - 1) }));
		chart1.addData(
				new ModelChart("Tháng 12", new double[] { DoanhThuThang(12, year), DoanhThuThang(12, year - 1) }));
		chart1.start();
		PropertyConfigurator.configure("D:\\HK4\\DUAN_1\\Duan1\\KSF\\src\\Log\\log4j.properties");
		progress1.start();
		progress2.start();
		progress3.start();
		Table();
		setSoLieu(year);
		jdate1.setVisible(false);
		jdate2.setVisible(false);
		daugach.setVisible(false);
		btnTim.setVisible(false);
	}

	public void Table() {
		tblThongKe.setModel(tblModel);
		filltable();
	}

	public void filltable() {
		try {

			String sql1 = "SELECT MaNV,MaKH,NgayXuat,TongTien FROM HoaDonPhong INNER JOIN HoaDonChiTiet\n"
					+ " ON HoaDonPhong.MaHoaDon = HoaDonChiTiet.MaHoaDon";
			st = con.createStatement();
			rts = st.executeQuery(sql1);
			tblModel.setRowCount(0);
			while (rts.next()) {
				Vector data = new Vector();
				data.add(rts.getString(1));
				data.add(rts.getString(2));
				data.add(rts.getString(3));
				data.add(formatter.format(rts.getInt(4)));

				tblModel.addRow(data);
			}
		} catch (Exception e1) {
			System.out.println(e1);
		}
	}

	public int DoanhThuThang(int Thang, int Nam) {
		int Tong = 0;
		try {
			String sql = "{call DoanhThuThang(?,?)}";
			PreparedStatement stt = JdbcHelper.prepareStatement(sql);
			stt.setInt(1, Thang);
			stt.setInt(2, Nam);
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

	void DatacboYear() {
		try {
			String sql1 = "SELECT  YEAR(NgayXuat) FROM HoaDonChiTiet\n" + "GROUP BY YEAR(NgayXuat) ";
			st = con.createStatement();
			rts = st.executeQuery(sql1);
			cboYear.removeAllItems();
			while (rts.next()) {
				cboYear.addItem(rts.getString(1));
			}
		} catch (Exception e1) {
			System.out.println(e1);
		}
	}

	public void setSoLieu(int year) {
		// proress 1

		try {
			String sql1 = "select sum(Tien) AS 'Tien Phong' from TongTienPhong WHERE Nam = ?";
			PreparedStatement stt = con.prepareStatement(sql1);
			stt.setInt(1, year);
			rts = stt.executeQuery();
			while (rts.next()) {
				float TienPhong = rts.getFloat(1);
				progress1.setValue(Math.round((TienPhong / 50000000) * 100));
			}
		} catch (Exception e1) {
			System.out.println(e1);
		}
		// proress 2
		try {
			String sql2 = "select sum(Tien) AS 'Tien DV ' from TongTienDichVu WHERE Nam = ?";
			PreparedStatement stt = con.prepareStatement(sql2);
			stt.setInt(1, year);
			rts = stt.executeQuery();
			while (rts.next()) {
				float TienDichVu = rts.getFloat(1);
				progress2.setValue(Math.round((TienDichVu / 50000000) * 100));
			}
		} catch (Exception e2) {
			System.out.println(e2);
		}
		// proress 3
		try {
			String sql3 = "SELECT YEAR(NgayXuat),sum(TongTien) FROM HoaDonPhong INNER JOIN HoaDonChiTiet\n"
					+ "ON HoaDonPhong.MaHoaDon = HoaDonChiTiet.MaHoaDon\n"
					+ "group by YEAR(NgayXuat) Having YEAR(NgayXuat) = ?";
			PreparedStatement stt = con.prepareStatement(sql3);
			stt.setInt(1, year);
			rts = stt.executeQuery();
			while (rts.next()) {
				float Tongtien = rts.getFloat(2);
				progress3.setValue(Math.round((Tongtien / 200000000) * 100));
			}
		} catch (Exception e1) {
			System.out.println(e1);
		}

	}

	private void chooseDirectoryToSave() {
		JFileChooser choose = new JFileChooser();
		int x = choose.showSaveDialog(null);
		if (x == JFileChooser.APPROVE_OPTION) {
			try {
				String file = choose.getSelectedFile().getAbsolutePath().toString() + ".pdf";
				File f = new File(file);
				FileOutputStream outFile = new FileOutputStream(file);
				Document document = PDFGenerator.generateDoanhThuToPDF(tblThongKe, outFile);
				document.close();
				outFile.close();

				if (DialogHelper.confirm(this, "Xuất tệp PDF thành công! Bạn có muốn mở lên không")) {
					Desktop.getDesktop().browse(f.toURI());
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		cboYear = new javax.swing.JComboBox<>();
		panel1 = new Utils.Panel();
		panel4 = new Utils.Panel();
		progress3 = new PanelChart.Progress();
		panel5 = new Utils.Panel();
		progress1 = new PanelChart.Progress();
		panel7 = new Utils.Panel();
		progress2 = new PanelChart.Progress();
		jLabel4 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		panel2 = new Utils.Panel();
		scrollPaneWin111 = new Utils.ScrollPaneWin11();
		jScrollPane1 = new javax.swing.JScrollPane();
		tblThongKe = new Utils.Table();
		panel3 = new Utils.Panel();
		chart1 = new PanelChart.ModelChart.chart.Chart();
		btnExcel = new Utils.Button();
		btnWorld = new Utils.Button();
		btnPDF = new Utils.Button();
		btnTim = new Utils.Button();
		jdate2 = new com.toedter.calendar.JDateChooser();
		jdate1 = new com.toedter.calendar.JDateChooser();
		daugach = new javax.swing.JLabel();
		cboHinhThuc = new javax.swing.JComboBox<>();

		setOpaque(false);

		cboYear.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
		cboYear.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				cboYearItemStateChanged(evt);
			}
		});

		panel1.setBackground(new java.awt.Color(51, 51, 51));
		panel1.setOpaque(true);
		panel1.setRoundBottomLeft(10);
		panel1.setRoundBottomRight(10);
		panel1.setRoundTopLeft(10);
		panel1.setRoundTopRight(10);

		panel4.setBackground(new java.awt.Color(51, 51, 51));

		progress3.setBackground(new java.awt.Color(117, 255, 119));
		progress3.setForeground(new java.awt.Color(255, 102, 102));
		progress3.setValue(34);

		javax.swing.GroupLayout panel4Layout = new javax.swing.GroupLayout(panel4);
		panel4.setLayout(panel4Layout);
		panel4Layout.setHorizontalGroup(panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panel4Layout.createSequentialGroup().addContainerGap()
						.addComponent(progress3, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
						.addContainerGap()));
		panel4Layout.setVerticalGroup(panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						panel4Layout.createSequentialGroup().addContainerGap().addComponent(progress3,
								javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)));

		panel5.setBackground(new java.awt.Color(51, 51, 51));

		progress1.setBackground(new java.awt.Color(117, 255, 119));
		progress1.setForeground(new java.awt.Color(102, 102, 255));
		progress1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

		javax.swing.GroupLayout panel5Layout = new javax.swing.GroupLayout(panel5);
		panel5.setLayout(panel5Layout);
		panel5Layout.setHorizontalGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 180, Short.MAX_VALUE)
				.addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						panel5Layout.createSequentialGroup().addContainerGap()
								.addComponent(progress1, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
								.addContainerGap())));
		panel5Layout.setVerticalGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 154, Short.MAX_VALUE)
				.addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
						progress1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE,
						154, Short.MAX_VALUE)));

		panel7.setBackground(new java.awt.Color(51, 51, 51));

		progress2.setBackground(new java.awt.Color(117, 255, 119));
		progress2.setForeground(new java.awt.Color(153, 153, 255));
		progress2.setValue(34);
		progress2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

		javax.swing.GroupLayout panel7Layout = new javax.swing.GroupLayout(panel7);
		panel7.setLayout(panel7Layout);
		panel7Layout.setHorizontalGroup(panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(progress2, javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE));
		panel7Layout.setVerticalGroup(panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						panel7Layout.createSequentialGroup()
								.addComponent(progress2, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
								.addContainerGap()));

		jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel4.setForeground(new java.awt.Color(255, 255, 255));
		jLabel4.setText("Báo cáo tháng");

		jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jLabel2.setForeground(new java.awt.Color(255, 255, 255));
		jLabel2.setText("Doanh thu phòng");

		jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jLabel3.setForeground(new java.awt.Color(255, 255, 255));
		jLabel3.setText("Tổng doanh thu");

		jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jLabel5.setForeground(new java.awt.Color(255, 255, 255));
		jLabel5.setText("Dịch vụ");

		javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
		panel1.setLayout(panel1Layout);
		panel1Layout.setHorizontalGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panel1Layout.createSequentialGroup().addContainerGap().addComponent(jLabel4)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						panel1Layout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panel7, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(45, 45, 45)
								.addComponent(panel4, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(30, 30, 30))
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
						.addGap(68, 68, 68).addComponent(jLabel2)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
						.addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 75,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(120, 120, 120)
						.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 115,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(43, 43, 43))
				.addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(panel1Layout.createSequentialGroup().addGap(28, 28, 28)
								.addComponent(panel5, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(461, Short.MAX_VALUE))));
		panel1Layout.setVerticalGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
						.addContainerGap().addComponent(jLabel4).addGap(18, 18, 18)
						.addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel2).addComponent(jLabel3).addComponent(jLabel5))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(panel7, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(panel4, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
				.addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						panel1Layout.createSequentialGroup().addContainerGap(68, Short.MAX_VALUE)
								.addComponent(panel5, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap())));

		tblThongKe
				.setModel(new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null } },
						new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		jScrollPane1.setViewportView(tblThongKe);

		scrollPaneWin111.setViewportView(jScrollPane1);

		javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
		panel2.setLayout(panel2Layout);
		panel2Layout.setHorizontalGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(scrollPaneWin111, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE));
		panel2Layout.setVerticalGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panel2Layout.createSequentialGroup().addComponent(scrollPaneWin111,
						javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 4, Short.MAX_VALUE)));

		panel3.setOpaque(true);

		chart1.setBackground(new java.awt.Color(51, 51, 51));
		chart1.setOpaque(true);

		javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
		panel3.setLayout(panel3Layout);
		panel3Layout.setHorizontalGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(chart1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		panel3Layout.setVerticalGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(chart1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE,
						410, Short.MAX_VALUE));

		btnExcel.setText("EXCEL");
		btnExcel.setBorderColor(new java.awt.Color(153, 255, 153));

		btnWorld.setText("WORD");
		btnWorld.setBorderColor(new java.awt.Color(153, 153, 255));
		btnWorld.setColorClick(new java.awt.Color(102, 102, 255));
		btnWorld.setColorOver(new java.awt.Color(102, 102, 255));

		btnPDF.setText("PDF");
		btnPDF.setBorderColor(new java.awt.Color(255, 153, 51));
		btnPDF.setColorClick(new java.awt.Color(255, 204, 102));
		btnPDF.setColorOver(new java.awt.Color(255, 153, 51));
		btnPDF.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnPDFActionPerformed(evt);
			}
		});

		btnTim.setForeground(new java.awt.Color(51, 51, 51));
		btnTim.setText("Tìm");
		btnTim.setBorderColor(new java.awt.Color(255, 102, 102));
		btnTim.setColorClick(new java.awt.Color(255, 204, 204));
		btnTim.setColorOver(new java.awt.Color(255, 51, 51));
		btnTim.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		btnTim.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnTimActionPerformed(evt);
			}
		});

		jdate2.setDateFormatString("dd-MM-yyyy");

		jdate1.setDateFormatString("dd-MM-yyyy");

		daugach.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		daugach.setText("_");

		cboHinhThuc.setModel(new javax.swing.DefaultComboBoxModel<>(
				new String[] { "Bảng thống kê", "Năm", "Ngày", "Tháng", "Khoảng Ngày" }));
		cboHinhThuc.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cboHinhThucActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addGap(6, 6, 6)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addContainerGap())
						.addGroup(layout.createSequentialGroup().addGroup(layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cboYear, javax.swing.GroupLayout.PREFERRED_SIZE, 398,
										javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGap(6, 6, 6))
										.addGroup(layout.createSequentialGroup()
												.addComponent(cboHinhThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 225,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(jdate1, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(daugach, javax.swing.GroupLayout.PREFERRED_SIZE, 13,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jdate2, javax.swing.GroupLayout.PREFERRED_SIZE, 140,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 97,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addContainerGap())))))
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 94,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(btnWorld, javax.swing.GroupLayout.PREFERRED_SIZE, 94,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(5, 5, 5).addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 94,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(16, 16, 16)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(4, 4, 4)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(cboHinhThuc)
												.addComponent(jdate1, javax.swing.GroupLayout.Alignment.TRAILING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jdate2, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnTim, javax.swing.GroupLayout.Alignment.TRAILING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(cboYear, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(daugach))
										.addGap(0, 3, Short.MAX_VALUE)))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btnWorld, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btnPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(panel3,
								javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)));
	}// </editor-fold>//GEN-END:initComponents

	private void cboYearItemStateChanged(java.awt.event.ItemEvent evt) {// GEN-FIRST:event_cboYearItemStateChanged
		String nam = (String) cboYear.getSelectedItem();
		if (nam == null) {
			System.out.println("aaaa");
		} else {
			setSoLieu(Integer.parseInt(nam));
			progress1.start();
			progress2.start();
			progress3.start();
		}

	}// GEN-LAST:event_cboYearItemStateChanged

	private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnTimActionPerformed

		try {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(jdate1.getDate());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			if (cboHinhThuc.getSelectedIndex() == 4) {
				String date1 = format.format(jdate1.getDate());
				String date12 = format.format(jdate2.getDate());
				TimTheoKhoangNgay(date1, date12);

			} else if (cboHinhThuc.getSelectedIndex() == 1) {
				TimTheoNam(calendar.get(Calendar.YEAR));
			} else if (cboHinhThuc.getSelectedIndex() == 2) {

				TimTheoNgay(calendar.get(Calendar.DAY_OF_MONTH));
			} else if (cboHinhThuc.getSelectedIndex() == 3) {
				TimTheoThang(calendar.get(Calendar.MONTH) + 1);
			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Chưa chọn thời gian !!! ");
		}

	}// GEN-LAST:event_btnTimActionPerformed

	private void cboHinhThucActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cboHinhThucActionPerformed
		if (cboHinhThuc.getSelectedIndex() == 4) {
			jdate1.setVisible(true);
			jdate2.setVisible(true);
			daugach.setVisible(true);
			btnTim.setVisible(true);
		} else if (cboHinhThuc.getSelectedIndex() != 0) {
			jdate1.setVisible(true);
			jdate2.setVisible(false);
			daugach.setVisible(false);
			btnTim.setVisible(true);
		} else {
			jdate1.setVisible(false);
			jdate2.setVisible(false);
			daugach.setVisible(false);
			btnTim.setVisible(false);
			filltable();
		}
	}// GEN-LAST:event_cboHinhThucActionPerformed

	private void btnPDFActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnPDFActionPerformed
		chooseDirectoryToSave();
		Log4j.logger.info("Nhân Viên: " + nhanVien.getMaNV() + " Đã xuất file PDF doanh thu");
	}// GEN-LAST:event_btnPDFActionPerformed

	public void TimTheoKhoangNgay(String NgayBatDau, String NgayKetThuc) {
		try {

			String sql = "SELECT MaNV,MaKH,NgayXuat,TongTien FROM HoaDonPhong INNER JOIN HoaDonChiTiet\n"
					+ "ON HoaDonPhong.MaHoaDon = HoaDonChiTiet.MaHoaDon \n" + "WHERE NgayXuat BETWEEN ? AND ?";
			PreparedStatement stt = con.prepareStatement(sql);
			stt.setString(1, NgayBatDau);
			stt.setString(2, NgayKetThuc);
			rts = stt.executeQuery();
			tblModel.setRowCount(0);
			while (rts.next()) {
				Vector data = new Vector();
				data.add(rts.getString(1));
				data.add(rts.getString(2));
				data.add(rts.getString(3));
				data.add(formatter.format(rts.getInt(4)));
				tblModel.addRow(data);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void TimTheoNam(int Nam) {
		try {
			String sql = "SELECT MaNV,MaKH,NgayXuat,TongTien FROM HoaDonPhong INNER JOIN HoaDonChiTiet\n"
					+ "ON HoaDonPhong.MaHoaDon = HoaDonChiTiet.MaHoaDon \n" + "WHERE year(NgayXuat) = ? ";
			PreparedStatement stt = con.prepareStatement(sql);
			stt.setInt(1, Nam);
			rts = stt.executeQuery();
			tblModel.setRowCount(0);
			while (rts.next()) {
				Vector data = new Vector();
				data.add(rts.getString(1));
				data.add(rts.getString(2));
				data.add(rts.getString(3));
				data.add(formatter.format(rts.getInt(4)));
				tblModel.addRow(data);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void TimTheoThang(int Thang) {
		try {
			String sql = "SELECT MaNV,MaKH,NgayXuat,TongTien FROM HoaDonPhong INNER JOIN HoaDonChiTiet\n"
					+ "ON HoaDonPhong.MaHoaDon = HoaDonChiTiet.MaHoaDon \n" + "WHERE MONTH(NgayXuat) = ?  ";
			PreparedStatement stt = con.prepareStatement(sql);
			stt.setInt(1, Thang);
			rts = stt.executeQuery();
			tblModel.setRowCount(0);
			while (rts.next()) {
				Vector data = new Vector();
				data.add(rts.getString(1));
				data.add(rts.getString(2));
				data.add(rts.getString(3));
				data.add(formatter.format(rts.getInt(4)));
				tblModel.addRow(data);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void TimTheoNgay(int Ngay) {
		try {
			String sql = "SELECT MaNV,MaKH,NgayXuat,TongTien FROM HoaDonPhong INNER JOIN HoaDonChiTiet\n"
					+ "ON HoaDonPhong.MaHoaDon = HoaDonChiTiet.MaHoaDon \n" + "WHERE DAY(NgayXuat) = ?  ";
			PreparedStatement stt = con.prepareStatement(sql);
			stt.setInt(1, Ngay);
			rts = stt.executeQuery();
			tblModel.setRowCount(0);
			while (rts.next()) {
				Vector data = new Vector();
				data.add(rts.getString(1));
				data.add(rts.getString(2));
				data.add(rts.getString(3));
				data.add(formatter.format(rts.getInt(4)));
				tblModel.addRow(data);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private Utils.Button btnExcel;
	private Utils.Button btnPDF;
	private Utils.Button btnTim;
	private Utils.Button btnWorld;
	private javax.swing.JComboBox<String> cboHinhThuc;
	private javax.swing.JComboBox<String> cboYear;
	private PanelChart.ModelChart.chart.Chart chart1;
	private javax.swing.JLabel daugach;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JScrollPane jScrollPane1;
	private com.toedter.calendar.JDateChooser jdate1;
	private com.toedter.calendar.JDateChooser jdate2;
	private Utils.Panel panel1;
	private Utils.Panel panel2;
	private Utils.Panel panel3;
	private Utils.Panel panel4;
	private Utils.Panel panel5;
	private Utils.Panel panel7;
	private PanelChart.Progress progress1;
	private PanelChart.Progress progress2;
	private PanelChart.Progress progress3;
	private Utils.ScrollPaneWin11 scrollPaneWin111;
	private Utils.Table tblThongKe;
	// End of variables declaration//GEN-END:variables
}
