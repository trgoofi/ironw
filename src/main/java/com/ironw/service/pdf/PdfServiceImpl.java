package com.ironw.service.pdf;

import com.ironw.domain.Order;
import com.ironw.domain.OrderItem;
import com.ironw.domain.Ware;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @author trgoofi
 */
@Service
public class PdfServiceImpl implements PdfService {
  @Inject private PrintConfig config;

  @Override
  public byte[] createPdfOf(Order order) {
    try {
      Font font = FontUtil.createFont(config.getContentFontSize());
      Rectangle pageSize = new Rectangle(config.getPageWidth(), config.getPageHeight());
      Document doc = new Document(pageSize, 0, 0, config.getTableMarginTop(), config.getTableMarginBottom());
      ByteArrayOutputStream ostream = new ByteArrayOutputStream();
      PdfWriter writer = PdfWriter.getInstance(doc, ostream);
      writer.setBoxSize("art", pageSize);
      writer.setPageEvent(new PdfHeaderFooterEvent(order, config));
      doc.open();

      PdfPTable table = new PdfPTable(7);
      table.setTotalWidth(config.getTableColumnWidths());
      table.setLockedWidth(true);
      table.setHeaderRows(2);
      table.setFooterRows(1);

      String[] tableHeads= {"编号", "商品", "单位", "数量", "单价", "实价", "金额"};
      for (String head : tableHeads) {
        PdfPCell cell = createCell(head, font, Element.ALIGN_CENTER);
        table.addCell(cell);
      }

      String total = moneyStyle(order.getTotal());
      PdfPCell tableFoot = createCell(String.format("总金额：￥%s", total), font, Element.ALIGN_RIGHT);
      tableFoot.setColspan(7);
      table.addCell(tableFoot);

      for (OrderItem orderItem : order.getItems()) {
        Ware ware = orderItem.getWare();
        table.addCell(createCell(ware.getCode(), font, Element.ALIGN_LEFT));
        table.addCell(createCell(ware.getName(), font, Element.ALIGN_LEFT));
        table.addCell(createCell(ware.getUnits(), font, Element.ALIGN_CENTER));
        table.addCell(createCell(atMostTwoFraction(orderItem.getQuantity()), font, Element.ALIGN_RIGHT));
        table.addCell(createCell(moneyStyle(orderItem.getPrice()), font, Element.ALIGN_RIGHT));
        table.addCell(createCell(moneyStyle(orderItem.getWare().getPrice().getRetail()), font, Element.ALIGN_RIGHT));
        table.addCell(createCell(moneyStyle(orderItem.getTotal()), font, Element.ALIGN_RIGHT));
      }

      doc.add(table);
      doc.close();

      return ostream.toByteArray();
    } catch (DocumentException e) {
      throw new RuntimeException(e);
    }
  }


  private PdfPCell createCell(String content, Font font, int alignment) {
    PdfPCell cell = new PdfPCell(new Phrase(content, font));
    cell.setBorderWidth(config.getTableBorderWidth());
    cell.setPadding(1);
    cell.setHorizontalAlignment(alignment);
    return cell;
  }

  private String atMostTwoFraction(BigDecimal decimal) {
    DecimalFormat df = new DecimalFormat("#,##0.##");
    String result = df.format(decimal);
    return result;
  }

  private String moneyStyle(BigDecimal decimal) {
    decimal.setScale(2, RoundingMode.HALF_UP);
    DecimalFormat df = new DecimalFormat("#,##0.00");
    String result = df.format(decimal);
    return result;
  }
}
