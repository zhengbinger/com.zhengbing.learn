package com.zhengbing.learn.boot.util.pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Description:
 * @author: zhengbing_vendor
 * @date: 2019/7/2315:08
 */
public class PdfUtils2 {

    public static void main (String[] args){
        try{
            PdfUtils2.updatePDFText();
        } catch ( Exception e ){
            e.printStackTrace();
        }
    }

    public void  replacePDFText(String source,String target){

    }

    public static void updatePDFText() throws IOException, DocumentException {
        PdfReader reader = new PdfReader("D:\\test1.pdf");
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("D:\\1.pdf"));
        PdfContentByte canvas = stamper.getUnderContent(1);
        canvas.saveState();
//      canvas.rectangle(36, 786, 66, 16);

//      开始写入文本
        canvas.beginText();
        // 设置字体和大小
        canvas.setFontAndSize(BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED),12);
        // 设置字体颜色
        // canvas.setColorFill(BaseColor.RED);
        // 设置位置
        canvas.setTextMatrix(500, 690);
        // 设置内容
        canvas.showText("9000219" );
        canvas.setTextMatrix(265,140);
        canvas.showText("2019年7月23日");
        canvas.endText();
        canvas.restoreState();

        stamper.close();
        reader.close();
    }
}
