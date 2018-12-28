package org.snlab.llvs.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;

public class FlowAlgebra {
	public static String CONFILCT="conflict";
	public static String PRIORITY="pri";
	public String IntersectionOperator(String v1,String v2)//According to Magellan-sub-2018.pdf：Table 4 
	{
		String resultv="";
		/*Assume v1 and v2 have the same width =： String length equal*/
		if(v1.length()!=v2.length())
		{
			return null;//must something wrong
		}
		for(int i=0;i<v1.length();i++)
		{
			if(v1.charAt(i)=='*'||v1.charAt(i)==v2.charAt(i))
			{
				resultv+=v2.charAt(i);
			}
			else
			{
				if(v2.charAt(i)=='*')
				{
					resultv+=v1.charAt(i);
				}
				else
				{
					resultv=CONFILCT;
				    return resultv;
				}				
			}
		}
		return resultv;
	}
	
	public Table_inFlowAglebra Sort(Table_inFlowAglebra t)//sort from high pri to low
	{
		Collections.sort(t.getEntries(), new Comparator<Map<String,String>>() {
		      @Override
		      public int compare(Map<String,String> e1, Map<String,String> e2) {
		        return Integer.valueOf(e1.get(PRIORITY))< Integer.valueOf(e2.get(PRIORITY)) ? 1 : 
		        	(Integer.valueOf(e1.get(PRIORITY)) > Integer.valueOf(e2.get(PRIORITY)) ? -1 : 0);
		      }
		    });
		return t;
	}
	public List<String> ProjectionOperator()//这一部分先不写，鉴于就两行代码
	{
		return null;
	}
	public Table_inFlowAglebra FlowjoinOperator(Table_inFlowAglebra  t1,Table_inFlowAglebra  t2)
	{
		Table_inFlowAglebra resultT= new Table_inFlowAglebra();
		/* merge schema */
		List<String> s1temp =new ArrayList<String>();
		List<String> s2temp = new ArrayList<String>();
		s1temp.addAll(t1.getFaschema());
		s2temp.addAll(t2.getFaschema());
		List<String> sJJ=new ArrayList<>();//交集
        sJJ.addAll(s2temp); 
        /* list的addAll方法，是浅拷贝，拷贝后，对新的list中数据更改，对原来的list中对象有影响。*/
       /* 但是若是list中保存的是基本类型的数据，拷贝前后的数据就没有联系了。即对新的list更改，不会影响原来的list。*/
        /*至少当前的测试显示sJJ和s2temp没有联系*/
		sJJ.retainAll(s1temp);
		s1temp.removeAll(sJJ);
		s2temp.removeAll(sJJ);
		System.out.println(sJJ);
		/*开始join*/
		resultT.getFaschema().addAll(sJJ);
		resultT.getFaschema().addAll(s1temp);
		resultT.getFaschema().addAll(s2temp);
		for(int el1=0;el1<t1.getEntries().size();el1++)
		{
			for(int el2=0;el2<t2.getEntries().size();el2++)
			{
				Map<String, String> entry = new HashMap<>();
				int j=1;
				for(;j<sJJ.size();j++)
				{
					String vmerge=IntersectionOperator(t1.getEntries().get(el1).get(sJJ.get(j)),
							t2.getEntries().get(el2).get(sJJ.get(j)));
					//System.out.println(vmerge);
					if(vmerge==CONFILCT||vmerge==null)
					{
						break;//说明此行冲突，这一整行都不要了
					}
					entry.put(sJJ.get(j), vmerge);//add in new entry temporarily
				}
				//System.out.println(j);
				if(j==sJJ.size())//no conflict in intersection part
				{
					int pri1=Integer.valueOf(t1.getEntries().get(el1).get(PRIORITY));
					int pri2=Integer.valueOf(t2.getEntries().get(el2).get(PRIORITY));
					entry.put(PRIORITY, String.valueOf(pri1+pri2));
					for(int j1=0;j1<s1temp.size();j1++)
					{
						entry.put(s1temp.get(j1),t1.getEntries().get(el1).get(s1temp.get(j1)));
					}
					for(int j2=0;j2<s2temp.size();j2++)
					{
						entry.put(s2temp.get(j2),t2.getEntries().get(el2).get(s2temp.get(j2)));
					}
					//System.out.println(entry);
					resultT.getEntries().add(entry);
				}		
			}
		} 
		//resultT.print();
		resultT=Sort(resultT);
		return resultT;		
	}

}
