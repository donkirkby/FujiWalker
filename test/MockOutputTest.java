/*
 * Created on Jun 6, 2005
 *
 */
package test;

import java.io.StringWriter;
import java.io.Writer;

import junit.framework.TestCase;

/**
 * @author dkirkby
 *
 */
public class MockOutputTest extends TestCase {

	private Writer m_outputWriter;

	public java.io.Writer getOutputWriter() {
		return m_outputWriter;
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		
		m_outputWriter = new StringWriter();
	}
}
