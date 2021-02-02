package dealership;

import entities.*;
import java.util.ArrayList;

public class Main {
	static ArrayList<Person> customers = new ArrayList<Person>();
	static ArrayList<Person> dealers = new ArrayList<Person>();
	static ArrayList<Vehicle> cars = new ArrayList<Vehicle>(); 
	static ArrayList<Vehicle> bikes = new ArrayList<Vehicle>();
	

	public static void main(String[] args) {
		Controller.welcome();
		Controller.showMenu();
	}

}
