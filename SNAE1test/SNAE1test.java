/** The purpose of this is just to do a HLL test of
 *  the code I'll have to make for S+N assessed exercise.
 *  Makes it easier eh.
 */
public class SNAE1test {


	public static void main(String[] args) {
		//Declare variables

		/*
		n=12;
		X = new int[n]; //This just looks like this for the sake of carrying across to Sig16
		X[0] = 3;
		X[1] =-6;
		X[2] = 27;
		X[3] =101;
		X[4] = 50;
		X[5] =  0;
		X[6] =-20;
		X[7] =-21;
		X[8] = 19;
		X[9] =  6;
		X[10]=  4;
		X[11]=-10;
		 */

		int possum=0; //SUM
		int negcount=0; //COUNT NEGATIVES
		int oddcount=0; //COUNT ODDS
		int n=12;
		int[] X = {3, -6, 27, 101, 50, 0, -20, -21, 19, 6, 4, -10};
		for(int i=0;i<n;i++) {
			if (X[i] >= 0) {
				possum+=X[i];
				if(X[i] % 2 != 0) {
					oddcount++;
				}
			}
			else {
				negcount++;
			}

		}
		System.out.println("SUM = "+possum+" - NEGATIVES = "+negcount+" - ODDS = "+oddcount);	
	}
}
