import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try{
            // Prompt the user for configuration parameters
            Configuration config = Configuration.promptForConfiguration();
            // Save the configuration to a text file
            config.saveToTextFile("config.txt");
            System.out.println("Configuration saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving configuration to file: " + e.getMessage());
        }
    }
}