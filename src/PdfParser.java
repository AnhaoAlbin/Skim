import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class PdfParser {

	public static void parse(String path) throws InvalidPasswordException, IOException {
		StringBuilder sb =  new StringBuilder(); 
		PDDocument document = PDDocument.load(new File(path));
		PDFTextStripperByArea stripper = new PDFTextStripperByArea();
		stripper.setSortByPosition(true);
		PDFTextStripper tStripper = new PDFTextStripper();
		String pdfFileInText = tStripper.getText(document);
		document.close();
		//"/Users/Elliott/Downloads/46906_bonjour.pdf"
		PrintWriter out = new PrintWriter(path.substring(0,path.length() - 4) + ".txt");
		out.print(pdfFileInText);
		out.flush();
		out.close();

	}
}
