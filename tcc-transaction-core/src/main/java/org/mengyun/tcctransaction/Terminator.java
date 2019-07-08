package org.mengyun.tcctransaction;

import org.apache.log4j.Logger;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.mengyun.tcctransaction.api.TransactionContextEditor;
import org.mengyun.tcctransaction.support.FactoryBuilder;
import org.mengyun.tcctransaction.utils.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * Created by changmingxie on 10/30/15.
 */
public class Terminator implements Serializable {

    private static final long serialVersionUID = -164958655471605778L;
    static final Logger logger = Logger.getLogger(Terminator.class.getSimpleName());

    public Terminator() {

    }

    public Object invoke(TransactionContext transactionContext, InvocationContext invocationContext, Class<? extends TransactionContextEditor> transactionContextEditorClass) {


        if (StringUtils.isNotEmpty(invocationContext.getMethodName())) {

            try {

                Object target = FactoryBuilder.factoryOf(invocationContext.getTargetClass())
                        .getInstance();

                Method method = null;

                method = target.getClass().getMethod(invocationContext.getMethodName(), invocationContext.getParameterTypes());

                FactoryBuilder.factoryOf(transactionContextEditorClass)
                        .getInstance()
                        .set(transactionContext, target, method, invocationContext.getArgs());
                logger.error("当前线程：" + Thread.currentThread().getName());
                logger.error("反射调用： " + invocationContext.getTargetClass() + "."+ invocationContext.getMethodName() + "参数：" + invocationContext.getArgs());
                return method.invoke(target, invocationContext.getArgs());

            } catch (Exception e) {
                throw new SystemException(e);
            }
        }
        return null;
    }
}
