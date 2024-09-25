package OOPGroupProjectSem2;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.scene.control.ComboBox;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import static javafx.application.Application.launch;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Main extends Application {
    private ObservableList<Flight> flights;
    private List<Customer> customers;
    private List<Booking> bookings;
    private Admin admin;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        flights = FXCollections.observableArrayList();
        customers = new ArrayList<>();
        bookings = new ArrayList<>();
        admin = new Admin("Admin", "admin@example.com","admin123");

        showLoginPage();
    }
    
    private void showLoginPage() {
        primaryStage.setTitle("₊˚⊹ Login");

        Label emailLabel = new Label("♡ Email:");
        emailLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
        emailLabel.setTextFill(Color.BLACK);
        TextField emailField = new TextField();
        Label passwordLabel = new Label("⊹₊ ⋆ Password:");
        passwordLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        loginButton.setFont(Font.font("Helvetica", FontWeight.BOLD,15));
        loginButton.setStyle("-fx-background-color: #ff847c; -fx-text-fill: white;");
        Button registerButton = new Button("Register");
        registerButton.setFont(Font.font("Helvetica", FontWeight.BOLD,15));
        registerButton.setStyle("-fx-background-color: #e84a5f; -fx-text-fill: white;");

        loginButton.setOnAction(e -> handleLogin(emailField.getText(), passwordField.getText()));
        registerButton.setOnAction(e -> showRegisterPage());

        VBox vbox = new VBox(10, emailLabel, emailField, passwordLabel, passwordField, loginButton, registerButton);
        vbox.setPadding(new Insets(25, 25, 25, 25));
        
    

        //vbox.setStyle("-fx-background-image: url('kapal.jpeg'); -fx-background-repeat: stretch; -fx-background-size: 900 506; -fx-background-position: center center; -fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0);");
            
        //vbox.setStyle("-fx-background-color: #99b898;");
        
        primaryStage.setScene(new Scene(vbox, 300, 300));
        primaryStage.show();
    }

    private void handleLogin(String email, String password) {
        if (admin.getEmail().equals(email) && admin.verifyPassword(password)) {
            showMainMenu();
        } else {
            showAlert("Incorrect email or password. Please try again.");
        }
    }

    private void showRegisterPage() {
        primaryStage.setTitle("₊˚⊹ Register");

        Label nameLabel = new Label("⊹₊ ⋆ Name:");
        nameLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
        nameLabel.setTextFill(Color.WHITE);
        TextField nameField = new TextField();
        nameField.setStyle("-fx-background-color:#99b898");
        Label emailLabel = new Label("⊹₊ ⋆ Email:");
        emailLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
        emailLabel.setTextFill(Color.WHITE);
        TextField emailField = new TextField();
        emailField.setStyle("-fx-background-color:#99b898");
        Label passwordLabel = new Label("⊹₊ ⋆ Password:");
        passwordLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
        passwordLabel.setTextFill(Color.WHITE);
        PasswordField passwordField = new PasswordField();
        passwordField.setStyle("-fx-background-color:#99b898");
        Button registerButton = new Button("Register");
        registerButton.setFont(Font.font("Helvetica", FontWeight.BOLD,15));
        registerButton.setStyle("-fx-background-color:#2a363b; -fx-text-fill: white;");
        
        Button backButton = new Button("Back");
        backButton.setFont(Font.font("Helvetica", FontWeight.BOLD,15));
        backButton.setStyle("-fx-background-color:white; -fx-text-fill: black;");

        registerButton.setOnAction(e -> handleRegister(nameField.getText(), emailField.getText(), passwordField.getText()));
        backButton.setOnAction(e -> showLoginPage());

        VBox vbox = new VBox(10, nameLabel, nameField, emailLabel, emailField, passwordLabel, passwordField, registerButton, backButton);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setStyle("-fx-background-color: #e84a5f;");
        primaryStage.setScene(new Scene(vbox, 300, 400));
        primaryStage.show();
    }

    private void handleRegister(String name, String email, String password) {
        admin = new Admin(name, email, password);
        showAlert("Registration successful! Please login.");
        showLoginPage();
    }

    private void showMainMenu() {
        primaryStage.setTitle("₊˚⊹ Airline Reservation System");

        Label welcomeLabel = new Label("Welcome, "+admin.getName()+"!");
        welcomeLabel.setFont(Font.font("Arial",  15));
        welcomeLabel.setStyle("-fx-text-fill: white;");
        Button manageCustomersButton = new Button("⊹₊ ⋆  1- Manage Customers");
        manageCustomersButton.setStyle("-fx-background-color: #7cb6a0; -fx-text-fill: white;");
        manageCustomersButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));

        Button manageFlightsButton = new Button("⊹₊ ⋆  2- Manage Flights");
        manageFlightsButton.setStyle("-fx-background-color: #7cb6a0; -fx-text-fill: white;");
        manageFlightsButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
        Button exitButton = new Button("Exit");

        manageCustomersButton.setOnAction(e -> showCustomerManagement());
        manageFlightsButton.setOnAction(e -> showFlightManagement());
        exitButton.setOnAction(e -> {
            primaryStage.close();
            System.out.println("Exiting system. Goodbye!");
        });

        VBox vbox = new VBox(10, welcomeLabel, manageCustomersButton, manageFlightsButton, exitButton);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setStyle("-fx-background-color: #2a363b;");
        
        primaryStage.setScene(new Scene(vbox, 300, 200));
        primaryStage.show();
    }

    private void showCustomerManagement() {
        Label customerLabel = new Label("Manage Customers");
        customerLabel.setFont(Font.font("arial",  15));
        Button addNewCustomerButton = new Button("1- Add New Customer •⩊•");
        addNewCustomerButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: pink;");
        addNewCustomerButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
        Button addNewBookingButton = new Button("2- Add New Booking •⩊•");
        addNewBookingButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: pink;");
        addNewBookingButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
        Button cancelBookingButton = new Button("3- Cancel Booking");
        cancelBookingButton.setStyle("-fx-background-color: white; -fx-text-fill: #2a363b;");
        cancelBookingButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
        Button viewBookingButton = new Button("4- View List of Bookings •⩊• ");
        viewBookingButton.setStyle("-fx-background-color: #2a363b; -fx-text-fill: white;");
        viewBookingButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
        Button backButton = new Button("Back");

        addNewCustomerButton.setOnAction(e -> addNewCustomer());
        addNewBookingButton.setOnAction(e -> addNewBooking());
        cancelBookingButton.setOnAction(e -> cancelBooking());
        viewBookingButton.setOnAction(e -> viewBooking());
        backButton.setOnAction(e -> showMainMenu());

        VBox vbox = new VBox(10, customerLabel, addNewCustomerButton, addNewBookingButton, cancelBookingButton, viewBookingButton, backButton);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setStyle("-fx-background-color: #c8c8a9;");
        primaryStage.setScene(new Scene(vbox, 400, 300));
    }

    private void showFlightManagement() {
        Label flightLabel = new Label("Manage Flights");
        flightLabel.setStyle(" -fx-text-fill: white;");
        flightLabel.setFont(Font.font("arial",  15));
        Button addFlightButton = new Button("1- Add New Flight");
        addFlightButton.setStyle("-fx-background-color: #db3498; -fx-text-fill: pink;");
        addFlightButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
        Button removeFlightButton = new Button("2- Remove Flight");
        removeFlightButton.setStyle("-fx-background-color: #db3498; -fx-text-fill: pink;");
        removeFlightButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
        Button viewFlightsButton = new Button("3- View List of Incoming Flights");
        viewFlightsButton.setStyle("-fx-background-color: #db3498; -fx-text-fill: pink;");
        viewFlightsButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
        Button backButton = new Button("Back");
        

        addFlightButton.setOnAction(e -> addNewFlight());
        removeFlightButton.setOnAction(e -> removeFlight());
        viewFlightsButton.setOnAction(e -> viewFlights());
        backButton.setOnAction(e -> showMainMenu());

        VBox vbox = new VBox(10, flightLabel, addFlightButton, removeFlightButton, viewFlightsButton, backButton);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setStyle("-fx-background-color: #2a363b;");

        primaryStage.setScene(new Scene(vbox, 300, 200));
    }

    private void addNewCustomer() {
        Dialog<Customer> dialog = new Dialog<>();
        dialog.setTitle("₊˚⊹ Add New Customer");
        dialog.setHeaderText("Enter Customer Details");

   
        Label nameLabel = new Label("1. ♡ Name:");
        TextField nameField = new TextField();
        nameLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
        nameLabel.setTextFill(Color.WHITE);
        Label emailLabel = new Label("2. ♡ Email:");
        emailLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
        emailLabel.setTextFill(Color.WHITE);
        TextField emailField = new TextField();

        VBox vbox = new VBox(10, nameLabel, nameField, emailLabel, emailField);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setStyle("-fx-background-color: #8b0000;");
        dialog.getDialogPane().setContent(vbox);

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return new Customer(nameField.getText(), emailField.getText());
            }
            return null;
        });

        dialog.showAndWait().ifPresent(customer -> {
            customers.add(customer);
            showAlert("Customer added successfully: " + customer.getName());
        });
    }

    private void addNewBooking() {
    Dialog<Booking> dialog = new Dialog<>();
    dialog.setTitle("₊˚⊹ Add New Booking");
    dialog.setHeaderText("Enter Booking Details");

    Label bookingIdLabel = new Label("♡⸝⸝ Booking ID:");
    bookingIdLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
    bookingIdLabel.setTextFill(Color.BLACK);
    TextField bookingIdField = new TextField();
    bookingIdField.setText(generateBookingId());
    //bookingIdLabel.setText(generateBookingId());
    Label statusLabel = new Label("♡⸝⸝ Status:");
    statusLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
    statusLabel.setTextFill(Color.BLACK);
    TextField statusField = new TextField();

    Label customerLabel = new Label("♡⸝⸝ Customer:");
    customerLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
    ComboBox<Customer> customerComboBox = new ComboBox<>();
    customerComboBox.setItems(FXCollections.observableArrayList(customers));
    customerComboBox.setConverter(new StringConverter<Customer>() {
        @Override
        public String toString(Customer customer) {
            return customer != null ? customer.getName() : "";
        }

        @Override
        public Customer fromString(String string) {
            return null; // Not used in this context
        }
    });

    Label flightLabel = new Label("♡⸝⸝ Flight:");
    flightLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
    ComboBox<Flight> flightComboBox = new ComboBox<>();
    flightComboBox.setItems(flights); // Ensure flights are in ObservableList<Flight>
    flightComboBox.setConverter(new StringConverter<Flight>() {
        @Override
        public String toString(Flight flight) {
            return flight != null ? flight.getFlightNumber() : "";
        }

        @Override
        public Flight fromString(String string) {
            return null; // Not used in this context
        }
    });

    VBox vbox = new VBox(10, bookingIdLabel, bookingIdField, statusLabel, statusField, customerLabel, customerComboBox, flightLabel, flightComboBox);
    vbox.setPadding(new Insets(10, 10, 10, 10));
    vbox.setStyle("-fx-background-color: #2a9d8f");
    dialog.getDialogPane().setContent(vbox);

    ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

    dialog.setResultConverter(dialogButton -> {
        if (dialogButton == addButtonType) {
            Customer selectedCustomer = customerComboBox.getSelectionModel().getSelectedItem();
            Flight selectedFlight = flightComboBox.getSelectionModel().getSelectedItem();

            if (selectedCustomer != null && selectedFlight != null) {
                Booking newBooking = new Booking(bookingIdField.getText(), selectedCustomer, selectedFlight, statusField.getText());
                selectedCustomer.addBooking(newBooking);
                selectedFlight.bookSeat();
                bookings.add(newBooking);
                return newBooking;
            } else {
                showAlert("Please select a customer and a flight.");
                return null;
            }
        }
        return null;
    });

    dialog.showAndWait().ifPresent(booking -> {
        showAlert("Booking added successfully: " + booking.getBookingId());
    });
}


    private void cancelBooking() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("₊˚⊹ Cancel Booking");
        dialog.setHeaderText("Enter Booking ID to Cancel");
        dialog.setContentText("Booking ID:");

        dialog.showAndWait().ifPresent(bookingId -> {
            boolean found = false;
            for (Customer customer : customers) {
                if (customer.cancelBooking(bookingId)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                showAlert("Booking ID not found: " + bookingId);
            }
            else{
                showAlert("Booking ID " + bookingId+" successfully cancelled");
            }
        });
    }

    private void viewBooking() {
        TableView<Booking> bookingTable = new TableView<>();

        TableColumn<Booking, String> bookingIdColumn = new TableColumn<>("Booking ID");
        bookingIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBookingId()));

        TableColumn<Booking, String> customerColumn = new TableColumn<>("Customer");
        customerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCustomer().getName()));

        TableColumn<Booking, String> flightColumn = new TableColumn<>("Flight");
        flightColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFlight().getFlightNumber()));

        TableColumn<Booking, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));

        bookingTable.getColumns().addAll(bookingIdColumn, customerColumn, flightColumn, statusColumn);
        bookingTable.setItems(FXCollections.observableArrayList(bookings));

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showCustomerManagement());

        VBox vbox = new VBox(10, bookingTable, backButton);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setStyle("-fx-background-color:#e76f51");

        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
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

    private void addNewFlight() {
        Dialog<Flight> dialog = new Dialog<>();
        dialog.setTitle("₊˚⊹ Add New Flight");
        dialog.setHeaderText("Enter Flight Details");

        Label flightNumberLabel = new Label("₊ ⊹ Flight Number:");
        flightNumberLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        flightNumberLabel.setTextFill(Color.WHITE);
        TextField flightNumberField = new TextField();
        flightNumberField.setStyle("-fx-background-color: #e9c46a");
        
        Label originLabel = new Label("₊ ⊹ Origin:");
        originLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        originLabel.setTextFill(Color.WHITE);
        TextField originField = new TextField();
        originField.setStyle("-fx-background-color: #e9c46a");
                
        Label destinationLabel = new Label("₊ ⊹ Destination:");
        destinationLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        destinationLabel.setTextFill(Color.WHITE);
        TextField destinationField = new TextField();
        destinationField.setStyle("-fx-background-color: #e9c46a");
        
        Label seatsLabel = new Label("₊ ⊹ Total Seats:");
        seatsLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        seatsLabel.setTextFill(Color.WHITE);
        TextField seatsField = new TextField();
        seatsField.setStyle("-fx-background-color: #e9c46a");

        VBox vbox = new VBox(10, flightNumberLabel, flightNumberField, originLabel, originField, destinationLabel, destinationField, seatsLabel, seatsField);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setStyle("-fx-background-color: #2a9d8f;");
        dialog.getDialogPane().setContent(vbox);

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return new Flight(flightNumberField.getText(), originField.getText(), destinationField.getText(), Integer.parseInt(seatsField.getText()));
            }
            return null;
        });

        dialog.showAndWait().ifPresent(flight -> {
            admin.addFlight(flight, flights);
            showAlert("Flight added successfully: " + flight.getFlightNumber());
        });
    }

    private void removeFlight() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("₊˚⊹ Remove Flight");
        dialog.setHeaderText("Enter Flight Number to Remove");
        dialog.setContentText("Flight Number:");
        

        dialog.showAndWait().ifPresent(flightNumber -> {
            admin.removeFlight(flightNumber, flights);
            showAlert("Flight removed successfully: " + flightNumber);
        });
    }

    private void viewFlights() {
        TableView<Flight> flightTable = new TableView<>();

        TableColumn<Flight, String> flightNumberColumn = new TableColumn<>("Flight Number");
        flightNumberColumn.setCellValueFactory(cellData -> cellData.getValue().flightNumberProperty());

        TableColumn<Flight, String> originColumn = new TableColumn<>("Origin");
        originColumn.setCellValueFactory(cellData -> cellData.getValue().originProperty());

        TableColumn<Flight, String> destinationColumn = new TableColumn<>("Destination");
        destinationColumn.setCellValueFactory(cellData -> cellData.getValue().destinationProperty());

        TableColumn<Flight, Integer> seatsAvailableColumn = new TableColumn<>("Seats Available");
        seatsAvailableColumn.setCellValueFactory(cellData -> cellData.getValue().seatsAvailableProperty().asObject());

        flightTable.getColumns().addAll(flightNumberColumn, originColumn, destinationColumn, seatsAvailableColumn);
        flightTable.setItems(flights);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showFlightManagement());

        VBox vbox = new VBox(10, flightTable, backButton);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setStyle("-fx-background-color: #8B0000;");

        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}