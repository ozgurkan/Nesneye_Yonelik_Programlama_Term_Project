import java.util.*;
public class Date {
	 private int day, month, year; 
	 public static Calendar today = Calendar.getInstance();
	 
	 public Date(int day, int month, int year) {  
		 this.day = day; 
		 this.month = month; 
		 this.year = year;   
		 if( !gecerliMi() ) {    
			 this.day = today.get(Calendar.DAY_OF_MONTH);     
			 this.month = today.get(Calendar.MONTH)+1;    
			 this.year = today.get(Calendar.YEAR);   
			 }  
		 }  
	 public int getDay() { return day; }  
	 public int getMonth() { return month; }  
	 public int getYear() { return year; } 
	 public void setDay(int day) { this.day = day; }  
	 public void setMonth(int month) { this.month = month; }  
	 public void setYear(int year) { this.year = year; } 
	 public boolean isAfterThan( Date other ) {   
		 if( year > other.year )    
			 return true;   
		 if( year == other.year && month > other.month ) 
			 return true;   
		 if( year == other.year && month == other.month && day > other.day )   
			 return true;   
		 return false;  
	} 
	 public boolean isBeforeThan( Date other ) {   
		 if( year < other.year )    
			 return true;   
		 if( year == other.year && month < other.month ) 
			 return true;   
		 if( year == other.year && month == other.month && day < other.day )   
			 return true;   
		 return false;  
	}  
	 public boolean isEqualsWith( Date other ) {   
		 if( day == other.day && month == other.month && year == other.year )    
			 return true;   
		 return false;  
		 }  
	 public boolean gecerliMi( ) {   
		 int maxGun = 30;   
		 if( month == 2 && year % 4 == 0 ) {    
			 maxGun = 29;    
			 if( year % 100 == 0 && year % 400 != 0 )
				 maxGun = 28;   
		 }   
		 if( (month <= 7 && month % 2 == 1) || (month >= 8 && month % 2 == 0 ) )    
			 maxGun = 31;   
		 if( day >=1 && day <= maxGun && month >= 1 && month <= 12 )    
			 return true;   
		 return false;  
	} 
	 public static Date getToday() {	 
		 Date simdi=new Date (today.get(Calendar.DAY_OF_MONTH),today.get(Calendar.MONTH)+1,today.get(Calendar.YEAR));		 
		 return simdi;
	 }
	@Override
	public String toString() {
		return  day + "-" + month + "-" + year ;
	}
}
