package com.chuove.app.cms.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FileUtil {
	private static int BUFFER = 1024;

	public static void copyFileToDest(File sourceFile, String destPath) {
		OutputStream os = null;
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(sourceFile));
			File destDirPath = new File(destPath);
			if (!destDirPath.exists()) {
				destDirPath.mkdirs();
			}
			File destDirFile = new File(destDirPath.getAbsolutePath() + File.separator + sourceFile.getName());
			os = new FileOutputStream(destDirFile);
			byte[] buf = new byte[BUFFER];
			int len = 0;
			while ((len = bis.read(buf, 0, BUFFER)) != -1) {
				os.write(buf, 0, len);
			}
			os.flush();
		} catch (Exception e) {

		} finally {
			try {
				if (bis != null)
					bis.close();
				if (os != null)
					os.close();
			} catch (Exception e) {

			}
		}
	}

	public static void copyFileToDest(String sourceFile, String destPath) {
		File file = new File(sourceFile);
		OutputStream os = null;
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(file));
			File destDirPath = new File(destPath);
			if (!destDirPath.exists()) {
				destDirPath.mkdirs();
			}
			File destDirFile = new File(destDirPath.getAbsolutePath() + File.separator + file.getName());
			os = new FileOutputStream(destDirFile);
			byte[] buf = new byte[BUFFER];
			int len = 0;
			while ((len = bis.read(buf, 0, BUFFER)) != -1) {
				os.write(buf, 0, len);
			}
			os.flush();
		} catch (Exception e) {
              
		} finally {
			try {
				if (bis != null)
					bis.close();
				if (os != null)
					os.close();
			} catch (Exception e) {

			}
		}
	}

	public static void writeStringToFile(String content, File destFile) {
		if (!destFile.exists()) {
			if (!destFile.getParentFile().exists()) {
				destFile.getParentFile().mkdirs();
			}
		}
		OutputStreamWriter write = null;
		BufferedWriter writer = null;
		try {
			write = new OutputStreamWriter(new FileOutputStream(destFile), "UTF-8");
			writer = new BufferedWriter(write);
			writer.write(content);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (write != null) {
					write.close();
				}
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void writeTo(InputStream in, String fileName, String destPath) {
		OutputStream os = null;
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(in);
			File destDirPath = new File(destPath);
			if (!destDirPath.exists()) {
				destDirPath.mkdirs();
			}
			File destDirFile = new File(destDirPath.getAbsolutePath() + File.separator + fileName);
			os = new FileOutputStream(destDirFile);
			byte[] buf = new byte[BUFFER];
			int len = 0;
			while ((len = bis.read(buf, 0, BUFFER)) != -1) {
				os.write(buf, 0, len);
			}
			os.flush();
		} catch (Exception e) {

		} finally {
			try {
				if (bis != null)
					bis.close();
				if (os != null)
					os.close();
			} catch (Exception e) {

			}
		}
	}
public static void renameTo(String sourceFile,String destDir,String destFilename){
	File file = new File(sourceFile);
	OutputStream os = null;
	BufferedInputStream bis = null;
	try {
		bis = new BufferedInputStream(new FileInputStream(file));
		File destDirPath = new File(destDir);
		if (!destDirPath.exists()) {
			destDirPath.mkdirs();
		}
		File destDirFile = new File(destDirPath.getAbsolutePath() + File.separator + destFilename);
		os = new FileOutputStream(destDirFile);
		byte[] buf = new byte[BUFFER];
		int len = 0;
		while ((len = bis.read(buf, 0, BUFFER)) != -1) {
			os.write(buf, 0, len);
		}
		os.flush();
	} catch (Exception e) {
          
	} finally {
		try {
			if (bis != null)
				bis.close();
			if (os != null)
				os.close();
		} catch (Exception e) {

		}
	}
}
	public static void delFileAndDir(String filepath) throws IOException {
		File f = new File(filepath);
		if (f.exists()) {
			if (f.isFile()) {
				f.delete();
				return;
			}
			if (f.isDirectory()) {
				if (f.listFiles().length == 0) {
					f.delete();
				} else {
					File delFile[] = f.listFiles();
					int i = f.listFiles().length;
					for (int j = 0; j < i; j++) {
						if (delFile[j].isDirectory()) {
							delFileAndDir(delFile[j].getAbsolutePath());
						}
						delFile[j].delete();
					}
				}
			}
		}
	}

	public static int count = 1;
	public static StringBuffer sb = new StringBuffer();

	public static void main(String[] args) {
		// try {
		// delFileAndDir("D:\\zipDir2");
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		File sourceDir = new File("D:\\zipDir2");
		sb.append("[");
		sb.append(getData("楼盘管理", 1, 0, false));
		print(sourceDir, 2, 1);
		sb.append("]");
		System.out.println(sb.toString());
	}

	private static String getData(String name, int id, int pId, boolean first) {
		StringBuffer data = new StringBuffer();
		if (first) {
			data.append(",");
		}
		String[] tmps = name.split("_");
		if (tmps.length == 2) {
			name = tmps[1];
		}
		data.append("{");
		data.append("\"id\":" + id + ",\"pId\":" + pId + ",\"name\":\"" + name + "\",\"tId\":\"dir_600_1_" + id + "\"");
		data.append("}");
		return data.toString();
	}

	public static void print(File sourceDir, int id, int pId) {
		File[] files = sourceDir.listFiles();
		if (files == null || files.length == 0)
			return;
		List<File> fileList = new ArrayList<File>();
		for (File f : files) {
			fileList.add(f);
		}
		Collections.sort(fileList, new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		count++;
		System.out.println("id="+id+",pId="+pId);
		for (File file : fileList) {
			if (file.isDirectory()) {
				id = count;
				sb.append(getData(file.getName(), id, pId, true));
				print(file, id, id);
			} else {
				System.out.println(id+file.getName());
			}
			id = count;

		}
	}
}
