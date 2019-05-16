import junit.framework.TestCase;

public class DateClassTests extends TestCase {
 Date tarih,tarih1,tarih2;
	protected void setUp() throws Exception {
		super.setUp();
		tarih= new Date(12,12,2019);
		tarih1= new Date(10,10,2017);
		tarih2=Date.getToday();
	}
	
	public void testDateClassGecerlimi() {
		assertEquals(true, tarih.gecerliMi());
	}
	
	public void testDateClassIsAfterThanTrue() {
		assertEquals(true, tarih.isAfterThan(Date.getToday()));
	}
	public void testDateClassIsAfterThanFalse() {
		assertEquals(false, tarih1.isAfterThan(Date.getToday()));
	}
	
	public void testDateClassIsBeforeThanTrue() {
		assertEquals(false, tarih.isBeforeThan(Date.getToday()));
	}
	public void testDateClassIsBeforeThanFalse() {
		assertEquals(true, tarih1.isBeforeThan(Date.getToday()));
	}
	
	public void testDateClassIsEqualsWithTrue() {
		assertEquals(true, tarih2.isEqualsWith(Date.getToday()));
	}
	public void testDateClassIsEqualsWithFalse() {
		assertEquals(false, tarih1.isEqualsWith(Date.getToday()));
	}
}
