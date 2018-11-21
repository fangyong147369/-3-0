package com.hc.sys.common.util.codeGenerate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class GenerateUtil {

	public static boolean writeText(File targetFile, String content,
			String charSet) {
		if(targetFile.exists())return false;
		boolean flag = false;
		OutputStreamWriter out = null;
		if (!targetFile.getParentFile().exists()) {
			targetFile.getParentFile().mkdirs();
		}
		try {
			out = new OutputStreamWriter(new FileOutputStream(targetFile),
					charSet);
			out.write(content.toCharArray());
			flag = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
}
