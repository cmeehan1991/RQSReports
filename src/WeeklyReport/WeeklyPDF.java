/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WeeklyReport;

import Dates.ReportingDates;
import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.awt.PdfGraphics2D;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import org.jfree.chart.JFreeChart;
import WeeklyReport.Sections.*;

/**
 *
 * @author cmeehan
 */
public class WeeklyPDF {

    private static PdfPCell cell;
    private static final Font HEADING_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static final Font SUBHEADING_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLDITALIC);
    private static final Font SECTION_HEADING = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
    private static final Font COLUMN_HEADER = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    private static final Font TEXT_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
    
    private static final int DECLINES = new CustomerQuoteData().declinedQuotes();
    private static final int BOOKINGS = new CustomerQuoteData().bookedQuotes();
    private static final int FEEDBACK = new CustomerQuoteData().feedbackQuotes();
    private static final int PENDING_RESPONSE = new CustomerQuoteData().pendingQuotes();
    private static final String REPORT_WEEK = new ReportingDates().reportPeriod();

    public void pdfWriter() {
        PdfWriter writer = null;
        Document document = new Document();
        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\cmeehan\\Desktop\\Weekly Report.pdf"));
            document.open();
            document.add(new CoverPage().backgroundImage());
            document.add(new CoverPage().coverTable());
            document.newPage();
            document.add(header());
            document.add(new Introduction().introduction());
            document.newPage();
            document.add(regionalAnalysis());
            document.add(southeastTable());
            document.add(northEastTable());
            document.add(midWestTable());
            document.add(westCoastTable());
            document.newPage();
            document.add(commoditySection());
            document.add(byCommodityTable());
            document.close();
        } catch (FileNotFoundException | DocumentException ex) {
            System.out.println(ex.getMessage());
        }
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

    

    public static PdfPTable regionalAnalysis() {
        PdfPTable table = new PdfPTable(1);
        table.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10f);

        cell = new PdfPCell(new Phrase("Regional Quote Data", SECTION_HEADING));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderWidthBottom(4f);
        cell.setPaddingBottom(10f);
        table.addCell(cell);
        
        // Account for singular or plural
        String declined = null;
        String booked = null;
        String pending = null;
        if(DECLINES == 1){
            declined = DECLINES + " was declined, ";
        }else{
            declined = DECLINES + " were declined, ";
        }
        
        if(BOOKINGS == 1){
            booked = DECLINES + " was booked, ";
        }else{
            booked = DECLINES + " were booked, ";
        }
        
        if (PENDING_RESPONSE == 1){
            pending = PENDING_RESPONSE + " is pending further action.";
        }else{
            pending = PENDING_RESPONSE + " are pending further action.";
        }

        cell = new PdfPCell(new Phrase("In week " + new ReportingDates().reportPeriod() + " a total of " + new CustomerQuoteData().totalNAQuotes() + " quotes were generated through RQS to North American customers (not including Canada). Of these quotes, " + declined + booked + FEEDBACK + " received customer feedback, and " + pending, TEXT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        return table;

    }

    public static PdfPTable southeastTable() {
        PdfPTable table = new PdfPTable(2);
        table.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10f);

        cell = new PdfPCell(new Phrase("South East", SUBHEADING_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Customer", COLUMN_HEADER));
        cell.setPaddingBottom(5f);
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderWidthBottom(2f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Number of Quotes", COLUMN_HEADER));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPaddingBottom(5f);
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderWidthBottom(2f);
        table.addCell(cell);

        Map<String, String> m = new CustomerQuoteData().southeastQuotes();
        for (Map.Entry<String, String> entry : m.entrySet()) {
            cell = new PdfPCell(new Phrase(entry.getKey(), TEXT_FONT));
            cell.setColspan(1);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(entry.getValue(), TEXT_FONT));
            cell.setColspan(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);
        }
        return table;
    }

    public static PdfPTable northEastTable() {
        PdfPTable table = new PdfPTable(2);
        table.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10f);

        cell = new PdfPCell(new Phrase("North East", SUBHEADING_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Customer", COLUMN_HEADER));
        cell.setPaddingBottom(5f);
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderWidthBottom(2f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Number of Quotes", COLUMN_HEADER));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPaddingBottom(5f);
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderWidthBottom(2f);
        table.addCell(cell);

        Map<String, String> m = new CustomerQuoteData().northEastQuotes();
        for (Map.Entry<String, String> entry : m.entrySet()) {
            cell = new PdfPCell(new Phrase(entry.getKey(), TEXT_FONT));
            cell.setColspan(1);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(entry.getValue(), TEXT_FONT));
            cell.setColspan(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);
        }
        return table;
    }

    public static PdfPTable midWestTable() {
        PdfPTable table = new PdfPTable(2);
        table.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10f);

        cell = new PdfPCell(new Phrase("Mid West", SUBHEADING_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Customer", COLUMN_HEADER));
        cell.setPaddingBottom(5f);
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderWidthBottom(2f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Number of Quotes", COLUMN_HEADER));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPaddingBottom(5f);
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderWidthBottom(2f);
        table.addCell(cell);

        Map<String, String> m = new CustomerQuoteData().midWestQuotes();
        for (Map.Entry<String, String> entry : m.entrySet()) {
            cell = new PdfPCell(new Phrase(entry.getKey(), TEXT_FONT));
            cell.setColspan(1);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(entry.getValue(), TEXT_FONT));
            cell.setColspan(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);
        }
        return table;
    }

    public static PdfPTable westCoastTable() {
        PdfPTable table = new PdfPTable(2);
        table.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10f);

        cell = new PdfPCell(new Phrase("West Coast", SUBHEADING_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Customer", COLUMN_HEADER));
        cell.setPaddingBottom(5f);
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderWidthBottom(2f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Number of Quotes", COLUMN_HEADER));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPaddingBottom(5f);
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderWidthBottom(2f);
        table.addCell(cell);

        Map<String, String> m = new CustomerQuoteData().westCoastQuotes();
        for (Map.Entry<String, String> entry : m.entrySet()) {
            cell = new PdfPCell(new Phrase(entry.getKey(), TEXT_FONT));
            cell.setColspan(1);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(entry.getValue(), TEXT_FONT));
            cell.setColspan(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);
        }
        return table;
    }

    public static PdfPTable commoditySection() {
        PdfPTable table = new PdfPTable(1);
        table.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10f);

        cell = new PdfPCell(new Phrase("COMMODITIES", SECTION_HEADING));
        cell.setColspan(1);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        // Get the top two commodities from the HashMap
        Map<String, String> m = new CargoTypeData().topTwoCommodities();
        //ArrayList to add the commodities to
        ArrayList<String> commodities = new ArrayList<>();
        m.entrySet().stream().forEach((entry) -> {
            commodities.add(entry.getKey());
        });
        
        cell = new PdfPCell(new Phrase("During week "+new ReportingDates().reportPeriod()+" a total of "+new CustomerQuoteData().totalQuotes()+" were generated through RQS. Of these quotes the top two commodity classes quoted were "+commodities.get(0)+" and "+commodities.get(1)+". The below table depicts the top ten commodities quoted during week "+new ReportingDates().reportPeriod()+".", TEXT_FONT));
        cell.setColspan(1);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        
        
        
        return table;
    }

    public static PdfPTable byCommodityTable() {
        PdfPTable table = new PdfPTable(2);
        table.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10f);

        cell = new PdfPCell(new Phrase("Quotes by Commodity", SUBHEADING_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        cell.setPaddingBottom(10f);
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderWidthBottom(2f);
        table.addCell(cell);

        Map<String, String> m = new CargoTypeData().quotesByCommodity();
        for (Map.Entry<String, String> entry : m.entrySet()) {
            cell = new PdfPCell(new Phrase(entry.getKey(), TEXT_FONT));
            cell.setColspan(1);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(entry.getValue(), TEXT_FONT));
            cell.setColspan(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);
        }
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
