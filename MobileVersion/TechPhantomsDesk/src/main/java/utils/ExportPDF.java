package utils;

import java.io.FileOutputStream;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.sql.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ExportPDF  {
    private static void leaveEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
    public static void ExportMeetPDF() throws Exception{
        Font COURIER = new Font(Font.FontFamily.COURIER, 20, Font.BOLD);
        Font COURIER_SMALL = new Font(Font.FontFamily.COURIER, 16, Font.BOLD);
        Font COURIER_SMALL_FOOTER = new Font(Font.FontFamily.COURIER, 12, Font.BOLD);
        /* Create Connection objects */
        Connection conn = DataSource.getInstance().getCnx();
        Statement stmt = conn.createStatement();
        /* Define the SQL query */
        ResultSet query_set = stmt.executeQuery("SELECT * FROM meet");
        /* Step-2: Initialize PDF documents - logical objects */
        Document my_pdf_report = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(my_pdf_report, new FileOutputStream("src\\pdfexport.pdf"));
        my_pdf_report.open();
        Image imgsup = Image.getInstance("C:\\Users\\MJ-INFO\\Desktop\\Codename\\TechPhantomsDesk\\src\\main\\resources\\icons\\icon.png");
        imgsup.scaleAbsolute(200, 100);
        imgsup.setAlignment(Element.ALIGN_RIGHT);
        // pdfsup.add(new Paragraph("Suppliers"));
        my_pdf_report.add(imgsup);
        Paragraph p1 = new Paragraph();
        leaveEmptyLine(p1, 1);
        p1.add(new Paragraph("Meetings Details Report",FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.BLUE)));
        p1.setAlignment(Element.ALIGN_CENTER);
        leaveEmptyLine(p1, 1);
        LocalDate d1 = LocalDate.now();
        p1.add(new Paragraph("Report generated on " + d1, COURIER_SMALL));
        my_pdf_report.add(p1);

        Paragraph paragraph = new Paragraph();
        leaveEmptyLine(paragraph, 3);
        my_pdf_report.add(paragraph);

        //we have four columns in our table
        PdfPTable my_report_table = new PdfPTable(7);
        my_report_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        my_report_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        //create a cell object
        PdfPCell table_cell;
        table_cell=new PdfPCell(new Phrase("Meeting ID"));
        table_cell.setBackgroundColor(BaseColor.CYAN);
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("Tutor"));
        table_cell.setBackgroundColor(BaseColor.CYAN);
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("Student"));
        table_cell.setBackgroundColor(BaseColor.CYAN);
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("Meet Date"));
        table_cell.setBackgroundColor(BaseColor.CYAN);
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("Meet Link"));
        table_cell.setBackgroundColor(BaseColor.CYAN);
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("Meeting PassCode"));
        table_cell.setBackgroundColor(BaseColor.CYAN);
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("Meeting Name"));
        table_cell.setBackgroundColor(BaseColor.CYAN);
        my_report_table.addCell(table_cell);
        my_report_table.setWidthPercentage(100);

        while (query_set.next()) {
            String dept_id = query_set.getString("meet_id");
            table_cell=new PdfPCell(new Phrase(dept_id));
            my_report_table.addCell(table_cell);


            String dept_name=query_set.getString("tutorid_id");
            table_cell=new PdfPCell(new Phrase(dept_name));
            my_report_table.addCell(table_cell);
            String manager_id=query_set.getString("studentid_id");
            table_cell=new PdfPCell(new Phrase(manager_id));
            my_report_table.addCell(table_cell);


            String location_id=query_set.getString("meet_link");
            table_cell=new PdfPCell(new Phrase(location_id));
            my_report_table.addCell(table_cell);

            String meet_date=query_set.getString("meet_date");
            table_cell=new PdfPCell(new Phrase(meet_date));
            my_report_table.addCell(table_cell);

            String meet_pass=query_set.getString("meet_pass");
            table_cell=new PdfPCell(new Phrase(meet_pass));
            my_report_table.addCell(table_cell);

            String meet_name=query_set.getString("meet_name");
            table_cell=new PdfPCell(new Phrase(meet_name));
            my_report_table.addCell(table_cell);
        }

        /* Attach report table to PDF */
        my_pdf_report.add(my_report_table);
        Paragraph p2 = new Paragraph();
        leaveEmptyLine(p2, 3);
        p2.setAlignment(Element.ALIGN_MIDDLE);
        p2.add(new Paragraph(
                "Copyright @TechPhantoms",
                COURIER_SMALL_FOOTER));

        my_pdf_report.add(p2);
        my_pdf_report.close();

        /* Close all DB related objects */
        query_set.close();
        stmt.close();
        conn.close();

    }
}