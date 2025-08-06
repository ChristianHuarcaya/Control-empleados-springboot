package com.control.empleados.util.reportes;

import com.control.empleados.Entidad.Empleado;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

public class EmpleadoExporterExcel {

	private XSSFWorkbook libro;
	private Sheet hoja;
	private List<Empleado> listaEmpleados;

	public EmpleadoExporterExcel(List<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
		libro = new XSSFWorkbook();
		hoja = libro.createSheet("Empleados");
	}

	private void escribirCabeceraDeTabla() {
		Row fila = hoja.createRow(0);
		CellStyle estilo = libro.createCellStyle();
		Font fuente = libro.createFont();
		fuente.setBold(true);
		fuente.setColor(IndexedColors.WHITE.getIndex());
		estilo.setFont(fuente);
		estilo.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		estilo.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		String[] encabezados = { "ID", "Nombre", "Apellido", "Email", "Fecha", "Teléfono", "Sexo", "Salario",
				"Puesto" };

		for (int i = 0; i < encabezados.length; i++) {
			Cell celda = fila.createCell(i);
			celda.setCellValue(encabezados[i]);
			celda.setCellStyle(estilo);
		}
	}

	private void escribirDatosDeLaTabla() {
		int numeroFila = 1;

		for (Empleado empleado : listaEmpleados) {
			Row fila = hoja.createRow(numeroFila++);

			fila.createCell(0).setCellValue(empleado.getId());
			fila.createCell(1).setCellValue(empleado.getNombre());
			fila.createCell(2).setCellValue(empleado.getApellido());
			fila.createCell(3).setCellValue(empleado.getEmail());
			fila.createCell(4).setCellValue(empleado.getFecha().toString());
			fila.createCell(5).setCellValue(empleado.getTelefono());
			fila.createCell(6).setCellValue(empleado.getSexo());
			fila.createCell(7).setCellValue(empleado.getSalario());
			fila.createCell(8).setCellValue(empleado.getPuesto());
		}

		// Ajustar ancho automático
		for (int i = 0; i < 8; i++) {
			hoja.autoSizeColumn(i);
		}
	}

	public void exportar(HttpServletResponse response) throws IOException {
		escribirCabeceraDeTabla();
		escribirDatosDeLaTabla();

		ServletOutputStream out = response.getOutputStream();
		libro.write(out);
		libro.close();
		out.close();
	}
}
