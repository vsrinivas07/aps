package com.apceps.util.cg;

import java.util.ArrayList;
import java.util.List;

public class CgObject {

	private String tableName = null;

	private String srcFolder = null;

	private String srcPackage = null;

	private JavaSource domain = null;

	private JavaSource domainHelper = null;

	private JavaSource service = null;

	private JavaSource serviceImpl = null;

	private JavaSource dao = null;

	private JavaSource daoImpl = null;
	
	private JavaSource domainExt = null;

	private JavaSource domainHelperExt = null;

	private JavaSource serviceExt = null;

	private JavaSource serviceImplExt = null;

	private JavaSource daoExt = null;

	private JavaSource daoImplExt = null;
	

	private String seqName = null;

	private DBColumn pk = null ; 
	
	public CgObject(String tableName, String srcFolder, String srcPackage) {
		super();
		this.tableName = tableName;
		this.srcFolder = srcFolder;
		this.srcPackage = srcPackage;
        
		setSeqName(CodeGenUtil.getSeqName(tableName));
        
		String javaClassName = CodeGenUtil.getJavaClassName(tableName);
		
		String packagePath  = srcPackage.replace("\\", ".");
		 
	    JavaSource domain = new JavaSource("class", javaClassName+"Base", packagePath.concat(".domain.cg"), srcFolder+"\\"+srcPackage+"\\domain\\cg");
	    setDomain(domain);

	    JavaSource domainExt = new JavaSource("class", javaClassName, packagePath.concat(".domain"), srcFolder+"\\"+srcPackage+"\\domain");
	    domainExt.setSuperClassAssociationType("extends");
	    domainExt.setSuperClassName(domain.getName());
	    domainExt.getImportList().add(domain.getFullName());
	    domainExt.setOverwrite(false);
	    setDomainExt(domainExt);
	    
	    
	    JavaSource domainHelper = new JavaSource("class", javaClassName+"HelperBase", packagePath.concat(".domain.cg"), srcFolder+"\\"+srcPackage+"\\domain\\cg");
	    setDomainHelper(domainHelper);
	    
	    JavaSource domainHelperExt = new JavaSource("class", javaClassName+"Helper", packagePath.concat(".domain"), srcFolder+"\\"+srcPackage+"\\domain");
	    domainHelperExt.setSuperClassAssociationType("extends");
	    domainHelperExt.setSuperClassName(domainHelper.getName());
	    domainHelperExt.getImportList().add(domainHelper.getFullName());
	    domainHelperExt.setOverwrite(false);
	    setDomainHelperExt(domainHelperExt);
	    
	    
	    JavaSource dao = new JavaSource("interface", javaClassName+"DaoBase" , packagePath.concat(".dao.cg"), srcFolder+"\\"+srcPackage+"\\dao\\cg");
	    setDao(dao);
	    getDao().getImportList().add(domainExt.getFullName()); 
	    
	    
	    JavaSource daoExt = new JavaSource("interface", javaClassName+"Dao" , packagePath.concat(".dao"), srcFolder+"\\"+srcPackage+"\\dao");
	    daoExt.setSuperClassAssociationType("extends");
	    daoExt.setSuperClassName(dao.getName());
	    daoExt.getImportList().add(dao.getFullName());
	    daoExt.setOverwrite(false);
	    setDaoExt(daoExt);	    
	    
		
	    JavaSource daoImpl = new JavaSource("class", javaClassName+"DaoBaseImpl", packagePath.concat(".dao.cg.impl"), srcFolder+"\\"+srcPackage+"\\dao\\cg\\impl");
	    daoImpl.setSuperClassAssociationType("implements");
	    daoImpl.setSuperClassName(dao.getName());
	    setDaoImpl(daoImpl);
	    getDaoImpl().getImportList().add(domainExt.getFullName());
	    getDaoImpl().getImportList().add(domainHelper.getFullName());
	    getDaoImpl().getImportList().add(dao.getFullName());
	    getDaoImpl().getImportList().add(getDomainHelperExt().getFullName());
		
	    
	    Method daoImplConstructor = new Method();
	    daoImplConstructor.setConstructor(true);
	    daoImplConstructor.setName(daoImpl.getName());
	    daoImplConstructor.setAccess("public");
	 
	    StringBuffer constBodyDaoImpl = new StringBuffer();
	    constBodyDaoImpl.append("\t").append("\t").append("this.").append(getDomainHelperExt().getNameForVariable()).append("=").append("new ").append(getDomainHelperExt().getName()).append("();");
	    daoImplConstructor.setBody(constBodyDaoImpl.toString());
 	    
	    getDaoImpl().getMethodList().add(daoImplConstructor);
	  	
	    
	    
	    StringBuffer setMethodBody = new StringBuffer();
		setMethodBody.append("\t").append("\t").append("this.").append(domainHelperExt.getNameForVariable()).append(" = ").append(domainHelperExt.getNameForVariable()).append(";");
		StringBuffer getMethodBody = new StringBuffer();
		getMethodBody.append("\t").append("\t").append("return ").append(domainHelperExt.getNameForVariable()).append(";");
		
	 
		List<Variable> params = new ArrayList<Variable>();
		params.add(new Variable(domainHelperExt.getName(), domainHelperExt.getName(), "private"));
		
	    getDaoImpl().getVariableList().add(new Variable(domainHelperExt.getNameForVariable(), domainHelperExt.getName(), "private"));
 		getDaoImpl().getMethodList().add(new Method("public", domainHelperExt.getName(), "get"+domainHelperExt.getName(), new ArrayList<Variable>(),getMethodBody.toString()));
	    getDaoImpl().getMethodList().add(new Method("public", "void", "set"+domainHelperExt.getName(), params,setMethodBody.toString()));
		
	    
	    JavaSource daoImplExt = new JavaSource("class", javaClassName+"DaoImpl", packagePath.concat(".dao.impl"), srcFolder+"\\"+srcPackage+"\\dao\\impl");
	    
	    daoImplExt.getImplmentionList().add(daoExt.getName());
	    
	    daoImplExt.setSuperClassAssociationType("extends");
	    daoImplExt.setSuperClassName(daoImpl.getName());
	    daoImplExt.getImportList().add(daoExt.getFullName());
	    daoImplExt.getImportList().add(daoImpl.getFullName());
	    
	    daoImplExt.setOverwrite(false);
	    setDaoImplExt(daoImplExt);	    
	    
	    JavaSource service = new JavaSource("interface", javaClassName+"ServiceBase" , packagePath.concat(".service.cg"), srcFolder+"\\"+srcPackage+"\\service\\cg");
	    List<String> importList = new ArrayList<String>();
	    importList.add(getDomainExt().getFullName());
		service.setImportList(importList);
	    setService(service);
	    
	    JavaSource serviceExt = new JavaSource("interface", javaClassName+"Service" , packagePath.concat(".service"), srcFolder+"\\"+srcPackage+"\\service");
	    serviceExt.setSuperClassAssociationType("extends");
	    serviceExt.setSuperClassName(service.getName());
	    serviceExt.getImportList().add(service.getFullName());
	    serviceExt.setOverwrite(false);
	    setServiceExt(serviceExt);
	    
	    
	    JavaSource serviceImpl = new JavaSource("class", javaClassName+"ServiceBaseImpl", packagePath.concat(".service.cg.impl"), srcFolder+"\\"+srcPackage+"\\service\\cg\\impl");
	    importList = new ArrayList<String>();
	    importList.add(getDomainExt().getFullName());
	    importList.add(getDao().getFullName());	    
	    importList.add(getDaoImpl().getFullName());	    
	    importList.add(getService().getFullName());	
	    
		
	    serviceImpl.setImportList(importList);
	    serviceImpl.setSuperClassAssociationType("implements");
	    serviceImpl.setSuperClassName(service.getName());
 	    
	    Variable daoVar = new Variable(dao.getName(), dao.getName(), "private"); 
	    params = new ArrayList<Variable>();
		params.add(daoVar);
		
		setMethodBody = new StringBuffer();
		setMethodBody.append("\t").append("\t").append("this.").append(daoVar.getName()).append(" = ").append(daoVar.getName()).append(";");
		getMethodBody = new StringBuffer();
		getMethodBody.append("\t").append("\t").append("return ").append(daoVar.getName()).append(";");
		
		serviceImpl.getVariableList().add(daoVar);
		serviceImpl.getMethodList().add(new Method("public", dao.getName(), "get"+CodeGenUtil.toUpperCase(dao.getName(), 0), new ArrayList<Variable>(),getMethodBody.toString()));
		serviceImpl.getMethodList().add(new Method("public", "void", "set"+CodeGenUtil.toUpperCase(dao.getName(), 0), params,setMethodBody.toString()));
		
		Method serviceConstructor = new Method();
	    serviceConstructor.setConstructor(true);
	    serviceConstructor.setName(serviceImpl.getName());
	    serviceConstructor.setAccess("public");
	 
	    StringBuffer constBodyServiceImpl = new StringBuffer();
	    constBodyServiceImpl.append("\t").append("\t").append("this.").append(getDao().getNameForVariable()).append("=").append("new ").append(getDaoImpl().getName()).append("();");
	    serviceConstructor.setBody(constBodyServiceImpl.toString());
 	    
	    serviceImpl.getMethodList().add(serviceConstructor);
	    serviceImpl.getImportList().add(domainExt.getFullName());
	    service.getImportList().add(domain.getFullName());
		setServiceImpl(serviceImpl);
		
		
		JavaSource serviceImplExt = new JavaSource("class", javaClassName+"ServiceImpl", packagePath.concat(".service.impl"), srcFolder+"\\"+srcPackage+"\\service\\impl");
		serviceImplExt.setSuperClassAssociationType("extends");
		serviceImplExt.setSuperClassName(serviceImpl.getName());
		serviceImplExt.getImplmentionList().add(serviceExt.getName());
		serviceImplExt.getImportList().add(serviceImpl.getFullName());
		serviceImplExt.getImportList().add(serviceExt.getFullName());
		serviceImplExt.setOverwrite(false);
	    setServiceImplExt(serviceImplExt);
	 		
 		
		getDao().getImportList().add("java.util.List");
		getDaoImpl().getImportList().add("java.sql.Connection");
  		getDaoImpl().getImportList().add("java.sql.ResultSet");
  		
  		getDomainHelper().getImportList().add("java.sql.ResultSet");
  		getDomainHelper().getImportList().add("java.sql.PreparedStatement");
  		getDomainHelper().getImportList().add("java.sql.Types");
  		
  		
		getDaoImpl().getImportList().add("java.sql.PreparedStatement");
		getDaoImpl().getImportList().add("java.util.List");
		getDaoImpl().getImportList().add("java.util.ArrayList");
		
		getDaoImpl().getImportList().add("com.apceps.util.ConnectionUtil");
		getDaoImpl().getImportList().add("com.apceps.util.DBUtil");
		
	
		getService().getImportList().add("java.util.List");
		getServiceImpl().getImportList().add("java.util.List");
	    
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getSrcFolder() {
		return srcFolder;
	}

	public void setSrcFolder(String srcFolder) {
		this.srcFolder = srcFolder;
	}

	public String getSrcPackage() {
		return srcPackage;
	}

	public void setSrcPackage(String srcPackage) {
		this.srcPackage = srcPackage;
	}

	public JavaSource getDomain() {
		return domain;
	}

	public void setDomain(JavaSource domain) {
		this.domain = domain;
	}
 
	public JavaSource getDomainHelper() {
		return domainHelper;
	}

	public void setDomainHelper(JavaSource domainHelper) {
		this.domainHelper = domainHelper;
	}

	public JavaSource getService() {
		return service;
	}

	public void setService(JavaSource service) {
		this.service = service;
	}

	public JavaSource getServiceImpl() {
		return serviceImpl;
	}

	public void setServiceImpl(JavaSource serviceImpl) {
		this.serviceImpl = serviceImpl;
	}

	public JavaSource getDao() {
		return dao;
	}

	public void setDao(JavaSource dao) {
		this.dao = dao;
	}

	public JavaSource getDaoImpl() {
		return daoImpl;
	}

	public void setDaoImpl(JavaSource daoImpl) {
		this.daoImpl = daoImpl;
	}
	
	public JavaSource getDomainExt() {
		return domainExt;
	}

	public void setDomainExt(JavaSource domainExt) {
		this.domainExt = domainExt;
	}

	public JavaSource getDomainHelperExt() {
		return domainHelperExt;
	}

	public void setDomainHelperExt(JavaSource domainHelperExt) {
		this.domainHelperExt = domainHelperExt;
	}

	public JavaSource getServiceExt() {
		return serviceExt;
	}

	public void setServiceExt(JavaSource serviceExt) {
		this.serviceExt = serviceExt;
	}

	public JavaSource getServiceImplExt() {
		return serviceImplExt;
	}

	public void setServiceImplExt(JavaSource serviceImplExt) {
		this.serviceImplExt = serviceImplExt;
	}

	public JavaSource getDaoExt() {
		return daoExt;
	}

	public void setDaoExt(JavaSource daoExt) {
		this.daoExt = daoExt;
	}

	public JavaSource getDaoImplExt() {
		return daoImplExt;
	}

	public void setDaoImplExt(JavaSource daoImplExt) {
		this.daoImplExt = daoImplExt;
	}

	public String getSeqName() {
		return seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

 	
	public DBColumn getPk() {
		return pk;
	}

	public void setPk(DBColumn pk) {
		this.pk = pk;
	}

	@Override
	public String toString() {
		return "CgObject [tableName=" + tableName + ", srcFolder=" + srcFolder
				+ ", srcPackage=" + srcPackage + ", domain=" + domain
				+ ", domainBase=" +  ", service=" + service
				+ ", serviceImpl=" + serviceImpl + ", dao=" + dao
				+ ", daoImpl=" + daoImpl + "]";
	}
  
}
