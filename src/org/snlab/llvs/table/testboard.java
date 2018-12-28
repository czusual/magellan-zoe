package org.snlab.llvs.table;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class testboard {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		String fatherpath="D:\\CZU\\Eclipse2018\\eclipse2018-workbench\\Magellan-Zoe\\src\\org\\snlab\\llvs\\table\\";
		String filepath=fatherpath+"table1.txt";
		try
		{
			Table_inFlowAglebra t1=new Table_inFlowAglebra(filepath);
			filepath=fatherpath+"table2.txt";
			Table_inFlowAglebra t2=new Table_inFlowAglebra(filepath);
			FlowAlgebra fa=new FlowAlgebra();
			Table_inFlowAglebra t3=fa.FlowjoinOperator(t1, t2);
			t3.print();
		}
		catch(IOException e)
		{
		    e.printStackTrace();
		}
		
		
		
	}

}
