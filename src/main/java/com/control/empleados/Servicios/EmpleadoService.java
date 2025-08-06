
package com.control.empleados.Servicios;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.control.empleados.Entidad.Empleado;

@Service
public interface EmpleadoService {

	List<Empleado> findAll();

	Page<Empleado> findAll(Pageable pageable);

	void save(Empleado empleado);

	Empleado findOne(Long id);

	void delete(Long id);

	long count(); // <--- agregar este método

	List<Empleado> buscarPorNombre(String nombre);
}
