package ece155b.data;

public class SellSupply
{
	public Supply supply;
	public int amountAvailable;

	public String toXML()
	{
		String returnstr="";
		returnstr += "<SellSupply>";
			returnstr += "<SupplyName>"+supply.name+"</SupplyName>";
			returnstr += "<SupplyPrice>"+supply.price+"</SupplyPrice>";
			returnstr += "<SupplyAmountAvailable>"+amountAvailable+"</SupplyAmountAvailable>";
		returnstr += "</SellSupply>";
		return returnstr;
	}

	public String toString()
	{
		String returnstr="";
		returnstr += "|| SellSupply\n";
			returnstr += "||| SupplyName: "+supply.name+"\n";
			returnstr += "||| SupplyPrice: "+supply.price+"\n";
			returnstr += "||| SupplyAmountAvailable: "+amountAvailable+"\n";
		return returnstr+"\n";
	}
}