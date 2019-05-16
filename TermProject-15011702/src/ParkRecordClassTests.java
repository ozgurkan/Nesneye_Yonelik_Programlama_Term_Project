import junit.framework.TestCase;

public class ParkRecordClassTests extends TestCase {
	Time enterTime= new Time(12,00);
	Time exitTime= new Time(14,00);
	ParkRecord a= new ParkRecord(enterTime);
	

	protected void setUp() throws Exception {
		super.setUp();	
	}	
	public void testParcRecordGetParkDuration() {	
		assertEquals(120, enterTime.getDifference(exitTime));
	}

}
