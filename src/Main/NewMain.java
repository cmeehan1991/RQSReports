/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Connections.DBConnection;
import WeeklyReport.*;
import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author cmeehan
 */
public class NewMain {
    private static int totalQuotes;
    private static double commodityCount;
    
    
    private static int totalQuotesNASEB(){
        Connection conn = new DBConnection().Connect();
        String SQL = "SELECT COUNT(*) AS 'COUNT' FROM allquotes WHERE tradeLane = 'North Atlantic Shuttle EB'";
        try{
            PreparedStatement ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                totalQuotes = rs.getInt("COUNT");
            }
        }catch(Exception ex){
           System.out.println(ex.getMessage());
        }
        
        System.out.println(totalQuotes);
        
        return totalQuotes;
    }
    
    private static int totalQuotesTALEB(){
         Connection conn = new DBConnection().Connect();
        String SQL = "SELECT COUNT(*) AS 'COUNT' FROM allquotes WHERE tradeLane = 'Trans-Atlantic EB'";
        try{
            PreparedStatement ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                totalQuotes = rs.getInt("COUNT");
            }
        }catch(Exception ex){
           System.out.println(ex.getMessage());
        }
        
        System.out.println(totalQuotes);
        
        return totalQuotes;
    }
    private static int totalQuotesNAXWB(){
         Connection conn = new DBConnection().Connect();
        String SQL = "SELECT COUNT(*) AS 'COUNT' FROM allquotes WHERE tradeLane = 'NAX WB'";
        try{
            PreparedStatement ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                totalQuotes = rs.getInt("COUNT");
            }
        }catch(Exception ex){
           System.out.println(ex.getMessage());
        }
        
        System.out.println(totalQuotes);
        
        return totalQuotes;
    }
    private static int totalQuotesECAMSSB(){
         Connection conn = new DBConnection().Connect();
        String SQL = "SELECT COUNT(*) AS 'COUNT' FROM allquotes WHERE tradeLane = 'ECAMS SB'";
        try{
            PreparedStatement ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                totalQuotes = rs.getInt("COUNT");
            }
        }catch(Exception ex){
           System.out.println(ex.getMessage());
        }
        
        System.out.println(totalQuotes);
        
        return totalQuotes;
    }
    
    private static JFreeChart totalQuotesChart(){
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(totalQuotesNASEB(), "NAS EB", "NAS EB");
        dataset.addValue(totalQuotesTALEB(), "TAL EB", "TAL EB");
        dataset.addValue(totalQuotesNAXWB(), "NAX WB", "NAX WB");
        dataset.addValue(totalQuotesECAMSSB(), "ECAMS SB", "ECAMS SB");
        
        JFreeChart barChart = ChartFactory.createBarChart("Total Quotes by Trade Lane", "Trade Lane", "Number of Quotes", dataset, PlotOrientation.VERTICAL, true, true, false);
        
        int width = 640;
        int height = 480;
        
        return barChart;
    }
    
    private static double automobiles(){
       Connection conn = new DBConnection().Connect();
        String SQL = "SELECT COUNT(*) AS 'COUNT' FROM allquotes WHERE comm_class LIKE 'Automobile%'";
        try{
            PreparedStatement ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                commodityCount = rs.getInt("COUNT");
            }
        }catch(Exception ex){
           System.out.println(ex.getMessage());
        }
        
        System.out.println(commodityCount);
        
        return commodityCount;
        
    }
     private static double construction(){
       Connection conn = new DBConnection().Connect();
        String SQL = "SELECT COUNT(*) AS 'COUNT' FROM allquotes WHERE comm_class LIKE 'Construction%'";
        try{
            PreparedStatement ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                commodityCount = rs.getInt("COUNT");
            }
        }catch(Exception ex){
           System.out.println(ex.getMessage());
        }
        
        System.out.println(commodityCount);
        
        return commodityCount;
        
    }
    
    private static JFreeChart quitesByCommodityType(){
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Automobile/POVs", new Double(automobiles()));
        dataset.setValue("Construction", new Double(construction()));
        
        JFreeChart chart = ChartFactory.createPieChart("Quotes by Commodity Class",dataset,true,true,false);
        return chart;
    }
    
    public static void writeChartToPDF(JFreeChart chart, int width, int height, String fileName){
        PdfWriter writer = null;
        Document document = new Document();
        try{
            writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            PdfContentByte contentByte = writer.getDirectContent();
            PdfTemplate template = contentByte.createTemplate(width, height);
            Graphics2D graphics2d = template.createGraphics(width, height, new DefaultFontMapper());
            Rectangle2D rectangle2d = new Rectangle2D.Double(0,0,width, height);
            chart.draw(graphics2d, rectangle2d);
            graphics2d.dispose();
            contentByte.addTemplate(template, 0,0);
        }catch(FileNotFoundException | DocumentException ex){
            ex.getMessage();
        }
        document.close();
    }
    
   
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        //writeChartToPDF(quitesByCommodityType(), 500, 400, "C:\\Users\\cmeehan\\Desktop\\PieChart.pdf");
        //writeChartToPDF(totalQuotesChart(), 500, 400, "C:\\Users\\cmeehan\\Desktop\\BarChart.pdf");
        
        new WeeklyPDF();
    }
    
    
    
}
