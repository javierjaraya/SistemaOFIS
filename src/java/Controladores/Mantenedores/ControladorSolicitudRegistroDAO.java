/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Mantenedores;

import Controladores.Mantenedores.Nucleo.Controlador;
import Dto.SolicitudRegistroDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 */
public class ControladorSolicitudRegistroDAO extends Controlador {
    
    public SolicitudRegistroDTO getSolicitudRegistroByIdSolicitud(int idSolicitud) {
        SolicitudRegistroDTO solicitud = null;
        try {
            String sql = " SELECT * FROM solicitudRegistro s WHERE 1 = 1 AND s.idSolicitud = " + idSolicitud + " ORDER BY s.idSolicitud DESC";//DESC y ASC
            ResultSet res = conector.getResultSet(sql);

            while (res.next()) {
                try {
                    solicitud = new SolicitudRegistroDTO();
                    solicitud.setIdSolicitud(res.getInt("idSolicitud"));
                    solicitud.setRun(res.getString("run"));
                    solicitud.setFechaSolicitud(res.getDate("fechaSolicitud"));                    

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conector.close();
        }

        return solicitud;
    }
    
    public List<SolicitudRegistroDTO> getSolicitudesRegistro(int pagina, int cantidad, String where) {
        ArrayList<SolicitudRegistroDTO> retorno = new ArrayList<SolicitudRegistroDTO>();
        
        try {
            String sql = obtenerSqlFinalPaginacion(
                    " SELECT * FROM solicitudRegistro s " + where, pagina, cantidad, "s.idSolicitud DESC");//DESC y ASC
            ResultSet res = conector.getResultSet(sql);

            while (res.next()) {
                try {
                    SolicitudRegistroDTO solicitud = new SolicitudRegistroDTO();
                    solicitud.setIdSolicitud(res.getInt("idSolicitud"));
                    solicitud.setRun(res.getString("run"));
                    solicitud.setFechaSolicitud(res.getDate("fechaSolicitud"));  

                    retorno.add(solicitud);

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
    
    public boolean actualizarSolicitudRegistro(SolicitudRegistroDTO solicitud) {
        int res = conector.executeUpdate("UPDATE solicitudRegistro SET run = ?, fechaSolicitud = ? WHERE idSolicitud = ? ", solicitud.getRun(), solicitud.getFechaSolicitud(), solicitud.getIdSolicitud());
        return res == 1;
    }

    public boolean eliminarSolicitudRegistro(int idSolicitud) {
        int res = conector.executeUpdate("DELETE FROM solicitudRegistro WHERE idSolicitud = ?", idSolicitud);
        return res == 1;
    }

    public boolean insertarSolicitudRegistro(SolicitudRegistroDTO solicitud) {
        int res = conector.executeInsert("INSERT INTO solicitudRegistro (run, fechaSolicitud) VALUES (?, now())", solicitud.getRun());
        return res > 0;
    }
}
