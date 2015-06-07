package org.lsh.io;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class GetPathTest {

	public void test() throws IOException {
		// 第一种：
		File f = new File(this.getClass().getResource("/").getPath());
		System.out.println(f);
		// 结果:
		// C:\Documents%20and%20Settings\Administrator\workspace\projectName\bin
		// 获取当前类的所在工程路径;
		// 如果不加“/”
		File f2 = new File(this.getClass().getResource("").getPath());
		System.out.println(f2);
		// 结果：
		// C:\Documents%20and%20Settings\Administrator\workspace\projectName\bin\com\test
		// 获取当前类的绝对路径；

		// 第二种：
		File directory = new File("");// 参数为空
		String courseFile = directory.getCanonicalPath();
		System.out.println(courseFile);
		// 结果：
		// C:\Documents and Settings\Administrator\workspace\projectName
		// 获取当前类的所在工程路径;

		// 第三种：
		URL xmlpath = this.getClass().getClassLoader().getResource("selected.txt");
		System.out.println(xmlpath);
		// 结果：
		// file:/C:/Documents%20and%20Settings/Administrator/workspace/projectName/bin/selected.txt
		// 获取当前工程src目录下selected.txt文件的路径

		// 第四种：
		System.out.println("System.getProperty(\"user.dir\") ：" + System.getProperty("user.dir"));
		// 结果：
		// C:\Documents and Settings\Administrator\workspace\projectName
		// 获取当前工程路径

		// 第五种：
		System.out.println(System.getProperty("java.class.path"));
		// 结果：
		// C:\Documents and Settings\Administrator\workspace\projectName\bin
		// 获取当前工程路径
	}

	/**
	 * 得到类所在的路径，例如E:/workspace/JavaGUI/bin/com/util
	 * 效率低：有待提高
	 * @return
	 * @throws java.lang.Exception
	 */
	public String getClassPath() throws Exception {
		try {
			String strClassName = getClass().getName();
			String strPackageName = "";
			if (getClass().getPackage() != null) {
				strPackageName = getClass().getPackage().getName();
			}
			String strClassFileName = "";
			if (!"".equals(strPackageName)) {
				strClassFileName = strClassName.substring(strPackageName.length() + 1, strClassName.length());
			} else {
				strClassFileName = strClassName;
			}
			URL url = null;
			url = getClass().getResource(strClassFileName + ".class");
			String strURL = url.toString();
			strURL = strURL.substring(strURL.indexOf('/') + 1, strURL.lastIndexOf('/'));
			// 返回当前类的路径，并且处理路径中的空格，因为在路径中出现的空格如果不处理的话，
			// 在访问时就会从空格处断开，那么也就取不到完整的信息了，这个问题在web开发中尤其要注意
			return strURL.replaceAll("%20", " ");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		/*
		 * try { new GetPathTest().test(); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */
		try {
			System.out.println(new GetPathTest().getClassPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
