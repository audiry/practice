
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class DiscountCalc {

	public static void main(String[] args) throws IOException {

		// as per given percentage discounts, generate data
		Discount d = new Discount();
		d.generateDiscountPercentages();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// read inventory as csv from console -- number of lines (n) in line 1
		// and followed by 'n' lines of inventory
		int numberOfLines = integerValueOf(br.readLine());
		String[] inventory = new String[numberOfLines];
	
		for (int i = 0; i < numberOfLines; i++) {
			inventory[i] = br.readLine();
		}

		final Map<Integer, Integer> discountedPricesOfInventory = d.getDiscountedPricesOfInventory(inventory);
		// read white space or blank line as per sample input & output given
		br.readLine();
		// read number of categories as per sales from console
		int lines = integerValueOf(br.readLine());
		String[] optionsOfCustomer = new String[lines];
		for (int i = 0; i < lines; i++) {
			optionsOfCustomer[i] = br.readLine();
		}

		int[] result = d.getComputedPricesOfOptions(optionsOfCustomer, discountedPricesOfInventory);

		// printing on console
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}

	// Method to convert String to Integer
	private static int integerValueOf(String s) {
		return Integer.parseInt(s.trim());
	}

}
