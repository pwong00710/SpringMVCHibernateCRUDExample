package test.xml;


import org.wiztools.xsdgen.XsdGen;
import java.io.File;
import java.io.FileOutputStream;

public class XsdGenTest {

	public static void main(String[] args) throws Exception {
		XsdGenTest test = new XsdGenTest();
		test.genXsd("country");
		test.genXsd("countryList");
	}
	
	public void genXsd(String name) throws Exception {
		XsdGen gen = new XsdGen();
		gen.parse(new File("H:/Tutorial/soa/workspace/SpringMVCHibernateCRUDExample/src/test/resources/"+name+".xml"));
		File out = new File("H:/Tutorial/soa/workspace/SpringMVCHibernateCRUDExample/src/test/resources/"+name+".xsd");
		gen.write(new FileOutputStream(out));
		
	}
}
