package DAO;

import java.sql.*;
import java.util.*;
import MODEL.ModeloPedido;
import conexionBBDD.ConexionBBDD;

public class DaoPedido {

    private ConexionBBDD conexion; // Variable para la conexión

    // Método para crear las tablas
    public void crearTablas() {
        conexion=new ConexionBBDD();
		String burrar = "DROP TABLE IF EXISTS pedidosjoel.pedidosjoel";
        String sql = "CREATE DATABASE IF NOT EXISTS pedidosjoel";
        String sql1 = "CREATE TABLE IF NOT EXISTS pedidosjoel.pedidosjoel (" +
                "ID NUMERIC(6) NOT NULL PRIMARY KEY," +
                "nombre_cliente VARCHAR(255)," +
                "precio DOUBLE," +
                "descripcion_pizza VARCHAR(255)," +
                "punto_recogida VARCHAR(255)," +
                "fecha NUMERIC(8)," +
                "hora NUMERIC(8)" +
                ");";

        try (Connection conn = conexion.getConnection();  // Obtén la conexión
             Statement stmt = conn.createStatement()) {

            // Ejecuta las consultas SQL
			stmt.execute(burrar);
			System.out.println("DELETED.");
            stmt.execute(sql);  // Crear la base de datos
            stmt.execute(sql1); // Crear la tabla
            System.out.println("Tabla 'PEDIDOSJOEL' creada correctamente.");
        } catch (SQLException e) {
            // Captura cualquier error en la creación de la tabla
            System.out.println("Error al crear la tabla 'PEDIDOSJOEL': " + e.getMessage());
        } finally {
            // Cierra la conexión
            conexion.closeConnection();
        }
    }

	public void cargarPedidos(ArrayList<ModeloPedido> lista) {
		conexion = new ConexionBBDD(); // Inicializamos la conexión
        String sql = "INSERT INTO pedidosjoel.pedidosjoel (ID, nombre_cliente, precio, descripcion_pizza, punto_recogida, fecha, hora) VALUES (?, ?, ?, ?, ?, ?, ?)";
     
        try (Connection conn = conexion.getConnection();  // Obtenemos la conexión
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (ModeloPedido mp:lista) {
				pstmt.setInt(1, mp.getId());
				pstmt.setString(2, mp.getNombreCliente());
				pstmt.setDouble(3, mp.getPrecio());
				pstmt.setString(4, mp.getDescripcionPizza());
				pstmt.setString(5, mp.getPuntoRecogida());
				pstmt.setInt(6, mp.getFecha());
				pstmt.setInt(7, mp.getHora());
				pstmt.executeUpdate(); // Ejecuta la inserción para cada pedido
			}
            System.out.println("Pedidos cargados correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar los pedidos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            conexion.closeConnection(); // Cerramos la conexión
        }
	}

    public void selectPedidos() {
		conexion = new ConexionBBDD(); // Inicializamos la conexión
        String sql = "SELECT ID, nombre_cliente, precio, descripcion_pizza, punto_recogida, fecha, hora FROM pedidosjoel.pedidosjoel WHERE punto_recogida = ? AND  fecha = ?";
     
        try (Connection conn = conexion.getConnection();  // Obtenemos la conexión
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(2, 20241012);
				pstmt.setString(1, "Local 1");
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt(1);
                    String nombre_cliente = rs.getString(2);
                    Double precio = rs.getDouble(3);
                    String descripcion_pizza = rs.getString(4);
				    String punto_recogida = rs.getString(5);
                    int fecha = rs.getInt(6);
                    int hora = rs.getInt(7);

                    System.out.println(id + " | " + nombre_cliente + " | " + precio + " | " + descripcion_pizza + " | " + punto_recogida + " | " + fecha + " | " + hora);
                }
            System.out.println("Hecho");
        } catch (SQLException e) {
            System.out.println("Error al insertar los pedidos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            conexion.closeConnection(); // Cerramos la conexión
        }
	}

	public void updatePedidos() {
		conexion = new ConexionBBDD(); // Inicializamos la conexión
		String selectSql = "SELECT ID, precio FROM pedidosjoel.pedidosjoel";
		String updateSql = "UPDATE pedidosjoel.pedidosjoel SET precio = ? WHERE ID = ?";
	
		try (Connection conn = conexion.getConnection();  // Obtenemos la conexión
			 PreparedStatement selectStmt = conn.prepareStatement(selectSql);
			 PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
	
			// Ejecutamos la consulta SELECT
			ResultSet rs = selectStmt.executeQuery();
	
			// Iteramos sobre los resultados del SELECT
			while (rs.next()) {
				int id = rs.getInt(1);
				double precio = rs.getDouble(2);
	
				// Calculamos el nuevo precio sumando el IVA (21%)
				double nuevoPrecio = precio * 1.20;
	
				// Preparamos la actualización
				updateStmt.setDouble(1, nuevoPrecio);
				updateStmt.setInt(2, id);
	
				// Ejecutamos la actualización
				updateStmt.executeUpdate();
	
				System.out.println("Actualizado el precio del pedido con ID=" + id + " al nuevo precio: " + nuevoPrecio);
			}
	
			System.out.println("Pedidos actualizados correctamente.");
	
		} catch (SQLException e) {
			System.out.println("Error al actualizar los pedidos: " + e.getMessage());
			e.printStackTrace();
		} finally {
			conexion.closeConnection(); // Cerramos la conexión
		}
	}

	public void deletePedidos(int dia) {
		conexion = new ConexionBBDD(); // Inicializamos la conexión
        String sql = "DELETE FROM pedidosjoel.pedidosjoel WHERE fecha = ?";
        try (Connection conn = conexion.getConnection();  // Obtenemos la conexión
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, dia);
				pstmt.executeUpdate(); // Ejecuta la inserción para cada pedido
            System.out.println("Pedidos borrados correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar los pedidos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            conexion.closeConnection(); // Cerramos la conexión
        }
	}
}
