/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WeeklyReport.Sections;

import Dates.ReportingDates;
import static Styling.Fonts.*;
import WeeklyReport.CargoTypeData;
import WeeklyReport.CustomerQuoteData;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author cmeehan
 */
public class Commodities {

    private PdfPCell cell = new PdfPCell();

    public PdfPTable commoditySectionIntroduction() {
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

        cell = new PdfPCell(new Phrase("During week " + new ReportingDates().reportPeriod() + " a total of " + new CustomerQuoteData().totalQuotes() + " were generated through RQS. Of these quotes the top two commodity classes quoted were " + commodities.get(0) + " and " + commodities.get(1) + ". The below table depicts the top ten commodities quoted during week " + new ReportingDates().reportPeriod() + ".", TEXT_FONT));
        cell.setColspan(1);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        return table;
    }

    public PdfPTable byCommodityTable() {
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
        m.entrySet().stream().forEach((entry) -> {
            cell = new PdfPCell(new Phrase(entry.getKey(), TEXT_FONT));
            cell.setColspan(1);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(entry.getValue(), TEXT_FONT));
            cell.setColspan(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);
        });
        return table;
    }

    private JFreeChart commodityChart(){
        DefaultPieDataset dataset = new DefaultPieDataset();
        Map<Double, String> mp = new CargoTypeData().quotesByCommodityCBM();
        mp.entrySet().stream().forEach((mapEntry)->{
            dataset.setValue(mapEntry.getValue(), mapEntry.getKey());
        });
        JFreeChart pieChart = ChartFactory.createPieChart3D("Commodities Quoted by Cubic Meter", dataset, true, true, false);
        
        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        
        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator("{0}: {1} cbm ({2})", new DecimalFormat("0.000"), new DecimalFormat("0%"));
        plot.setLabelGenerator(gen);
        
        return pieChart;
    }
    
    public Image commodityChartImage(PdfWriter writer) throws BadElementException{
        JFreeChart chart = commodityChart();
        PdfContentByte contentByte = writer.getDirectContent();
        PdfTemplate template = contentByte.createTemplate(525f, 475f);
        Graphics2D graphics2d = template.createGraphics(525f, 475f, new DefaultFontMapper());
        Rectangle2D rectangle2d = new Rectangle2D.Float(0, 0, 525f, 475f);
        chart.draw(graphics2d, rectangle2d);
        graphics2d.dispose();
        
        Image chartImage = Image.getInstance(template);
        return chartImage;
    }
}
