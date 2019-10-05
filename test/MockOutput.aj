/*
 * Created on Jun 2, 2005
 *
 */
import test.MockOutputTest;
import java.io.Writer;

/**
 * @author dkirkby
 *
 */
public aspect MockOutput {
	pointcut testMethod(MockOutputTest test)
	: execution(* MockOutputTest+.*(..)) && this(test) && within(MockOutputTest+);
	
	pointcut getWriter()
	: call(Writer getWriter());
	
	pointcut createMockWriter(MockOutputTest test)
	: getWriter() && cflowbelow(testMethod(test));
	
	Writer around(MockOutputTest test) : createMockWriter(test) {
		return test.getOutputWriter();
	}

}
