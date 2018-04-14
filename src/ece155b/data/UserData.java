package ece155b.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import ece155b.xml.XMLParser;

public class UserData{
	public Distributor distributor = new Distributor();
	public static String fileDir = System.getProperty("user.dir")+ File.separator+"SavedXml"+ File.separator;

	public void toXmlFile(String url)
	{
		try
		{
			new File("SavedXml").mkdirs();
			File xmlfile = new File(fileDir + url);
			BufferedWriter br = new BufferedWriter(new FileWriter(xmlfile));
			br.write("<?xml version='1.0' ?>");
			br.write(distributor.toXML());
			br.close();
		}
		catch (Exception ex)
		{
			System.out.println ("Exception:"+ex);
		}
	}

	public void readXmlFile(String url)
	{
		XMLParser myparser = new XMLParser();
		try
		{
			SAXParserFactory factory = SAXParserFactory.newInstance(  );
			SAXParser parser = factory.newSAXParser();
			parser.parse(url,myparser);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		distributor = myparser.distributor;
	}

}
