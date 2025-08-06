package com.control.empleados.Entidad;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "empleados")
public class Empleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "El nombre no puede estar vacío")
	private String nombre;

	@NotEmpty(message = "El apellido no puede estar vacío")
	private String apellido;

	@NotEmpty(message = "El correo no puede estar vacío")
	@Email(message = "Debe proporcionar un correo válido")
	private String email;

	@NotNull(message = "El teléfono es obligatorio")
	@Digits(integer = 9, fraction = 0, message = "Debe ser un número válido sin decimales")
	private Integer telefono; // ✔️ cambiado a Integer para manejar nulls y validación

	@NotEmpty(message = "Debe seleccionar el sexo")
	private String sexo;

	@NotNull(message = "El salario es obligatorio")
	@DecimalMin(value = "0.0", inclusive = false, message = "El salario debe ser mayor a 0")
	private Double salario; // ✔️ cambiado a Double para manejar nulls

	@NotNull(message = "La fecha es obligatoria")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha;
	
	@Column(name = "puesto")
	@NotNull(message = "El puesto es obligatorio") // Campo obligatorio
	private String puesto;

	// Constructor vacío
	public Empleado() {
	}

	// Getters y setters
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
}
