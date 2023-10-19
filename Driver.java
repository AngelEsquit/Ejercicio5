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
        //Creación de variables e instancias para el funcionamiento del programa principal
        Scanner scanner = new Scanner(System.in);
        ArrayList<Jugador> jugadores = new ArrayList<>();
        boolean salir = true;
        int opcion = 0;
        int conteo = -1;
        
        //Creación de variables para guardar los atributos de los jugadores
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
        float efectividad = 1;
        
        // Variable para saltar la primera fila de encabezados
        boolean primera_fila = true;

        String csvFilePath = "Jugadores.csv";
        try (BufferedReader csvReader = new BufferedReader(new FileReader(csvFilePath))) { // Lector de CSV
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
                                System.out.println(pais);
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

                switch (posicion.toLowerCase()) { // 
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

                Comparator<Jugador> comparadorEfectividad = Comparator.comparing(Jugador::getEfectividad);
                Collections.sort(jugadores, Collections.reverseOrder(comparadorEfectividad));
                System.out.println(); // Salto de línea para cada fila
                conteo = -1;
            }
        } catch (IOException e) { // Catch para errores al leer el CSV
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }

        while (salir) { // Ciclo principal del programa
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
                case 1: // Lista de jugadores inscritos
                    listaJugadores(jugadores);
                    break;
                case 2: // Mejores 3 líberos
                    break;
                case 3: // Cantidad de pasadores con más de un 80% de efectividad
                    System.out.println("// Lógica para la opción 3");
                    break;
                case 4: // Salir
                    salir = false;
                    System.out.println("Hasta pronto :)");
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
        System.out.println("");
        for (Jugador jugador : jugadores) {
            System.out.println(n + ": " + jugador.toString());
            n += 1;
        }
    }

    public static void mejoresJugadores(ArrayList<Jugador> jugadores) {
        int n = 1;
        System.out.println("");
        for (Jugador jugador : jugadores) {
            if (jugador instanceof Libero) {
                System.out.println(n + ": " + jugador.toString());
                n += 1;
            }
        }
    }
}
