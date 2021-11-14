package Assigment.Utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONObject;

import Assignment.CoffeeDispenser;
import Assignment.model.Beverage;
import Assignment.model.Ingredient;
import Assignment.model.IngredientInventory;

public class IOUtil {
	private static final String MACHINE = "machine";
	private static final String OUTLETS = "outlets";
	private static final String COUNT = "count_n";
	private static final String TOTAL_ITEMS_QUANTITY = "total_items_quantity";
	private static final String BEVERAGES = "beverages";

	/*
	 * this method reads the json files from src/main/resources and initilizes the
	 * ingredient inventory and beverage list of coffee dispensor
	 */
	public static void jsonInputParser(String jsonFileName) throws IOException {

		URL url = Beverage.class.getClassLoader().getResource(jsonFileName);
		Path absPath = new File(url.getPath()).toPath();
		CoffeeDispenser dispensor = CoffeeDispenser.getInstance();
		String jsonFile = new String(Files.readAllBytes(absPath));
		JSONObject data = new JSONObject(jsonFile);
		JSONObject machineData = data.getJSONObject(MACHINE);

		// parse number of outlets
		JSONObject outlets = machineData.getJSONObject(OUTLETS);
		int outletCount = outlets.getInt(COUNT);
		dispensor.setN(outletCount);

		// parse list of ingredients
		JSONObject ingredients = machineData.getJSONObject(TOTAL_ITEMS_QUANTITY);
		IngredientInventory inventory = IngredientInventory.getInstance();
		for (String item : ingredients.keySet()) {
			int quantity = ingredients.getInt(item);
			inventory.addIngredients(item, quantity);
		}

		// parse list of beverages and their ingredients
		JSONObject beverages = machineData.getJSONObject(BEVERAGES);
		List<Beverage> beverageList = new LinkedList<Beverage>();
		for (String beverage : beverages.keySet()) {
			JSONObject beverageIngredients = beverages.getJSONObject(beverage);
			List<Ingredient> ingList = new LinkedList<Ingredient>();
			for (String item : beverageIngredients.keySet()) {
				ingList.add(new Ingredient(item, beverageIngredients.getInt(item)));
			}
			beverageList.add(new Beverage(beverage, ingList));
		}

		dispensor.setBeverages(beverageList);
	}

}
