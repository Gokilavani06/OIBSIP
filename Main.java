package onlinereservation;
import java.text.ParseException;
import java.util.*;
public class Main {
	public static void main(String[] args) throws ParseException{
		    try (Scanner scanner = new Scanner(System.in)) {
				ArrayList<Loginform> loginforms=new ArrayList<Loginform>();
				ArrayList<Reservationform>reservationforms=new ArrayList<Reservationform>();
				reservationforms.add(new Reservationform(12679,"coimbatore exp"));
				reservationforms.add(new Reservationform(16669,"yercaud exp"));
				reservationforms.add(new Reservationform(16627,"west coast exp"));
				String[]coimbatore_exp=new String[]{"Hosur","Dharmapuri","omalur","salem junction","Erode junction"};
				String[]yercaud_exp=new String[]{"chennai central","perambur","trivellore","arakkonam","ambur"};
				String[]west_cost_exp=new String[]{"chennai central","Arakonam","katpadi junction","jolarpettai","morappur"};            
				ArrayList<Traindetail>traindetails=new ArrayList<Traindetail>();
				traindetails.add(new Traindetail(12679,"coimbatore exp",30,"first class AC",coimbatore_exp));
				traindetails.add(new Traindetail(12679,"coimbatore exp",4,"second class",coimbatore_exp));
				traindetails.add(new Traindetail(16669,"yercaud exp",30,"first class AC",yercaud_exp));
				traindetails.add(new Traindetail(16669,"yercaud exp",40,"second class",yercaud_exp));
				traindetails.add(new Traindetail(16627,"west cost exp",50,"first class AC",west_cost_exp));
				traindetails.add(new Traindetail(16627,"west cost exp",60,"second class",west_cost_exp));
				ArrayList<Booking>bookings=new ArrayList<Booking>();
				int userinput=1;
				while(userinput==1 || userinput==2 || userinput==3) {
					System.out.println("       MENU           ");
					System.out.println(".....................................");
					System.out.println("ENTER 1 TO REGISTER");
					System.out.println("ENTER 2 TO LOGIN");
					System.out.println("ENTER 3 TO CANCEL RESERVATIO");
					System.out.println("ENTER 4 TO EXIST");
					System.out.println(".....................................");
					String h=scanner.next();
					while(!checkusernum(h)) {
						System.out.println("please enter correct num");
						h=scanner.next();
					}
					userinput=Integer.parseInt(h);
					scanner.nextLine();
					if(userinput==1) {
						System.out.println();
						System.out.println("PLEASE REGISTER YOUR ACCOUNT");
						System.out.println();
						Loginform loginform=new Loginform();
						if(loginform.isregister(loginforms)) {
							loginforms.add(loginform);
						}
					}
					if(userinput==1 || userinput==2) {
						System.out.println();
						System.out.println("PLEASE LOGIN YOUR ACCOUNT");
						System.out.println();
						if(isavailable(loginforms)) {
							System.out.println();
							System.out.println(".....................................");
							System.out.println("LIST OF TRAINS");
							System.out.println(".....................................");
							for(Reservationform a:reservationforms) {
								a.displayinfo();
							}
							System.out.println(".....................................");
							System.out.println();
							Booking booking=new Booking(traindetails,reservationforms);
							if(booking.isavailable(traindetails,bookings)) {
								bookings.add(booking);
								System.out.println("YOUR PNR NO="+booking.getpnr());
								System.out.println("Train number :"+ booking.getnumber() +", Class type:"+ booking.getclasstype() +", No of seats:"+ booking.getseatno() +", SUCESSFULLY CONFORMED");
								System.out.println("<<<<<<<<THANK YOU>>>>>>>>>>>>>");
							}
							else {
								System.out.println("sorry the train is full");
							}
						}
					}
					if(userinput==3) {
						int i;
						System.out.println("please enter the PNR numnber:");
						int pnr=scanner.nextInt();
						for(int j=0;j<bookings.size();j++) {
							Booking k=bookings.get(j);
							if(pnr==k.getpnr()) {
								bookings.remove(j);
								System.out.println("Train number :"+ k.getnumber() +", Class type:"+ k.getclasstype() +", No of seats:"+ k.getseatno() +", SUCESSFULLY CANCELLED");
							}
						}
					}
				}
		    }
	}

	private static boolean isavailable(ArrayList<Loginform> loginforms) {
			Scanner s = new Scanner(System.in);
			System.out.println("ENTER USER USERNAME");
			String id=s.nextLine();
			System.out.println("ENTER YOUR PASSWORD");
			String p=s.nextLine();
				for(Loginform a:loginforms) {
					if(id.equals(a.getloginid()) && p.equals(a.getpassword())) {
						System.out.println("          ******SUCCESSFULLY LOGIN*******        ");
						return true;
					}
				}
				System.out.println("please enter the correct details");
				return false;
	}
	public static boolean checkusernum(String a) {
		if(a.equals("1") || a.equals("2") || a.equals("3") ||a.equals("4")) {
			return true;
		}
		return false;
		
	}
}
