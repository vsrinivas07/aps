package com.apceps.util.cg;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.apceps.util.StringUtil;

public class JavaSource {

	private String type = null;

	private String name = null;

	private String sourcePackage = null;

	private String sourceFolder = null;

	private String superClassName = null;

	private String superClassAssociationType = null;

	private List<String> implmentionList = new ArrayList<String>();

	private List<String> importList = new ArrayList<String>();

	private List<Variable> variableList = new ArrayList<Variable>();

	private List<Method> methodList = new ArrayList<Method>();

	private boolean overwrite = true;

	public JavaSource(String type, String name, String sourcePackage,
			String sourceFolder) {
		super();
		this.type = type;
		this.name = name;
		this.sourcePackage = sourcePackage;
		this.sourceFolder = sourceFolder;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSourcePackage() {
		return sourcePackage;
	}

	public void setSourcePackage(String sourcePackage) {
		this.sourcePackage = sourcePackage;
	}

	public String getSourceFolder() {
		return sourceFolder;
	}

	public void setSourceFolder(String sourceFolder) {
		this.sourceFolder = sourceFolder;
	}

	public String getSuperClassName() {
		return superClassName;
	}

	public void setSuperClassName(String superClassName) {
		this.superClassName = superClassName;
	}

	public String getSuperClassAssociationType() {
		return superClassAssociationType;
	}

	public void setSuperClassAssociationType(String superClassAssociationType) {
		this.superClassAssociationType = superClassAssociationType;
	}

	public List<String> getImplmentionList() {
		return implmentionList;
	}

	public void setImplmentionList(List<String> implmentionList) {
		this.implmentionList = implmentionList;
	}

	public List<String> getImportList() {
		return importList;
	}

	public void setImportList(List<String> importList) {
		this.importList = importList;
	}

	public List<Variable> getVariableList() {
		return variableList;
	}

	public void setVariableList(List<Variable> variableList) {
		this.variableList = variableList;
	}

	public List<Method> getMethodList() {
		return methodList;
	}

	public void setMethodList(List<Method> methodList) {
		this.methodList = methodList;
	}

	public boolean isOverwrite() {
		return overwrite;
	}

	public void setOverwrite(boolean overwrite) {
		this.overwrite = overwrite;
	}

	public String getFullName() {
		return getSourcePackage().concat(".").concat(getName());
	}

	public String getNameForVariable() {
		return CodeGenUtil.toLowerCase(getName(), 0);
	}

	public String getPackageStatementCode() {
		StringBuffer content = new StringBuffer();
		content.append("package ").append(getSourcePackage()).append(";")
				.append(StringUtil.LINE_SEPARTOR)
				.append(StringUtil.LINE_SEPARTOR);
		return content.toString();
	}

	public String getImportStatementCode() {
		if (getImportList().isEmpty())
			return "";

		StringBuffer content = new StringBuffer();
		for (String s : getImportList())
			content.append("import ").append(s).append(";")
					.append(StringUtil.LINE_SEPARTOR);

		content.append(StringUtil.LINE_SEPARTOR);
		return content.toString();
	}

	public String getSourceNameCode() {
		StringBuffer content = new StringBuffer();
		content.append("public ").append(getType()).append(" ")
				.append(getName()).append(" ");

		if (!StringUtil.isEmpty(getSuperClassAssociationType())) {
			content.append(
					getSuperClassAssociationType() + " " + getSuperClassName())
					.append(" ");
		}

		int i = 0;
		for (String implClass : implmentionList) {
			if (i > 0)
				content.append(", ");
			content.append(" implements " + implClass).append(" ");
			i++;
		}

		content.append("{").append(StringUtil.LINE_SEPARTOR)
				.append(StringUtil.LINE_SEPARTOR);
		return content.toString();
	}

	public String getEndOfSource() {
		return "}";
	}

	public void generateCode() throws IOException {
		if (getName() == null)
			return;
		File serviceDirectory = new File(getSourceFolder());
		if (!serviceDirectory.exists())
			serviceDirectory.mkdirs();
		File srcFile = new File(getSourceFolder() + "\\" + getName() + ".java");

		if (!isOverwrite() && srcFile.exists())
			return;

		BufferedWriter output = new BufferedWriter(new FileWriter(srcFile));

		// BufferedWriter output = getBufferdWriter();
		StringBuffer content = new StringBuffer();
		content.append(getPackageStatementCode());
		content.append(getImportStatementCode());
		content.append(getSourceNameCode());

		content.append(getInstanceVariablesCode());
		content.append(getMethodsCode());

		content.append(getEndOfSource());
		output.write(content.toString());
		output.close();

	}

	private String getMethodsCode() {

		if (getMethodList().isEmpty())
			return "";

		StringBuffer content = new StringBuffer();

		for (Method method : getMethodList()) {
			content.append(method.getMethodText());
		}

		return content.toString();
	}

	private Object getInstanceVariablesCode() {
		if (getVariableList().isEmpty())
			return "";

		StringBuffer content = new StringBuffer();

		for (Variable variable : getVariableList()) {
			content.append(variable.getInstanceVariableCode());
		}
		return content.toString();
	}

	public BufferedWriter getBufferdWriter() throws IOException {
		File serviceDirectory = new File(getSourceFolder());
		if (!serviceDirectory.exists())
			serviceDirectory.mkdirs();
		File serviceClass = new File(getSourceFolder() + "\\" + getName()
				+ ".java");
		BufferedWriter output = new BufferedWriter(new FileWriter(serviceClass));
		return output;
	}

	@Override
	public String toString() {
		return "JavaSource [type=" + type + ", name=" + name
				+ ", sourcePackage=" + sourcePackage + ", sourceFolder="
				+ sourceFolder + "]";
	}

}
