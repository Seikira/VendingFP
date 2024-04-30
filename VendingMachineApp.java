import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javafx.scene.control.Alert;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import javafx.application.Platform; // Import Platform

public class VendingMachineApp extends Application {

    private double totalPrice = 0.0; // Variable to hold the total price
    private Label totalLabel; // Declare totalLabel as a class-level variable

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        Parent root = FXMLLoader.load(getClass().getResource("vending_machine.fxml"));

        // Create a Scene with the loaded FXML layout
        Scene scene = new Scene(root);

        // Set the scene to the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Vending Machine");
        primaryStage.show();

        // Load the total price from file
        loadTotal();

        // Center the "Snack Haven" label horizontally
        GridPane gridPane = (GridPane) root;
        GridPane.setHalignment(gridPane.getChildren().get(0), javafx.geometry.HPos.CENTER);

        // Get references to the buttons and labels
        Button pretzelsBtn = (Button) scene.lookup("#pretzelsBtn");
        Button sugarCookiesBtn = (Button) scene.lookup("#sugarCookiesBtn");
        Button chocolateBarBtn = (Button) scene.lookup("#chocolateBarBtn");
        Button chipsBtn = (Button) scene.lookup("#chipsBtn");
        Button chocolateChipCookiesBtn = (Button) scene.lookup("#chocolateChipCookiesBtn");
        Button sourCandyBtn = (Button) scene.lookup("#sourCandyBtn");

        Button buyBtn = (Button) scene.lookup("#buyBtn");
        Button clearSelectionsBtn = (Button) scene.lookup("#clearSelectionsBtn");
        Button cancelBtn = (Button) scene.lookup("#cancelBtn");

        totalLabel = (Label) scene.lookup("#totalLabel"); // Assign totalLabel

        // Set the initial value of the total label
        totalLabel.setText("Total: $" + String.format("%.2f", totalPrice));

        // Set event handlers for the buttons
        pretzelsBtn.setOnAction(e -> {
            // Handle pretzels button action
            totalPrice += 1.50; // Increment total price by the price of pretzels
            updateTotalLabel();
        });

        sugarCookiesBtn.setOnAction(e -> {
            // Handle sugar cookies button action
            totalPrice += 2.00; // Increment total price by the price of sugar cookies
            updateTotalLabel();
        });

        chocolateBarBtn.setOnAction(e -> {
            // Handle chocolate bar button action
            totalPrice += 1.75; // Increment total price by the price of chocolate bar
            updateTotalLabel();
        });

        chipsBtn.setOnAction(e -> {
            // Handle chips button action
            totalPrice += 1.25; // Increment total price by the price of chips
            updateTotalLabel();
        });

        chocolateChipCookiesBtn.setOnAction(e -> {
            // Handle chocolate chip cookies button action
            totalPrice += 2.25; // Increment total price by the price of chocolate chip cookies
            updateTotalLabel();
        });

        sourCandyBtn.setOnAction(e -> {
            // Handle sour candy button action
            totalPrice += 1.50; // Increment total price by the price of sour candy
            updateTotalLabel();
        });

        buyBtn.setOnAction(e -> {
            // Handle buy button action
            showThankYouMessage();
        });

        clearSelectionsBtn.setOnAction(e -> {
            // Reset the total price to 0
            totalPrice = 0.0;
            // Update the total label to display the new total
            updateTotalLabel();
        });

        cancelBtn.setOnAction(e -> {
            // Close the application
            Platform.exit();
        });

        // Save the total price to file when the application is closed
        primaryStage.setOnCloseRequest(event -> {
            saveTotal();
        });

        // Fetch product details from RapidAPI
        fetchProductDetails();
    }

    // Your other methods remain unchanged...

    // Helper method to update the total label
    private void updateTotalLabel() {
        totalLabel.setText("Total: $" + String.format("%.2f", totalPrice));
    }

    // Helper method to show a thank you message
    private void showThankYouMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thank You!");
        alert.setHeaderText(null);
        alert.setContentText("Thank you for your purchase!");
        alert.showAndWait();
    }

    // Helper method to save the total to a file
    private void saveTotal() {
        try {
            FileWriter writer = new FileWriter("total.txt");
            writer.write(String.valueOf(totalPrice));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to save total to file.");
        }
    }

    // Helper method to load the total from a file
    private void loadTotal() {
        File file = new File("total.txt");
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = reader.readLine();
                totalPrice = Double.parseDouble(line);
                reader.close();
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
                showAlert("Error", "Failed to load total from file.");
            }
        }
    }

    // Define a helper method to show alert dialogs
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void fetchProductDetails() {
        String apiKey = "a6277cd436msheafae34a1045e39p15166ejsn863e6fcd5171";
        try {
            // Create HttpClient
            HttpClient client = HttpClient.newHttpClient();

            // Create HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://producthub-api.p.rapidapi.com/api/products/1"))
                    .header("X-RapidAPI-Key", apiKey)
                    .header("X-RapidAPI-Host", "producthub-api.p.rapidapi.com")
                    .build();

            // Send the request asynchronously and handle the response
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(responseBody -> {
                        // Process the response here
                        System.out.println(responseBody);
                    })
                    .join(); // Wait for the request to complete
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            showAlert("Error", "Failed to fetch product details.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
