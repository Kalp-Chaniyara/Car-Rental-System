import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarQuantityUpdater {
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

    public static void main(String[] args) {
        String filePath = "cars.csv";
        List<Car> cars = readCSV(filePath);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter car name: ");
        String carName = scanner.nextLine();
        updateQuantity(carName, cars);
        writeCSV(filePath, cars);
        scanner.close();
        System.out.println("Quantity updated successfully.");
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

    // Function to update the quantity of a car in the list and delete if quantity is zero
    static void updateQuantity(String carName, List<Car> cars) {
        for (Car car : cars) {
            if (car.name.equalsIgnoreCase(carName)) {
                if (car.quantity > 0) {
                    car.quantity--;
                    if (car.quantity == 0) {
                        cars.remove(car);
                        System.out.println("Car removed from the list as quantity became zero.");
                    }
                } else {
                    System.out.println("Quantity for this car is already zero. Cannot update further.");
                }
                return;
            }
        }
        System.out.println("Car not found in the list.");
    }

    // Function to write the updated list of cars back to the CSV file
    static void writeCSV(String filePath, List<Car> cars) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Car car : cars) {
                writer.write(String.format("%s,%s,%d,%d%n", car.name, car.fuelType, car.price, car.quantity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
