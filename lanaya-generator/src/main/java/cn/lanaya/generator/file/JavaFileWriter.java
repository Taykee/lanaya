package cn.lanaya.generator.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import cn.lanaya.generator.dom.java.CompilationUnit;
import cn.lanaya.generator.dom.java.QualifiedJavaType;

public class JavaFileWriter {
	
	public static void writer(List<CompilationUnit> units, String dir) {
		File f = new File(dir);
		f.mkdirs();
		try {
			for (CompilationUnit unit : units) {
				QualifiedJavaType type = unit.getType();
				String baseShortName = type.getBaseShortName();
				File file = new File(f, baseShortName + ".java");
				if(!file.exists()) {
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file);
				fw.write(unit.getFormattedContent());
				fw.flush();
				fw.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void writer(CompilationUnit unit, String dir) {
		File f = new File(dir);
		f.mkdirs();
		QualifiedJavaType type = unit.getType();
		String baseShortName = type.getBaseShortName();
		File file = new File(f, baseShortName + ".java");
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			fw.write(unit.getFormattedContent());
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
