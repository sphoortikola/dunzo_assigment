package Assignment.model;

public class Ingredient {

	private String name;
	private int quantity;
	
	public Ingredient(String n,int q){
		this.name=n;
		this.quantity=q;
	}
	public String getName() {
		return this.name;
	}
	public int getQuantity() {
		return this.quantity;
	}
}
