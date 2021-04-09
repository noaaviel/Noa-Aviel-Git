package Q2;

//abstract class of pizza decorator that implements pizza interface
public abstract class PizzaDecorator implements Pizza {
	protected Pizza decoratedPizza;

	public PizzaDecorator(Pizza pizza) {
		this.decoratedPizza = pizza;
	}

}
