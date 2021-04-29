package ar.edu.unju.edm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Producto;

@Service
public interface IProductoService {

	public void guardarProducto(Producto unProducto);

	public void modificarProducto(Producto productoAModificar);

	public void eliminarProducto(Producto productoAEliminar);

	public Producto obtenerUnProducto(String nombreProducto);

	public List<Producto> obtenerTodosProductos();

	public Producto obtenerProductoNuevo();

	public Object obtenerUltimoProducto();

}
