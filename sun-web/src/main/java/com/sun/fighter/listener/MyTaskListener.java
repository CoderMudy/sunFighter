package com.sun.fighter.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * usertask节点配置了处理人所以触发assignment事件监听，当任务运转到usertask的时候触发了create事件。
 * 任务完成的时候，触发complete事件，因为任务完成之后，要从ACT_RU_TASK中删除这条记录，所以触发delete事件。
 */
public class MyTaskListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        String eventName = delegateTask.getEventName();

        if ("create".endsWith(eventName)) {
            System.out.println("create=========");
        } else if ("assignment".endsWith(eventName)) {
            System.out.println("assignment========");
        } else if ("complete".endsWith(eventName)) {
            System.out.println("complete===========");
        } else if ("delete".endsWith(eventName)) {
            System.out.println("delete=============");
        }
    }
}
