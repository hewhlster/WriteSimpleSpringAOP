package org.fjh.aop;



import lombok.Setter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 切面是切入和通知（增强逻辑）的定义
 */

public class Aspect {
    private Advice advice;
    private PointCut pointCut;

    public Aspect(Advice advice,PointCut pointCut) {
        this.advice = advice;
        this.pointCut = pointCut;
    }

    //模拟Spring容器，保存bean
    private Map beanMap = new ConcurrentHashMap<>();

    //将对像加入模拟Spring容器
    public void registerBean(final Object clazz,String beanName){
        Object newInstance=null;
        if (clazz!=null &&
                clazz.getClass().getPackage().getName().matches(pointCut.getMatchPackages())){
            InvocationHandler invocationHandler = new JackInvocationHandler();
            ((JackInvocationHandler) invocationHandler).setTarget(clazz);
            newInstance = Proxy.newProxyInstance(
                    clazz.getClass().getClassLoader(),
                    clazz.getClass().getInterfaces(),
                    invocationHandler
            );
           }
        else {
            newInstance = clazz;
        }


        //将代理过后的bean加入容器
        beanMap.put(beanName,newInstance);
        }



    //从容器中根据bean的名字取得bean
        public Object getBean(String beanName){
            Object object= beanMap.get(beanName);
        return object ;
    }

    /**
     * 代理处理类
     */
    @Setter
    class JackInvocationHandler implements  InvocationHandler{
        //目标对象
        private Object target;
        //回调方法
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object ret = null;//返回值
                            if (method.getName().matches(pointCut.getMatchMethods())) {
                                //符合pointcut的定义方法进行增强
                                try {
                                    //调用增强逻辑
                                    advice.before();
                                    ret = method.invoke(target, args);
                                    advice.around();
                                    advice.aftering();
                                } catch (Exception e) {
                                    advice.exception();
                                } finally {
                                    advice.after();
                                }
                            } else {
                                ret = method.invoke(target, args);
                            }
            return ret;
        }
    }
}
