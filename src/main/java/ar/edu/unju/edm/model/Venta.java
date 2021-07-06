package ar.edu.unju.edm.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="ventas")
public class Venta {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column
	private Integer idVenta;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name ="idProducto")
	private Producto producto;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name ="idCliente")
	private Cliente cliente;
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate fechaVenta;
	@Column
	private Integer cantProducto;
	
	public Integer getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public LocalDate getFechaVenta() {
		return fechaVenta;
	}
	public void setFechaVenta(LocalDate fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	public Integer getCantProducto() {
		return cantProducto;
	}
	public void setCantProducto(Integer cantProducto) {
		this.cantProducto = cantProducto;
	}
	
	 public Venta() {
		// TODO Auto-generated constructor stub
	}

}
