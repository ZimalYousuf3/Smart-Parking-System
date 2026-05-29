# 🅿️ Smart Parking System

A Java-based desktop application for managing a parking lot. Built with **Java Swing** for the GUI, this system allows you to park vehicles, calculate fees, and track available slots — all in a clean, dark-themed interface.

---

## 📸 Features

- 🚗 **Park vehicles** (Car or Bike) with a plate number
- 🚪 **Exit vehicles** and automatically generate a receipt with fee
- 📊 **Dashboard** showing total, available, and occupied slots in real time
- 🗺️ **Slot View** with a color-coded grid (green = free, red = occupied)
- 🧾 **Receipt generation** with duration and fee details
- 🔒 Prevents duplicate parking of the same plate number

---

## 🏗️ Project Structure

```
SmartParkingSystem/
│
├── parking/
│   ├── Vehicle.java        # Abstract base class for all vehicles
│   ├── Car.java            # Car class (extends Vehicle)
│   ├── Bike.java           # Bike class (extends Vehicle)
│   ├── ParkingSlot.java    # Represents a single parking slot
│   ├── ParkingLot.java     # Manages all slots (park, find, count)
│   ├── Receipt.java        # Stores exit/fee details
│   └── MainFrame.java      # Main GUI (Java Swing)
```

---

## 💰 Fee Structure

| Vehicle | Rate       |
|---------|------------|
| Car     | Rs. 200/hr |
| Bike    | Rs. 100/hr |

> Minimum charge is **1 hour** even if parked for less than 60 minutes.

---

## 🧠 OOP Concepts Used

| Concept          | Where Used                                       |
|------------------|--------------------------------------------------|
| **Inheritance**  | `Car` and `Bike` extend `Vehicle`                |
| **Abstraction**  | `Vehicle` has abstract method `calculateFee()`   |
| **Polymorphism** | `calculateFee()` behaves differently per vehicle |
| **Encapsulation**| All fields are private with getters              |

---

## 🖥️ How to Run

### Requirements
- Java JDK 8 or above
- Any IDE (IntelliJ IDEA, Eclipse, VS Code) or command line

### Steps

**Option 1 — Using an IDE:**
1. Clone this repository
2. Open the project in your IDE
3. Run `MainFrame.java`

**Option 2 — Using Command Line:**
```bash
# Compile all files
javac parking/*.java

# Run the application
java parking.MainFrame
```

---

## 📋 How to Use

1. **Dashboard Tab** — See total, available, and occupied slots at a glance
2. **Park Vehicle Tab** — Enter plate number, choose Car or Bike, click "Park Vehicle"
3. **Exit Vehicle Tab** — Enter plate number, click "Calculate & Exit" to get receipt
4. **View Slots Tab** — See all slots in a grid; click "Refresh" to update

---

## 📂 Class Descriptions

### `Vehicle.java`
Abstract class. Stores plate number, vehicle type, and entry time. Forces subclasses to implement `calculateFee()`.

### `Car.java` / `Bike.java`
Extend `Vehicle`. Each implements its own `calculateFee()` with different hourly rates.

### `ParkingSlot.java`
Represents one slot. Tracks if it's occupied and which vehicle is parked there.

### `ParkingLot.java`
Manages an array of `ParkingSlot` objects. Handles parking, finding a vehicle by plate, and counting available slots.

### `Receipt.java`
Holds exit details — plate number, vehicle type, slot ID, duration, and fee. Used to display the receipt after a vehicle exits.

### `MainFrame.java`
The main GUI class using Java Swing. Contains four tabs: Dashboard, Park Vehicle, Exit Vehicle, and View Slots.

---

## 👩‍💻 Author

**Zimal** — BSCS Student  
Smart Parking System — OOP Course Project

---

## 📄 License

This project is for educational purposes only.
