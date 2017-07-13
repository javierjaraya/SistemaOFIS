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
public class ProductoDTO {
    public int idProducto;
    public String nombreProducto;
    public String descripcionProducto;
    public int stock;
    public double precio;
    public int idCategora;

    public ProductoDTO() {
    }

    public ProductoDTO(int idProducto, String nombreProducto, String descripcionProducto, int stock, double precio, int idCategora) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.stock = stock;
        this.precio = precio;
        this.idCategora = idCategora;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdCategora() {
        return idCategora;
    }

    public void setIdCategora(int idCategora) {
        this.idCategora = idCategora;
    }
    
    
}
