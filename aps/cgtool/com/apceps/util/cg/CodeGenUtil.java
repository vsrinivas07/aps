package com.apceps.util.cg;

import java.util.StringTokenizer;

public class CodeGenUtil {

	
	
	public static  String toUpperCase(String input,int index){
		StringBuffer sb = new StringBuffer();
		
		sb.append( (input.substring(index,index+1)).toUpperCase());
		sb.append( (input.substring(index+1)));
		
		return sb.toString();
	}
	
	
	public  static String toLowerCase(String input,int index){
		StringBuffer sb = new StringBuffer();
		sb.append( (input.substring(index,index+1)).toLowerCase());
		sb.append( (input.substring(index+1)));
		
		return sb.toString();
	}
	
	
	public static String getJavaColName(String input) {
		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new  StringTokenizer(input,"_");
		int i=0;
		while(st.hasMoreTokens()){ 
			String str  = st.nextToken().toLowerCase();
			if(i>0){
				sb.append( (str.substring(0,1)).toUpperCase());
				sb.append( (str.substring(1)));
			}else sb.append(str);
			i++;
		}
		return sb.toString();
	}
 

	public static   String getJavaClassName(String input) {
		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new  StringTokenizer(input,"_");
		int i=0;
 		while(st.hasMoreTokens()){ 
			String str  = st.nextToken().toLowerCase();
	 		if(i>0) 
	 			sb.append(toUpperCase(str,0));
			i++;
		}
		return sb.toString();
	}


	public static   String getSeqName(String input) {
	
		return	input.concat("_SEQ");
     
	}


}
