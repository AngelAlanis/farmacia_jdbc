package com.misael.farmacia;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Ticket {

    private ArrayList<VentaTienda> listaProductos;
    private double                 importeTotal;
    private double                 totalPagado;
    private double                 cambio;
    private int                    numeroTicket;
    private Utilidades             utilidades;

    public Ticket(ArrayList<VentaTienda> listaProductos, double importeTotal, double totalPagado, int numeroTicket) {
        this.listaProductos = listaProductos;
        this.importeTotal   = importeTotal;
        this.totalPagado    = totalPagado;
        this.cambio         = totalPagado - importeTotal;
        this.numeroTicket   = numeroTicket + 1;
        this.utilidades     = new Utilidades();

        printPDF(generarTexto());
    }

    public String generarTexto() {
        String textoTicket;

        DateTimeFormatter formatoFecha     = DateTimeFormatter.ISO_DATE;
        DateTimeFormatter formatoHora      = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime     localDateTimeNow = LocalDateTime.now();

        String horaActual  = formatoHora.format(localDateTimeNow);
        String fechaActual = formatoFecha.format(localDateTimeNow);

        ImageIcon logoTicket = utilidades.imageIcon("/logoSinTexto.png");
        JLabel    labelLogo  = new JLabel(logoTicket);


        textoTicket = "--------------------------------------------------";
        textoTicket += String.format("\n\n%34s", "Vesa Pharmacy");
        textoTicket += String.format("\n\n%41s", "Blvd. Felipe Pescador 1830");
        textoTicket += String.format("\n\n%45s", "Nueva Vizcaya, 34080 Durango, Dgo");
        textoTicket += String.format("\n\n%36s", "+52 618 233 6430");

        textoTicket += "\n\n--------------------------------------------------";
        textoTicket += String.format("\n%-5s %-20.20s %-12s %-12s", "Cant.", "Artículo", "Precio unit.", "Importe");
        textoTicket += "\n\n--------------------------------------------------";

        for (VentaTienda producto : listaProductos) {
            textoTicket += String.format("\n%-5s %-20.20s $%-12.2f $%-12.2f",
                                         producto.getCantidad(),
                                         producto.getDescripcion(),
                                         producto.getPrecio(),
                                         producto.getImporte());
        }

        textoTicket += "\n--------------------------------------------------";

        textoTicket += String.format("\n%s $%41.2f", "Total", importeTotal);
        textoTicket += String.format("\n%s $%38.2f", "Recibido", totalPagado);
        textoTicket += String.format("\n%s $%40.2f", "Cambio", cambio);

        textoTicket += "\n--------------------------------------------------";

        textoTicket += String.format("\n%15s %5s %10s %5s", "Hora:", horaActual, "Fecha:", fechaActual);
        textoTicket += String.format("\n%32s", "Venta # " + numeroTicket);

        textoTicket += "\n**************************************************";
        textoTicket += String.format("\n%38s", "GRACIAS POR SU COMPRA");
        textoTicket += "\n**************************************************";

        return textoTicket;
    }

    public void printPDF(String stringTicket) {
        PDDocument pdDocument = new PDDocument();
        PDPage     pdPage     = new PDPage();
        PDFont     pdFont     = PDType1Font.COURIER;

        String nombrePDF = numeroTicket + ".pdf";

        pdDocument.addPage(pdPage);
        pdPage.setMediaBox(new PDRectangle(480, 600));

        String[] lineas = stringTicket.split("\n");

        try {
            PDPageContentStream contentStream = new PDPageContentStream(pdDocument, pdPage);
            contentStream.beginText();
            contentStream.setFont(pdFont, 14);
            float nextLine = pdPage.getMediaBox().getHeight() - 32;
            contentStream.newLineAtOffset(10, nextLine);

            for (String line : lineas) {
                contentStream.newLineAtOffset(0, -16);
                contentStream.showText(line);
            }

            contentStream.endText();
            contentStream.close();

            pdDocument.save(new File(System.getProperty("user.home"), "/Desktop/" + numeroTicket + ".pdf"));
            pdDocument.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
