public class Pasador extends Jugador {
    private int pases;
    private int fintas;

    public Pasador(String nombre, String pais, int errores, int aces, int total_servicios, float efectividad,
            int pases, int fintas) {
        super(nombre, pais, errores, aces, total_servicios);
        this.pases = pases;
        this.fintas = fintas;
    }
    
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

    @Override
    public String toString() {
        return "Pasador - " + nombre + " - " + efectividad;
    }
}
