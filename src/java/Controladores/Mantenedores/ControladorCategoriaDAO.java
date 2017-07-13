/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Mantenedores;

import Controladores.Mantenedores.Nucleo.Controlador;
import Dto.CategoriaDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author 
 */
public class ControladorCategoriaDAO extends Controlador  {
    
    public CategoriaDTO getCategoriaByIdCategoria(int idCategoria) {
        CategoriaDTO categoria = null;
        try {
            String sql = " SELECT * FROM categoria c WHERE 1 = 1 AND c.idCategoria = " + idCategoria + " ORDER BY c.idCategoria DESC";//DESC y ASC
            ResultSet res = conector.getResultSet(sql);

            while (res.next()) {
                try {
                    categoria = new CategoriaDTO();
                    categoria.setIdCategoria(res.getInt("idCategoria"));
                    categoria.setNombreCategoria(res.getString("nombreCategoria"));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conector.close();
        }

        return categoria;
    }
    
    public List<CategoriaDTO> getCategorias(int pagina, int cantidad, String where) {
        ArrayList<CategoriaDTO> retorno = new ArrayList<CategoriaDTO>();
        
        try {
            String sql = obtenerSqlFinalPaginacion(
                    " SELECT * FROM categoria c " + where, pagina, cantidad, "c.idCategoria DESC");//DESC y ASC
            ResultSet res = conector.getResultSet(sql);

            while (res.next()) {
                try {
                    CategoriaDTO categoria = new CategoriaDTO();
                    categoria.setIdCategoria(res.getInt("idCategoria"));
                    categoria.setNombreCategoria(res.getString("nombreCategoria"));

                    retorno.add(categoria);

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
    
    public boolean actualizarCategoria(CategoriaDTO categoria) {
        int res = conector.executeUpdate("UPDATE categoria SET nombreCategoria = ? WHERE idCategoria = ? ", categoria.getNombreCategoria(), categoria.getIdCategoria());
        return res == 1;
    }

    public boolean eliminarCategoria(int idCategoria) {
        int res = conector.executeUpdate("DELETE FROM categoria WHERE idCategoria = ?", idCategoria);
        return res == 1;
    }

    public boolean insertarCategoria(CategoriaDTO categoria) {
        int res = conector.executeInsert("INSERT INTO categoria (nombreCategoria) VALUES (?)", categoria.getNombreCategoria());
        return res > 0;
    }
}
