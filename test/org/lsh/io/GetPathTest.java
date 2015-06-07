package org.lsh.io;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class GetPathTest {

	public void test() throws IOException {
		// ��һ�֣�
		File f = new File(this.getClass().getResource("/").getPath());
		System.out.println(f);
		// ���:
		// C:\Documents%20and%20Settings\Administrator\workspace\projectName\bin
		// ��ȡ��ǰ������ڹ���·��;
		// ������ӡ�/��
		File f2 = new File(this.getClass().getResource("").getPath());
		System.out.println(f2);
		// �����
		// C:\Documents%20and%20Settings\Administrator\workspace\projectName\bin\com\test
		// ��ȡ��ǰ��ľ���·����

		// �ڶ��֣�
		File directory = new File("");// ����Ϊ��
		String courseFile = directory.getCanonicalPath();
		System.out.println(courseFile);
		// �����
		// C:\Documents and Settings\Administrator\workspace\projectName
		// ��ȡ��ǰ������ڹ���·��;

		// �����֣�
		URL xmlpath = this.getClass().getClassLoader().getResource("selected.txt");
		System.out.println(xmlpath);
		// �����
		// file:/C:/Documents%20and%20Settings/Administrator/workspace/projectName/bin/selected.txt
		// ��ȡ��ǰ����srcĿ¼��selected.txt�ļ���·��

		// �����֣�
		System.out.println("System.getProperty(\"user.dir\") ��" + System.getProperty("user.dir"));
		// �����
		// C:\Documents and Settings\Administrator\workspace\projectName
		// ��ȡ��ǰ����·��

		// �����֣�
		System.out.println(System.getProperty("java.class.path"));
		// �����
		// C:\Documents and Settings\Administrator\workspace\projectName\bin
		// ��ȡ��ǰ����·��
	}

	/**
	 * �õ������ڵ�·��������E:/workspace/JavaGUI/bin/com/util
	 * Ч�ʵͣ��д����
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
			// ���ص�ǰ���·�������Ҵ���·���еĿո���Ϊ��·���г��ֵĿո����������Ļ���
			// �ڷ���ʱ�ͻ�ӿո񴦶Ͽ�����ôҲ��ȡ������������Ϣ�ˣ����������web����������Ҫע��
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
