package ece155b.test;

import ece155b.data.Distributor;
import ece155b.data.Supply;
import ece155b.data.UserData;
import ece155b.data.SellSupply;
import ece155b.data.NeedSupply;
import ece155b.xml.XMLParser;
import java.io.*;
import javax.xml.parsers.*;

public class DemoXML {
    
    public static void main(String [] args) {
       // new DemoXML(args);
    	new DemoXML(System.getProperty("user.dir") + File.separator + "test.xml");
    }
    
    public DemoXML(String  args) {
    	String fileurl = UserData.fileDir + "hello.xml";

    	Distributor dist = new Distributor();
    	dist.name = "The Company";
    	dist.address = "Address";
    	dist.contact = "Contact me";

    	SellSupply ssupply = new SellSupply();
    	ssupply.supply = new Supply();
    	ssupply.supply.name = "apple";
    	ssupply.supply.price = 35.05;
    	ssupply.amountAvailable = 100;
    	
    	NeedSupply nsupply = new NeedSupply();
    	nsupply.supply = new Supply();
    	nsupply.supply.name = "banaan";
    	nsupply.amountNeeded = 50;
    	
    	dist.addSellItem(ssupply);
    	dist.addNeedItem(nsupply);
    	
    	System.out.println (dist);
    	toXmlFile (dist,fileurl);
    	System.out.println (readXmlFile(fileurl));
    }


    
    public void toXmlFile(Distributor dist, String url)
    {
    	try
	    {
	    	File xmlfile = new File(url);
    		BufferedWriter br = new BufferedWriter(new FileWriter(xmlfile));
    			br.write("<?xml version='1.0' ?>");
    			br.write(dist.toXML());
    		br.close();
	    }
	    catch (Exception ex)
	    {
	    	System.out.println ("Exception:"+ex);
	    }
    }

    public Distributor readXmlFile(String url)
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
	    return myparser.distributor;
    }

    /**/
}