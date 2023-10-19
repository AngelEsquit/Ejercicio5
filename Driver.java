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
        ArrayList<Jugador> jugadores = new ArrayList<>();
        boolean salir = true;
        int opcion = 0;
        int conteo = -1;

        String nombre = "";
        String posicion = "";
        String pais = "";
        int errores = 0;
        int aces = 0;
        int total_servicios = 0;
        int ataques = 0;
        int bloqueos_efectivos = 0;
        int bloqueos_fallidos = 0;
        int pases = 0;
        int fintas = 0;
        int recibos = 0;
        float efectividad = 0;
        
        boolean primera_fila = true;

        String csvFilePath = "Jugadores.csv";
        try (BufferedReader csvReader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = csvReader.readLine()) != null) {
                if (primera_fila) {
                    primera_fila = false;
                    continue;
                }

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
                    }
                }

                switch (posicion.toLowerCase()) {
                    case "auxiliar":
                        efectividad = ((((ataques + bloqueos_efectivos - bloqueos_fallidos - errores) * 100) / (ataques + bloqueos_efectivos + bloqueos_fallidos + errores)) + (aces * 100) / total_servicios);
                        jugadores.add(new Auxiliar(nombre, pais, errores, aces, total_servicios, efectividad, ataques, bloqueos_efectivos, bloqueos_fallidos));
                        break;
                    case "pasador":
                        efectividad = ((((pases + fintas - errores) * 100) / (pases + fintas + errores)) + (aces * 100) / total_servicios);
                        jugadores.add(new Pasador(nombre, pais, errores, aces, total_servicios, efectividad, pases, fintas));
                        break;
                    case "libero":
                        efectividad = ((((recibos - errores) * 100) / (recibos + errores)) + (aces * 100) / total_servicios);
                        jugadores.add(new Libero(nombre, pais, errores, aces, total_servicios, efectividad, recibos));
                        break;
                    case "opuesto":
                        efectividad = ((((ataques + bloqueos_efectivos - bloqueos_fallidos - errores) * 100) / (ataques + bloqueos_efectivos + bloqueos_fallidos + errores)) + (aces * 100) / total_servicios);
                        jugadores.add(new Opuesto(nombre, pais, errores, aces, total_servicios, efectividad, ataques, bloqueos_efectivos, bloqueos_fallidos));
                        break;
                }

                System.out.println(); // Salto de línea para cada fila
                conteo = 0;
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }

        while (salir) {
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
                    listaJugadores(jugadores);
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("// Lógica para la opción 3");
                    break;
                case 4:
                    salir = false;
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

    public static void listaJugadores(ArrayList<Jugador> jugadores) {
        int n = 1;
        System.out.println("");
        for (Jugador jugador : jugadores) {
            System.out.println(n + ": " + jugador.toString());
        }
    }
}
