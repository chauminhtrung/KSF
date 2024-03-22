/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import Dao.DaoPhong;
import Model.NhanVien;
import Model.Phong;
import Utils.DateHelper;
import Utils.DialogHelper;

/**
 *
 * @author Admin
 */
public class FormQLPhong extends javax.swing.JPanel {

	DaoPhong phong_dao = new DaoPhong();
	int i;
	JPanel pnSerchPT1 = new JPanel(new GridLayout(0, 6, 10, 10));
	JPanel pnSerchPT2 = new JPanel(new GridLayout(0, 6, 10, 10));
	Phong phong;
	JPanel[] pnPhong = new JPanel[phong_dao.CoutId_Tang()];
	JLabel[] lblLoaiPhong = new JLabel[phong_dao.CoutId_Tang()];
	JLabel[] lblSoPhong = new JLabel[phong_dao.CoutId_Tang()];
	JLabel[] lblGia = new JLabel[phong_dao.CoutId_Tang()];
	JLabel[] lblTrangThai = new JLabel[phong_dao.CoutId_Tang()];
	NhanVien NhanVien;
	int row = 0;
	String MaPhong1;
	private StringBuilder error = new StringBuilder();
	List<Phong> list_phong = new ArrayList<>();
	List<Phong> list_phong2 = new ArrayList<>();

	public FormQLPhong(NhanVien nhanvien) {
		initComponents();
		filltable();
		NhanVien = nhanvien;
		btnTru.setVisible(false);
		pnEditPhong.setVisible(false);
		ScrollPhong.setVisible(false);
		mnuChuyenVaoPhong.setVisible(false);
		btnHuyDoiPhong.setVisible(false);
		fillPHONG();

	}

	public void openEditPhong() {
		btncong.setVisible(false);
		btnTru.setVisible(true);
		pnEditPhong.setVisible(true);
		scrollDatPhong.setVisible(false);
		ScrollPhong.setVisible(true);
	}

	void exitEditPhong() {
		btncong.setVisible(true);
		btnTru.setVisible(false);
		pnEditPhong.setVisible(false);
		scrollDatPhong.setVisible(true);
		ScrollPhong.setVisible(false);
	}

	void RemovePHONG() {
		pnTang1.remove(pnSerchPT1);
		pnTang2.remove(pnSerchPT2);
		pnSerchPT1.removeAll();
		pnSerchPT2.removeAll();
		scrollDatPhong.setVisible(false);
		scrollDatPhong.setVisible(true);
	}

	void selectDKphongTrong() {
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		list_phong2 = phong_dao.DukienPhongTrong(date_duklienphongtrong.getDate());
		lblSLPhong.setText("Số Lượng Phòng: " + phong_dao.CoutId_Trong());
		pnSerchPT1.setBackground(Color.WHITE);
		pnSerchPT2.setBackground(Color.WHITE);
		mnuSuaChuaPhong.setVisible(false);
		mnudoiPhong.setVisible(false);
		for (i = 0; i < list_phong2.size(); i++) {

			pnPhong[i] = new JPanel();
			lblLoaiPhong[i] = new JLabel();
			lblSoPhong[i] = new JLabel();
			lblGia[i] = new JLabel();
			lblTrangThai[i] = new JLabel();
			pnPhong[i].setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
			pnPhong[i].setBorder(javax.swing.BorderFactory.createEtchedBorder());

			lblLoaiPhong[i].setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
			lblLoaiPhong[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			lblLoaiPhong[i].setText("LOẠI PHÒNG");

			pnPhong[i].add(lblLoaiPhong[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 160, 30));

			lblSoPhong[i].setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
			lblSoPhong[i].setForeground(new java.awt.Color(0, 102, 102));
			lblSoPhong[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			lblSoPhong[i].setText("101");

			pnPhong[i].add(lblSoPhong[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 160, 40));

			lblGia[i].setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
			lblGia[i].setForeground(new java.awt.Color(255, 51, 0));
			lblGia[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			lblGia[i].setText("Giá");

			pnPhong[i].add(lblGia[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 140, 60));

			lblTrangThai[i].setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
			lblTrangThai[i].setForeground(new java.awt.Color(255, 255, 255));
			lblTrangThai[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			lblTrangThai[i].setText("Trạng Thái:");

			pnPhong[i].add(lblTrangThai[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 140, 40));

			Phong model = list_phong2.get(i);

			if (model.getMaTang() == 1) {

				lblLoaiPhong[i].setText(model.getTenPhong());
				lblSoPhong[i].setText(model.getMaPhong());
				lblGia[i].setText(String.valueOf(formatter.format(model.getGia())) + " VNĐ");
				lblTrangThai[i].setText(model.getTrangThai());

				Color mau = new Color(204, 204, 204);
				Color mau1 = new Color(26, 246, 127);
				Color mau2 = new Color(255, 204, 0);

				if (lblTrangThai[i].getText().equals("Đã Đặt")) {
					pnPhong[i].setBackground(mau1);
				} else if (lblTrangThai[i].getText().equals("Trống")) {
					pnPhong[i].setBackground(mau);
				} else if (lblTrangThai[i].getText().equals("Đang Dọn Phòng")) {
					pnPhong[i].setBackground(mau2);
					lblGia[i].setVisible(true);
				} else {
					pnPhong[i].setBackground(Color.RED);
					lblGia[i].setVisible(false);
				}

				pnSerchPT1.add(pnPhong[i]);
				pnTang1.add(pnSerchPT1);

			} else if (model.getMaTang() == 2) {
				lblLoaiPhong[i].setText(model.getTenPhong());
				lblSoPhong[i].setText(model.getMaPhong());
				lblGia[i].setText(String.valueOf(formatter.format(model.getGia())) + " VNĐ");
				lblTrangThai[i].setText(model.getTrangThai());

				Color mau = new Color(204, 204, 204);
				Color mau1 = new Color(26, 246, 127);
				Color mau2 = new Color(255, 204, 0);

				if (lblTrangThai[i].getText().equals("Đã Đặt")) {
					pnPhong[i].setBackground(mau1);
				} else if (lblTrangThai[i].getText().equals("Trống")) {
					pnPhong[i].setBackground(mau);
				} else if (lblTrangThai[i].getText().equals("Đang Dọn Phòng")) {
					pnPhong[i].setBackground(mau2);
					lblGia[i].setVisible(true);
				} else {
					pnPhong[i].setBackground(Color.RED);
					lblGia[i].setVisible(false);
				}
				pnSerchPT2.add(pnPhong[i]);
				pnTang2.add(pnSerchPT2);

			}
			pnPhong[i].addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
				}

				@Override
				public void mousePressed(MouseEvent e) {
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					int id = phong_dao.CoutId_Tang();

					for (int j = 0; j < id; j++) {
						if (e.getSource() == pnPhong[j]) {

							if (e.isPopupTrigger() && model.getTrangThai().equals("Trống")) {
								ppm101.show(e.getComponent(), e.getX(), e.getY());
								mnuDatPhong.setEnabled(true);
								mnuDonPhong.setEnabled(false);
								mnuTraPhong.setEnabled(false);
								mnuSuaChuaPhong.setEnabled(true);
								mnudoiPhong.setVisible(false);
							}
						}

					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					int id = phong_dao.CoutId_Tang();
					for (int j = 0; j < id; j++) {
						if (e.getSource() == pnPhong[j]) {
							phong = phong_dao.selectbyID(lblSoPhong[j].getText());

							Color mau = new Color(0, 153, 153);
							pnPhong[j].setBorder(javax.swing.BorderFactory.createLineBorder(mau, 5));
						}

					}

				}

				@Override
				public void mouseExited(MouseEvent e) {
					int id = phong_dao.CoutId_Tang();

					for (int j = 0; j < id; j++) {
						if (e.getSource() == pnPhong[j]) {

							pnPhong[j].setBorder(javax.swing.BorderFactory.createEmptyBorder());
						}

					}
				}
			});

		}
	}

	void selectDaDat() {
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		lblSLPhong.setText("Số Lượng Phòng: " + phong_dao.CoutId_DaDat());
		pnSerchPT1.setBackground(Color.WHITE);
		pnSerchPT2.setBackground(Color.WHITE);
		mnuSuaChuaPhong.setVisible(false);
		mnudoiPhong.setVisible(true);

		for (i = 0; i < phong_dao.CoutId_Tang(); i++) {

			pnPhong[i] = new JPanel();
			lblLoaiPhong[i] = new JLabel();
			lblSoPhong[i] = new JLabel();
			lblGia[i] = new JLabel();
			lblTrangThai[i] = new JLabel();

			pnPhong[i].setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
			pnPhong[i].setBorder(javax.swing.BorderFactory.createEtchedBorder());

			lblLoaiPhong[i].setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
			lblLoaiPhong[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			lblLoaiPhong[i].setText("LOẠI PHÒNG");

			pnPhong[i].add(lblLoaiPhong[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 160, 30));

			lblSoPhong[i].setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
			lblSoPhong[i].setForeground(new java.awt.Color(0, 102, 102));
			lblSoPhong[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			lblSoPhong[i].setText("101");

			pnPhong[i].add(lblSoPhong[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 160, 40));

			lblGia[i].setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
			lblGia[i].setForeground(new java.awt.Color(255, 51, 0));
			lblGia[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			lblGia[i].setText("Giá");

			pnPhong[i].add(lblGia[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 140, 60));

			lblTrangThai[i].setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
			lblTrangThai[i].setForeground(new java.awt.Color(255, 255, 255));
			lblTrangThai[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			lblTrangThai[i].setText("Trạng Thái:");

			pnPhong[i].add(lblTrangThai[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 140, 40));

			Phong model = list_phong.get(i);

			if (model.getMaTang() == 1 && model.getTrangThai().equals("Đã Đặt")) {

				lblLoaiPhong[i].setText(model.getTenPhong());
				lblSoPhong[i].setText(model.getMaPhong());
				lblGia[i].setText(String.valueOf(formatter.format(model.getGia())) + " VNĐ");
				lblTrangThai[i].setText(model.getTrangThai());

				Color mau1 = new Color(26, 246, 127);

				pnPhong[i].setBackground(mau1);
				pnSerchPT1.add(pnPhong[i]);
				pnTang1.add(pnSerchPT1);

			} else if (model.getMaTang() == 2 && model.getTrangThai().equals("Đã Đặt")) {
				lblLoaiPhong[i].setText(model.getTenPhong());
				lblSoPhong[i].setText(model.getMaPhong());
				lblGia[i].setText(String.valueOf(formatter.format(model.getGia())) + " VNĐ");
				lblTrangThai[i].setText(model.getTrangThai());

				Color mau1 = new Color(26, 246, 127);

				if (lblTrangThai[i].getText().equals("Đã Đặt")) {
					pnPhong[i].setBackground(mau1);
				}
				pnSerchPT2.add(pnPhong[i]);
				pnTang2.add(pnSerchPT2);

			}
			pnPhong[i].addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
				}

				@Override
				public void mousePressed(MouseEvent e) {
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					int id = phong_dao.CoutId_Tang();

					for (int j = 0; j < id; j++) {
						if (e.getSource() == pnPhong[j]) {

							if (e.isPopupTrigger() && model.getTrangThai().equals("Đã Đặt")) {
								ppm101.show(e.getComponent(), e.getX(), e.getY());
								mnuDatPhong.setEnabled(false);
								mnuDonPhong.setEnabled(false);
								mnuTraPhong.setEnabled(true);
								mnuSuaChuaPhong.setEnabled(false);
								mnudoiPhong.setVisible(true);
							}
						}

					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					int id = phong_dao.CoutId_Tang();
					for (int j = 0; j < id; j++) {
						if (e.getSource() == pnPhong[j]) {
							Color mau = new Color(0, 153, 153);
							pnPhong[j].setBorder(javax.swing.BorderFactory.createLineBorder(mau, 5));
						}

					}

				}

				@Override
				public void mouseExited(MouseEvent e) {
					int id = phong_dao.CoutId_Tang();

					for (int j = 0; j < id; j++) {
						if (e.getSource() == pnPhong[j]) {

							pnPhong[j].setBorder(javax.swing.BorderFactory.createEmptyBorder());
						}

					}
				}
			});

		}
	}

	void selectTrong() {
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		lblSLPhong.setText("Số Lượng Phòng: " + phong_dao.CoutId_Tang());
		pnSerchPT1.setBackground(Color.WHITE);
		pnSerchPT2.setBackground(Color.WHITE);
		mnuSuaChuaPhong.setVisible(false);
		mnudoiPhong.setVisible(false);
		for (i = 0; i < phong_dao.CoutId_Tang(); i++) {

			pnPhong[i] = new JPanel();
			lblLoaiPhong[i] = new JLabel();
			lblSoPhong[i] = new JLabel();
			lblGia[i] = new JLabel();
			lblTrangThai[i] = new JLabel();
			pnPhong[i].setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
			pnPhong[i].setBorder(javax.swing.BorderFactory.createEtchedBorder());

			lblLoaiPhong[i].setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
			lblLoaiPhong[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			lblLoaiPhong[i].setText("LOẠI PHÒNG");

			pnPhong[i].add(lblLoaiPhong[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 160, 30));

			lblSoPhong[i].setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
			lblSoPhong[i].setForeground(new java.awt.Color(0, 102, 102));
			lblSoPhong[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			lblSoPhong[i].setText("101");

			pnPhong[i].add(lblSoPhong[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 160, 40));

			lblGia[i].setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
			lblGia[i].setForeground(new java.awt.Color(255, 51, 0));
			lblGia[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			lblGia[i].setText("Giá");

			pnPhong[i].add(lblGia[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 140, 60));

			lblTrangThai[i].setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
			lblTrangThai[i].setForeground(new java.awt.Color(255, 255, 255));
			lblTrangThai[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			lblTrangThai[i].setText("Trạng Thái:");

			pnPhong[i].add(lblTrangThai[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 140, 40));

			Phong model = list_phong.get(i);

			if (model.getMaTang() == 1 && model.getTrangThai().equals("Trống")) {

				lblLoaiPhong[i].setText(model.getTenPhong());
				lblSoPhong[i].setText(model.getMaPhong());
				lblGia[i].setText(String.valueOf(formatter.format(model.getGia())) + " VNĐ");
				lblTrangThai[i].setText(model.getTrangThai());

				Color mau = new Color(204, 204, 204);

				pnPhong[i].setBackground(mau);
				pnSerchPT1.add(pnPhong[i]);
				pnTang1.add(pnSerchPT1);

			} else if (model.getMaTang() == 2 && model.getTrangThai().equals("Trống")) {
				lblLoaiPhong[i].setText(model.getTenPhong());
				lblSoPhong[i].setText(model.getMaPhong());
				lblGia[i].setText(String.valueOf(formatter.format(model.getGia())) + " VNĐ");
				lblTrangThai[i].setText(model.getTrangThai());

				Color mau = new Color(204, 204, 204);

				if (lblTrangThai[i].getText().equals("Trống")) {
					pnPhong[i].setBackground(mau);
				}
				pnSerchPT2.add(pnPhong[i]);
				pnTang2.add(pnSerchPT2);

			}
			pnPhong[i].addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
				}

				@Override
				public void mousePressed(MouseEvent e) {
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					int id = phong_dao.CoutId_Tang();

					for (int j = 0; j < id; j++) {
						if (e.getSource() == pnPhong[j]) {

							if (e.isPopupTrigger() && model.getTrangThai().equals("Trống")) {
								ppm101.show(e.getComponent(), e.getX(), e.getY());
								mnuDatPhong.setEnabled(true);
								mnuDonPhong.setEnabled(false);
								mnuTraPhong.setEnabled(false);
								mnuSuaChuaPhong.setEnabled(true);
							}
						}

					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					int id = phong_dao.CoutId_Tang();
					for (int j = 0; j < id; j++) {
						if (e.getSource() == pnPhong[j]) {
							phong = phong_dao.selectbyID(lblSoPhong[j].getText());

							Color mau = new Color(0, 153, 153);
							pnPhong[j].setBorder(javax.swing.BorderFactory.createLineBorder(mau, 5));
						}

					}

				}

				@Override
				public void mouseExited(MouseEvent e) {
					int id = phong_dao.CoutId_Tang();

					for (int j = 0; j < id; j++) {
						if (e.getSource() == pnPhong[j]) {

							pnPhong[j].setBorder(javax.swing.BorderFactory.createEmptyBorder());
						}

					}
				}
			});

		}
	}

	void selectDangDon() {
		lblSLPhong.setText("Số Lượng Phòng: " + phong_dao.CoutId_DangDon());
		pnSerchPT1.setBackground(Color.WHITE);
		pnSerchPT2.setBackground(Color.WHITE);
		mnuSuaChuaPhong.setVisible(false);
		mnudoiPhong.setVisible(false);
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		for (i = 0; i < phong_dao.CoutId_Tang(); i++) {

			pnPhong[i] = new JPanel();
			lblLoaiPhong[i] = new JLabel();
			lblSoPhong[i] = new JLabel();
			lblGia[i] = new JLabel();
			lblTrangThai[i] = new JLabel();

			pnPhong[i].setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
			pnPhong[i].setBorder(javax.swing.BorderFactory.createEtchedBorder());

			lblLoaiPhong[i].setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
			lblLoaiPhong[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			lblLoaiPhong[i].setText("LOẠI PHÒNG");

			pnPhong[i].add(lblLoaiPhong[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 160, 30));
			lblSoPhong[i].setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
			lblSoPhong[i].setForeground(new java.awt.Color(0, 102, 102));
			lblSoPhong[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			lblSoPhong[i].setText("101");

			pnPhong[i].add(lblSoPhong[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 160, 40));

			lblGia[i].setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
			lblGia[i].setForeground(new java.awt.Color(255, 51, 0));
			lblGia[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			lblGia[i].setText("Giá");

			pnPhong[i].add(lblGia[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 140, 60));

			lblTrangThai[i].setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
			lblTrangThai[i].setForeground(new java.awt.Color(255, 255, 255));
			lblTrangThai[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			lblTrangThai[i].setText("Trạng Thái:");

			pnPhong[i].add(lblTrangThai[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 140, 40));

			Phong model = list_phong.get(i);

			if (model.getMaTang() == 1 && model.getTrangThai().equals("Đang Dọn Phòng")) {

				lblLoaiPhong[i].setText(model.getTenPhong());
				lblSoPhong[i].setText(model.getMaPhong());
				lblGia[i].setText(String.valueOf(formatter.format(model.getGia())) + " VNĐ");
				lblTrangThai[i].setText(model.getTrangThai());

				Color mau2 = new Color(255, 204, 0);

				pnPhong[i].setBackground(mau2);
				pnSerchPT1.add(pnPhong[i]);
				pnTang1.add(pnSerchPT1);

			} else if (model.getMaTang() == 2 && model.getTrangThai().equals("Đang Dọn Phòng")) {
				lblLoaiPhong[i].setText(model.getTenPhong());
				lblSoPhong[i].setText(model.getMaPhong());
				lblGia[i].setText(String.valueOf(formatter.format(model.getGia())) + " VNĐ");
				lblTrangThai[i].setText(model.getTrangThai());

				Color mau2 = new Color(255, 204, 0);

				pnPhong[i].setBackground(mau2);

				pnSerchPT2.add(pnPhong[i]);
				pnTang2.add(pnSerchPT2);

			}
			pnPhong[i].addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
				}

				@Override
				public void mousePressed(MouseEvent e) {
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					int id = phong_dao.CoutId_Tang();

					for (int j = 0; j < id; j++) {
						if (e.getSource() == pnPhong[j]) {
							if (e.isPopupTrigger() && model.getTrangThai().equals("Đang Dọn Phòng")) {
								ppm101.show(e.getComponent(), e.getX(), e.getY());
								mnuDatPhong.setEnabled(false);
								mnuDonPhong.setEnabled(true);
								mnuTraPhong.setEnabled(false);
								mnuSuaChuaPhong.setEnabled(false);
								mnudoiPhong.setVisible(false);
							}
						}

					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					int id = phong_dao.CoutId_Tang();
					for (int j = 0; j < id; j++) {
						if (e.getSource() == pnPhong[j]) {
							Color mau = new Color(0, 153, 153);
							pnPhong[j].setBorder(javax.swing.BorderFactory.createLineBorder(mau, 5));
						}

					}

				}

				@Override
				public void mouseExited(MouseEvent e) {
					int id = phong_dao.CoutId_Tang();

					for (int j = 0; j < id; j++) {
						if (e.getSource() == pnPhong[j]) {

							pnPhong[j].setBorder(javax.swing.BorderFactory.createEmptyBorder());
						}

					}
				}
			});

		}
	}

	void selectDangSua() {
		lblSLPhong.setText("Số Lượng Phòng: " + phong_dao.CoutId_DangSua());
		pnSerchPT1.setBackground(Color.WHITE);
		pnSerchPT2.setBackground(Color.WHITE);
		mnuSuaChuaPhong.setVisible(false);
		mnudoiPhong.setVisible(false);
		for (i = 0; i < phong_dao.CoutId_Tang(); i++) {

			pnPhong[i] = new JPanel();
			lblLoaiPhong[i] = new JLabel();
			lblSoPhong[i] = new JLabel();
			lblGia[i] = new JLabel();
			lblTrangThai[i] = new JLabel();

			pnPhong[i].setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
			pnPhong[i].setBorder(javax.swing.BorderFactory.createEtchedBorder());

			lblLoaiPhong[i].setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
			lblLoaiPhong[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			lblLoaiPhong[i].setText("LOẠI PHÒNG");

			pnPhong[i].add(lblLoaiPhong[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 160, 30));

			lblSoPhong[i].setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
			lblSoPhong[i].setForeground(new java.awt.Color(0, 102, 102));
			lblSoPhong[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			lblSoPhong[i].setText("101");

			pnPhong[i].add(lblSoPhong[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 160, 40));

			lblGia[i].setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
			lblGia[i].setForeground(new java.awt.Color(255, 51, 0));
			lblGia[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			lblGia[i].setText("Giá");

			pnPhong[i].add(lblGia[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 140, 60));

			lblTrangThai[i].setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
			lblTrangThai[i].setForeground(new java.awt.Color(255, 255, 255));
			lblTrangThai[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			lblTrangThai[i].setText("Trạng Thái:");

			pnPhong[i].add(lblTrangThai[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 140, 40));

			Phong model = list_phong.get(i);

			if (model.getMaTang() == 1 && model.getTrangThai().equals("Đang Sửa Chữa")) {

				lblLoaiPhong[i].setText(model.getTenPhong());
				lblSoPhong[i].setText(model.getMaPhong());
				lblGia[i].setText(String.valueOf(model.getGia()) + " VNĐ");
				lblTrangThai[i].setText(model.getTrangThai());

				pnPhong[i].setBackground(Color.RED);
				lblGia[i].setVisible(false);

				pnSerchPT1.add(pnPhong[i]);
				pnTang1.add(pnSerchPT1);

			} else if (model.getMaTang() == 2 && model.getTrangThai().equals("Đang Sửa Chữa")) {
				lblLoaiPhong[i].setText(model.getTenPhong());
				lblSoPhong[i].setText(model.getMaPhong());
				lblGia[i].setText(String.valueOf(model.getGia()) + " VNĐ");
				lblTrangThai[i].setText(model.getTrangThai());

				pnPhong[i].setBackground(Color.RED);
				lblGia[i].setVisible(false);

				pnSerchPT2.add(pnPhong[i]);
				pnTang2.add(pnSerchPT2);

			}
			pnPhong[i].addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
				}

				@Override
				public void mousePressed(MouseEvent e) {
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					int id = phong_dao.CoutId_Tang();

					for (int j = 0; j < id; j++) {
						if (e.getSource() == pnPhong[j]) {

							if (e.isPopupTrigger() && model.getTrangThai().equals("Đang Sửa Chữa")) {
								ppm102.show(e.getComponent(), e.getX(), e.getY());
							}
						}

					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					int id = phong_dao.CoutId_Tang();
					for (int j = 0; j < id; j++) {
						if (e.getSource() == pnPhong[j]) {
							Color mau = new Color(0, 153, 153);
							pnPhong[j].setBorder(javax.swing.BorderFactory.createLineBorder(mau, 5));
						}

					}

				}

				@Override
				public void mouseExited(MouseEvent e) {
					int id = phong_dao.CoutId_Tang();

					for (int j = 0; j < id; j++) {
						if (e.getSource() == pnPhong[j]) {

							pnPhong[j].setBorder(javax.swing.BorderFactory.createEmptyBorder());
						}

					}
				}
			});

		}
	}

	void fillPHONG() {
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		phong_dao = new DaoPhong();
		pnPhong = new JPanel[phong_dao.CoutId_Tang()];
		lblLoaiPhong = new JLabel[phong_dao.CoutId_Tang()];
		lblSoPhong = new JLabel[phong_dao.CoutId_Tang()];
		lblGia = new JLabel[phong_dao.CoutId_Tang()];
		lblTrangThai = new JLabel[phong_dao.CoutId_Tang()];
		rdioTatCa.setSelected(true);
		lblSLPhong.setText("Số Lượng Phòng: " + list_phong.size());
		pnSerchPT1.setBackground(Color.WHITE);
		pnSerchPT2.setBackground(Color.WHITE);

		for (int i = 0; i < phong_dao.CoutId_Tang(); i++) {

			pnPhong[i] = new JPanel();
			lblLoaiPhong[i] = new JLabel();
			lblSoPhong[i] = new JLabel();
			lblGia[i] = new JLabel();
			lblTrangThai[i] = new JLabel();

			pnPhong[i].setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
			pnPhong[i].setBorder(javax.swing.BorderFactory.createEtchedBorder());

			lblLoaiPhong[i].setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
			lblLoaiPhong[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			lblLoaiPhong[i].setText("LOẠI PHÒNG");

			pnPhong[i].add(lblLoaiPhong[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 160, 30));

			lblSoPhong[i].setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
			lblSoPhong[i].setForeground(new java.awt.Color(0, 102, 102));
			lblSoPhong[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			lblSoPhong[i].setText("101");

			pnPhong[i].add(lblSoPhong[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 160, 40));

			lblGia[i].setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
			lblGia[i].setForeground(new java.awt.Color(255, 51, 0));
			lblGia[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			lblGia[i].setText("Giá");

			pnPhong[i].add(lblGia[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 140, 40));

			lblTrangThai[i].setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
			lblTrangThai[i].setForeground(new java.awt.Color(255, 255, 255));
			lblTrangThai[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			lblTrangThai[i].setText("Trạng Thái:");

			pnPhong[i].add(lblTrangThai[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 140, 40));

			Phong model = list_phong.get(i);
			if (model.getMaTang() == 1) {
				lblLoaiPhong[i].setText(model.getTenPhong());
				lblSoPhong[i].setText(model.getMaPhong());
				lblGia[i].setText(String.valueOf(formatter.format(model.getGia())) + " VNĐ");
				lblTrangThai[i].setText(model.getTrangThai());

				Color mau = new Color(204, 204, 204);
				Color mau1 = new Color(26, 246, 127);
				Color mau2 = new Color(255, 204, 0);

				if (lblTrangThai[i].getText().equals("Đã Đặt")) {
					pnPhong[i].setBackground(mau1);
				} else if (lblTrangThai[i].getText().equals("Trống")) {
					pnPhong[i].setBackground(mau);
				} else if (lblTrangThai[i].getText().equals("Đang Dọn Phòng")) {
					pnPhong[i].setBackground(mau2);
					lblGia[i].setVisible(true);
				} else {
					pnPhong[i].setBackground(Color.RED);
					lblGia[i].setVisible(false);
				}
				pnSerchPT1.add(pnPhong[i]);
				pnTang1.add(pnSerchPT1);

			} else {
				lblLoaiPhong[i].setText(model.getTenPhong());
				lblSoPhong[i].setText(model.getMaPhong());
				lblGia[i].setText(String.valueOf(formatter.format(model.getGia())) + " VNĐ");
				lblTrangThai[i].setText(model.getTrangThai());

				Color mau = new Color(204, 204, 204);
				Color mau1 = new Color(26, 246, 127);
				Color mau2 = new Color(255, 204, 0);

				if (lblTrangThai[i].getText().equals("Đã Đặt")) {
					pnPhong[i].setBackground(mau1);
				} else if (lblTrangThai[i].getText().equals("Trống")) {
					pnPhong[i].setBackground(mau);
				} else if (lblTrangThai[i].getText().equals("Đang Dọn Phòng")) {
					pnPhong[i].setBackground(mau2);
					lblGia[i].setVisible(true);
				} else {
					pnPhong[i].setBackground(Color.RED);
					lblGia[i].setVisible(false);
				}
				pnSerchPT2.add(pnPhong[i]);
				pnTang2.add(pnSerchPT2);

			}
			pnPhong[i].addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
				}

				@Override
				public void mousePressed(MouseEvent e) {
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					int id = phong_dao.CoutId_Tang();

					for (int j = 0; j < id; j++) {
						if (e.getSource() == pnPhong[j]) {

							if (e.isPopupTrigger() && model.getTrangThai().equals("Trống")) {
								ppm101.show(e.getComponent(), e.getX(), e.getY());
								mnuDatPhong.setEnabled(true);
								mnuDonPhong.setEnabled(false);
								mnuTraPhong.setEnabled(false);
								mnudoiPhong.setVisible(false);
								mnuSuaChuaPhong.setEnabled(true);
							} else if (e.isPopupTrigger() && model.getTrangThai().equals("Đã Đặt")) {
								ppm101.show(e.getComponent(), e.getX(), e.getY());
								mnuDatPhong.setEnabled(false);
								mnuDonPhong.setEnabled(false);
								mnuTraPhong.setEnabled(true);
								mnudoiPhong.setVisible(true);
								mnuSuaChuaPhong.setEnabled(false);
							} else if (e.isPopupTrigger() && model.getTrangThai().equals("Đang Dọn Phòng")) {
								ppm101.show(e.getComponent(), e.getX(), e.getY());
								mnuDatPhong.setEnabled(false);
								mnuDonPhong.setEnabled(true);
								mnudoiPhong.setVisible(false);
								mnuTraPhong.setEnabled(false);
								mnuSuaChuaPhong.setEnabled(false);
							} else if (e.isPopupTrigger() && model.getTrangThai().equals("Đang Sửa Chữa")) {
								ppm102.show(e.getComponent(), e.getX(), e.getY());
							} // truong hop doi phong

						}

					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					int id = phong_dao.CoutId_Tang();
					for (int j = 0; j < id; j++) {
						if (e.getSource() == pnPhong[j]) {
							phong = phong_dao.selectbyID(lblSoPhong[j].getText());

							Color mau = new Color(0, 153, 153);
							pnPhong[j].setBorder(javax.swing.BorderFactory.createLineBorder(mau, 5));
						}

					}

				}

				@Override
				public void mouseExited(MouseEvent e) {
					int id = phong_dao.CoutId_Tang();

					for (int j = 0; j < id; j++) {
						if (e.getSource() == pnPhong[j]) {

							pnPhong[j].setBorder(javax.swing.BorderFactory.createEmptyBorder());
						}

					}
				}
			});

		}
	}

	void filltable() {
		DefaultTableModel model = (DefaultTableModel) tblQuanLyphong.getModel();

		model.setRowCount(0);
		try {
			list_phong = phong_dao.selectAll();
			for (Phong phong : list_phong) {
				Object[] row = { phong.getMaPhong(), phong.getTenPhong(), phong.getMaTang(), phong.getMoTa(),
						phong.getGia(), phong.getTrangThai() };
				model.addRow(row);
			}
		} catch (Exception e) {

			System.out.println(e);
		}
	}

	void edit() {
		try {
			String maPH = (String) tblQuanLyphong.getValueAt(this.row, 0);
			Phong model = phong_dao.selectbyID(maPH);
			if (model != null) {
				setForm(model);

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	void setForm(Phong model) {
		txtMaPhong.setText(model.getMaPhong());
		txtTenPhong.setText(model.getTenPhong());
		txtGia.setText(String.valueOf(model.getGia()));
		cboTrangThai.setSelectedItem(model.getTrangThai());
		txtTang.setText(String.valueOf(model.getMaTang()));
		txaMoTa.setText(model.getMoTa());
		txtMaLoaiPhong.setText(String.valueOf(model.getMaLoaiPhong()));
	}

	Phong getForm() {
		Phong model = new Phong();
		model.setMaPhong(txtMaPhong.getText());
		model.setTenPhong(txtTenPhong.getText());
		model.setTrangThai(String.valueOf(cboTrangThai.getSelectedItem()));
		model.setMaTang(Integer.valueOf(txtTang.getText()));

		model.setMoTa(txaMoTa.getText());
		model.setGia(Double.valueOf(txtGia.getText()));
		model.setMaLoaiPhong(Integer.valueOf(txtMaLoaiPhong.getText()));
		return model;

	}

	void clearForm() {
		this.setForm(new Phong());
		cboTrangThai.setSelectedIndex(0);
	}

	/**
	 *
	 */
	void checkVali() {

		if (txtMaPhong.getText().isEmpty()) {
			error.append("Vui lòng nhập mã phòng\n");
		} else if (txtMaPhong.getText().length() > 3) {
			error.append("Mã phòng chỉ dưới 3 ký tự\n");
		}

		if (txtMaLoaiPhong.getText().isEmpty()) {
			error.append("Vui lòng nhập mã loại phòng\n");
		} else {
			try {
				int MLPhong = Integer.parseInt(txtMaLoaiPhong.getText());
				if (MLPhong < 0) {
					error.append("Mã loại phòng không tồn tại\n");
				}
			} catch (Exception e) {
				error.append("Mã Loại Phòng không đúng định dạng\n");
			}
		}
		if (txtGia.getText().isEmpty()) {
			error.append("Vui lòng nhập giá phòng\n");
		} else {
			try {
				double gia = Double.parseDouble(txtMaLoaiPhong.getText());
				if (gia < 0) {
					error.append("Giá phòng phải lớn hơn 0\n");
				}
			} catch (Exception e) {
				error.append("Giá Phòng không đúng định dạng\n");
			}
		}
		if (txaMoTa.getText().isEmpty()) {
			error.append("Vui lòng nhập mô tả phòng\n");
		}
		if (txtTang.getText().isEmpty()) {
			error.append("Vui lòng nhập giá phòng\n");
		} else {
			try {
				int tang = Integer.parseInt(txtMaLoaiPhong.getText());
				if (tang < 0) {
					error.append("Tầng không tồn tại\n");
				}
			} catch (Exception e) {
				error.append("Tầng không đúng định dạng\n");
			}
		}

	}

	boolean check() {

		checkVali();
		if (error.length() > 0) {
			DialogHelper.alert(this, error.toString());
			error.setLength(0);
			return false;
		}
		return true;

	}

	void insert() {
		Phong model = getForm();
		if (phong_dao.selectbyID(model.getMaPhong()) != null) {
			error.append("Mã Phòng đã tồn tại\n");
		}
		if (!check()) {
			return;
		}
		try {
			phong_dao.insert(model);
			this.filltable();
			this.RemovePHONG();
			this.fillPHONG();
			this.clearForm();
			lblSLPhong.setText("Số Lượng Phòng: " + list_phong.size());
			JOptionPane.showMessageDialog(this, "Insert Thanh Cong");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Insert that bai");
			System.out.println(e);

		}
	}

	void update() {
		Phong model = getForm();
		if (!check()) {
			return;
		}
		try {
			phong_dao.update(model);
			this.filltable();
			this.RemovePHONG();
			this.fillPHONG();
			JOptionPane.showMessageDialog(this, "Update Thanh Cong");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Update that bai");
			System.out.println(e);
		}
	}

	void delete() {
		String maPhong = txtMaPhong.getText();
		try {
			phong_dao.delete(maPhong);
			this.filltable();
			this.RemovePHONG();
			this.fillPHONG();
			this.clearForm();
			lblSLPhong.setText("Số Lượng Phòng: " + list_phong.size());
			JOptionPane.showMessageDialog(this, "Delete Thanh Cong");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Delete That Bai");
			System.out.println(e);
		}
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		ppm101 = new javax.swing.JPopupMenu();
		mnuDatPhong = new javax.swing.JMenuItem();
		mnuTraPhong = new javax.swing.JMenuItem();
		mnuDonPhong = new javax.swing.JMenuItem();
		mnuSuaChuaPhong = new javax.swing.JMenuItem();
		mnudoiPhong = new javax.swing.JMenuItem();
		mnuChuyenVaoPhong = new javax.swing.JMenuItem();
		ppm102 = new javax.swing.JPopupMenu();
		mnuOpenPhong = new javax.swing.JMenuItem();
		buttonGroup1 = new javax.swing.ButtonGroup();
		buttonGroup2 = new javax.swing.ButtonGroup();
		pn1 = new javax.swing.JPanel();
		ScrollPhong = new Utils.ScrollPaneWin11();
		tblQuanLyphong = new javax.swing.JTable();
		scrollDatPhong = new Utils.ScrollPaneWin11();
		jPanel2 = new javax.swing.JPanel();
		pnTang1 = new javax.swing.JPanel();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		pnTang2 = new javax.swing.JPanel();
		jPanel10 = new javax.swing.JPanel();
		jPanel9 = new javax.swing.JPanel();
		jPanel8 = new javax.swing.JPanel();
		jPanel7 = new javax.swing.JPanel();
		jPanel5 = new javax.swing.JPanel();
		jPanel11 = new javax.swing.JPanel();
		jPanel12 = new javax.swing.JPanel();
		jPanel13 = new javax.swing.JPanel();
		jPanel14 = new javax.swing.JPanel();
		jPanel15 = new javax.swing.JPanel();
		rdioTatCa = new javax.swing.JRadioButton();
		rdioDangDon = new javax.swing.JRadioButton();
		rdioDaDat = new javax.swing.JRadioButton();
		rdioTrong = new javax.swing.JRadioButton();
		rdioDangSua = new javax.swing.JRadioButton();
		lblSLPhong = new javax.swing.JLabel();
		jPanel4 = new javax.swing.JPanel();
		jLabel19 = new javax.swing.JLabel();
		btnTru = new Utils.Button();
		btncong = new Utils.Button();
		pnEditPhong = new javax.swing.JPanel();
		jLabel4 = new javax.swing.JLabel();
		txtMaPhong = new javax.swing.JTextField();
		txtTenPhong = new javax.swing.JTextField();
		jLabel5 = new javax.swing.JLabel();
		txtTang = new javax.swing.JTextField();
		jLabel6 = new javax.swing.JLabel();
		jLabel9 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		scrollPaneWin113 = new Utils.ScrollPaneWin11();
		txaMoTa = new javax.swing.JTextArea();
		btnXoa = new Utils.Button();
		btnThem = new Utils.Button();
		btnSua = new Utils.Button();
		btnMoi = new Utils.Button();
		jLabel18 = new javax.swing.JLabel();
		txtGia = new javax.swing.JTextField();
		cboTrangThai = new javax.swing.JComboBox<>();
		jLabel12 = new javax.swing.JLabel();
		txtMaLoaiPhong = new javax.swing.JTextField();
		date_duklienphongtrong = new com.toedter.calendar.JDateChooser();
		jLabel1 = new javax.swing.JLabel();
		btnHuyDoiPhong = new Utils.Button();
		btn_DKphong = new Utils.Button();

		mnuDatPhong.setText("Đặt Phòng");
		mnuDatPhong.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				mnuDatPhongActionPerformed(evt);
			}
		});
		ppm101.add(mnuDatPhong);

		mnuTraPhong.setText("Trả Phòng");
		mnuTraPhong.setEnabled(false);
		mnuTraPhong.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				mnuTraPhongActionPerformed(evt);
			}
		});
		ppm101.add(mnuTraPhong);

		mnuDonPhong.setText("Đã Dọn");
		mnuDonPhong.setEnabled(false);
		mnuDonPhong.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				mnuDonPhongActionPerformed(evt);
			}
		});
		ppm101.add(mnuDonPhong);

		mnuSuaChuaPhong.setText("Sửa Phòng");
		mnuSuaChuaPhong.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				mnuSuaChuaPhongActionPerformed(evt);
			}
		});
		ppm101.add(mnuSuaChuaPhong);

		mnudoiPhong.setText("Đổi Phòng Này");
		mnudoiPhong.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				mnudoiPhongActionPerformed(evt);
			}
		});
		ppm101.add(mnudoiPhong);

		mnuChuyenVaoPhong.setText("Chuyển Vào Phòng");
		mnuChuyenVaoPhong.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				mnuChuyenVaoPhongMousePressed(evt);
			}
		});
		mnuChuyenVaoPhong.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				mnuChuyenVaoPhongActionPerformed(evt);
			}
		});
		ppm101.add(mnuChuyenVaoPhong);

		ppm102.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				ppm102MouseEntered(evt);
			}
		});

		mnuOpenPhong.setText("Mở Lại Phòng");
		mnuOpenPhong.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				mnuOpenPhongMouseEntered(evt);
			}
		});
		mnuOpenPhong.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				mnuOpenPhongActionPerformed(evt);
			}
		});
		ppm102.add(mnuOpenPhong);

		pn1.setBackground(new java.awt.Color(255, 255, 255));
		pn1.setMinimumSize(new java.awt.Dimension(1000, 620));
		pn1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		tblQuanLyphong.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null } },
				new String[] { "Số Phòng", "Tên Phòng", "Tầng", "Mô Tả", "Giá Phòng", "Trạng Thái" }));
		tblQuanLyphong.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tblQuanLyphongMouseClicked(evt);
			}
		});
		ScrollPhong.setViewportView(tblQuanLyphong);

		pn1.add(ScrollPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 112, 1000, 640));

		scrollDatPhong.setEnabled(false);

		jPanel2.setBackground(new java.awt.Color(255, 255, 255));
		jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

		pnTang1.setBackground(new java.awt.Color(255, 255, 255));
		pnTang1.setForeground(new java.awt.Color(255, 255, 255));
		pnTang1.setLayout(new java.awt.GridLayout(1, 0));

		jLabel7.setBackground(new java.awt.Color(102, 204, 255));
		jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel7.setText("TẦNG 1");
		jLabel7.setOpaque(true);

		jLabel8.setBackground(new java.awt.Color(102, 204, 255));
		jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel8.setText("TẦNG 2");
		jLabel8.setOpaque(true);

		pnTang2.setBackground(new java.awt.Color(255, 255, 255));
		pnTang2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		pnTang2.setLayout(new java.awt.GridLayout(1, 0));

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addGap(6, 6, 6).addGroup(jPanel2Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE)
						.addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(pnTang1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(pnTang2, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(0, 0, Short.MAX_VALUE)))));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addGap(4, 4, 4)
						.addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(9, 9, 9)
						.addComponent(pnTang1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(pnTang2, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		scrollDatPhong.setViewportView(jPanel2);

		pn1.add(scrollDatPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 1000, 640));

		jPanel10.setBackground(new java.awt.Color(255, 255, 204));
		jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		jPanel9.setBackground(new java.awt.Color(204, 204, 204));

		javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
		jPanel9.setLayout(jPanel9Layout);
		jPanel9Layout.setHorizontalGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 20, Short.MAX_VALUE));
		jPanel9Layout.setVerticalGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 20, Short.MAX_VALUE));

		jPanel8.setBackground(new java.awt.Color(255, 204, 0));

		javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
		jPanel8.setLayout(jPanel8Layout);
		jPanel8Layout.setHorizontalGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 20, Short.MAX_VALUE));
		jPanel8Layout.setVerticalGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 20, Short.MAX_VALUE));

		jPanel7.setBackground(new java.awt.Color(255, 0, 0));

		javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
		jPanel7.setLayout(jPanel7Layout);
		jPanel7Layout.setHorizontalGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 20, Short.MAX_VALUE));
		jPanel7Layout.setVerticalGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 20, Short.MAX_VALUE));

		jPanel5.setBackground(new java.awt.Color(26, 246, 127));

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 20, Short.MAX_VALUE));
		jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 20, Short.MAX_VALUE));

		jPanel11.setLayout(new java.awt.GridLayout(1, 0));

		jPanel12.setBackground(new java.awt.Color(26, 246, 127));

		javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
		jPanel12.setLayout(jPanel12Layout);
		jPanel12Layout.setHorizontalGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 7, Short.MAX_VALUE));
		jPanel12Layout.setVerticalGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 20, Short.MAX_VALUE));

		jPanel11.add(jPanel12);

		jPanel13.setBackground(new java.awt.Color(204, 204, 204));

		javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
		jPanel13.setLayout(jPanel13Layout);
		jPanel13Layout.setHorizontalGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 7, Short.MAX_VALUE));
		jPanel13Layout.setVerticalGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 20, Short.MAX_VALUE));

		jPanel11.add(jPanel13);

		jPanel14.setBackground(new java.awt.Color(255, 204, 0));

		javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
		jPanel14.setLayout(jPanel14Layout);
		jPanel14Layout.setHorizontalGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 7, Short.MAX_VALUE));
		jPanel14Layout.setVerticalGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 20, Short.MAX_VALUE));

		jPanel11.add(jPanel14);

		jPanel15.setBackground(new java.awt.Color(255, 0, 0));

		javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
		jPanel15.setLayout(jPanel15Layout);
		jPanel15Layout.setHorizontalGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 7, Short.MAX_VALUE));
		jPanel15Layout.setVerticalGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 20, Short.MAX_VALUE));

		jPanel11.add(jPanel15);

		buttonGroup2.add(rdioTatCa);
		rdioTatCa.setSelected(true);
		rdioTatCa.setText("Tất Cả");
		rdioTatCa.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				rdioTatCaActionPerformed(evt);
			}
		});

		buttonGroup2.add(rdioDangDon);
		rdioDangDon.setText("Đang Dọn Dẹp");
		rdioDangDon.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				rdioDangDonActionPerformed(evt);
			}
		});

		buttonGroup2.add(rdioDaDat);
		rdioDaDat.setText("Đã Đặt");
		rdioDaDat.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				rdioDaDatActionPerformed(evt);
			}
		});

		buttonGroup2.add(rdioTrong);
		rdioTrong.setText("Trống");
		rdioTrong.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				rdioTrongActionPerformed(evt);
			}
		});

		buttonGroup2.add(rdioDangSua);
		rdioDangSua.setText("Đang Sửa Phòng");
		rdioDangSua.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				rdioDangSuaActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
		jPanel10.setLayout(jPanel10Layout);
		jPanel10Layout.setHorizontalGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel10Layout.createSequentialGroup().addGroup(jPanel10Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel10Layout.createSequentialGroup().addGap(99, 99, 99)
								.addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, 0).addComponent(rdioDaDat).addGap(59, 59, 59)
								.addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, 0).addComponent(rdioTrong))
						.addGroup(jPanel10Layout.createSequentialGroup().addGap(98, 98, 98)
								.addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, 0).addComponent(rdioDangDon).addGap(18, 18, 18)
								.addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, 0).addComponent(rdioDangSua)))
						.addGap(0, 0, Short.MAX_VALUE))
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						jPanel10Layout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, 0).addComponent(rdioTatCa).addGap(306, 306, 306)));
		jPanel10Layout.setVerticalGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(rdioDaDat)
								.addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(rdioTrong))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(rdioTatCa))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(rdioDangDon)
								.addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(rdioDangSua))
						.addContainerGap()));

		pn1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, 390, -1));

		lblSLPhong.setBackground(new java.awt.Color(255, 204, 204));
		lblSLPhong.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
		lblSLPhong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblSLPhong.setText("SL Phòng: 39");
		lblSLPhong.setOpaque(true);
		lblSLPhong.addAncestorListener(new javax.swing.event.AncestorListener() {
			public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
				lblSLPhongAncestorAdded(evt);
			}

			public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
			}

			public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
			}
		});
		pn1.add(lblSLPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 310, 40));

		jPanel4.setBackground(new java.awt.Color(255, 255, 204));
		jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel19.setText("Chỉnh Sửa Phòng");
		jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 278, 36));

		btnTru.setForeground(new java.awt.Color(255, 255, 255));
		btnTru.setText("-");
		btnTru.setColor(new java.awt.Color(0, 102, 51));
		btnTru.setColorOver(new java.awt.Color(0, 153, 51));
		btnTru.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		btnTru.setRippleColor(new java.awt.Color(153, 255, 153));
		btnTru.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnTruActionPerformed(evt);
			}
		});
		jPanel4.add(btnTru, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 240, 40));

		btncong.setForeground(new java.awt.Color(255, 255, 255));
		btncong.setText("+");
		btncong.setColor(new java.awt.Color(0, 102, 51));
		btncong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		btncong.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				btncongMouseClicked(evt);
			}
		});
		btncong.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btncongActionPerformed(evt);
			}
		});
		jPanel4.add(btncong, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 240, 40));

		pn1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 10, 330, 90));

		pnEditPhong.setBackground(new java.awt.Color(204, 255, 204));
		pnEditPhong.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		pnEditPhong.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel4.setText("Mã Phòng:");
		pnEditPhong.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, 30));

		txtMaPhong.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtMaPhongActionPerformed(evt);
			}
		});
		pnEditPhong.add(txtMaPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 200, 30));

		txtTenPhong.setEnabled(false);
		txtTenPhong.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtTenPhongActionPerformed(evt);
			}
		});
		pnEditPhong.add(txtTenPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 200, 30));

		jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel5.setText("Tên Phòng:");
		pnEditPhong.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 100, 30));

		txtTang.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtTangActionPerformed(evt);
			}
		});
		pnEditPhong.add(txtTang, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 200, 30));

		jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel6.setText("Trạng Thái:");
		pnEditPhong.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 110, 30));

		jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel9.setText("Tầng:");
		pnEditPhong.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 60, 30));

		jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel10.setText("Mô Tả:");
		pnEditPhong.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 60, -1));

		txaMoTa.setColumns(20);
		txaMoTa.setRows(5);
		scrollPaneWin113.setViewportView(txaMoTa);

		pnEditPhong.add(scrollPaneWin113, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, 200, 100));

		btnXoa.setText("Xóa");
		btnXoa.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnXoaActionPerformed(evt);
			}
		});
		pnEditPhong.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 590, 130, 40));

		btnThem.setText("Thêm");
		btnThem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnThemActionPerformed(evt);
			}
		});
		pnEditPhong.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 130, 40));

		btnSua.setText("Sửa");
		btnSua.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSuaActionPerformed(evt);
			}
		});
		pnEditPhong.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 590, 130, 40));

		btnMoi.setText("Mới");
		btnMoi.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnMoiActionPerformed(evt);
			}
		});
		pnEditPhong.add(btnMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 530, 130, 40));

		jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel18.setText("Giá:");
		pnEditPhong.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 37, 30));

		txtGia.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtGiaActionPerformed(evt);
			}
		});
		pnEditPhong.add(txtGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 200, 30));

		cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(
				new String[] { "Trống", "Đã Đặt", "Đang Dọn Phòng", "Đang Sửa Chữa" }));
		pnEditPhong.add(cboTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 200, 30));

		jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel12.setText("Loại Phòng:");
		pnEditPhong.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 110, 30));

		txtMaLoaiPhong.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtMaLoaiPhongActionPerformed(evt);
			}
		});
		pnEditPhong.add(txtMaLoaiPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 200, 30));

		pn1.add(pnEditPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 100, 330, 650));

		date_duklienphongtrong.setDateFormatString(" yyyy-MM-dd");
		date_duklienphongtrong.addAncestorListener(new javax.swing.event.AncestorListener() {
			public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
				date_duklienphongtrongAncestorAdded(evt);
			}

			public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
			}

			public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
			}
		});
		pn1.add(date_duklienphongtrong, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 210, -1));

		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		jLabel1.setText("Ngày dự kiến trống phòng:");
		pn1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 170, -1));

		btnHuyDoiPhong.setText("Hủy Đổi Phòng");
		btnHuyDoiPhong.setBorderColor(new java.awt.Color(255, 153, 153));
		btnHuyDoiPhong.setColorClick(new java.awt.Color(255, 153, 153));
		btnHuyDoiPhong.setColorOver(new java.awt.Color(255, 153, 153));
		btnHuyDoiPhong.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnHuyDoiPhongActionPerformed(evt);
			}
		});
		pn1.add(btnHuyDoiPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 50, 180, 50));

		btn_DKphong.setText("Tìm");
		btn_DKphong.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn_DKphongActionPerformed(evt);
			}
		});
		pn1.add(btn_DKphong, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, -1, -1));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(pn1, javax.swing.GroupLayout.DEFAULT_SIZE, 1374, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(pn1,
				javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
	}// </editor-fold>//GEN-END:initComponents

	private void tblQuanLyphongMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tblQuanLyphongMouseClicked
		this.row = tblQuanLyphong.rowAtPoint(evt.getPoint());

		edit();
	}// GEN-LAST:event_tblQuanLyphongMouseClicked

	private void rdioTatCaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_rdioTatCaActionPerformed
		RemovePHONG();
		fillPHONG();
	}// GEN-LAST:event_rdioTatCaActionPerformed

	private void rdioDangDonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_rdioDangDonActionPerformed
		RemovePHONG();
		selectDangDon();
	}// GEN-LAST:event_rdioDangDonActionPerformed

	private void rdioDaDatActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_rdioDaDatActionPerformed
		RemovePHONG();
		selectDaDat();
	}// GEN-LAST:event_rdioDaDatActionPerformed

	private void rdioTrongActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_rdioTrongActionPerformed
		RemovePHONG();
		selectTrong();
	}// GEN-LAST:event_rdioTrongActionPerformed

	private void rdioDangSuaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_rdioDangSuaActionPerformed
		RemovePHONG();
		selectDangSua();
	}// GEN-LAST:event_rdioDangSuaActionPerformed

	private void lblSLPhongAncestorAdded(javax.swing.event.AncestorEvent evt) {// GEN-FIRST:event_lblSLPhongAncestorAdded

		lblSLPhong.setText("Số Lượng Phòng: " + list_phong.size());
	}// GEN-LAST:event_lblSLPhongAncestorAdded

	private void btnTruActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnTruActionPerformed
		exitEditPhong();
	}// GEN-LAST:event_btnTruActionPerformed

	private void btncongMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btncongMouseClicked

	}// GEN-LAST:event_btncongMouseClicked

	private void btncongActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btncongActionPerformed
		openEditPhong();
	}// GEN-LAST:event_btncongActionPerformed

	private void txtMaPhongActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtMaPhongActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_txtMaPhongActionPerformed

	private void txtTenPhongActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtTenPhongActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_txtTenPhongActionPerformed

	private void txtTangActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtTangActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_txtTangActionPerformed

	private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnXoaActionPerformed
		delete();
	}// GEN-LAST:event_btnXoaActionPerformed

	private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnThemActionPerformed
		insert();
	}// GEN-LAST:event_btnThemActionPerformed

	private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSuaActionPerformed
		update();
	}// GEN-LAST:event_btnSuaActionPerformed

	private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnMoiActionPerformed
		clearForm();
	}// GEN-LAST:event_btnMoiActionPerformed

	private void txtGiaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtGiaActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_txtGiaActionPerformed

	private void txtMaLoaiPhongActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtMaLoaiPhongActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_txtMaLoaiPhongActionPerformed

	private void mnuDatPhongActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_mnuDatPhongActionPerformed
		FormDatPhong a = new FormDatPhong(phong, NhanVien);
		a.setVisible(true);

		new Thread() {
			@Override
			public void run() {
				try {
					while (true) {
						if (a.isVisible()) {
							while (true) {
								Thread.sleep(100);
								if (!a.isVisible()) {
									filltable();
									RemovePHONG();
									fillPHONG();
									break;
								}
							}
						}

					}

				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}.start();
	}// GEN-LAST:event_mnuDatPhongActionPerformed

	private void mnuTraPhongActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_mnuTraPhongActionPerformed
		FormDatPhong a = new FormDatPhong(phong, NhanVien);
		a.setVisible(true);
		new Thread() {
			@Override
			public void run() {
				try {
					while (true) {
						if (a.isVisible()) {
							while (true) {

								Thread.sleep(100);
								if (!a.isVisible()) {
									filltable();
									RemovePHONG();
									fillPHONG();
									break;
								}
							}
						}
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}.start();
	}// GEN-LAST:event_mnuTraPhongActionPerformed

	private void mnuSuaChuaPhongActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_mnuSuaChuaPhongActionPerformed
		phong_dao.UpdateTrangThai("Đang Sửa Chữa", phong.getMaPhong());
		filltable();
		RemovePHONG();
		fillPHONG();
	}// GEN-LAST:event_mnuSuaChuaPhongActionPerformed

	private void mnuOpenPhongMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_mnuOpenPhongMouseEntered

	}// GEN-LAST:event_mnuOpenPhongMouseEntered

	private void mnuOpenPhongActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_mnuOpenPhongActionPerformed
		phong_dao.UpdateTrangThai("Trống", phong.getMaPhong());
		filltable();
		RemovePHONG();
		fillPHONG();
	}// GEN-LAST:event_mnuOpenPhongActionPerformed

	private void ppm102MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_ppm102MouseEntered

	}// GEN-LAST:event_ppm102MouseEntered

	private void mnuDonPhongActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_mnuDonPhongActionPerformed
		phong_dao.UpdateTrangThai("Trống", phong.getMaPhong());
		filltable();
		RemovePHONG();
		fillPHONG();
	}// GEN-LAST:event_mnuDonPhongActionPerformed

	private void mnudoiPhongActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_mnudoiPhongActionPerformed
		RemovePHONG();
		rdioTrong.setSelected(true);
		selectTrong();
		mnuChuyenVaoPhong.setVisible(true);
		mnuDatPhong.setVisible(false);
		mnudoiPhong.setVisible(false);
		mnuDonPhong.setVisible(false);
		mnuSuaChuaPhong.setVisible(false);
		mnuTraPhong.setVisible(false);
		btncong.setEnabled(false);
		btnHuyDoiPhong.setVisible(true);
		rdioDaDat.setEnabled(false);
		rdioDangDon.setEnabled(false);
		rdioDangSua.setEnabled(false);
		rdioTatCa.setEnabled(false);
		rdioTrong.setEnabled(false);
		MaPhong1 = phong.getMaPhong();
		System.out.println(MaPhong1);
	}// GEN-LAST:event_mnudoiPhongActionPerformed

	private void btnHuyDoiPhongActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnHuyDoiPhongActionPerformed
		RemovePHONG();
		fillPHONG();
		sethuyDoiPhong();
	}// GEN-LAST:event_btnHuyDoiPhongActionPerformed

	public void sethuyDoiPhong() {
		rdioTatCa.setSelected(true);
		mnuChuyenVaoPhong.setVisible(false);
		mnuDatPhong.setVisible(true);
		mnuDonPhong.setVisible(true);
		mnuSuaChuaPhong.setVisible(true);
		mnuTraPhong.setVisible(true);
		mnudoiPhong.setVisible(true);
		btnHuyDoiPhong.setVisible(false);
		btncong.setEnabled(true);
		rdioDaDat.setEnabled(true);
		rdioDangDon.setEnabled(true);
		rdioDangSua.setEnabled(true);
		rdioTatCa.setEnabled(true);
		rdioTrong.setEnabled(true);
	}

	private void mnuChuyenVaoPhongMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_mnuChuyenVaoPhongMousePressed
		// TODO add your handling code here:
	}// GEN-LAST:event_mnuChuyenVaoPhongMousePressed

	private void mnuChuyenVaoPhongActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_mnuChuyenVaoPhongActionPerformed
		phong_dao.setDoiPhong(MaPhong1, phong.getMaPhong());
		sethuyDoiPhong();
		filltable();
		RemovePHONG();
		fillPHONG();
		DialogHelper.alert(this, "Đổi Phòng Thành Công");
	}// GEN-LAST:event_mnuChuyenVaoPhongActionPerformed

	private void btn_DKphongActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_DKphongActionPerformed
		RemovePHONG();
		selectDKphongTrong();
	}// GEN-LAST:event_btn_DKphongActionPerformed

	private void date_duklienphongtrongAncestorAdded(javax.swing.event.AncestorEvent evt) {// GEN-FIRST:event_date_duklienphongtrongAncestorAdded
		date_duklienphongtrong.setDate(DateHelper.now());
	}// GEN-LAST:event_date_duklienphongtrongAncestorAdded

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private Utils.ScrollPaneWin11 ScrollPhong;
	private Utils.Button btnHuyDoiPhong;
	private Utils.Button btnMoi;
	private Utils.Button btnSua;
	private Utils.Button btnThem;
	private Utils.Button btnTru;
	private Utils.Button btnXoa;
	private Utils.Button btn_DKphong;
	private Utils.Button btncong;
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.ButtonGroup buttonGroup2;
	private javax.swing.JComboBox<String> cboTrangThai;
	private com.toedter.calendar.JDateChooser date_duklienphongtrong;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel18;
	private javax.swing.JLabel jLabel19;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel10;
	private javax.swing.JPanel jPanel11;
	private javax.swing.JPanel jPanel12;
	private javax.swing.JPanel jPanel13;
	private javax.swing.JPanel jPanel14;
	private javax.swing.JPanel jPanel15;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel7;
	private javax.swing.JPanel jPanel8;
	private javax.swing.JPanel jPanel9;
	private javax.swing.JLabel lblSLPhong;
	private javax.swing.JMenuItem mnuChuyenVaoPhong;
	private javax.swing.JMenuItem mnuDatPhong;
	private javax.swing.JMenuItem mnuDonPhong;
	private javax.swing.JMenuItem mnuOpenPhong;
	private javax.swing.JMenuItem mnuSuaChuaPhong;
	private javax.swing.JMenuItem mnuTraPhong;
	private javax.swing.JMenuItem mnudoiPhong;
	private javax.swing.JPanel pn1;
	private javax.swing.JPanel pnEditPhong;
	private javax.swing.JPanel pnTang1;
	private javax.swing.JPanel pnTang2;
	private javax.swing.JPopupMenu ppm101;
	private javax.swing.JPopupMenu ppm102;
	private javax.swing.JRadioButton rdioDaDat;
	private javax.swing.JRadioButton rdioDangDon;
	private javax.swing.JRadioButton rdioDangSua;
	private javax.swing.JRadioButton rdioTatCa;
	private javax.swing.JRadioButton rdioTrong;
	private Utils.ScrollPaneWin11 scrollDatPhong;
	private Utils.ScrollPaneWin11 scrollPaneWin113;
	private javax.swing.JTable tblQuanLyphong;
	private javax.swing.JTextArea txaMoTa;
	private javax.swing.JTextField txtGia;
	private javax.swing.JTextField txtMaLoaiPhong;
	private javax.swing.JTextField txtMaPhong;
	private javax.swing.JTextField txtTang;
	private javax.swing.JTextField txtTenPhong;
	// End of variables declaration//GEN-END:variables
}
