package com.control.empleados.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.control.empleados.Entidad.Empleado;

@Repository
public interface EmpleadoRepository  extends JpaRepository<Empleado,Long>{
	 
	
	 
	 // Búsqueda por nombre o puesto (insensible a mayúsculas/minúsculas)
	List<Empleado> findByNombre(String nombre); // <-- Aquí agregamos la búsqueda por nombre
}
	 
	
	

