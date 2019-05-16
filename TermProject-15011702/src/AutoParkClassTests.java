import junit.framework.TestCase;

public class AutoParkClassTests extends TestCase {
	AutoPark otopark;
	protected void setUp() throws Exception {
		super.setUp();
		otopark=new AutoPark(50,100);
	}
	

	public void testSearchVevihle() {
		assertEquals(null, otopark.searchVehicle("34-jh-1219"));		
	}
	
	public void testIsParkedTrue() {
		String plate="34-aa-1994";
		RegularVehicle vehicle=new RegularVehicle(plate);
		Time enter= new Time(10,00);
		boolean isOfficial=true;
		otopark.vehicleEnters(vehicle.getPlate(), enter, isOfficial);
		assertEquals(true,otopark.isParked(plate));	
	}
	
	public void testIsParkedFalse() {
		assertEquals(false,otopark.isParked("34-tt-1212"));	
	}
		
	public void testAddVehicle() {
		SubscribedVehicle a= new SubscribedVehicle("34-aa-1994");
		assertEquals(true, otopark.addVehicle(a));
	}
	
	public void testVehiclEnters() {
		String plate;
		Time enter;
		boolean isOfficial;
		plate="34-jh-12190";//polis arabasý
		enter =new Time(9,00);
		isOfficial=true;
		assertEquals(true, otopark.vehicleEnters(plate, enter, isOfficial));
		
		plate="34-jh-12191";//normal araba veya üye araba kullanýcý seçer
		enter =new Time(10,00);
		isOfficial=false;
		assertEquals(true, otopark.vehicleEnters(plate, enter, isOfficial));
	}
	
	
	public void testVehiclExits() {
		String plate;
		Time enter,exit;
		boolean isOfficial;
		plate="34-jh-12190";//polis arabasý
		enter =new Time(9,00);
		exit=new Time(12,00);
		isOfficial=false;
		otopark.vehicleEnters(plate, enter, isOfficial);
		assertEquals(true, otopark.vehicleExits(plate, exit));
	}
	public void testGetIncomeDailyOfficialVehicle() {
		String plate;
		Time enter,exit;
		boolean isOfficial;
		plate="34-jh-12190";//polis arabasý
		enter =new Time(9,00);
		exit=new Time(12,00);
		isOfficial=true;
		otopark.vehicleEnters(plate, enter, isOfficial);
		otopark.vehicleExits(plate, exit);
		assertEquals(0.0, otopark.getIncomeDaily());
	}
	
	public void testGetIncomeDailyNotOfficialVehicle() {
		String plate;
		Time enter,exit;
		boolean isOfficial;
		plate="34-jh-12190";//polis arabasý
		enter =new Time(9,00);
		exit=new Time(12,00);
		isOfficial=false;
		otopark.vehicleEnters(plate, enter, isOfficial);
		otopark.vehicleExits(plate, exit);
		if(otopark.searchVehicle(plate)!=null) {
			assertEquals(75.0, otopark.getIncomeDaily());
		}else {
			assertEquals(150.0, otopark.getIncomeDaily());
		}		
	}
	
	public void testGetCapacity() {
		int capacity= otopark.getCapacity();
		assertEquals(100, capacity);
	}
	
	
	/*public void testParkrcordsAddRegularVehicle() {
		RegularVehicle vehicle=new RegularVehicle("34-jh-1219");
		Time enter= new Time(10,00);
		boolean isOfficial=false;
		assertEquals(true, otopark.vehicleEnters(vehicle.getPlate(), enter, isOfficial));
	}*/

}
