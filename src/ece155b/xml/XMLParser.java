package ece155b.xml;

import ece155b.data.Distributor;
import ece155b.data.SellSupply;
import ece155b.data.NeedSupply;
import ece155b.data.Supply;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class XMLParser extends DefaultHandler {
	public Distributor distributor;
	private SellSupply ssupply;
	private NeedSupply nsupply;
	private Supply supply;
	private StringBuffer accumulator = new StringBuffer();    

	public XMLParser() {
		distributor = new Distributor();
	}
	public void characters(char[] buffer, int start, int length) {
		accumulator.append(buffer, start, length);
	}

	// - - - - - - - - - - - - - - - - - - - - -
	//	Each time a new tag is opened,
	//		this method is called
	public void startElement(String uri,String lname, String name, Attributes attributes)
	{
		// - - - - - - - - - - - - - - - - - - - - -
		//	New SellSupply starts
		if(name.equals ("SellSupply"))
		{
			ssupply = new SellSupply();
			supply = new Supply();
			ssupply.supply = supply;
		}	
		// - - - - - - - - - - - - - - - - - - - - -
		//	New NeedSupply starts
		else if(name.equals ("NeedSupply"))
		{
			nsupply = new NeedSupply();
			supply = new Supply();
			nsupply.supply = supply;
		}
		// - - - - - - - - - - - - - - - - - - - - -
		// Reset accumulator
		accumulator.setLength(0);
	}

	public void endElement(String uri,String lname,String name)
	{
		String value = accumulator.toString();

		switch(name)
		{
		// - - - - - - - - - - - - - - - - - - - - -
		// 	Company informations
		case "CompanyName":
			distributor.name = value;
			break;
		case "CompanyAddress":
			distributor.address = value;
			break;
		case "CompanyContact":
			distributor.contact = value;
			break;
		// - - - - - - - - - - - - - - - - - - - - -
		// 	Add sell supply and need supply objects
		case "SellSupply":
			ssupply.supply = supply;
			distributor.addSellItem(ssupply);
			break;
		case "NeedSupply":
			nsupply.supply = supply;
			distributor.addNeedItem(nsupply);
			break;
		// - - - - - - - - - - - - - - - - - - - - -
		// 	Set parameters of Supply Object
		case "SupplyName":
			supply.name = value;
			break;
		case "SupplyPrice":
			supply.price = Double.parseDouble(value);
			break;
		// - - - - - - - - - - - - - - - - - - - - -
		// 	Set parameters of SellSupply and NeedSupply Object
		case "SupplyAmountAvailable":
			ssupply.amountAvailable = Integer.parseInt(value);
			break;			
		case "SupplyAmountNeeded":
			nsupply.amountNeeded = Integer.parseInt(value);
			break;	
		}		

	}
}