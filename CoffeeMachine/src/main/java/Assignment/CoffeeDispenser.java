package Assignment;

import java.util.List;

import Assignment.model.Beverage;
import Assignment.model.IngredientInventory;

public class CoffeeDispenser {

	private static CoffeeDispenser dispenserInstance = null;
	// N is total no of outlets of dispenser
	private static int N;
	// contains map of ingredients and their quantities
	private static IngredientInventory inventory;
	// contains list of beverages and quantities of ingredients
	private static List<Beverage> beverages;

	// making the constructor private so it cannot be initialized from any other
	// class
	private CoffeeDispenser() {

	}

	// singleton, is initialized only once
	public static CoffeeDispenser getInstance() {
		if (dispenserInstance == null) {
			dispenserInstance = new CoffeeDispenser();
		}
		return dispenserInstance;

	}

	// this method is to reset the singleton object for use in tests
	public static void reset() {
		dispenserInstance = null;
		beverages = null;
		IngredientInventory.reset();

	}

	public static void setN(int outlets) {
		N = outlets;
	}

	public static void setBeverages(List<Beverage> beverageList) {
		beverages = beverageList;
	}

	public List<Beverage> getBeverages() {
		return beverages;
	}

	// this method instantiates threads for all beverages
	public void dispense() {

		for (Beverage b : beverages) {
			Thread t = new Thread(b);
			t.start();
		}

	}

}
