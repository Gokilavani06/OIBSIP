package onlinereservation;
import java.util.*;
import java.lang.reflect.Array;
import java.text.*;
public class Reservationform {
	final Scanner scanner = new Scanner(System.in);
	private int train_number;
	private String train_name;
	public Reservationform(int train_number,String train_name){
		this.train_number=train_number;
		this.train_name=train_name;
	}
	public Reservationform(ArrayList<Reservationform>reservationforms){
		System.out.println("enter the train number");
		String a=scanner.next();
		while(!iscorrectnumber(reservationforms,a)){
			System.out.println("please enter correct train number");
			a=scanner.next();
			}
		train_number=Integer.parseInt(a);
		scanner.nextLine();
		System.out.println("enter the train name");
		String b=scanner.nextLine();
		while(!iscorrectname(reservationforms,b,train_number)) {
			System.out.println("please enter correct train name");
			b=scanner.nextLine();
		}
		train_name=b;
	}
	public void setnumber(int train_number) {
		this.train_number=train_number;
	}
	public int getnumber() {
		return train_number;
	}
	public void setname(String train_name) {
		this.train_name=train_name;
	}
	public String getname() {
		return train_name;
	}
	public void displayinfo() {
		System.out.println(train_number+"-"+train_name);
	}
	public static boolean iscorrectnumber(ArrayList<Reservationform>reservationforms,String n) {
		for(Reservationform a:reservationforms) {
			if(n.equals(String.valueOf(a.getnumber()))) {
				return true;
			}
		}
		return false;
	}
	public static boolean iscorrectname(ArrayList<Reservationform>reservationforms,String n,int train_number) {
		for(Reservationform a:reservationforms) {
			if(train_number==a.getnumber()) {
				if(a.getname().equalsIgnoreCase(n)) {
					return true;
				}
			}
		}
		return false;
	}
} 
class Traindetail extends Reservationform{
	 private int capacity;
	 private String class_type;
	 private String[]station;
	Traindetail(int train_number,String train_name,int capacity,String class_type,String[]station){
		super(train_number,train_name);
		this.capacity=capacity;
		this.class_type=class_type;
		this.station=station;	
	}
	Traindetail(ArrayList<Traindetail>traindetails,ArrayList<Reservationform>reservationforms){
		super(reservationforms);
		System.out.println("enter the number of seats");
		while(!scanner.hasNextInt()) {
			System.out.println("please enter number of seats in integera");
			scanner.next();
		}
		capacity=scanner.nextInt();
		scanner.nextLine();
		System.out.println(".....................................");
		System.out.println("AVAILABLE CLASSES IN THE TRAIN NO"+getnumber());
		System.out.println(".....................................");
		displayclasstype(traindetails,getnumber());
		System.out.println(".....................................");
		System.out.println();
		System.out.println("enter the class type");
		while(!iscorrectclass(traindetails,class_type=scanner.nextLine(),getnumber())) {
			System.out.println("please enter correct class type");
		}
	}
	public void setcapacity(int capacity) {
		this.capacity=capacity;
	}
	public int getcapacity() {
		return capacity;
	}
	public void setclasstype(String class_type) {
		this.class_type=class_type;
	}
	public String getclasstype() {
		return class_type;
	}
	public void setstationlist(String[]station) {
		this.station=station;	
	}
	public String[]getstationlist() {
		return station;
	}
	public void displayclasstype(ArrayList<Traindetail>traindetails,int no) {
		for(Traindetail a:traindetails) {
			if(no==a.getnumber()) {
				System.out.println(a.getclasstype());
			}
		}
	}
	public static boolean iscorrectclass(ArrayList<Traindetail>traindetails,String n,int train_number) {
		for(Traindetail a:traindetails) {
			if(train_number==a.getnumber()) {
				if(a.getclasstype().equalsIgnoreCase(n)) {
					return true;
				}
			}
		}
		return false;
	}
}
class Booking extends Traindetail {
	private int from;
	private int to;
	private Date dateinput;
	private int uniquekey;
	private ArrayList<Integer>seatno;
	Booking(ArrayList<Traindetail>traindetails,ArrayList<Reservationform>reservationforms) throws ParseException{
		super(traindetails,reservationforms);
		System.out.println(".....................................");
		System.out.println("LIST OF STATIONS");
		System.out.println(".....................................");
		displaystations(traindetails,getnumber(),getclasstype());
		System.out.println(".....................................");
		System.out.println();
		System.out.println("enter the distination from");
		String a=scanner.next();
		while(!correctfrom(traindetails,a)) {
			System.out.println("please enter the correct from destination");
			a=scanner.next();
		}
		from=Integer.parseInt(a);
		System.out.println("enter the distination to");
		String b=scanner.next();
		while(!correctto(traindetails,b)) {
			System.out.println("please enter the correct from destination");
			b=scanner.next();
		}
		to=Integer.parseInt(b);
		scanner.nextLine();
		System.out.println("enter the date of journey dd/MM/yyyy");
		String date=scanner.nextLine();
		while(!isvalieddate(date)) {
			System.out.println("please enter correct date formate dd/MM/yyyy");
			date=scanner.nextLine();
		}
		dateinput=new SimpleDateFormat("dd/MM/yyyy").parse(date);
		Random random=new Random();
		uniquekey=random.nextInt(1000000000)+1000000000;
		seatno=new ArrayList<Integer>();
	}
	public void setfrom(int from) {
		this.from=from;
	}
	public int getfrom() {
		return from;
    }
	public void setto(int to) {
		this.to=to;
	}
	public int getto() {
		return to;
    }
	public void setdate(Date dateinput) {
		this.dateinput=dateinput;
	}
	public Date getdateinput() {
		return dateinput;
    }
	
	public void setpnr(int uniquekey) {
		this.uniquekey=uniquekey;
	}
	public int getpnr() {
		return uniquekey;
	}
	public void setseatno(ArrayList<Integer> seatno) {
		this.seatno=seatno;
	}
	public ArrayList<Integer> getseatno() {
		return seatno;
	}
	public void displaystations(ArrayList<Traindetail>traindetails,int no,String type) {
		String[] s;
		for(Traindetail a:traindetails) {
			if(no==a.getnumber()&& type.equalsIgnoreCase(a.getclasstype())) {
				s=a.getstationlist();
				for(int i=0;i<s.length;i++) {
					int j=i+1;
					System.out.println("enter "+j+" to:"+s[i]);
				}
			}
		}
	}
	public boolean correctfrom(ArrayList<Traindetail>traindetails,String no) {
		for(Traindetail a:traindetails) {
			if(getnumber()==a.getnumber()&& getclasstype().equalsIgnoreCase(a.getclasstype())) {
				String[] s=a.getstationlist();
				for(int i=0;i<(s.length-1);i++) {
					if(no.equalsIgnoreCase(String.valueOf(i+1))) {
						return true;
					}
				}
				
			}
		}
		return false;
	}
	public boolean correctto(ArrayList<Traindetail>traindetails,String no) {
		for(Traindetail a:traindetails) {
			if(getnumber()==a.getnumber()&& getclasstype().equalsIgnoreCase(a.getclasstype())) {
				String[] s=a.getstationlist();
				for(int i=1;i<(s.length);i++) {
					if(no.equalsIgnoreCase(String.valueOf(i+1))) {
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean isvalieddate(String date) {
		SimpleDateFormat s=new SimpleDateFormat("dd/MM/yyy");
		s.setLenient(false);
		try {
			s.parse(date);
			return true;
			
		}
		catch(ParseException e) {
			return false;
		}
		
	}
	public boolean seatisavailable(ArrayList<Booking>bookings,int cap,int currentseat) throws ArrayIndexOutOfBoundsException {
		for(Booking b:bookings) {
			if(getnumber()==b.getnumber() && getclasstype().equalsIgnoreCase(b.getclasstype()) && getdateinput().equals(b.getdateinput())){
				for(Integer a:b.seatno) {
					if(a==currentseat) {
			    		if((from>=b.to && to>b.to) || (from<b.from&&to<=b.from) || (from!=b.from&&to!=b.to)) {
               
			    		}
			    		else {
			    			return false;
			    		}
					}
				}
			}
		}
		return true;				
	}
	
	public boolean isavailable(ArrayList<Traindetail>traindetails,ArrayList<Booking>bookings) {
		int cap=0;int maxseat=0;int currentseat=1;
			for(Traindetail a:traindetails) {
				if(getnumber()==a.getnumber()&& getclasstype().equalsIgnoreCase(a.getclasstype())) {
					cap=a.getcapacity();
				}
			}
			for(int i=0;i<getcapacity();i++) {
				if(bookings.size()!=0) {
					for(Booking b:bookings) {
						if(getnumber()==b.getnumber() && getclasstype().equalsIgnoreCase(b.getclasstype()) && getdateinput().equals(b.getdateinput())){
							for(Integer c:b.seatno) {
								if(c>maxseat) {
									maxseat=c;
								}
								if(currentseat==c) {
									if(seatisavailable(bookings,cap,currentseat)) {
										seatno.add(currentseat);
										
									}
									else {
										currentseat++;
									}
								}
							}
						}
					}
					currentseat++;
				}
				
				else {
					seatno.add(maxseat+1);
					maxseat++;
				}
			}
			if(seatno.size()==getcapacity()) {
				System.out.println("your seat no is.......");
				for(int a:seatno) {
					System.out.println(a);
				}
				return true;
			}
			else {
				int s=Math.abs(seatno.size()-getcapacity());
				for(int i=0;i<s;i++) {
					if(maxseat<cap) {
						seatno.add(maxseat+1);
						maxseat=maxseat+1;
					}
					else {
						System.out.println("the available seats are : "+seatno.size());
						return false;
					}
				}
				System.out.println("your seat no is");
				for(int a:seatno) {
					System.out.println(a);
				}
				return true;
			}
	}
}