package OOPGroupProjectSem2;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;

public class Customer {
    private final SimpleStringProperty name;
    private final SimpleStringProperty email;
    private List<Booking> bookings;

    public Customer(String name, String email) {
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        
        this.bookings = new ArrayList<>();
    }
    
    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }
    
    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public boolean cancelBooking(String bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingId)) {
                booking.getFlight().cancelSeat();
                booking.setStatus("Cancelled");
                System.out.println("Booking cancelled successfully: " + bookingId);
                return true;
            }
        }
        System.out.println("Booking ID not found: " + bookingId);
        return false;
    }

    public List<Booking> getBookings() {
        return bookings;
    }
    
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }
    
    @Override
    public String toString() {
        return getName();
    }
    
}
