package com.hc.sys.common.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;



import com.hc.sys.common.util.log.LogUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 工具类-freemarker的模板处理
 * @author fangyong
 * @version 1.0.0.0
 * @since 2017年11月16日
 */
public class FreemarkerUtil {

	public static Configuration CONFIG;

	public static String renderTemplate(String s, Object data) throws IOException, TemplateException {
		Template t = new Template(null, new StringReader(s), CONFIG);
		// 执行插值，并输出到指定的输出流中
		StringWriter w = new StringWriter();
		t.getConfiguration();
		try {
			t.process(data, w);
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("填充模板内容出现异常，模板："+s);
		}

		return w.getBuffer().toString();
	}

	public static String renderFileTemplate(String file, Map<String, Object> data) throws IOException,
		TemplateException {
		Configuration cfg = CONFIG;
		cfg.setDefaultEncoding("UTF-8");
		// 取得模板文件
		Template t = cfg.getTemplate(file);
		// 执行插值，并输出到指定的输出流中
		StringWriter w = new StringWriter();
		t.getConfiguration();
		t.process(data, w);
		return w.getBuffer().toString();
	}

}
