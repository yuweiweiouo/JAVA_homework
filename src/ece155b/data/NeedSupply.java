package ece155b.data;

public class NeedSupply
{
	public Supply supply = new Supply();
	public int amountNeeded;
	
	public String toXML()
	{
		String returnstr="";
		returnstr += "<NeedSupply>";
			returnstr += "<SupplyName>"+supply.name+"</SupplyName>";
			returnstr += "<SupplyAmountNeeded>"+amountNeeded+"</SupplyAmountNeeded>";
		returnstr += "</NeedSupply>";
		return returnstr;
	}

	public String toString()
	{
		String returnstr="";
		returnstr += "|| NeedSupply\n";
			returnstr += "||| SupplyName: "+supply.name+"\n";
			returnstr += "||| SupplyAmountNeeded: "+amountNeeded+"\n";
		return returnstr+"\n";
	}
}