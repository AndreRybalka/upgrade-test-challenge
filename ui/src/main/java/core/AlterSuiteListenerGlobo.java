package core;

import core.utils.ConfigVariables.Country;
import core.utils.ConfigVariables.SystemPropertyKey;
import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlSuite;

import java.util.List;

/**
 * Changes standard Suite Listener behavior
 *
 *
 */
public class AlterSuiteListenerGlobo implements IAlterSuiteListener {

	@Override
	public void alter(List<XmlSuite> suites) {
		XmlSuite suite = suites.get(0);
		suite.addIncludedGroup(

				Country.getCountry(System.getProperty(SystemPropertyKey.LOCALE.getValue())).getValue().toLowerCase());
	}
}
