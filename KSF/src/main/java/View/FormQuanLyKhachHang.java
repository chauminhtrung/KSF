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

import Dao.DaoPhong;
import Dao.KhachHangDao;
import Dao.LoaiPhongDao;
import Model.KhachHang;
import Utils.DialogHelper;
import Utils.Tableheader;

/**
 *
 * @author Admin
 */
public class FormQuanLyKhachHang extends javax.swing.JPanel {

	KhachHangDao khDao = new KhachHangDao();
	LoaiPhongDao lpDao = new LoaiPhongDao();
	DaoPhong pDao = new DaoPhong();
	DefaultTableModel model;
	int index = 0;
	private StringBuilder error = new StringBuilder();

	public FormQuanLyKhachHang() {
		initComponents();

		rdoNam.setOpaque(false);
		rdoNu.setOpaque(false);
		tblKhachHang.setShowHorizontalLines(false);
		tblKhachHang.setBorder(null);
		tblKhachHang.setRowHeight(40);
		tblKhachHang.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
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
		FillTable();
		setStatus(true);
		countAll();
	}

	public void FillTable() {
		model = (DefaultTableModel) tblKhachHang.getModel();
		model.setRowCount(0);
		try {
			String maKhachHang = txtTim.getText();
			List<KhachHang> list = (List<KhachHang>) khDao.selectByAll(maKhachHang);
			for (KhachHang kh : list) {
				Object rows[] = { kh.getMaKH(), kh.getHoTen(), kh.getGioiTinh() == 1 ? "Nam" : "Nữ", kh.getNgaySinh(),
						kh.getSoDT(), kh.getSoCCCD(), kh.getDiaChi() };
				model.addRow(rows);
			}
		} catch (Exception e) {

		}
	}

	public boolean validates() {
		if (txtMaKhachHang.getText().isEmpty()) {
			error.append("Chưa nhập mã Khách Hàng\n");
			txtMaKhachHang.requestFocus();
			return false;
		}

		if (txtTenKhachhHang.getText().isEmpty()) {
			error.append("Chưa nhập mã Khách Hàng\n");
			txtTenKhachhHang.requestFocus();
			return false;
		}
		if (txtSDT.getText().isEmpty()) {
			error.append("Chưa nhập số điện thoại\n");
			txtSDT.requestFocus();
			return false;
		}

		if (txtSDT.getText().length() != 10) {
			error.append("Số điện thoại phải là 10 số\n");
			txtSDT.requestFocus();
			return false;
		}

		if (!txtSDT.getText().startsWith("0")) {
			error.append("Số điện thoại sai định dạng\n");
			txtSDT.requestFocus();
			return false;
		}
		if (txtCCCD.getText().isEmpty()) {
			error.append("Chưa nhập số CCCD/CMNN\n");
			txtCCCD.requestFocus();
			return false;
		}
		if (txtCCCD.getText().length() != 12) {
			error.append("Số CCCD/CMNN phải là 12 số\n");
			txtCCCD.requestFocus();
			return false;
		}

		if (txtNgaySinh.getDate() == null) {
			error.append("Chưa nhập ngày sinh\n");
			txtNgaySinh.requestFocus();
			return false;
		}

		if (txtDiaChi.getText().isEmpty()) {
			error.append("Chưa nhập địa chỉ\n");
			txtDiaChi.requestFocus();
			return false;
		}

		return true;
	}

	public void setModel(KhachHang kh) {
		txtMaKhachHang.setText(kh.getMaKH());
		txtTenKhachhHang.setText(kh.getHoTen());
		rdoNam.setSelected(kh.getGioiTinh() == 1 ? true : false);
		rdoNu.setSelected(kh.getGioiTinh() == 0 ? true : false);
		txtNgaySinh.setDate(kh.getNgaySinh());
		txtSDT.setText(kh.getSoDT());
		txtCCCD.setText(kh.getSoCCCD());
		txtDiaChi.setText(kh.getDiaChi());
	}

	public KhachHang getModel() {
		KhachHang kh = new KhachHang();
		kh.setMaKH(txtMaKhachHang.getText());
		kh.setHoTen(txtTenKhachhHang.getText());
		kh.setGioiTinh(rdoNam.isSelected() ? 1 : 0);
		kh.setNgaySinh(txtNgaySinh.getDate());
		kh.setSoDT(txtSDT.getText());
		kh.setSoCCCD(txtCCCD.getText());
		kh.setDiaChi(txtDiaChi.getText());
		return kh;
	}

	public void setStatus(boolean bl) {
		btnThem.setEnabled(bl);
		btnSua.setEnabled(!bl);
		btnXoa.setEnabled(!bl);
		boolean first = this.index > 0;
		boolean last = this.index < tblKhachHang.getRowCount() - 1;
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
			String maKH = (String) tblKhachHang.getValueAt(index, 0);
			KhachHang kh = khDao.selectbyID(maKH);
			if (kh != null) {
				setModel(kh);
				setStatus(false);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void clear() {
		setModel(new KhachHang());
		setStatus(true);
	}

	public void insert() {
		if (validates()) {
			String maKhachHang = txtMaKhachHang.getText();
			Model.KhachHang cd = khDao.selectbyID(maKhachHang);
			if (cd != null) {
				DialogHelper.alert(this, "Mã khách hàng đã tồn tại");
			} else {
				Model.KhachHang model = getModel();
				try {
					khDao.insert(model);
					DialogHelper.alert(this, "Thêm khách hàng thành công");
					FillTable();
					countAll();
				} catch (Exception e) {
					DialogHelper.alert(this, "Thêm khách hàng that bai");
				}
			}
		}
	}

	public void update() {
		if (validates()) {
			KhachHang kh = getModel();
			try {
				khDao.update(kh);
				DialogHelper.alert(this, "Cập nhật khách hàng Thành công");
				FillTable();
			} catch (Exception e) {
				DialogHelper.alert(this, "Cập nhật khách hàng thát bại");
			}
		}
	}

	public void delete() {
		if (DialogHelper.confirm(this, "Bạn có chắc chắn muốn xóa chuyên đề này")) {
			String maKhachHang = txtMaKhachHang.getText();
			try {
				khDao.delete(maKhachHang);
				DialogHelper.alert(this, "Xóa khách hàng thành công");
				this.FillTable();
				this.clear();
				countAll();
			} catch (Exception e) {
				DialogHelper.alert(this, "Xóa khách hàng thất bại");
				throw new RuntimeException(e);

			}
		}
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
		if (index < tblKhachHang.getRowCount() - 1) {
			index++;
			edit();
		}
	}

	public void last() {
		index = tblKhachHang.getRowCount() - 1;
		edit();
	}

	public void countAll() {
		int tongSoKhachHang = khDao.countAll();
		lblTongSoKhachHang.setText("Tổng số khách hàng: " + tongSoKhachHang);
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

		buttonGroup1 = new javax.swing.ButtonGroup();
		jPanel3 = new javax.swing.JPanel();
		jLabel3 = new javax.swing.JLabel();
		jPanel4 = new javax.swing.JPanel();
		lblTongSoKhachHang = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		jPanel5 = new javax.swing.JPanel();
		scrollPaneWin111 = new Utils.ScrollPaneWin11();
		tblKhachHang = new javax.swing.JTable();
		jPanel2 = new javax.swing.JPanel();
		btnMoi = new javax.swing.JButton();
		btnXoa = new javax.swing.JButton();
		btnSua = new javax.swing.JButton();
		btnThem = new javax.swing.JButton();
		txtTim = new javax.swing.JTextField();
		btnTim = new Utils.Boder();
		jLabel1 = new javax.swing.JLabel();
		btnNext = new Utils.Boder();
		c = new javax.swing.JLabel();
		btnFirst = new Utils.Boder();
		a = new javax.swing.JLabel();
		btnLast = new Utils.Boder();
		d = new javax.swing.JLabel();
		btnPrev = new Utils.Boder();
		b = new javax.swing.JLabel();
		txtDiaChi = new javax.swing.JTextField();
		jLabel11 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		rdoNam = new javax.swing.JRadioButton();
		rdoNu = new javax.swing.JRadioButton();
		txtNgaySinh = new com.toedter.calendar.JDateChooser();
		jLabel6 = new javax.swing.JLabel();
		txtCCCD = new javax.swing.JTextField();
		jLabel7 = new javax.swing.JLabel();
		txtSDT = new javax.swing.JTextField();
		jLabel5 = new javax.swing.JLabel();
		txtTenKhachhHang = new javax.swing.JTextField();
		jLabel4 = new javax.swing.JLabel();
		txtMaKhachHang = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();

		jPanel3.setBackground(new java.awt.Color(0, 102, 51));

		jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
		jLabel3.setForeground(new java.awt.Color(255, 255, 255));
		jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel3.setText("Quản Lí Khách Hàng");

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(
				jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel3,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE));

		jPanel4.setBackground(new java.awt.Color(255, 255, 255));
		jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

		lblTongSoKhachHang.setBackground(new java.awt.Color(204, 255, 204));
		lblTongSoKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		lblTongSoKhachHang.setForeground(new java.awt.Color(0, 102, 51));
		lblTongSoKhachHang.setText("Tổng số khách hàng: 0");

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout
				.setHorizontalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel4Layout.createSequentialGroup().addComponent(lblTongSoKhachHang,
								javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, Short.MAX_VALUE)));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(lblTongSoKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE));

		jPanel5.setBackground(new java.awt.Color(255, 255, 255));

		tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null } },
				new String[] { "Mã KH", "Tên KH", "Giới tính", "Ngày sinh", "SDT", "CCCD", "Địa chỉ" }));
		tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tblKhachHangMouseClicked(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				tblKhachHangMousePressed(evt);
			}
		});
		scrollPaneWin111.setViewportView(tblKhachHang);

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(scrollPaneWin111, javax.swing.GroupLayout.DEFAULT_SIZE, 1009, Short.MAX_VALUE));
		jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel5Layout.createSequentialGroup().addComponent(scrollPaneWin111,
						javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 20, Short.MAX_VALUE)));

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel5,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel5,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		jPanel2.setBackground(new java.awt.Color(255, 255, 255));
		jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

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

		btnSua.setBackground(new java.awt.Color(242, 242, 242));
		btnSua.setText(" Sữa ");
		btnSua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
		btnSua.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSuaActionPerformed(evt);
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
		btnThem.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				btnThemKeyPressed(evt);
			}
		});

		txtTim.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
		txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				txtTimKeyReleased(evt);
			}
		});

		btnTim.setForeground(new java.awt.Color(0, 153, 51));
		btnTim.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				btnTimMouseClicked(evt);
			}
		});

		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		jLabel1.setForeground(new java.awt.Color(255, 255, 255));
		jLabel1.setText("Tìm");

		javax.swing.GroupLayout btnTimLayout = new javax.swing.GroupLayout(btnTim);
		btnTim.setLayout(btnTimLayout);
		btnTimLayout.setHorizontalGroup(btnTimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(btnTimLayout.createSequentialGroup().addGap(19, 19, 19)
						.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
						.addContainerGap()));
		btnTimLayout.setVerticalGroup(btnTimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(btnTimLayout.createSequentialGroup().addContainerGap()
						.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
						.addContainerGap()));

		btnNext.setForeground(new java.awt.Color(242, 242, 242));
		btnNext.setPreferredSize(new java.awt.Dimension(73, 32));
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
				.addGroup(btnNextLayout
						.createSequentialGroup().addGap(25, 25, 25).addComponent(c,
								javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(15, Short.MAX_VALUE)));
		btnNextLayout.setVerticalGroup(btnNextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(btnNextLayout.createSequentialGroup().addContainerGap().addComponent(c)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		btnFirst.setForeground(new java.awt.Color(242, 242, 242));
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
						.createSequentialGroup().addGap(22, 22, 22).addComponent(a,
								javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(26, Short.MAX_VALUE)));
		btnFirstLayout.setVerticalGroup(btnFirstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(btnFirstLayout.createSequentialGroup().addContainerGap().addComponent(a,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));

		btnLast.setForeground(new java.awt.Color(242, 242, 242));
		btnLast.setPreferredSize(new java.awt.Dimension(73, 32));
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
						btnLastLayout.createSequentialGroup().addContainerGap(29, Short.MAX_VALUE).addComponent(d,
								javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(24, 24, 24)));
		btnLastLayout.setVerticalGroup(btnLastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						btnLastLayout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(d)
								.addContainerGap()));

		btnPrev.setForeground(new java.awt.Color(242, 242, 242));
		btnPrev.setPreferredSize(new java.awt.Dimension(73, 32));
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
				.addGroup(btnPrevLayout.createSequentialGroup().addGap(26, 26, 26).addComponent(b).addContainerGap(26,
						Short.MAX_VALUE)));
		btnPrevLayout.setVerticalGroup(btnPrevLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						btnPrevLayout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(b)
								.addContainerGap()));

		jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jLabel11.setText("Địa chỉ");

		jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jLabel10.setText("Giới tính");

		buttonGroup1.add(rdoNam);
		rdoNam.setSelected(true);
		rdoNam.setText("Nam");

		buttonGroup1.add(rdoNu);
		rdoNu.setText("Nữ");

		jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jLabel6.setText("Năm sinh");

		jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jLabel7.setText("CCCD");

		jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jLabel5.setText("SDT");

		jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jLabel4.setText("Họ Tên");

		jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jLabel2.setText("Mã KH");

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel2Layout.createSequentialGroup().addComponent(txtTim)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addGroup(jPanel2Layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
												.addGroup(jPanel2Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addGroup(jPanel2Layout.createSequentialGroup()
																.addComponent(btnFirst,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(btnPrev,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(18, 18, 18)
																.addComponent(btnNext,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(btnLast,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(jPanel2Layout.createSequentialGroup()
																.addGroup(jPanel2Layout.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING,
																		false)
																		.addComponent(btnThem,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				141, Short.MAX_VALUE)
																		.addComponent(btnXoa,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addGroup(jPanel2Layout.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING,
																		false)
																		.addComponent(btnSua,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(btnMoi,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				134, Short.MAX_VALUE)))))
										.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
												jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jLabel10).addComponent(jLabel6)
														.addComponent(jLabel7).addComponent(jLabel5)
														.addComponent(jLabel4).addComponent(jLabel2)
														.addComponent(
																jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 58,
																javax.swing.GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addGroup(jPanel2Layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																.addGroup(jPanel2Layout.createSequentialGroup()
																		.addComponent(txtDiaChi,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				262,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(0, 0, Short.MAX_VALUE))
																.addComponent(
																		txtNgaySinh,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addGroup(jPanel2Layout.createSequentialGroup()
																		.addGroup(jPanel2Layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(txtCCCD,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						270,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addGroup(jPanel2Layout
																						.createSequentialGroup()
																						.addComponent(rdoNam)
																						.addGap(18, 18, 18)
																						.addComponent(rdoNu))
																				.addGroup(jPanel2Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addGroup(jPanel2Layout
																								.createSequentialGroup()
																								.addGap(0, 0,
																										Short.MAX_VALUE)
																								.addComponent(
																										txtSDT,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										269,
																										javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addComponent(txtTenKhachhHang,
																								javax.swing.GroupLayout.Alignment.LEADING,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								268,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(txtMaKhachHang)))
																		.addGap(1, 1, 1)))))
								.addGap(13, 13, 13)))
						.addContainerGap()));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(btnTim, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtTim))
						.addGap(41, 41, 41)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel2))
						.addGap(26, 26, 26)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(txtTenKhachhHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel4))
						.addGap(26, 26, 26)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel5))
						.addGap(26, 26, 26)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel7))
						.addGap(26, 26, 26)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(txtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(26, 26, 26)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel10).addComponent(rdoNam).addComponent(rdoNu))
						.addGap(26, 26, 26)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel11).addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE,
										108, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
								.addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(btnMoi, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
								.addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(22, 22, 22)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))));
	}// </editor-fold>//GEN-END:initComponents

	private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnMoiActionPerformed
		clear();
		tblKhachHang.clearSelection();
	}// GEN-LAST:event_btnMoiActionPerformed

	private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnXoaActionPerformed
		delete();
	}// GEN-LAST:event_btnXoaActionPerformed

	private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSuaActionPerformed
		update();
	}// GEN-LAST:event_btnSuaActionPerformed

	private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnThemActionPerformed
		insert();
	}// GEN-LAST:event_btnThemActionPerformed

	private void btnThemKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_btnThemKeyPressed
		insert();
	}// GEN-LAST:event_btnThemKeyPressed

	private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtTimKeyReleased
		FillTable();
	}// GEN-LAST:event_txtTimKeyReleased

	private void btnTimMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnTimMouseClicked

	}// GEN-LAST:event_btnTimMouseClicked

	private void btnNextMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnNextMousePressed
		next();
	}// GEN-LAST:event_btnNextMousePressed

	private void btnFirstMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnFirstMousePressed
		first();
	}// GEN-LAST:event_btnFirstMousePressed

	private void btnLastMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnLastMousePressed
		last();
	}// GEN-LAST:event_btnLastMousePressed

	private void btnPrevMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnPrevMousePressed
		prev();
	}// GEN-LAST:event_btnPrevMousePressed

	private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tblKhachHangMouseClicked
		// TODO add your handling code here:
	}// GEN-LAST:event_tblKhachHangMouseClicked

	private void tblKhachHangMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tblKhachHangMousePressed
		if (index >= 0) {
			index = tblKhachHang.getSelectedRow();
			edit();
		}
	}// GEN-LAST:event_tblKhachHangMousePressed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel a;
	private javax.swing.JLabel b;
	private Utils.Boder btnFirst;
	private Utils.Boder btnLast;
	private javax.swing.JButton btnMoi;
	private Utils.Boder btnNext;
	private Utils.Boder btnPrev;
	private javax.swing.JButton btnSua;
	private javax.swing.JButton btnThem;
	private Utils.Boder btnTim;
	private javax.swing.JButton btnXoa;
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.JLabel c;
	private javax.swing.JLabel d;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JLabel lblTongSoKhachHang;
	private javax.swing.JRadioButton rdoNam;
	private javax.swing.JRadioButton rdoNu;
	private Utils.ScrollPaneWin11 scrollPaneWin111;
	private javax.swing.JTable tblKhachHang;
	private javax.swing.JTextField txtCCCD;
	private javax.swing.JTextField txtDiaChi;
	private javax.swing.JTextField txtMaKhachHang;
	private com.toedter.calendar.JDateChooser txtNgaySinh;
	private javax.swing.JTextField txtSDT;
	private javax.swing.JTextField txtTenKhachhHang;
	private javax.swing.JTextField txtTim;
	// End of variables declaration//GEN-END:variables
}
