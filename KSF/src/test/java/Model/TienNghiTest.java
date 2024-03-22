package Model;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TienNghiTest {

	TienNghi tn;

	@Before
	public void setUp() throws Exception {
		tn = new TienNghi();
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
	public void testGetTenTienNghi() {
		tn.setTenTienNghi("Ban ui");
		try {
			String TenTienNghiExpected = "Ban ui";
			Assert.assertEquals(TenTienNghiExpected, tn.getTenTienNghi());
			System.out.println("testGetTenTienNghi thanh cong");
		} catch (Exception e) {
			System.out.println("testGetTenTienNghi khong thanh cong");
			fail("testGetTenTienNghi khong thanh cong");
		}
	}

	@Test
	public void testSetTenTienNghi() {
		tn.setTenTienNghi("Ban ui");
		try {
			String TenTienNghiExpected = "Ban ui";
			Assert.assertEquals(TenTienNghiExpected, tn.getTenTienNghi());
			System.out.println("testSetTenTienNghi thanh cong");
		} catch (Exception e) {
			System.out.println("testSetTenTienNghi khong thanh cong");
			fail("testSetTenTienNghi khong thanh cong");
		}
	}

}
