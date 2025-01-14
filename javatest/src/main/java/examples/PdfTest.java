package examples;

import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import java.io.File;
import java.io.FileOutputStream;

public class PdfTest {
    public static void main(String[] args) throws Exception {
        PdfReader reader = new PdfReader("javatest/src/main/resources/pdf/pdf1.pdf");
        File outputFile = new File("javatest/src/main/resources/pdf/pdf-out.pdf");
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outputFile));
        PdfContentByte content = stamper.getUnderContent(1);//1 for the first page
        BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ITALIC, BaseFont.CP1250, BaseFont.EMBEDDED);
        content.beginText();
        content.setFontAndSize(bf, 18);
        content.showTextAligned(PdfContentByte.ALIGN_CENTER, "JavaCodeGeeks", 250, 650, 0);
        content.endText();
        stamper.close();
        reader.close();
    }
}
