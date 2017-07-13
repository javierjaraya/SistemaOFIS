/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import java.sql.Date;

/**
 *
 * @author 
 */
public class CompraDTO {
    public int idCompra;
    public Date fechaCompra;
    public String estado;
    public String metodoDespacho;
    public String direccion;
    public String personaRetira;
    public String run;

    public CompraDTO() {
    }

    public CompraDTO(int idCompra, Date fechaCompra, String estado, String metodoDespacho, String direccion, String personaRetira, String run) {
        this.idCompra = idCompra;
        this.fechaCompra = fechaCompra;
        this.estado = estado;
        this.metodoDespacho = metodoDespacho;
        this.direccion = direccion;
        this.personaRetira = personaRetira;
        this.run = run;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMetodoDespacho() {
        return metodoDespacho;
    }

    public void setMetodoDespacho(String metodoDespacho) {
        this.metodoDespacho = metodoDespacho;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPersonaRetira() {
        return personaRetira;
    }

    public void setPersonaRetira(String personaRetira) {
        this.personaRetira = personaRetira;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }
    
    
}
