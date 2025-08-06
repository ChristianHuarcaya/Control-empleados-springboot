package com.control.empleados.Controlador;

import com.control.empleados.Entidad.Empleado;
import com.control.empleados.Servicios.EmpleadoService;
import com.control.empleados.util.Paginacion.PageRender;
import com.control.empleados.util.reportes.EmpleadoExporterExcel;
import com.control.empleados.util.reportes.EmpleadoExporterPDF;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("empleado")
public class EmpleadoController {

	@Autowired
	private EmpleadoService empleadoService;

	// === [1] LISTA - VISIBLE A TODOS LOS USUARIOS AUTENTICADOS ===
	@GetMapping("/lista")
	public String listarEmpleados(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 8, Sort.by("id").ascending());
		Page<Empleado> empleados = empleadoService.findAll(pageRequest);

		PageRender<Empleado> pageRender = new PageRender<>("/lista", empleados);

		model.addAttribute("titulo", "Listado de empleados");
		model.addAttribute("empleados", empleados.getContent()); // <-- enviar solo la lista
		model.addAttribute("page", pageRender);
		return "listar"; // nombre de tu vista
	}

	// === [2] VER DETALLES - TODOS PUEDEN VER ===
	@GetMapping("/ver/{id}")
	public String verDetalles(@PathVariable Long id, Model model, RedirectAttributes flash) {
		Empleado empleado = empleadoService.findOne(id);

		if (empleado == null) {
			flash.addFlashAttribute("error", "El empleado no existe.");
			return "redirect:/lista";
		}

		model.addAttribute("empleado", empleado);
		model.addAttribute("titulo", "Detalles del empleado");
		return "ver"; // crea ver.html si quieres mostrar más datos
	}

	// === [3] FORMULARIO REGISTRO - SOLO ADMIN ===
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/form")
	public String mostrarFormulario(Model model) {
		model.addAttribute("empleado", new Empleado());
		model.addAttribute("titulo", "Registro de Empleado");
		return "form";
	}

	// === [4] EDITAR - SOLO ADMIN ===
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/form/{id}")
	public String editar(@PathVariable Long id, Model model, RedirectAttributes flash) {
		Empleado empleado = empleadoService.findOne(id);

		if (empleado == null) {
			flash.addFlashAttribute("error", "El empleado no existe.");
			return "redirect:/lista";
		}

		model.addAttribute("empleado", empleado);
		model.addAttribute("titulo", "Edición de Empleado");
		return "form";
	}

	// === [5] GUARDAR EMPLEADO - SOLO ADMIN ===
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/form")
	public String guardarEmpleado(@Valid @ModelAttribute("empleado") Empleado empleado, BindingResult result,
			Model model, RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Empleado");
			return "form";
		}

		String mensaje = (empleado.getId() != null) ? "Empleado editado con éxito" : "Empleado registrado con éxito";

		empleadoService.save(empleado);
		status.setComplete();
		flash.addFlashAttribute("success", mensaje);
		return "redirect:/lista";
	}

	// === [6] ELIMINAR - SOLO ADMIN ===
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/eliminar/{id}")
	public String eliminarEmpleado(@PathVariable Long id, RedirectAttributes flash) {
		if (id > 0) {
			empleadoService.delete(id);
			flash.addFlashAttribute("success", "Empleado eliminado con éxito.");
		} else {
			flash.addFlashAttribute("error", "ID inválido.");
		}
		return "redirect:/lista";
	}

	// === [7] EXPORTAR PDF - SOLO ADMIN ===
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/exportarPDF")
	public void exportarPDF(HttpServletResponse response) throws IOException {
		response.setContentType("application/pdf");
		String fecha = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		response.setHeader("Content-Disposition", "attachment; filename=Empleados_" + fecha + ".pdf");

		List<Empleado> empleados = empleadoService.findAll();
		EmpleadoExporterPDF exporter = new EmpleadoExporterPDF(empleados);
		exporter.exportar(response);
	}

	// === [8] EXPORTAR EXCEL - SOLO ADMIN ===
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/exportarExcel")
	public void exportarExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		String fecha = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		response.setHeader("Content-Disposition", "attachment; filename=Empleados_" + fecha + ".xlsx");

		List<Empleado> empleados = empleadoService.findAll();
		EmpleadoExporterExcel exporter = new EmpleadoExporterExcel(empleados);
		exporter.exportar(response);
	}

	@GetMapping("/lista/buscar/{nombre}")
	public String listarEmpleadosPorNombre(@PathVariable("nombre") String nombre, Model model) {
		List<Empleado> empleados;
		if (nombre != null && !nombre.isEmpty()) {
			empleados = empleadoService.buscarPorNombre(nombre);
		} else {
			empleados = empleadoService.findAll();
		}
		model.addAttribute("empleados", empleados);
		return "empleados/lista";
	}

}
