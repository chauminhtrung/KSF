/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import Dao.DaoNhanVien;
import Model.NhanVien;
import Utils.ClearForm;
import Utils.DateHelper;
import Utils.DialogHelper;
import Utils.Encryption;
import Utils.ValidateClass;
import Utils.XImage;

/**
 *
 * @author Administrator
 */
public class FormNhanVien extends javax.swing.JPanel {

	private DefaultTableModel tblModel = new DefaultTableModel();
	private List<NhanVien> list = new ArrayList<>();
	private DaoNhanVien dao = new DaoNhanVien();
	private int isShowPassword = 0;
	private int isShowPasswordConfirm = 0;
	private StringBuilder error = new StringBuilder();
	private int row = 0;
	public static String key = "MySecretKey12345";
	private Encryption encryption = new Encryption(key);
	NhanVien nhanvien;

	/**
	 * Creates new form FormNhanVien
	 */
	public FormNhanVien(NhanVien NhanVien) {
		initComponents();
		fillToTable();
		nhanvien = NhanVien;
		pnlAvatar.setToolTipText(null);
		txtBirth.setText(null);
		setStatus(true);
	}
//    public void fillToTable(List<NhanVien> list){
//        tblModel = (DefaultTableModel) tblNV.getModel();
//        tblModel.setRowCount(0);
//        int i = 1;
//        for (NhanVien nv : list) {
//            tblModel.addRow(new Object[]{
//                i++, nv.getMaNV(), nv.getHoVaTen(), nv.getGioiTinh()==1?"Nam":"Nữ", nv.getEmail(), nv.getSdt(), nv.getVaiTro()==1?"Quản lý":"Nhân viên"
//            });
//        }
//    }

	public void fillToTable() {
		tblModel = (DefaultTableModel) tblNV.getModel();
		tblModel.setRowCount(0);
		int index = cboRole.getSelectedIndex();
		if (index == 0) {
			list = dao.loadList();
		} else if (index == 1) {
			list = dao.loadListQL();
		} else if (index == 2) {
			list = dao.loadListNV();
		}
		int i = 1;
		for (NhanVien nv : list) {
			tblModel.addRow(new Object[] { i++, nv.getMaNV(), nv.getHoVaTen(), nv.getGioiTinh() == 1 ? "Nam" : "Nữ",
					nv.getEmail(), nv.getSdt(), nv.getVaiTro() == 1 ? "Quản lý" : "Nhân viên" });
		}
	}

	// Phương thức đổ dữ liệu lên bảng sau khi tìm kiếm
	public void fillTableForSearch() {
		List<NhanVien> otherList = searchByKeyWord(list, txtSearch.getText());
		tblModel = (DefaultTableModel) tblNV.getModel();
		tblModel.setRowCount(0);
		int i = 1;
		for (NhanVien nv : otherList) {
			tblModel.addRow(new Object[] { i++, nv.getMaNV(), nv.getHoVaTen(), nv.getGioiTinh() == 1 ? "Nam" : "Nữ",
					nv.getEmail(), nv.getSdt(), nv.getVaiTro() == 1 ? "Quản lý" : "Nhân viên" });
		}
	}

	// Phương thức checkForm
	public void checkForm() {
		if (txtID.getText().isEmpty()) {
			error.append("Phải nhập mã\n");
		} else if (txtID.getText().length() > 5) {
			error.append("Mã chỉ dưới 5 ký tự\n");
		}
		if (txtBirth.getText().isEmpty()) {
			error.append("Phải nhập ngày sinh\n");
		} else {
			if (!ValidateClass.isOver18(DateHelper.toDate(txtBirth.getText(), "dd-MM-yyyy"))) {
				error.append("Phải trên 18 tuổi\n");
			}
		}
		if (txtCCCD.getText().isEmpty()) {
			error.append("Phải nhập số căn cước công dân\n");
		} else {
			try {
				long SoCC = Long.parseLong(txtCCCD.getText());
				if (SoCC < 0) {
					error.append("Số căn cước không đúng định dạng\n");
				} else if (txtCCCD.getText().length() != 12) {
					error.append("Số căn cước phải đúng 12 số\n");
				}
			} catch (Exception e) {
				error.append("Số căn cước không đúng định dạng\n");
			}
		}
		if (txtDiaChi.getText().isEmpty()) {
			error.append("Phải nhập địa chỉ\n");
		}
		if (txtEmail.getText().isEmpty()) {
			error.append("Phải nhập email\n");
		} else {
			if (!ValidateClass.isValidEmail(txtEmail.getText())) {
				error.append("Email không đúng định dạng\n");
			}
		}
		if (txtName.getText().isEmpty()) {
			error.append("Phải nhập họ và tên\n");
		} else if (!ValidateClass.isName(txtName.getText())) {
			error.append("Họ tên không đúng định dạng\n");
		}
		if (txtPhoneNumber.getText().isEmpty()) {
			error.append("Phải nhập số điện thoại\n");
		} else {
			if (!ValidateClass.isValidPhoneNumber(txtPhoneNumber.getText())) {
				error.append("Số điện thoại không đúng định dạng\n");
			}
		}
		if (txtPassword.getText().isEmpty()) {
			error.append("Phải nhập mật khẩu\n");
		} else {
			if (!ValidateClass.isValidPassWord(txtPassword.getText())) {
				error.append("Mật khẩu phải từ 6 ký tự trở lên và không chứa ký tự tiếng Việt\n");
			} else {
				if (txtConfirmPassword.getText().isEmpty()) {
					error.append("Vui lòng xác nhận lại mật khẩu\n");
				} else {
					if (!txtPassword.getText().equals(txtConfirmPassword.getText())) {
						error.append("Xác nhận mật khẩu không đúng, vui lòng nhập lại!\n");
					}
				}
			}
		}
		if (btgGender.getSelection() == null) {
			error.append("Bạn phải chọn giới tính\n");
		}
		if (btgRole.getSelection() == null) {
			error.append("Bạn phải chọn vai trò");
		}
	}

	// Phương thức showError()
	public boolean showError() {
		checkForm();
		if (error.length() > 0) {
			DialogHelper.alert(this, error.toString());
			error.setLength(0);
			return false;
		}
		return true;
	}

	// Lấy dữ liệu từ form
	public NhanVien getForm() {
		NhanVien nv = new NhanVien();
		nv.setMaNV(txtID.getText());
		nv.setHoVaTen(txtName.getText());
		nv.setCCCD(txtCCCD.getText());
		nv.setDiaChi(txtDiaChi.getText());
		nv.setGioiTinh(rdoMale.isSelected() ? 1 : 0);
		nv.setVaiTro(rdoManager.isSelected() ? 1 : 0);
		if (txtBirth.getText().isEmpty()) {
			nv.setNgaySinh(null);
		} else {
			nv.setNgaySinh(DateHelper.toDate(txtBirth.getText(), "dd-MM-yyyy"));
		}
		if (pnlAvatar.getToolTipText() == "") {
			nv.setHinh(null);
		} else {
			nv.setHinh(pnlAvatar.getToolTipText());
		}
		nv.setEmail(txtEmail.getText());
		nv.setSdt(txtPhoneNumber.getText());
		try {
			nv.setMatKhau(encryption.encrypt(txtPassword.getText()));
		} catch (Exception ex) {
			Logger.getLogger(FormNhanVien.class.getName()).log(Level.SEVERE, null, ex);
		}
		return nv;
	}

	// Phương thức chèn dữ liệu vào form
	public void setForm(NhanVien nv) {
		txtID.setText(nv.getMaNV());
		txtName.setText(nv.getHoVaTen());
		txtBirth.setText(DateHelper.toString(nv.getNgaySinh(), "dd-MM-yyyy"));
		txtCCCD.setText(nv.getCCCD().trim());
		txtEmail.setText(nv.getEmail().trim());
		try {
			txtPassword.setText(encryption.decrypt(nv.getMatKhau().trim()));
			txtConfirmPassword.setText(encryption.decrypt(nv.getMatKhau().trim()));
		} catch (Exception ex) {
			Logger.getLogger(FormNhanVien.class.getName()).log(Level.SEVERE, null, ex);
		}
		txtDiaChi.setText(nv.getDiaChi().trim());
		txtPhoneNumber.setText(nv.getSdt());
		rdoMale.setSelected(nv.getGioiTinh() == 1 ? true : false);
		rdoFemale.setSelected(nv.getGioiTinh() == 0 ? true : false);
		rdoManager.setSelected(nv.getVaiTro() == 1 ? true : false);
		rdoEmployee.setSelected(nv.getVaiTro() == 0 ? true : false);
		if (nv.getHinh() != null) {
			pnlAvatar.setIcon(XImage.read(nv.getHinh()));
			pnlAvatar.setToolTipText(nv.getHinh());
		} else {
			pnlAvatar.setIcon(XImage.read("otherUser.png"));
			pnlAvatar.setToolTipText(null);
		}
	}

	// Phương thức chèn dữ liệu vào form khi nhấn vào bảng (từ setForm())
	public void edit(int i) {
		tblNV.setRowSelectionInterval(i, i);
		String maNV = (String) tblNV.getValueAt(i, 1);
		setForm(dao.FindByMa(maNV));
		setStatus(false);
		date.setSelectedDate(dao.FindByMa(maNV).getNgaySinh());
	}

	// Phương thức chèn
	public void insert() {
		NhanVien nv = getForm();
		if (dao.FindByMa(nv.getMaNV()) != null) {
			error.append("Mã nhân viên đã tồn tại\n");
		}
		if (!showError()) {
			return;
		}
		try {
			dao.insert(nv);
			fillToTable();
			clearForm();
			DialogHelper.alert(this, "Thêm mới thành công!");
		} catch (Exception e) {
			DialogHelper.alert(this, "Lỗi truy vấn");
		}
	}

	// Phương thức cập nhật
	public void update() {
		if (!showError()) {
			return;
		}
		NhanVien nv = getForm();
		try {
			if (nhanvien.getVaiTro() == 1) {
				if (DialogHelper.confirm(this, "Bạn muốn cập nhật cho nhân viên này?")) {
					dao.update(nv);
					fillToTable();
					clearForm();
					DialogHelper.alert(this, "Cập nhật thành công!");
				}
			} else {
				DialogHelper.alert(this, "Nhân viên không thể sửa thông tin!");
			}
		} catch (Exception e) {
			System.out.println(e);
			DialogHelper.alert(this, "Lỗi truy vấn");
		}
	}

	// Phương thức xoá
	public void delete() {
		NhanVien nv = getForm();
		try {
			if (nhanvien.getVaiTro() == 1) {
				if (DialogHelper.confirm(this, "Bạn muốn xoá nhân viên này?")) {
					dao.delete(nv.getMaNV());
					fillToTable();
					clearForm();
					DialogHelper.alert(this, "Xoá thành công!");
				}
			} else {
				DialogHelper.alert(this, "Nhân viên không thể xoá!");
			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Lỗi truy vấn");
		}
	}

	// Phương thức tìm kiếm
	public List<NhanVien> searchByKeyWord(List<NhanVien> otherList, String keyWord) {
		List<NhanVien> result = new ArrayList<>();
		for (NhanVien nhanVien : otherList) {
			if (nhanVien.getMaNV().contains(keyWord)) {
				result.add(nhanVien);
			}
		}
		return result;
	}

	// Phương thức setStatus()
	public void setStatus(boolean insertable) {
		txtID.setEditable(insertable);
		btnAdd.setEnabled(insertable);
		btnUpdate.setEnabled(!insertable);
		btnDelete.setEnabled(!insertable);
	}

	// Phương thức xoá trắng form
	public void clearForm() {
		ClearForm.clearFields(pnlDetail);
		btgGender.clearSelection();
		btgRole.clearSelection();
		pnlAvatar.setIcon(XImage.read("otherUser.png"));
		pnlAvatar.setToolTipText(null);
		setStatus(true);
	}

	// Phương thức đổi avatar
	public void selectPicture() {
		JFileChooser fileChooser = new JFileChooser();
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			XImage.save(file);// lưu vào package Image
			ImageIcon icon = XImage.read(file.getName());// lấy hình từ package Image
			pnlAvatar.setIcon(icon);
			pnlAvatar.setToolTipText(file.getName());
		}
	}

	// Đổi định dạng cho password
	public void changeEyeIcon(JLabel lbl) {
		if (lbl == hideIcon) {
			if (isShowPassword == 0) {
				txtPassword.setEchoChar((char) 0);
				hideIcon.setIcon(new ImageIcon("src//Icon//eye.png"));
				isShowPassword++;
			} else {
				txtPassword.setEchoChar('*');
				hideIcon.setIcon(new ImageIcon("src//Icon//hideye.png"));
				isShowPassword = 0;
			}
		}
		if (lbl == hideIcon1) {
			if (isShowPasswordConfirm == 0) {
				txtConfirmPassword.setEchoChar((char) 0);
				hideIcon1.setIcon(new ImageIcon("src//Icon//eye.png"));
				isShowPasswordConfirm++;
			} else {
				txtConfirmPassword.setEchoChar('*');
				hideIcon1.setIcon(new ImageIcon("src//Icon//hideye.png"));
				isShowPasswordConfirm = 0;
			}
		}
	}

	public void first() {
		row = 0;
		edit(row);
	}

	public void last() {
		row = list.size() - 1;
		edit(row);
	}

	public void pre() {
		if (row > 0) {
			row--;
			edit(row);
		}
	}

	public void next() {
		if (row < list.size() - 1) {
			row++;
			edit(row);
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

		btgGender = new javax.swing.ButtonGroup();
		btgRole = new javax.swing.ButtonGroup();
		date = new Datechooser.DateChooser();
		pnlNV = new javax.swing.JPanel();
		tabPanelQLNV = new Utils.TabbedPaneCustom();
		pnlDetail = new javax.swing.JPanel();
		jLabel16 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		jLabel18 = new javax.swing.JLabel();
		jLabel15 = new javax.swing.JLabel();
		jLabel14 = new javax.swing.JLabel();
		jLabel13 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		txtID = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		txtName = new javax.swing.JTextField();
		jLabel12 = new javax.swing.JLabel();
		txtCCCD = new javax.swing.JTextField();
		jLabel4 = new javax.swing.JLabel();
		txtBirth = new javax.swing.JTextField();
		jLabel17 = new javax.swing.JLabel();
		txtEmail = new javax.swing.JTextField();
		jLabel6 = new javax.swing.JLabel();
		txtPassword = new javax.swing.JPasswordField();
		btnCalender = new javax.swing.JLabel();
		hideIcon = new javax.swing.JLabel();
		hideIcon1 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		rdoMale = new Utils.RadioButtonCustom();
		rdoFemale = new Utils.RadioButtonCustom();
		rdoManager = new Utils.RadioButtonCustom();
		rdoEmployee = new Utils.RadioButtonCustom();
		jLabel9 = new javax.swing.JLabel();
		pnlAvatar = new Utils.Paneltron();
		btnChangeAvatar = new Utils.Button();
		jLabel25 = new javax.swing.JLabel();
		jLabel27 = new javax.swing.JLabel();
		jLabel29 = new javax.swing.JLabel();
		jLabel40 = new javax.swing.JLabel();
		txtDiaChi = new javax.swing.JTextField();
		jLabel41 = new javax.swing.JLabel();
		txtPhoneNumber = new javax.swing.JTextField();
		jLabel42 = new javax.swing.JLabel();
		txtConfirmPassword = new javax.swing.JPasswordField();
		btnAdd = new Utils.Button();
		btnUpdate = new Utils.Button();
		btnDelete = new Utils.Button();
		btnClear = new Utils.Button();
		jPanel2 = new javax.swing.JPanel();
		jLabel21 = new javax.swing.JLabel();
		jLabel22 = new javax.swing.JLabel();
		cboRole = new Utils.ComboBoxSuggestion();
		txtSearch = new Utils.TextFieldSuggestion();
		btnSearch = new Utils.Button();
		jScrollPane2 = new javax.swing.JScrollPane();
		tblNV = new Utils.TableBlue();
		panelRound1 = new Utils.PanelRound();
		btnDown = new Utils.Button();
		btnNext = new Utils.Button();
		btnPrevious = new Utils.Button();
		btnUp = new Utils.Button();
		gradientPanel2 = new Utils.GradientPanel();
		jLabel1 = new javax.swing.JLabel();

		date.setForeground(new java.awt.Color(0, 204, 255));
		date.setTextRefernce(txtBirth);

		pnlNV.setBackground(new java.awt.Color(255, 255, 255));

		tabPanelQLNV.setForeground(new java.awt.Color(255, 255, 255));
		tabPanelQLNV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		tabPanelQLNV.setSelectedColor(new java.awt.Color(0, 153, 204));
		tabPanelQLNV.setUnselectedColor(new java.awt.Color(185, 214, 255));

		pnlDetail.setBackground(new java.awt.Color(255, 255, 255));
		pnlDetail.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jLabel16.setForeground(new java.awt.Color(0, 153, 255));
		jLabel16.setText("_____________________________________________");
		jLabel16.setVerticalAlignment(javax.swing.SwingConstants.TOP);
		jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
		pnlDetail.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 510, 330, 20));

		jLabel10.setForeground(new java.awt.Color(0, 153, 255));
		jLabel10.setText("_____________________________________________");
		jLabel10.setVerticalAlignment(javax.swing.SwingConstants.TOP);
		jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
		pnlDetail.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 330, -1, -1));

		jLabel18.setForeground(new java.awt.Color(0, 153, 255));
		jLabel18.setText("_____________________________________________");
		jLabel18.setVerticalAlignment(javax.swing.SwingConstants.TOP);
		jLabel18.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
		pnlDetail.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, -1, -1));

		jLabel15.setForeground(new java.awt.Color(0, 153, 255));
		jLabel15.setText("_____________________________________________");
		jLabel15.setVerticalAlignment(javax.swing.SwingConstants.TOP);
		jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
		pnlDetail.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, -1, -1));

		jLabel14.setForeground(new java.awt.Color(0, 153, 255));
		jLabel14.setText("_____________________________________________");
		jLabel14.setVerticalAlignment(javax.swing.SwingConstants.TOP);
		jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
		pnlDetail.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, -1, -1));

		jLabel13.setForeground(new java.awt.Color(0, 153, 255));
		jLabel13.setText("_____________________________________________");
		jLabel13.setVerticalAlignment(javax.swing.SwingConstants.TOP);
		jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
		pnlDetail.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 230, -1));

		jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel2.setForeground(new java.awt.Color(153, 153, 153));
		jLabel2.setText("Mã nhân viên:");
		pnlDetail.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, -1));

		txtID.setFont(txtID.getFont());
		txtID.setForeground(new java.awt.Color(51, 153, 255));
		txtID.setBorder(null);
		pnlDetail.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 330, 30));

		jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel3.setForeground(new java.awt.Color(153, 153, 153));
		jLabel3.setText("Họ và tên:");
		pnlDetail.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, -1, -1));

		txtName.setFont(txtName.getFont());
		txtName.setForeground(new java.awt.Color(51, 153, 255));
		txtName.setBorder(null);
		pnlDetail.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 330, 30));

		jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel12.setForeground(new java.awt.Color(153, 153, 153));
		jLabel12.setText("Số căn cước công dân (CMND):");
		pnlDetail.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, -1, -1));

		txtCCCD.setFont(txtCCCD.getFont());
		txtCCCD.setForeground(new java.awt.Color(51, 153, 255));
		txtCCCD.setBorder(null);
		pnlDetail.add(txtCCCD, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 330, 30));

		jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel4.setForeground(new java.awt.Color(153, 153, 153));
		jLabel4.setText("Ngày/Tháng/Năm sinh:");
		pnlDetail.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, -1, -1));

		txtBirth.setFont(txtBirth.getFont());
		txtBirth.setForeground(new java.awt.Color(51, 153, 255));
		txtBirth.setBorder(null);
		pnlDetail.add(txtBirth, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, 330, 30));

		jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel17.setForeground(new java.awt.Color(153, 153, 153));
		jLabel17.setText("Email:");
		pnlDetail.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 370, -1, -1));

		txtEmail.setFont(txtEmail.getFont());
		txtEmail.setForeground(new java.awt.Color(51, 153, 255));
		txtEmail.setBorder(null);
		pnlDetail.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 400, 330, 30));

		jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel6.setForeground(new java.awt.Color(153, 153, 153));
		jLabel6.setText("Mật khẩu:");
		pnlDetail.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 460, -1, -1));

		txtPassword.setFont(txtPassword.getFont());
		txtPassword.setForeground(new java.awt.Color(51, 153, 255));
		txtPassword.setBorder(null);
		pnlDetail.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 490, 330, 30));

		btnCalender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/calender.png"))); // NOI18N
		btnCalender.setText(" ");
		btnCalender.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				btnCalenderMouseClicked(evt);
			}
		});
		pnlDetail.add(btnCalender, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 310, 26, -1));

		hideIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/hideye.png"))); // NOI18N
		hideIcon.setText(" ");
		hideIcon.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				hideIconMouseClicked(evt);
			}
		});
		pnlDetail.add(hideIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 490, 26, -1));

		hideIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/hideye.png"))); // NOI18N
		hideIcon1.setText(" ");
		hideIcon1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				hideIcon1MouseClicked(evt);
			}
		});
		pnlDetail.add(hideIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 410, 26, -1));

		jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel7.setForeground(new java.awt.Color(153, 153, 153));
		jLabel7.setText("Giới tính:");
		pnlDetail.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 550, -1, -1));

		jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel8.setForeground(new java.awt.Color(153, 153, 153));
		jLabel8.setText("Vai trò:");
		pnlDetail.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 550, -1, -1));

		btgGender.add(rdoMale);
		rdoMale.setForeground(new java.awt.Color(153, 153, 153));
		rdoMale.setSelected(true);
		rdoMale.setText("Nam");
		rdoMale.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		pnlDetail.add(rdoMale, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 580, -1, -1));

		btgGender.add(rdoFemale);
		rdoFemale.setForeground(new java.awt.Color(153, 153, 153));
		rdoFemale.setText("Nữ");
		rdoFemale.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		pnlDetail.add(rdoFemale, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 580, -1, -1));

		btgRole.add(rdoManager);
		rdoManager.setForeground(new java.awt.Color(153, 153, 153));
		rdoManager.setText("Quản lý");
		rdoManager.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		pnlDetail.add(rdoManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 580, -1, -1));

		btgRole.add(rdoEmployee);
		rdoEmployee.setForeground(new java.awt.Color(153, 153, 153));
		rdoEmployee.setSelected(true);
		rdoEmployee.setText("Nhân viên");
		rdoEmployee.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		pnlDetail.add(rdoEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 580, -1, -1));

		jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		jLabel9.setForeground(new java.awt.Color(203, 200, 200));
		jLabel9.setText(
				"<html>Vui lòng nhấn vào nút camera\n<br>để tải hoặc thay đổi hình ảnh\n<br>\n<br>Ảnh được giới hạn tối đa 5MB\n<br>\n<br>Ảnh được định dạng kiểu JPG, JPEG, PNG</html>"); // NOI18N
		pnlDetail.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 40, 230, -1));

		pnlAvatar.setForeground(new java.awt.Color(0, 204, 255));
		pnlAvatar.setBorderSize(2);
		pnlAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/otherUser.png"))); // NOI18N

		btnChangeAvatar.setBackground(new java.awt.Color(40, 167, 210));
		btnChangeAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/camera.png"))); // NOI18N
		btnChangeAvatar.setBorderColor(new java.awt.Color(40, 167, 210));
		btnChangeAvatar.setColor(new java.awt.Color(40, 167, 210));
		btnChangeAvatar.setColorClick(new java.awt.Color(51, 153, 255));
		btnChangeAvatar.setColorOver(new java.awt.Color(40, 167, 210));
		btnChangeAvatar.setPreferredSize(new java.awt.Dimension(70, 70));
		btnChangeAvatar.setRadius(70);
		btnChangeAvatar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnChangeAvatarActionPerformed(evt);
			}
		});
		pnlAvatar.add(btnChangeAvatar);
		btnChangeAvatar.setBounds(140, 140, 50, 50);

		pnlDetail.add(pnlAvatar, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, 210, 190));

		jLabel25.setForeground(new java.awt.Color(0, 153, 255));
		jLabel25.setText("_____________________________________________");
		jLabel25.setVerticalAlignment(javax.swing.SwingConstants.TOP);
		jLabel25.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
		pnlDetail.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 430, -1, 20));

		jLabel27.setForeground(new java.awt.Color(0, 153, 255));
		jLabel27.setText("_____________________________________________");
		jLabel27.setVerticalAlignment(javax.swing.SwingConstants.TOP);
		jLabel27.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
		pnlDetail.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 340, -1, -1));

		jLabel29.setForeground(new java.awt.Color(0, 153, 255));
		jLabel29.setText("_____________________________________________");
		jLabel29.setVerticalAlignment(javax.swing.SwingConstants.TOP);
		jLabel29.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
		pnlDetail.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 260, -1, -1));

		jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel40.setForeground(new java.awt.Color(153, 153, 153));
		jLabel40.setText("Địa chỉ:");
		pnlDetail.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 200, -1, -1));

		txtDiaChi.setFont(txtDiaChi.getFont());
		txtDiaChi.setForeground(new java.awt.Color(51, 153, 255));
		txtDiaChi.setBorder(null);
		pnlDetail.add(txtDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 240, 330, 30));

		jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel41.setForeground(new java.awt.Color(153, 153, 153));
		jLabel41.setText("Số điện thoại:");
		pnlDetail.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 290, -1, -1));

		txtPhoneNumber.setFont(txtPhoneNumber.getFont());
		txtPhoneNumber.setForeground(new java.awt.Color(51, 153, 255));
		txtPhoneNumber.setBorder(null);
		pnlDetail.add(txtPhoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 320, 330, 30));

		jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel42.setForeground(new java.awt.Color(153, 153, 153));
		jLabel42.setText("Xác nhận mật khẩu:");
		pnlDetail.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 380, -1, -1));

		txtConfirmPassword.setFont(txtConfirmPassword.getFont());
		txtConfirmPassword.setForeground(new java.awt.Color(51, 153, 255));
		txtConfirmPassword.setBorder(null);
		pnlDetail.add(txtConfirmPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 410, 330, 30));

		btnAdd.setBackground(new java.awt.Color(51, 153, 255));
		btnAdd.setForeground(new java.awt.Color(255, 255, 255));
		btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/add.png"))); // NOI18N
		btnAdd.setText("Thêm");
		btnAdd.setBorderColor(new java.awt.Color(51, 153, 255));
		btnAdd.setColor(new java.awt.Color(51, 153, 255));
		btnAdd.setColorClick(new java.awt.Color(0, 153, 255));
		btnAdd.setColorOver(new java.awt.Color(51, 153, 255));
		btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		btnAdd.setRadius(25);
		btnAdd.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAddActionPerformed(evt);
			}
		});
		pnlDetail.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 570, 130, 40));

		btnUpdate.setBackground(new java.awt.Color(51, 153, 255));
		btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
		btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/update.png"))); // NOI18N
		btnUpdate.setText("Sửa");
		btnUpdate.setBorderColor(new java.awt.Color(51, 153, 255));
		btnUpdate.setColor(new java.awt.Color(51, 153, 255));
		btnUpdate.setColorClick(new java.awt.Color(0, 153, 255));
		btnUpdate.setColorOver(new java.awt.Color(51, 153, 255));
		btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		btnUpdate.setRadius(25);
		btnUpdate.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnUpdateActionPerformed(evt);
			}
		});
		pnlDetail.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 570, 130, 40));

		btnDelete.setBackground(new java.awt.Color(51, 153, 255));
		btnDelete.setForeground(new java.awt.Color(255, 255, 255));
		btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/delete.png"))); // NOI18N
		btnDelete.setText("Xoá");
		btnDelete.setBorderColor(new java.awt.Color(51, 153, 255));
		btnDelete.setColor(new java.awt.Color(51, 153, 255));
		btnDelete.setColorClick(new java.awt.Color(0, 153, 255));
		btnDelete.setColorOver(new java.awt.Color(51, 153, 255));
		btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		btnDelete.setRadius(25);
		btnDelete.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnDeleteActionPerformed(evt);
			}
		});
		pnlDetail.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 570, 130, 40));

		btnClear.setBackground(new java.awt.Color(51, 153, 255));
		btnClear.setForeground(new java.awt.Color(255, 255, 255));
		btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/clear.png"))); // NOI18N
		btnClear.setText("Mới");
		btnClear.setBorderColor(new java.awt.Color(51, 153, 255));
		btnClear.setColor(new java.awt.Color(51, 153, 255));
		btnClear.setColorClick(new java.awt.Color(0, 153, 255));
		btnClear.setColorOver(new java.awt.Color(51, 153, 255));
		btnClear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		btnClear.setRadius(25);
		btnClear.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnClearActionPerformed(evt);
			}
		});
		pnlDetail.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 570, 130, 40));

		tabPanelQLNV.addTab("Chi tiết", pnlDetail);

		jPanel2.setBackground(new java.awt.Color(255, 255, 255));

		jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel21.setForeground(new java.awt.Color(153, 153, 153));
		jLabel21.setText("Vai trò:");

		jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel22.setForeground(new java.awt.Color(153, 153, 153));
		jLabel22.setText("Tìm kiếm theo mã:");

		cboRole.setEditable(false);
		cboRole.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả", "Quản lý", "Nhân viên" }));
		cboRole.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cboRoleActionPerformed(evt);
			}
		});

		btnSearch.setBackground(new java.awt.Color(40, 167, 210));
		btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/search.png"))); // NOI18N
		btnSearch.setBorderColor(new java.awt.Color(40, 167, 210));
		btnSearch.setColor(new java.awt.Color(40, 167, 210));
		btnSearch.setColorClick(new java.awt.Color(51, 153, 255));
		btnSearch.setColorOver(new java.awt.Color(40, 167, 210));
		btnSearch.setPreferredSize(new java.awt.Dimension(70, 70));
		btnSearch.setRadius(70);
		btnSearch.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSearchActionPerformed(evt);
			}
		});

		tblNV.setForeground(new java.awt.Color(255, 255, 255));
		tblNV.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null } },
				new String[] { "Số thứ tự", "Mã nhân viên", "Họ tên", "Giới tính", "Email", "Số điện thoại",
						"Vai trò" }));
		tblNV.setGridColor(new java.awt.Color(204, 204, 204));
		tblNV.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tblNVMouseClicked(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				tblNVMousePressed(evt);
			}
		});
		jScrollPane2.setViewportView(tblNV);

		panelRound1.setBackground(new java.awt.Color(0, 204, 255));
		panelRound1.setRoundBottomLeft(70);
		panelRound1.setRoundBottomRight(70);
		panelRound1.setRoundTopLeft(70);
		panelRound1.setRoundTopRight(70);

		btnDown.setBackground(new java.awt.Color(0, 204, 255));
		btnDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/down.png"))); // NOI18N
		btnDown.setBorderColor(new java.awt.Color(0, 204, 255));
		btnDown.setColor(new java.awt.Color(0, 204, 255));
		btnDown.setColorClick(new java.awt.Color(51, 153, 255));
		btnDown.setColorOver(new java.awt.Color(0, 204, 255));
		btnDown.setPreferredSize(new java.awt.Dimension(70, 70));
		btnDown.setRadius(30);
		btnDown.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnDownActionPerformed(evt);
			}
		});

		btnNext.setBackground(new java.awt.Color(0, 204, 255));
		btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/double_down.png"))); // NOI18N
		btnNext.setBorderColor(new java.awt.Color(0, 204, 255));
		btnNext.setColor(new java.awt.Color(0, 204, 255));
		btnNext.setColorClick(new java.awt.Color(51, 153, 255));
		btnNext.setColorOver(new java.awt.Color(0, 204, 255));
		btnNext.setPreferredSize(new java.awt.Dimension(70, 70));
		btnNext.setRadius(30);
		btnNext.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnNextActionPerformed(evt);
			}
		});

		btnPrevious.setBackground(new java.awt.Color(0, 204, 255));
		btnPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/double_up.png"))); // NOI18N
		btnPrevious.setBorderColor(new java.awt.Color(0, 204, 255));
		btnPrevious.setColor(new java.awt.Color(0, 204, 255));
		btnPrevious.setColorClick(new java.awt.Color(51, 153, 255));
		btnPrevious.setColorOver(new java.awt.Color(0, 204, 255));
		btnPrevious.setPreferredSize(new java.awt.Dimension(70, 70));
		btnPrevious.setRadius(30);
		btnPrevious.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnPreviousActionPerformed(evt);
			}
		});

		btnUp.setBackground(new java.awt.Color(0, 204, 255));
		btnUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/up.png"))); // NOI18N
		btnUp.setBorderColor(new java.awt.Color(0, 204, 255));
		btnUp.setColor(new java.awt.Color(0, 204, 255));
		btnUp.setColorClick(new java.awt.Color(51, 153, 255));
		btnUp.setColorOver(new java.awt.Color(0, 204, 255));
		btnUp.setPreferredSize(new java.awt.Dimension(70, 70));
		btnUp.setRadius(30);
		btnUp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnUpActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
		panelRound1.setLayout(panelRound1Layout);
		panelRound1Layout.setHorizontalGroup(panelRound1Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
						.addContainerGap(18, Short.MAX_VALUE)
						.addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(btnUp, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(
										panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(btnDown, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGap(15, 15, 15)));
		panelRound1Layout.setVerticalGroup(panelRound1Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
						.addGap(24, 24, 24)
						.addComponent(btnUp, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(47, 47, 47)
						.addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
						.addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(47, 47, 47).addComponent(btnDown, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(20, 20, 20)));

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						jPanel2Layout.createSequentialGroup().addContainerGap()
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addGroup(jPanel2Layout.createSequentialGroup().addComponent(jScrollPane2)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addContainerGap())
										.addGroup(jPanel2Layout.createSequentialGroup().addComponent(jLabel21)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(cboRole, javax.swing.GroupLayout.PREFERRED_SIZE, 233,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														526, Short.MAX_VALUE)
												.addComponent(jLabel22).addGap(18, 18, 18)
												.addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 208,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(89, 89, 89)))));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addGap(26, 26, 26).addGroup(jPanel2Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel21)
								.addComponent(cboRole, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel22))
						.addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
								javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel2Layout.createSequentialGroup().addGap(18, 18, 18)
										.addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 539,
												Short.MAX_VALUE)
										.addContainerGap())
								.addGroup(jPanel2Layout.createSequentialGroup().addGap(46, 46, 46)
										.addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))));

		tabPanelQLNV.addTab("Danh sách", jPanel2);

		jLabel1.setBackground(new java.awt.Color(255, 255, 255));
		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel1.setForeground(new java.awt.Color(255, 255, 255));
		jLabel1.setText("QUẢN LÝ NHÂN VIÊN");

		javax.swing.GroupLayout gradientPanel2Layout = new javax.swing.GroupLayout(gradientPanel2);
		gradientPanel2.setLayout(gradientPanel2Layout);
		gradientPanel2Layout
				.setHorizontalGroup(gradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(gradientPanel2Layout.createSequentialGroup().addGap(34, 34, 34).addComponent(jLabel1)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gradientPanel2Layout
				.setVerticalGroup(gradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(gradientPanel2Layout.createSequentialGroup().addGap(27, 27, 27).addComponent(jLabel1)
								.addContainerGap(34, Short.MAX_VALUE)));

		javax.swing.GroupLayout pnlNVLayout = new javax.swing.GroupLayout(pnlNV);
		pnlNV.setLayout(pnlNVLayout);
		pnlNVLayout.setHorizontalGroup(pnlNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlNVLayout.createSequentialGroup().addContainerGap()
						.addComponent(tabPanelQLNV, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap())
				.addComponent(gradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		pnlNVLayout.setVerticalGroup(pnlNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlNVLayout.createSequentialGroup()
						.addComponent(gradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(tabPanelQLNV, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				pnlNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				pnlNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
	}// </editor-fold>//GEN-END:initComponents

	private void btnCalenderMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnCalenderMouseClicked
		date.showPopup();
	}// GEN-LAST:event_btnCalenderMouseClicked

	private void hideIconMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_hideIconMouseClicked
		if (nhanvien.getVaiTro() == 1) {
			changeEyeIcon(hideIcon);
		}
	}// GEN-LAST:event_hideIconMouseClicked

	private void hideIcon1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_hideIcon1MouseClicked
		if (nhanvien.getVaiTro() == 1) {
			changeEyeIcon(hideIcon1);
		}
	}// GEN-LAST:event_hideIcon1MouseClicked

	private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAddActionPerformed
		insert();
	}// GEN-LAST:event_btnAddActionPerformed

	private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnUpdateActionPerformed
		update();
	}// GEN-LAST:event_btnUpdateActionPerformed

	private void btnChangeAvatarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnChangeAvatarActionPerformed
		selectPicture();
	}// GEN-LAST:event_btnChangeAvatarActionPerformed

	private void tblNVMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tblNVMouseClicked
		// Nothing
	}// GEN-LAST:event_tblNVMouseClicked

	private void tblNVMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tblNVMousePressed
		if (evt.getClickCount() == 2) {
			row = tblNV.getSelectedRow();
			edit(row);
			tabPanelQLNV.setSelectedIndex(0);
		}
	}// GEN-LAST:event_tblNVMousePressed

	private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnDeleteActionPerformed
		delete();
	}// GEN-LAST:event_btnDeleteActionPerformed

	private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnClearActionPerformed
		clearForm();
	}// GEN-LAST:event_btnClearActionPerformed

	private void cboRoleActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cboRoleActionPerformed
		int i = cboRole.getSelectedIndex();
		if (i > -1) {
			fillToTable();
		}
	}// GEN-LAST:event_cboRoleActionPerformed

	private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSearchActionPerformed
		fillTableForSearch();
	}// GEN-LAST:event_btnSearchActionPerformed

	private void btnUpActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnUpActionPerformed
		first();
	}// GEN-LAST:event_btnUpActionPerformed

	private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnPreviousActionPerformed
		pre();
	}// GEN-LAST:event_btnPreviousActionPerformed

	private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnNextActionPerformed
		next();
	}// GEN-LAST:event_btnNextActionPerformed

	private void btnDownActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnDownActionPerformed
		last();
	}// GEN-LAST:event_btnDownActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.ButtonGroup btgGender;
	private javax.swing.ButtonGroup btgRole;
	private Utils.Button btnAdd;
	private javax.swing.JLabel btnCalender;
	private Utils.Button btnChangeAvatar;
	private Utils.Button btnClear;
	private Utils.Button btnDelete;
	private Utils.Button btnDown;
	private Utils.Button btnNext;
	private Utils.Button btnPrevious;
	private Utils.Button btnSearch;
	private Utils.Button btnUp;
	private Utils.Button btnUpdate;
	private Utils.ComboBoxSuggestion cboRole;
	private Datechooser.DateChooser date;
	private Utils.GradientPanel gradientPanel2;
	private javax.swing.JLabel hideIcon;
	private javax.swing.JLabel hideIcon1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel16;
	private javax.swing.JLabel jLabel17;
	private javax.swing.JLabel jLabel18;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel21;
	private javax.swing.JLabel jLabel22;
	private javax.swing.JLabel jLabel25;
	private javax.swing.JLabel jLabel27;
	private javax.swing.JLabel jLabel29;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel40;
	private javax.swing.JLabel jLabel41;
	private javax.swing.JLabel jLabel42;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane2;
	private Utils.PanelRound panelRound1;
	public Utils.Paneltron pnlAvatar;
	private javax.swing.JPanel pnlDetail;
	private javax.swing.JPanel pnlNV;
	private Utils.RadioButtonCustom rdoEmployee;
	private Utils.RadioButtonCustom rdoFemale;
	private Utils.RadioButtonCustom rdoMale;
	private Utils.RadioButtonCustom rdoManager;
	private Utils.TabbedPaneCustom tabPanelQLNV;
	private Utils.TableBlue tblNV;
	private javax.swing.JTextField txtBirth;
	private javax.swing.JTextField txtCCCD;
	private javax.swing.JPasswordField txtConfirmPassword;
	private javax.swing.JTextField txtDiaChi;
	private javax.swing.JTextField txtEmail;
	private javax.swing.JTextField txtID;
	private javax.swing.JTextField txtName;
	private javax.swing.JPasswordField txtPassword;
	private javax.swing.JTextField txtPhoneNumber;
	private Utils.TextFieldSuggestion txtSearch;
	// End of variables declaration//GEN-END:variables
}
