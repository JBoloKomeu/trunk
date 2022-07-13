package Classes;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.TimeZone;

public class DataHora {

	public static void main(String[] args) throws ParseException {
		dateFormat();
		dateFormatFromString();
		calendarFormat();
		timestamp();
		retornaDataHojeString();
	}
	
	public static void retornaDataHojeString() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        String hoje = zonedDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("zonedDateTime: " + hoje);
        
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataF = LocalDate.parse(hoje, formatter1);
        System.out.println("DateTimeFormatter - yyyy-MM-dd: " + dataF);
        
        String data1 = dataF.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("DateTimeFormatter - dd/MM/yyyy: " + data1);
   }
	
	private static void timestamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String date = new SimpleDateFormat("dd/MM/yyyy").format(timestamp.getTime());
		System.out.println("Timestamp to String date: " + date);

		Date d = new Date(timestamp.getTime());
		System.out.println("Timestamp to Java Util Date: " + d);
		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss"); 
		Date data = df.parse("11-11-2019 17:48:00", new ParsePosition(0));
		Timestamp ts = new Timestamp(data.getTime());
		System.out.println("Java Util Date to Timestamp: " + ts);
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 11); 
		cal.set(Calendar.MONTH, 11); 
		cal.set(Calendar.YEAR, 2019); 
		Timestamp tstamp = new Timestamp(cal.getTimeInMillis());
		System.out.println("GregorianCalendar to Timestamp: " + tstamp);
	}

	private static void calendarFormat() {
		/**
		 * utilizando a classe Calendar
		 */
		Calendar c = Calendar.getInstance();

		System.out.println("Data e Hora atual: " + c.getTime());

		/*
		 * pega a data atual do sistema
		 */
		c.setTime(new Date()); 
		int diaAtual = c.get(Calendar.DAY_OF_MONTH); // DIA
		int mesAtual = c.get(Calendar.MONTH); // MES
		int anoAtual = c.get(Calendar.YEAR); // ANO

		System.out.println(" 	DIA: " + diaAtual);
		System.out.println(" 	MÊS: " + mesAtual);
		System.out.println(" 	ANO: " + anoAtual);
	}

	public static void dateFormat() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
		
		Date date = new Date();
		java.sql.Date dt = new java.sql.Date(date.getTime());
		
		System.out.println("SQL Date - data atual: " + dt);
		System.out.println("SQL Date - data e hora atual: " + dateFormat.format(dt.getTime()));
		System.out.println("SQL Date - data atual: " + dateFormat1.format(dt.getTime()));
		System.out.println("SQL Date - hora atual: " + dateFormat2.format(dt.getTime()));
		System.out.println("Date - Data e Hora atual: " + dateFormat.format(date));

		// Hour in day (0-23):
		DateFormat df0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Hour in day (1-24):
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		// Hour in am/pm (0-11):
		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss");
		// Hour in am/pm (1-12):
		DateFormat df3 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		System.out.println("\nHour in day (0-23): " + df0.format(dt.getTime()));
		System.out.println("Hour in day (1-24): " + df1.format(dt.getTime()));
		System.out.println("Hour in am/pm (0-11): " + df2.format(dt.getTime()));
		System.out.println("Hour in am/pm (1-12): " + df3.format(dt.getTime()));
	}
	
	public static void dateFormatFromString() throws ParseException {
		
		DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
		
		Date date = new Date();
		/**
		 * recebe String do teclado e converte para SQL Date/Time
		 */
		Scanner leitor = new Scanner(System.in);
		System.out.print("Data atual - dd/MM/yyyy: ");
		System.out.println("Util Date - data atual: " + dateFormat1.format(date.parse(leitor.next())));
		
		System.out.print("Hora atual - HH:mm:ss: ");
		Date time = dateFormat2.parse(leitor.next());
		System.out.println("Util Date - hora atual: " + time );
		
	    java.sql.Time ppstime = new java.sql.Time(date.getTime());
		System.out.println("Sql Time - hora atual: " + ppstime );
	    
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		System.out.println("util Date: " + date);
		System.out.println("sql Date: " + sqlDate);
	}
}