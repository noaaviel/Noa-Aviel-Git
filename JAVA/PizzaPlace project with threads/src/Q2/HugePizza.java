package Q2;

public class HugePizza implements Pizza {
	private String name;
	private int price;
	private int calories;

	//constructors
	public HugePizza(String name, int price, int calories) {
		this.name = name;
		this.price = price;
		this.calories = calories;
	}

	public HugePizza() {
	}

	//getters and setters
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
	//getting total price of pizza
	@Override
	public double getTotal() {
		return 55;
	}

	//toString method
	@Override
	public String toString() {
		return "hugePizzaa [name=" + name + ", price=" + price + ", calories=" + calories + "]";
	}

	//adding topings to huge pizza (eventually)
	@Override
	public String addToping() {
		return "Huge Pizza: ";
	}

}
