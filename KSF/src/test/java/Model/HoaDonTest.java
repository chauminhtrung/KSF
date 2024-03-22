package Model;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

public class HoaDonTest {
	HoaDon hoadon;
	SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
	@Rule
	public ErrorCollector collector = new ErrorCollector();

	@Before
	public void setUp() throws Exception {
		hoadon = new HoaDon();
//		collector.addError(new Throwable("Lỗi ở dòng 1"));
//		collector.addError(new Throwable("Lỗi ở dòng 2"));
	}

	@After
	public void tearDown() throws Exception {
		hoadon = null;
	}

	@Test
	public void testHoaDon() {
		HoaDon testhoadon;
		try {
			testhoadon = new HoaDon(121, formatDate.parse("12/12/2022"), formatDate.parse("21/12/2022"), "KH01",
					"0373394564", "P101", "T1", "VIP", "150000", "Oki", "NV01");
			System.out.println("Nhập Hóa đơn thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("Nhập Hóa đơn không thành công");
			fail("Test Hóa đơn không thành công");
		}

	}

	@Test
	public void testGetMaHoaDon() {
		hoadon.setMaHoaDon(122);
		try {
			int MadvExpected = 122;
			Assert.assertEquals(MadvExpected, hoadon.getMaHoaDon());
			System.out.println("TestGet mã Hóa đơn thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestGet mã Hóa đơn không thành công");
			fail("TestGet mã Hóa đơn không thành công");
		}
	}

	@Test
	public void testSetMaHoaDon() {
		hoadon.setMaHoaDon(122);
		try {
			int MadvExpected = 122;
			Assert.assertEquals(MadvExpected, hoadon.getMaHoaDon());
			System.out.println("TestGSet mã Hóa đơn thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet mã Hóa đơn không thành công");
			fail("TestSet mã Hóa đơn không thành công");
		}
	}

	@Test
	public void testSetMaHoaDon0() {
		hoadon.setMaHoaDon(1);
		try {
			int MadvExpected = -1;
			Assert.assertNotEquals(MadvExpected, hoadon.getMaHoaDon());
			System.out.println("TestSet mã Hóa đơn <0 thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet mã Hóa đơn <0 không thành công");
			fail("TestSet mã Hóa đơn <0 không thành công");
		}
	}

	@Test
	public void testGetNgayNhanPhong() throws Exception {
		hoadon.setNgayNhanPhong(formatDate.parse("12/02/2012"));
		try {
			Date MadvExpected = formatDate.parse("12/02/2012");
			Assert.assertEquals(MadvExpected, hoadon.getNgayNhanPhong());
			System.out.println("TestGet Ngày nhạn phòng thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestGet Ngày nhạn phòng không thành công");
			fail("TestGet Ngày nhạn phòng không thành công");
		}
	}

	@Test
	public void testSetNgayNhanPhong() throws Exception {
		hoadon.setNgayNhanPhong(formatDate.parse("12/02/2012"));
		try {
			Date MadvExpected = formatDate.parse("12/02/2012");
			Assert.assertEquals(MadvExpected, hoadon.getNgayNhanPhong());
			System.out.println("TestSet Ngày nhạn phòng thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet Ngày nhạn phòng không thành công");
			fail("TestSet Ngày nhạn phòng không thành công");
		}
	}

	@Test
	public void testGetNgayTraPhong() throws Exception {
		hoadon.setNgayTraPhong(formatDate.parse("21/02/2012"));
		try {
			Date MadvExpected = formatDate.parse("21/02/2012");
			Assert.assertEquals(MadvExpected, hoadon.getNgayTraPhong());
			System.out.println("TestGet Ngày trả phòng thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestGet Ngày trả phòng không thành công");
			fail("TestGet Ngày nhạn phòng không thành công");
		}
	}

	@Test
	public void testSetNgayTraPhong() throws Exception {
		hoadon.setNgayTraPhong(formatDate.parse("21/02/2012"));
		try {
			Date MadvExpected = formatDate.parse("21/02/2012");
			Assert.assertEquals(MadvExpected, hoadon.getNgayTraPhong());
			System.out.println("TestSet Ngày trả phòng thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet Ngày trả phòng không thành công");
			fail("TestSet Ngày nhạn phòng không thành công");
		}
	}

	@Test
	public void testGetMaKH() {
		hoadon.setMaKH("KH01");
		try {
			String MadvExpected = "KH01";
			Assert.assertEquals(MadvExpected, hoadon.getMaKH());
			System.out.println("TestGet mã Khách Hàng thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestGet mã Khách Hàng không thành công");
			fail("TestGet mã Khách Hàng không thành công");
		}
	}

	@Test
	public void testSetMaKH() {
		hoadon.setMaKH("KH01");
		try {
			String MadvExpected = "KH01";
			Assert.assertEquals(MadvExpected, hoadon.getMaKH());
			System.out.println("TestSet mã Khách Hàng thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet mã Khách Hàng không thành công");
			fail("TestSet mã Khách Hàngn không thành công");
		}
	}

	@Test
	public void testSetMaKHrong() {
		hoadon.setMaKH("");
		try {
			String MadvExpected = "";
			Assert.assertEquals(MadvExpected, hoadon.getMaKH());
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
			Assert.assertEquals(MadvExpected, hoadon.getMaKH());
			System.out.println("TestSet mã Khách Hàng null thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet mã Khách Hàng null không thành công");
			fail("TestSet mã Khách Hàng null không thành công");
		}
	}

	@Test
	public void testGetSDT() {
		hoadon.setSDT("0373395604");
		try {
			String MadvExpected = "0373395604";
			Assert.assertEquals(MadvExpected, hoadon.getSDT());
			System.out.println("TestGet SĐT thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestGet SĐT không thành công");
			fail("TestGet SĐT không thành công");
		}
	}

	@Test
	public void testSetSDT() {
		hoadon.setSDT("0373395604");
		try {
			String MadvExpected = "0373395604";
			Assert.assertEquals(MadvExpected, hoadon.getSDT());
			System.out.println("TestSet SĐT thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet SĐT không thành công");
			fail("TestSet SĐT không thành công");
		}
	}

	@Test
	public void testSetSDTrong() {
		hoadon.setSDT("");
		try {
			String MadvExpected = "";
			Assert.assertEquals(MadvExpected, hoadon.getSDT());
			System.out.println("TestSet SĐT rỗng thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet SĐT rỗng không thành công");
			fail("TestSet SĐT rỗng không thành công");
		}
	}

	@Test
	public void testSetSDTnull() {

		try {
			String MadvExpected = null;
			Assert.assertEquals(MadvExpected, hoadon.getSDT());
			System.out.println("TestSet SĐT null thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet SĐT null không thành công");
			fail("TestSet SĐT null không thành công");
		}
	}

	@Test
	public void testGetMaPhong() {
		hoadon.setMaPhong("P101");
		try {
			String MadvExpected = "P101";
			Assert.assertEquals(MadvExpected, hoadon.getMaPhong());
			System.out.println("TestGet Mã Phòng thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestGet Mã Phòng không thành công");
			fail("TestGet Mã Phòng không thành công");
		}
	}

	@Test
	public void testSetMaPhong() {
		hoadon.setMaPhong("P101");
		try {
			String MadvExpected = "P101";
			Assert.assertEquals(MadvExpected, hoadon.getMaPhong());
			System.out.println("TestSet Mã Phòng thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet Mã Phòng không thành công");
			fail("TestSet Mã Phòng không thành công");
		}
	}

	@Test
	public void testGetMaTang() {
		hoadon.setMaTang("T1");
		try {
			String MadvExpected = "T1";
			Assert.assertEquals(MadvExpected, hoadon.getMaTang());
			System.out.println("TestGet Mã Tầng thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestGet Mã Tầng không thành công");
			fail("TestGet Mã Tầng không thành công");
		}
	}

	@Test
	public void testSetMaTang() {
		hoadon.setMaTang("T1");
		try {
			String MadvExpected = "T1";
			Assert.assertEquals(MadvExpected, hoadon.getMaTang());
			System.out.println("TestSet Mã Tầng thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet Mã Tầng không thành công");
			fail("TestSet Mã Tầng không thành công");
		}
	}

	@Test
	public void testGetMaLoaiPhong() {
		hoadon.setMaLoaiPhong("VIP");
		try {
			String MadvExpected = "VIP";
			Assert.assertEquals(MadvExpected, hoadon.getMaLoaiPhong());
			System.out.println("TestGet Mã Loại Phòng thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestGet Mã Loại Phòng không thành công");
			fail("TestGet Mã Loại Phòng không thành công");
		}
	}

	@Test
	public void testSetMaLoaiPhong() {
		hoadon.setMaLoaiPhong("VIP");
		try {
			String MadvExpected = "VIP";
			Assert.assertEquals(MadvExpected, hoadon.getMaLoaiPhong());
			System.out.println("TestSet Mã Loại Phòng thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet Mã Loại Phòng không thành công");
			fail("TestSet Mã Loại Phòng không thành công");
		}
	}

	@Test
	public void testGetGia() {
		hoadon.setGia("150000");
		try {
			String MadvExpected = "150000";
			Assert.assertEquals(MadvExpected, hoadon.getGia());
			System.out.println("TestGet Giá Phòng thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestGet Giá Phòng không thành công");
			fail("TestGet Giá Phòng không thành công");
		}
	}

	@Test
	public void testSetGia() {
		hoadon.setGia("150000");
		try {
			String MadvExpected = "150000";
			Assert.assertEquals(MadvExpected, hoadon.getGia());
			System.out.println("TestSet Giá Phòng thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet Giá Phòng không thành công");
			fail("TestSet Giá Phòng không thành công");
		}
	}

	@Test
	public void testSetGiarong() {
		hoadon.setGia("");
		try {
			String MadvExpected = "";
			Assert.assertEquals(MadvExpected, hoadon.getGia());
			System.out.println("TestSet Giá Phòng rỗng thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet Giá Phòng rỗng không thành công");
			fail("TestSet Giá Phòng rỗng không thành công");
		}
	}

	@Test
	public void testSetGianull() {

		try {
			String MadvExpected = null;
			Assert.assertEquals(MadvExpected, hoadon.getGia());
			System.out.println("TestSet Giá Phòng null thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet Giá Phòng null không thành công");
			fail("TestSet Giá Phòng không thành công");
		}
	}

	@Test
	public void testGetGhiChu() {
		hoadon.setGhiChu("oki");
		try {
			String MadvExpected = "oki";
			Assert.assertEquals(MadvExpected, hoadon.getGhiChu());
			System.out.println("TestGet Ghi chú thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestGet Ghi chú không thành công");
			fail("TestGet Ghi chú không thành công");
		}
	}

	@Test
	public void testSetGhiChu() {
		hoadon.setGhiChu("oki");
		try {
			String MadvExpected = "oki";
			Assert.assertEquals(MadvExpected, hoadon.getGhiChu());
			System.out.println("TestSet Ghi chú thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet Ghi chú không thành công");
			fail("TestSet Ghi chú không thành công");
		}
	}

	@Test
	public void testSetGhiChurong() {
		hoadon.setGhiChu("");
		try {
			String MadvExpected = "";
			Assert.assertEquals(MadvExpected, hoadon.getGhiChu());
			System.out.println("TestSet Ghi chú rỗng thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet Ghi chú rỗng không thành công");
			fail("TestSet Ghi chú rỗng không thành công");
		}
	}

	@Test
	public void testSetGhiChunull() {

		try {
			String MadvExpected = null;
			Assert.assertEquals(MadvExpected, hoadon.getGhiChu());
			System.out.println("TestSet Ghi chú null thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet Ghi chú null không thành công");
			fail("TestSet Ghi chú null không thành công");
		}
	}

	@Test
	public void testGetMaNV() {
		hoadon.setMaNV("NV01");
		try {
			String MadvExpected = "NV01";
			Assert.assertEquals(MadvExpected, hoadon.getMaNV());
			System.out.println("TestGet mã Nhân Viên thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestGet mã Nhân Viên không thành công");
			fail("TestGet mã Nhân Viên không thành công");
		}
	}

	@Test
	public void testSetMaNV() {
		hoadon.setMaNV("NV01");
		try {
			String MadvExpected = "NV01";
			Assert.assertEquals(MadvExpected, hoadon.getMaNV());
			System.out.println("TestSet mã Nhân Viên thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet mã Nhân Viên không thành công");
			fail("TestSet mã Nhân Viên không thành công");
		}
	}

	@Test
	public void testSetMaNVrong() {
		hoadon.setMaNV("");
		try {
			String MadvExpected = "";
			Assert.assertEquals(MadvExpected, hoadon.getMaNV());
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
			Assert.assertEquals(MadvExpected, hoadon.getMaNV());
			System.out.println("TestSet mã Nhân Viên null thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet mã Nhân Viên null không thành công");
			fail("TestGet mã Nhân Viên null không thành công");
		}
	}

}
