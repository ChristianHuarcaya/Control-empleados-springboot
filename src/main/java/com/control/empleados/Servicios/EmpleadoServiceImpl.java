package com.control.empleados.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.control.empleados.Entidad.Empleado;
import com.control.empleados.Repository.EmpleadoRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepository;

	public List<Empleado> buscarPorNombre(String nombre) {
		// Llamamos al repositorio para buscar por nombre
		return empleadoRepository.findByNombre(nombre);

	}

	@Override
	public List<Empleado> findAll() {
		return empleadoRepository.findAll();
	}

	@Override
	public Page<Empleado> findAll(Pageable pageable) {
		return empleadoRepository.findAll(pageable);
	}

	@Override
	public Empleado findOne(Long id) {
		return empleadoRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		empleadoRepository.deleteById(id);
	}

	@Override
	public long count() {
		return empleadoRepository.count();
	}

	@Override
	public void save(Empleado empleado) {
		// Verificar si el puesto es nulo o vacío y asignar un valor por defecto
		if (empleado.getPuesto() == null || empleado.getPuesto().isEmpty()) {
			empleado.setPuesto("Sin puesto asignado");
		}
		// Guardar el empleado en el repositorio
		empleadoRepository.save(empleado);
	}
}
