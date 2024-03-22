package Model;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import Utils.DateHelper;

public class NhanVienTest {
	NhanVien nv;

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	@Before
	public void setUp() throws Exception {
		nv = new NhanVien();
		collector.addError(new Throwable("Loi o dong 1"));
		collector.addError(new Throwable("Loi o dong 2"));
		System.out.println("tao doi tuong nhan vien thanh cong");
	}

	@After
	public void tearDown() throws Exception {
		nv = null;
		System.out.println("huy bo doi tuong nhan vien thanh cong");
	}

	@Test
	public void testNhanVien() {
		NhanVien testNhanVien;

		try {
			testNhanVien = new NhanVien("B005", "123456", "Chau Minh Trung", 0, 1, "minhtrungchau123@gmail.com",
					"0907274629", "1231312312", "binh duong", "trung.jpg",
					DateHelper.toDate("2004-01-01", "dd-MM-yyyy"));
			System.out.println("testNhanVien thanh cong");
		} catch (Exception e) {
			collector.addError(e);
			System.out.println("testNhanVien khong thanh cong");
			fail("testNhanVien khong thanh cong ");
		}
	}

	@Test
	public void testGetMaNV() throws Exception {
		nv.setMaNV("B005");
		try {
			String ManvExpected = "B005";
			Assert.assertEquals(ManvExpected, nv.getMaNV());
			System.out.println("testGetMaNV thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testGetMaNV khong thanh cong");
			fail("testGetMaNV khong thanh cong");
		}
	}

	@Test
	public void testSetMaNV() throws Exception {
		nv.setMaNV("B005");
		try {
			String ManvExpected = "B005";
			Assert.assertEquals(ManvExpected, nv.getMaNV());
			System.out.println("testSetMaNV thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testSetMaNV khong thanh cong");
			fail("testSetMaNV khong thanh cong");
		}
	}

	@Test
	public void testSetMaNVTrong() throws Exception {
		nv.setMaNV("");
		try {
			String ManvExpected = "";
			Assert.assertEquals(ManvExpected, nv.getMaNV());
			System.out.println("testSetMaNVTrong thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testSetMaNVTrong khong thanh cong");
			fail("testSetMaNVTrong khong thanh cong");
		}
	}

	@Test
	public void testSetMaNVNhieu() throws Exception {
		nv.setMaNV("adsasdnaodnasod");
		try {
			String ManvExpected = "adsasdnaodnasod";
			Assert.assertEquals(ManvExpected, nv.getMaNV());
			System.out.println("testSetMaNVNhieu thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testSetMaNVNhieu khong thanh cong");
			fail("testSetMaNVNhieu khong thanh cong");
		}
	}

	@Test
	public void testSetMaNVIt() throws Exception {
		nv.setMaNV("ab");
		try {
			String ManvExpected = "ab";
			Assert.assertEquals(ManvExpected, nv.getMaNV());
			System.out.println("testSetMaNVIt thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testSetMaNVIt khong thanh cong");
			fail("testSetMaNVIt khong thanh cong");
		}
	}

	@Test
	public void testSetMaNVNull() throws Exception {
		try {
			nv.setMaNV(null);
			System.out.println("testSetMaNVNull thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testSetMaNVNull khong thanh cong");
			fail("testSetMaNVNull khong thanh cong");
		}
	}

	@Test
	public void testGetNgaySinh() throws Exception {
		nv.setNgaySinh(DateHelper.toDate("2004-01-01", "dd-MM-yyyy"));
		try {
			Date NgaySinhExpected = DateHelper.toDate("2004-01-01", "dd-MM-yyyy");
			Assert.assertEquals(NgaySinhExpected, nv.getNgaySinh());
			System.out.println("testGetNgaySinh thanh cong");
		} catch (IllegalArgumentException e) {
			System.out.println("testGetNgaySinh khong thanh cong");
			fail("testGetNgaySinh khong thanh cong");
		}
	}

	@Test
	public void testSetNgaySinh() throws Exception {
		nv.setNgaySinh(DateHelper.toDate("2004-01-01", "dd-MM-yyyy"));
		try {
			Date NgaySinhExpected = DateHelper.toDate("2004-01-01", "dd-MM-yyyy");
			Assert.assertEquals(NgaySinhExpected, nv.getNgaySinh());
			System.out.println("testSetNgaySinh thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testSetNgaySinh khong thanh cong");
			fail("testSetNgaySinh khong thanh cong");
		}
	}

	@Test
	public void testSetNgaySinhTrong() throws Exception {
		nv.setNgaySinh(DateHelper.toDate("", "dd-MM-yyyy"));
		try {
			Date NgaySinhExpected = DateHelper.toDate("", "dd-MM-yyyy");
			Assert.assertEquals(NgaySinhExpected, nv.getNgaySinh());
			System.out.println("testSetNgaySinhTrong thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testSetNgaySinhTrong khong thanh cong");
			fail("testSetNgaySinhTrong khong thanh cong");
		}
	}

	@Test
	public void testGetMatKhau() throws Exception {
		nv.setMatKhau("123456");
		try {
			String MatKhauExpected = "123456";
			Assert.assertEquals(MatKhauExpected, nv.getMatKhau());
			System.out.println("testGetMatKhau thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testGetMatKhau khong thanh cong");
			fail("testGetMatKhau khong thanh cong");
		}
	}

	@Test
	public void testSetMatKhau() throws Exception {
		nv.setMatKhau("123456");
		try {
			String MatKhauExpected = "123456";
			Assert.assertEquals(MatKhauExpected, nv.getMatKhau());
			System.out.println("testSetMatKhau thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testSetMatKhau khong thanh cong");
			fail("testSetMatKhau khong thanh cong");
		}
	}

	@Test
	public void testSetMatKhauTrong() throws Exception {
		nv.setMatKhau("");
		try {
			String MatKhauExpected = "";
			Assert.assertEquals(MatKhauExpected, nv.getMatKhau());
			System.out.println("testSetMatKhauTrong thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testSetMatKhauTrong khong thanh cong");
			fail("testSetMatKhauTrong khong thanh cong");
		}
	}

	@Test
	public void testSetMatKhauDai() throws Exception {
		nv.setMatKhau("dasjdansdajdnla");
		try {
			String MatKhauExpected = "dasjdansdajdnla";
			Assert.assertEquals(MatKhauExpected, nv.getMatKhau());
			System.out.println("testSetMatKhauDai thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testSetMatKhauDai khong thanh cong");
			fail("testSetMatKhauDai khong thanh cong");
		}
	}

	@Test
	public void testSetMatKhauIt() throws Exception {
		nv.setMatKhau("12");
		try {
			String MatKhauExpected = "12";
			Assert.assertEquals(MatKhauExpected, nv.getMatKhau());
			System.out.println("testSetMatKhauDai thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testSetMatKhauDai khong thanh cong");
			fail("testSetMatKhauDai khong thanh cong");
		}
	}

	@Test
	public void testGetHoVaTen() throws Exception {
		nv.setHoVaTen("Chau Minh Trung");
		try {
			String HoVaTenExpected = "Chau Minh Trung";
			Assert.assertEquals(HoVaTenExpected, nv.getHoVaTen());
			System.out.println("testGetHoVaTen thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testGetHoVaTen khong thanh cong");
			fail("testGetHoVaTen khong thanh cong");
		}
	}

	@Test
	public void testSetHoVaTen() throws Exception {
		nv.setHoVaTen("Chau Minh Trung");
		try {
			String HoVaTenExpected = "Chau Minh Trung";
			Assert.assertEquals(HoVaTenExpected, nv.getHoVaTen());
			System.out.println("testSetHoVaTen thanh cong");
		} catch (Throwable e) {
			collector.addError(e);
			System.out.println("testSetHoVaTen khong thanh cong");
			fail("testSetHoVaTen khong thanh cong");
		}
	}

	@Test
	public void testSetHoVaTenTrong() throws Exception {
		nv.setHoVaTen("");
		try {
			String HoVaTenExpected = "";
			Assert.assertEquals(HoVaTenExpected, nv.getHoVaTen());
			System.out.println("testSetHoVaTen thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testSetHoVaTen khong thanh cong");
			fail("testSetHoVaTen khong thanh cong");
		}
	}

	@Test
	public void testSetHoVaTenDai() throws Exception {
		nv.setHoVaTen("Chau Minh Trung oke duoc chua");
		try {
			String HoVaTenExpected = "Chau Minh Trung oke duoc chua";
			Assert.assertEquals(HoVaTenExpected, nv.getHoVaTen());
			System.out.println("testSetHoVaTenDai thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testSetHoVaTenDai khong thanh cong");
			fail("testSetHoVaTenDai khong thanh cong");
		}
	}

	@Test
	public void testSetHoVaTenIt() throws Exception {
		nv.setHoVaTen("Chau");
		try {
			String HoVaTenExpected = "Chau";
			Assert.assertEquals(HoVaTenExpected, nv.getHoVaTen());
			System.out.println("testSetHoVaTenIt thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testSetHoVaTenIt khong thanh cong");
			fail("testSetHoVaTenIt khong thanh cong");
		}
	}

	@Test
	public void testGetVaiTro() throws Exception {
		nv.setVaiTro(1);
		try {
			int VaiTroExpected = 1;
			Assert.assertEquals(VaiTroExpected, nv.getVaiTro());
			System.out.println("testGetVaiTro thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testGetVaiTro khong thanh cong");
			fail("testGetVaiTro khong thanh cong");
		}
	}

	@Test
	public void testSetVaiTro() throws Exception {
		nv.setVaiTro(1);
		try {
			int VaiTroExpected = 1;
			Assert.assertEquals(VaiTroExpected, nv.getVaiTro());
			System.out.println("testSetVaiTro thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testSetVaiTro khong thanh cong");
			fail("testSetVaiTro khong thanh cong");
		}
	}

	@Test
	public void testGetGioiTinh() throws Exception {
		nv.setGioiTinh(1);
		try {
			int GioiTinhExpected = 1;
			Assert.assertEquals(GioiTinhExpected, nv.getGioiTinh());
			System.out.println("testGetGioiTinh thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testGetGioiTinh khong thanh cong");
			fail("testSetVaiTro khong thanh cong");
		}
	}

	@Test
	public void testSetGioiTinh() throws Exception {
		nv.setGioiTinh(1);
		try {
			int GioiTinhExpected = 1;
			Assert.assertEquals(GioiTinhExpected, nv.getGioiTinh());
			System.out.println("testSetGioiTinh thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testSetGioiTinh khong thanh cong");
			fail("testSetGioiTinh khong thanh cong");
		}
	}

	@Test
	public void testGetEmail() throws Exception {
		nv.setEmail("minhtrungchau123@gmail.com");
		try {
			String EmailExpected = "minhtrungchau123@gmail.com";
			Assert.assertEquals(EmailExpected, nv.getEmail());
			System.out.println("testGetEmail thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testGetEmail khong thanh cong");
			fail("testGetEmail khong thanh cong");
		}
	}

	@Test
	public void testSetEmail() throws Exception {
		nv.setEmail("minhtrungchau123@gmail.com");
		try {
			String EmailExpected = "minhtrungchau123@gmail.com";
			Assert.assertEquals(EmailExpected, nv.getEmail());
			System.out.println("testSetEmail thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testSetEmail khong thanh cong");
			fail("testSetEmail khong thanh cong");
		}
	}

	@Test
	public void testGetSdt() throws Exception {
		nv.setSdt("0907274629");
		try {
			String SdtlExpected = "0907274629";
			Assert.assertEquals(SdtlExpected, nv.getSdt());
			System.out.println("testGetSdt thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testGetSdt khong thanh cong");
			fail("testGetSdt khong thanh cong");
		}
	}

	@Test
	public void testSetSdt() throws Exception {
		nv.setSdt("0907274629");
		try {
			String SdtlExpected = "0907274629";
			Assert.assertEquals(SdtlExpected, nv.getSdt());
			System.out.println("testSetSdt thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testSetSdt khong thanh cong");
			fail("testSetSdt khong thanh cong");
		}
	}

	@Test
	public void testGetCCCD() throws Exception {
		nv.setCCCD("012318172");
		try {
			String CCDlExpected = "012318172";
			Assert.assertEquals(CCDlExpected, nv.getCCCD());
			System.out.println("testGetCCCD thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testGetCCCD khong thanh cong");
			fail("testGetCCCD khong thanh cong");
		}
	}

	@Test
	public void testSetCCCD() throws Exception {
		nv.setCCCD("012318172");
		try {
			String CCDlExpected = "012318172";
			Assert.assertEquals(CCDlExpected, nv.getCCCD());
			System.out.println("testSetCCCD thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testSetCCCD khong thanh cong");
			fail("testSetCCCD khong thanh cong");
		}
	}

	@Test
	public void testGetDiaChi() throws Exception {
		nv.setDiaChi("binh duong");
		try {
			String DiaChilExpected = "binh duong";
			Assert.assertEquals(DiaChilExpected, nv.getDiaChi());
			System.out.println("testGetDiaChi thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testGetDiaChi khong thanh cong");
			fail("testGetDiaChi khong thanh cong");
		}
	}

	@Test
	public void testSetDiaChi() throws Exception {
		nv.setDiaChi("binh duong");
		try {
			String DiaChilExpected = "binh duong";
			Assert.assertEquals(DiaChilExpected, nv.getDiaChi());
			System.out.println("testSetDiaChi thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testSetDiaChi khong thanh cong");
			fail("testSetDiaChi khong thanh cong");
		}
	}

	@Test
	public void testGetHinh() throws Exception {
		nv.setHinh("minhtrung.jpg");
		try {
			String HinhlExpected = "minhtrung.jpg";
			Assert.assertEquals(HinhlExpected, nv.getHinh());
			System.out.println("testGetHinh thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testGetHinh khong thanh cong");
			fail("testGetHinh khong thanh cong");
		}
	}

	@Test
	public void testSetHinh() throws Exception {
		nv.setHinh("minhtrung.jpg");
		try {
			String HinhlExpected = "minhtrung.jpg";
			Assert.assertEquals(HinhlExpected, nv.getHinh());
			System.out.println("testSetHinh thanh cong");
		} catch (IllegalArgumentException e) {
			collector.addError(e);
			System.out.println("testSetHinh khong thanh cong");
			fail("testSetHinh khong thanh cong");
		}
	}

}
