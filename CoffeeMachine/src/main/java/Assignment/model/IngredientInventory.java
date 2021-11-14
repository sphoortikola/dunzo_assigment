package Assignment.model;

import java.util.HashMap;
import java.util.Map;

public class IngredientInventory {

	private static IngredientInventory inventory = null;
	private Map<String, Integer> items = new HashMap<String, Integer>();

	private IngredientInventory() {

	}

	public static IngredientInventory getInstance() {
		if (inventory == null)
			inventory = new IngredientInventory();
		return inventory;
	}

	public static void reset() {
		inventory = null;

	}

	// add/update ingredients in the machine
	public void addIngredients(String key, int quantity) {

		if (items.get(key) == null)
			items.put(key, quantity);
		else
			items.put(key, items.get(key) + quantity); // refill when item is low in quantity
	}

	/*
	 * this method serves beverage if all ingredients are available in required
	 * quantities, else throws error messages
	 */
	public synchronized void serve(Beverage beverage) {
		/*
		 * making the method synchronized to ensure no race condition when multiple
		 * threads try to access and modify ingredient inventory
		 */

		StringBuilder rejectReason = new StringBuilder("");
		boolean served = true;
		for (Ingredient i : beverage.getIngredients()) {

			// ingredient isn't available
			if (items.get(i.getName()) == null) {
				served = false;
				rejectReason.append(i.getName());
				rejectReason.append(" is not available");
				break;
			}
			// quantity of ingredient is insufficient
			else if (i.getQuantity() > items.get(i.getName())) {
				served = false;
				rejectReason.append(i.getName());
				rejectReason.append(" is not sufficient");
				break;
			}

		}

		// case all items needed by beverage are available and in sufficient quanties
		if (served) {
			// update ingredient quantities
			for (Ingredient i : beverage.getIngredients()) {
				items.put(i.getName(), items.get(i.getName()) - i.getQuantity());
			}
			System.out.println(beverage.getType() + " is prepared");
			beverage.setServed();
		} else {
			System.out.println(beverage.getType() + " cannot be prepared because " + rejectReason);
		}
	}
}
