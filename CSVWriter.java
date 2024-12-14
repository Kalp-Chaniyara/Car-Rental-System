import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
    public static void main(String[] args) {
        String filePath = "cars.csv";
        String newEntry = "Toyota Corolla,G,400000,8";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.newLine(); // Move to a new line
            writer.write(newEntry); // Write the new entry
            System.out.println("New entry added to the CSV file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
