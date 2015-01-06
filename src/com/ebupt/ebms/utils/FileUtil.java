package com.ebupt.ebms.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;

import org.apache.commons.io.FileUtils;

public class FileUtil {

	/**
	 * @param path
	 *            文件路径
	 * @param name
	 *            文件名字
	 * @param content
	 *            文件内容
	 */
	public static String saveFile(String path, String name, String content) {
		File tf = null;
		try {
			File dir = new File(path);
			FileUtils.forceMkdir(dir);
			tf = new File(dir, name);
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(tf), "UTF-8");
			out.write(content);
			out.flush();
			out.close();

			return path + name;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String writeStringToFile(String filePath, String content) {
		File file = new File(filePath);
		try {
			FileUtils.writeStringToFile(file, content, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String readFileToString(String filePath) {
		File file = new File(filePath);
		try {
			return FileUtils.readFileToString(file, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String saveFile(String path, String name, byte[] data) {
		File tf = null;
		try {
			File dir = new File(path);
			FileUtils.forceMkdir(dir);
			tf = new File(dir, name);
			FileUtils.writeByteArrayToFile(tf, data);

			return path + name;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static long getFileSize(String pathAndFileName) {
		File file = new File(pathAndFileName);
		return file.length();
	}

	public static String copyFileToDirectory(String filePath, String directory) {
		try {
			File oldFile = new File(filePath);
			File newPath = new File(directory);

			FileUtils.forceMkdir(newPath);
			FileUtils.copyFileToDirectory(oldFile, newPath);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return directory;
	}

	public static String moveFileToDirectory(String filePath, String directory) {
		try {
			File oldFile = new File(filePath);
			File newPath = new File(directory);

			FileUtils.forceMkdir(newPath);
			FileUtils.moveFileToDirectory(oldFile, newPath, false);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return directory;
	}

	public static void copyDirectoryToDirectory(String dir1, String dir2) {
		try {
			File file1 = new File(dir1);
			File file2 = new File(dir2);

			FileUtils.copyDirectoryToDirectory(file1, file2);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void copyDirectoryFilesToDirectory(String dir1, String dir2) {
		try {
			File file1 = new File(dir1);
			File file2 = new File(dir2);

			if (file1.exists()) {
				File files[] = file1.listFiles();
				for (File file : files) {
					if (file.isFile())
						FileUtils.copyFileToDirectory(file, file2);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void copyFile(String dir1, String dir2) {
		try {
			File file1 = new File(dir1);
			File file2 = new File(dir2);

			FileUtils.copyFile(file1, file2);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void copyURLToFile(String url, String path) {
		try {
			URL _url = new URL(url);
			File file = new File(path);

			FileUtils.copyURLToFile(_url, file);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param url
	 * @param path
	 *            文件保存路径
	 */
	public static void readUrl(String url, String path) {
		try {
			URL ur = new URL(url);
			InputStream input = ur.openStream();
			int size = 0;
			int totalSize = 0;
			byte[] buf = new byte[1024];
			while ((size = input.read(buf)) > 0) {
				totalSize += size;
			}

			ByteBuffer bb = ByteBuffer.allocate(totalSize);
			size = 0;
			input = ur.openStream();
			while ((size = input.read(buf)) > 0) {
				bb.put(buf, 0, size);
			}

			FileUtils.forceMkdir(new File(path.substring(0, path.lastIndexOf('/'))));

			if (url.endsWith(".txt")) {
				// String encode = null;// "UTF-8";
				String content = null;
				// if (encode != null) {
				// content = new String(bb.array(), encode);
				// } else {
				content = new String(bb.array());
				// }
				System.out.println(content);
				Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "gb2312"));
				out.write(content);
				out.close();
			} else {
				FileOutputStream out = new FileOutputStream(path);
				out.write(bb.array());
				out.close();
			}
			input.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void deleteFile(String filePath) {
		File file = new File(filePath);
		try {
			FileUtils.forceDelete(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void rmFile(String filePath) {
		try {
			String command = "rm -rf " + filePath + ".*";
			Process proc = Runtime.getRuntime().exec(command);
			proc.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void deleteDirectory(String directory) {
		File file = new File(directory);
		try {
			FileUtils.deleteDirectory(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean renameDirectory(String directory, String newDirectory) {
		File file = new File(directory);
		File newFile = new File(newDirectory);
		return file.renameTo(newFile);
	}

	public static String getFileName(String file) {
		File fi = new File(file);
		return fi.getName();
	}

	public static String getTarFile(String directory, String name) {
		String path = directory.substring(0, directory.indexOf(name));
		String tarpath = path + name + ".tar.gz";
		System.out.println(path);
		String cmd = "tar -zcvf " + tarpath + " " + name;
		System.out.println(cmd);

		try {
			Process proc = Runtime.getRuntime().exec(cmd, null, new File(path));
			proc.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return tarpath;
	}

	/**
	 * 
	 * @param oldName
	 *            源文件绝对路径
	 * @param newName
	 *            新文件名称
	 * @return 新文件绝对路径
	 */
	public static String changeFileName(String filePath, String newName) {
		String picName = filePath.substring(filePath.lastIndexOf('/') + 1);
		String endfix = picName.substring(picName.lastIndexOf('.'));
		File file = new File(filePath);
		filePath = filePath.substring(0, filePath.lastIndexOf('/') + 1) + newName + endfix;
		file.renameTo(new File(filePath));
		return filePath;
	}

	public static void main(String[] args) {
		// saveFile("D:/temp", "1.jpg", "你好");
		// saveFile("F:/123", "1.xml", "你好");
		// copyDirectoryFilesToDirectory("D:/1","D:/2");
//		copyURLToFile("http://10.1.1.151:8888/ebms_bj/public/content/images/ae4762428b33aa9c63e266d2c31976d8.jpg","D:/1.jpg");
		// readUrl("http://10.1.1.151:8888/ebms_bj/public/content/images/ae4762428b33aa9c63e266d2c31976d8.jpg","D:/1.jpg");
		writeStringToFile("H:/aaa/1.txt","killall stbclient");
	}
}
