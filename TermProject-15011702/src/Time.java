import java.time.LocalTime;

public class Time {
	private int hour;
	private int minute;
	public static LocalTime now= LocalTime.now();
	
	public Time(int hour,int minute) {
		this.hour=hour;
		this.minute=minute;
	}
	
	public int getHour() {return hour;}
	public int getMinute() {return minute;}
	
	public void setHour(int hour) {this.hour=hour;}
	public void setMinute(int minute) {this.minute=minute;}
	
	public int getDifference(Time other) {
		int dif;
		int a;
		int b;
			a=other.hour*60 + other.minute;
			b=this.hour*60 + this.minute;
			if(a>b) {
				dif=a-b;
			}else {
				dif=b-a;
			}	
		return dif;
	}
	
	public static Time getTime() {
		Time suan=new Time(LocalTime.now().getHour() ,LocalTime.now().getMinute() );
		return suan;
	}
	
	@Override
	public String toString() {
		return hour + ":" + minute;
	}	
}
