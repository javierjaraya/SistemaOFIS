/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Mantenedores;

import Controladores.Mantenedores.Nucleo.Controlador;
import Dto.DetalleCompraDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 */
public class ControladorDetalleCompraDAO extends Controlador {

    public DetalleCompraDTO getDetalleCompraByIdDetalleCompra(int idDetalle) {
        DetalleCompraDTO detalle = null;
        try {
            String sql = " SELECT dc.idDetalle, dc.idCompra, dc.idProducto, dc.precio, dc.cantidad, p.nombreProducto FROM detalle_compra as dc join producto as p on p.idProducto = dc.idProducto where dc.idCompra  WHERE 1 = 1 AND dc.idDetalle = " + idDetalle + " ORDER BY dc.idDetalle DESC";//DESC y ASC
            ResultSet res = conector.getResultSet(sql);

            while (res.next()) {
                try {
                    detalle = new DetalleCompraDTO();
                    detalle.setIdDetalle(res.getInt("idDetalle"));
                    detalle.setIdCompra(res.getInt("idCompra"));
                    detalle.setIdProducto(res.getInt("idProducto"));
                    detalle.setPrecio(res.getDouble("precio"));
                    detalle.setCantidad(res.getInt("cantidad"));
                    detalle.setNombreProducto(res.getString("nombreProducto"));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conector.close();
        }

        return detalle;
    }

    public DetalleCompraDTO getDetalleCompraByIdCompra(int idCompra) {
        DetalleCompraDTO detalle = null;
        try {
            String sql = " SELECT dc.idDetalle, dc.idCompra, dc.idProducto, dc.precio, dc.cantidad, p.nombreProducto FROM detalle_compra as dc join producto as p on p.idProducto = dc.idProducto WHERE 1 = 1 AND dc.idCompra = " + idCompra + " ORDER BY dc.idCompra DESC";//DESC y ASC
            ResultSet res = conector.getResultSet(sql);

            while (res.next()) {
                try {
                    detalle = new DetalleCompraDTO();
                    detalle.setIdDetalle(res.getInt("idDetalle"));
                    detalle.setIdCompra(res.getInt("idCompra"));
                    detalle.setIdProducto(res.getInt("idProducto"));
                    detalle.setPrecio(res.getDouble("precio"));
                    detalle.setCantidad(res.getInt("cantidad"));
                    detalle.setNombreProducto(res.getString("nombreProducto"));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conector.close();
        }

        return detalle;
    }

    public List<DetalleCompraDTO> getDetalleCompraByIdProducto(int idProducto) {
        List<DetalleCompraDTO> retorno = new ArrayList<DetalleCompraDTO>();
        try {
            String sql = " SELECT dc.idDetalle, dc.idCompra, dc.idProducto, dc.precio, dc.cantidad, p.nombreProducto FROM detalle_compra as dc join producto as p on p.idProducto = dc.idProducto where dc.idCompra  WHERE 1 = 1 AND dc.idProducto = " + idProducto + " ORDER BY dc.idProducto DESC";//DESC y ASC
            ResultSet res = conector.getResultSet(sql);

            if (res != null) {
                while (res.next()) {
                    try {
                        DetalleCompraDTO detalle = new DetalleCompraDTO();
                        detalle.setIdDetalle(res.getInt("idDetalle"));
                        detalle.setIdCompra(res.getInt("idCompra"));
                        detalle.setIdProducto(res.getInt("idProducto"));
                        detalle.setPrecio(res.getDouble("precio"));
                        detalle.setCantidad(res.getInt("cantidad"));
                        detalle.setNombreProducto(res.getString("nombreProducto"));
                        
                        retorno.add(detalle);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conector.close();
        }

        return retorno;
    }

    public boolean actualizarDetalleCompra(DetalleCompraDTO detalle) {
        int res = conector.executeUpdate("UPDATE detalle_compra SET idCompra = ?, idProducto = ?, precio = ?, cantidad = ?  WHERE idDetalle = ? ", detalle.getIdCompra(), detalle.getIdProducto(), detalle.getPrecio(), detalle.getCantidad(), detalle.getIdDetalle());
        return res == 1;
    }

    public boolean eliminarDetalleCompra(int idDetalle) {
        int res = conector.executeUpdate("DELETE FROM detalle_compra WHERE idImagen = ?", idDetalle);
        return res == 1;
    }

    public boolean insertarDetalleCompra(DetalleCompraDTO detalle) {
        int res = conector.executeInsert("INSERT INTO detalle_compra (idCompra, idProducto, precio, cantidad) VALUES (?,?,?,?)", detalle.getIdCompra(), detalle.getIdProducto(), detalle.getPrecio(), detalle.getCantidad());
        return res > 0;
    }
}
