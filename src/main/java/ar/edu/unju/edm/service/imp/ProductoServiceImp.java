package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.service.IProductoService;
import ar.edu.unju.edm.util.ListadoProductos;

@Service
@Qualifier("impproducto")
public class ProductoServiceImp implements IProductoService {

	private static final Log LOGGER = LogFactory.getLog(ProductoServiceImp.class);

	@Autowired
	Producto unProducto;

	// ArrayList<Producto> listaDeProductos = new ArrayList<Producto>();

	private List<Producto> listaDeProductos = ListadoProductos.productos;

	@Override
	public void guardarProducto(Producto unProducto) {
		// TODO Auto-generated method stub
		System.out.println(unProducto.getNombre());
		listaDeProductos.add(unProducto);
		System.out.println(listaDeProductos.size());

		LOGGER.info("METHOD: ingresando a Guardar Producto");
		LOGGER.info("RESULT: guardado " + listaDeProductos.get(listaDeProductos.size() - 1).getNombre());
	}

	@Override
	public void modificarProducto(Producto productoModificado) {
		// TODO Auto-generated method stub
		for (int i=0; i< listaDeProductos.size();i++)
		{
			if(listaDeProductos.get(i).getCodProducto() == productoModificado.getCodProducto())
			{
				listaDeProductos.set(i, productoModificado);
			}
		}
	}

	@Override
	public Producto obtenerUnProducto(String nombreProducto) {	
		return null;
	}

	@Override
	public Producto obtenerProductoNuevo() {
		// TODO Auto-generated method stub
		return unProducto;
	}

	@Override
	public List<Producto> obtenerTodosProductos() {
		// TODO Auto-generated method stub
		return listaDeProductos;
	}

	@Override
	public Producto obtenerUltimoProducto() {
		// TODO Auto-generated method stub
		int i = listaDeProductos.size() - 1;
		return listaDeProductos.get(i);
	}

	@Override
	public Producto encontrarUnProducto(int cod) {
		// TODO Auto-generated method stub
		for (int i=0; i < listaDeProductos.size();i++)
		{
			if(listaDeProductos.get(i).getCodProducto() == cod)
			{
				unProducto = listaDeProductos.get(i);
	    	}
		}
		return unProducto;
	}

	@Override
	public Object crearProducto() {
		// TODO Auto-generated method stub
		return unProducto;
	}

	@Override
	public void eliminarProducto(int cod) {
		// TODO Auto-generated method stub
		for (int i=0; i< listaDeProductos.size();i++)
		{
			if(listaDeProductos.get(i).getCodProducto() == cod)
			{
				listaDeProductos.remove(i);
			}
		}
	}

}
