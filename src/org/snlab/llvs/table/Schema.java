package org.snlab.llvs.table;
import java.util.ArrayList;
import java.util.List;
public class Schema {
	private List<String> meta = new ArrayList<>();
	  private List<String> inputs = new ArrayList<>();
	  private String output;

	  Schema() {
	    this.meta.add("pri");
	  }

	  public List<String> getMeta() {
	    return meta;
	  }

	  public void setMeta(List<String> meta) {
	    this.meta = meta;
	  }

	  public List<String> getInputs() {
	    return inputs;
	  }

	  public void setInputs(List<String> inputs) {
	    this.inputs = inputs;
	  }

	  public String getOutput() {
	    return output;
	  }

	  public void setOutput(String output) {
	    this.output = output;
	  }
	  public void print() {
		    for (String m : this.meta) {
		      System.out.print(m + " | ");
		    }
		    System.out.print(this.output + " | ");
		    for (String input : this.inputs) {
		      System.out.print(input + " | ");
		    }
		    System.out.print('\n');
		  }


}
