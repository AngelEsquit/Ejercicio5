import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
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


        Jugador jugador;
        Libero libero;
        Opuesto opuesto;
        Auxiliar auxiliar;
        Pasador pasador;
        
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

                    if (!datos.equals("-") || datos.equals("0")) {
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
                            case 12: // Efectividad
                                efectividad = Integer.parseInt(datos);
                                break;
                            default:
                                break;
                        }
                    }
                }

                switch (posicion.toLowerCase()) { // Crear a los jugadores del CSV
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
                Collections.sort(jugadores, Collections.reverseOrder(comparadorEfectividad)); // Ordenar la lista de jugadores por efectividad
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
                    System.out.println("");
                    break;
                case 2: // Mejores 3 líberos
                    mejoresLiberos(jugadores);
                    break;
                case 3: // Cantidad de pasadores con más de un 80% de efectividad
                    cantidadPasadores80(jugadores);
                    break;
                case 4: // Inscribir un jugador
                    System.out.println("");
                    posicion = posicionJugador(scanner);
                    jugador = preguntasDatos(scanner, posicion);
                    jugadores.add(jugador);
                    Comparator<Jugador> comparadorEfectividad = Comparator.comparing(Jugador::getEfectividad);
                    Collections.sort(jugadores, Collections.reverseOrder(comparadorEfectividad)); // Ordenar la lista de jugadores por efectividad
                    break;
                case 5: // Salir
                    String csvFilePath2 = "datos.csv"; // Ruta del archivo CSV

                    try (FileWriter writer = new FileWriter(csvFilePath2)) {
                        writer.append("nombre;posicion;pais;errores;aces;total_servicios;ataques;bloqueos_efectivos;bloqueos_fallidos;pases;fintas;recibos;efectividad\n"); // Escribir encabezados

                        for (Jugador jugadorG : jugadores) {
                            if (jugadorG instanceof Libero) {
                                libero = (Libero) jugadorG;
                                writer.append(libero.getNombre() + ";libero;" + libero.getPais() + ";" + libero.getErrores() + ";" + libero.getAces() + ";" + libero.getTotal_servicios() + ";-;-;-;-;-;" + libero.getRecibos() + ";" + libero.getEfectividad() + "\n");
                            }

                            else if (jugadorG instanceof Opuesto) {
                                opuesto = (Opuesto) jugadorG;
                                writer.append(opuesto.getNombre() + ";opuesto;" + opuesto.getPais() + ";" + opuesto.getErrores() + ";" + opuesto.getAces() + ";" + opuesto.getTotal_servicios() + ";" + opuesto.getAtaques()+ ";" + opuesto.getBloqueos_efectivos() + ";" + opuesto.getBloqueos_fallidos() + ";-;-;-;" + opuesto.getEfectividad() + "\n");
                            }

                            else if (jugadorG instanceof Pasador) {
                                pasador = (Pasador) jugadorG;
                                writer.append(pasador.getNombre() + ";pasador;" + pasador.getPais() + ";" + pasador.getErrores() + ";" + pasador.getAces() + ";" + pasador.getTotal_servicios() + ";-;-;-;" + pasador.getPases() + ";" + pasador.getFintas() + ";-;" + pasador.getEfectividad() + "\n");
                            }

                            else if (jugadorG instanceof Auxiliar) {
                                auxiliar = (Auxiliar) jugadorG;
                                writer.append(auxiliar.getNombre() + ";auxiliar;" + auxiliar.getPais() + ";" + auxiliar.getErrores() + ";" + auxiliar.getAces() + ";" + auxiliar.getTotal_servicios() + ";" + auxiliar.getAtaques()+ ";" + auxiliar.getBloqueos_efectivos() + ";" + auxiliar.getBloqueos_fallidos() + ";-;-;-;" + auxiliar.getEfectividad() + "\n");
                            }
                        }     
                        System.out.println("");       
                        System.out.println("Datos guardados en " + csvFilePath2);
                    }
                    
                    catch (IOException e) {
                        System.err.println("Error al guardar el archivo CSV: " + e.getMessage());
                    }

                    salir = false;
                    System.out.println("Hasta pronto :)");
                    System.out.println("");
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
        System.out.println("4: Inscribir un nuevo jugador");
        System.out.println("5: Salir");
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

    public static void mejoresLiberos(ArrayList<Jugador> jugadores) {
        int n = 1;
        System.out.println("");
        for (Jugador jugador : jugadores) {
            if (jugador instanceof Libero) {
                if (n < 4) {
                    System.out.println(n + ": " + jugador.toString());
                    n += 1;
                }
            }
        }
    }

    public static void cantidadPasadores80(ArrayList<Jugador> jugadores) {
        int n = 0;
        System.out.println("");
        for (Jugador jugador : jugadores) {
            if (jugador instanceof Pasador) {
                if (jugador.getEfectividad() > 80) {
                    n += 1;
                }
            }
        }

        if (n == 1) {
            System.out.println("Hay "+ n + " pasador con efectividad mayor a 80%");
        }

        else {
            System.out.println("Hay "+ n + " pasadores con efectividad mayor a 80%");
        }
    }

    public static Jugador preguntasDatos(Scanner scanner, String posicion) {
        String nombre = "";
        String posicionN = "";
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
        Jugador jugador = null;
        
        switch (posicion.toLowerCase()) {
            case "libero":
                System.out.println("");
                nombre = nombreJugador(scanner);
                System.out.println("");
                posicionN = "libero";
                pais = paisJugador(scanner);
                System.out.println("");
                errores = erroresJugador(scanner);
                System.out.println("");
                aces = acesJugador(scanner);
                System.out.println("");
                total_servicios = totalServiciosJugador(scanner);
                System.out.println("");
                recibos = recibosJugador(scanner);
                System.out.println("");
                efectividad = ((((recibos - errores) * 100) / (recibos + errores)) + (aces * 100) / total_servicios);
                jugador = new Libero(nombre, pais, errores, aces, total_servicios, efectividad, recibos);
                break;
            case "opuesto":
                System.out.println("");
                nombre = nombreJugador(scanner);
                posicionN = "opuesto";
                System.out.println("");
                pais = paisJugador(scanner);
                System.out.println("");
                errores = erroresJugador(scanner);
                System.out.println("");
                aces = acesJugador(scanner);
                System.out.println("");
                total_servicios = totalServiciosJugador(scanner);
                System.out.println("");
                ataques = ataquesJugador(scanner);
                System.out.println("");
                bloqueos_efectivos = bloqueosEfectivosJugador(scanner);
                System.out.println("");
                bloqueos_fallidos = bloqueosFallidosJugador(scanner);
                System.out.println("");
                efectividad = ((((ataques + bloqueos_efectivos - bloqueos_fallidos - errores) * 100) / (ataques + bloqueos_efectivos + bloqueos_fallidos + errores)) + (aces * 100) / total_servicios);
                jugador = new Opuesto(nombre, pais, errores, aces, total_servicios, efectividad, ataques, bloqueos_efectivos, bloqueos_fallidos);
                break;
            case "pasador":
                System.out.println("");
                nombre = nombreJugador(scanner);
                System.out.println("");
                posicionN = "pasador";
                pais = paisJugador(scanner);
                System.out.println("");
                errores = erroresJugador(scanner);
                System.out.println("");
                aces = acesJugador(scanner);
                System.out.println("");
                total_servicios = totalServiciosJugador(scanner);
                System.out.println("");
                pases = pasesJugador(scanner);
                System.out.println("");
                fintas = fintasJugador(scanner);
                System.out.println("");
                efectividad = ((((pases + fintas - errores) * 100) / (pases + fintas + errores)) + (aces * 100) / total_servicios);
                jugador = new Pasador(nombre, pais, errores, aces, total_servicios, efectividad, pases, fintas);
                break;
            case "auxiliar":
                System.out.println("");
                nombre = nombreJugador(scanner);
                System.out.println("");
                posicionN = "auxiliar";
                pais = paisJugador(scanner);
                System.out.println("");
                errores = erroresJugador(scanner);
                System.out.println("");
                aces = acesJugador(scanner);
                System.out.println("");
                total_servicios = totalServiciosJugador(scanner);
                System.out.println("");
                ataques = ataquesJugador(scanner);
                System.out.println("");
                bloqueos_efectivos = bloqueosEfectivosJugador(scanner);
                System.out.println("");
                bloqueos_fallidos = bloqueosFallidosJugador(scanner);
                System.out.println("");
                efectividad = ((((ataques + bloqueos_efectivos - bloqueos_fallidos - errores) * 100) / (ataques + bloqueos_efectivos + bloqueos_fallidos + errores)) + (aces * 100) / total_servicios);
                jugador = new Auxiliar(nombre, pais, errores, aces, total_servicios, efectividad, ataques, bloqueos_efectivos, bloqueos_fallidos);
                break;
            default:
                System.out.println("Esa posición no exite");
        }

        return jugador;
    }

    public static String posicionJugador(Scanner scanner) {
        System.out.println("Ingrese la posición del jugador");
        String posicion = scanner.nextLine().toLowerCase();
        return posicion;
    }
    
    public static String nombreJugador(Scanner scanner) {
        System.out.println("Ingrese el nombre del jugador");
        String nombre = scanner.nextLine().toLowerCase();
        return nombre;
    }

    public static String paisJugador(Scanner scanner) {
        System.out.println("Ingrese el país del jugador");
        String pais = scanner.nextLine().toLowerCase();
        return pais;
    }

    public static int erroresJugador(Scanner scanner) {
        int errores = -1;
        boolean salir = true;

        while (salir) {
            try {
                System.out.println("Ingrese la cantidad de errores del jugador");
                errores = scanner.nextInt();
                scanner.nextLine();
            }
            
            catch (InputMismatchException e) {
                System.out.println("");
                System.out.println("Ingrese un número.");
                scanner.nextLine();
            }

            salir = false;
        }
        
        return errores;
    }

    public static int acesJugador(Scanner scanner) {
        int aces = -1;
        boolean salir = true;

        while (salir) {
            try {
                System.out.println("Ingrese la cantidad de aces del jugador");
                aces = scanner.nextInt();
                scanner.nextLine();
            }
            
            catch (InputMismatchException e) {
                System.out.println("");
                System.out.println("Ingrese un número.");
                scanner.nextLine();
            }

            salir = false;
        }

        return aces;
    }

    public static int totalServiciosJugador(Scanner scanner) {
        int total_servicios = -1;
        boolean salir = true;

        while (salir) {
            try {
                System.out.println("Ingrese la cantidad de servicios del jugador");
                total_servicios = scanner.nextInt();
                scanner.nextLine();
            }
            
            catch (InputMismatchException e) {
                System.out.println("");
                System.out.println("Ingrese un número.");
                scanner.nextLine();
            }

            salir = false;
        }

        return total_servicios;
    }

    public static int ataquesJugador(Scanner scanner) {
        int ataques = -1;
        boolean salir = true;

        while (salir) {
            try {
                System.out.println("Ingrese la cantidad de ataques del jugador");
                ataques = scanner.nextInt();
                scanner.nextLine();
            }
            
            catch (InputMismatchException e) {
                System.out.println("");
                System.out.println("Ingrese un número.");
                scanner.nextLine();
            }

            salir = false;
        }

        return ataques;
    }

    public static int bloqueosEfectivosJugador(Scanner scanner) {
        int bloqueos_efectivos = -1;
        boolean salir = true;

        while (salir) {
            try {
                System.out.println("Ingrese la cantidad de bloqueos efectivos del jugador");
                bloqueos_efectivos = scanner.nextInt();
                scanner.nextLine();
            }
            
            catch (InputMismatchException e) {
                System.out.println("");
                System.out.println("Ingrese un número.");
                scanner.nextLine();
            }

            salir = false;
        }

        return bloqueos_efectivos;
    }

    public static int bloqueosFallidosJugador(Scanner scanner) {
        int bloqueos_fallidos = -1;
        boolean salir = true;

        while (salir) {
            try {
                System.out.println("Ingrese la cantidad de bloqueos fallidos del jugador");
                bloqueos_fallidos = scanner.nextInt();
                scanner.nextLine();
            }
            
            catch (InputMismatchException e) {
                System.out.println("");
                System.out.println("Ingrese un número.");
                scanner.nextLine();
            }

            salir = false;
        }

        return bloqueos_fallidos;
    }

    public static int pasesJugador(Scanner scanner) {
        int pases = -1;
        boolean salir = true;

        while (salir) {
            try {
                System.out.println("Ingrese la cantidad de pases del jugador");
                pases = scanner.nextInt();
                scanner.nextLine();
            }
            
            catch (InputMismatchException e) {
                System.out.println("");
                System.out.println("Ingrese un número.");
                scanner.nextLine();
            }

            salir = false;
        }

        return pases;
    }

    public static int fintasJugador(Scanner scanner) {
        int fintas = -1;
        boolean salir = true;

        while (salir) {
            try {
                System.out.println("Ingrese la cantidad de fintas del jugador");
                fintas = scanner.nextInt();
                scanner.nextLine();
            }
            
            catch (InputMismatchException e) {
                System.out.println("");
                System.out.println("Ingrese un número.");
                scanner.nextLine();
            }

            salir = false;
        }

        return fintas;
    }

    public static int recibosJugador(Scanner scanner) {
        int recibos = -1;
        boolean salir = true;

        while (salir) {
            try {
                System.out.println("Ingrese la cantidad de recibos del jugador");
                recibos = scanner.nextInt();
                scanner.nextLine();
            }
            
            catch (InputMismatchException e) {
                System.out.println("");
                System.out.println("Ingrese un número.");
                scanner.nextLine();
            }

            salir = false;
        }

        return recibos;
    }
}
