import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ProductApiClient {

    public static void main(String[] args) {
        getProductDetails("chips");
    }

    public static void getProductDetails(String productName) {
        try {
            // Create HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://producthub-api.p.rapidapi.com/api/products/" + productName))
                    .header("X-RapidAPI-Key", "a6277cd436msheafae34a1045e39p15166ejsn863e6fcd5171")
                    .header("X-RapidAPI-Host", "producthub-api.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            // Send HTTP request and get response
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            // Print response body
            System.out.println("Response body: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
