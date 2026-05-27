package parking;

public abstract class Vehicle {
	private String plateNumber ;
	private String vehicleType ;
	private long entryTime;

	// Constructor
	public Vehicle(String plateNumber, String vehicleType) {
		this.plateNumber = plateNumber ;
		this.vehicleType = vehicleType ;
		this.entryTime = System.currentTimeMillis() ;
	}
	
	// Getters
	public String getPlateNumber() {
		return plateNumber;
	}
	
	public String getVehicleType() {
		return vehicleType;
	}
	
	public long getEntryTime() {
		return entryTime;
	}
	
	// Abstract Method for Fee Calculation
	public abstract double calculateFee (long exitTime) ;

}


