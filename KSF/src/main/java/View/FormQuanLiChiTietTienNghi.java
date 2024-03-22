/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View;

import java.awt.Color;
import java.awt.Component;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Dao.DaoPhong;
import Dao.TienNghiChiTietDao;
import Dao.TienNghiDao;
import Model.Phong;
import Model.TienNghiChiTiet;
import Utils.DialogHelper;
import Utils.Tableheader;

/**
 *
 * @author ngomi
 */
public class FormQuanLiChiTietTienNghi extends javax.swing.JDialog {

	TienNghiChiTietDao dao = new TienNghiChiTietDao();
	TienNghiDao tnDao = new TienNghiDao();
	DaoPhong pDao = new DaoPhong();
	int index = 0;
	DefaultTableModel model;
	private StringBuilder error = new StringBuilder();

	/**
	 * Creates new form QuanLiDichVuJDialog
	 */
	public FormQuanLiChiTietTienNghi(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.white);
		tblTienNghiChiTiet.setShowVerticalLines(false);
		tblTienNghiChiTiet.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Tableheader header = new Tableheader(value + "");
				return header;
			}
		});
		init();
	}

	public void init() {
		fillTable();
		setStatus(true);
		loadTienNghi();
	}

	public void fillTable() {
		model = (DefaultTableModel) tblTienNghiChiTiet.getModel();
		model.setRowCount(0);
		try {
			List<TienNghiChiTiet> list = (List<TienNghiChiTiet>) dao.selectAll();
			for (TienNghiChiTiet tnct : list) {
				Object rows[] = { tnct.getMaTienNghi(), tnct.getMaPhong(), tnct.getSoLuong() };
				model.addRow(rows);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void search() {
		model = (DefaultTableModel) tblTienNghiChiTiet.getModel();
		model.setRowCount(0);
		try {
			String key = txtTim.getText();
			List<TienNghiChiTiet> list = (List<TienNghiChiTiet>) dao.selectByAll(key);
			for (TienNghiChiTiet tnct : list) {
				Object rows[] = { tnct.getMaTienNghi(), tnct.getMaPhong(), tnct.getSoLuong() };
				model.addRow(rows);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void loadTienNghi() {
		DefaultComboBoxModel model = (DefaultComboBoxModel) cboTenTienNghi.getModel();
		model.removeAllElements();
		try {
			List<Model.TienNghi> list = tnDao.selectAll();
			for (Model.TienNghi tienNghi : list) {
				model.addElement(tienNghi);
			}
		} catch (Exception e) {
		}
	}

	public void setModel(TienNghiChiTiet tnct) {

		cboTenTienNghi.setSelectedIndex(tnct.getMaTienNghi() - 1);
		txtMaPhong.setText(tnct.getMaPhong());
		txtSoLuong.setText(String.valueOf(tnct.getSoLuong()));
	}

	public TienNghiChiTiet getModel() {
		TienNghiChiTiet tnct = new TienNghiChiTiet();
		Model.TienNghi tn = (Model.TienNghi) cboTenTienNghi.getSelectedItem();
		tnct.setMaTienNghi(tn.getMaTienNghi());
		tnct.setMaPhong(txtMaPhong.getText());
		tnct.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
		return tnct;
	}

	public void setStatus(boolean bl) {
		btnThem.setEnabled(bl);
		btnSua.setEnabled(!bl);
		btnXoa.setEnabled(!bl);
	}

	public void edit() {
		try {
			int maTienNghi = (int) tblTienNghiChiTiet.getValueAt(index, 0);
			String maPhong = (String) tblTienNghiChiTiet.getValueAt(index, 1);
			TienNghiChiTiet tn = dao.selectByTwo(maTienNghi, maPhong);
			if (tn != null) {
				setModel(tn);
				setStatus(false);
				tab.setSelectedIndex(0);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void clear() {
		setModel(new TienNghiChiTiet());
		setStatus(true);
	}

	public boolean validates() {

		if (txtMaPhong.getText().trim().isEmpty()) {
			error.append("Chưa nhập mã phòng\n");
			txtMaPhong.requestFocus();
			return false;
		}

		Phong maPhong = pDao.selectbyID(txtMaPhong.getText());
		if (maPhong == null) {
			DialogHelper.alert(this, "Mã phòng không tồn tại");
			txtMaPhong.requestFocus();
			return false;
		}

		if (txtSoLuong.getText().trim().isEmpty()) {
			error.append("Chưa nhập số lượng\n");
			txtSoLuong.requestFocus();
			return false;
		}

		try {
			float soLuong = Float.parseFloat(txtSoLuong.getText());
			if (soLuong <= 0) {
				error.append("Số lượng phải lớn hơn 0\n");
				txtSoLuong.requestFocus();
				return false;
			}
		} catch (Exception e) {
			error.append("Số lượng không hợp lệ\n");
			txtSoLuong.requestFocus();
			return false;
		}

		return true;
	}

	public void insert() {
		if (validates()) {
			Model.TienNghi tn = (Model.TienNghi) cboTenTienNghi.getSelectedItem();
			String maPhong = txtMaPhong.getText();
			TienNghiChiTiet tnct = dao.checkInsert(tn.getMaTienNghi(), maPhong);
			if (tnct != null) {
				DialogHelper.alert(this, "Tiện nghi đã tồn tồn tại trong phòng " + maPhong);
			} else {
				TienNghiChiTiet model = getModel();
				try {
					dao.insert(model);
					DialogHelper.alert(this, "Thêm thành công");
					fillTable();
				} catch (Exception e) {
					DialogHelper.alert(this, "Thêm thất bại");
				}
			}
		}
	}

	public void update() {
		if (validates()) {
			TienNghiChiTiet ldv = getModel();
			try {
				dao.update(ldv);
				DialogHelper.alert(this, "Cập nhật Thành công");
				fillTable();
			} catch (Exception e) {
				DialogHelper.alert(this, "Cập nhật thát bại");
			}
		}
	}

	public void delete() {
		if (DialogHelper.confirm(this, "Bạn có chắc chắn muốn xóa tiện nghi chi tiết này này")) {
			Model.TienNghi tn = (Model.TienNghi) cboTenTienNghi.getSelectedItem();
			String p = txtMaPhong.getText();
			try {
				dao.deleteTwo(tn.getMaTienNghi(), p);
				DialogHelper.alert(this, "Xóa thành công");
				fillTable();
				this.clear();
			} catch (Exception e) {
				DialogHelper.alert(this, "Xóa thất bại");
				throw new RuntimeException(e);

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

		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		jLabel1 = new javax.swing.JLabel();
		tab = new javax.swing.JTabbedPane();
		jPanel1 = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		txtSoLuong = new javax.swing.JTextField();
		btnMoi = new javax.swing.JButton();
		btnSua = new javax.swing.JButton();
		btnXoa = new javax.swing.JButton();
		btnThem = new javax.swing.JButton();
		cboTenTienNghi = new javax.swing.JComboBox<>();
		txtMaPhong = new javax.swing.JTextField();
		jPanel2 = new javax.swing.JPanel();
		jScrollPane2 = new javax.swing.JScrollPane();
		tblTienNghiChiTiet = new javax.swing.JTable();
		txtTim = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();

		jTable1.setModel(
				new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null } },
						new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		jScrollPane1.setViewportView(jTable1);

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel1.setText("Chi Tiết Tiện Nghi");

		tab.setBackground(new java.awt.Color(255, 255, 255));

		jPanel1.setBackground(new java.awt.Color(255, 255, 255));

		jLabel2.setText("Tên tiện nghi");

		jLabel4.setText("Mã phòng");

		jLabel5.setText("Số lượng");

		btnMoi.setBackground(new java.awt.Color(242, 242, 242));
		btnMoi.setText("Mới");
		btnMoi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
		btnMoi.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnMoiActionPerformed(evt);
			}
		});

		btnSua.setBackground(new java.awt.Color(242, 242, 242));
		btnSua.setText("Sữa");
		btnSua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
		btnSua.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSuaActionPerformed(evt);
			}
		});

		btnXoa.setBackground(new java.awt.Color(242, 242, 242));
		btnXoa.setText("Xóa");
		btnXoa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
		btnXoa.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnXoaActionPerformed(evt);
			}
		});

		btnThem.setBackground(new java.awt.Color(242, 242, 242));
		btnThem.setText("Thêm");
		btnThem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
		btnThem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnThemActionPerformed(evt);
			}
		});

		cboTenTienNghi.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
						.addGap(28, 28, 28)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(jLabel4).addComponent(jLabel2).addComponent(jLabel5))
						.addGap(18, 18, 18)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(jPanel1Layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
												.addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 174,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addComponent(cboTenTienNghi, 0, javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))
										.addGap(43, 43, 43))
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addComponent(txtMaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 174,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(20, 20, 20)
						.addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 67,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(5, 5, 5)
						.addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 67,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(5, 5, 5)
						.addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 67,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 67,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(23, 23, 23)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel2)
								.addComponent(cboTenTienNghi, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(22, 22, 22)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel4).addComponent(txtMaPhong, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(25, 25, 25)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel5))
						.addGap(18, 18, 18)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 29,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 29,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 29,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 29,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(22, Short.MAX_VALUE)));

		tab.addTab("Chi tiết", jPanel1);

		jPanel2.setBackground(new java.awt.Color(255, 255, 255));

		tblTienNghiChiTiet
				.setModel(
						new javax.swing.table.DefaultTableModel(
								new Object[][] { { null, null, null }, { null, null, null }, { null, null, null },
										{ null, null, null } },
								new String[] { "Mã Tiện nghi", "Mã phòng", "Số lượng" }));
		tblTienNghiChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				tblTienNghiChiTietMousePressed(evt);
			}
		});
		jScrollPane2.setViewportView(tblTienNghiChiTiet);

		txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				txtTimKeyReleased(evt);
			}
		});

		jLabel3.setText("Tìm kiếm");

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										jPanel2Layout.createSequentialGroup().addComponent(jLabel3)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(txtTim)))
						.addContainerGap()));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
						.addGap(8, 8, 8)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel3).addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(8, 8, 8)
						.addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
						.addContainerGap()));

		tab.addTab("Thông tin", jPanel2);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(tab).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel1)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addComponent(jLabel1)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(tab)
						.addGap(0, 0, 0)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnMoiActionPerformed
		clear();
	}// GEN-LAST:event_btnMoiActionPerformed

	private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSuaActionPerformed
		update();
	}// GEN-LAST:event_btnSuaActionPerformed

	private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnXoaActionPerformed
		delete();
	}// GEN-LAST:event_btnXoaActionPerformed

	private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnThemActionPerformed
		insert();
	}// GEN-LAST:event_btnThemActionPerformed

	private void tblTienNghiChiTietMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tblTienNghiChiTietMousePressed
		if (index >= 0) {
			index = tblTienNghiChiTiet.getSelectedRow();
			edit();
		}
	}// GEN-LAST:event_tblTienNghiChiTietMousePressed

	private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtTimKeyReleased
		if (txtTim.getText().isEmpty()) {
			fillTable();
		} else {
			search();
		}
	}// GEN-LAST:event_txtTimKeyReleased

	/**
	 * @param args the command line arguments
	 */

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnMoi;
	private javax.swing.JButton btnSua;
	private javax.swing.JButton btnThem;
	private javax.swing.JButton btnXoa;
	private javax.swing.JComboBox<String> cboTenTienNghi;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTable jTable1;
	private javax.swing.JTabbedPane tab;
	private javax.swing.JTable tblTienNghiChiTiet;
	private javax.swing.JTextField txtMaPhong;
	private javax.swing.JTextField txtSoLuong;
	private javax.swing.JTextField txtTim;
	// End of variables declaration//GEN-END:variables
}
