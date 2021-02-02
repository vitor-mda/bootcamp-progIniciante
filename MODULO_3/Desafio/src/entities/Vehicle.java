package entities;

public abstract class Vehicle {
	protected String model;
	protected String brand;
	protected int year;
	protected double price;
	protected Dealer dealer;
	protected Customer buyer;
	
	public String getModel() {
		return model;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public int getYear() {
		return year;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public Dealer getDealer() {
		return dealer;
	}
	
	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}
	
	public Customer getBuyer() {
		return buyer;
	}
	
	public void setBuyer(Customer buyer) {
		if (this.buyer == null) {
			this.buyer = buyer;
		} else {
			System.out.printf("O veículo já foi vendido.%n");
		}
	}
	
	public String toString() {
		return (buyer == null ? "R$ " + price + " | " : "(VENDIDO) ") + brand + " " + model + ", " + year;
	}
	
	public Vehicle(String model, String brand, int year, double price, Dealer dealer) {
		this.model = model;
		this.brand = brand;
		this.year = year;
		this.price = price;
	}
	
	public Vehicle(String model, String brand, int year, double price) {
		this.model = model;
		this.brand = brand;
		this.year = year;
		this.price = price;
	}
	
	public Vehicle(String model, String brand, int year) {
		this.model = model;
		this.brand = brand;
		this.year = year;
	}
}
