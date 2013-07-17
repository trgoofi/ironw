package com.ironw.service.pdf;

import com.ironw.domain.Order;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.joda.time.DateTime;

/**
 * @author trgoofi
 */
public class PdfHeaderFooterEvent extends PdfPageEventHelper {
  private Order order;
  private PrintConfig config;

  private Font titleFont;
  private Font subTitleFont;
  private Font contentFontSize;

  public PdfHeaderFooterEvent(Order order, PrintConfig config) {
    this.order = order;
    this.config = config;

    this.titleFont = FontUtil.createFont(config.getTitleFontSize());
    this.subTitleFont = FontUtil.createFont(config.getSubTitleFontSize());
    this.contentFontSize = FontUtil.createFont(config.getContentFontSize());
  }

  @Override
  public void onEndPage(PdfWriter writer, Document document) {
    Rectangle box = writer.getBoxSize("art");
    PdfContentByte canvas = writer.getDirectContent();

    float yOffset = 0;

    yOffset += config.getTitleFontSize();
    ColumnText.showTextAligned(canvas,
            Element.ALIGN_CENTER,
            new Phrase(config.getTitle(), titleFont),
            box.getRight() / 2,
            box.getTop(config.getMarginTop()) - yOffset,
            0);



    yOffset += config.getSubTitleFontSize() + config.getLeading();
    ColumnText.showTextAligned(canvas,
            Element.ALIGN_CENTER,
            new Phrase(String.format("电话：%s   地址：%s", config.getPhone(), config.getAddress()), subTitleFont),
            box.getRight() / 2,
            box.getTop(config.getMarginTop()) - yOffset,
            0);



    yOffset += config.getContentFontSize() + config.getLeading();
    ColumnText.showTextAligned(canvas,
            Element.ALIGN_LEFT,
            new Phrase(String.format("客户：%s", order.getClient().getName()), contentFontSize),
            box.getLeft(config.getMarginLeft()),
            box.getTop(config.getMarginTop()) - yOffset,
            0);

    ColumnText.showTextAligned(canvas,
            Element.ALIGN_LEFT,
            new Phrase(String.format("联系人：%s", order.getClient().getContacts()), contentFontSize),
            box.getRight() / 2 - config.getCenterColumnOffset(),
            box.getTop(config.getMarginTop()) - yOffset,
            0);

    ColumnText.showTextAligned(canvas,
            Element.ALIGN_LEFT,
            new Phrase(String.format("订单号：%s", order.getNumber()), contentFontSize),
            box.getRight(config.getMarginRight()) - config.getRightColumnOffset(),
            box.getTop(config.getMarginTop()) - yOffset,
            0);



    yOffset += config.getContentFontSize() + config.getLeading();
    ColumnText.showTextAligned(canvas,
            Element.ALIGN_LEFT,
            new Phrase(String.format("地址：%s", order.getClient().getAddress()), contentFontSize),
            box.getLeft(config.getMarginLeft()),
            box.getTop(config.getMarginTop()) - yOffset,
            0);

    ColumnText.showTextAligned(canvas,
            Element.ALIGN_LEFT,
            new Phrase(String.format("电话：%s", order.getClient().getPhone()), contentFontSize),
            box.getRight() / 2  - config.getCenterColumnOffset(),
            box.getTop(config.getMarginTop()) - yOffset,
            0);

    String orderDateString = new DateTime(order.getCreateAt()).toString("yyyy.MM.dd");
    ColumnText.showTextAligned(canvas,
            Element.ALIGN_LEFT,
            new Phrase(String.format("订单日期：%s", orderDateString), contentFontSize),
            box.getRight(config.getMarginRight()) - config.getRightColumnOffset(),
            box.getTop(config.getMarginTop()) - yOffset,
            0);



    ColumnText.showTextAligned(canvas,
            Element.ALIGN_LEFT,
            new Phrase("制单：____________   审核：____________   收货单位（签章）：____________", contentFontSize),
            box.getLeft(config.getMarginLeft()),
            box.getBottom(config.getMarginBottom()), 0);


  }
}
