package parking;

public class ParkingSlot {
	
	private int slotID ;
	private boolean isOccupied ;
	private Vehicle vehicle ;

	// Constructor
	public ParkingSlot(int slotID) {
		this.slotID = slotID;
		this.isOccupied = false;
		this.vehicle = null ;
	}
	
	// Getters
	public int getSlotID () {
		return slotID;
	}
	
	public boolean isOccupied () {
		return isOccupied;
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}

	// Function for occupying slot
	public void parkVehicle (Vehicle v) {
		this.vehicle = v ;
		this.isOccupied = true;
	}
	
	// Function for clearing slot
	public void clearSlot () {
		this.vehicle = null ;
		this.isOccupied = false;
	}
}
