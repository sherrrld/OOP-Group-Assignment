package OOPGroupProjectSem2;
import javafx.collections.ObservableList;

public class Admin{
    private String password;
    private String name;
    private String email;

    public Admin(String name, String email, String password) {
        this.name=name;
        this.email=email;
        this.password=password;
    }

    public void addFlight(Flight flight, ObservableList<Flight> flights) {
        flights.add(flight);
        System.out.println("Flight added successfully: " + flight.getFlightNumber());
    }

    public void removeFlight(String flightNumber, ObservableList<Flight> flights) {
        flights.removeIf(flight -> flight.getFlightNumber().equals(flightNumber));
        System.out.println("Flight removed successfully: " + flightNumber);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }
}
