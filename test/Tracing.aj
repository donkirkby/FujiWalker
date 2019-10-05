/*
 * Created on Jun 2, 2005
 *
 */
/*
import main.SolutionWriter;
import test.SolutionWriterTest;
import java.io.Writer;
*/
/**
 * @author dkirkby
 *
 */
public aspect Tracing {
	/*
	pointcut targetMethod()
	: execution(* SolutionWriterTest.*(..))
	|| execution(* SolutionWriter.*(..));
	
	pointcut createWriter()
	: call(Writer+.new(..));
	
	before() : targetMethod() {
		System.out.println(thisJoinPointStaticPart.getSignature().getName() + " start --");
	}
	after() : targetMethod() {
		System.out.println(thisJoinPointStaticPart.getSignature().getName() + " end --");
	}
	
	before() : createWriter() {
		System.out.println("Creating writer.");
	}
	*/
}
