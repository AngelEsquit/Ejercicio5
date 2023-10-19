public class Pasador extends Jugador {
    // Creación de los atributos
    private int pases;
    private int fintas;

    // Constructor
    public Pasador(String nombre, String pais, int errores, int aces, int total_servicios, float efectividad,
            int pases, int fintas) {
        super(nombre, pais, errores, aces, total_servicios, efectividad);
        this.pases = pases;
        this.fintas = fintas;
    }
    
    // Getters y setters
    public int getPases() {
        return pases;
    }

    public void setPases(int pases) {
        this.pases = pases;
    }

    public int getFintas() {
        return fintas;
    }

    public void setFintas(int fintas) {
        this.fintas = fintas;
    }

    // toString personalizado para clase hija
    @Override
    public String toString() {
        return nombre + " - Pasador - " + "Efectividad: " + efectividad;
    }
}
