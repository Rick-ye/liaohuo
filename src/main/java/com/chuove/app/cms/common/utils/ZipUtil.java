package com.chuove.app.cms.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

public class ZipUtil {
	private static int BUFFER = 1024;

	/**
	 * 解压缩zip包
	 * 
	 * @param zipFilePath
	 *            zip文件路径
	 * @param targetPath
	 *            解压缩到的位置，如果为null或空字符串则默认解压缩到跟zip包同目录跟zip包同名的文件夹下
	 * @throws IOException
	 */
	public static void unzip(String zipFilePath, String targetPath) throws IOException {
		ZipFile zipFile = new ZipFile(zipFilePath, "GBK"); // 以“GBK”编码创建zip文件，用来处理winRAR压缩的文件。
		Enumeration<ZipEntry> emu = zipFile.getEntries();

		while (emu.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) emu.nextElement();
			if (entry.isDirectory()) {
				new File(targetPath + entry.getName()).mkdirs();
				continue;
			}
			BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry));

			File file = new File(targetPath + entry.getName());
			File parent = file.getParentFile();
			if (parent != null && (!parent.exists())) {
				parent.mkdirs();
			}
			FileOutputStream fos = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(fos, BUFFER);

			byte[] buf = new byte[BUFFER];
			int len = 0;
			while ((len = bis.read(buf, 0, BUFFER)) != -1) {
				fos.write(buf, 0, len);
			}
			bos.flush();
			bos.close();
			bis.close();
		}
		zipFile.close();
	}
}
