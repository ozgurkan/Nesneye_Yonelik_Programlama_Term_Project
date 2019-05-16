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
		//System.out.println("Ara� "+fark+" dakika otoparkda kald�.");
		JOptionPane.showMessageDialog(null, "Ara� "+fark+" dakika otoparkda kald�.");
		return fark;	
	}
	
	@Override
	public String toString() {		
		if(vehicle.isSpecial()==true) {			
			return "Ara� Plaka:	"+ vehicle.getPlate()+ "	 Giri� Saati:	" + enterTime +  "	 ��k�� Saati:	" + exitTime+"  --�zel araba";
		}
		else if(vehicle.getSubscription()!=null){
			return "Ara� Plaka:	"+ vehicle.getPlate()+ "	 Giri� Saati:	" + enterTime +  "	 ��k�� Saati:	" + exitTime+"  --�ye araba";
		}
			return "Ara� Plaka:	"+ vehicle.getPlate()+ "	 Giri� Saati:	" + enterTime +  "	 ��k�� Saati:	" + exitTime+" --normal araba";	
	}	
}
