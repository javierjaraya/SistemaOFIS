
package Controladores.Mantenedores;

import Controladores.Mantenedores.Nucleo.Controlador;
import Dto.UsuarioDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 
 * 
 * Esta clase permita la persitencia con la BD, contiene los metodos update, insert, delete y select de la tabla usuario
 */
public class ControladorUsuarioDAO extends Controlador {

    public UsuarioDTO getUsuarioByRun(String run) {
        UsuarioDTO usuario = null;
        try {
            String sql = " SELECT u.run, u.nombres, u.apellidos, u.correoElectronico, u.telefono, u.sexo, u.direccion, u.clave, u.idPerfil, u.tipoUsuario, u.estado, p.nombrePerfil "
                    + " FROM usuario u "
                    + " INNER JOIN perfil p ON u.idPerfil = p.idPerfil  WHERE 1 = 1 AND u.run = '" + run + "' ORDER BY u.run DESC";//DESC y ASC
            ResultSet res = conector.getResultSet(sql);

            while (res.next()) {
                try {
                    usuario = new UsuarioDTO();
                    usuario.setRun(res.getString("run"));
                    usuario.setNombres(res.getString("nombres"));
                    usuario.setApellidos(res.getString("apellidos"));
                    usuario.setCorreoElectronico(res.getString("correoElectronico"));
                    usuario.setTelefono(res.getInt("telefono"));
                    usuario.setSexo(res.getString("sexo").charAt(0));
                    usuario.setDireccion(res.getString("direccion"));
                    usuario.setClave(res.getString("clave"));
                    usuario.setIdPerfil(res.getInt("idPerfil"));
                    usuario.setTipoUsuario(res.getString("tipoUsuario"));
                    usuario.setEstado(res.getInt("estado"));
                    usuario.setNombrePerfil(res.getString("nombrePerfil"));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conector.close();
        }

        return usuario;
    }
    
    public List<UsuarioDTO> getUsuarios(int pagina, int cantidad, String where) {
        ArrayList<UsuarioDTO> retorno = new ArrayList<UsuarioDTO>();
        
        try {
            String sql = obtenerSqlFinalPaginacion(
                    " SELECT u.run, u.nombres, u.apellidos, u.correoElectronico, u.telefono, u.sexo, u.direccion, u.clave, u.idPerfil, u.tipoUsuario, u.estado, p.nombrePerfil "
                    + " FROM usuario u "
                    + " INNER JOIN perfil p ON u.idPerfil = p.idPerfil  " + where, pagina, cantidad, "u.run DESC");//DESC y ASC
            ResultSet res = conector.getResultSet(sql);

            while (res.next()) {
                try {
                    UsuarioDTO usuario = new UsuarioDTO();
                    usuario.setRun(res.getString("run"));
                    usuario.setNombres(res.getString("nombres"));
                    usuario.setApellidos(res.getString("apellidos"));
                    usuario.setCorreoElectronico(res.getString("correoElectronico"));
                    usuario.setTelefono(res.getInt("telefono"));
                    usuario.setSexo(res.getString("sexo").charAt(0));
                    usuario.setDireccion(res.getString("direccion"));
                    usuario.setClave(res.getString("clave"));
                    usuario.setIdPerfil(res.getInt("idPerfil"));
                    usuario.setTipoUsuario(res.getString("tipoUsuario"));
                    usuario.setEstado(res.getInt("estado"));
                    usuario.setNombrePerfil(res.getString("nombrePerfil"));

                    retorno.add(usuario);

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
    
}
