package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.model.Venta;
import ar.edu.unju.edm.service.IClienteService;
import ar.edu.unju.edm.service.IProductoService;
import ar.edu.unju.edm.service.IVentaService;

@Controller
public class VentaController {
	
	@Autowired
	@Qualifier("impmysqlproducto")
	IProductoService productoService;
	
	@Autowired
	@Qualifier("impmysql")
	IClienteService iClienteService;
	
	@Autowired
	Producto productoSeleccionado;
	
	@Autowired
	IVentaService ventaService;
	
	@GetMapping("/producto/ventas")
	public String cragarVentas(Model model) {
		model.addAttribute("productos", productoService.obtenerTodosProductos());
		return ("ventas");
	}

	@GetMapping("/producto/vender/{idProducto}")
	public String realizarVenta(Model model, @PathVariable(name="idProducto") Integer idProducto) throws Exception {
		Venta venta = new Venta();
		try {
			productoSeleccionado = productoService.obtenerProductoCodigo(idProducto);
			venta = ventaService.crearVenta();
			venta.setProducto(productoSeleccionado);
			model.addAttribute("venta", venta);
			model.addAttribute("clientes", iClienteService.obtenerTodosClientes());
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
		}
		return "modal-venta";
	}
	
	@PostMapping("/producto/vender")
public String guardarNuevoProducto(@ModelAttribute("venta") Venta unaVenta, Model model) {
	ventaService.guardarVenta(unaVenta);
	return("redirect:/producto/ventas");
}
}
