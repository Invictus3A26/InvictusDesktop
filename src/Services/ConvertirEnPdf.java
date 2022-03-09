/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
//import javax.swing.text.Document;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Tools.MyConnexion;

/**
 *
 * @author Khach
 */
public class ConvertirEnPdf {

    public static void main(String[] args) {
        try {
            String fileName = "C:\\generate_pdf\\ticket.pdf";
            Document doc = new Document();
            PdfWriter.getInstance((com.itextpdf.text.Document) doc, new FileOutputStream(fileName));
            doc.open();
            /*Paragraph para = new Paragraph("ticket Reservation");
            doc.add(para);*/
            //bd
            MyConnexion conn = new MyConnexion();
            Connection connection = conn.getCnx();
            PreparedStatement ps = null;
            ResultSet rs=null;
            String query ="select * from bagage";
            ps=connection.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next()){
                Paragraph para2 = new Paragraph(rs.getInt("id"), rs.getString("poids"));
                doc.add(para2);
                doc.add(new Paragraph(""));
            }
            //add table

            /*PdfPTable table = new PdfPTable(3);
            PdfPCell c1 = new PdfPCell(new Phrase("Heading 1"));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Heading 2"));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Heading 3"));
            table.addCell(c1);
            table.setHeaderRows(2);

            table.addCell("1.0");
            table.addCell("1.1");
            table.addCell("1.2");
            table.addCell("2.1");
            table.addCell("2.2");
            table.addCell("2.3");
            doc.add(table);*/

            doc.close();

        } catch (Exception e) {

        }
    }

}
