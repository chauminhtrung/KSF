package Model;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TienNghiChiTietTest {

	TienNghiChiTiet tn;

	@Before
	public void setUp() throws Exception {
		tn = new TienNghiChiTiet();
	}

	@After
	public void tearDown() throws Exception {
		tn = null;
	}

	@Test
	public void testGetMaTienNghi() {
		tn.setMaTienNghi(1);
		try {
			int MaTienNghiExpected = 1;
			Assert.assertEquals(MaTienNghiExpected, tn.getMaTienNghi());
			System.out.println("testGetMaTienNghi thanh cong");
		} catch (Exception e) {
			System.out.println("testGetMaTienNghi khong thanh cong");
			fail("testGetMaTienNghi khong thanh cong");
		}
	}

	@Test
	public void testSetMaTienNghi() {
		tn.setMaTienNghi(1);
		try {
			int MaTienNghiExpected = 1;
			Assert.assertEquals(MaTienNghiExpected, tn.getMaTienNghi());
			System.out.println("testSetMaTienNghi thanh cong");
		} catch (Exception e) {
			System.out.println("testSetMaTienNghi khong thanh cong");
			fail("testSetMaTienNghi khong thanh cong");
		}
	}

	@Test
	public void testGetMaPhong() {
		tn.setMaPhong("P001");
		try {
			String MaPhongExpected = "P001";
			Assert.assertEquals(MaPhongExpected, tn.getMaPhong());
			System.out.println("testGetMaPhong thanh cong");
		} catch (Exception e) {
			System.out.println("testGetMaPhong khong thanh cong");
			fail("testGetMaPhong khong thanh cong");
		}
	}

	@Test
	public void testSetMaPhong() {
		tn.setMaPhong("P001");
		try {
			String MaPhongExpected = "P001";
			Assert.assertEquals(MaPhongExpected, tn.getMaPhong());
			System.out.println("testSetMaPhong thanh cong");
		} catch (Exception e) {
			System.out.println("testSetMaPhong khong thanh cong");
			fail("testSetMaPhong khong thanh cong");
		}
	}

	@Test
	public void testGetSoLuong() {
		tn.setSoLuong(2);
		try {
			int SoLuongExpected = 2;
			Assert.assertEquals(SoLuongExpected, tn.getSoLuong());
			System.out.println("testGetSoLuong thanh cong");
		} catch (Exception e) {
			System.out.println("testGetSoLuong khong thanh cong");
			fail("testGetSoLuong khong thanh cong");
		}
	}

	@Test
	public void testSetSoLuong() {
		tn.setSoLuong(2);
		try {
			int SoLuongExpected = 2;
			Assert.assertEquals(SoLuongExpected, tn.getSoLuong());
			System.out.println("testSetSoLuong thanh cong");
		} catch (Exception e) {
			System.out.println("testSetSoLuong khong thanh cong");
			fail("testSetSoLuong khong thanh cong");
		}
	}

}
