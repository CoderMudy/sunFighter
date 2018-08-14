package com.sun.fighter.study.controller.test;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Collection;

@Controller
public class TestController {

    @Resource
    private RepositoryService repositoryService;
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private TaskService taskService;

    @RequestMapping("/test")
    public void test(){
        Deployment deployment = repositoryService.createDeploymentQuery().deploymentId("22501").singleResult();

        System.out.println("deployId:" + deployment.getId());
        //查询流程定义
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinition.getId());

        Collection<FlowElement> flowElements = bpmnModel.getMainProcess().getFlowElements();
        for(FlowElement flowElement : flowElements){
            if(flowElement instanceof UserTask){
                System.out.println("flow id:"+flowElement.getId()+",flow name:"+flowElement.getName()+",flow class:"+flowElement.getClass().getName());
            }
        }



        Long businessKey = new Double(1000000 * Math.random()).longValue();
        //启动流程
        runtimeService.startProcessInstanceById(processDefinition.getId(), businessKey.toString());
    }
}
