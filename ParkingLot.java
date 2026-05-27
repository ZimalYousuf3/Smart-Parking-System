package parking;

public class ParkingLot {
    private ParkingSlot[] slots;
    private int totalSlots;

    // Constructor
    public ParkingLot(int totalSlots) {
        this.totalSlots = totalSlots;
        slots = new ParkingSlot[totalSlots];
        
        for (int i = 0; i < totalSlots; i++) {
            slots[i] = new ParkingSlot(i + 1);
        }
    }

    // Park a vehicle. It returns slot number or returns -1 if full
    public int parkVehicle(Vehicle v) {
        for (ParkingSlot slot : slots) {
            if (!slot.isOccupied()) {
                slot.parkVehicle(v);
                return slot.getSlotID();
            }
        }
        return -1; // no slot available parking is full
    }

    // Find vehicle by plate number
    public ParkingSlot findVehicle(String plate) {
        for (ParkingSlot slot : slots) {
            if (slot.isOccupied() &&        // compares plate number ignoring uppercase/lowercase 
                slot.getVehicle().getPlateNumber().equalsIgnoreCase(plate)) { 
                return slot;
            }
        }
        return null; // When vehicle is not found in any slot
    }

    public ParkingSlot[] getSlots() { // for showing slot grid in GUI
    	return slots;
    }
    public int getTotalSlots() {  // for returning total slot count
    	return totalSlots;
    }

    public int getAvailableCount() { // Available slot counter
        int count = 0;
        for (ParkingSlot s : slots) {
            if (!s.isOccupied()) count++;
        }
        return count;
        
    }
}
