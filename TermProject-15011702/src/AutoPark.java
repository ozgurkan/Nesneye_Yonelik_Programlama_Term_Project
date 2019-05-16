//import java.util.Scanner;

import java.text.DecimalFormat;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AutoPark {
	private SubscribedVehicle subscribedVehicles[];
	private ParkRecord parkRecords[];
	private ParkRecord exitRecords[];
	private double hourlyFee,incomeDaily=0;
	private int vehicleCount = 0;
	private int subsCount=0;
	private int exitVehicleCount=0;
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	
	public AutoPark(double hourlyFee,int capacity) {
		this.hourlyFee=hourlyFee;
		subscribedVehicles = new SubscribedVehicle[10000];
		parkRecords = new ParkRecord[capacity];
		exitRecords = new ParkRecord[10000];
	}

	public SubscribedVehicle searchVehicle( String plate ) {
		for( int i=0; i<subscribedVehicles.length; i++ ) {
			if( subscribedVehicles[i] != null && subscribedVehicles[i].getPlate().equalsIgnoreCase(plate) ==true ) {    
				return subscribedVehicles[i];    
			}
		}
		return null;	
	}
	
	public boolean isParked( String plate ) {		
		for( int i=0; i<parkRecords.length; i++ ) {
			if( parkRecords[i] != null && parkRecords[i].getVehicle().getPlate().equalsIgnoreCase(plate) ==true ) {    
				return true;    
			}
		}
		return false;			
	}
	
	private void enlargeVehicleArray() {		
		SubscribedVehicle[] newArray = new SubscribedVehicle[subscribedVehicles.length + 1];
		System.arraycopy(subscribedVehicles, 0, newArray, 0, subscribedVehicles.length);
	    subscribedVehicles = newArray;
	    //System.out.println("üye dizisi geniþledi");
	    JOptionPane.showMessageDialog(null, "üye dizisi geniþledi");
	}
	
	public boolean addVehicle( SubscribedVehicle vehicle ) {		
		if( searchVehicle(vehicle.getPlate()) != null )
			return false;
		if(subsCount==subscribedVehicles.length) {
			enlargeVehicleArray();
		}
		subscribedVehicles[subsCount]=vehicle;
		subsCount++;
		return true;
	}
	
	public boolean vehicleEnters( String plate, Time enter, boolean isOfficial ) { 

		if(vehicleCount<parkRecords.length) {
			if(isParked(plate)==true) {
				//System.out.println("arac park edilmiþ");
				return false;
			}
				
			
			if(isOfficial == true) {
				OfficialVehicle a= new OfficialVehicle(plate);			
				ParkRecord b=new ParkRecord(enter);
				b.setVehicle(a);
				parkRecords[vehicleCount]=b;
			}			
			else if(searchVehicle(plate)==null) {	
				//Scanner sayi = new Scanner(System.in); 
				//System.out.print("Üye olmak istiyormusunuz? üye olmak için 1 giriniz: ");
				int dialogResult = JOptionPane.showConfirmDialog (null, "Üye olmak istiyormusunuz? ","Warning",JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION){
						Date end;
						//end=new Date(Date.getToday().getDay(),Date.getToday().getMonth(),Date.getToday().getYear()+1);
						String end_year;
						String end_month;
						String end_day;
						end_year = JOptionPane.showInputDialog("Üyelik bitiþ yýlý");
						end_month = JOptionPane.showInputDialog("Üyelik bitiþ ayý");
						end_day = JOptionPane.showInputDialog("Üyelik bitiþ günü");
						end= new Date(Integer.parseInt(end_day),Integer.parseInt(end_month),Integer.parseInt(end_year));
						if(end.isBeforeThan(Date.getToday())) {
							JOptionPane.showMessageDialog(null, " Üyelik tarihi yanlýþ.Araç üye olamadý ve giriþ yapamadý.");
							return false;
						}else {
							Subscription c = new Subscription(Date.getToday(), end, plate);
							ParkRecord b=new ParkRecord(enter);
							addVehicle(c.getVehicle());
							b.setVehicle(c.getVehicle());
							parkRecords[vehicleCount]=b;	
						}
									
				}
				else {
					RegularVehicle a= new RegularVehicle(plate);			
					ParkRecord b=new ParkRecord(enter);
					b.setVehicle(a);
					parkRecords[vehicleCount]=b;
				}
				/*if(sayi.nextInt()==1) {
					Date end;
					end=new Date(12,6,2019);
					Subscription c = new Subscription(Date.getToday(), end, plate);
					ParkRecord b=new ParkRecord(enter);
					addVehicle(c.getVehicle());
					b.setVehicle(c.getVehicle());
					parkRecords[vehicleCount]=b;
				}else {
					RegularVehicle a= new RegularVehicle(plate);			
					ParkRecord b=new ParkRecord(enter);
					b.setVehicle(a);
					parkRecords[vehicleCount]=b;
				}	*/			
				//sayi.close();	
			}else {
				ParkRecord b=new ParkRecord(enter);
				searchVehicle(plate).getSubscription().getVehicle();
				addVehicle(searchVehicle(plate).getSubscription().getVehicle());
				b.setVehicle(searchVehicle(plate).getSubscription().getVehicle());
				parkRecords[vehicleCount]=b;	
			}
			vehicleCount++;			
			return true;
		}
		JOptionPane.showMessageDialog(null, " Otopark doludur.Giriþ yapamaz.");
		//System.out.println("Otopark doludur.Araba giriþ yapamaz.");		
		return false;		
	}
	
	public boolean vehicleExits(String plate, Time exit) {
		if(isParked(plate)==false) {
			//System.out.println("park kaydý yok");
			JOptionPane.showMessageDialog(null, " Park kayýdý bulunamadý.");
			return false;
			}
		else if(searchVehicle(plate)==null) {				
			for( int i=0; i<parkRecords.length; i++ ) {
				if( parkRecords[i] != null && parkRecords[i].getVehicle().getPlate().equalsIgnoreCase(plate) ==true  ) {    
					if(parkRecords[i].getVehicle().isSpecial()==true) {
						//System.out.println("bu araba özel araç park ücreti ödemez");
						JOptionPane.showMessageDialog(null, " bu araba özel araç park ücreti ödemez");
						arac_cikart(i,exit);
						i=parkRecords.length;
					}
					else {
						//System.out.println("bu araba normal araç üye deðil normal ücret öder");
						JOptionPane.showMessageDialog(null, "bu araba normal araç üye deðil normal ücret öder");
						parkRecords[i].setExitTime(exit);
						double total=parkRecords[i].getParkDuration()*(hourlyFee/60);
						//total=df2.format(total);
						//total=total*hourlyFee;
						//System.out.println("Ödenecek miktar:"+total);
						JOptionPane.showMessageDialog(null, "Ödenecek miktar:"+df2.format(total)+"TL");
						incomeDaily+=total;
						arac_cikart(i,exit);
						i=parkRecords.length;						
					}					
				}
			}
		}
		else {
			//System.out.println("bu araba normal araç fakat üye fiyatý az öder");
			JOptionPane.showMessageDialog(null, "Üye araba fiyatý az öder");
			for( int i=0; i<parkRecords.length; i++ ) {
				if( parkRecords[i] != null && parkRecords[i].getVehicle().getPlate().equalsIgnoreCase(plate) ==true  ) {  
					parkRecords[i].setExitTime(exit);
					double total=parkRecords[i].getParkDuration()*(hourlyFee/60);
					total=total/2;
					//System.out.println("Ödenecek miktar:"+total);
					JOptionPane.showMessageDialog(null, "Ödenecek miktar:"+df2.format(total)+"TL");
					incomeDaily+=total;
					arac_cikart(i,exit);
					i=parkRecords.length;										
				}				
			}
		}
		return true;		
	}
	
	public void arac_cikart(int i,Time exitTime) {
		if(exitRecords.length<exitVehicleCount) {
			enlargeExitRecordsArray();
		}
		exitRecords[exitVehicleCount]=parkRecords[i];
		exitRecords[exitVehicleCount].setExitTime(exitTime);
		exitVehicleCount++;
		parkRecords[i]=null;
		vehicleCount--;
		for( int j=i; j<parkRecords.length-1; j++ ) {
			parkRecords[j]=parkRecords[j+1];
		}
	}
	
	private void enlargeExitRecordsArray() {		
		ParkRecord[] newArray = new ParkRecord[exitRecords.length + 1];
		System.arraycopy(exitRecords, 0, newArray, 0, exitRecords.length+1);
		exitRecords = newArray;
	    //System.out.println("Çýkan araçlar dizisi geniþledi");
	    JOptionPane.showMessageDialog(null, "Çýkan araçlar dizisi geniþledi");
	}
	
	
	
	public void getParkRecords() {
		//System.out.println("-----------------PARK KAYITLARI-------------");
			JFrame window = new JFrame("OTOPARKTA BULUNAN ARAÇLAR"); 
			window.setBounds(300, 100, 600, 500);
			JPanel pMeasure = new JPanel();
			pMeasure.setLayout(new BoxLayout(pMeasure, BoxLayout.Y_AXIS));			
			window.setVisible(true);
			window.add(pMeasure);
			window.setResizable(false);
			if(vehicleCount==0) {
				pMeasure.add(new JLabel("<html>Otopark þuanda boþtur.<br/></html>"));
			}else {
				for( int i=0; i<vehicleCount; i++ ) {
					//System.out.println("indis:"+i+" "	+parkRecords[i].toString());
					pMeasure.add(new JLabel("<html>indis:"+i+"------"+parkRecords[i].toString()+"<br/></html>"));
				}
			}	
	}

	public void getSubscribedVehicle() {
		//System.out.println("-----------------UYE ARABALAR-------------");
		JFrame window = new JFrame("ÜYE OLAN ARAÇLAR"); 
		window.setBounds(300, 100, 600, 500);
		JPanel pMeasure = new JPanel();
		pMeasure.setLayout(new BoxLayout(pMeasure, BoxLayout.Y_AXIS));			
		window.setVisible(true);
		window.add(pMeasure);
		window.setResizable(false);
		if(subsCount==0) {
			pMeasure.add(new JLabel("<html>Üye olan araç yoktur.<br/></html>"));
		}else {
			for( int i=0; i<subsCount; i++ ) {
			//System.out.println(subscribedVehicles[i].getSubscription().toString());
			pMeasure.add(new JLabel("<html>indis:"+i+"------"+subscribedVehicles[i].getSubscription().toString()+"<br/></html>"));
			}
		}
		
	}
	
	
	public void getExitRecords() {
		//System.out.println("-----------------ÇIKIÞ YAPAN ARAÇLAR-------------");
		JFrame window = new JFrame("ÇIKIÞ YAPAN ARAÇLAR"); 
		window.setBounds(300, 100, 600, 500);
		JPanel pMeasure = new JPanel();
		pMeasure.setLayout(new BoxLayout(pMeasure, BoxLayout.Y_AXIS));			
		window.setVisible(true);
		window.add(pMeasure);
		window.setResizable(false);
		if(exitVehicleCount==0) {
			pMeasure.add(new JLabel("<html>Çýkýþ yapan araç yoktur.<br/></html>"));
		}else {		
			for( int i=0; i<exitVehicleCount; i++ ) {
				//System.out.println("indis:"+i+" "	+exitRecords[i].toString());
				pMeasure.add(new JLabel("<html>indis:"+i+"------"+exitRecords[i].toString()+"<br/></html>"));
			}
		}		
	}
	
	public double getIncomeDaily() {
		return incomeDaily;
	}	
	
	public int getCapacity() {
		return parkRecords.length;
	}
}
