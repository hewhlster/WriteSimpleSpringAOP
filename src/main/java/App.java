import org.fjh.aop.Advice;
import org.fjh.aop.Aspect;
import org.fjh.aop.PointCut;
import org.fjh.service.UserService;
import org.fjh.service.impl.UserServiceImpl;

/**
 * 实现AOP编程思想的小案例
 * 采用了JDK的动态代理
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //要增强的包和方法（正则表达式）
        String packagePattern ="org.fjh.service..*";
        String matchMethods="reg.*";
        //生成pointcut
        PointCut pointCut = new PointCut(packagePattern,matchMethods);
        //生成advice（增强）
        Advice advice = new Advice();
        //生成aspect（切面）
        Aspect aspect = new Aspect(advice,pointCut);

        //生成目标对象
        UserService userService = new UserServiceImpl();

        //注册bean
        aspect.registerBean(userService,"userServcie");

        //取得bean

        UserService bean= (UserService) aspect.getBean("userServcie");


        //调用bean的方法
        bean.register();

        //bean.delete();

    }
}
