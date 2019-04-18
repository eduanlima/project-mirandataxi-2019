/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *
 * @author eduan
 */
public class MaskMetric {
   public static String getMask(BigDecimal distance){
       String formatMetric = new DecimalFormat("#,##0.000").format(distance);
       return formatMetric;
   } 
}
