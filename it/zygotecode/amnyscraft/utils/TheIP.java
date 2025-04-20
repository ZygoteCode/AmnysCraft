package it.zygotecode.amnyscraft.utils;

public class TheIP
{
	private String IP;
	private int strike;
	private String name;
	
	public TheIP(String IP, int strike, String name)
	{
		this.IP = IP;
		this.strike = strike;
		this.name = name;
	}

	public String getIP()
	{
		return IP;
	}

	public void setIP(String iP)
	{
		IP = iP;
	}

	public int getStrike()
	{
		return strike;
	}

	public void setStrike(int strike)
	{
		this.strike = strike;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}