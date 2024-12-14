import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarFinder {

    static class Car {
        String name;
        String fuelType;
        int price;
        int quantity;

        Car(String name, String fuelType, int price, int quantity) {
            this.name = name;
            this.fuelType = fuelType;
            this.price = price;
            this.quantity = quantity;
        }
    }

    // Function to read the CSV file and return data as a list of Car objects
    static List<Car> readCSV(String filePath) {
        List<Car> cars = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                String fuelType = parts[1];
                int price = Integer.parseInt(parts[2]);
                int quantity = Integer.parseInt(parts[3]);
                cars.add(new Car(name, fuelType, price, quantity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cars;
    }

    // Function to find a close match for the car name within a maximum difference of 2-3 words
    static String findClosestMatch(String carName, List<Car> cars) {
        double maxDifference = 0.6; // Adjust this value for similarity cutoff
        String closestMatch = null;
        for (Car car : cars) {
            if (car.name.equalsIgnoreCase(carName)) {
                return car.name;
            }
            double similarity = similarity(car.name.toLowerCase(), carName.toLowerCase());
            if (similarity > maxDifference) {
                maxDifference = similarity;
                closestMatch = car.name;
            }
        }
        return closestMatch;
    }

    // Function to calculate similarity between two strings
    static double similarity(String s1, String s2) {
        int longer = Math.max(s1.length(), s2.length());
        return (double) (longer - editDistance(s1, s2)) / (double) longer;
    }

    // Function to calculate edit distance between two strings
    static int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0)
                    costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue), costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0)
                costs[s2.length()] = lastValue;
        }
        return costs[s2.length()];
    }

    // Function to display car information based on user input
    static void displayCarInfo(String carName, List<Car> cars) {
        boolean carFound = false;
        for (Car car : cars) {
            if (car.name.equalsIgnoreCase(carName)) {
                carFound = true;
                System.out.println("Car Information:");
                System.out.println("Name: " + car.name);
                System.out.println("Fuel Type: " + car.fuelType);
                System.out.println("Price: " + car.price);
                System.out.println("Available Quantity: " + car.quantity);
                break;
            }
        }
        if (!carFound) {
            String matchedCarName = findClosestMatch(carName, cars);
            if (matchedCarName != null) {
                System.out.println("Did you mean " + matchedCarName + "?");
            } else {
                System.out.println("Car not found.");
            }
        }
    }

    public static void main(String[] args) {
        String filePath = "cars.csv";
        List<Car> cars = readCSV(filePath);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter car name: ");
        String carName = scanner.nextLine();
        displayCarInfo(carName, cars);
        scanner.close();
    }
}
