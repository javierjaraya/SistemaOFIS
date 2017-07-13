/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Mantenedores;

import Controladores.Mantenedores.Nucleo.Controlador;
import Dto.PerfilDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 
 */
public class ControladorPerfilDAO extends Controlador {
    
    public PerfilDTO getPerfilByIdPerfil(int idPerfil) {
        PerfilDTO perfil = null;
        try {
            String sql = " SELECT * FROM perfil p WHERE 1 = 1 AND p.idPerfil = " + idPerfil + " ORDER BY p.idPerfil DESC";//DESC y ASC
            ResultSet res = conector.getResultSet(sql);

            while (res.next()) {
                try {
                    perfil = new PerfilDTO();
                    perfil.setIdPerfil(res.getInt("idPerfil"));
                    perfil.setNombrePerfil(res.getString("nombrePerfil"));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conector.close();
        }

        return perfil;
    }
    
    public List<PerfilDTO> getPerfiles(int pagina, int cantidad, String where) {
        ArrayList<PerfilDTO> retorno = new ArrayList<PerfilDTO>();
        
        try {
            String sql = obtenerSqlFinalPaginacion(
                    " SELECT * FROM perfil p " + where, pagina, cantidad, "p.idPerfil DESC");//DESC y ASC
            ResultSet res = conector.getResultSet(sql);

            while (res.next()) {
                try {
                    PerfilDTO perfil = new PerfilDTO();
                    perfil.setIdPerfil(res.getInt("idPerfil"));
                    perfil.setNombrePerfil(res.getString("nombrePerfil"));

                    retorno.add(perfil);

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
    
    public boolean actualizarPerfil(PerfilDTO perfil) {
        int res = conector.executeUpdate("UPDATE perfil SET nombrePerfil = ? WHERE idPerfil = ? ", perfil.getNombrePerfil(), perfil.getIdPerfil());
        return res == 1;
    }

    public boolean eliminarPerfil(int idPerfil) {
        int res = conector.executeUpdate("DELETE FROM perfil WHERE idPerfil = ?", idPerfil);
        return res == 1;
    }

    public boolean insertarPerfil(PerfilDTO perfil) {
        int res = conector.executeInsert("INSERT INTO perfil (nombrePerfil) VALUES (?)", perfil.getNombrePerfil());
        return res > 0;
    }
}
