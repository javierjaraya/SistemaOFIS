/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Mantenedores;

import Controladores.Mantenedores.Nucleo.Controlador;
import Dto.CompraDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 */
public class ControladorCompraDAO extends Controlador {

    public int getID() {
        int id = 1;
        try {
            String sql = "SELECT (IFNULL(max(idCompra),0)+1) as id FROM compra";
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

    public int getMontoTotalCompra(int idCompra) {
        int total = 0;
        try {
            String sql = "SELECT sum(cantidad*precio) as total FROM detalle_compra WHERE idCompra = " + idCompra;
            ResultSet res = conector.getResultSet(sql);

            while (res.next()) {
                try {
                    total = res.getInt("total");

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conector.close();
        }

        return total;
    }

    public CompraDTO getCompraByIdCompra(int idCompra) {
        CompraDTO compra = null;
        try {
            String sql = " SELECT * FROM compra c WHERE 1 = 1 AND c.idCompra = " + idCompra + " ORDER BY c.idCompra DESC";//DESC y ASC
            ResultSet res = conector.getResultSet(sql);

            while (res.next()) {
                try {
                    compra = new CompraDTO();
                    compra.setIdCompra(res.getInt("idCompra"));
                    compra.setFechaCompra(res.getDate("fechaCompra"));
                    compra.setEstado(res.getString("estado"));
                    compra.setMetodoDespacho(res.getString("metodoDespacho"));
                    compra.setDireccion(res.getString("direccionDespacho"));
                    compra.setPersonaRetira(res.getString("personaRetira"));
                    compra.setRun(res.getString("run"));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conector.close();
        }

        return compra;
    }

    public List<CompraDTO> getCompras(int pagina, int cantidad, String where) {
        ArrayList<CompraDTO> retorno = new ArrayList<CompraDTO>();
        try {
            String sql = obtenerSqlFinalPaginacion(
                    " SELECT * FROM compra c " + where, pagina, cantidad, "c.idCompra DESC");//DESC y ASC
            ResultSet res = conector.getResultSet(sql);

            while (res.next()) {
                try {
                    CompraDTO compra = new CompraDTO();
                    compra.setIdCompra(res.getInt("idCompra"));
                    compra.setFechaCompra(res.getDate("fechaCompra"));
                    compra.setEstado(res.getString("estado"));
                    compra.setMetodoDespacho(res.getString("metodoDespacho"));
                    compra.setDireccion(res.getString("direccionDespacho"));
                    compra.setPersonaRetira(res.getString("personaRetira"));
                    compra.setRun(res.getString("run"));

                    retorno.add(compra);

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

    public boolean actualizarCompra(CompraDTO compra) {
        int res = conector.executeUpdate("UPDATE compra SET fechaCompra = ?, estado = ?, metodoDespacho = ?, direccionDespacho = ?, personaRetira = ?, run = ?  WHERE idCompra = ? ", compra.getFechaCompra(), compra.getEstado(), compra.getMetodoDespacho(), compra.getDireccion(), compra.getPersonaRetira(), compra.getRun());
        return res == 1;
    }

    public boolean eliminarCompra(int idCompra) {
        int res = conector.executeUpdate("DELETE FROM compra WHERE idCompra = ?", idCompra);
        return res == 1;
    }

    public boolean insertarCompra(CompraDTO compra) {
        int res = conector.executeInsert("INSERT INTO compra ( fechaCompra, estado, metodoDespacho, direccionDespacho, personaRetira, run) VALUES (now(),?,?,?,?,?)", compra.getEstado(), compra.getMetodoDespacho(), compra.getDireccion(), compra.getPersonaRetira(), compra.getRun());
        return res > 0;
    }
}
