package ar.edu.unju.edm.service.imp;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.repository.IProductoDAO;
import ar.edu.unju.edm.service.IProductoService;

@Service
@Qualifier("impmysqlproducto")
public class ProductoServiceMySQL implements IProductoService{
	@Autowired
	Producto unProducto;
	
	@Autowired
	IProductoDAO productoDAO;
	
	@Override
	public void guardarProducto(Producto productoGuardado) {
		// TODO Auto-generated method stub
		productoDAO.save(productoGuardado);
	}
	@Override
	public Producto obtenerUnProducto(String nombreProducto) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<Producto> obtenerTodosProductos() {
		// TODO Auto-generated method stub
		return (ArrayList<Producto>) productoDAO.findAll();
	}
	@Override
	public Producto obtenerProductoNuevo() {
		// TODO Auto-generated method stub
		return unProducto;
	}
	@Override
	public Producto obtenerUltimoProducto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto encontrarUnProducto(int cod) throws Exception {
		return productoDAO.findByCodProducto(cod).orElseThrow(()->new Exception("El producto NO existe"));
	}
	
	@Override
	public Object crearProducto() {
		// TODO Auto-generated method stub
		return unProducto;
	}

	@Override
	public void modificarProducto(Producto productoModificado) throws Exception {
		// TODO Auto-generated method stub
				Producto productoAModificar = productoDAO.findByCodProducto(productoModificado.getCodProducto()).orElseThrow(()->new Exception("El Producto no fue encontrado"));
				cambiarProducto(productoModificado, productoAModificar);
				productoDAO.save(productoAModificar);
			}
	
	private void cambiarProducto(Producto productoModificado, Producto productoAModificar) {
		// TODO Auto-generated method stub
		productoAModificar.setCodProducto(productoModificado.getCodProducto());
		productoAModificar.setNombre(productoModificado.getNombre());
		productoAModificar.setMarca(productoModificado.getMarca());
		productoAModificar.setPrecio(productoModificado.getPrecio());
		productoAModificar.setStock(productoModificado.getStock());
		productoAModificar.setDescripcion(productoModificado.getDescripcion());
	}
	
	@Override
	public void eliminarProducto(int cod) throws Exception {
		// TODO Auto-generated method stub
		Producto productoEliminar = productoDAO.findById(cod).orElseThrow(()->new Exception("El Producto no fue encontrado"));
		productoDAO.delete(productoEliminar);
	}

}