import FormatIO.*;
public class Ex2 {
	public static void main(String[] args) {

		//First create a console which prompts the user to input account number and initial deposit
		Console console = new Console("Online Banking", 5, 100);
		console.println("Welcome to the bank!\nPlease enter your account number and initial deposit:");

		//The user input is read in with StringIn and the account number and deposit are extracted.
		StringIn sin = new StringIn(console.readLine());
		int accountNumber = sin.readInt();
		double balance = sin.readDouble();

		//The account info file is created and the account number and balance stored to it.
		String fileName = "account-"+accountNumber;
		FileOut fout = new FileOut(fileName);
		fout.println(accountNumber+" "+balance);
		console.println("\nAccount file <"+fileName+"> has been written to.") ;
		fout.close();

		console.println("\nPlease enter your account number:");
		sin = new StringIn(console.readLine());
		accountNumber = sin.readInt();
		fileName = "account-"+accountNumber;
		FileIn fin = new FileIn(fileName);
		accountNumber = fin.readInt();
		balance = fin.readDouble();
		boolean isTransactionLoopComplete, isDWLoopComplete, isPaymentLoopComplete, isRSLoopComplete, isCurrencyConversionComplete;
		do {
			console.println("Your balance is at £"+balance+"\nWould you like to make a new transaction?(Y/N)");
			String answer = console.readWord();
			if (answer.compareTo("Y") == 0) {
				do {
					console.println("Would you like to make a deposit or withdrawal? (D/W)");
					String answerDW = console.readWord();
					if (answerDW.compareTo("D") == 0) {
						console.println("How much would you like to deposit?");
						double deposit = console.readDouble();
						balance += deposit;
						console.println("You're balance has been updated.\nBalance is now: "+balance);
						fout = new FileOut(fileName);
						fout.println(accountNumber+" "+balance);
						fout.close();
						isDWLoopComplete = true;
					}
					else if (answerDW.compareTo("W") == 0) {
						console.println("How much would you like to Withdraw?");
						double withdrawal = console.readDouble();
						if (balance < withdrawal) {
							console.println("Insufficient funds available.");
						}
						else {
						balance -= withdrawal;
						console.println("You're balance has been updated.\nBalance is now: "+balance);
						fout = new FileOut(fileName);
						fout.println(accountNumber+" "+balance);
						fout.close();
						}
						isDWLoopComplete = true;
					}
					else {
						console.println("Please enter a valid input\n");
						isDWLoopComplete = false;
					}
				}while (isDWLoopComplete == false);
				isTransactionLoopComplete = true;
			}
			else if (answer.compareTo("N") == 0){
				isTransactionLoopComplete = true;
			}
			else {
				console.println("You have not entered a valid input");
				isTransactionLoopComplete = false;
			}
		} while (isTransactionLoopComplete == false);
	do {
		console.println("Would you like to make a payment?(Y/N)");
		String answerPay = console.readWord();
		if(answerPay.compareTo("Y") == 0) {
			console.println("Please enter the account number and initial balance of the account you wish to pay into:");
			sin = new StringIn(console.readLine());
			int accountNumber2 = sin.readInt();
			double balance2 = sin.readDouble();
			String fileName2 = "account-"+accountNumber2;
			FileOut fout2 = new FileOut(fileName2);
			fout2.println(accountNumber2+" "+balance2);
			do {
				console.println("Do you wish to make a single payment or repeating?(S/R)");
				String answerSR = console.readWord();
				if (answerSR.compareTo("S") == 0) {
					console.println("Please enter the amount you wish to pay from Account <"+accountNumber+"> to Account <"+accountNumber2+">:");
					double payment = console.readDouble();
					balance -=payment;
					balance2 += payment;
					console.println("The balances of each account have been updated.\nAccount<"+accountNumber+"> has a balance of "+balance+" and Account <"+accountNumber2+"> has a balance of "+balance2);
					fout = new FileOut(fileName);
					fout.println(accountNumber+" "+balance);
					fout2 = new FileOut(fileName2);
					fout2.println(accountNumber2+" "+balance2);
					isRSLoopComplete = true;
				}
				else if (answerSR.compareTo("R") == 0) {
					console.println("Please enter the amount you wish to pay from Account <"+accountNumber+"> to Account <"+accountNumber2+">:");
					double payment = console.readDouble();
					console.println("How many repeats of this payment should be made?");
					int repeats = console.readInt();
					balance -= (payment*repeats);
					balance2 += (payment*repeats);
					console.println("The balances of each account have been updated.\nAccount<"+accountNumber+"> has a balance of "+balance+" and Account <"+accountNumber2+"> has a balance of "+balance2);
					fout = new FileOut(fileName);
					fout.println(accountNumber+" "+balance);
					fout2 = new FileOut(fileName2);
					fout2.println(accountNumber2+" "+balance2);
					isRSLoopComplete = true;
				}
				else {
					console.println("Please enter a valid input.\n");
					isRSLoopComplete = false;
				}
			} while (isRSLoopComplete == false);
			isPaymentLoopComplete =true;
		}
		else if (answerPay.compareTo("N") == 0) {
			isPaymentLoopComplete = true;
		}
		else {
			console.println("Please enter a valid input.\n");
			isPaymentLoopComplete = false;
		}
	}while (isPaymentLoopComplete == false);
	do {
		isCurrencyConversionComplete = true;
	} while (isCurrencyConversionComplete == false);
	console.println("Thank you for using Online Banking!");
	}

}
