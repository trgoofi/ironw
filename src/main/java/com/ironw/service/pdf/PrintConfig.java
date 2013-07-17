package com.ironw.service.pdf;

/**
 * @author trgoofi
 */
public class PrintConfig {
  private String title;
  private String phone;
  private String address;

  private float pageWidth;
  private float pageHeight;

  private float leading;

  private float marginLeft;
  private float marginRight;
  private float marginTop;
  private float marginBottom;

  private float titleFontSize;
  private float subTitleFontSize;
  private float contentFontSize;

  private float rightColumnOffset;
  private float centerColumnOffset;


  private float tableMarginTop;
  private float tableMarginBottom;
  private float tableBorderWidth;
  private float[] tableColumnWidths;


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public float getPageWidth() {
    return pageWidth;
  }

  public void setPageWidth(float pageWidth) {
    this.pageWidth = pageWidth;
  }

  public float getPageHeight() {
    return pageHeight;
  }

  public void setPageHeight(float pageHeight) {
    this.pageHeight = pageHeight;
  }

  public float getLeading() {
    return leading;
  }

  public void setLeading(float leading) {
    this.leading = leading;
  }

  public float getMarginLeft() {
    return marginLeft;
  }

  public void setMarginLeft(float marginLeft) {
    this.marginLeft = marginLeft;
  }

  public float getMarginRight() {
    return marginRight;
  }

  public void setMarginRight(float marginRight) {
    this.marginRight = marginRight;
  }

  public float getMarginTop() {
    return marginTop;
  }

  public void setMarginTop(float marginTop) {
    this.marginTop = marginTop;
  }

  public float getMarginBottom() {
    return marginBottom;
  }

  public void setMarginBottom(float marginBottom) {
    this.marginBottom = marginBottom;
  }

  public float getTitleFontSize() {
    return titleFontSize;
  }

  public void setTitleFontSize(float titleFontSize) {
    this.titleFontSize = titleFontSize;
  }

  public float getSubTitleFontSize() {
    return subTitleFontSize;
  }

  public void setSubTitleFontSize(float subTitleFontSize) {
    this.subTitleFontSize = subTitleFontSize;
  }

  public float getContentFontSize() {
    return contentFontSize;
  }

  public void setContentFontSize(float contentFontSize) {
    this.contentFontSize = contentFontSize;
  }

  public float getRightColumnOffset() {
    return rightColumnOffset;
  }

  public void setRightColumnOffset(float rightColumnOffset) {
    this.rightColumnOffset = rightColumnOffset;
  }

  public float getCenterColumnOffset() {
    return centerColumnOffset;
  }

  public void setCenterColumnOffset(float centerColumnOffset) {
    this.centerColumnOffset = centerColumnOffset;
  }

  public float getTableMarginTop() {
    return tableMarginTop;
  }

  public void setTableMarginTop(float tableMarginTop) {
    this.tableMarginTop = tableMarginTop;
  }

  public float getTableMarginBottom() {
    return tableMarginBottom;
  }

  public void setTableMarginBottom(float tableMarginBottom) {
    this.tableMarginBottom = tableMarginBottom;
  }

  public float getTableBorderWidth() {
    return tableBorderWidth;
  }

  public void setTableBorderWidth(float tableBorderWidth) {
    this.tableBorderWidth = tableBorderWidth;
  }

  public float[] getTableColumnWidths() {
    return tableColumnWidths;
  }

  public void setTableColumnWidths(float[] tableColumnWidths) {
    this.tableColumnWidths = tableColumnWidths;
  }
}
