package cn.lanaya.generator.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import cn.lanaya.generator.dom.xml.Document;
import cn.lanaya.generator.table.FullyQualifiedTable;

public class XmlMapperWriter {

	public static void writeXmlMaper(FullyQualifiedTable table, Document document, String dir) {
		File file = new File(dir);
		file.mkdirs();
		File f = new File(file,  table.getMapperName() + ".xml");
		FileWriter fw = null;
		try {
			if(!f.exists()) {
				f.createNewFile();
			}
			fw = new FileWriter(f);
			fw.write(document.getFormattedContent());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
