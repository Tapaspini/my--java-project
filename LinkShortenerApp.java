import java.util.HashMap;
import java.util.Scanner;

public class LinkShortenerApp {
    private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final HashMap<String, String> urlMapping = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Link Shortener Application");
            System.out.println("1. Shorten URL");
            System.out.println("2. Retrieve Original URL");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    shortenURL(scanner);
                    break;
                case 2:
                    retrieveOriginalURL(scanner);
                    break;
                case 3:
                    System.out.println("Exiting Link Shortener Application. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void shortenURL(Scanner scanner) {
        System.out.print("Enter the long URL: ");
        String longURL = scanner.nextLine();

        String shortURL = generateShortURL(longURL);
        urlMapping.put(shortURL, longURL);

        System.out.println("Shortened URL: " + shortURL);
        System.out.println();
    }

    private static void retrieveOriginalURL(Scanner scanner) {
        System.out.print("Enter the short URL: ");
        String shortURL = scanner.nextLine();

        String longURL = urlMapping.get(shortURL);

        if (longURL != null) {
            System.out.println("Original URL: " + longURL);
        } else {
            System.out.println("Short URL not found in the mapping.");
        }

        System.out.println();
    }

    private static String generateShortURL(String longURL) {
        // Example: Convert a hash of the long URL to base62
        String hash = hashFunction(longURL);
        long decimal = Long.parseLong(hash, 16);
        return convertToBase62(decimal);
    }

    private static String hashFunction(String input) {
        // Implement your hash function (e.g., MD5, SHA-256)
        // Return the hash value as a hexadecimal string
        // For simplicity, using a basic hash for demonstration purposes
        int hashCode = input.hashCode();
        return Integer.toHexString(hashCode);
    }

    private static String convertToBase62(long decimal) {
        StringBuilder shortURL = new StringBuilder();
        while (decimal > 0) {
            shortURL.insert(0, BASE62.charAt((int) (decimal % 62)));
            decimal /= 62;
        }
        return shortURL.toString();
    }
}
