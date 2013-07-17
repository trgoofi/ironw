package com.ironw.service.pdf;

import com.ironw.domain.Order;

/**
 * @author trgoofi
 */
public interface PdfService {
  byte[] createPdfOf(Order order);
}
