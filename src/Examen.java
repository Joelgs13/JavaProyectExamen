import DAO.DaoPedido;
import java.io.*;
import java.util.*;
import MODEL.ModeloPedido;

public class Examen {
    public static void main(String[] args) throws Exception {
        //System.out.println("Hello, World!");
        DaoPedido examen = new DaoPedido();
        examen.crearTablas();

        String archivoCsv = "C:\\Users\\HP\\Desktop\\DM2\\ADAT\\EXAMENES\\T3\\EXAMENJoel\\JavaProyectExamen\\src\\FICHEROS\\pedidos_riomaiore.csv";
        
        ArrayList<ModeloPedido> pedidos = leerCSV(archivoCsv);
        examen.cargarPedidos(pedidos);
        examen.selectPedidos();
        examen.updatePedidos();
        examen.deletePedidos(20240922);
    }

    // Método para leer un archivo CSV
    public static ArrayList<ModeloPedido> leerCSV(String archivoCsv) {
        String linea = "";
        ArrayList<ModeloPedido> pedidos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCsv))) {
            // Leer la primera línea (cabeceras) y omitirla si es necesario
            br.readLine(); // Si no necesitas las cabeceras, puedes omitir esta línea

            // Leer cada línea del archivo CSV
            while ((linea = br.readLine()) != null) {
                // Separar los datos por el separador (en este caso, coma)
                String[] datos = linea.split(",");

                // Acceder a cada campo del CSV

                /*int id = Integer.parseInt(datos[0]);
                String nombreCliente = datos[1];
                double precio = Double.parseDouble(datos[2]);
                String descripcionPizza = datos[3];
                String puntoRecogida = datos[4];
                String fecha = datos[5];
                String hora = datos[6];
                */

                pedidos.add(new ModeloPedido(Integer.parseInt(datos[0]), datos[1], Double.parseDouble(datos[2]), datos[3], datos[4], Integer.parseInt(datos[5]), Integer.parseInt(datos[6])));
                /*System.out.println("Pedido: ID=" + id + ", Cliente=" + nombreCliente +
                        ", Precio=" + precio + ", Pizza=" + descripcionPizza +
                        ", Punto de recogida=" + puntoRecogida + ", Fecha=" + fecha + ", Hora=" + hora);*/
            }
            return pedidos;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } 
    }
}
