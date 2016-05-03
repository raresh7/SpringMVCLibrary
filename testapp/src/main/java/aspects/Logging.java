package aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Logging {

	@Pointcut("execution(* controllers.*.*(..))")
	private void selectControls(){} 
	
	@AfterReturning(pointcut="selectControls()", returning="retVal")
	private void afterReturningAdvice(Object retVal){
		
//		AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(WebSpringConfig.class);
//		
//		StudentDAO service = appContext.getBean("sv", StudentDAO.class);
//		Student stud = new Student();
//		stud.setAge(22);
//		stud.setStudentName("gimi tu");
//		service.addStudent(stud);
		if(retVal != null)
			System.out.println("The control points you towards :" + retVal.toString() + " page");
		
	}
}
