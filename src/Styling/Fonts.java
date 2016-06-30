/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Styling;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;

/**
 *
 * @author cmeehan
 */
public class Fonts {
    public static final Font HEADING_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    public static final Font SUBHEADING_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLDITALIC);
    public static final Font SECTION_HEADING = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
    public static final Font COLUMN_HEADER = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    public static final Font TEXT_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
    
}
