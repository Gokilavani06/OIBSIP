package atminterface;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		ArrayList<Account>accounts=new ArrayList<Account>();
		ArrayList<Account.History>history=new ArrayList<Account.History>();
		Scanner scanner=new Scanner(System.in);
	 	int choice;
	 	System.out.println("PLEASE ENTER 1 TO REGISTER 2 TO LOGIN");
	 	String h=scanner.next();
		while(!checkusernum(h)) {
			System.out.println("please enter correct num");
			h=scanner.next();
		}
	    choice=Integer.parseInt(h);
	    scanner.nextLine();
	 	while(choice==1) {
	 		System.out.println();
   	    	System.out.println("PLEASE REGISTER YOUR ACCOUNT");
   	    	System.out.println();
   	    	Account account=new Account();
   	    	choice=account.registeracc(account,accounts);
  			System.out.println();
	 	}
	 	int userinput=1;
	       while(userinput ==1 || userinput ==2 || userinput ==3 || userinput==4){
	    	    System.out.println();
				System.out.println("********************USER MENU**************************");
			    System.out.println("ENTER 1 TO DEPOSITE AMOUNT");
			    System.out.println("ENTER 2 TO WITHDRAW MONEY");
			    System.out.println("ENTER 3 TO TRANSFER AMOUNT");
			    System.out.println("ENTER 4 TO VIEW TRANSACTION HISTORY");
			    System.out.println("ENTER 5 TO EXIST");
			    System.out.println("******************************************************");
			    String b=scanner.next();
			    Account a=Account.loginacc(choice,accounts);
				while(!checkmenu(b)) {
					System.out.println("please enter correct num");
					b=scanner.next();
				}
			    userinput=Integer.parseInt(b);
			    switch(userinput) {
 			    case 1:
 			 		a.deposite(accounts,history);
 			 		break;
 			    case 2:
 		 			a.withdraw(accounts,history);
 		 			break;
 		 		case 3:
 	 				a.transfer(accounts,history);
 	 				break;
 		 		case 4:
 		 			a.transaction(history);
 					 break;
 				case 5:
 					System.out.println(".................THANK YOU..............");
 					break;
 			 	}
 	       }
	}
	public static boolean checkusernum(String a) {
		if(a.equals("1") || a.equals("2") ) {
			return true;
		}
		return false;
		
	}
	public static boolean checkmenu(String a) {
		if(a.equals("1") || a.equals("2") || a.equals("3") || a.equals("4") ||  a.equals("5") ) {
			return true;
		}
		return false;
		
	}
}

