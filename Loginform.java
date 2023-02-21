package onlinereservation;
import java.util.*;
public class Loginform {
    Scanner scanner = new Scanner(System.in);
	private String loginid;
	private String password;
	public Loginform(){
			System.out.println("Enter the user name must in atleast 5 character ");
			String a=scanner.next();
			while(!valied(a)) {
				System.out.println("password atleast 5 characters ");
				a=scanner.next();
			}
			loginid=a;
			System.out.println("Enter the password must in atleast 5 character");
			String b=scanner.next();
			while(!valied(b)) {
				System.out.println("password atleast 5 characters ");
				b=scanner.next();
			}
			password=b;

	}
	public String getloginid() {
		return loginid;
	}
	public String getpassword() {
		return password;
	}
	public boolean valied(String n) {
		if(n.length()>=5) {
			return true;
		}
		return false;
	}
	public boolean isregister(ArrayList<Loginform>loginforms) {
		if(loginforms.size()==0) {
			System.out.println("          ******SUCCESSFULLY REGISTERED******        ");
			return true;
		}
		else {
			for(Loginform a:loginforms) {
				if(loginid.equals(a.getloginid())) {
					System.out.println("the login id is already exist please login or try another one");
					return false;
				}
			}
			System.out.println("          ******SUCCESSFULLY REGISTERED******        ");
			return true;
		}
	}
	
}
	


