/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Mantenedores;

import Controladores.Mantenedores.Nucleo.Controlador;
import Dto.ProductoDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 */
public class ControladorProductoDAO extends Controlador {

    public int getID() {
        int id = 1;
        try {
            String sql = "SELECT (IFNULL(max(idProducto),0)+1) as id FROM producto";
            ResultSet res = conector.getResultSet(sql);

            while (res.next()) {
                try {
                    id = res.getInt("id");

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conector.close();
        }

        return id;
    }
    
    public ProductoDTO getProductoByID(int idProducto) {
        ProductoDTO producto = null;
        try {
            String sql = " SELECT p.idProducto, p.nombreProducto, p.descripcionProducto, p.stock, p.precio, p.idCategoria, c.nombreCategoria "
                    + "FROM producto p JOIN categoria c ON p.idCategoria = c.idCategoria WHERE 1 = 1 AND p.idProducto = " + idProducto + " ORDER BY p.idProducto DESC";//DESC y ASC
            ResultSet res = conector.getResultSet(sql);

            while (res.next()) {
                try {
                    producto = new ProductoDTO();
                    producto.setIdProducto(res.getInt("idProducto"));
                    producto.setNombreProducto(res.getString("nombreProducto"));
                    producto.setDescripcionProducto(res.getString("descripcionProducto"));
                    producto.setStock(res.getInt("stock"));
                    producto.setPrecio(res.getDouble("precio"));
                    producto.setIdCategoria(res.getInt("idCategoria"));
                    producto.setNombreCategoria(res.getString("nombreCategoria"));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conector.close();
        }

        return producto;
    }
    
    public List<ProductoDTO> getProductos(int pagina, int cantidad, String where) {
        ArrayList<ProductoDTO> retorno = new ArrayList<ProductoDTO>();
        
        try {
            String sql = obtenerSqlFinalPaginacion(
                    " SELECT p.idProducto, p.nombreProducto, p.descripcionProducto, p.stock, p.precio, p.idCategoria, c.nombreCategoria "
                    + " FROM producto p JOIN categoria c ON p.idCategoria = c.idCategoria " + where, pagina, cantidad, "p.idProducto DESC");//DESC y ASC
            ResultSet res = conector.getResultSet(sql);

            while (res.next()) {
                try {
                    ProductoDTO producto = new ProductoDTO();
                    producto.setIdProducto(res.getInt("idProducto"));
                    producto.setNombreProducto(res.getString("nombreProducto"));
                    producto.setDescripcionProducto(res.getString("descripcionProducto"));
                    producto.setStock(res.getInt("stock"));
                    producto.setPrecio(res.getDouble("precio"));
                    producto.setIdCategoria(res.getInt("idCategoria"));
                    producto.setNombreCategoria(res.getString("nombreCategoria"));

                    retorno.add(producto);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conector.close();
        }

        return retorno;
    }
    
    public boolean actualizarProducto(ProductoDTO producto) {
        int res = conector.executeUpdate("UPDATE producto SET nombreProducto = ?, descripcionProducto = ?, stock = ?, precio = ? , idCategoria = ? WHERE idProducto = ? ", producto.getNombreProducto(), producto.getDescripcionProducto(), producto.getStock(), producto.getPrecio(),producto.getIdCategoria(), producto.getIdProducto());
        return res == 1;
    }
    
    public boolean eliminarProducto(int idProducto) {
        int res = conector.executeUpdate("DELETE FROM producto WHERE idProducto = ?", idProducto);
        return res == 1;
    }

    public boolean insertarProducto(ProductoDTO producto) {
        int res = conector.executeInsert("INSERT INTO producto (idProducto, nombreProducto, descripcionProducto, stock, precio, idCategoria) VALUES (?,?,?,?,?,?)", producto.getIdProducto(), producto.getNombreProducto(), producto.getDescripcionProducto(),producto.getStock(), producto.getPrecio(),producto.getIdCategoria());
        return res > 0;
    }
    
}
