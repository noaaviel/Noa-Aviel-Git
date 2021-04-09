package Q2;

public class OlivesDecorator extends PizzaDecorator {
	private Pizza pizza1;

	// constructor
	public OlivesDecorator(Pizza pizza) {
		super(pizza);
		this.pizza1 = pizza;
	}

	// adding toping to total
	@Override
	public double getTotal() {
		return this.pizza1.getTotal() + 8;
	}

	// adding toping to pizza
	@Override
	public String addToping() {
		return this.pizza1.addToping() + " With Olives ";
	}

}
