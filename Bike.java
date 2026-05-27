package parking;

public class Bike extends Vehicle{
	
	// Constructor
	public Bike(String plateNumber) {
		super (plateNumber, "Bike");
	}

	@Override
	public double calculateFee(long exitTime) {
		long millisParked = exitTime - getEntryTime();
		long hoursParked = millisParked / (1000 * 60 * 60);
		
		if (hoursParked < 1) {
			hoursParked = 1; // Minimum 1 hour
		}
		return (hoursParked * 100); // Rs.20 per hour for Bike
	}

}
