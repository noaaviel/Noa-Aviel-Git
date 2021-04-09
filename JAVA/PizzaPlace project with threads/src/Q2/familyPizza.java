package Q2;

public class familyPizza implements Pizza {
	private String name;
	private int price;
	private int calories;

	// constructors
	public familyPizza(String name, int price, int calories) {
		this.name = name;
		this.price = price;
		this.calories = calories;
	}

	public familyPizza() {

	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	// get total price
	@Override
	public double getTotal() {
		return 35;
	}

	// to String method
	@Override
	public String toString() {
		return "familyPizza [name=" + name + ", price=" + price + ", calories=" + calories + "]";
	}

	// adding topings to personal pizza (eventually)
	@Override
	public String addToping() {
		return "Faimly Pizza: ";
	}
}
