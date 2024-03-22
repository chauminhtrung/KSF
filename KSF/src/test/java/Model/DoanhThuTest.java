package Model;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

public class DoanhThuTest {
	DoanhThu doanhthu;
	@Rule
	public ErrorCollector collector = new ErrorCollector();

	@Before
	public void setUp() throws Exception {
		doanhthu = new DoanhThu();
//		collector.addError(new Throwable("Lỗi ở dòng 1"));
//		collector.addError(new Throwable("Lỗi ở dòng 2"));
	}

	@After
	public void tearDown() throws Exception {
		doanhthu = null;
	}

	@Test
	public void testDoanhThu() {
		DoanhThu testdoanhthu = new DoanhThu();
		try {
			testdoanhthu = new DoanhThu("NV01", "KH01", "12/12/2002", 20000.0);
			System.out.println("Nhập Doanh thu thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("Nhập Doanh thu không thành công");
			fail("Test Doanh Thu không thành công");
		}
	}

	@Test
	public void testGetMaNV() {
		doanhthu.setMaNV("NV01");
		try {
			String MadvExpected = "NV01";
			Assert.assertEquals(MadvExpected, doanhthu.getMaNV());
			System.out.println("TestGet mã Nhân Viên thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestGet mã Nhân Viên không thành công");
			fail("TestGet mã Nhân Viên không thành công");
		}
	}

	@Test
	public void testSetMaNV() {
		doanhthu.setMaNV("NV01");
		try {
			String MadvExpected = "NV01";
			Assert.assertEquals(MadvExpected, doanhthu.getMaNV());
			System.out.println("TestSet mã Nhân Viên thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet mã Nhân Viên không thành công");
			fail("TestSet mã Nhân Viên không thành công");
		}
	}

	@Test
	public void testSetMaNVrong() {
		doanhthu.setMaNV("");
		try {
			String MadvExpected = "";
			Assert.assertEquals(MadvExpected, doanhthu.getMaNV());
			System.out.println("TestSet mã Nhân Viên rỗng thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet mã Nhân Viên rỗng không thành công");
			fail("TestSet mã Nhân Viên rỗng không thành công");
		}
	}

	@Test
	public void testSetMaNVnull() {

		try {
			String MadvExpected = null;
			Assert.assertEquals(MadvExpected, doanhthu.getMaNV());
			System.out.println("TestSet mã Nhân Viên null thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet mã Nhân Viên null không thành công");
			fail("TestGet mã Nhân Viên null không thành công");
		}
	}

	@Test
	public void testGetMaKH() {
		doanhthu.setMaKH("KH01");
		try {
			String MadvExpected = "KH01";
			Assert.assertEquals(MadvExpected, doanhthu.getMaKH());
			System.out.println("TestGet mã Khách Hàng thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestGet mã Khách Hàng không thành công");
			fail("TestGet mã Khách Hàng không thành công");
		}
	}

	@Test
	public void testSetMaKH() {
		doanhthu.setMaKH("KH01");
		try {
			String MadvExpected = "KH01";
			Assert.assertEquals(MadvExpected, doanhthu.getMaKH());
			System.out.println("TestSet mã Khách Hàng thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet mã Khách Hàng không thành công");
			fail("TestSet mã Khách Hàngn không thành công");
		}
	}

	@Test
	public void testSetMaKHrong() {
		doanhthu.setMaKH("");
		try {
			String MadvExpected = "";
			Assert.assertEquals(MadvExpected, doanhthu.getMaKH());
			System.out.println("TestSet mã Khách Hàng rỗng thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet mã Khách Hàng rỗng không thành công");
			fail("TestSet mã Khách Hàng không thành công");
		}
	}

	@Test
	public void testSetMaKHnull() {

		try {
			String MadvExpected = null;
			Assert.assertEquals(MadvExpected, doanhthu.getMaKH());
			System.out.println("TestSet mã Khách Hàng null thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet mã Khách Hàng null không thành công");
			fail("TestSet mã Khách Hàng null không thành công");
		}
	}

	@Test
	public void testGetNgayxuat() {
		doanhthu.setNgayxuat("02/02/2003");
		try {
			String MadvExpected = "02/02/2003";
			Assert.assertEquals(MadvExpected, doanhthu.getNgayxuat());
			System.out.println("TestGet Ngày xuất thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestGet Ngày xuất không thành công");
			fail("TestGet Ngày xuất không thành công");
		}
	}

	@Test
	public void testSetNgayxuat() {
		doanhthu.setNgayxuat("02/02/2003");
		try {
			String MadvExpected = "02/02/2003";
			Assert.assertEquals(MadvExpected, doanhthu.getNgayxuat());
			System.out.println("TestSet Ngày xuất thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet Ngày xuất không thành công");
			fail("TestSet Ngày xuất không thành công");
		}
	}

	@Test
	public void testSetNgayxuatrong() {
		doanhthu.setNgayxuat("");
		try {
			String MadvExpected = "";
			Assert.assertEquals(MadvExpected, doanhthu.getNgayxuat());
			System.out.println("TestSet Ngày xuất rỗng thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet Ngày xuất rỗng không thành công");
			fail("TestSet Ngày xuất rỗng không thành công");
		}
	}

	@Test
	public void testSetNgayxuatnull() {

		try {
			String MadvExpected = null;
			Assert.assertEquals(MadvExpected, doanhthu.getNgayxuat());
			System.out.println("TestSet Ngày xuất null thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet Ngày xuất null không thành công");
			fail("TestSet Ngày xuất null không thành công");
		}
	}

	@Test
	public void testGetTongTien() {
		doanhthu.setTongTien(2000.0);
		try {
			double MadvExpected = 2000.0;
			Assert.assertNotEquals(MadvExpected, doanhthu.getTongTien());
			System.out.println("TestGet tổng tiền thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestGet tổng tiền không thành công");
			fail("TestGet tổng tiền không thành công");
		}
	}

	@Test
	public void testSetTongTien() {
		doanhthu.setTongTien(2000.0);
		try {
			double MadvExpected = 2000.0;
			Assert.assertNotEquals(MadvExpected, doanhthu.getTongTien());
			System.out.println("TestSet tổng tiền thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet tổng tiền không thành công");
			fail("TestGet tổng tiền không thành công");
		}
	}

	@Test
	public void testSetTongTien0() {
		doanhthu.setTongTien(0.0);
		try {
			double MadvExpected = -1;
			Assert.assertNotEquals(MadvExpected, doanhthu.getTongTien());
			System.out.println("TestSet tổng tiền <0 thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet tổng tiền <0 không thành công");
			fail("TestGet tổng tiền <0 không thành công");
		}
	}

}
