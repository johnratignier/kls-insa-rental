package com.kls.manage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.fop.apps.FopFactory;
import org.xml.sax.SAXException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.kls.configuration.DAOFactory;

public class LocationPDF{
	private DAOFactory daofactory;
	private FopFactory fopfactory;

	public LocationPDF(){

	}

	public void generationPDF(String c, String codeHtml, String nomFichier) throws IOException, SAXException, DocumentException{
		Document document = new Document();
		// step 2
		String ccc = "C:\\Users\\UTILISATEUR\\Documents\\Programmation JAVA EE\\kls\\WebContent\\facturePDF\\";
		File file = new File(c+"/"+nomFichier+".pdf");
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
		// step 3
		document.open();
		// step 4

		/*XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
		InputStream is = new ByteArrayInputStream(codeHtml.getBytes("UTF-8"));
		worker.parseXHtml(writer, document, is, Charset.forName("UTF-8"), new XMLWorkerFontProvider("resources/fonts/"));
		// step 5
		document.close();*/


		// CSS
		CSSResolver cssResolver =
				XMLWorkerHelper.getInstance().getDefaultCssResolver(true);

		// HTML
		HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
		htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
		htmlContext.setImageProvider(new Base64ImageProvider());

		// Pipelines
		PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
		HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
		CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);

		// XML Worker
		XMLWorker worker = new XMLWorker(css, true);
		XMLParser p = new XMLParser(worker);
		p.parse(new ByteArrayInputStream(codeHtml.getBytes()));

		// step 5
		document.close();
	}

}

