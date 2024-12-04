import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try{
            Configuration config = Configuration.promptForConfiguration();
            config.saveToTextFile("config.txt");
            System.out.println("Configuration saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving configuration to file: " + e.getMessage());
        }
    }
}