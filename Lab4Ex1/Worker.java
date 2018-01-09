import java.util.*;
import FormatIO.*;

public class Worker {
	public int quantity;
	//public ArrayList<String> names;
	public double costTotal;
	public double delivery = 4.50;

	public void getInput(Console console) {

		console.println("Please enter the number of items you wish to buy:");
		StringIn sinQuantity = new StringIn(console.readWord());
		quantity = sinQuantity.readInt();
		int counter=0;
		
		while (counter < quantity){
			console.println("Please enter the name, cost and quantity of each item.\nPlease write in order and separate with a space. ");
			StringIn sin = new StringIn(console.readLine());
			String name = sin.readWord();
			double cost = sin.readDouble();
			int orders = sin.readInt();
			//names.add(0, name);
			costTotal = costTotal + cost;
			counter = counter + orders;
			if (counter > quantity) {
				console.println("Error: you have described too many items, please start again \n");
				counter = 0;}
			else if (counter < quantity)
				console.println("You have "+ (quantity - counter) + " items unaccounted for.\n");
			else
				costTotal = costTotal + delivery;
				console.println("Thank you, all items are accounted for. Your total cost is £" + costTotal + "\n" );
		} 
	}

	public void getCost(Console console) {
		int costCoins = (int) (costTotal*100);
		if (costCoins % 3 == 0 && quantity % 3 == 0 ) { //All Equal
			int coinsEach = costCoins/3;
			int quanEach = quantity/3;
			double costEach = ((double) coinsEach)/100;
			console.println("Each user will get " + quanEach + " items, and owes exactly £" +costEach );
		}
		else if (costCoins % 3 != 0 && quantity % 3 == 0 ) { //Quantity Equal cost unequal  
			int quanEach = quantity/3;
		    int coins23 = costCoins / 3;
		    	double cost23 = ((double) coins23)/100;
		    int coins1  = coins23 + costCoins % 3;
		    	double cost1 = ((double) coins1)/100;
		    console.println("Each user will get " + quanEach + " items, User 1 owes £"+cost1+" and Users 2 and 3 owe £"+cost23+" each." );
			}
		
		else if (costCoins % 3 == 0 && quantity % 3 != 0 ) { //Cost Equal Quantity not 
			int coinsEach = costCoins/3;
				double costEach = ((double) coinsEach)/100;
			int quan23 = quantity / 3;
			int quan1 = quan23 + quantity % 3;
			console.println("User 1 will get " + quan1 + " items, while Users 2 and 3 will each get " + quan23 + " items. Each User owes exactly £" +costEach );
		}
		else {
		    int coins23 = costCoins / 3;
		    	double cost23 = ((double) coins23)/100;
		    int coins1  = coins23 + costCoins % 3;
		    	double cost1 = ((double) coins1)/100;
		   	int quan23 = quantity / 3;
			int quan1 = quan23 + quantity % 3;
			console.println("User 1 will get " + quan1 + " items, while Users 2 and 3 will each get " + quan23 + " items. User 1 owes £ "+cost1+" and Users 2 and 3 owe £"+cost23+" each." );
		    
		}
	}
}
