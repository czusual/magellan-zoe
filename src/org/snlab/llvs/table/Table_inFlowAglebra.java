package org.snlab.llvs.table;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Table_inFlowAglebra {
	//assume  no distinction between inputs or outputs(according to paper)
	private List<String> faschema=new ArrayList<String>();
	private List<Map<String,String>> entries = new ArrayList<>();
	public List<String> getFaschema() {
		return faschema;
	}
	public void setFaschema(List<String> faschema) {
		this.faschema = faschema;
	}
	public List<Map<String,String>>getEntries() {
		return entries;
	}
	public void setEntries(List<Map<String,String>> entries) {
		this.entries = entries;
	}
	public Table_inFlowAglebra() {}
	public Table_inFlowAglebra(String filepath) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		String line=br.readLine();
		String[] s = line.split("\\s+");
	    for (int i = 0; i < s.length; i++) 
	    { 
	    	faschema.add(s[i]);
	    }
		for (; (line = br.readLine()) != null; ) 
		{
			String[] vals = line.split("\\s+");
			Map<String, String> entry = new HashMap<>();
			for(int j=0;j<faschema.size();j++)
			{
				entry.put(faschema.get(j), vals[j]);		
			}
		    entries.add(entry);
		}
		br.close();
	}
		
	public Table_inFlowAglebra(Table tinput)//还没把entries部分写完，正在考虑深拷贝还是浅拷贝
	{
		faschema.addAll(tinput.getSchema().getMeta());
		faschema.addAll(tinput.getSchema().getInputs());
		faschema.add(tinput.getSchema().getOutput());
		//entries.addAll(tinput.getEntries());
	}
	
    
	public void print()
	{
		for(int i=0;i<faschema.size();i++)
		{
			System.out.print(faschema.get(i)+"|");
		}
		 System.out.print('\n');
		 for (Map<String, String> entry : this.entries)
		 {
		     for (String m : faschema) 
		     {
		         System.out.print(entry.get(m) + " ");
		     }
		      System.out.print('\n');
		 }
		
	}

}
