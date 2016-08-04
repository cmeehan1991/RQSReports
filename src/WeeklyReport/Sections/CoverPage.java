/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WeeklyReport.Sections;

import Dates.ReportingDates;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.IOException;

/**
 *
 * @author cmeehan
 */
public class CoverPage {

    private PdfPTable table;
    private PdfPCell cell;
    private Image image;
    private PdfContentByte canvas;
    private final Font TITLE_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.UNDERLINE);
    private final String REPORT_WEEK = new ReportingDates().reportPeriod();

    public Image backgroundImage() {
        try {
            image = Image.getInstance(getClass().getClassLoader().getResource("resources/P6200107.jpg"));
            image.scaleAbsolute(PageSize.A4);
            image.setAbsolutePosition(0, 0);
            return image;
        } catch (BadElementException | IOException ex) {
            return image;
        }
    }

    public PdfPTable coverTable() {
        table = new PdfPTable(1);
        cell = new PdfPCell(new Phrase("RQS Week "+REPORT_WEEK+" Quoting Report", TITLE_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        return table;
    }

}
