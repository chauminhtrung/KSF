/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Dao.DaoDichVu;
import Dao.LoaiDichVuDao;
import Model.LoaiDichVu;
import Utils.DialogHelper;
import Utils.Tableheader;

/**
 *
 * @author ngomi
 */
public class FormDichVu extends javax.swing.JPanel {

	DaoDichVu dvDao = new DaoDichVu();
	LoaiDichVuDao ldvDao = new LoaiDichVuDao();
	int index = 0;
	DefaultTableModel model;
	private List<LoaiDichVu> danhSachLoaiDichVu = new ArrayList<>();

	public FormDichVu() {
		initComponents();
		tblDichVu.setShowHorizontalLines(false);
		tblDichVu.setBorder(null);
		tblDichVu.setRowHeight(40);

		tblDichVu.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
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
		loadLoaiDichVu();
		setStatus(true);
		countAll();
	}

	public void fillTable() {
		model = (DefaultTableModel) tblDichVu.getModel();
		model.setRowCount(0);
		try {
			String key = txtTim.getText();
			List<Model.DichVu> list = (List<Model.DichVu>) dvDao.selectByAll(key);
			for (Model.DichVu dv : list) {
				Object rows[] = { dv.getMaDichVu(), dv.getMaLoaiDichVu(), dv.getDonGia(), dv.getMoTa() };
				model.addRow(rows);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void loadLoaiDichVu() {
		DefaultComboBoxModel model = (DefaultComboBoxModel) cboLoaiDichVu.getModel();
		model.removeAllElements();
		try {
			List<LoaiDichVu> list = ldvDao.selectAll();
			for (LoaiDichVu ldv : list) {
				model.addElement(ldv);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
//    public void loadLDV() {
//        try {
//            danhSachLoaiDichVu = ldvDao.selectAll();
//
//            DefaultComboBoxModel model = (DefaultComboBoxModel) cboLoaiDichVu.getModel();
//            model.removeAllElements();
//
//            for (LoaiDichVu loaiDichVu : danhSachLoaiDichVu) {
//                model.addElement(loaiDichVu);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

	public void setModel(Model.DichVu dv) {
		txtMaDichVu.setText(dv.getMaDichVu());
		cboLoaiDichVu.setSelectedIndex(dv.getMaLoaiDichVu() - 1);
		txtPhi.setText(String.valueOf(dv.getDonGia()));
		txtMoTa.setText(dv.getMoTa());
	}

	public Model.DichVu getModel() {
		Model.DichVu dv = new Model.DichVu();
		LoaiDichVu ldv = (LoaiDichVu) cboLoaiDichVu.getSelectedItem();

		dv.setMaDichVu(txtMaDichVu.getText());
		dv.setMaLoaiDichVu(ldv.getMaLoaiDichVu());
		dv.setDonGia(Float.parseFloat(txtPhi.getText()));
		dv.setMoTa(txtMoTa.getText());
		return dv;
	}

	public void setStatus(boolean bl) {
		btnThem.setEnabled(bl);
		btnSua.setEnabled(!bl);
		btnXoa.setEnabled(!bl);
		boolean first = this.index > 0;
		boolean last = this.index < tblDichVu.getRowCount() - 1;
		btnFirst.setEnabled(!bl && first);
		btnPrev.setEnabled(!bl && first);
		btnNext.setEnabled(!bl && last);
		btnLast.setEnabled(!bl && last);
		a.setEnabled(!bl && first);
		b.setEnabled(!bl && first);
		c.setEnabled(!bl && last);
		d.setEnabled(!bl && last);
	}

	public void edit() {
		try {
			String maDV = (String) tblDichVu.getValueAt(index, 0);
			Model.DichVu dv = dvDao.selectbyID(maDV);
			if (dv != null) {
				setModel(dv);
				setStatus(false);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void clear() {
		setModel(new Model.DichVu());
		setStatus(true);
	}

	public void first() {
		index = 0;
		edit();
	}

	public void prev() {
		if (index > 0) {
			index--;
			edit();
		}
	}

	public void next() {
		if (index < tblDichVu.getRowCount() - 1) {
			index++;
			edit();
		}
	}

	public void last() {
		index = tblDichVu.getRowCount() - 1;
		edit();
	}

	public void countAll() {
		int tongSoDichVu = dvDao.countAll();
		lblTongDichVu.setText("Tổng số dịch vụ : " + tongSoDichVu);
	}

	public boolean validates() {

		if (txtMaDichVu.getText().trim().isEmpty()) {
			DialogHelper.alert(this, "Chưa nhập mã dịch vụ");
			txtMaDichVu.requestFocus();
			return false;
		}

		if (txtPhi.getText().trim().isEmpty()) {
			DialogHelper.alert(this, "Chưa nhập phí dịch vụ");
			txtPhi.requestFocus();
			return false;
		}

		try {
			float phiValue = Float.parseFloat(txtPhi.getText());

			if (phiValue <= 0) {
				DialogHelper.alert(this, "Phí dịch vụ phải là số thực lớn hơn 0");
				txtPhi.requestFocus();
				return false;
			}
		} catch (NumberFormatException e) {
			DialogHelper.alert(this, "Phí dịch vụ không hợp lệ");
			txtPhi.requestFocus();
			return false;
		}

		if (txtMoTa.getText().trim().isEmpty()) {
			DialogHelper.alert(this, "Chưa nhập mô tả dịch vụ");
			txtMoTa.requestFocus();
			return false;
		}

		return true;
	}

	public void insert() {
		if (validates()) {
			String maloai = txtMaDichVu.getText();
			Model.DichVu cd = dvDao.selectbyID(maloai);
			if (cd != null) {
				DialogHelper.alert(this, "Mã dịch vụ đã tồn tại");
			} else {
				Model.DichVu model = getModel();
				try {
					dvDao.insert(model);
					DialogHelper.alert(this, "Thêm dịch vụ thành công");
					fillTable();
				} catch (Exception e) {
					DialogHelper.alert(this, "Thêm dịch vụ thất bại");
				}
			}
		}
	}

	public void update() {
		if (validates()) {
			Model.DichVu ldv = getModel();
			try {
				dvDao.update(ldv);
				DialogHelper.alert(this, "Cập nhật dịch vụ Thành công");
				fillTable();
			} catch (Exception e) {
				DialogHelper.alert(this, "Cập nhật dịch vụ thát bại");
			}
		}
	}

	public void delete() {
		if (DialogHelper.confirm(this, "Bạn có chắc chắn muốn xóa dịch này")) {
			String maLoai = txtMaDichVu.getText();
			try {
				dvDao.delete(maLoai);
				DialogHelper.alert(this, "Xóa dịch vụ thành công");
				fillTable();
				this.clear();
			} catch (Exception e) {
				DialogHelper.alert(this, "Xóa dịch vụ thất bại");
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

		jPanel2 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		tblDichVu = new javax.swing.JTable();
		txtTim = new javax.swing.JTextField();
		button1 = new Utils.Button();
		lblTongDichVu = new javax.swing.JLabel();
		boder1 = new Utils.Boder();
		jLabel1 = new javax.swing.JLabel();
		button4 = new Utils.Button();
		jLabel5 = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		btnMoi = new javax.swing.JButton();
		btnXoa = new javax.swing.JButton();
		jLabel17 = new javax.swing.JLabel();
		txtMaDichVu = new javax.swing.JTextField();
		btnThem = new javax.swing.JButton();
		cboLoaiDichVu = new javax.swing.JComboBox<>();
		txtPhi = new javax.swing.JTextField();
		jLabel14 = new javax.swing.JLabel();
		jLabel16 = new javax.swing.JLabel();
		btnSua = new javax.swing.JButton();
		btnLast = new Utils.Boder();
		d = new javax.swing.JLabel();
		btnPrev = new Utils.Boder();
		b = new javax.swing.JLabel();
		btnNext = new Utils.Boder();
		c = new javax.swing.JLabel();
		btnFirst = new Utils.Boder();
		a = new javax.swing.JLabel();
		txtMoTa = new javax.swing.JTextField();
		jLabel20 = new javax.swing.JLabel();

		jPanel2.setBackground(new java.awt.Color(255, 255, 255));

		tblDichVu.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null } },
				new String[] { "Mã dịch vụ", "Loại dịch vụ", "Phí dịch vụ", "Mô tả" }));
		tblDichVu.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				tblDichVuMousePressed(evt);
			}
		});
		jScrollPane1.setViewportView(tblDichVu);

		txtTim.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
		txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				txtTimKeyReleased(evt);
			}
		});

		button1.setForeground(new java.awt.Color(0, 153, 51));

		lblTongDichVu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		lblTongDichVu.setForeground(new java.awt.Color(51, 51, 51));
		lblTongDichVu.setText("Tổng dịch vụ: 0");

		javax.swing.GroupLayout button1Layout = new javax.swing.GroupLayout(button1);
		button1.setLayout(button1Layout);
		button1Layout.setHorizontalGroup(button1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(button1Layout.createSequentialGroup().addContainerGap().addComponent(lblTongDichVu)
						.addContainerGap(15, Short.MAX_VALUE)));
		button1Layout.setVerticalGroup(button1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						button1Layout.createSequentialGroup().addContainerGap()
								.addComponent(lblTongDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
								.addContainerGap()));

		boder1.setForeground(new java.awt.Color(0, 153, 51));

		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jLabel1.setForeground(new java.awt.Color(255, 255, 255));
		jLabel1.setText("Tìm");

		javax.swing.GroupLayout boder1Layout = new javax.swing.GroupLayout(boder1);
		boder1.setLayout(boder1Layout);
		boder1Layout.setHorizontalGroup(boder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(boder1Layout.createSequentialGroup().addGap(33, 33, 33).addComponent(jLabel1)
						.addContainerGap(36, Short.MAX_VALUE)));
		boder1Layout.setVerticalGroup(boder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						boder1Layout.createSequentialGroup().addContainerGap()
								.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
								.addContainerGap()));

		button4.setForeground(new java.awt.Color(0, 153, 51));
		button4.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				button4MouseClicked(evt);
			}
		});

		jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		jLabel5.setForeground(new java.awt.Color(51, 51, 51));
		jLabel5.setText("Thêm loại dịch vụ");

		javax.swing.GroupLayout button4Layout = new javax.swing.GroupLayout(button4);
		button4.setLayout(button4Layout);
		button4Layout.setHorizontalGroup(button4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, button4Layout.createSequentialGroup()
						.addContainerGap(23, Short.MAX_VALUE).addComponent(jLabel5).addGap(19, 19, 19)));
		button4Layout
				.setVerticalGroup(button4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								button4Layout.createSequentialGroup().addContainerGap()
										.addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addContainerGap()));

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1)
						.addGroup(jPanel2Layout.createSequentialGroup()
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(jPanel2Layout.createSequentialGroup()
												.addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 416,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18).addComponent(boder1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(jPanel2Layout.createSequentialGroup()
												.addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(20, 20, 20).addComponent(button4,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addGap(0, 495, Short.MAX_VALUE)))
						.addContainerGap()));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(boder1, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtTim))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(button4, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 506,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		jPanel1.setBackground(new java.awt.Color(255, 255, 255));
		jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

		btnMoi.setBackground(new java.awt.Color(242, 242, 242));
		btnMoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		btnMoi.setText(" Mới ");
		btnMoi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
		btnMoi.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnMoiActionPerformed(evt);
			}
		});

		btnXoa.setBackground(new java.awt.Color(242, 242, 242));
		btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		btnXoa.setText(" Xóa  ");
		btnXoa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
		btnXoa.setPreferredSize(new java.awt.Dimension(32, 18));
		btnXoa.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnXoaActionPerformed(evt);
			}
		});

		jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jLabel17.setText("Phí dịch vụ");

		btnThem.setBackground(new java.awt.Color(242, 242, 242));
		btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		btnThem.setText("Thêm");
		btnThem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
		btnThem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnThemActionPerformed(evt);
			}
		});

		cboLoaiDichVu.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
		cboLoaiDichVu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cboLoaiDichVuActionPerformed(evt);
			}
		});
		cboLoaiDichVu.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				cboLoaiDichVuKeyReleased(evt);
			}
		});

		jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jLabel14.setText("Loại dịch vụ");

		jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jLabel16.setText("Mã dịch vụ");

		btnSua.setBackground(new java.awt.Color(242, 242, 242));
		btnSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		btnSua.setText(" Sữa ");
		btnSua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
		btnSua.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSuaActionPerformed(evt);
			}
		});

		btnLast.setForeground(new java.awt.Color(242, 242, 242));
		btnLast.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				btnLastMousePressed(evt);
			}
		});

		d.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		d.setText(">|");

		javax.swing.GroupLayout btnLastLayout = new javax.swing.GroupLayout(btnLast);
		btnLast.setLayout(btnLastLayout);
		btnLastLayout.setHorizontalGroup(btnLastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						btnLastLayout.createSequentialGroup().addContainerGap(21, Short.MAX_VALUE).addComponent(d,
								javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(16, 16, 16)));
		btnLastLayout.setVerticalGroup(btnLastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(btnLastLayout.createSequentialGroup().addContainerGap().addComponent(d)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		btnPrev.setForeground(new java.awt.Color(242, 242, 242));
		btnPrev.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				btnPrevMousePressed(evt);
			}
		});

		b.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		b.setText("<<");

		javax.swing.GroupLayout btnPrevLayout = new javax.swing.GroupLayout(btnPrev);
		btnPrev.setLayout(btnPrevLayout);
		btnPrevLayout.setHorizontalGroup(btnPrevLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(btnPrevLayout.createSequentialGroup().addGap(17, 17, 17).addComponent(b).addContainerGap(19,
						Short.MAX_VALUE)));
		btnPrevLayout.setVerticalGroup(btnPrevLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						btnPrevLayout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(b)
								.addContainerGap()));

		btnNext.setForeground(new java.awt.Color(242, 242, 242));
		btnNext.setPreferredSize(new java.awt.Dimension(57, 32));
		btnNext.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				btnNextMousePressed(evt);
			}
		});

		c.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		c.setText(">>");

		javax.swing.GroupLayout btnNextLayout = new javax.swing.GroupLayout(btnNext);
		btnNext.setLayout(btnNextLayout);
		btnNextLayout.setHorizontalGroup(btnNextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						btnNextLayout.createSequentialGroup().addContainerGap(19, Short.MAX_VALUE).addComponent(c,
								javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
		btnNextLayout.setVerticalGroup(btnNextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						btnNextLayout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(c)
								.addContainerGap()));

		btnFirst.setForeground(new java.awt.Color(242, 242, 242));
		btnFirst.setPreferredSize(new java.awt.Dimension(57, 32));
		btnFirst.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				btnFirstMousePressed(evt);
			}
		});

		a.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		a.setText(" |<");

		javax.swing.GroupLayout btnFirstLayout = new javax.swing.GroupLayout(btnFirst);
		btnFirst.setLayout(btnFirstLayout);
		btnFirstLayout.setHorizontalGroup(btnFirstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(btnFirstLayout
						.createSequentialGroup().addGap(16, 16, 16).addComponent(a,
								javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(21, Short.MAX_VALUE)));
		btnFirstLayout.setVerticalGroup(btnFirstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						btnFirstLayout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(a)
								.addContainerGap()));

		jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jLabel20.setText("Mô tả");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(15, 15, 15)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(jLabel20).addComponent(jLabel17).addComponent(jLabel16)
								.addComponent(jLabel14).addComponent(txtMaDichVu)
								.addComponent(cboLoaiDichVu, 0, 299, Short.MAX_VALUE).addComponent(txtPhi)
								.addComponent(txtMoTa))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(20, 20, 20).addGroup(jPanel1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
								.createParallelGroup(
										javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										jPanel1Layout.createSequentialGroup()
												.addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18)
												.addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(20, 20, Short.MAX_VALUE)
												.addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18)
												.addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 104,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, Short.MAX_VALUE)))
								.addContainerGap(18, Short.MAX_VALUE))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addGroup(jPanel1Layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
												.addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 104,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(jPanel1Layout.createSequentialGroup()
												.addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 104,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 104,
														javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addGap(32, 32, 32)))));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup().addGap(17, 17, 17).addComponent(jLabel16)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(txtMaDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18).addComponent(jLabel14).addGap(12, 12, 12)
								.addComponent(cboLoaiDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18).addComponent(jLabel17).addGap(12, 12, 12)
								.addComponent(txtPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18).addComponent(jLabel20).addGap(12, 12, 12)
								.addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(35, 35, 35)
								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(btnPrev, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnFirst, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnNext, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addGap(36, 36, 36)
												.addGroup(jPanel1Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE,
																40, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addGap(34, 34, 34)
												.addGroup(jPanel1Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE,
																38, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE,
																38, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGap(122, 122, 122))
										.addGroup(jPanel1Layout.createSequentialGroup()
												.addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)))));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
						.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE));
	}// </editor-fold>//GEN-END:initComponents

	private void button4MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_button4MouseClicked
		new FormQuanLiLoaiDichVu(null, true).setVisible(true);

	}// GEN-LAST:event_button4MouseClicked

	private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnXoaActionPerformed
		delete();
	}// GEN-LAST:event_btnXoaActionPerformed

	private void tblDichVuMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tblDichVuMousePressed
		if (index >= 0) {
			index = tblDichVu.getSelectedRow();
			edit();
		}

	}// GEN-LAST:event_tblDichVuMousePressed

	private void btnFirstMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnFirstMousePressed
		first();
	}// GEN-LAST:event_btnFirstMousePressed

	private void btnPrevMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnPrevMousePressed
		prev();
	}// GEN-LAST:event_btnPrevMousePressed

	private void btnNextMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnNextMousePressed
		next();
	}// GEN-LAST:event_btnNextMousePressed

	private void btnLastMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnLastMousePressed
		last();
	}// GEN-LAST:event_btnLastMousePressed

	private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtTimKeyReleased
		fillTable();
	}// GEN-LAST:event_txtTimKeyReleased

	private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnThemActionPerformed
		insert();
	}// GEN-LAST:event_btnThemActionPerformed

	private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSuaActionPerformed
		update();
	}// GEN-LAST:event_btnSuaActionPerformed

	private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnMoiActionPerformed
		clear();
	}// GEN-LAST:event_btnMoiActionPerformed

	private void cboLoaiDichVuKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_cboLoaiDichVuKeyReleased
	}// GEN-LAST:event_cboLoaiDichVuKeyReleased

	private void cboLoaiDichVuActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cboLoaiDichVuActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_cboLoaiDichVuActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel a;
	private javax.swing.JLabel b;
	private Utils.Boder boder1;
	private Utils.Boder btnFirst;
	private Utils.Boder btnLast;
	private javax.swing.JButton btnMoi;
	private Utils.Boder btnNext;
	private Utils.Boder btnPrev;
	private javax.swing.JButton btnSua;
	private javax.swing.JButton btnThem;
	private javax.swing.JButton btnXoa;
	private Utils.Button button1;
	private Utils.Button button4;
	private javax.swing.JLabel c;
	private javax.swing.JComboBox<String> cboLoaiDichVu;
	private javax.swing.JLabel d;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel16;
	private javax.swing.JLabel jLabel17;
	private javax.swing.JLabel jLabel20;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel lblTongDichVu;
	private javax.swing.JTable tblDichVu;
	private javax.swing.JTextField txtMaDichVu;
	private javax.swing.JTextField txtMoTa;
	private javax.swing.JTextField txtPhi;
	private javax.swing.JTextField txtTim;
	// End of variables declaration//GEN-END:variables

}
