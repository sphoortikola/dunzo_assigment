package Assignment;

import java.io.IOException;

import org.junit.Test;

import Assigment.Utils.IOUtil;
import Assignment.CoffeeDispenser;
import Assignment.model.Beverage;
import junit.framework.Assert;

public class NoBeveragesServedTest {
	@Test

	/*
	 * this case handles when 0 beverages are served because of ingredients
	 * unavailable and insufficient quantity of ingredients
	 */
	public void NoBeverageServed() throws IOException, InterruptedException {
		CoffeeDispenser dispenser = CoffeeDispenser.getInstance();
		IOUtil.jsonInputParser("NoSuccess.json");
		dispenser.dispense();
		Thread.sleep(1000);
		int count = 0;
		for (Beverage c : dispenser.getBeverages()) {
			if (c.getServed())
				count++;
		}
		Assert.assertEquals(0, count);
		CoffeeDispenser.reset();
	}
}
