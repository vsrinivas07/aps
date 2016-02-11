package com.apceps.util.cg;

import com.apceps.util.StringUtil;

public class Variable {
	
public static final String PARAM_MODE_EXE = "EXE" ; 

public static final String PARAM_MODE_DECL = "DECL" ; 


private String name = null ; 

private String type = null ; 

private String access = null ;

private String initVal = null ; 

public Variable(String name, String type, String access) {
	super();
	
	setName(name);
	
	this.type = type;
	this.access = access;
}

public String getName() {
	if(getAccess().equalsIgnoreCase("public static final")) return name.toUpperCase();
	return name;
}

public void setName(String name) {
	this.name = CodeGenUtil.toLowerCase(name, 0);
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public String getAccess() {
	return access;
}

public void setAccess(String access) {
	this.access = access;

}



public String getInitVal() {
	return initVal;
}

public void setInitVal(String initVal) {
	this.initVal = initVal;
}

public String getInstanceVariableCode() {
	StringBuffer sb = new StringBuffer();
	sb.append("\t").append(getAccess()).append(" ").append(getType()).append(" ")
			.append(getName()).append(" = ")
			.append(getColInitialization()).append("; ")
			.append(StringUtil.LINE_SEPARTOR)
			.append(StringUtil.LINE_SEPARTOR);
	return sb.toString();
} 

private String getColInitialization() {

	if(!StringUtil.isEmpty(getInitVal())) return getInitVal();
	
	if (getType().equals("Long") || getType().equals("String"))
		return "null";

	if (getType().equals("boolean") )
		return "false";

	
	return "null";
}

public String getParamCode(String mode) {
	StringBuffer sb = new StringBuffer();
	if(PARAM_MODE_DECL.equalsIgnoreCase(mode)) sb.append(getType()).append(" ");
	sb.append(getName()); 
	return sb.toString();
}


	
	
	
}
