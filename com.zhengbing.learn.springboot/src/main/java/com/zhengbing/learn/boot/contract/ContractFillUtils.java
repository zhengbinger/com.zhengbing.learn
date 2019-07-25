package com.zhengbing.learn.boot.contract;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author zhengbing
 * @descript
 * @date 2019-07-23
 * @email mydreambing@126.com
 * @since version 1.0
 */
public class ContractFillUtils {

    private String IN_FILE_PATH = null;
    //    private String OUT_FILE_PATH = null;
    private PdfReader reader = null;
    private PdfStamper stamper = null;

    /**
     * 填充合同内容
     *
     * @param fillLocations
     * @return
     */
    public InputStream fillContract(File file, List<FillLocation> fillLocations) {
        try {
            reader = new PdfReader(file.getAbsolutePath());
            stamper = new PdfStamper(reader, new FileOutputStream(""));

            // 获取合同页数
            int pages = reader.getNumberOfPages();

            // 根据合同页获取合同填写项
            for (int i = 0; i < pages; i++) {

                PdfContentByte canvas = stamper.getUnderContent(i);

                // 开始写入文本
                canvas.beginText();
                canvas.setFontAndSize(BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED), 12);
                // 填写合同项
                for (FillLocation fillLocation : fillLocations) {
                    //设置字体和大小
                    if (fillLocation.getLotPage() == i) {
                        canvas.setTextMatrix(fillLocation.getLotX(), fillLocation.getLotY());
                        canvas.showText(fillLocation.getContent());
                    }
                }
                canvas.endText();

            }

        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (DocumentException de) {
            de.printStackTrace();
        } finally {
            if (null != reader) {
                reader.close();
            }
            if (null != stamper) {
                try {
                    stamper.close();
                } catch (IOException ie) {
                    ie.printStackTrace();
                } catch (DocumentException de) {
                    de.printStackTrace();
                }
            }
        }
        return null;
    }
}
