package entities;

public class Motorcycle extends Vehicle {
	public Motorcycle(String model, String brand, int year, double price, Dealer dealer) {
		super(model, brand, year, price, dealer);
	}
	
	public Motorcycle(String model, String brand, int year, double price) {
		super(model, brand, year, price);
	}
	
	public Motorcycle(String model, String brand, int year) {
		super(model, brand, year);
	}
}
