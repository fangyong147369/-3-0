//package com.hc.sys.common.util.codeGenerate;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
///**
// * 自动生成代码
// * 包含Entiy、Model、Service、Controller、Dao（可选）
// *
// * 生成之前需要修改实体名称、数据库表名称
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:applicationConfig.xml"})
//public class CodeGen {
//
//	@Test
//	public void generate() {
//
//		//实体名称(生成之前需要修改)
//		String entityName = "Testone";
//
//		//实体对应的数据库表名称(生成之前需要修改)
//		String tableName = "test1";
//
//		//包名称(并非完整包名，只是包含Entiy、View、Convert等的父包名)(为空时自动取实体名称的小写)
//		String packageName = "test.com.one";
//		//模块名称
//		String moduleName = "测试模块one";
//		//模块代码
//		String dbModel = "M";
//
//		HibernateCodeGenerate gen = new HibernateCodeGenerate();
//		gen.setPackageName(packageName);
//		gen.setEntityName(entityName);
//		gen.setTableName(tableName);
//		gen.setModuleName(moduleName);
//		gen.setDbModel(dbModel);
//        gen.generate();
//	}
//
//}
