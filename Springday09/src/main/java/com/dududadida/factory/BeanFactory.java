package com.dududadida.factory;

import com.dududadida.service.AccountService;
import com.dududadida.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @PackageName com.dududadida.factory
 * @ClassName BeanFactory
 * @Author wangzheng
 * @Date 2020/2/2 17:13
 * @Description 用于创建Service的代理对象的工厂
 */
public class BeanFactory {
    private TransactionManager tsm;

    public void setTsm(TransactionManager tsm) {
        this.tsm = tsm;
    }

    private AccountService accountService;

    public final void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 获取Service代理对象
     * @return
     */
    public AccountService getAccountService(){
        Proxy.newProxyInstance(accountService.getClass().getClassLoader(),
                AccountService.class.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * 添加事务的支持
                     * @param proxy
                     * @param method
                     * @param args
                     * @return
                     * @throws Throwable
                     */
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if ("testTransfer".equals(method.getName())) {
                            return method.invoke(accountService, args);
                        }

                        Object rtValue = null;

                        try {
                            //开启事务
                            tsm.beginTransaction();
                            //执行操作
                            rtValue = method.invoke(accountService, args);
                            //提交事务
                            tsm.commit();
                            //返回结果
                            return rtValue;
                        }catch (Exception e){
                            //回滚操作
                            tsm.rollback();
                            throw new RuntimeException(e);
                        }finally {
                            //释放连接
                            tsm.release();
                        }
                    }
                });
        return accountService;
    }
}
