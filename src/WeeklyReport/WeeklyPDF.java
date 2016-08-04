/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WeeklyReport;

import Dates.ReportingDates;
import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Map;
import org.jfree.chart.JFreeChart;
import WeeklyReport.Sections.*;

/**
 *
 * @author cmeehan
 */
public final class WeeklyPDF {

    private static PdfPCell cell;
    private static final Font HEADING_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static final Font SUBHEADING_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLDITALIC);
    private static final Font SECTION_HEADING = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
    private static final Font COLUMN_HEADER = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    private static final Font TEXT_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

    public void pdfWriter() {
        PdfWriter writer = null;
        Document document = new Document();
        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Weekly Reports\\Week " + new ReportingDates().reportPeriod() + " Quoting Report.pdf"));
            document.open();
            document.add(new CoverPage().backgroundImage());
            document.add(new CoverPage().coverTable());
            document.newPage();
            document.add(header());
            document.add(new Introduction().introduction());
            document.newPage();
            document.add(new RegionalQuoteData().regionalAnalysis());
            document.add(new RegionalQuoteData().southeastTable());
            document.add(new RegionalQuoteData().northEastTable());
            document.add(new RegionalQuoteData().midWestTable());
            document.add(new RegionalQuoteData().westCoastTable());
            document.newPage();
            document.add(new Commodities().commoditySectionIntroduction());
            document.add(new Commodities().byCommodityTable());
            document.add(new Commodities().commodityChartImage(writer));
            if (new CustomerQuoteData().declinedQuotes() >= 1) {
                document.newPage();
                document.add(new Declines().declinesByReasonImage(writer));
                document.add(new Declines().declinesByReasonTable());
                document.add(new Declines().declinesByCommodityImage(writer));
                document.add(new Declines().declinesByCommodityTable());
            }
            if (new CustomerQuoteData().bookedQuotes() >= 1) {
                document.newPage();
                document.add(new Bookings().bookingsByTradelaneImage(writer));
                document.add(new Bookings().bookingsByPODImage(writer));
            }
            document.newPage();
            document.add(endOfReport());
            document.close();
        } catch (FileNotFoundException | DocumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private PdfPTable endOfReport() {
        PdfPTable table = new PdfPTable(1);
        cell = new PdfPCell(new Phrase("End of Report", HEADING_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setBorderWidth(2f);
        table.addCell(cell);

        return table;
    }

    public static PdfPTable header() {
        PdfPTable table = new PdfPTable(1);
        table.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        table.setWidthPercentage(100f);

        cell = new PdfPCell(new Phrase("RQS Report", HEADING_FONT));
        cell.setColspan(1);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Week: " + new ReportingDates().reportPeriod(), SUBHEADING_FONT));
        cell.setColspan(1);
        cell.setBorder(Rectangle.BOTTOM);
        table.addCell(cell);

        return table;
    }

    public static void writeChartToPDF(JFreeChart chart, int width, int height, String fileName) {
        PdfWriter writer = null;
        Document document = new Document();
        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            document.add(new Paragraph("Here is the recommendation"));
            PdfContentByte contentByte = writer.getDirectContent();
            PdfTemplate template = contentByte.createTemplate(width, height);
            Graphics2D graphics2d = template.createGraphics(width, height, new DefaultFontMapper());
            Rectangle2D rectangle2d = new Rectangle2D.Double(5, -100, width, height);
            chart.draw(graphics2d, rectangle2d);
            graphics2d.dispose();
            contentByte.addTemplate(template, 0, 0);
        } catch (FileNotFoundException | DocumentException ex) {
            ex.getMessage();
        }
        document.close();
    }

    public WeeklyPDF() {
        //writeChartToPDF(new Charts().pieChart(), 590, 400, "C:\\Users\\cmeehan\\Desktop\\Weekly Report.pdf");
        //System.out.println(new ReportingDates().lastWeek());
        pdfWriter();
    }
}
