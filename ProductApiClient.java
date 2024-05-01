import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;

/**
 * ProductApiClient class retrieves and updates product details from an API.
 */
public class ProductApiClient {

    /**
     * The main method of the ProductApiClient class.
     * It retrieves and prints details of a product with the given ID.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        getProductDetails(1); // Assuming the product ID is 1
    }

    /**
     * Retrieves and prints details of a product with the given ID.
     * The product details are fetched from an API and then parsed using Gson.
     * The name of the product is set to "chips" and the price is updated to 1.25.
     *
     * @param productId The ID of the product to retrieve.
     */
    public static void getProductDetails(int productId) {
        try {
            // Create HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://producthub-api.p.rapidapi.com/api/products/" + productId))
                    .header("X-RapidAPI-Key", "a6277cd436msheafae34a1045e39p15166ejsn863e6fcd5171")
                    .header("X-RapidAPI-Host", "producthub-api.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            // Send HTTP request and get response
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            // Print the response body
            System.out.println("Response Body: " + response.body());

            // Parse JSON response
            Gson gson = new Gson();
            Product product = gson.fromJson(response.body(), Product.class);

            // Extract data from JSON object
            int id = product.getId();
            // Set the name to "chips"
            String name = "chips";
            double originalPrice = product.getPrice();

            // Modify the price of the product
            double newPrice = 1.25; // New price
            product.setPrice(newPrice);

            // Print the updated product details
            System.out.println("Product ID: " + id);
            System.out.println("Name: " + name);
            System.out.println("Original Price: $" + originalPrice);
            System.out.println("Updated Price: $" + product.getPrice());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
