package com.springbootclienteapp.util;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import com.springbootclienteapp.entity.Cliente;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.List;
import java.util.Map;

@Component("/views/clientes/listar")
public class ListarClientePdf extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        @SuppressWarnings("unchecked")
        List<Cliente> listadoClientes = (List<Cliente>) model.get("clientes");
        /* Fuentes, Tamños y colores para cade seccion*/
        Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, Color.BLUE);
        Font fuenteTituloColumnas = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.BLUE);
        Font fuenteDataCeldas = FontFactory.getFont(FontFactory.COURIER, 10, Color.BLACK);

        document.setPageSize(PageSize.LETTER.rotate());
        document.setMargins(-20, -20, 40, 20);
        document.open();
        PdfPCell celda = null;

        /*Tabla Para el titulo del PDF*/
        PdfPTable tableTitulo = new PdfPTable(1);
        celda = new PdfPCell(new Phrase("LISTADO GENERAL DEL CLIENTES", fuenteTitulo));
        celda.setBorder(0);
        celda.setBackgroundColor(new Color(40, 190, 138));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(30);

        tableTitulo.addCell(celda);
        tableTitulo.setSpacingAfter(30);

        /*Tabla para mostrar listado del Clientes*/
        PdfPTable tablaClientes = new PdfPTable(6);
        tablaClientes.setWidths(new float[]{0.6f, 2f, 2f, 1.5f, 3.5f, 1.5f});
        celda = new PdfPCell(new Phrase("Id", fuenteTitulo));
        celda.setBackgroundColor(Color.lightGray);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaClientes.addCell(celda);


        celda = new PdfPCell(new Phrase("Nombres", fuenteTituloColumnas));
        celda.setBackgroundColor(Color.lightGray);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaClientes.addCell(celda);

        celda = new PdfPCell(new Phrase("Apellidos", fuenteTituloColumnas));
        celda.setBackgroundColor(Color.lightGray);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaClientes.addCell(celda);



        celda = new PdfPCell(new Phrase("Teléfono", fuenteTituloColumnas));
        celda.setBackgroundColor(Color.lightGray);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaClientes.addCell(celda);

        celda = new PdfPCell(new Phrase("Email", fuenteTituloColumnas));
        celda.setBackgroundColor(Color.lightGray);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaClientes.addCell(celda);


        celda = new PdfPCell(new Phrase("Cuidad", fuenteTituloColumnas));
        celda.setBackgroundColor(Color.lightGray);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaClientes.addCell(celda);

        /* Bucle For, mostrar todos los datos de los clientes*/

        for (Cliente cliente : listadoClientes) {
            celda = new PdfPCell(new Phrase(cliente.getId().toString(),fuenteDataCeldas));
            celda.setPadding(5);
            tablaClientes.addCell(celda);

            celda = new PdfPCell(new Phrase(cliente.getNombres().toString(),fuenteDataCeldas));
            celda.setPadding(5);
            tablaClientes.addCell(celda);

            celda = new PdfPCell(new Phrase(cliente.getApellidos().toString(),fuenteDataCeldas));
            celda.setPadding(5);
            tablaClientes.addCell(celda);

            celda = new PdfPCell(new Phrase(cliente.getTelefono().toString(),fuenteDataCeldas));
            celda.setPadding(5);
            tablaClientes.addCell(celda);

            celda = new PdfPCell(new Phrase(cliente.getEmail().toString(),fuenteDataCeldas));
            celda.setPadding(5);
            tablaClientes.addCell(celda);

            celda = new PdfPCell(new Phrase(cliente.getCiudad().getCiudad(),fuenteDataCeldas));
            celda.setBackgroundColor(new Color(40, 190, 138));
            celda.setPadding(5);
            tablaClientes.addCell(celda);

        }

        /*

        listarClientes.forEach(cliente -> {
            tablaClientes.addCell(cliente.getId().toString());

            tablaClientes.addCell(cliente.getNombres());
            tablaClientes.addCell(cliente.getApellidos());
            tablaClientes.addCell(cliente.getTelefono());
            tablaClientes.addCell(cliente.getEmail());
            tablaClientes.addCell(cliente.getCiudad().getCiudad());
        });*/

        /*Anexamos las tablas al Documento*/
        document.add(tableTitulo);
        document.add(tablaClientes);

    }
}
