package Controladores.Mantenedores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import Dto.UsuarioDTO;
import Controladores.Mantenedores.Nucleo.Controlador;

public class ControladorLoginDAO extends Controlador {


    public ControladorLoginDAO() {
    }

   

    public boolean validarContrasenaActual(int idUsuario, String contrasena) {
        if (contrasena != null && !contrasena.equals("")) {
            try {
                ResultSet rsSesion = conector.getResultSet("validarContrasena", contrasena, "" + idUsuario);
                while (rsSesion.next()) {
                    if (rsSesion.getInt("cantidad") > 0) {
                        return true;
                    }
                }
            } catch (Exception e) {
                System.out.println("[Query][validarContrase�aActual] error:" + e.getMessage());
            } finally {
                conector.close();
            }
        }
        return false;
    }

    public boolean modificarContrasena(int idUsuario, String contrasena) {
        try {
            int respuesta = conector.executeUpdate("actualizarPass", "" + idUsuario, contrasena);
            if (respuesta > 0) {
                //conector.close();
                return true;
            }

        } catch (Exception e) {

            System.out.println("[Query][modificarContrasena] error:" + e.getMessage());
        } finally {
            conector.close();
        }
        return false;
    }

  
}
