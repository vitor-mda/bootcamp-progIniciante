package entities;

public class Order {
	private Customer customer;
	private Pizza pizza;
	private float distance;
	
	public String getCustomerName() {
		return customer.getName();
	}
	
	public float getDistance() {
		return distance;
	}
	
	public String toString() {
		return pizza + " para o(a )cliente " + customer.getName() + " (" + distance + " km).";
	}
	
	public Order(Customer customer, int pizzaType, float distance) {
		this.customer = customer;
		this.pizza = new Pizza(pizzaType);
		this.distance = distance;
	}
}
