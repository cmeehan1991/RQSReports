/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WeeklyReport.Sections;

import WeeklyReport.CargoQuoteType;
import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author cmeehan
 */
public class Bookings {

    private JFreeChart bookingsByPODChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<Double, Map<String, String>> map = new CargoQuoteType().bookingsByPOD();
        map.entrySet().stream().forEach((mapEntry) -> {
            Map<String, String> m1 = mapEntry.getValue();
            m1.entrySet().stream().forEach((pair) -> {
                dataset.setValue(mapEntry.getKey(), pair.getKey(), pair.getValue());
            });
        });
        JFreeChart barChart = ChartFactory.createBarChart3D("Bookings by POD", "Company", "Cubic Meters", dataset, PlotOrientation.HORIZONTAL, true, true, false);
        barChart.setBackgroundPaint(Color.WHITE);
        CategoryPlot categoryPlot = barChart.getCategoryPlot();
        CategoryAxis domainAxis = categoryPlot.getDomainAxis();
        BarRenderer br = (BarRenderer) categoryPlot.getRenderer();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
        categoryPlot.setBackgroundPaint(Color.WHITE);
        categoryPlot.setRangeGridlinePaint(Color.BLACK);
        return barChart;
    }

    public Image bookingsByPODImage(PdfWriter writer) throws BadElementException {
        JFreeChart chart = new Bookings().bookingsByPODChart();
        PdfContentByte contentByte = writer.getDirectContent();
        PdfTemplate template = contentByte.createTemplate(600f, 400f);
        Graphics2D graphics2d = template.createGraphics(600f, 400f, new DefaultFontMapper());
        Rectangle2D rectangle2d = new Rectangle2D.Float(10f, 0, 500f, 400f);
        chart.draw(graphics2d, rectangle2d);
        graphics2d.dispose();

        Image chartImage = Image.getInstance(template);
        return chartImage;
    }

    private JFreeChart bookingsByTradelaneChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<Double, Map<String, String>> map = new CargoQuoteType().bookingsByTradeLane();
        map.entrySet().stream().forEach((mapEntry) -> {
            Map<String, String> m1 = mapEntry.getValue();
            m1.entrySet().stream().forEach((pair) -> {
                dataset.setValue(mapEntry.getKey(), pair.getKey(), pair.getValue());
            });
        });
        JFreeChart barChart = ChartFactory.createBarChart3D("Bookings by Trade Lane", "Company", "Cubic Meters", dataset, PlotOrientation.VERTICAL, true, true, false);
        barChart.setBackgroundPaint(Color.WHITE);
        CategoryPlot categoryPlot = barChart.getCategoryPlot();
        CategoryAxis domainAxis = categoryPlot.getDomainAxis();
        BarRenderer br = (BarRenderer) categoryPlot.getRenderer();
        if (map.values().stream().mapToInt((list) -> list.size()).count() > 2) {
            domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        }
        categoryPlot.setBackgroundPaint(Color.WHITE);
        categoryPlot.setRangeGridlinePaint(Color.BLACK);
        return barChart;
    }

    public Image bookingsByTradelaneImage(PdfWriter writer) throws BadElementException {
        JFreeChart chart = new Bookings().bookingsByTradelaneChart();
        PdfContentByte contentByte = writer.getDirectContent();
        PdfTemplate template = contentByte.createTemplate(600f, 400f);
        Graphics2D graphics2d = template.createGraphics(600f, 400f, new DefaultFontMapper());
        Rectangle2D rectangle2d = new Rectangle2D.Float(10f, 0, 500f, 400f);
        chart.draw(graphics2d, rectangle2d);
        graphics2d.dispose();

        Image chartImage = Image.getInstance(template);
        return chartImage;
    }
}
