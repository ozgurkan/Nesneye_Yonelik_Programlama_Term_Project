import javax.swing.JOptionPane;

public class ParkRecord {
	private Time enterTime,exitTime;
	private Vehicle vehicle;
	
	public ParkRecord(Time enterTime) {
		this.enterTime=enterTime;		
 	}
	public void setEnterTime(Time enterTime) {this.enterTime=enterTime;}
	public void setExitTime(Time exitTime) {this.exitTime=exitTime;}
	
	public Time getEnterTime() {return enterTime;}
	public Time getExitTime() {return exitTime;}
	
	public void setVehicle( Vehicle vehicle ) { this.vehicle = vehicle; }
	public Vehicle getVehicle() {return vehicle;}
	
	
	public int getParkDuration() {		
		int fark= enterTime.getDifference(exitTime);	
		//System.out.println("Araç "+fark+" dakika otoparkda kaldý.");
		JOptionPane.showMessageDialog(null, "Araç "+fark+" dakika otoparkda kaldý.");
		return fark;	
	}
	
	@Override
	public String toString() {		
		if(vehicle.isSpecial()==true) {			
			return "Araç Plaka:	"+ vehicle.getPlate()+ "	 Giriþ Saati:	" + enterTime +  "	 Çýkýþ Saati:	" + exitTime+"  --özel araba";
		}
		else if(vehicle.getSubscription()!=null){
			return "Araç Plaka:	"+ vehicle.getPlate()+ "	 Giriþ Saati:	" + enterTime +  "	 Çýkýþ Saati:	" + exitTime+"  --üye araba";
		}
			return "Araç Plaka:	"+ vehicle.getPlate()+ "	 Giriþ Saati:	" + enterTime +  "	 Çýkýþ Saati:	" + exitTime+" --normal araba";	
	}	
}
