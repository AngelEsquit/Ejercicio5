public class Jugador {
    protected String nombre;
    protected String pais;
    protected int errores;
    protected int aces;
    protected int total_servicios;
    protected float efectividad;

    public Jugador(String nombre, String pais, int errores, int aces, int total_servicios, float efectividad) {
        this.nombre = nombre;
        this.pais = pais;
        this.errores = errores;
        this.aces = aces;
        this.total_servicios = total_servicios;
        this.efectividad = efectividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getErrores() {
        return errores;
    }

    public void setErrores(int errores) {
        this.errores = errores;
    }

    public int getAces() {
        return aces;
    }

    public void setAces(int aces) {
        this.aces = aces;
    }

    public int getTotal_servicios() {
        return total_servicios;
    }

    public void setTotal_servicios(int total_servicios) {
        this.total_servicios = total_servicios;
    }

    public float getEfectividad() {
        return efectividad;
    }

    public void setEfectividad(float efectividad) {
        this.efectividad = efectividad;
    }

    @Override
    public String toString() {
        return "Jugador - " + nombre + " - " + efectividad;
    }
}   
