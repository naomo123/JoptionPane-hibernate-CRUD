package model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")

public class Producto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")

	private Long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "marca")
	private String marca;

	
	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "stock")
	private int stock;

	@Column(name = "precio")
	private double precio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	


	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", marca=" + marca + ", descripcion=" + descripcion
				+ ", stock=" + stock + ", precio=" + precio + "]";
	}
	
	

}
