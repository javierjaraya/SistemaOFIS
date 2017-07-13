
package Controladores;

import Controladores.Mantenedores.ControladorLoginDAO;
import Controladores.Mantenedores.ControladorUsuarioDAO;

/**
 *
 * @author 
 */
public class ControlSistema {
    private static ControlSistema instancia = null;
    
    private ControladorLoginDAO controlLogin;
    private ControladorUsuarioDAO controlUsuario;    

    public ControlSistema() {
        this.controlLogin = new ControladorLoginDAO();
        this.controlUsuario = new ControladorUsuarioDAO();
    }
    
    public static ControlSistema getInstancia() {
        if(instancia == null){
            instancia = new ControlSistema();
        }
        return instancia;
    }

    public ControladorLoginDAO getControlLogin() {
        return controlLogin;
    }    
    
    public ControladorUsuarioDAO getControlUsuario() {
        return controlUsuario;
    }
    
    
    
}
