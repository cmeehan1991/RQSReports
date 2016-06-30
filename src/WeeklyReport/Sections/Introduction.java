/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WeeklyReport.Sections;

import Dates.ReportingDates;
import WeeklyReport.CustomerQuoteData;
import com.itextpdf.text.pdf.PdfPCell;
import static Styling.Fonts.HEADING_FONT;
import static Styling.Fonts.TEXT_FONT;
import WeeklyReport.CargoTypeData;
import com.itextpdf.text.Paragraph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author cmeehan
 */
public class Introduction {

    private PdfPCell cell;
    private Paragraph paragraph;
    private final String reportPeriod = new ReportingDates().reportPeriod();
    private final int totalQuotes = new CustomerQuoteData().totalQuotes(), totalNAQuotes = new CustomerQuoteData().totalNAQuotes();
    private final String TOP_NAS_CARGO = new CargoTypeData().topNASCommodity();
    private final String TOP_TAL_CARGO = new CargoTypeData().topTalCargo();
    private final String TOP_NAX_CARGO = new CargoTypeData().topNaxCargo();
    private final String TOP_ECAMS_CARGO = new CargoTypeData().topEcamsCargo();
    private final String TOP_NE_CUSTOMER = new CustomerQuoteData().topNECustomer();
    private final String TOP_SE_CUSTOMER = new CustomerQuoteData().topSECustomer();
    private final String TOP_MW_CUSTOMER = new CustomerQuoteData().topMWCustomer();
    private final String TOP_WC_CUSTOMER = new CustomerQuoteData().topWCCustomer();
    private final String TOP_NAS_CUSTOMER = new CustomerQuoteData().topNASCustomer();
    private final String TOP_TAL_CUSTOMER = new CustomerQuoteData().topTALCustomer();
    private final String TOP_ECAMS_CUSTOMER = new CustomerQuoteData().topECAMSCustomer();
    private final String TOP_NAX_CUSTOMER = new CustomerQuoteData().topNAXCustomer();
    private final Map<String, String> TOP_THREE_COMMODITIES = new CargoTypeData().quotesByCommodity();

    private Paragraph firstParagraph() {
        paragraph = new Paragraph("This report contains a summary of RQS activity for week " + reportPeriod + ". The purpose of this report is to better guide Account Managers and Senior Management personnel in their weekly sales activities. The RQS weekly report includes data on cargo bookings, customer feedback, commodity movement, and customer activity. The customers identified in this report are United States based accounts that have been identified as potential targets. It is important to note that while some of the accounts listed in this report already have service contracacts, they are listed due to a high amount of activity outside of their contracted rates.", TEXT_FONT);
        paragraph.setFirstLineIndent(36f);
        return paragraph;
    }

    private Paragraph secondParagraph() {
        paragraph = new Paragraph("During week " + reportPeriod + " a total of " + totalQuotes + " quotes were generated and/or updated from \"K\" Line America offices to customers and regional offices globally. Of these " + totalQuotes + " quotes, " + totalNAQuotes + " quotes were generated and/or updated for known US customers. The top quoted account from each region was " + TOP_SE_CUSTOMER + " (Southeast), " + TOP_NE_CUSTOMER + " (North East), " + TOP_MW_CUSTOMER + " (Mid-West), and " + TOP_WC_CUSTOMER + " (West Coast).", TEXT_FONT);
        paragraph.setFirstLineIndent(36f);
        return paragraph;
    }

    private Paragraph thirdParagraph() {
        // Get the top three commodities from the Map and put into an ArrayList to be ouput into the report. 
        ArrayList<String> topThree = new ArrayList<>();
        TOP_THREE_COMMODITIES.entrySet().stream().forEach((entry) -> {
            topThree.add(entry.getKey());
        });

        paragraph = new Paragraph("The top three commodity types quoted to United States based customers were " + topThree.get(0) + ", " + topThree.get(1) + ", and " + topThree.get(2) + ". The top commodity quoted on each trade lane was " + TOP_NAS_CARGO + " on the North Atlantic Shuttle East Bound, " + TOP_TAL_CARGO + " on the Trans-Atlantic East Bound, " + TOP_ECAMS_CARGO + " on the ECAMS South Bound, and " + TOP_NAX_CARGO + " on the NAX West Bound. The most quoted customer on each trade lane in week " + reportPeriod + " was "+TOP_NAS_CUSTOMER+" on the NAS, "+TOP_TAL_CUSTOMER+" on the TAL, "+TOP_ECAMS_CUSTOMER+" on the ECAMS, and "+TOP_NAX_CUSTOMER+" on the NAX trade lane.", TEXT_FONT);
        paragraph.setFirstLineIndent(36f);
        return paragraph;
    }

    public Paragraph introduction() {

        Paragraph p = new Paragraph();
        p.add(new Paragraph("INTRODUCTION", HEADING_FONT));
        p.add(firstParagraph());
        p.add(secondParagraph());
        p.add(thirdParagraph());

        return p;
    }
}
