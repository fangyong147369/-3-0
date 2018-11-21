package com.hc.sys.common.util.codeGenerate.template;

import com.hc.sys.common.util.date.DateUtil;
import com.hc.sys.common.util.validate.StringUtil;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.transaction.annotation.Transactional;

public class CodeTemplate {

	private String moduleName = "TestOne";
	private String author = "fy";
	private String version = "1.0.0.0";
	private String since = DateUtil.dateStr6(DateUtil.getNow());
	private String dbModel = "M";
	
	private String packageName = "";
	private String entityName = "";
	private String tableName = "";
	
	private String entityPackage = "";
	private String modelPackage = "";
	private String servicePackage = "";
	private String serviceImplPackage = "";
	private String controllerPackage = "";
	private String daoPackage = "";
	private String daoImplPackage = "";
	
	private String modelName = "";
	private String serviceName = "";
	private String serviceImplName = "";
	private String controllerName = "";
	private String daoName = "";
	private String daoImplName = "";
	
	private String projectPackage = "com.hc.sys.core";
	
	public CodeTemplate(String packageName, String entityName, String tableName,String projectPackage) {
		this.packageName = packageName;
		this.entityName = entityName;
		this.tableName = tableName;
		this.projectPackage = projectPackage;
		entityPackage = String.format(""+projectPackage+".%s.entity", packageName);
		modelPackage = String.format(""+projectPackage+".%s.model", packageName);
		servicePackage = String.format(""+projectPackage+".%s.service", packageName);
		serviceImplPackage = String.format(""+projectPackage+".%s.service.impl", packageName);
		controllerPackage = String.format(""+projectPackage+".%s.controller", packageName);
		daoPackage = String.format(""+projectPackage+".%s.dao", packageName);
		daoImplPackage = String.format(""+projectPackage+".%s.dao.impl", packageName);

		modelName = String.format("%sModel", entityName);
		serviceName = String.format("%sService", entityName);
		serviceImplName = String.format("%sServiceImpl", entityName);
		controllerName = String.format("%sController", entityName);
		daoName = String.format("%sDao", entityName);
		daoImplName = String.format("%sDaoImpl", entityName);
	}
	
	public String entity(){
		StringBuilder sb = new StringBuilder();
		sb.append("package ").append(entityPackage).append(";\n");
		sb.append("import javax.persistence.Entity;\n");
		sb.append("import javax.persistence.Table;\n");
		sb.append("\n");
		sb.append("import com.zc.sys.common.entity.LongPKEntity;\n");
		sb.append("import "+projectPackage+".common.constant.BaseConstant;\n");
		sb.append("/**\n * "+moduleName+"\n * @author "+author+"\n * @version "+version+"\n * @since "+since+"\n */\n");
		sb.append("@Entity\n");
		sb.append("@DynamicUpdate(true)\n");
		sb.append("@DynamicInsert(true)\n");
		sb.append("@Table(name=BaseConstant.DB_PREFIX + BaseConstant.DB_MODEL_" + dbModel + " + \"_%s\")\n");
		sb.append("public class %s extends LongPKEntity {\n");
		sb.append("\t/** 序列号 **/\n");
		sb.append("\tprivate static final long serialVersionUID = 1L;\n");
		sb.append("\n");
		sb.append("}");
		return String.format(sb.toString(), tableName, entityName);
	}
	
	public String model(){
		String entityNameLower = StringUtil.firstCharLowerCase(entityName);
		String modelNameLower = StringUtil.firstCharLowerCase(modelName);
		StringBuilder sb = new StringBuilder();
		sb.append("package ").append(modelPackage).append(";\n");
		sb.append("import org.springframework.beans.BeanUtils;\n");
		sb.append("\n");
		sb.append("import com.hc.sys.common.model.jpa.Page;\n");
		sb.append("import ").append(entityPackage).append(".").append(entityName).append(";\n");
		sb.append("/**\n * "+moduleName+"\n * @author "+author+"\n * @version "+version+"\n * @since "+since+"\n */\n");
		sb.append("public class %s extends " + entityName + " {\n");
		sb.append("\t/** 序列号 **/\n");
		sb.append("\tprivate static final long serialVersionUID = 1L;\n");
		sb.append("\n");
		sb.append("\t/** 当前页码 **/\n");
		sb.append("\tprivate int pageNo;\n");
		sb.append("\t/** 每页数据条数 **/\n");
		sb.append("\tprivate int pageSize = Page.ROWS;\n");
		sb.append("\t/** 条件查询 **/\n");
		sb.append("\tprivate String searchName;\n");
		sb.append("\n");
		
		sb.append("\t/**\n\t * 实体转换model\n\t */\n");
		sb.append("\tpublic static "+modelName+" instance(" + entityName + " "+entityNameLower+") {\n");
		sb.append("\t\t"+modelName+" "+ modelNameLower+" = new "+modelName+"();\n");
		sb.append("\t\tBeanUtils.copyProperties("+entityNameLower+", "+modelNameLower+");\n");
		sb.append("\t\treturn "+ modelNameLower+";\n");
		sb.append("\t}\n");
		sb.append("\n");
		sb.append("\t/**\n\t * model转换实体\n\t */\n");
		sb.append("\tpublic "+entityName+" prototype() {\n");
		sb.append("\t\t" + entityName + " "+entityNameLower+" = new "+entityName+"();\n");
		sb.append("\t\tBeanUtils.copyProperties(this, "+entityNameLower+");\n");
		sb.append("\t\treturn "+entityNameLower+";\n");
		sb.append("\t}\n");
		sb.append("\n");
		sb.append("\t/** 获取【当前页码】 **/\n");
		sb.append("\tpublic int getPageNo() {\n");
		sb.append("\t\treturn pageNo;\n");
		sb.append("\t}\n");
		sb.append("\n");
		sb.append("\t/** 设置【当前页码】 **/\n");
		sb.append("\tpublic void setPageNo(int pageNo) {\n");
		sb.append("\t\tthis.pageNo = pageNo;\n");
		sb.append("\t}\n");
		sb.append("\n");
		sb.append("\t/** 获取【每页数据条数】 **/\n");
		sb.append("\tpublic int getPageSize() {\n");
		sb.append("\t\treturn pageSize;\n");
		sb.append("\t}\n");
		sb.append("\n");
		sb.append("\t/** 设置【每页数据条数】 **/\n");
		sb.append("\tpublic void setPageSize(int pageSize) {\n");
		sb.append("\t\tthis.pageSize = pageSize;\n");
		sb.append("\t}\n");
		sb.append("\n");
		sb.append("\t/** 获取【条件查询】 **/\n");
		sb.append("\tpublic String getSearchName() {\n");
		sb.append("\t\treturn searchName;\n");
		sb.append("\t}\n");
		sb.append("\n");
		sb.append("\t/** 设置【条件查询】 **/\n");
		sb.append("\tpublic void setSearchName(String searchName) {\n");
		sb.append("\t\tthis.searchName = searchName;\n");
		sb.append("\t}\n");
		
		sb.append("\n");
		sb.append("}");
		return String.format(sb.toString(), modelName);
	}
	
	public String service(){
		StringBuilder sb = new StringBuilder();
		sb.append("package ").append(servicePackage).append(";\n");
		sb.append("import ").append(modelPackage).append(".").append(modelName).append(";\n");
		sb.append("import com.hc.sys.common.form.Result;\n");
		sb.append("/**\n * "+moduleName+"\n * @author "+author+"\n * @version "+version+"\n * @since "+since+"\n */\n");
		sb.append("public interface %s{\n");
		sb.append("\n");
		sb.append("\t/**\n \t * 列表\n \t * @param model\n \t * @return\n \t */\n");
		sb.append("\tpublic Result list("+modelName+" model);\n");
		sb.append("\n");
		sb.append("\t/**\n \t * 添加\n \t * @param model\n \t * @return\n \t */\n");
		sb.append("\tpublic Result add("+modelName+" model);\n");
		sb.append("\n");
		sb.append("\t/**\n \t * 修改\n \t * @param model\n \t * @return\n \t */\n");
		sb.append("\tpublic Result update("+modelName+" model);\n");
		sb.append("\n");
		sb.append("\t/**\n \t * 获取\n \t * @param model\n \t * @return\n \t */\n");
		sb.append("\tpublic Result getById("+modelName+" model);\n");
		sb.append("\n");
		sb.append("}");
		return String.format(sb.toString(), serviceName, entityName, entityName);
	}
	
	public String serviceImpl(){
		StringBuilder sb = new StringBuilder();
		sb.append("package ").append(serviceImplPackage).append(";\n");
		sb.append("import org.springframework.stereotype.Service;\n");
		sb.append("import org.springframework.transaction.annotation.Transactional;\n");
		sb.append("import javax.annotation.Resource;\n");
		sb.append("import com.hc.sys.common.form.Result;\n");
		sb.append("import ").append(daoPackage).append(".").append(daoName).append(";\n");
		sb.append("import ").append(modelPackage).append(".").append(modelName).append(";\n");
		sb.append("import ").append(servicePackage).append(".").append(serviceName).append(";\n");
		sb.append("/**\n * "+moduleName+"\n * @author "+author+"\n * @version "+version+"\n * @since "+since+"\n */\n");
		sb.append("@Service\n");
		sb.append("public class ").append(serviceImplName).append(" implements ").append(serviceName).append(" {\n");
		sb.append("\n");
		sb.append("\t@Resource\n");
		sb.append("\tprivate ").append(daoName).append(" "+StringUtil.firstCharLowerCase(daoName) + ";");
		sb.append("\n");
		sb.append("\t/**\n \t * 列表\n \t * @param model\n \t * @return\n \t */\n");
		sb.append("\t@Override\n");
		sb.append("\tpublic Result list("+modelName+" model){\n");
		sb.append("\n");
		sb.append("\t\treturn null;\n");
		sb.append("\t}\n");
		sb.append("\n");
		sb.append("\t/**\n \t * 添加\n \t * @param model\n \t * @return\n \t */\n");
		sb.append("\t@Override\n");
		sb.append("\t@Transactional\n");
		sb.append("\tpublic Result add("+modelName+" model){\n");
		sb.append("\n");
		sb.append("\t\treturn null;\n");
		sb.append("\t}\n");
		sb.append("\n");
		sb.append("\t/**\n \t * 修改\n \t * @param model\n \t * @return\n \t */\n");
		sb.append("\t@Override\n");
		sb.append("\t@Transactional\n");
		sb.append("\tpublic Result update("+modelName+" model){\n");
		sb.append("\n");
		sb.append("\t\treturn null;\n");
		sb.append("\t}\n");
		sb.append("\n");
		sb.append("\t/**\n \t * 获取\n \t * @param model\n \t * @return\n \t */\n");
		sb.append("\t@Override\n");
		sb.append("\tpublic Result getById("+modelName+" model){\n");
		sb.append("\n");
		sb.append("\t\treturn null;\n");
		sb.append("\t}\n");
		sb.append("\n");
		sb.append("}");
		return String.format(sb.toString(), entityName);
	}
	
	public String controller(){
		String serviceNameUncapitalize = StringUtil.uncapitalize(serviceName);
		StringBuilder sb = new StringBuilder();
		sb.append("package ").append(controllerPackage).append(";\n");
		sb.append("import javax.annotation.Resource;\n");
		sb.append("import org.springframework.web.bind.annotation.RequestMapping;\n");
		sb.append("import org.springframework.web.bind.annotation.RestController;\n");
		sb.append("import org.springframework.web.bind.annotation.ResponseBody;\n");
		sb.append("import org.springframework.web.bind.annotation.RequestMethod;\n");
		sb.append("\n");
		sb.append("import com.hc.sys.common.exception.BusinessException;\n");
		sb.append("import com.hc.sys.core.common.web.BaseController;\n");
		sb.append("import ").append(modelPackage).append(".").append(modelName).append(";\n");
		sb.append("import ").append(servicePackage).append(".").append(serviceName).append(";\n");
		sb.append("/**\n * "+moduleName+"\n * @author "+author+"\n * @version "+version+"\n * @since "+since+"\n */\n");
		sb.append("@RestController\n");
		sb.append("@RequestMapping(\"/").append(StringUtil.uncapitalize(dbModel)).append("/").append(entityName.toLowerCase()).append("\")\n");
		sb.append("public class ").append(controllerName).append(" extends BaseController<")
		.append(modelName).append("> {\n");
		sb.append("\n");
		sb.append("\t@Resource\n");
		sb.append("\t").append(serviceName).append(" ").append(serviceNameUncapitalize).append(";\n\n");
		sb.append("\t/**\n \t * 列表\n \t * @param model\n \t * @return\n \t */\n");
		sb.append("\t@RequestMapping(value = \"/list\", method = RequestMethod.POST)\n");
		sb.append("\t@ResponseBody\n");
		sb.append("\tpublic Object list("+modelName+" model) throws BusinessException {\n");
		sb.append("\t\treturn "+serviceNameUncapitalize+".list(model);\n");
		sb.append("\t}\n");
		sb.append("\n");
		sb.append("\t/**\n \t * 添加\n \t * @param model\n \t * @return\n \t */\n");
		sb.append("\t@RequestMapping(value = \"/add\", method = RequestMethod.POST)\n");
		sb.append("\t@ResponseBody\n");
		sb.append("\tpublic Object add("+modelName+" model) throws BusinessException {\n");
		sb.append("\t\treturn "+serviceNameUncapitalize+".add(model);\n");
		sb.append("\t}\n");
		sb.append("\n");
		sb.append("\t/**\n \t * 修改\n \t * @param model\n \t * @return\n \t */\n");
		sb.append("\t@RequestMapping(value = \"/update\", method = RequestMethod.POST)\n");
		sb.append("\t@ResponseBody\n");
		sb.append("\tpublic Object update("+modelName+" model) throws BusinessException {\n");
		sb.append("\t\treturn "+serviceNameUncapitalize+".update(model);\n");
		sb.append("\t}\n");
		sb.append("\n");
		sb.append("\t/**\n \t * 获取\n \t * @param model\n \t * @return\n \t */\n");
		sb.append("\t@RequestMapping(value = \"/getById\", method = RequestMethod.POST)\n");
		sb.append("\t@ResponseBody\n");
		sb.append("\tpublic Object getById("+modelName+" model) throws BusinessException {\n");
		sb.append("\t\treturn "+serviceNameUncapitalize+".getById(model);\n");
		sb.append("\t}\n");
		sb.append("}");
		return sb.toString();
	}
	
	public String dao(){
		StringBuilder sb = new StringBuilder();
		sb.append("package ").append(daoPackage).append(";\n");
		sb.append("import com.hc.sys.common.dao.BaseDao;\n");
		sb.append("import ").append(entityPackage).append(".").append(entityName).append(";\n");
		sb.append("/**\n * "+moduleName+"\n * @author "+author+"\n * @version "+version+"\n * @since "+since+"\n */\n");
		sb.append("public interface %s extends BaseDao<%s> {\n");
		sb.append("\t");
		sb.append("\n");
		sb.append("}");
		return String.format(sb.toString(), daoName,entityName);
	}
	
	public String daoImpl(){
		StringBuilder sb = new StringBuilder();
		sb.append("package ").append(daoImplPackage).append(";\n");
		sb.append("import org.springframework.stereotype.Repository;\n");
		sb.append("import com.hc.sys.common.dao.jpa.BaseDaoImpl;\n");
		sb.append("import ").append(entityPackage).append(".").append(entityName).append(";\n");
		sb.append("import ").append(daoPackage).append(".").append(daoName).append(";\n");
		sb.append("/**\n * "+moduleName+"\n * @author "+author+"\n * @version "+version+"\n * @since "+since+"\n */\n");
		sb.append("@Repository\n");
		sb.append("public class ").append(daoImplName).append(" extends BaseDaoImpl<").append(entityName).append("> implements ").append(daoName).append(" {\n");
		sb.append("\n");
		sb.append("}");
		return sb.toString();
	}
	
	/** 获取【packageName】 **/
	public String getPackageName() {
		return packageName;
	}

	/** 设置【packageName】 **/
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/** 获取【entityName】 **/
	public String getEntityName() {
		return entityName;
	}

	/** 设置【entityName】 **/
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	/** 获取【tableName】 **/
	public String getTableName() {
		return tableName;
	}

	/** 设置【tableName】 **/
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/** 获取【entityPackage】 **/
	public String getEntityPackage() {
		return entityPackage;
	}

	/** 设置【entityPackage】 **/
	public void setEntityPackage(String entityPackage) {
		this.entityPackage = entityPackage;
	}

	/** 获取【modelPackage】 **/
	public String getModelPackage() {
		return modelPackage;
	}

	/** 设置【modelPackage】 **/
	public void setModelPackage(String modelPackage) {
		this.modelPackage = modelPackage;
	}

	/** 获取【servicePackage】 **/
	public String getServicePackage() {
		return servicePackage;
	}

	/** 设置【servicePackage】 **/
	public void setServicePackage(String servicePackage) {
		this.servicePackage = servicePackage;
	}

	/** 获取【serviceImplPackage】 **/
	public String getServiceImplPackage() {
		return serviceImplPackage;
	}

	/** 设置【serviceImplPackage】 **/
	public void setServiceImplPackage(String serviceImplPackage) {
		this.serviceImplPackage = serviceImplPackage;
	}

	/** 获取【controllerPackage】 **/
	public String getControllerPackage() {
		return controllerPackage;
	}

	/** 设置【controllerPackage】 **/
	public void setControllerPackage(String controllerPackage) {
		this.controllerPackage = controllerPackage;
	}

	/** 获取【daoPackage】 **/
	public String getDaoPackage() {
		return daoPackage;
	}

	/** 设置【daoPackage】 **/
	public void setDaoPackage(String daoPackage) {
		this.daoPackage = daoPackage;
	}

	/** 获取【daoImplPackage】 **/
	public String getDaoImplPackage() {
		return daoImplPackage;
	}

	/** 设置【daoImplPackage】 **/
	public void setDaoImplPackage(String daoImplPackage) {
		this.daoImplPackage = daoImplPackage;
	}

	/** 获取【modelName】 **/
	public String getModelName() {
		return modelName;
	}

	/** 设置【modelName】 **/
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	/** 获取【serviceName】 **/
	public String getServiceName() {
		return serviceName;
	}

	/** 设置【serviceName】 **/
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/** 获取【serviceImplName】 **/
	public String getServiceImplName() {
		return serviceImplName;
	}

	/** 设置【serviceImplName】 **/
	public void setServiceImplName(String serviceImplName) {
		this.serviceImplName = serviceImplName;
	}

	/** 获取【controllerName】 **/
	public String getControllerName() {
		return controllerName;
	}

	/** 设置【controllerName】 **/
	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}

	/** 获取【daoName】 **/
	public String getDaoName() {
		return daoName;
	}

	/** 设置【daoName】 **/
	public void setDaoName(String daoName) {
		this.daoName = daoName;
	}

	/** 获取【daoImplName】 **/
	public String getDaoImplName() {
		return daoImplName;
	}

	/** 设置【daoImplName】 **/
	public void setDaoImplName(String daoImplName) {
		this.daoImplName = daoImplName;
	}
	
	/** 获取【moduleName】 **/
	public String getModuleName() {
		return moduleName;
	}

	/** 设置【moduleName】 **/
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	/** 获取【author】 **/
	public String getAuthor() {
		return author;
	}

	/** 设置【author】 **/
	public void setAuthor(String author) {
		this.author = author;
	}

	/** 获取【version】 **/
	public String getVersion() {
		return version;
	}

	/** 设置【version】 **/
	public void setVersion(String version) {
		this.version = version;
	}

	/** 获取【since】 **/
	public String getSince() {
		return since;
	}

	/** 设置【since】 **/
	public void setSince(String since) {
		this.since = since;
	}
	
	/** 获取【dbModel】 **/
	public String getDbModel() {
		return dbModel;
	}

	/** 设置【dbModel】 **/
	public void setDbModel(String dbModel) {
		this.dbModel = dbModel;
	}

	/** 获取【projectPackage】 **/
	public String getProjectPackage() {
		return projectPackage;
	}

	/** 设置【projectPackage】 **/
	public void setProjectPackage(String projectPackage) {
		this.projectPackage = projectPackage;
	}

	public static void main(String[] args) {
		String packageName = "test.com.one";
		String entityName = "TestONE";
		String tableName = "test001";
		CodeTemplate ct = new CodeTemplate(packageName, entityName, tableName,"com.zc.sys.core");
		ct.setModuleName("测试模块");
		ct.setDbModel("M");
		String s1 = ct.entity();
		System.out.println(s1);
		String s2 = ct.model();
		System.out.println(s2);
		String s3 = ct.service();
		System.out.println(s3);
		String s4 = ct.serviceImpl();
		System.out.println(s4);
		String s5 = ct.dao();
		System.out.println(s5);
		String s6 = ct.daoImpl();
		System.out.println(s6);
		String s7 = ct.controller();
		System.out.println(s7);
	}

}
