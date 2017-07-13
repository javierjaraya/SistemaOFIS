/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 
 */
public class CarroCompraDTO {
    public double totalCarro;
    public int cantidadProductos;
    public List<ProductoDTO> productos;

    public CarroCompraDTO() {
        productos = new ArrayList<ProductoDTO>();
        this.totalCarro = 0;
        this.cantidadProductos = 0;
    }
    
    public void agregarProducto(ProductoDTO producto){
        //Revisar si exite
        boolean exite = false;
        for (int i = 0; i < productos.size(); i++) {
            if(productos.get(i).idProducto == producto.idProducto) {
                productos.get(i).cantidad += producto.cantidad;
                exite = true;
            }
        }
        if(!exite){
            productos.add(producto);
        }
        this.totalCarro += producto.cantidad * producto.precio;
        this.cantidadProductos += producto.cantidad;
    }
    
    public void eliminarProducto(ProductoDTO producto){
        //Revisar si exite
        boolean exite = false;
        int indice = 0;
        for (int i = 0; i < productos.size(); i++) {
            if(productos.get(i).idProducto == producto.idProducto) {
                this.cantidadProductos -= productos.get(i).cantidad;
                this.totalCarro -= productos.get(i).cantidad * productos.get(i).precio;
                exite = true;
                indice = i;
            }
        }
        if(exite){
            productos.remove(indice);
        }
    }
    
    public double getTotalCarro(){
        return this.totalCarro;
    }
    
    public int getCantidadProductos(){
        return this.cantidadProductos;
    }
    
    public void vaciarCarrito(){
        this.productos.clear();
        this.cantidadProductos = 0;
        this.totalCarro = 0;        
    }
    
    public List<ProductoDTO> getListaCarrito(){
        return productos;
    }
}
