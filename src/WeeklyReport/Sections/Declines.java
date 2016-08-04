/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WeeklyReport.Sections;

import static Styling.Fonts.*;
import WeeklyReport.CargoQuoteType;
import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author cmeehan
 */
public class Declines {

    private final Map<Double, String> DECLINES_BY_REASON = new CargoQuoteType().declinesByReason();
    private final Map<Double, Map<String, String>> DECLINES_BY_COMMODITY_CLASS = new CargoQuoteType().declinesByCommClass();
    private PdfPCell cell;

    private JFreeChart declinesByReasonChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        Map<Double, String> mp = DECLINES_BY_REASON;
        mp.entrySet().stream().forEach((mapEntry) -> {
            dataset.setValue(mapEntry.getValue(), mapEntry.getKey());
        });
        JFreeChart pieChart = ChartFactory.createPieChart3D("Reason for Decline by total CBM", dataset, true, true, false);
        Plot plot = pieChart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        return pieChart;
    }

    private JFreeChart declinesByCommodityClassChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<Double, Map<String, String>> mp = DECLINES_BY_COMMODITY_CLASS;
        mp.entrySet().stream().forEach((mapEntry) -> {
            Map<String, String> m1 = mapEntry.getValue();
            m1.entrySet().stream().forEach((pair) -> {
                dataset.setValue(mapEntry.getKey(), pair.getValue(), pair.getKey());
            });
        });
        JFreeChart barChart = ChartFactory.createBarChart3D("Declines by Commodity Class", "Commodity Class", "Cubic Meters", dataset, PlotOrientation.VERTICAL, true, true, false);
        return barChart;

    }
    
    public PdfPTable declinesByCommodityTable(){
        PdfPTable table = new PdfPTable(2);
        cell = new PdfPCell(new Phrase("Commodities Declined by Cubic Meter & Trade Lane", SUBHEADING_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Commodity", COLUMN_HEADER));
        cell.setBorder(Rectangle.BOTTOM);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Trade Lane"));
        cell.setBorder(Rectangle.BOTTOM);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Cubic Meters"));
        cell.setBorder(Rectangle.BOTTOM);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        Map<Double, Map<String, String>> mp = DECLINES_BY_COMMODITY_CLASS;
        mp.entrySet().stream().forEach((mapEntry)->{
            Map<String, String> mp1 = mapEntry.getValue();
            mp1.entrySet().stream().forEach((secondMapEntry)->{
                cell = new PdfPCell(new Phrase(String.valueOf(secondMapEntry.getValue()), TEXT_FONT));
                cell.setBorder(Rectangle.TOP);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(String.valueOf(secondMapEntry.getValue()), TEXT_FONT));
                cell.setBorder(Rectangle.TOP);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(String.valueOf(mapEntry.getKey()), TEXT_FONT));
                cell.setBorder(Rectangle.TOP);
                table.addCell(cell);                
            });
        });
        return table;
    }
    
    public PdfPTable declinesByReasonTable() {
        PdfPTable table = new PdfPTable(2);
        cell = new PdfPCell(new Phrase("Reasons for Decline by Cubic Meter", SUBHEADING_FONT));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Reason for Decline", COLUMN_HEADER));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Cubic Meters", COLUMN_HEADER));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        Map<Double, String> mp = DECLINES_BY_REASON;
        mp.entrySet().stream().forEach((mapEntry) -> {
            cell = new PdfPCell(new Phrase(String.valueOf(mapEntry.getValue()), TEXT_FONT));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(String.valueOf(mapEntry.getKey()), TEXT_FONT));
            table.addCell(cell);
        });
        return table;
    }

    public Image declinesByReasonImage(PdfWriter writer) throws BadElementException {
        JFreeChart chart = declinesByReasonChart();
        PdfContentByte contentByte = writer.getDirectContent();
        PdfTemplate template = contentByte.createTemplate(600f, 400f);
        Graphics2D graphics2d = template.createGraphics(600f, 400f, new DefaultFontMapper());
        Rectangle2D rectangle2d = new Rectangle2D.Float(10f, 0, 500f, 400f);
        chart.draw(graphics2d, rectangle2d);
        graphics2d.dispose();
        
        Image chartImage = Image.getInstance(template);
        return chartImage;
    }

    public Image declinesByCommodityImage(PdfWriter writer) throws BadElementException {
        JFreeChart chart = new Declines().declinesByCommodityClassChart();
        PdfContentByte contentByte = writer.getDirectContent();
        PdfTemplate template = contentByte.createAppearance(600f, 400f);
        Graphics2D graphics2d = template.createGraphics(600f, 400f, new DefaultFontMapper());
        Rectangle2D rectangle2d = new Rectangle2D.Float(10f, 0, 500f,400f);
        chart.draw(graphics2d, rectangle2d);
        graphics2d.dispose();
        
        Image chartImage = Image.getInstance(template);
        return chartImage;
    }

}
