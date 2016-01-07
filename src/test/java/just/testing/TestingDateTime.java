package just.testing;

import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class TestingDateTime {

	public static void main(String[] args) {
		Date date = new Date();
		DateTime datatime = new DateTime();
		DateTime datatime1 = DateTime.now(DateTimeZone.UTC);
		
		System.out.println("Date and time:" + date);
	}
	
}
