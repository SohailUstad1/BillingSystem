package uniqu_billing_system.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.layout.SharedContext;

public class HTML2PDF {
	public static boolean convert2pdf(String fileName) throws IOException{
		String htmlBillsPath="Y:\\htmlbillls\\"+fileName+".html";
		String pdfBillsPath="Y:\\pdfbills\\"+fileName+".pdf";
		boolean status=false;
		File htmlFile = new File("Y:\\htmlbillls\\"+fileName+".html");
        Document doc = Jsoup.parse(htmlFile,"UTF-8");
        doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        try {
			OutputStream os = new FileOutputStream("Y:\\pdfbills\\"+fileName+".pdf");
			ITextRenderer renderer = new ITextRenderer();
			SharedContext sharedContext = renderer.getSharedContext();
			sharedContext.setPrint(true);
			sharedContext.setInteractive(false);
			String baseurl = FileSystems.getDefault().getPath("Y:\\pdfbills\\"+fileName+".pdf").toUri().toURL().toString();
			renderer.setDocumentFromString(doc.html(),baseurl);
			renderer.layout();
			renderer.createPDF(os);
			status=true;
			System.out.println("True");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return status;
	}
	
	public static void main(String[] args) throws IOException {
		convert2pdf("37");
	}
	
}
