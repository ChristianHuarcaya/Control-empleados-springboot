package com.control.empleados.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.control.empleados.Servicios.EmpleadoService;

@Controller
public class DashboardController {

	@Autowired
	private EmpleadoService empleadoService;

	@GetMapping("/dashboard")
	public String verDashboard(Model model) {
		model.addAttribute("totalEmpleados", empleadoService.count());

		return "dashboard";
	}
}
