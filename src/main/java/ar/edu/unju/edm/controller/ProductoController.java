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
import ar.edu.unju.edm.service.IProductoService;

@Controller
public class ProductoController {

	@Autowired
	@Qualifier("impmysqlproducto")
	IProductoService productoService;

	@GetMapping("/producto")
	public String cragarProducto(Model model) {
		model.addAttribute("unProducto", productoService.obtenerProductoNuevo());
		model.addAttribute("productos", productoService.obtenerTodosProductos());
		return ("producto");
	}
	
	@GetMapping("/producto/editar/{codProducto}")
	public String editarProducto(Model model, @PathVariable(name="codProducto") int cod) throws Exception {
		try {
			Producto productoEncontrado = productoService.encontrarUnProducto(cod);
			model.addAttribute("unProducto", productoEncontrado);
			model.addAttribute("editMode", "true");
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unProducto", productoService.crearProducto());
			model.addAttribute("editMode", "false");
		}
		model.addAttribute("productos", productoService.obtenerTodosProductos());
		return("producto");
	}

	@PostMapping("/producto/guardar")
	public String guardarNuevoProducto(@ModelAttribute("unProducto") Producto nuevoProducto, Model model) {
		productoService.guardarProducto(nuevoProducto);
		System.out.println(productoService.obtenerTodosProductos().get(0).getMarca());
		model.addAttribute("productos", productoService.obtenerTodosProductos());

		return "redirect:/producto";
	}
	
	@PostMapping("/producto/modificar")
	public String modificarProducto(@ModelAttribute("unProducto") Producto productoModificado, Model model) {
		try {
			productoService.modificarProducto(productoModificado);
			model.addAttribute("unProducto", new Producto());				
			model.addAttribute("editMode", "false");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// pasar las excepciones al html
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unProducto", productoModificado);			
			model.addAttribute("productos", productoService.obtenerTodosProductos());
			model.addAttribute("editMode", "true");
		}
		model.addAttribute("productos", productoService.obtenerTodosProductos());
		return("producto");
	}
}
