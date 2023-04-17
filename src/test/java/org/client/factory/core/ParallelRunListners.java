package org.client.factory.core;

import java.util.List;

import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlSuite;

public class ParallelRunListners implements IAlterSuiteListener {

	
	@Override
	public void alter(List<XmlSuite> suites) {
		XmlSuite suite = suites.get(0);
		if (suite.getParameter("parallelRun").contains("true")) {

			// XmlSuite suite = context.getCurrentXmlTest().getSuite().getParentSuite();
			suite.setParallel(XmlSuite.ParallelMode.TESTS);
			suite.setThreadCount(2);
		}
	}
	 
		 
	 

}


