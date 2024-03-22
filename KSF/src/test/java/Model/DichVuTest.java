package Model;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DichVuTest {
	DichVu dichvu;

	@Before
	public void setUp() throws Exception {
		dichvu = new DichVu();
	}

	@After
	public void tearDown() throws Exception {
		dichvu = null;
	}

	@Test
	public void testDichVu() throws Exception {
		DichVu testdichvu;
		try {
			testdichvu = new DichVu(101, "DV01", "Massage", 50000, "Thư dãn gân cốt");
			System.out.println("Nhập Dịch Vụ thành công");
		} catch (Exception e) {
			System.out.println("Nhập Dịch Vụ không thành công");
			fail("TestDichVu không thành công");
		}
	}

	@Test
	public void testGetMaLoaiDichVu() throws Exception {
		dichvu.setMaLoaiDichVu(101);
		try {
			int MadvExpected = 101;
			Assert.assertEquals(MadvExpected, dichvu.getMaLoaiDichVu());
			System.out.println("TestGet mã loại Dịch Vụ thành công");
		} catch (Exception e) {
			System.out.println("TestGet mã loại Dịch Vụ không thành công");
			fail("TestGet mã loại Dịch Vụ không thành công");
		}
	}

	@Test
	public void testSetMaLoaiDichVu() {
		dichvu.setMaLoaiDichVu(101);
		try {
			int MadvExpected = 101;
			Assert.assertEquals(MadvExpected, dichvu.getMaLoaiDichVu());
			System.out.println("TestSet mã loại Dịch Vụ thành công");
		} catch (Exception e) {
			System.out.println("TestSet mã loại Dịch Vụ không thành công");
			fail("TestSet mã loại Dịch Vụ không thành công");
		}
	}

	@Test
	public void testSetMaLoaiDichVu0() {
		dichvu.setMaLoaiDichVu(0);
		try {
			int MadvExpected = 0;
			Assert.assertEquals(MadvExpected, dichvu.getMaLoaiDichVu());
			System.out.println("TestSet0 mã loại Dịch Vụ thành công");
		} catch (Exception e) {
			System.out.println("TestSet0 mã loại Dịch Vụ không thành công");
			fail("TestSet0 mã loại Dịch Vụ không thành công");
		}
	}

	@Test
	public void testSetMaLoaiDichVu1() {
		dichvu.setMaLoaiDichVu(-1);
		try {
			int MadvExpected = 0;
			Assert.assertEquals(MadvExpected, dichvu.getMaLoaiDichVu());
			System.out.println("TestSet1 mã loại Dịch Vụ không được <0 thành công");
		} catch (Exception e) {
			System.out.println("TestSet1 mã loại Dịch Vụ không thành công");
			fail("TestSet1 mã loại Dịch Vụ không thành công");
		}
	}

	@Test
	public void testSetMaLoaiDichVuNhieu() {
		dichvu.setMaLoaiDichVu(101101);
		try {
			int MadvExpected = 101101;
			Assert.assertEquals(MadvExpected, dichvu.getMaLoaiDichVu());
			System.out.println("TestSetNhieu mã loại Dịch Vụ thành công");
		} catch (Exception e) {
			System.out.println("TestSetnhieu mã loại Dịch Vụ không thành công");
			fail("TestSetnhieu mã loại Dịch Vụ không thành công");
		}
	}

	@Test
	public void testSetMaLoaiDichVuit() {
		dichvu.setMaLoaiDichVu(11);
		try {
			int MadvExpected = 11;
			Assert.assertEquals(MadvExpected, dichvu.getMaLoaiDichVu());
			System.out.println("TestSetIt mã loại Dịch Vụ thành công");
		} catch (Exception e) {
			System.out.println("TestSetIt mã loại Dịch Vụ không thành công");
			fail("TestSetIt mã loại Dịch Vụ không thành công");
		}
	}

	@Test
	public void testGetMaDichVu() {
		dichvu.setMaDichVu("DV01");
		;
		try {
			String MadvExpected = "DV01";
			Assert.assertEquals(MadvExpected, dichvu.getMaDichVu());
			System.out.println("TestSet mã Dịch Vụ thành công");
		} catch (Exception e) {
			System.out.println("TestSet mã Dịch Vụ không thành công");
			fail("TestSet mã Dịch Vụ không thành công");
		}
	}

	@Test
	public void testSetMaDichVu() {
		dichvu.setMaDichVu("DV01");
		try {
			String MadvExpected = "DV01";
			Assert.assertEquals(MadvExpected, dichvu.getMaDichVu());
			System.out.println("TestSet mã Dịch Vụ thành công");
		} catch (Exception e) {
			System.out.println("TestSet mã Dịch Vụ không thành công");
			fail("TestSet mã Dịch Vụ không thành công");
		}
	}

	@Test
	public void testSetMaDichVurong() {
		dichvu.setMaDichVu("");
		try {
			String MadvExpected = "";
			Assert.assertEquals(MadvExpected, dichvu.getMaDichVu());
			System.out.println("TestSetrong mã Dịch Vụ thành công");
		} catch (Exception e) {
			System.out.println("TestSetrong mã Dịch Vụ không thành công");
			fail("TestSetrong mã Dịch Vụ không thành công");
		}
	}

	@Test
	public void testSetMaDichVuNhieu() {
		dichvu.setMaDichVu("DV101010101");
		try {
			String MadvExpected = "DV101010101";
			Assert.assertEquals(MadvExpected, dichvu.getMaDichVu());
			System.out.println("TestSetNhieu mã Dịch Vụ thành công");
		} catch (Exception e) {
			System.out.println("TestSetNhieu mã Dịch Vụ không thành công");
			fail("TestSetNhieu mã Dịch Vụ không thành công");
		}
	}

	@Test
	public void testSetMaDichVuNull() {

		try {
			String MadvExpected = null;
			Assert.assertEquals(MadvExpected, dichvu.getMaDichVu());
			System.out.println("TestSetnull mã Dịch Vụ thành công");
		} catch (Exception e) {
			System.out.println("TestSetnull mã Dịch Vụ không thành công");
			fail("TestSetnull mã Dịch Vụ không thành công");
		}
	}

	@Test
	public void testGetTenDichVu() {
		dichvu.setTenDichVu("Massage");
		;
		try {
			String MadvExpected = "Massage";
			Assert.assertEquals(MadvExpected, dichvu.getTenDichVu());
			System.out.println("TestGet Tên Dịch Vụ thành công");
		} catch (Exception e) {
			System.out.println("TestGet Tên Dịch Vụ không thành công");
			fail("TestGet Tên Dịch Vụ không thành công");
		}
	}

	@Test
	public void testSetTenDichVu() {
		dichvu.setTenDichVu("Massage");
		;
		try {
			String MadvExpected = "Massage";
			Assert.assertEquals(MadvExpected, dichvu.getTenDichVu());
			System.out.println("TestSet Tên Dịch Vụ thành công");
		} catch (Exception e) {
			System.out.println("TestSet Tên Dịch Vụ không thành công");
			fail("TestSet Tên Dịch Vụ không thành công");
		}
	}

	@Test
	public void testSetTenDichVuRong() {
		dichvu.setTenDichVu("");
		;
		try {
			String MadvExpected = "";
			Assert.assertEquals(MadvExpected, dichvu.getTenDichVu());
			System.out.println("TestSetrong Tên Dịch Vụ thành công");
		} catch (Exception e) {
			System.out.println("TestSetrong Tên Dịch Vụ không thành công");
			fail("TestSet Tên Dịch Vụ không thành công");
		}
	}

	@Test
	public void testSetTenDichVunull() {
		try {
			String MadvExpected = null;
			Assert.assertEquals(MadvExpected, dichvu.getTenDichVu());
			System.out.println("TestSetnull Tên Dịch Vụ thành công");
		} catch (Exception e) {
			System.out.println("TestSetnull Tên Dịch Vụ không thành công");
			fail("TestSet Tên Dịch Vụ không thành công");
		}
	}

	@Test
	public void testGetDonGia() {
		dichvu.setDonGia(50000);
		;
		try {
			float MadvExpected = 50000;
			Assert.assertNotEquals(MadvExpected, dichvu.getDonGia());
			System.out.println("TestGet Don gia thành công");
		} catch (Exception e) {
			System.out.println("TestGet Đơn Giá không thành công");
			fail("TestGet Đơn Giá không thành công");
		}
	}

	@Test
	public void testSetDonGia() {
		dichvu.setDonGia(50000);
		;
		try {
			float MadvExpected = 50000;
			Assert.assertNotEquals(MadvExpected, dichvu.getDonGia());
			System.out.println("TestSet Don gia thành công");
		} catch (Exception e) {
			System.out.println("TestSet Đơn Giá không thành công");
			fail("TestSet Đơn Giá không thành công");
		}
	}

	public void testSetDonGia1() {
		dichvu.setDonGia(-1);
		try {
			float MadvExpected = 0;
			Assert.assertNotEquals(MadvExpected, dichvu.getDonGia());
			System.out.println("TestSet Don gia >0 thành công");
		} catch (Exception e) {
			System.out.println("TestSet Đơn Giá không thành công");
			fail("TestSet Đơn Giá không thành công");
		}
	}

	@Test
	public void testGetMoTa() {
		dichvu.setMoTa("Thư giãn gân cốt");
		;
		try {
			String MadvExpected = "Thư giãn gân cốt";
			Assert.assertEquals(MadvExpected, dichvu.getMoTa());
			System.out.println("Testget Mô tả thành công");
		} catch (Exception e) {
			System.out.println("Testget Mô tả không thành công");
			fail("Testget Mô tả không thành công");
		}
	}

	@Test
	public void testSetMoTa() {
		dichvu.setMoTa("Thư giãn gân cốt");
		;
		try {
			String MadvExpected = "Thư giãn gân cốt";
			Assert.assertEquals(MadvExpected, dichvu.getMoTa());
			System.out.println("TestSet Mô tả thành công");
		} catch (Exception e) {
			System.out.println("TestSet Mô tả không thành công");
			fail("TestSet Mô tả không thành công");
		}
	}

	@Test
	public void testSetMoTarong() {
		dichvu.setMoTa("");
		;
		try {
			String MadvExpected = "";
			Assert.assertEquals(MadvExpected, dichvu.getMoTa());
			System.out.println("TestSetrong Mô tả thành công");
		} catch (Exception e) {
			System.out.println("TestSetrong Mô tả không thành công");
			fail("TestSetrong Mô tả không thành công");
		}
	}

}
