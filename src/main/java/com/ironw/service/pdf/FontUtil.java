package com.ironw.service.pdf;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;


/**
 * @author trgoofi
 */
public class FontUtil {

  public static Font createFont(float size) {
    try {
      BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
      Font font = new Font(bf, size);
      return font;
    } catch (Exception e) {
      throw new RuntimeException("Could not create font", e);
    }
  }
}
