package parking;

public class Car extends Vehicle {

	// Constructor
	public Car(String plateNumber) {
		super (plateNumber, "Car");
	}

	@Override
	public double calculateFee(long exitTime) {
		long millisParked = exitTime - getEntryTime();
		long hoursParked = millisParked / (1000 * 60 * 60);
		
		if (hoursParked < 1) {
			hoursParked = 1; // Minimum 1 hour
		}
		return (hoursParked * 200); // Rs.50 per hour for Car
	}

}
