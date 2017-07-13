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
public class SolicitudRegistroDTO {
    public int idSolicitud;
    public String run;
    public Date fechaSolicitud;

    public SolicitudRegistroDTO() {
    }

    public SolicitudRegistroDTO(int idSolicitud, String run, Date fechaSolicitud) {
        this.idSolicitud = idSolicitud;
        this.run = run;
        this.fechaSolicitud = fechaSolicitud;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }
    
    
}
