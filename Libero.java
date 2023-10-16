public class Libero extends Jugador {
    private int recibos;

    public Libero(String nombre, String pais, int errores, int aces, int total_servicios, float efectividad,
            int recibos) {
        super(nombre, pais, errores, aces, total_servicios, efectividad);
        this.recibos = recibos;
    }

    public int getRecibos() {
        return recibos;
    }

    public void setRecibos(int recibos) {
        this.recibos = recibos;
    }
}
