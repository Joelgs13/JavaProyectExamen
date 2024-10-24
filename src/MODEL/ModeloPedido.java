package MODEL;
public class ModeloPedido {

    private int id;
    private String nombreCliente;
    private Double precio;
    private String descripcionPizza;
    private String puntoRecogida;
    private int fecha;
    private int hora;

    public ModeloPedido(int id, String nombreCliente, Double precio, String descripcionPizza, String puntoRecogida, int fecha, int hora) {
        this.id=id;
        this.nombreCliente = nombreCliente;
        this.precio = precio;
        this.descripcionPizza = descripcionPizza;
        this.puntoRecogida = puntoRecogida;
        this.fecha = fecha;
        this.hora = hora;
    }
    public int getId() {
        return id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public Double getPrecio() {
        return precio;
    }

    public String getDescripcionPizza() {
        return descripcionPizza;
    }

    public String getPuntoRecogida() {
        return puntoRecogida;
    }

    public int getFecha() {
        return fecha;
    }

    public int getHora() {
        return hora;
    }
}
