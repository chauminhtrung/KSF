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

public class HoaDonPhongTest {
	HoaDonPhong hoadonphong;
	SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
	@Rule
	public ErrorCollector collector = new ErrorCollector();

	@Before
	public void setUp() throws Exception {
		hoadonphong = new HoaDonPhong();
//		collector.addError(new Throwable("Lỗi ở dòng 1"));
//		collector.addError(new Throwable("Lỗi ở dòng 2"));
	}

	@After
	public void tearDown() throws Exception {
		hoadonphong = null;
	}

	@Test
	public void testHoaDonPhong() {
		HoaDonPhong testhoadonphong;
		try {
			testhoadonphong = new HoaDonPhong("HD01", formatDate.parse("21/12/2022"), formatDate.parse("22/12/2022"),
					"Oki", "NV01", "P101", 101, formatDate.parse("21/12/2022"), "Theo ngày", 50000, 10000, "Tiền mặt",
					20000);
			System.out.println("Nhập Hóa đơn Phòng thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("Nhập Hóa đơn Phòng không thành công");
			fail("Test Hóa đơn Phòng không thành công");
		}
	}

	@Test
	public void testGetHinhThucThue() {
		hoadonphong.setHinhThucThue("Theo ngày");
		try {
			String MadvExpected = "Theo ngày";
			Assert.assertEquals(MadvExpected, hoadonphong.getHinhThucThue());
			System.out.println("TestGet hình thức thuê thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestGet hình thức thuê không thành công");
			fail("TestGet hình thức thuê không thành công");
		}
	}

	@Test
	public void testSetHinhThucThue() {
		hoadonphong.setHinhThucThue("Theo ngày");
		try {
			String MadvExpected = "Theo ngày";
			Assert.assertEquals(MadvExpected, hoadonphong.getHinhThucThue());
			System.out.println("TestSet hình thức thuê theo ngày thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet hình thức thuê theo ngày không thành công");
			fail("TestSet hình thức thuê theo ngày không thành công");
		}
	}

	@Test
	public void testSetHinhThucThue1() {
		hoadonphong.setHinhThucThue("Theo giờ");
		try {
			String MadvExpected = "Theo giờ";
			Assert.assertEquals(MadvExpected, hoadonphong.getHinhThucThue());
			System.out.println("TestSet hình thức thuê theo giờ thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet hình thức thuê theo giờ không thành công");
			fail("TestSet hình thức thuê theo giờ không thành công");
		}
	}

	@Test
	public void testGetGiamGia() {
		hoadonphong.setGiamGia(50000);
		try {
			int MadvExpected = 50000;
			Assert.assertEquals(MadvExpected, hoadonphong.getGiamGia());
			System.out.println("TestGet giảm giá thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestGet giảm giá không thành công");
			fail("TestGet giảm giá không thành công");
		}
	}

	@Test
	public void testSetGiamGia() {
		hoadonphong.setGiamGia(50000);
		try {
			int MadvExpected = 50000;
			Assert.assertEquals(MadvExpected, hoadonphong.getGiamGia());
			System.out.println("TestSet giảm giá thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet giảm giá không thành công");
			fail("TestSet giảm giá không thành công");
		}
	}

	@Test
	public void testSetGiamGia0() {
		hoadonphong.setGiamGia(0);
		try {
			int MadvExpected = 0;
			Assert.assertEquals(MadvExpected, hoadonphong.getGiamGia());
			System.out.println("TestSet giảm giá 0 thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet giảm giá 0 không thành công");
			fail("TestSet giảm giá 0 không thành công");
		}
	}

	@Test
	public void testGetPhuThu() {
		hoadonphong.setPhuThu(50000);
		try {
			float MadvExpected = 50000;
			Assert.assertNotEquals(MadvExpected, hoadonphong.getPhuThu());
			System.out.println("TestGet Phụ thu thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestGet Phụ thu không thành công");
			fail("TestGet Phụ thu không thành công");
		}
	}

	@Test
	public void testSetPhuThu() {
		hoadonphong.setPhuThu(50000);
		try {
			float MadvExpected = 50000;
			Assert.assertNotEquals(MadvExpected, hoadonphong.getPhuThu());
			System.out.println("TestSet Phụ thu thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet Phụ thu không thành công");
			fail("TestSet Phụ thu không thành công");
		}
	}

	@Test
	public void testSetPhuThu0() {
		hoadonphong.setPhuThu(0);
		try {
			float MadvExpected = 0;
			Assert.assertNotEquals(MadvExpected, hoadonphong.getPhuThu());
			System.out.println("TestSet Phụ thu 0 thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet Phụ thu 0 không thành công");
			fail("TestSet Phụ thu 0 không thành công");
		}
	}

	@Test
	public void testGetThanhToan() {
		hoadonphong.setThanhToan("Tiền Mặt");
		try {
			String MadvExpected = "Tiền Mặt";
			Assert.assertEquals(MadvExpected, hoadonphong.getThanhToan());
			System.out.println("TesGet thanh toán thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestGet thanh toán không thành công");
			fail("TestGet thanh toán không thành công");
		}
	}

	@Test
	public void testSetThanhToan() {
		hoadonphong.setThanhToan("Tiền Mặt");
		try {
			String MadvExpected = "Tiền Mặt";
			Assert.assertEquals(MadvExpected, hoadonphong.getThanhToan());
			System.out.println("TestSet thanh toán Tiền Mặt thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet thanh toán Tiền Mặt không thành công");
			fail("TestSet thanh toán Tiền Mặt không thành công");
		}
	}

	@Test
	public void testSetThanhToanCK() {
		hoadonphong.setThanhToan("Chuyển Khoản");
		try {
			String MadvExpected = "Chuyển Khoản";
			Assert.assertEquals(MadvExpected, hoadonphong.getThanhToan());
			System.out.println("TestSet thanh toán Chuyển Khoản thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet thanh toán Chuyển Khoản không thành công");
			fail("TestSet thanh toán Chuyển Khoản không thành công");
		}
	}

	@Test
	public void testSetThanhToanNH() {
		hoadonphong.setThanhToan("Thẻ Ngân Hàng");
		try {
			String MadvExpected = "Thẻ Ngân Hàng";
			Assert.assertEquals(MadvExpected, hoadonphong.getThanhToan());
			System.out.println("TestSet thanh toán Thẻ Ngân Hàng thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet thanh toán Thẻ Ngân Hàng không thành công");
			fail("TestSet thanh toán Thẻ Ngân Hàng không thành công");
		}
	}

	@Test
	public void testGetTraTruoc() {
		hoadonphong.setTraTruoc(50000);
		try {
			float MadvExpected = 50000;
			Assert.assertNotEquals(MadvExpected, hoadonphong.getTraTruoc());
			System.out.println("TestGet Trả trước thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestGet Trả trước không thành công");
			fail("TestGet Trả trước không thành công");
		}
	}

	@Test
	public void testSetTraTruoc() {
		hoadonphong.setTraTruoc(50000);
		try {
			float MadvExpected = 50000;
			Assert.assertNotEquals(MadvExpected, hoadonphong.getTraTruoc());
			System.out.println("TestSet Trả trước thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet Trả trước không thành công");
			fail("TestSet Trả trước không thành công");
		}
	}

	@Test
	public void testSetTraTruoc0() {
		hoadonphong.setTraTruoc(0);
		try {
			float MadvExpected = 0;
			Assert.assertNotEquals(MadvExpected, hoadonphong.getTraTruoc());
			System.out.println("TestSet Trả trước 0 thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet Trả trước 0 không thành công");
			fail("TestSet Trả trước 0 không thành công");
		}
	}

	@Test
	public void testGetNgayXuatHoaDon() throws Exception {
		hoadonphong.setNgayXuatHoaDon(formatDate.parse("12/02/2012"));
		try {
			Date MadvExpected = formatDate.parse("12/02/2012");
			Assert.assertEquals(MadvExpected, hoadonphong.getNgayXuatHoaDon());
			System.out.println("TestGet Ngày xuất hóa đơn thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestGet Ngày xuất hóa đơn không thành công");
			fail("TestGet Ngày xuất hóa đơn không thành công");
		}
	}

	@Test
	public void testSetNgayXuatHoaDon() throws Exception {
		hoadonphong.setNgayXuatHoaDon(formatDate.parse("12/02/2012"));
		try {
			Date MadvExpected = formatDate.parse("12/02/2012");
			Assert.assertEquals(MadvExpected, hoadonphong.getNgayXuatHoaDon());
			System.out.println("TestSet Ngày xuất hóa đơn thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet Ngày xuất hóa đơn không thành công");
			fail("TestSet Ngày xuất hóa đơn không thành công");
		}
	}

	@Test
	public void testGetMaHD() {
		hoadonphong.setMaHD("HD01");
		try {
			String MadvExpected = "HD01";
			Assert.assertEquals(MadvExpected, hoadonphong.getMaHD());
			System.out.println("TestGet mã hóa đơn thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestGet mã hóa đơn không thành công");
			fail("TestGet mã hóa đơn không thành công");
		}
	}

	@Test
	public void testSetMaHD() {
		hoadonphong.setMaHD("HD01");
		try {
			String MadvExpected = "HD01";
			Assert.assertEquals(MadvExpected, hoadonphong.getMaHD());
			System.out.println("TestSet mã hóa đơn thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet mã hóa đơn không thành công");
			fail("TestSet mã hóa đơn không thành công");
		}
	}

	@Test
	public void testGetNgayNhanPhong() throws Exception {
		hoadonphong.setNgayNhanPhong(formatDate.parse("12/02/2012"));
		try {
			Date MadvExpected = formatDate.parse("12/02/2012");
			Assert.assertEquals(MadvExpected, hoadonphong.getNgayNhanPhong());
			System.out.println("TestGet Ngày nhạn phòng thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestGet Ngày nhạn phòng không thành công");
			fail("TestGet Ngày nhạn phòng không thành công");
		}
	}

	@Test
	public void testSetNgayNhanPhong() throws Exception {
		hoadonphong.setNgayNhanPhong(formatDate.parse("12/02/2012"));
		try {
			Date MadvExpected = formatDate.parse("12/02/2012");
			Assert.assertEquals(MadvExpected, hoadonphong.getNgayNhanPhong());
			System.out.println("TestSet Ngày nhạn phòng thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet Ngày nhạn phòng không thành công");
			fail("TestSet Ngày nhạn phòng không thành công");
		}
	}

	@Test
	public void testGetNgayTraPhong() throws Exception {
		hoadonphong.setNgayTraPhong(formatDate.parse("21/02/2012"));
		try {
			Date MadvExpected = formatDate.parse("21/02/2012");
			Assert.assertEquals(MadvExpected, hoadonphong.getNgayTraPhong());
			System.out.println("TestGet Ngày trả phòng thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestGet Ngày trả phòng không thành công");
			fail("TestGet Ngày nhạn phòng không thành công");
		}
	}

	@Test
	public void testSetNgayTraPhong() throws Exception {
		hoadonphong.setNgayTraPhong(formatDate.parse("21/02/2012"));
		try {
			Date MadvExpected = formatDate.parse("21/02/2012");
			Assert.assertEquals(MadvExpected, hoadonphong.getNgayTraPhong());
			System.out.println("TestSet Ngày trả phòng thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet Ngày trả phòng không thành công");
			fail("TestSet Ngày nhạn phòng không thành công");
		}
	}

	@Test
	public void testGetGhiChu() {
		hoadonphong.setGhiChu("oki");
		try {
			String MadvExpected = "oki";
			Assert.assertEquals(MadvExpected, hoadonphong.getGhiChu());
			System.out.println("TestGet Ghi chú thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestGet Ghi chú không thành công");
			fail("TestGet Ghi chú không thành công");
		}
	}

	@Test
	public void testSetGhiChu() {
		hoadonphong.setGhiChu("oki");
		try {
			String MadvExpected = "oki";
			Assert.assertEquals(MadvExpected, hoadonphong.getGhiChu());
			System.out.println("TestSet Ghi chú thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet Ghi chú không thành công");
			fail("TestSet Ghi chú không thành công");
		}
	}

	@Test
	public void testSetGhiChurong() {
		hoadonphong.setGhiChu("");
		try {
			String MadvExpected = "";
			Assert.assertEquals(MadvExpected, hoadonphong.getGhiChu());
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
			Assert.assertEquals(MadvExpected, hoadonphong.getGhiChu());
			System.out.println("TestSet Ghi chú null thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet Ghi chú null không thành công");
			fail("TestSet Ghi chú null không thành công");
		}
	}

	@Test
	public void testGetMaNV() {
		hoadonphong.setMaNV("NV01");
		try {
			String MadvExpected = "NV01";
			Assert.assertEquals(MadvExpected, hoadonphong.getMaNV());
			System.out.println("TestGet mã Nhân Viên thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestGet mã Nhân Viên không thành công");
			fail("TestGet mã Nhân Viên không thành công");
		}
	}

	@Test
	public void testSetMaNV() {
		hoadonphong.setMaNV("NV01");
		try {
			String MadvExpected = "NV01";
			Assert.assertEquals(MadvExpected, hoadonphong.getMaNV());
			System.out.println("TestSet mã Nhân Viên thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet mã Nhân Viên không thành công");
			fail("TestSet mã Nhân Viên không thành công");
		}
	}

	@Test
	public void testSetMaNVrong() {
		hoadonphong.setMaNV("");
		try {
			String MadvExpected = "";
			Assert.assertEquals(MadvExpected, hoadonphong.getMaNV());
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
			Assert.assertEquals(MadvExpected, hoadonphong.getMaNV());
			System.out.println("TestSet mã Nhân Viên null thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet mã Nhân Viên null không thành công");
			fail("TestGet mã Nhân Viên null không thành công");
		}
	}

	@Test
	public void testGetMaPhong() {
		hoadonphong.setMaPhong("P101");
		try {
			String MadvExpected = "P101";
			Assert.assertEquals(MadvExpected, hoadonphong.getMaPhong());
			System.out.println("TestGet mã phòng thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestGet mã phòng không thành công");
			fail("TestGet mã phòng không thành công");
		}
	}

	@Test
	public void testSetMaPhong() {
		hoadonphong.setMaPhong("P101");
		try {
			String MadvExpected = "P101";
			Assert.assertEquals(MadvExpected, hoadonphong.getMaPhong());
			System.out.println("TestSet mã phòng thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet mã phòng không thành công");
			fail("TestSet mã phòng không thành công");
		}
	}

	@Test
	public void testGetMaHDCT() {
		hoadonphong.setMaHDCT(101);
		try {
			int MadvExpected = 101;
			Assert.assertEquals(MadvExpected, hoadonphong.getMaHDCT());
			System.out.println("TestGet mã HDCT thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestGet mã HDCT không thành công");
			fail("TestGet mã HDCT không thành công");
		}
	}

	@Test
	public void testSetMaHDCT() {
		hoadonphong.setMaHDCT(101);
		try {
			int MadvExpected = 101;
			Assert.assertEquals(MadvExpected, hoadonphong.getMaHDCT());
			System.out.println("TestSet mã HDCT thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet mã HDCT không thành công");
			fail("TestSet mã HDCT không thành công");
		}
	}

	@Test
	public void testSetMaHDCT0() {
		hoadonphong.setMaHDCT(0);
		try {
			int MadvExpected = -1;
			Assert.assertNotEquals(MadvExpected, hoadonphong.getMaHDCT());
			System.out.println("TestSet mã HDCT <0 thành công");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("TestSet mã HDCT <0 không thành công");
			fail("TestSet mã HDCT <0 không thành công");
		}
	}

}
