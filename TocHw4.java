import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;
import org.json.*;

public class TocHw4 {

	
	  public static  JSONArray readJsonFromUrl(String url) throws IOException, JSONException {
		    InputStream is = new URL(url).openStream();
		    try {
		      BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		      JSONArray jsonRealPrice = new JSONArray(new JSONTokener(rd));
		      
		    //  System.out.println("HELLOW");
		      return jsonRealPrice;
		    } finally {
		      is.close();
		    }
		  }//end read Json array
	  public static void main(String[] args) throws IOException, JSONException {
		  JSONArray json = readJsonFromUrl(args[0]);
		//  System.out.println("HELLOW");
		  String address;
	
		  int maxtimes=0;
		  
		  HashMap<String, HashMap<Integer, Object>> map = new HashMap<String, HashMap<Integer,Object>>();
		  HashMap<String,Integer> addressmothcount = new HashMap<String, Integer>();
		  HashMap<String,Integer> maxaddress = new HashMap<String, Integer>();
		  HashMap<String,Integer> maxprice = new HashMap<String, Integer>();
		  HashMap<String,Integer> miniprice = new HashMap<String, Integer>();
		  //  map.put("key", new HashMap<Integer, Object>());
		   // map.put("key1", new HashMap<String, Object>()); if no this map(key1)-->null
		   // map.get("key").put(10302, 1); 
		  //  map.get("key").put("key3",2);
		//  System.out.println(map.get("key").get(10302));
		 //   System.out.println(map.get("key1"));//---->null
		 

		  for(int i = 0;i<json.length();i++)//for every json object
	    	{
			  int temp;
		
			 
			  String goal="";
			 
			 
			  address=json.getJSONObject(i).getString("跋琿竚┪跋礟");
	//		  System.out.println(address);
			
			  temp=address.indexOf("隔");
			  if(temp>=0)
			  {
				  goal=address.substring(0, temp+1);
			  }
			  else if( (temp=address.indexOf("笵"))>=0)
			  { 
				  goal=address.substring(0, temp+2);
				  
			  }
			  else if ( (temp=address.indexOf("刁"))>=0)
			  {
				  goal=address.substring(0, temp+1);
			  }
			  else if ( (temp=address.indexOf(""))>=0)
			  {
				  goal=address.substring(0, temp+1);
			  }
			if(temp!=-1)
			{
			  if((map.get(goal))==null)//if the road 
			  {
				  map.put(goal, new HashMap<Integer, Object>());
				  temp = json.getJSONObject(i).getInt("ユる");
				  map.get(goal).put(temp,1);
			  }
			  else
			  {
				  temp = json.getJSONObject(i).getInt("ユる");
				  if(map.get(goal).get(temp)==null)
				  {
					  map.get(goal).put(temp,1);
				  }
			  }  
			  int price =json.getJSONObject(i).getInt("羆基じ");
			  	if(maxprice.get(goal)==null){
				  maxprice.put(goal,price);
				  miniprice.put(goal,price);
			  	}
			  	else{
			  		
			  		if(price>maxprice.get(goal)){
			  			maxprice.put(goal,price);
			  		}
			  		if(price<miniprice.get(goal)){
			  			miniprice.put(goal,price);
			  		} 
			  			
			  		
			  	}
			  		
			  
			 }//end if temp ==-1
			}//end for loop for every json object
		  for(String name:map.keySet())
		  {
			  addressmothcount.put(name,map.get(name).size());
			  //System.out.println(name+":"+map.get(name).size());
		  }
		  
		  for(String name:addressmothcount.keySet())
		  {
			  if(maxaddress==null)
			  {
				  maxtimes=addressmothcount.get(name);
			  }
			  else if(addressmothcount.get(name)>maxtimes)
			  {
				  maxtimes=addressmothcount.get(name);
			  }
		  }
		  
		  int outputcount = 0;
		  for(String name:addressmothcount.keySet())
		  {		
		  	  		
			  if(addressmothcount.get(name)==maxtimes)
			  {
					if(outputcount==0)
			  		{
			  			System.out.print( "\"");
			  			outputcount++;
			  		}	
					else
					{
						System.out.println();
					}
				  maxaddress.put(name,maxtimes);
				  
				  System.out.print(name+", 程蔼Θユ基:"+maxprice.get(name)+", 程Θユ基:"+miniprice.get(name));
				
			  }
		  }
		  
		  System.out.print( "\"");
	  }//end main class
	
	
	
	
}
