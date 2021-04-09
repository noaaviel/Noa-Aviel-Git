package Q2;

public class TomatosDecorator extends PizzaDecorator {
	private Pizza pizza1;

	// constructor
	public TomatosDecorator(Pizza pizza) {
		super(pizza);
		this.pizza1 = pizza;
	}

	// adding toping to total
	@Override
	public double getTotal() {
		return pizza1.getTotal() + 8;
	}

	// adding toping to pizza
	@Override
	public String addToping() {
		return pizza1.addToping() + " With Tomatos ";
	}

}
