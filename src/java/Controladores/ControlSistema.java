
package Controladores;

import Controladores.Mantenedores.ControladorCategoriaDAO;
import Controladores.Mantenedores.ControladorCompraDAO;
import Controladores.Mantenedores.ControladorDetalleCompraDAO;
import Controladores.Mantenedores.ControladorImagenDAO;
import Controladores.Mantenedores.ControladorLoginDAO;
import Controladores.Mantenedores.ControladorPerfilDAO;
import Controladores.Mantenedores.ControladorProductoDAO;
import Controladores.Mantenedores.ControladorSolicitudRegistroDAO;
import Controladores.Mantenedores.ControladorUsuarioDAO;

/**
 *
 * @author 
 */
public class ControlSistema {
    private static ControlSistema instancia = null;
    
    private ControladorCategoriaDAO controlCategoria;
    private ControladorCompraDAO controlCompra;
    private ControladorDetalleCompraDAO controlDetalleCompra;
    private ControladorImagenDAO controlImagen;
    private ControladorLoginDAO controlLogin;
    private ControladorPerfilDAO controlPerfil;
    private ControladorProductoDAO controlProducto;
    private ControladorSolicitudRegistroDAO controlSolicitudRegistro;
    private ControladorUsuarioDAO controlUsuario;    

    public ControlSistema() {
        this.controlCategoria = new ControladorCategoriaDAO();
        this.controlCompra = new ControladorCompraDAO();
        this.controlDetalleCompra = new ControladorDetalleCompraDAO();
        this.controlImagen = new ControladorImagenDAO();
        this.controlLogin = new ControladorLoginDAO();
        this.controlPerfil = new ControladorPerfilDAO();
        this.controlProducto = new ControladorProductoDAO();
        this.controlSolicitudRegistro = new ControladorSolicitudRegistroDAO();
        this.controlUsuario = new ControladorUsuarioDAO();
    }
    
    public static ControlSistema getInstancia() {
        if(instancia == null){
            instancia = new ControlSistema();
        }
        return instancia;
    }

    public ControladorCategoriaDAO getControlCategoria() {
        return controlCategoria;
    }

    public ControladorCompraDAO getControlCompra() {
        return controlCompra;
    }

    public ControladorDetalleCompraDAO getControlDetalleCompra() {
        return controlDetalleCompra;
    }

    public ControladorImagenDAO getControlImagen() {
        return controlImagen;
    }

    public ControladorLoginDAO getControlLogin() {
        return controlLogin;
    }

    public ControladorPerfilDAO getControlPerfil() {
        return controlPerfil;
    }

    public ControladorProductoDAO getControlProducto() {
        return controlProducto;
    }

    public ControladorSolicitudRegistroDAO getControlSolicitudRegistro() {
        return controlSolicitudRegistro;
    }

    public ControladorUsuarioDAO getControlUsuario() {
        return controlUsuario;
    }
    
    
}
