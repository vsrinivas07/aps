package com.apceps.util.cg;



public class DBColumn {
	
	private String dbColumnName = null ; 
	
	private String dbColumnType  = null ; 
	
	private Integer dataPrecision = null ;
	
	private Integer dataScale = null ;
	
	private String columnName = null; 
	
	private String columnType = null; 

	private boolean isPrimaryKeyColumn ; 
	
	private boolean isUniqueKeyColumn ; 
	
	private boolean isRefKeyColumn ;

	public String getDbColumnName() {
		return dbColumnName;
	}

	public void setDbColumnName(String dbColumnName) {
		this.dbColumnName = dbColumnName;
	}

	public String getDbColumnType() {
		return dbColumnType;
	}
	
	public Integer getDataPrecision() {
		return dataPrecision;
	}

	public void setDataPrecision(Integer dataPrecision) {
		this.dataPrecision = dataPrecision;
	}

	public Integer getDataScale() {
		return dataScale;
	}

	public void setDataScale(Integer dataScale) {
		this.dataScale = dataScale;
	}

	public void setDbColumnType(String dbColumnType) {
		this.dbColumnType = dbColumnType;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	
	
	public boolean isPrimaryKeyColumn() {
		return isPrimaryKeyColumn;
	}

	public void setPrimaryKeyColumn(boolean isPrimaryKeyColumn) {
		this.isPrimaryKeyColumn = isPrimaryKeyColumn;
	}

	public boolean isUniqueKeyColumn() {
		return isUniqueKeyColumn;
	}

	public void setUniqueKeyColumn(boolean isUniqueKeyColumn) {
		this.isUniqueKeyColumn = isUniqueKeyColumn;
	}

	public boolean isRefKeyColumn() {
		return isRefKeyColumn;
	}

	public void setRefKeyColumn(boolean isRefKeyColumn) {
		this.isRefKeyColumn = isRefKeyColumn;
	}

	
	public String getMethodName(){
	     if("boolean".equals(getColumnType())) return "is" + CodeGenUtil.toUpperCase(getColumnName(), 0);
		
		return "get" +  CodeGenUtil.toUpperCase(getColumnName(), 0);
	}
	

	public String setMethodName(){
	    return "set" +  CodeGenUtil.toUpperCase(getColumnName(), 0);
	}
    public  String getJavaColType( String dbProductName) {
    	String input =  getDbColumnType().toLowerCase();
    	
    	if("Oracle".equalsIgnoreCase(dbProductName)){

    		if( "NUMBER".equalsIgnoreCase(input) && getDataScale()==0) return "Long" ; 

    		if( "NUMBER".equalsIgnoreCase(input) && getDataScale()> 0)   return "Double" ; 
    	
		}else {

			if("bigint".equalsIgnoreCase(input) || "int".equalsIgnoreCase(input) || "INTEGER".equalsIgnoreCase(input) || "NUMBER".equalsIgnoreCase(input)) return "Long" ; 

			if("decimal".equalsIgnoreCase(input)) return "Double" ; 
		
		}
    		
		if("varchar".equalsIgnoreCase(input) || "varchar2".equalsIgnoreCase(input)) return "String" ; 
		
		if("char".equalsIgnoreCase(input)) return "boolean" ; 
		
		
		if("date".equalsIgnoreCase(input)) return "Date" ; 
			
 		return input ;
	}



    public  String getNullType() {
    	
    	String input =  getColumnType();
    	
    	if("String".equals(input)) return "Types.VARCHAR"; 
    	
    	if("boolean".equals(input)) return "Types.BOOLEAN"; 
    	

    	if("Date".equals(input)) return "Types.DATE";
    	
    	if("Long".equals(input)) return "Types.INTEGER";
    	
    	if("Double".equals(input)) return "Types.DECIMAL";
    	
    	 			
 		return input.toUpperCase() ;
	}

    
    
    
	@Override
	public String toString() {
		return "DBColumn [columnName=" + columnName + ", columnType="
				+ columnType + ", isUniqueKeyColumn=" + isUniqueKeyColumn
				+ ", isRefKeyColumn=" + isRefKeyColumn + "]";
	}

	  	
}
