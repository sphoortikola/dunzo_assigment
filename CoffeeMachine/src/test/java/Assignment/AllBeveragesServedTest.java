package Assignment;

import java.io.IOException;

import org.junit.Test;

import Assigment.Utils.IOUtil;
import Assignment.CoffeeDispenser;
import Assignment.model.Beverage;
import junit.framework.Assert;

public class AllBeveragesServedTest {
	@Test

	/*
	 * this test handles the case where all beverages are served because all items
	 * are available and in sufficient quantity
	 */
	public void AllBeveragesSuccessful() throws IOException, InterruptedException {
		CoffeeDispenser dispenser = CoffeeDispenser.getInstance();
		IOUtil.jsonInputParser("AllSuccess.json");
		dispenser.dispense();
		// waiting for dispense action to complete
		Thread.sleep(1000);
		int count = 0;
		for (Beverage c : dispenser.getBeverages()) {
			if (c.getServed())
				count++;
		}
		Assert.assertEquals(4, count);
		CoffeeDispenser.reset();
	}
}
