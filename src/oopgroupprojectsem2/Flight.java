package OOPGroupProjectSem2;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Flight {
    private final SimpleStringProperty flightNumber;
    private final SimpleStringProperty origin;
    private final SimpleStringProperty destination;
    private final SimpleIntegerProperty seatsAvailable;
    private final int totalSeats;

    public Flight(String flightNumber, String origin, String destination, int totalSeats) {
        this.flightNumber = new SimpleStringProperty(flightNumber);
        this.origin = new SimpleStringProperty(origin);
        this.destination = new SimpleStringProperty(destination);
        this.seatsAvailable = new SimpleIntegerProperty(totalSeats);
        this.totalSeats = totalSeats;
    }

    public String getFlightNumber() {
        return flightNumber.get();
    }

    public SimpleStringProperty flightNumberProperty() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin.get();
    } 

    public SimpleStringProperty originProperty() {
        return origin;
    }

    public String getDestination() {
        return destination.get();
    }

    public SimpleStringProperty destinationProperty() {
        return destination;
    }

    public int getSeatsAvailable() {
        return seatsAvailable.get();
    }

    public SimpleIntegerProperty seatsAvailableProperty() {
        return seatsAvailable;
    }

    public void bookSeat() {
        if (seatsAvailable.get() > 0) {
            seatsAvailable.set(seatsAvailable.get() - 1);
        }
    }

    public void cancelSeat() {
        if (seatsAvailable.get() < totalSeats) {
            seatsAvailable.set(seatsAvailable.get() + 1);
        }
    }
    
    public String toString() {
        return getFlightNumber();
    }
}
