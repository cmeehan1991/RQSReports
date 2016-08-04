/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WeeklyReport.Sections;

import Dates.ReportingDates;
import static Styling.Fonts.*;
import WeeklyReport.CustomerQuoteData;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.util.Map;

/**
 *
 * @author cmeehan
 */
public class RegionalQuoteData {

    private final int DECLINES = new CustomerQuoteData().declinedQuotes();
    private final int BOOKINGS = new CustomerQuoteData().bookedQuotes();
    private final int FEEDBACK = new CustomerQuoteData().feedbackQuotes();
    private final int PENDING_RESPONSE = new CustomerQuoteData().pendingQuotes();
    private final String REPORT_WEEK = new ReportingDates().reportPeriod();
    private final Map<Map<String, String>, Map<String, String>> SOUTH_EAST_QUOTES = new CustomerQuoteData().southeastQuotes();
    private final Map<Map<String, String>, Map<String, String>> NORTH_EAST_QUOTES = new CustomerQuoteData().northEastQuotes();
    private final Map<Map<String, String>, Map<String, String>> MID_WEST_QUOTES = new CustomerQuoteData().midWestQuotes();
    private final Map<Map<String, String>, Map<String, String>> WEST_COAST_QUOTES = new CustomerQuoteData().westCoastQuotes();
    private PdfPCell cell, companyCell, tradeCell, unitCell, cubicMetersCell;

    public PdfPTable regionalAnalysis() {
        PdfPTable table = new PdfPTable(1);
        table.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10f);

        cell = new PdfPCell(new Phrase("Regional Quote Data", SECTION_HEADING));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderWidthBottom(2f);
        cell.setPaddingBottom(10f);
        table.addCell(cell);

        String pending = null;
        
        if (PENDING_RESPONSE == 1) {
            pending = PENDING_RESPONSE + " is pending further action.";
        } else {
            pending = PENDING_RESPONSE + " are pending further action.";
        }

        cell = new PdfPCell(new Phrase("In week " + new ReportingDates().reportPeriod() + " a total of " + new CustomerQuoteData().totalNAQuotes() + " quotes were generated through RQS to North American customers (not including Canada). Of these quotes there were "+DECLINES+" declines, " + BOOKINGS+" bookings, and " + FEEDBACK + " received customer feedback, and " + pending, TEXT_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        return table;

    }

    
    public PdfPTable southeastTable() {
        PdfPTable table = new PdfPTable(4);
        table.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        table.setWidthPercentage(100f);

        cell = new PdfPCell(new Phrase("South East", SUBHEADING_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(4);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Company Name", COLUMN_HEADER));
        cell.setPaddingBottom(5f);
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderWidthBottom(2f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Trade Lane", COLUMN_HEADER));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingBottom(5f);
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderWidthBottom(2f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Unit Count", COLUMN_HEADER));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPaddingBottom(5f);
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderWidthBottom(2f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Cubic Meters", COLUMN_HEADER));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPaddingBottom(5f);
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderWidthBottom(2f);
        table.addCell(cell);

        Map<Map<String, String>, Map<String, String>> m = SOUTH_EAST_QUOTES;
        m.entrySet().stream().map((entry) -> {
            Map<String, String> m1 = entry.getKey();
            m1.entrySet().stream().map((pair) -> {
                companyCell = new PdfPCell(new Phrase(String.valueOf(pair.getValue()), TEXT_FONT));
                return pair;
            }).forEach((pair) -> {
                tradeCell = new PdfPCell(new Phrase(String.valueOf(pair.getKey()), TEXT_FONT));
            });
            return entry;
        }).map((entry) -> {
            companyCell.setColspan(1);
            tradeCell.setColspan(1);
            return entry;
        }).map((entry) -> {
            companyCell.setBorder(Rectangle.BOTTOM);
            tradeCell.setBorder(Rectangle.BOTTOM);
            return entry;
        }).map((entry) -> {
            table.addCell(companyCell);
            table.addCell(tradeCell);
            Map<String, String> m1 = entry.getValue();
            m1.entrySet().stream().map((pair) -> {
                unitCell = new PdfPCell(new Phrase(String.valueOf(pair.getKey()), TEXT_FONT));
                return pair;
            }).forEach((pair) -> {
                cubicMetersCell = new PdfPCell(new Phrase(String.valueOf(pair.getValue()), TEXT_FONT));
            });
            return entry;
        }).map((_item) -> {
            unitCell.setColspan(1);
            cubicMetersCell.setColspan(1);
            return _item;
        }).map((_item) -> {
            unitCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cubicMetersCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            return _item;
        }).map((_item) -> {
            unitCell.setBorder(Rectangle.BOTTOM);
            cubicMetersCell.setBorder(Rectangle.BOTTOM);
            return _item;
        }).forEach((_item) -> {
            table.addCell(unitCell);
            table.addCell(cubicMetersCell);
        });
        return table;
    }

    public PdfPTable northEastTable() {
        PdfPTable table = new PdfPTable(4);
        table.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10f);

        cell = new PdfPCell(new Phrase("North East", SUBHEADING_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(4);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Company Name", COLUMN_HEADER));
        cell.setPaddingBottom(5f);
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderWidthBottom(2f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Trade Lane", COLUMN_HEADER));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingBottom(5f);
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderWidthBottom(2f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Unit Count", COLUMN_HEADER));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPaddingBottom(5f);
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderWidthBottom(2f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Cubic Meters", COLUMN_HEADER));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPaddingBottom(5f);
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderWidthBottom(2f);
        table.addCell(cell);

        Map<Map<String, String>, Map<String, String>> m = NORTH_EAST_QUOTES;
        m.entrySet().stream().map((entry) -> {
            Map<String, String> m1 = entry.getKey();
            m1.entrySet().stream().map((pair) -> {
                companyCell = new PdfPCell(new Phrase(String.valueOf(pair.getValue()), TEXT_FONT));
                return pair;
            }).forEach((pair) -> {
                tradeCell = new PdfPCell(new Phrase(String.valueOf(pair.getKey()), TEXT_FONT));
            });
            return entry;
        }).map((entry) -> {
            companyCell.setColspan(1);
            tradeCell.setColspan(1);
            return entry;
        }).map((entry) -> {
            companyCell.setBorder(Rectangle.BOTTOM);
            tradeCell.setBorder(Rectangle.BOTTOM);
            return entry;
        }).map((entry) -> {
            table.addCell(companyCell);
            table.addCell(tradeCell);
            Map<String, String> m1 = entry.getValue();
            m1.entrySet().stream().map((pair) -> {
                unitCell = new PdfPCell(new Phrase(String.valueOf(pair.getKey()), TEXT_FONT));
                return pair;
            }).forEach((pair) -> {
                cubicMetersCell = new PdfPCell(new Phrase(String.valueOf(pair.getValue()), TEXT_FONT));
            });
            return entry;
        }).map((_item) -> {
            unitCell.setColspan(1);
            cubicMetersCell.setColspan(1);
            return _item;
        }).map((_item) -> {
            unitCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cubicMetersCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            return _item;
        }).map((_item) -> {
            unitCell.setBorder(Rectangle.BOTTOM);
            cubicMetersCell.setBorder(Rectangle.BOTTOM);
            return _item;
        }).forEach((_item) -> {
            table.addCell(unitCell);
            table.addCell(cubicMetersCell);
        });
        return table;
    }

    public PdfPTable midWestTable() {
        PdfPTable table = new PdfPTable(4);
        table.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10f);

        cell = new PdfPCell(new Phrase("Mid West", SUBHEADING_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(4);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Company Name", COLUMN_HEADER));
        cell.setPaddingBottom(5f);
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderWidthBottom(2f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Trade Lane", COLUMN_HEADER));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingBottom(5f);
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderWidthBottom(2f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Unit Count", COLUMN_HEADER));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPaddingBottom(5f);
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderWidthBottom(2f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Cubic Meters", COLUMN_HEADER));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPaddingBottom(5f);
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderWidthBottom(2f);
        table.addCell(cell);

        Map<Map<String, String>, Map<String, String>> m = MID_WEST_QUOTES;
        m.entrySet().stream().map((entry) -> {
            Map<String, String> m1 = entry.getKey();
            m1.entrySet().stream().map((pair) -> {
                companyCell = new PdfPCell(new Phrase(String.valueOf(pair.getValue()), TEXT_FONT));
                return pair;
            }).forEach((pair) -> {
                tradeCell = new PdfPCell(new Phrase(String.valueOf(pair.getKey()), TEXT_FONT));
            });
            return entry;
        }).map((entry) -> {
            companyCell.setColspan(1);
            tradeCell.setColspan(1);
            return entry;
        }).map((entry) -> {
            companyCell.setBorder(Rectangle.BOTTOM);
            tradeCell.setBorder(Rectangle.BOTTOM);
            return entry;
        }).map((entry) -> {
            table.addCell(companyCell);
            table.addCell(tradeCell);
            Map<String, String> m1 = entry.getValue();
            m1.entrySet().stream().map((pair) -> {
                unitCell = new PdfPCell(new Phrase(String.valueOf(pair.getKey()), TEXT_FONT));
                return pair;
            }).forEach((pair) -> {
                cubicMetersCell = new PdfPCell(new Phrase(String.valueOf(pair.getValue()), TEXT_FONT));
            });
            return entry;
        }).map((_item) -> {
            unitCell.setColspan(1);
            cubicMetersCell.setColspan(1);
            return _item;
        }).map((_item) -> {
            unitCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cubicMetersCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            return _item;
        }).map((_item) -> {
            unitCell.setBorder(Rectangle.BOTTOM);
            cubicMetersCell.setBorder(Rectangle.BOTTOM);
            return _item;
        }).forEach((_item) -> {
            table.addCell(unitCell);
            table.addCell(cubicMetersCell);
        });
        return table;
    }

    public PdfPTable westCoastTable() {
        PdfPTable table = new PdfPTable(4);
        table.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10f);

        cell = new PdfPCell(new Phrase("West Coast", SUBHEADING_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(4);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Company Name", COLUMN_HEADER));
        cell.setPaddingBottom(5f);
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderWidthBottom(2f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Trade Lane", COLUMN_HEADER));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingBottom(5f);
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderWidthBottom(2f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Unit Count", COLUMN_HEADER));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPaddingBottom(5f);
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderWidthBottom(2f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Cubic Meters", COLUMN_HEADER));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPaddingBottom(5f);
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderWidthBottom(2f);
        table.addCell(cell);

        Map<Map<String, String>, Map<String, String>> m = WEST_COAST_QUOTES;
        m.entrySet().stream().map((entry) -> {
            Map<String, String> m1 = entry.getKey();
            m1.entrySet().stream().map((pair) -> {
                companyCell = new PdfPCell(new Phrase(String.valueOf(pair.getValue()), TEXT_FONT));
                return pair;
            }).forEach((pair) -> {
                tradeCell = new PdfPCell(new Phrase(String.valueOf(pair.getKey()), TEXT_FONT));
            });
            return entry;
        }).map((entry) -> {
            companyCell.setColspan(1);
            tradeCell.setColspan(1);
            return entry;
        }).map((entry) -> {
            companyCell.setBorder(Rectangle.BOTTOM);
            tradeCell.setBorder(Rectangle.BOTTOM);
            return entry;
        }).map((entry) -> {
            table.addCell(companyCell);
            table.addCell(tradeCell);
            Map<String, String> m1 = entry.getValue();
            m1.entrySet().stream().map((pair) -> {
                unitCell = new PdfPCell(new Phrase(String.valueOf(pair.getKey()), TEXT_FONT));
                return pair;
            }).forEach((pair) -> {
                cubicMetersCell = new PdfPCell(new Phrase(String.valueOf(pair.getValue()), TEXT_FONT));
            });
            return entry;
        }).map((_item) -> {
            unitCell.setColspan(1);
            cubicMetersCell.setColspan(1);
            return _item;
        }).map((_item) -> {
            unitCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cubicMetersCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            return _item;
        }).map((_item) -> {
            unitCell.setBorder(Rectangle.BOTTOM);
            cubicMetersCell.setBorder(Rectangle.BOTTOM);
            return _item;
        }).forEach((_item) -> {
            table.addCell(unitCell);
            table.addCell(cubicMetersCell);
        });
        return table;
    }
}
