package Assignment.model;

import java.util.List;

public class Beverage implements Runnable {

	private String type;
	// contains list of ingredients and their quantities
	private List<Ingredient> ingredients;
	/*
	 * this variable is set to true if all the items required to serve the beverage
	 * are present, used in ingredient inventory class
	 */
	private boolean served = false;

	public Beverage(String n, List<Ingredient> ingredients) {
		this.type = n;
		this.ingredients = ingredients;
	}

	public void setServed() {
		served = true;
	}

	public boolean getServed() {
		return served;
	}

	public String getType() {
		return this.type;
	}

	public List<Ingredient> getIngredients() {

		return this.ingredients;
	}

	public void run() {
		IngredientInventory.getInstance().serve(this);
	}

}
