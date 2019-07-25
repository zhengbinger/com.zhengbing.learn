package com.zhengbing.learn.boot.util.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @Description:
 * @author: zhengbing_vendor
 * @date: 2019/7/22 20:13
 */
public class SensePdfEvent extends PdfPageEventHelper {

    private String DOC_HEADER = "";

    public SensePdfEvent () {
        super();
    }

    public SensePdfEvent (String contractName) {
        super();
        this.DOC_HEADER = contractName;
    }

    @Override
    public void onOpenDocument (PdfWriter writer, Document document) {
        super.onOpenDocument(writer, document);
    }

    @Override
    public void onStartPage (PdfWriter writer, Document document) {
        super.onStartPage(writer, document);
    }

    @Override
    public void onEndPage (PdfWriter writer, Document document) {
        super.onEndPage(writer, document);
        PdfContentByte cb = writer.getDirectContent();
        cb.saveState();

        cb.beginText();
        BaseFont bf = null;
        try {
            bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        cb.setFontAndSize(bf, 10);

        //Header
        float x = document.top(-1);
        //右
        cb.showTextAligned(PdfContentByte.ALIGN_RIGHT,
                DOC_HEADER,
                document.right(), x, 0);
        //Footer
        float y = document.bottom(-20);

        //中
        cb.showTextAligned(PdfContentByte.ALIGN_CENTER,
                "- " + writer.getPageNumber()  + " -",
                (document.right() + document.left()) / 2,
                y, 0);

        cb.endText();
        cb.restoreState();
    }

    @Override
    public void onCloseDocument (PdfWriter writer, Document document) {
        super.onCloseDocument(writer, document);
    }

    @Override
    public void onParagraph (PdfWriter writer, Document document, float paragraphPosition) {
        super.onParagraph(writer, document, paragraphPosition);
    }

    @Override
    public void onParagraphEnd (PdfWriter writer, Document document, float paragraphPosition) {
        super.onParagraphEnd(writer, document, paragraphPosition);
    }

    @Override
    public void onChapter (PdfWriter writer, Document document, float paragraphPosition, Paragraph title) {
        super.onChapter(writer, document, paragraphPosition, title);
    }

    @Override
    public void onChapterEnd (PdfWriter writer, Document document, float position) {
        super.onChapterEnd(writer, document, position);
    }

    @Override
    public void onSection (PdfWriter writer, Document document, float paragraphPosition, int depth, Paragraph title) {
        super.onSection(writer, document, paragraphPosition, depth, title);
    }

    @Override
    public void onSectionEnd (PdfWriter writer, Document document, float position) {
        super.onSectionEnd(writer, document, position);
    }

    @Override
    public void onGenericTag (PdfWriter writer, Document document, Rectangle rect, String text) {
        super.onGenericTag(writer, document, rect, text);
    }
}
