package com.zhengbing.learn.boot.util.pdf;

import com.zhengbing.learn.boot.util.pdf.replace.PdfReplacer;

/**
 * @Description:
 * @author: zhengbing_vendor
 * @date: 2019/7/2316:35
 */
public class PdfUtils3 {

    public static void main(String[] args) throws Exception {
        PdfReplacer textReplacer = new PdfReplacer("I://test.pdf");
        textReplacer.replaceText("陈坤", "小白");
        textReplacer.replaceText("本科", "社会大学");
        textReplacer.replaceText("0755-29493863", "15112345678");
        textReplacer.toPdf("I://ticket_out.pdf");
    }
}
