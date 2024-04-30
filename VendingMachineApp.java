import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class VendingMachineApp extends Application {

    private double totalPrice = 0.0; // Variable to hold the total price

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
        
        Label totalLabel = (Label) scene.lookup("#totalLabel");
        Label moneyPaidLabel = (Label) scene.lookup("#moneyPaidLabel");
        Label changeGivenLabel = (Label) scene.lookup("#changeGivenLabel");
        
        // Set event handlers for the buttons
        pretzelsBtn.setOnAction(e -> {
            // Handle pretzels button action
            totalPrice += 1.50; // Increment total price by the price of pretzels
            totalLabel.setText("Total: $" + String.format("%.2f", totalPrice)); // Update total label
        });
        
        sugarCookiesBtn.setOnAction(e -> {
            // Handle sugar cookies button action
            totalPrice += 2.00; // Increment total price by the price of sugar cookies
            totalLabel.setText("Total: $" + String.format("%.2f", totalPrice)); // Update total label
        });
        
        chocolateBarBtn.setOnAction(e -> {
            // Handle chocolate bar button action
            totalPrice += 1.75; // Increment total price by the price of chocolate bar
            totalLabel.setText("Total: $" + String.format("%.2f", totalPrice)); // Update total label
        });
        
        chipsBtn.setOnAction(e -> {
            // Handle chips button action
            totalPrice += 1.25; // Increment total price by the price of chips
            totalLabel.setText("Total: $" + String.format("%.2f", totalPrice)); // Update total label
        });
        
        chocolateChipCookiesBtn.setOnAction(e -> {
            // Handle chocolate chip cookies button action
            totalPrice += 2.25; // Increment total price by the price of chocolate chip cookies
            totalLabel.setText("Total: $" + String.format("%.2f", totalPrice)); // Update total label
        });
        
        sourCandyBtn.setOnAction(e -> {
            // Handle sour candy button action
            totalPrice += 1.50; // Increment total price by the price of sour candy
            totalLabel.setText("Total: $" + String.format("%.2f", totalPrice)); // Update total label
        });
        
        buyBtn.setOnAction(e -> {
            // Handle buy button action
            // Implement buy functionality here
        });
        
        clearSelectionsBtn.setOnAction(e -> {
            // Reset the total price to 0
            totalPrice = 0.0;
            // Update the total label to display the new total
            totalLabel.setText("Total: $" + String.format("%.2f", totalPrice));
        });
        
        cancelBtn.setOnAction(e -> {
            // Handle cancel button action
            // Implement cancel functionality here
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
