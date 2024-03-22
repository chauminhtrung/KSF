/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import java.awt.Component;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Dao.TienNghiDao;
import Utils.DialogHelper;
import Utils.Tableheader;

/**
 *
 * @author ngomi
 */
public class FormTienNghi extends javax.swing.JPanel {

	TienNghiDao tnDao = new TienNghiDao();
	int index = 0;
	DefaultTableModel model;

	public FormTienNghi() {
		initComponents();
		tblTienNghi.setShowHorizontalLines(false);
		tblTienNghi.setBorder(null);
		tblTienNghi.setRowHeight(40);
		tblTienNghi.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
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
		countAll();
	}

	public void fillTable() {
		model = (DefaultTableModel) tblTienNghi.getModel();
		model.setRowCount(0);
		try {
			String key = txtTim.getText();
			List<Model.TienNghi> list = (List<Model.TienNghi>) tnDao.selectByAll(key);
			for (Model.TienNghi tn : list) {
				Object rows[] = { tn.getMaTienNghi(), tn.getTenTienNghi()

				};
				model.addRow(rows);
			}
		} catch (Exception e) {

		}
	}

	public void search() {
		model = (DefaultTableModel) tblTienNghi.getModel();
		model.setRowCount(0);
		try {
			String key = txtTim.getText();
			List<Model.TienNghi> list = (List<Model.TienNghi>) tnDao.selectByAll(key);
			for (Model.TienNghi tn : list) {
				Object rows[] = { tn.getMaTienNghi(), tn.getTenTienNghi()

				};
				model.addRow(rows);
			}
		} catch (Exception e) {

		}
	}

	public void setModel(Model.TienNghi tn) {
		txtMaTienNghi.setText(String.valueOf(tn.getMaTienNghi()));
		txtTenTienNghi.setText(tn.getTenTienNghi());
	}

	public Model.TienNghi getModel() {
		Model.TienNghi tn = new Model.TienNghi();
		tn.setMaTienNghi(Integer.valueOf(txtMaTienNghi.getText()));
		tn.setTenTienNghi(txtTenTienNghi.getText());
		return tn;
	}

	public void setStatus(boolean bl) {
		btnThem.setEnabled(bl);
		btnSua.setEnabled(!bl);
		btnXoa.setEnabled(!bl);
		boolean first = this.index > 0;
		boolean last = this.index < tblTienNghi.getRowCount() - 1;
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
			int maKH = (int) tblTienNghi.getValueAt(index, 0);
			Model.TienNghi tn = tnDao.selectbyID(maKH);
			if (tn != null) {
				setModel(tn);
				setStatus(false);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void clear() {
		setModel(new Model.TienNghi());
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
		if (index < tblTienNghi.getRowCount() - 1) {
			index++;
			edit();
		}
	}

	public void last() {
		index = tblTienNghi.getRowCount() - 1;
		edit();
	}

	public void countAll() {
		int tongSoDichVu = tnDao.countAll();
		lblTongTienNghi.setText("Tổng số dịch vụ : " + tongSoDichVu);
	}

	public boolean validates() {

		if (txtMaTienNghi.getText().trim().isEmpty()) {
			DialogHelper.alert(this, "Chưa nhập mã tiện nghi");
			txtMaTienNghi.requestFocus();
			return false;
		}

		try {
			int maTienNghi = Integer.parseInt(txtMaTienNghi.getText());
		} catch (Exception e) {
			DialogHelper.alert(this, "Mã tiện nghi không hợp lệ");
			txtMaTienNghi.requestFocus();
			return false;
		}

		if (txtTenTienNghi.getText().trim().isEmpty()) {
			DialogHelper.alert(this, "Chưa nhập tên tiện nghi");
			txtTenTienNghi.requestFocus();
			return false;
		}

		return true;
	}

	public void insert() {
		if (validates()) {
			int maloai = Integer.valueOf(txtMaTienNghi.getText());
			Model.TienNghi cd = tnDao.selectbyID(maloai);
			if (cd != null) {
				DialogHelper.alert(this, "Mã tiện nghi đã tồn tại");
			} else {
				Model.TienNghi model = getModel();
				try {
					tnDao.insert(model);
					DialogHelper.alert(this, "Thêm tiện nghi thành công");
					fillTable();
				} catch (Exception e) {
					DialogHelper.alert(this, "Thêm tiện nghi thất bại");
				}
			}
		}
	}

	public void update() {
		if (validates()) {
			Model.TienNghi ldv = getModel();
			try {
				tnDao.update(ldv);
				DialogHelper.alert(this, "Cập nhật tiện nghi Thành công");
				fillTable();
			} catch (Exception e) {
				DialogHelper.alert(this, "Cập nhậttiện nghi thát bại");
			}
		}
	}

	public void delete() {
		if (DialogHelper.confirm(this, "Bạn có chắc chắn muốn xóa tiện nghi này này")) {
			int maLoai = Integer.valueOf(txtMaTienNghi.getText());
			try {
				tnDao.delete(maLoai);
				DialogHelper.alert(this, "Xóa tiên nghi thành công");
				fillTable();
				this.clear();
			} catch (Exception e) {
				DialogHelper.alert(this, "Xóa tiện nghi thất bại");
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
		tblTienNghi = new javax.swing.JTable();
		txtTim = new javax.swing.JTextField();
		boder1 = new Utils.Boder();
		jLabel1 = new javax.swing.JLabel();
		button1 = new Utils.Button();
		lblTongTienNghi = new javax.swing.JLabel();
		button4 = new Utils.Button();
		jLabel5 = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		btnMoi = new javax.swing.JButton();
		btnXoa = new javax.swing.JButton();
		txtMaTienNghi = new javax.swing.JTextField();
		btnThem = new javax.swing.JButton();
		jLabel14 = new javax.swing.JLabel();
		jLabel16 = new javax.swing.JLabel();
		btnSua = new javax.swing.JButton();
		txtTenTienNghi = new javax.swing.JTextField();
		btnFirst = new Utils.Boder();
		a = new javax.swing.JLabel();
		btnPrev = new Utils.Boder();
		b = new javax.swing.JLabel();
		btnNext = new Utils.Boder();
		c = new javax.swing.JLabel();
		btnLast = new Utils.Boder();
		d = new javax.swing.JLabel();

		jPanel2.setBackground(new java.awt.Color(255, 255, 255));

		tblTienNghi.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null }, { null, null }, { null, null }, { null, null } },
				new String[] { "Mã tiện nghi", "Tên tiện nghi" }));
		tblTienNghi.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				tblTienNghiMousePressed(evt);
			}
		});
		jScrollPane1.setViewportView(tblTienNghi);

		txtTim.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
		txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				txtTimKeyReleased(evt);
			}
		});

		boder1.setForeground(new java.awt.Color(0, 153, 51));
		boder1.setPreferredSize(new java.awt.Dimension(94, 44));
		boder1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				boder1MouseClicked(evt);
			}
		});

		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jLabel1.setForeground(new java.awt.Color(255, 255, 255));
		jLabel1.setText("Tìm");

		javax.swing.GroupLayout boder1Layout = new javax.swing.GroupLayout(boder1);
		boder1.setLayout(boder1Layout);
		boder1Layout.setHorizontalGroup(boder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(boder1Layout.createSequentialGroup().addGap(34, 34, 34).addComponent(jLabel1)
						.addContainerGap(34, Short.MAX_VALUE)));
		boder1Layout.setVerticalGroup(boder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						boder1Layout.createSequentialGroup().addContainerGap()
								.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
								.addContainerGap()));

		button1.setForeground(new java.awt.Color(0, 153, 51));

		lblTongTienNghi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		lblTongTienNghi.setForeground(new java.awt.Color(51, 51, 51));
		lblTongTienNghi.setText("Tổng tiện nghi: 0");

		javax.swing.GroupLayout button1Layout = new javax.swing.GroupLayout(button1);
		button1.setLayout(button1Layout);
		button1Layout.setHorizontalGroup(button1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(button1Layout.createSequentialGroup().addContainerGap().addComponent(lblTongTienNghi)
						.addContainerGap(15, Short.MAX_VALUE)));
		button1Layout
				.setVerticalGroup(button1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								button1Layout.createSequentialGroup().addContainerGap()
										.addComponent(lblTongTienNghi, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addContainerGap()));

		button4.setForeground(new java.awt.Color(0, 153, 51));
		button4.setPreferredSize(new java.awt.Dimension(169, 34));
		button4.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				button4MouseClicked(evt);
			}
		});

		jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		jLabel5.setForeground(new java.awt.Color(51, 51, 51));
		jLabel5.setText("Tiện nghi chi tiết");

		javax.swing.GroupLayout button4Layout = new javax.swing.GroupLayout(button4);
		button4.setLayout(button4Layout);
		button4Layout.setHorizontalGroup(button4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(button4Layout.createSequentialGroup().addGap(23, 23, 23).addComponent(jLabel5)
						.addContainerGap(22, Short.MAX_VALUE)));
		button4Layout.setVerticalGroup(button4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						button4Layout.createSequentialGroup().addContainerGap()
								.addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
								.addContainerGap()));

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addGap(14, 14, 14).addGroup(jPanel2Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1)
						.addGroup(jPanel2Layout.createSequentialGroup()
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(jPanel2Layout.createSequentialGroup()
												.addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 402,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(boder1, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(jPanel2Layout.createSequentialGroup()
												.addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addGap(0, 545, Short.MAX_VALUE)))
						.addContainerGap()));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(boder1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(button4, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jScrollPane1)));

		jPanel1.setBackground(new java.awt.Color(255, 255, 255));
		jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

		btnMoi.setBackground(new java.awt.Color(242, 242, 242));
		btnMoi.setText(" Mới ");
		btnMoi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
		btnMoi.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnMoiActionPerformed(evt);
			}
		});

		btnXoa.setBackground(new java.awt.Color(242, 242, 242));
		btnXoa.setText(" Xóa  ");
		btnXoa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
		btnXoa.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnXoaActionPerformed(evt);
			}
		});

		txtMaTienNghi.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtMaTienNghiActionPerformed(evt);
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

		jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jLabel14.setText("Tên tiện nghi");

		jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jLabel16.setText("Mã tiện nghi");

		btnSua.setBackground(new java.awt.Color(242, 242, 242));
		btnSua.setText(" Sữa ");
		btnSua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
		btnSua.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSuaActionPerformed(evt);
			}
		});

		txtTenTienNghi.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtTenTienNghiActionPerformed(evt);
			}
		});

		btnFirst.setForeground(new java.awt.Color(242, 242, 242));
		btnFirst.setPreferredSize(new java.awt.Dimension(62, 32));
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
						.createSequentialGroup().addGap(20, 20, 20).addComponent(a,
								javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(22, Short.MAX_VALUE)));
		btnFirstLayout.setVerticalGroup(btnFirstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						btnFirstLayout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(a)
								.addContainerGap()));

		btnPrev.setForeground(new java.awt.Color(242, 242, 242));
		btnPrev.setPreferredSize(new java.awt.Dimension(62, 32));
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
				.addGroup(btnPrevLayout.createSequentialGroup().addGap(19, 19, 19)
						.addComponent(b, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE).addContainerGap()));
		btnPrevLayout.setVerticalGroup(btnPrevLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						btnPrevLayout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(b)
								.addContainerGap()));

		btnNext.setForeground(new java.awt.Color(242, 242, 242));
		btnNext.setPreferredSize(new java.awt.Dimension(62, 32));
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
				.addGroup(btnNextLayout.createSequentialGroup().addGap(20, 20, 20)
						.addComponent(c, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE).addContainerGap()));
		btnNextLayout.setVerticalGroup(btnNextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(btnNextLayout.createSequentialGroup().addContainerGap().addComponent(c)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

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
						btnLastLayout.createSequentialGroup().addContainerGap(24, Short.MAX_VALUE).addComponent(d,
								javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)));
		btnLastLayout.setVerticalGroup(btnLastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(btnLastLayout.createSequentialGroup().addContainerGap().addComponent(d)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(14, 14, 14).addGroup(jPanel1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
								.addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(jPanel1Layout.createSequentialGroup()
								.addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 118,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 118,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(jPanel1Layout.createSequentialGroup()
								.addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 118,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 118,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(jPanel1Layout.createSequentialGroup()
								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jLabel14)
										.addComponent(txtTenTienNghi, javax.swing.GroupLayout.PREFERRED_SIZE, 263,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(txtMaTienNghi, javax.swing.GroupLayout.PREFERRED_SIZE, 263,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel16))
								.addGap(0, 0, Short.MAX_VALUE)))
						.addGap(14, 14, 14)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(23, 23, 23).addComponent(jLabel16)
						.addGap(18, 18, 18)
						.addComponent(txtMaTienNghi, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(28, 28, 28).addComponent(jLabel14).addGap(18, 18, 18)
						.addComponent(txtTenTienNghi, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(208, 208, 208)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(btnLast, javax.swing.GroupLayout.Alignment.TRAILING,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(btnNext, javax.swing.GroupLayout.Alignment.TRAILING,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(btnPrev, javax.swing.GroupLayout.Alignment.TRAILING,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(btnFirst, javax.swing.GroupLayout.Alignment.TRAILING,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addGap(29, 29, 29)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(btnMoi, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(34, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel2,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE));
	}// </editor-fold>//GEN-END:initComponents

	private void boder1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_boder1MouseClicked

	}// GEN-LAST:event_boder1MouseClicked

	private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnXoaActionPerformed
		delete();
	}// GEN-LAST:event_btnXoaActionPerformed

	private void button4MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_button4MouseClicked
		new FormQuanLiChiTietTienNghi(null, true).setVisible(true);
	}// GEN-LAST:event_button4MouseClicked

	private void txtTenTienNghiActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtTenTienNghiActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_txtTenTienNghiActionPerformed

	private void txtMaTienNghiActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtMaTienNghiActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_txtMaTienNghiActionPerformed

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

	private void tblTienNghiMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tblTienNghiMousePressed
		if (index >= 0) {
			index = tblTienNghi.getSelectedRow();
			edit();
		}
	}// GEN-LAST:event_tblTienNghiMousePressed

	private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnThemActionPerformed
		insert();
	}// GEN-LAST:event_btnThemActionPerformed

	private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSuaActionPerformed
		update();
	}// GEN-LAST:event_btnSuaActionPerformed

	private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnMoiActionPerformed
		clear();
	}// GEN-LAST:event_btnMoiActionPerformed

	private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtTimKeyReleased
		fillTable();
	}// GEN-LAST:event_txtTimKeyReleased

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
	private javax.swing.JLabel d;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel16;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel lblTongTienNghi;
	private javax.swing.JTable tblTienNghi;
	private javax.swing.JTextField txtMaTienNghi;
	private javax.swing.JTextField txtTenTienNghi;
	private javax.swing.JTextField txtTim;
	// End of variables declaration//GEN-END:variables
}
