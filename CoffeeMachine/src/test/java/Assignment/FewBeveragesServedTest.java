package Assignment;

import java.io.IOException;

import org.junit.Test;

import Assigment.Utils.IOUtil;
import Assignment.CoffeeDispenser;
import Assignment.model.Beverage;
import junit.framework.Assert;

public class FewBeveragesServedTest {

	@Test
	/*
	 * this test handles case when one of the ingredients needed by beverage is not
	 * available
	 */
	public void PartialBeveragesServedItemNotAvailable() throws IOException, InterruptedException {
		CoffeeDispenser dispenser = CoffeeDispenser.getInstance();
		IOUtil.jsonInputParser("ItemNotAvailable.json");
		dispenser.dispense();
		Thread.sleep(1000);
		int count = 0;
		Beverage notServed = null;
		for (Beverage c : dispenser.getBeverages()) {
			if (c.getServed())
				count++;
			if (c.getType().equals("green_tea"))
				notServed = c;
		}
		Assert.assertEquals(3, count);
		// checking for green tea is not served as green mixture is not available
		Assert.assertEquals(notServed.getServed(), false);
		CoffeeDispenser.reset();
	}

	/*
	 * this test handles the case when one of the ingredients is not sufficient in
	 * quantity to serve all beverages
	 */
	@Test
	public void PartialBeveragesServedItemNotSufficient() throws IOException, InterruptedException {
		CoffeeDispenser dispenser = CoffeeDispenser.getInstance();
		IOUtil.jsonInputParser("ItemNotSufficient.json");
		dispenser.dispense();
		Thread.sleep(1000);
		int count = 0;
		for (Beverage c : dispenser.getBeverages()) {
			if (c.getServed())
				count++;
		}
		Assert.assertEquals(3, count);
		CoffeeDispenser.reset();
	}

}
