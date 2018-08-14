package com.sun.fighter.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.Expression;

/**
 * 全局监听器和连线监听器
 * 流程实例start、end、take的时候调用。take是监控连线的时候使用的。
 */
public class MyExecutionListener implements ExecutionListener {

    private Expression fieldName;
    private Expression userType;

    public Expression getExpression() {
        return fieldName;
    }

    public void setExpression(Expression fieldName) {
        this.fieldName = fieldName;
    }

    public Expression getUserType() {
        return userType;
    }

    public void setUserType(Expression userType) {
        this.userType = userType;
    }

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {

        if(fieldName != null){
            System.out.println("流程监听器" + fieldName.getValue(delegateExecution));
        }
        if(userType != null){
            System.out.println("流程监听器" + userType.getValue(delegateExecution));
        }
        String eventName = delegateExecution.getEventName();
        //start
        if ("start".equals(eventName)) {
            System.out.println("start=========");
        }else if ("end".equals(eventName)) {
            System.out.println("end=========");
        }else if ("take".equals(eventName)) {
            System.out.println("take=========");
        }
    }
}
