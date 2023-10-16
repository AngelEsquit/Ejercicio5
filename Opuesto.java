public class Opuesto {
    private int ataques;
    private int bloqueos_efectivos;
    private int bloqueos_fallidos;

    public Opuesto(int ataques, int bloqueos_efectivos, int bloqueos_fallidos) {
        this.ataques = ataques;
        this.bloqueos_efectivos = bloqueos_efectivos;
        this.bloqueos_fallidos = bloqueos_fallidos;
    }

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
}
