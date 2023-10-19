public class Auxiliar extends Jugador {
    // Creación de los atributos
    private int ataques;
    private int bloqueos_efectivos;
    private int bloqueos_fallidos;
    
    // Constructor
    public Auxiliar(String nombre, String pais, int errores, int aces, int total_servicios, float efectividad,
            int ataques, int bloqueos_efectivos, int bloqueos_fallidos) {
        super(nombre, pais, errores, aces, total_servicios, efectividad);
        this.ataques = ataques;
        this.bloqueos_efectivos = bloqueos_efectivos;
        this.bloqueos_fallidos = bloqueos_fallidos;
    }

    // Getters y setters
    public int getAtaques() {
        return ataques;
    }

    public void setAtaques(int ataques) {
        this.ataques = ataques;
    }

    public int getBloqueos_efectivos() {
        return bloqueos_efectivos;
    }

    public void setBloqueos_efectivos(int bloqueos_efectivos) {
        this.bloqueos_efectivos = bloqueos_efectivos;
    }

    public int getBloqueos_fallidos() {
        return bloqueos_fallidos;
    }

    public void setBloqueos_fallidos(int bloqueos_fallidos) {
        this.bloqueos_fallidos = bloqueos_fallidos;
    }

    // toString personalizado para clase hija
    @Override
    public String toString() {
        return nombre + " - Auxiliar - " + "Efectividad: " + efectividad;
    }
}
