package application;

public class Dinosaur {

	private int dinoID;
	private String dinoName;
	private int dinoHeight;
	private int dinoWeight;
	private int dinoLength;
	private int dinoFerocity;
	private int dinoIntel;

	public Dinosaur(int id, String name, int height, int weight, int length, int ferocity, int intel) {

		dinoID = id;
		dinoName = name;
		dinoHeight = height;
		dinoWeight = weight;
		dinoLength = length;
		dinoFerocity = ferocity;
		dinoIntel = intel;

	}
	
	public int getID() {
		return dinoID;
	}
	
	public String getName() {
		return dinoName;
	}
	
	public int getHeight() {
		return dinoHeight;
	}
	
	public int getWeight() {
		return dinoWeight;
	}
	
	public int getLength() {
		return dinoLength;
	}
	
	public int getFerocity() {
		return dinoFerocity;
	}
	
	public int getIntel(){
		return dinoIntel;
	}
}

