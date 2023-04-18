package org.client.factory.core;

import java.util.List;

import org.testng.IAlterSuiteListener;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.xml.XmlSuite;

public class ParallelRunListners implements IAlterSuiteListener {
	XmlSuite suite ;
	@Override
	public void alter(List<XmlSuite> suites) {
		 this.suite = suites.get(0);
	}
	
	@Parameters({"IsParallel","thread_count"})
	public void setParallelAttributes( @Optional("false") String IsParallel, @Optional("1") String thread_count) {
		
		if (suite.getParameter("IsParallel").contains("true")) {
			// XmlSuite suite = context.getCurrentXmlTest().getSuite().getParentSuite();
			suite.setParallel(XmlSuite.ParallelMode.TESTS);
			suite.setThreadCount(Integer.valueOf(thread_count));
		}
	}
	
	
	 
		 
	 

}


