/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Mantenedores;

import Controladores.Mantenedores.Nucleo.Controlador;
import Dto.ImagenDTO;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author
 */
public class ControladorImagenDAO extends Controlador {

    public ImagenDTO getImagenByIdImagen(int idImagen) {
        ImagenDTO imagen = null;
        try {
            String sql = " SELECT * FROM imagen i WHERE 1 = 1 AND i.idImagen = " + idImagen + " ORDER BY i.idImagen DESC";//DESC y ASC
            ResultSet res = conector.getResultSet(sql);

            while (res.next()) {
                try {
                    imagen = new ImagenDTO();
                    imagen.setIdImagen(res.getInt("idImagen"));
                    imagen.setNombreImagen(res.getString("nombreImagen"));
                    imagen.setRutaImagen(res.getString("rutaImagen"));
                    imagen.setIdProducto(res.getInt("idProducto"));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conector.close();
        }

        return imagen;
    }

    public ImagenDTO getImagenByIdProducto(int idProducto) {
        ImagenDTO imagen = null;
        try {
            String sql = " SELECT * FROM imagen i WHERE 1 = 1 AND i.idProducto = " + idProducto + " ORDER BY i.idProducto DESC";//DESC y ASC
            ResultSet res = conector.getResultSet(sql);

            while (res.next()) {
                try {
                    imagen = new ImagenDTO();
                    imagen.setIdImagen(res.getInt("idImagen"));
                    imagen.setNombreImagen(res.getString("nombreImagen"));
                    imagen.setRutaImagen(res.getString("rutaImagen"));
                    imagen.setIdProducto(res.getInt("idProducto"));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conector.close();
        }

        return imagen;
    }

    public boolean actualizarImagen(ImagenDTO imagen) {
        int res = conector.executeUpdate("UPDATE imagen SET nombreImagen = ?, rutaImagen = ?, idProducto = ? WHERE idImagen = ? ", imagen.getNombreImagen(), imagen.getRutaImagen(), imagen.getIdProducto(), imagen.getIdImagen());
        return res == 1;
    }

    public boolean eliminarImagen(int idImagen) {
        int res = conector.executeUpdate("DELETE FROM imagen WHERE idImagen = ?", idImagen);
        return res == 1;
    }

    public boolean insertarImagen(ImagenDTO imagen) {
        int res = conector.executeInsert("INSERT INTO imagen (idImagen, nombreImagen, rutaImagen, idProducto) VALUES (?,?,?,?)", imagen.getIdImagen(), imagen.getNombreImagen(), imagen.getRutaImagen(), imagen.getIdProducto());
        return res > 0;
    }
}
