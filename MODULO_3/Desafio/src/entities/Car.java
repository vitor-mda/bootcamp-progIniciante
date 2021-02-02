package entities;

public class Car extends Vehicle {	
	public Car(String model, String brand, int year, double price, Dealer dealer) {
		super(model, brand, year, price, dealer);
	}
	
	public Car(String model, String brand, int year, double price) {
		super(model, brand, year, price);
	}
	
	public Car(String model, String brand, int year) {
		super(model, brand, year);
	}
}
