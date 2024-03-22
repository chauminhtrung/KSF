package Model;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PhongTest {

	Phong phong;

	@Before
	public void setUp() throws Exception {
		phong = new Phong();
	}

	@After
	public void tearDown() throws Exception {
		phong = null;
	}

//	@Test
//	public void testPhong() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGetTenPhong() {
		phong.setTenPhong("Phong Vip");
		try {
			String PhongExpected = "Phong Vip";
			Assert.assertEquals(PhongExpected, phong.getTenPhong());
			System.out.println("testGetTenPhong thanh cong");
		} catch (Exception e) {
			System.out.println("testGetTenPhong khong thanh cong");
			fail("testGetTenPhong khong thanh cong");
		}
	}

	@Test
	public void testSetTenPhong() {
		phong.setTenPhong("Phong Vip");
		try {
			String PhongExpected = "Phong Vip";
			Assert.assertEquals(PhongExpected, phong.getTenPhong());
			System.out.println("testSetTenPhong thanh cong");
		} catch (Exception e) {
			System.out.println("testSetTenPhong khong thanh cong");
			fail("testSetTenPhong khong thanh cong");
		}
	}

	@Test
	public void testGetMaLoaiPhong() {
		phong.setMaLoaiPhong(1);
		try {
			int MaLoaiPhongExpected = 1;
			Assert.assertEquals(MaLoaiPhongExpected, phong.getMaLoaiPhong());
			System.out.println("testGetMaLoaiPhong thanh cong");
		} catch (Exception e) {
			System.out.println("testGetMaLoaiPhong khong thanh cong");
			fail("testGetMaLoaiPhong khong thanh cong");
		}
	}

	@Test
	public void testSetMaLoaiPhong() {
		phong.setMaLoaiPhong(1);
		try {
			int MaLoaiPhongExpected = 1;
			Assert.assertEquals(MaLoaiPhongExpected, phong.getMaLoaiPhong());
			System.out.println("testSetMaLoaiPhong thanh cong");
		} catch (Exception e) {
			System.out.println("testSetMaLoaiPhong khong thanh cong");
			fail("testSetMaLoaiPhong khong thanh cong");
		}
	}

	@Test
	public void testGetMaPhong() {
		phong.setMaPhong("P006");
		try {
			String MaPhongExpected = "P006";
			Assert.assertEquals(MaPhongExpected, phong.getMaPhong());
			System.out.println("testGetMaPhong thanh cong");
		} catch (Exception e) {
			System.out.println("testGetMaPhong khong thanh cong");
			fail("testGetMaPhong khong thanh cong");
		}
	}

	@Test
	public void testSetMaPhong() {
		phong.setMaPhong("P006");
		try {
			String MaPhongExpected = "P006";
			Assert.assertEquals(MaPhongExpected, phong.getMaPhong());
			System.out.println("testSetMaPhong thanh cong");
		} catch (Exception e) {
			System.out.println("testSetMaPhong khong thanh cong");
			fail("testSetMaPhong khong thanh cong");
		}
	}

	@Test
	public void testGetGia() {
		phong.setGia(5400000.0);
		try {
			double GiaPhongExpected = 5400000.0;
			Assert.assertEquals(GiaPhongExpected, phong.getGia());
			System.out.println("testGetGia thanh cong");
		} catch (Exception e) {
			System.out.println("testGetGia khong thanh cong");
			fail("testGetGia khong thanh cong");
		}
	}

	@Test
	public void testSetGia() {
		phong.setGia(5400000.0);
		try {
			double GiaPhongExpected = 5400000.0;
			Assert.assertEquals(GiaPhongExpected, phong.getGia());
			System.out.println("testSetGia thanh cong");
		} catch (Exception e) {
			System.out.println("testSetGia khong thanh cong");
			fail("testSetGia khong thanh cong");
		}
	}

	@Test
	public void testGetTrangThai() {
		phong.setTrangThai("mo");
		try {
			String TrangThaiExpected = "mo";
			Assert.assertEquals(TrangThaiExpected, phong.getTrangThai());
			System.out.println("testGetTrangThai thanh cong");
		} catch (Exception e) {
			System.out.println("testGetTrangThai khong thanh cong");
			fail("testGetTrangThai khong thanh cong");
		}
	}

	@Test
	public void testSetTrangThai() {
		phong.setTrangThai("mo");
		try {
			String TrangThaiExpected = "mo";
			Assert.assertEquals(TrangThaiExpected, phong.getTrangThai());
			System.out.println("testSetTrangThai thanh cong");
		} catch (Exception e) {
			System.out.println("testSetTrangThai khong thanh cong");
			fail("testSetTrangThai khong thanh cong");
		}
	}

	@Test
	public void testGetMoTa() {
		phong.setMoTa("View Dep");
		try {
			String MoTaExpected = "View Dep";
			Assert.assertEquals(MoTaExpected, phong.getMoTa());
			System.out.println("testGetMoTa thanh cong");
		} catch (Exception e) {
			System.out.println("testGetMoTa khong thanh cong");
			fail("testGetMoTa khong thanh cong");
		}
	}

	@Test
	public void testSetMoTa() {
		phong.setMoTa("View Dep");
		try {
			String MoTaExpected = "View Dep";
			Assert.assertEquals(MoTaExpected, phong.getMoTa());
			System.out.println("testSetMoTa thanh cong");
		} catch (Exception e) {
			System.out.println("testSetMoTa khong thanh cong");
			fail("testSetMoTa khong thanh cong");
		}
	}

	@Test
	public void testGetMaTang() {
		phong.setMaTang(3);
		try {
			int MaTangExpected = 3;
			Assert.assertEquals(MaTangExpected, phong.getMaTang());
			System.out.println("testGetMaTang thanh cong");
		} catch (Exception e) {
			System.out.println("testGetMaTang khong thanh cong");
			fail("testGetMaTang khong thanh cong");
		}
	}

	@Test
	public void testSetMaTang() {
		phong.setMaTang(3);
		try {
			int MaTangExpected = 3;
			Assert.assertEquals(MaTangExpected, phong.getMaTang());
			System.out.println("testSetMaTang thanh cong");
		} catch (Exception e) {
			System.out.println("testSetMaTang khong thanh cong");
			fail("testSetMaTang khong thanh cong");
		}
	}

}
