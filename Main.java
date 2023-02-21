package numberguessing;
import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		int count=6;int min=1;int max=100;int score=100;int i;
		int generated=(int) (Math.random()*(max-min));
		System.out.println("GUESS THE NUMBER INBETWEEN 1 to 100");
		for(i=1;i<count;i++){
			System.out.println("GUESS THE NUMBER");
			while(!scanner.hasNextInt()) {
				System.err.println("please enter the number");
				scanner.next();
			}
			int usernum=scanner.nextInt();
			if(usernum>100) {
				System.err.println("please enter the number less than 100");
			}
			if(usernum==generated ){
				System.out.println(".................................");
				System.out.println("CONGRATULATIONS YOU WON THE GAME");
				System.out.println("NEXT LEVEL");
				System.out.println(".................................");
				generated=(int) (Math.random()*(max-min));
				count=6;
				i=0;
				score=score+100;
			}
			else {
				if(usernum>generated) {
					System.out.println("the generated number is less than"+" "+usernum);
					score=score-20;
				}
				else{
					System.out.println("the generated number is greater than"+" " +usernum);
					score=score-20;
				}
			}
		}
		if(i==6) {
			System.out.println("THE ACTUAL GENERATED NUMBER IS:"+" "+generated);
			System.out.println("OOPS! YOU ARE LOST THE GAME");
			System.out.println("YOUR SCORE:"+" "+score);
		}
	}
}

