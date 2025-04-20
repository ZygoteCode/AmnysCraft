package it.zygotecode.amnyscraft.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class VPNUtils
{
	public static boolean isVPN(String ip)
	{
		try
		{
	        URL url = new URL("");
	        HttpURLConnection con = (HttpURLConnection)url.openConnection();
	        int response = con.getResponseCode();
	        
	        if (response != 200)
	        {
	        	return false;
	        }
	        else
	        {
	            InputStream dstream = con.getInputStream();
	            byte[] buf = new byte[256];
	            int bytesread = dstream.read(buf);
	            
	            if (bytesread < 0)
	            {
	            	return false;
	            }
	            else
	            {
	            	String responseString = new String(buf);
	            	
	            	if (responseString.toLowerCase().contains("y"))
	            	{
	            		return false;
	            	}
	            	else
	            	{
	            		return true;
	            	}
	            }
	        }
		}
		catch (Exception ex)
		{
			return false;
		}
	}
}