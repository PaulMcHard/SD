import java.sql.*;
import FormatIO.*;
import java.util.*;

public class DatabaseReadTest {
	
	public static int[] idList = null;
	public static ArrayList<String> nameList = new ArrayList<String>();
	public static int[] heiList = null;
	public static int[] weiList = null;
	public static int[] lenList = null;
	public static int[] ferList = null;
	public static int[] itlList = null;

	public static void main(String[] args) throws Exception{
		


		Console con = new Console("");
		Connection c = null;
		Statement stmt = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","123");
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT COUNT(*) FROM DINODECK;" );
			
			while(rs.next()) {
			idList= new int[rs.getInt(1)];
			System.err.println(idList.length);
			heiList= new int[rs.getInt(1)];
			weiList= new int[rs.getInt(1)];
			lenList= new int[rs.getInt(1)];
			ferList= new int[rs.getInt(1)];
			itlList= new int[rs.getInt(1)];
			}
			
			stmt = c.createStatement();
			rs = stmt.executeQuery( "SELECT * FROM DINODECK;" );
			
			
			int counter = 0;
			while ( rs.next() ) {
				int id = rs.getInt("dinoid");
				idList[counter] = id;
				System.err.print(idList[counter]);
				String  name = rs.getString("name");
				nameList.add(counter, name);
				int height  = rs.getInt("height");
				heiList[counter] = height;
				int weight  = rs.getInt("weight");
				weiList[counter] = weight;
				int length = rs.getInt("length");
				lenList[counter] = length;
				int ferocity  =  rs.getInt("ferocity");
				ferList[counter] = ferocity;
				int intel = rs.getInt("intelligence");
				itlList[counter] = intel;

				con.println( "ID = " + id);
				con.println( "NAME = " + name );
				con.println( "HEIGHT = " + height );
				con.println( "WEIGTH = " + weight);
				con.println( "LENGTH = " + length );
				con.println( "FEROCITY = " + ferocity );
				con.println( "INTELLIGENCE = " + intel );
				con.println();
				counter++;
			}
			rs.close();
			stmt.close();
			c.close();
			con.println("Operation done successfully");
			//con.println("Number of registered Dinosaurs: "+idList.size());

			Random rand = new Random();

			int  n = rand.nextInt(15);
			con.println("The ferocity of a " +nameList.get(n)+ " is: " +ferList[n]);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}

	}

}
