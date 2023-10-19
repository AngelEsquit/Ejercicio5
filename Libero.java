public class Libero extends Jugador {
    // Creación del atributo
    private int recibos;
    
    // Constructor
    public Libero(String nombre, String pais, int errores, int aces, int total_servicios, float efectividad,
            int recibos) {
        super(nombre, pais, errores, aces, total_servicios, efectividad);
        this.recibos = recibos;
    }

    // Getters y setters
    public int getRecibos() {
        return recibos;
    }

    public void setRecibos(int recibos) {
        this.recibos = recibos;
    }

    // toString personalizado para clase hija
    @Override
    public String toString() {
        return nombre + " - Líbero - " + "Efectividad: " + efectividad;
    }
}
