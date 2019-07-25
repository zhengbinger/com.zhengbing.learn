package com.zhengbing.learn.boot.util.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;

/**
 * @Description:
 * @author: zhengbing_vendor
 * @date: 2019/7/2215:56
 */
public class PdfUtils {

    private PdfWriter writer = null;

    private static final String FILE_PATH = "D:\\劳动合同书.PDF";
    private static final String EMP_NO_TITLE = "员工编号：________";
    private static final String CONTRACT_NAME = "劳动合同书";

    /**
     *  创建文档对象
     */
    private Document document = null;
    private BaseFont baseFont = null;

    public PdfUtils(){
        setDocument();
        setWriter();
        setBaseFont();
    }
    /**
     * 设置Document对象
     */
    private void setDocument(){
        document = new Document(PageSize.A4, 80, 45, 20, 45);
    }

    /**
     * 设置BaseFont基本字体
     */
    private void setBaseFont(){
        try{
            baseFont = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
        }catch ( IOException ie ){
            ie.printStackTrace();
        }catch ( DocumentException de ){
            de.printStackTrace();
        }
    }

    /**
     * 设置 PdfWrite对象
     *
     * @return
     * @throws FileNotFoundException
     * @throws DocumentException
     */
    public void setWriter(){
        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream(FILE_PATH));
            writer.setPageEmpty(false);
        }catch ( FileNotFoundException fe ) {
            fe.printStackTrace();
        }catch ( DocumentException de ){
            de.printStackTrace();
        }
    }

    /**
     * 设置合同页眉页脚
     */
    public void writePageTitleAndFooter(){
        writer.setPageEvent(new SensePdfEvent(CONTRACT_NAME));
    }
    /**
     *  编写PDF合同封面
     */
    public void writePDFCover () {

        try {
            document.open();

            PdfContentByte canvas = writer.getDirectContent();
            Font emp_no_font = new Font( baseFont,10 );
            Phrase emp_no = new Phrase(EMP_NO_TITLE,emp_no_font);
            ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, emp_no, 510, 800, 0);

            Font fileTitleFont = new Font( baseFont,50 );
            Phrase name = new Phrase(CONTRACT_NAME,fileTitleFont);
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, name, 300, 500, 0);

            Font firstBottomFont = new Font( baseFont,14,Font.BOLD );
            Phrase firstParty = new Phrase("甲方：",firstBottomFont);
            Phrase partyB = new Phrase("乙方：",firstBottomFont);
            Phrase dateOfSignIn = new Phrase("签订日期：",firstBottomFont);
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, firstParty, 180, 200, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, partyB, 180, 175, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, dateOfSignIn, 195, 150, 0);

        }catch ( Exception e ){
            e.printStackTrace();
        }
    }

    /**
     * 编写合同内容
     * @return
     */
    public Boolean writeContractContent(){

        try {
            Font contentFont = new Font( baseFont ,12 );
            Font contentBoldFont = new Font( baseFont ,12,Font.BOLD);
            document.newPage();
            Paragraph paragraph = new Paragraph();
            paragraph.setAlignment(Element.ALIGN_LEFT);
            // 段落设置字体
            paragraph.setFont(contentFont);
            // 设置段落前后间距
            paragraph.setSpacingAfter(50);
            paragraph.setSpacingBefore(50);
            // 整段缩进
//           paragraph.setIndentationRight(50);
//           paragraph.setIndentationLeft(50);
            // 首行缩进
            paragraph.setFirstLineIndent(25);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(
                                    new File("D:\\Java\\workspace_zb\\pdf\\src\\main\\java\\com\\sense\\text\\pdf\\topdf\\content.txt"))));
            while ( reader.ready() ){
                paragraph.add( reader.readLine()+"\r\n");
            }

            document.add( paragraph );

            Paragraph prerequisite = new Paragraph();
            prerequisite.setSpacingAfter(50);
            prerequisite.setFont(contentBoldFont);
            prerequisite.add("第一条：合同的前提条件");
            document.add( prerequisite );

            document.newPage();

            document.add(new Paragraph("New page"));
        }catch ( Exception e ){
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 打开文档对象
     */
    private void openDocument(){
        this.document.open();
    }
    /**
     * 关闭文档对象
     */
    private void closeDocument(){
        this.document.close();
    }

    public static void main (String[] args) {
        PdfUtils pdfUtils = new PdfUtils();
        pdfUtils.openDocument();
//        pdfUtils.writePDFCover();
        pdfUtils.writePageTitleAndFooter();
        pdfUtils.writeContractContent();
        pdfUtils.closeDocument();
    }
}
