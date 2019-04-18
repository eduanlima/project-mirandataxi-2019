/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MaskMonetary {
    public static String getMask(BigDecimal total){
        String totalFormat = new DecimalFormat("#,##0.00").format(total);
        return totalFormat;

    }
}
