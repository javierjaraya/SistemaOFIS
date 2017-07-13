/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

/**
 *
 * @author 
 */
public class ImagenDTO {
    public int idImagen;
    public String nombreImagen;
    public String rutaImagen;
    public int idProducto;

    public ImagenDTO() {
    }

    public ImagenDTO(int idImagen, String nombreImagen, String rutaImagen, int idProducto) {
        this.idImagen = idImagen;
        this.nombreImagen = nombreImagen;
        this.rutaImagen = rutaImagen;
        this.idProducto = idProducto;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
    
}
