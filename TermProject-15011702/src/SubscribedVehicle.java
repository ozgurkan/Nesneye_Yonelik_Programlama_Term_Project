public class SubscribedVehicle implements Vehicle {
	private String plate;
	private Subscription subscription;	
	
	public SubscribedVehicle(String plate) {
		this.plate=plate;
	}
	
	public void setPlate(String plate) {
		this.plate=plate;
	}	
	
	public void setSubscription(Subscription subscription) {
		this.subscription=subscription;
		if( subscription.getVehicle() != this )
			subscription.setVehicle(this);
	}	
	
	@Override
	public String getPlate() {
		return plate;
	}
	
	@Override
	public Subscription getSubscription() {
		return subscription;
	}

	@Override
	public boolean isSpecial() {
		return false;
	}
}
