import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Driver {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int salir = 0;
        int opcion = 0;

        String csvFilePath = "/Jugadores.csv";
        try (BufferedReader csvReader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = csvReader.readLine()) != null) {
                String[] data = line.split(",");
                for (String datum : data) {
                    System.out.print(datum + " ");
                }
                System.out.println(); // Salto de línea para cada fila
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }

        while (salir != 0) {
            printMenu();

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("");
                System.out.println("Ingrese un número.");
                scanner.nextLine();
            }

            switch (opcion) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("// Lógica para la opción 3");
                    break;
                case 4:
                    System.out.println("// Lógica para la opción 4");
                    break;
                case 5:
                    salir = 0;
                    System.out.println("// Salir del bucle");
                    break;
                case 0:
                    continue;
                default:
                    System.out.println("");
                    System.out.println("Número inválido. Intente nuevamente.");
                    break;
            }

            opcion = 0;
        }
    }

    public static void printMenu() {
        System.out.println("");
        System.out.println("**************************************");
        System.out.println("                Menú");
        System.out.println("**************************************");
        System.out.println("Ingrese la opción que desee:");
        System.out.println("1: Mostrar lista de jugadores inscritos");
        System.out.println("2: Mostrar los mejores líberos");
        System.out.println("3: Mostrar la cantidad de pasadores con más de un 80% de efectividad");
        System.out.println("4: Salir");
        System.out.println("");
    }
}
