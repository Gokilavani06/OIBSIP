package atminterface;
import java.text.*;
import java.util.*;
public class Account {
	final Scanner scanner = new Scanner(System.in);
	private long accountno;
	private long loginid;
	private int password;
	private float amount;
    Account() {
		System.out.println("Enter the account number");
		while(!scanner.hasNextLong()) {
			System.out.println("please enter the correct account no");
			scanner.next();
		}
		accountno=scanner.nextLong ();
		scanner.nextLine();
	    System.out.println("Enter the 16 digit ATM card number");
	    String a=scanner.next();
	    while(!valied(a)) {
	 	    	System.out.println("please enter 16 digit number");
	 	    	a=scanner.next();
	    }
		loginid=Long.parseLong(a);
		scanner.nextLine();
		System.out.println("Enter the 4 digit pin number");
		String b=scanner.next();
	    while(!valiedpin(b)) {
	 	    	System.out.println("please enter 4 digit pin number");
	 	    	b=scanner.next();
	    }
	    password=Integer.parseInt(b);
	    scanner.nextLine();
	    amount=0;
    }
    public long getaccountno() {return accountno;}
	public long getloginid() {return loginid;}
	public int getpassword() {return password;}
	public void setaccountno(long accountno) {this.accountno=accountno;}
	public void setloginid(long loginid) {this.loginid=loginid;}
	public void setpassword(int password) {this.password=password;}
	public static boolean valied(String n) {
		if(n.length()==16) {
			try {
				Long.parseLong(n);
				return true;
			}
			catch(NumberFormatException e) {
				return false;
			}
		}
		return false;
	}
	public static boolean valiedpin(String n) {
		if(n.length()==4) {
			try {
				Integer.parseInt(n);
				return true;
			}
			catch(NumberFormatException e) {
				return false;
			}
		}
		return false;
	}
	public int registeracc(Account account,ArrayList<Account>accounts) {
		if(accounts.size()==0) {
			accounts.add(account);
			System.out.println("............SUCCESSFULLY REGISTERED...........");
			return 2;
		}
		else {
			for(Account a:accounts) {
				if(accountno!=a.accountno && loginid!=a.loginid ) {
					accounts.add(account);
					System.out.println("............SUCCESSFULLY REGISTERED.......");
					return 2;
				}
			}
			System.out.println("This account is already registered please login");
			return 2;
		}
	}
	public static Account loginacc(int choice,ArrayList<Account>accounts) {
		 while(choice==2){
			 Scanner scanner=new Scanner(System.in);
		    	System.out.println();
		    	System.out.println("PLEASE LOGIN YOUR ACCOUNT");
		    	System.out.println();
		    	System.out.println("Enter the 16 digit ATM card number");
			    String c=scanner.next();
			    while(!valied(c)) {
			 	    	System.out.println("please enter 16 digit number");
			 	    	c=scanner.next();
			    }
				Long loginid=Long.parseLong(c);
				System.out.println("Enter the 4 digit pin number");
				String b=scanner.next();
			    while(!valiedpin(b)) {
			 	    	System.out.println("please enter 16 digit number");
			 	    	b=scanner.next();
			    }
			    int password=Integer.parseInt(b);
	 	        for(Account a:accounts) { 
	 	        	if(loginid==a.getloginid() && password==a.getpassword() ) {
					System.out.println("............SUCCESSFULLY LOGIN...........");
					return a;
				    }
	            }
	 	        System.out.println("plese enter correct details");
	 	        choice=2;
		 }
		 return null;
	}
	public void deposite(ArrayList<Account>accounts,ArrayList<Account.History>history) {
		System.out.println("please enter the amount to deposite");
		while(!scanner.hasNextInt()) {
			System.out.println("plese enter the amount");
			scanner.next();
		}
	    int deposite=scanner.nextInt();
	    for(Account b:accounts) {
	    	if(accountno==b.accountno) {
	    		amount=amount+deposite;
	    		System.out.println("total balance="+amount);
	    		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date date = new Date(); 
				String d=formatter.format(date);
	    		history.add(new History(accountno,d,"    deposte    ",deposite));
	    	}
	    }
	    
	}
	public void withdraw(ArrayList<Account>accounts,ArrayList<Account.History>history) {
		System.out.println("please enter the amount withdraw");
		while(!scanner.hasNextInt()) {
			System.out.println("plese enter the amount");
			scanner.next();
		}
	    int withdraw=scanner.nextInt();
	    for(Account b:accounts) {
	    	if(accountno==b.accountno) {
	    		if(amount>withdraw) {
	    			amount=amount-withdraw;
		    		System.out.println("total balance="+amount);
		    		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					Date date = new Date(); 
					String d=formatter.format(date);
		    		history.add(new History(accountno,d,"   withdraw   ",withdraw));
	    		}
	    		else {
	    			System.out.println("insufficient balance");
	    		}
	    		
	    	}
	    }
	    
	}
	public void transfer(ArrayList<Account>accounts,ArrayList<Account.History>history) {
		System.out.println("Enter the account number to transfer money");
		while(!scanner.hasNextLong()) {
			System.out.println("please enter the correct account no");
			scanner.next();
		}
		long no=scanner.nextLong();
		System.out.println("Enter the amount to transfer");
		while(!scanner.hasNextInt()) {
			System.out.println("plese enter the amount");
			scanner.next();
		}
		int trans=scanner.nextInt();
		 for(Account b:accounts) {
		    	if(accountno==b.accountno) {
		    		if(amount>trans) {
		    			amount=amount-trans;
			    		System.out.println("total balance="+amount);
			    		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
						Date date = new Date(); 
						String d=formatter.format(date);
						history.add(new History(accountno,d,"     transfer     ",trans));
						for(Account c:accounts) {
					    	if(trans==c.accountno) {
					    		c.deposite(accounts, history);
					    	}
						}
		    		}
		    		else {
		    			System.out.println("insufficient balance");
		    		}
		    	}
		 }
		 
	}
	public void transaction(ArrayList<Account.History>history) {
				for(History b1:history) {
					if(accountno==b1.no) {
						System.out.println(b1.d +"       "+b1.detail+"      "+ b1.money);
					}
	             }
	}
	public class History{
    	final Scanner scanner = new Scanner(System.in);
    	private long no;
		private String d; 
		private String detail;
		private float money;
		History(long no,String d,String detail,float money){
			this.no=no;
			this.d=d;
			this.detail=detail;
			this.money=money;
		}
		 public long getno() {return no;}
		 public String getd() {return d;}
		 public String getdetails() {return detail;}
		 public float getmoney() {return money;}
		 public void setno(long no) {this.no=no;;}
		 public void setd(String d){this.d=d;}
		 public void setdetail(String detail) {this.detail=detail;}
		 public void setmoney(float money) {this.money=money;}
	}
	
	
}

