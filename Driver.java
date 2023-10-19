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
        int conteo = 0;

        String nombre;
        String posicion;
        String pais;
        int errores;
        int aces;
        int total_servicios;
        int ataques;
        int bloqueos_efectivos;
        int bloqueos_fallidos;
        int pases;
        int fintas;
        int recibos;
        float efectividad;

        String csvFilePath = "Jugadores.csv";
        try (BufferedReader csvReader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = csvReader.readLine()) != null) {
                String[] data = line.split(";");
                for (String datos : data) {
                    conteo += 1;

                    if (!datos.equals("-")) {
                        switch (conteo) {
                            case 0: // Nombre
                                nombre = datos;
                                break;
                            case 1: // Posicion
                                posicion = datos;
                                break;
                            case 2: // Pais
                                pais = datos;
                                break;
                            case 3: // Errores
                                errores = Integer.parseInt(datos);
                                break;
                            case 4: // Aces
                                aces = Integer.parseInt(datos);
                                break;
                            case 5: // Total servicios
                                total_servicios = Integer.parseInt(datos);
                                break;
                            case 6: // Ataques
                                ataques = Integer.parseInt(datos);
                                break;
                            case 7: // Bloqueos efectivos
                                bloqueos_efectivos = Integer.parseInt(datos);
                                break;
                            case 8: // Bloqueos fallidos
                                bloqueos_fallidos = Integer.parseInt(datos);
                                break;
                            case 9: // Pases
                                pases = Integer.parseInt(datos);
                                break;
                            case 10: // Fintas
                                fintas = Integer.parseInt(datos);
                                break;
                            case 11: // Recibos
                                recibos = Integer.parseInt(datos);
                                break;
                            default:
                                break;
                        }

                        System.out.print(datos + " ");
                    }
                }
                System.out.println(); // Salto de línea para cada fila
                conteo = 0;
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
