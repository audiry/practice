
import java.util.HashMap;
import java.util.Map;

public class Discount {

	public static final Map<String, Integer> DISCOUNT_PERC = new HashMap<String, Integer>();

	//hard coded values 
	public void generateDiscountPercentages() {
		DISCOUNT_PERC.put("Womens wear", 50);
		DISCOUNT_PERC.put("Mens wear", 0);

		DISCOUNT_PERC.put("Shirts", 0);
		DISCOUNT_PERC.put("Trousers", 0);
		DISCOUNT_PERC.put("Casuals", 30);
		DISCOUNT_PERC.put("Jeans", 20);
		DISCOUNT_PERC.put("Dresses", 50);
		DISCOUNT_PERC.put("Footwear", 50);

		DISCOUNT_PERC.put("Arrow", 20);
		DISCOUNT_PERC.put("Wrangler", 10);
		DISCOUNT_PERC.put("Vero Moda", 60);
		DISCOUNT_PERC.put("UCB", 0);
		DISCOUNT_PERC.put("Adidas", 5);
		DISCOUNT_PERC.put("Provogue", 20);
	}

	// calculate discounted prices as per given brand and category in inventory
	// choice wise
	public Map<Integer, Integer> getDiscountedPricesOfInventory(String[] inventory) {
		final Map<Integer, Integer> discountedPricesOfInventory = new HashMap<Integer, Integer>();

		for (int i = 0; i < inventory.length; i++) {
			String[] sts = splitString(inventory[i]);
			int lineNumber = 0, discountOfBrand = 0, discountOfCategory = 0, discount = -1, actualPrice = 0,
					discountedPrice = 0;

			lineNumber = integerValueOf(sts[0]);
			discountOfBrand = getDiscountPercentageOf(sts[1]);
			discountOfCategory = getDiscountPercentageOf(sts[2]);
			discount = getMaximumOf(discountOfBrand, discountOfCategory);
			actualPrice = integerValueOf(sts[3]);
			discountedPrice = getDiscountedPrice(discount, actualPrice);
			discountedPricesOfInventory.put(lineNumber, discountedPrice);
		}
		return discountedPricesOfInventory;
	}

	public int[] getComputedPricesOfOptions(String[] optionsOfCustomer,
			Map<Integer, Integer> discountedPricesOfInventory) {
		// for each option; get choices and their discounted prices and sum them
		// up , print it
		int[] r = new int[optionsOfCustomer.length];

		for (int i = 0; i < optionsOfCustomer.length; i++) {
			int result = 0;
			String[] choicesOfCustemerInOption = splitString(optionsOfCustomer[i]);
			int[] choices = new int[choicesOfCustemerInOption.length];

			for (int j = 0; j < choicesOfCustemerInOption.length; j++) {
				choices[j] = integerValueOf(choicesOfCustemerInOption[j]);
				result += discountedPricesOfInventory.get(choices[j]);
			}

			// For each option given; add result to array
			r[i] = result;
		}
		return r;
	}

	private static int getDiscountPercentageOf(String s) {
		return DISCOUNT_PERC.get(s.trim().toString());
	}

	private static int getDiscountedPrice(int discount, int actualPrice) {
		return (int) actualPrice - (actualPrice * discount) / 100;
	}

	// utility functions

	// Split the given string based on delimiter -- in this case : comma ","
	private static String[] splitString(String s) {
		return s.split(",");
	}

	// String to Integer conversion
	private static int integerValueOf(String s) {
		return Integer.parseInt(s.trim());
	}

	// Returns maximum of two numbers;
	private static int getMaximumOf(int a, int b) {
		return (a >= b) ? a : b;
	}

}
