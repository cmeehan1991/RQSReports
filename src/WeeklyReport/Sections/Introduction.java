/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WeeklyReport.Sections;

import Dates.ReportingDates;
import WeeklyReport.CustomerQuoteData;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import Styling.Fonts;
import WeeklyReport.CargoTypeData;

/**
 *
 * @author cmeehan
 */
public class Introduction {

    private PdfPCell cell;
    private String paragraph;
    private final String reportPeriod = new ReportingDates().reportPeriod();
    private final int totalQuotes = new CustomerQuoteData().totalQuotes(), totalNAQuotes = new CustomerQuoteData().totalNAQuotes();
    private final String newParagraph = "\n";
    private final String TOP_NAS_CARGO = new CargoTypeData().topNASCommodity();
    private final String TOP_TAL_CARGO = new CargoTypeData().topTalCargo();
    private final String TOP_NAX_CARGO = new CargoTypeData().topNaxCargo();
    private final String TOP_ECAMS_CARGO = new CargoTypeData().topEcamsCargo();
    
    
    
    private String firstParagraph() {
        paragraph = "This report contains a summary of RQS activity for week " + reportPeriod + ". The purpose of this report is to better guide Account Managers and Senior Management personnel in their weekly sales activities. The RQS weekly report includes data on cargo bookings, customer feedback, commodity movement, and customer activity. The customers identified in this report are United States based accounts that have been identified as potential targets. It is important to note that while some of the accounts listed in this report already have service contracacts, they are listed due to a high amount of activity outside of their contracted rates.";
        return paragraph;
    }

    private String secondParagraph(){
        paragraph = "During week " + reportPeriod + " a total of " + totalQuotes + " quotes were generated and/or updated from \"K\" Line America offices to customers and regional offices globally. Of these "+totalQuotes+" quotes, "+totalNAQuotes+" quotes were generated and/or updated for identified North American target accounts. The top quoted account from each region was ___ (Southeast), ___ (North East), ___ (Mid-West), and ___ (West Coast).";
        return paragraph;
    }
    private String thirdParagraph(){
        paragraph = "During week "+reportPeriod+" a total of ___ general commodities were quoted to North American Target Customers. Of this number, the top three commodity types quoted were ___, ___, and ___. The top commodity quoted on each trade lane was "+TOP_NAS_CARGO+" on the North Atlantic Shuttle East Bound, "+TOP_TAL_CARGO+" on the Trans-Atlantic East Bound, "+TOP_ECAMS_CARGO+" on the ECAMS South Bound, and "+TOP_NAX_CARGO+" on the NAX West Bound. The top customer quoted on each trade lane in week "+reportPeriod+" was ___ on the NAS, ___ on the TAL, ___ on the ECAMS, and ___ on the NAX trade lane. ";
        return paragraph;
    }
    
    
    public PdfPTable introduction() {
        PdfPTable table = new PdfPTable(1);
        table.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10f);

        cell = new PdfPCell(new Phrase("INTRODUCTION", Fonts.SECTION_HEADING));
        cell.setColspan(1);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(newParagraph + firstParagraph() + newParagraph + secondParagraph()+newParagraph+thirdParagraph(), Fonts.TEXT_FONT));
        cell.setColspan(1);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        return table;
    }
}
