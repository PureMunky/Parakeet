package my.parakeet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;

public class MoneyEntry {

	public double Amount;
	public String Description;
	public Date TimeStamp;
	public String OccMonth;
	public String OccDay;
	public String OccYear;
	
	public MoneyEntry(double inAmount) {
		FillValues(inAmount, "", new Date(), "xx", "xx", "xx");
	}
	public MoneyEntry(double inAmount, String inDesc) {
		FillValues(inAmount, inDesc, new Date(), "xx", "xx", "xx");
	}
	public MoneyEntry(double inAmount, String inDesc, Date inTime) {
		FillValues(inAmount, inDesc, inTime, "xx", "xx", "xx");
	}
	public MoneyEntry(double inAmount, String inDesc, Date inTime, String inMonth, String inDay, String inYear) {
		FillValues(inAmount, inDesc, inTime, inMonth, inDay, inYear);
	}
	
	public void FillValues(double inAmount, String inDesc, Date inTime, String inMonth, String inDay, String inYear) {
		Amount = inAmount;
		Description = inDesc;
		TimeStamp = inTime;
		OccMonth = inMonth;
		OccDay = inDay;
		OccYear = inYear;
	}
	
	public Date getOccDate() {
		int m;
		int d;
		int y;
		
		String StrMonth = String.valueOf(new Date().getMonth());
		String StrDay = String.valueOf(new Date().getDate());
		String StrYear = String.valueOf(new Date().getYear());
		
		if(StrMonth.length() == 1) {
			StrMonth = "0" + StrMonth;
		}
		if(StrDay.length() == 1) {
			StrDay = "0" + StrDay;
		}
		if(StrYear.length() == 1) {
			StrYear = "0" + StrYear;
		}
		
		if(!this.OccMonth.equalsIgnoreCase("00")) {
			m = new Date().getMonth();
		}else{
			m = TimeStamp.getMonth();
		}
		
		if(this.OccDay.equalsIgnoreCase("00")) {
			d = new Date().getDate();
		}else{
			d = TimeStamp.getDate();
		}
		
		if(!this.OccYear.equalsIgnoreCase("00")) {
			y = new Date().getYear();
		}else{
			y = TimeStamp.getYear();
		}
		
		//Date nd = new Date();
		
		return new Date(y, m, d);
	}
	
	public String ToXML() {
		String rtnStr = "";
		
		rtnStr = "<money amount=\"" + this.Amount + "\" desc=\"" + this.Description + "\" time_stamp=\"" + new SimpleDateFormat("MM/dd/yyyy h:mm:ss a").format(this.TimeStamp) + "\" occ_day=\"" + this.OccDay + "\" occ_month=\"" + this.OccMonth + "\" occ_year=\"" + this.OccYear + "\"></money>";

		return rtnStr;
	}
	public String ToString() {
		String rtnStr = "";
		rtnStr = new java.text.DecimalFormat("$0.00").format(this.Amount);
		if(this.Description.length() > 0){
			rtnStr += " - " + this.Description;
		}
		
		DateFormat shortDate = new SimpleDateFormat("MM-dd-yyyy");
		if(this.TimeStamp.getDate() == new Date().getDate() && this.TimeStamp.getYear() == new Date().getYear()){
			shortDate = new SimpleDateFormat("h:mm a");
		}
		
		rtnStr += IIF(rtnStr.length() > 0, " - ", "") + shortDate.format(this.TimeStamp);
		
		
		//rtnStr = this.ToXML() + " - " + this.getOccDate().toLocaleString();
		return rtnStr;
	}
	
	public String IIF(boolean Condition, String TrueReturn, String FalseReturn){
		String rtnStr = "";
		
		if(Condition){
			rtnStr = TrueReturn;
		}else{
			rtnStr = FalseReturn;
		}
		
		return rtnStr;
	}
}
