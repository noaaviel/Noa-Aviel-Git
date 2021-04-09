package Q2;
//318323391 Noa Aviel
public class Main {

	public static void main(String[] args) {
		// creating 3 pizzas each of every kind
		Pizza pizza1 = new personalPizza();
		System.out.println(pizza1.addToping() + pizza1.getTotal());
		Pizza pizza2 = new familyPizza();
		System.out.println(pizza2.addToping() + pizza2.getTotal());
		Pizza pizza3 = new HugePizza();
		System.out.println(pizza3.addToping() + pizza3.getTotal());
		// adding toping decorators to pizza
		Pizza pizza1withtop = new TomatosDecorator(new BulgarianCheeseDecorator(pizza1));
		Pizza pizza2withtop = new MushroomsDecorator(new TomatosDecorator(pizza2));
		Pizza pizza3withtop = new OlivesDecorator(new TomatosDecorator(pizza3));
		// printing final price after topings were added
		System.out.println(pizza1withtop.addToping() + pizza1withtop.getTotal());
		System.out.println(pizza2withtop.addToping() + pizza2withtop.getTotal());
		System.out.println(pizza3withtop.addToping() + pizza3withtop.getTotal());

	}

}
