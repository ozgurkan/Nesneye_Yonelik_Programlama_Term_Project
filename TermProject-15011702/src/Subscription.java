import javax.swing.JOptionPane;

public class Subscription {
	private Date begin, end; 
	private SubscribedVehicle vehicle;	
	
	public Subscription(Date begin, Date end,String plate ) {
		this.begin=begin;
		this.end=end;
			if(isValid()==true) {
				vehicle = new SubscribedVehicle(plate); 
				vehicle.setSubscription(this);
			}
			else {
				//System.out.println("üyelik tarihi hatalý");
				JOptionPane.showMessageDialog(null, "üyelik tarihi hatalý");
			}
	}
	
	public Date getBegin() {
		return begin;
	}
	
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	
	public Date getEnd() {
		return end;
	}
	
	public void setEnd(Date end) {
		this.end = end;
	}	
	
	public SubscribedVehicle getVehicle() {
		return vehicle;
	}
	
	public void setVehicle(SubscribedVehicle vehicle) {
		this.vehicle = vehicle;
		if( vehicle.getSubscription() != this )
			this.vehicle.setSubscription(this); 
	}

	public boolean isValid() {
		Date bugun;
		bugun=Date.getToday();
		if(end.isAfterThan(bugun)==true && begin.isBeforeThan(bugun)==true) {
			return true;
		}
		if(begin.isEqualsWith(bugun)==true && end.isAfterThan(bugun)==true) {
			return true;
		}
		if(end.isEqualsWith(bugun)==true && begin.isBeforeThan(bugun)==true) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {		
		return "Araç plakasý:" + vehicle.getPlate() + "	Üyelik Baslangic Tarihi:" + begin + "	Üyelik Bitis Tarihi:" + end ;
	}	
}
