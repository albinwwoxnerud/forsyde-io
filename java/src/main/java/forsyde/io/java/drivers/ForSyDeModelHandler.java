/**
 * 
 */
package forsyde.io.java.drivers;

import java.io.File;

import forsyde.io.java.core.ForSyDeModel;

/**
 * @author rjordao
 *
 */
public final class ForSyDeModelHandler {

	@SuppressWarnings("deprecation")
	public static ForSyDeModel loadModel(String filePath) throws Exception {
		ForSyDeModelDriver driver;
		if(filePath.endsWith(".xml")) {
			driver = new ForSyDeXMLDriver();
		} else if (filePath.endsWith(".forxml") || filePath.endsWith(".forsyde.xml")) {
			driver = new ForSyDeMLDriver();
		} else {
			throw new Exception("Only '.xml' and '.forxml' formats are supported.");
		}
		return driver.loadModel(filePath);
	}

	@SuppressWarnings("deprecation")
	public static ForSyDeModel loadModel(File file) throws Exception {
		ForSyDeModelDriver driver;
		if(file.getName().endsWith(".xml")) {
			driver = new ForSyDeXMLDriver();
		} else if (file.getName().endsWith(".forxml") || file.getName().endsWith(".forsyde.xml")) {
			driver = new ForSyDeMLDriver();
		} else {
			throw new Exception("Only '.xml' and '.forxml' formats are supported.");
		}
		return driver.loadModel(file);
	}
	
	@SuppressWarnings("deprecation")
	public static void writeModel(ForSyDeModel model, String filePath) throws Exception {
		ForSyDeModelDriver driver;
		if(filePath.endsWith(".xml")) {
			driver = new ForSyDeXMLDriver();
		} else if (filePath.endsWith(".forxml")) {
			driver = new ForSyDeMLDriver();
		} else if(filePath.endsWith(".graphml")) {
			driver = new ForSyDeGraphMLDriver();
		} else {
			throw new Exception("Supported write formats: ['forxml', 'graphml'].");
		}
		driver.writeModel(model, filePath);
	}

	@SuppressWarnings("deprecation")
	public static void writeModel(ForSyDeModel model, File file) throws Exception {
		ForSyDeModelDriver driver;
		if(file.getName().endsWith(".xml")) {
			driver = new ForSyDeXMLDriver();
		} else if (file.getName().endsWith(".forxml") || file.getName().endsWith(".forsyde.xml")) {
			driver = new ForSyDeMLDriver();
		} else if(file.getName().endsWith(".graphml")) {
			driver = new ForSyDeGraphMLDriver();
		} else {
			throw new Exception("Supported write formats: ['forxml', 'graphml'].");
		}
		driver.writeModel(model, file);
	}
}
