package com.control.empleados.util.reportes;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import com.control.empleados.Entidad.Empleado;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import jakarta.servlet.http.HttpServletResponse;

public class EmpleadoExporterPDF {

	private List<Empleado> listaEmpleados;

	public EmpleadoExporterPDF(List<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;

	}

	private void escribirCabeceraDeLaTabla(PdfPTable tabla) {
		PdfPCell celda = new PdfPCell();
		celda.setBackgroundColor(Color.BLUE);
		celda.setPadding(5);

		Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fuente.setColor(Color.WHITE);

		// Agregamos "Puesto" al arreglo de encabezados
		String[] encabezados = { "ID", "Nombre", "Apellido", "Email", "Fecha", "Teléfono", "Sexo", "Salario",
				"Puesto" };

		for (String encabezado : encabezados) {
			celda.setPhrase(new Phrase(encabezado, fuente));
			tabla.addCell(celda);
		}
	}

	private void escribirDatosDeLaTabla(PdfPTable tabla) {
		for (Empleado empleado : listaEmpleados) {
			tabla.addCell(String.valueOf(empleado.getId()));
			tabla.addCell(empleado.getNombre());
			tabla.addCell(empleado.getApellido());
			tabla.addCell(empleado.getEmail());
			tabla.addCell(empleado.getFecha().toString());
			tabla.addCell(String.valueOf(empleado.getTelefono()));
			tabla.addCell(empleado.getSexo());
			tabla.addCell(String.valueOf(empleado.getSalario()));
			tabla.addCell(empleado.getPuesto()); // Aquí agregas "Puesto"
		}
	}

	public void exportar(HttpServletResponse response) throws DocumentException, IOException {
		Document documento = new Document(PageSize.A4);
		PdfWriter.getInstance(documento, response.getOutputStream());

		documento.open();

		Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fuenteTitulo.setColor(Color.BLUE);
		fuenteTitulo.setSize(18);

		Paragraph titulo = new Paragraph("Lista de empleados", fuenteTitulo);
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		documento.add(titulo);

		documento.add(new Paragraph(" ")); // Espacio

		// Aquí actualizamos a 9 columnas
		PdfPTable tabla = new PdfPTable(9); // Cambiado a 9 para incluir "Puesto"
		tabla.setWidthPercentage(100);
		tabla.setSpacingBefore(15);
		tabla.setWidths(new float[] { 1f, 2.3f, 2.3f, 5f, 2.5f, 2.5f, 2f, 2.5f, 2.5f }); // Ajustamos las proporciones
																							// si es necesario

		escribirCabeceraDeLaTabla(tabla);
		escribirDatosDeLaTabla(tabla);

		documento.add(tabla);
		documento.close();
	}
}