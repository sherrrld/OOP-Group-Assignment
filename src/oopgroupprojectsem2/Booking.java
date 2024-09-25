package OOPGroupProjectSem2;

import java.util.Random;

public class Booking {

    static Flight getflights() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private String bookingId;
    private Customer customer;
    private Flight flight;
    private String status;

    public Booking(String bookingId, Customer customer, Flight flight, String status) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.flight = flight;
        this.status = status;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Flight getFlight() {
        return flight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String generateBookingId(){
        // Define the characters for letters and numbers
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";

        // Create a random instance
        Random random = new Random();

        // Generate 3 random letters
        StringBuilder randomLetters = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            char randomLetter = letters.charAt(random.nextInt(letters.length()));
            randomLetters.append(randomLetter);
        }

        // Generate 3 random numbers
        StringBuilder randomNumbers = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            char randomNumber = numbers.charAt(random.nextInt(numbers.length()));
            randomNumbers.append(randomNumber);
        }

        // Combine the random letters and numbers
        String bookingId = randomLetters.toString() + randomNumbers.toString();

        // Print the generated booking ID
        return bookingId;
    }
}
