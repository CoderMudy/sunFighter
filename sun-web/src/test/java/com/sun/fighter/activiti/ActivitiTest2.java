package com.sun.fighter.activiti;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.fighter.SunFighterApplication;
import com.sun.fighter.study.system.domain.User;
import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.javax.el.ExpressionFactory;
import org.activiti.engine.impl.javax.el.ValueExpression;
import org.activiti.engine.impl.juel.ExpressionFactoryImpl;
import org.activiti.engine.impl.juel.SimpleContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.task.Task;
import org.activiti.validation.ProcessValidator;
import org.activiti.validation.ProcessValidatorFactory;
import org.activiti.validation.ValidationError;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SunFighterApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActivitiTest2 {

    @Resource
    private RepositoryService repositoryService;
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private TaskService taskService;

    @Test
    public void test3() {
        String processId = "dynamic";
        String processName = "动态创建流程";
        String processDesc = "测试动态创建流程";
        List<FlowElement> flowElements = new ArrayList<>();
        //1.创建模型
        BpmnModel bpmnModel = new BpmnModel();
        Process process = new Process();

        process.setId(processId);
        process.setName(processName);
        process.setDocumentation(processDesc);
        //2.添加流程
        //开始流程
        StartEvent startEvent = createStartEvent();
        process.addFlowElement(startEvent);
        flowElements.add(startEvent);

        UserTask userTask = createGroupTask("storekeeper", "待仓库人员提交", "管理员,仓库人员");
        process.addFlowElement(userTask);
        flowElements.add(userTask);

        UserTask userTask1 = createGroupTask("hospitalPurchaseBuyer", "待医院采购审核", "管理员,采购主管,采购专员");
        process.addFlowElement(userTask1);
        flowElements.add(userTask1);

        ExclusiveGateway exclusiveGateway = createExclusiveGateWay("hospitalPurchaseBuyerApprove","");
        process.addFlowElement(exclusiveGateway);
        flowElements.add(exclusiveGateway);

        UserTask userTask2 = createGroupTask("hospitalFinancial", "待医院财务审核", "管理员,财务主管,财务专员");
        process.addFlowElement(userTask2);
        flowElements.add(userTask2);

        ExclusiveGateway exclusiveGateway1 = createExclusiveGateWay("hospitalFinancialApprove","");
        process.addFlowElement(exclusiveGateway1);
        flowElements.add(exclusiveGateway1);

        UserTask userTask3 = createGroupTask("hospitalLeaderBuyer", "待医院领导审核", "管理员,院领导");
        process.addFlowElement(userTask3);
        flowElements.add(userTask3);

        ExclusiveGateway exclusiveGateway2 = createExclusiveGateWay("hospitalLeaderBuyerApprove","");
        process.addFlowElement(exclusiveGateway2);
        flowElements.add(exclusiveGateway2);

        UserTask userTask4 = createGroupTask("hospitalPurchaseOrder", "待生成订单", "管理员,采购主管,采购专员");
        process.addFlowElement(userTask4);
        flowElements.add(userTask4);

        //结束流程
        EndEvent endEvent = createEndEvent();
        process.addFlowElement(endEvent);
        flowElements.add(endEvent);
        //3.连线
        List<SequenceFlow> sequenceFlows = new ArrayList<>();
        List<SequenceFlow> startSequenceFlows = new ArrayList<>();
        FlowElement from = null;
        FlowElement to = null;
        SequenceFlow sequenceFlow = null;
        int size = flowElements.size();
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                continue;
            }
            from = flowElements.get(i);
            to = flowElements.get(i + 1);
            if(from instanceof ExclusiveGateway){
                sequenceFlows.clear();
                ExclusiveGateway fromGateway = (ExclusiveGateway) from;
                sequenceFlow = createSequenceFlow(from.getId(), to.getId(), "通过", "${"+fromGateway.getId()+"=='true'}");
                process.addFlowElement(sequenceFlow);
                sequenceFlows.add(sequenceFlow);
                if(to instanceof UserTask){
                    ((UserTask) to).setIncomingFlows(sequenceFlows);
                }
//                if(i==3){
//                    UserTask returnTask = (UserTask) flowElements.get(1);
//                    sequenceFlow = createSequenceFlow(from.getId(), returnTask.getId(), "不通过", "${"+fromGateway.getId()+"=='false'}");
//                    process.addFlowElement(sequenceFlow);
//                    sequenceFlows.add(sequenceFlow);
////                    fromGateway.setOutgoingFlows(sequenceFlows);
//                    startSequenceFlows.add(sequenceFlow);
//                }

//                returnTask.setIncomingFlows(sequenceFlows);
            }else{
                sequenceFlows.clear();
                sequenceFlow = createSequenceFlow(from.getId(), to.getId(), "", "");
                process.addFlowElement(sequenceFlow);
                sequenceFlows.add(sequenceFlow);
                if (from instanceof StartEvent) {
                    startEvent.setOutgoingFlows(sequenceFlows);
                }
                if (to instanceof UserTask) {
                    UserTask toTask = (UserTask) to;
                    toTask.setIncomingFlows(sequenceFlows);

                }

                if (from instanceof UserTask) {
                    UserTask fromTask = (UserTask) from;
                    fromTask.setIncomingFlows(sequenceFlows);
                }

                if(to instanceof ExclusiveGateway){
                    ExclusiveGateway toGateway = (ExclusiveGateway) to;
                    toGateway.setIncomingFlows(sequenceFlows);
                }
            }
        }
        bpmnModel.addProcess(process);
        //4.生成图形信息
        new BpmnAutoLayout(bpmnModel).execute();
        //bpmnModel 转换为标准的bpmn xml文件
        BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
        byte[] convertToXML = bpmnXMLConverter.convertToXML(bpmnModel);
        String bytes = new String(convertToXML);
        System.out.println("流程图XML：" + bytes);
        //验证bpmnModel 是否是正确的bpmn xml文件
        ProcessValidatorFactory processValidatorFactory=new ProcessValidatorFactory();
        ProcessValidator defaultProcessValidator = processValidatorFactory.createDefaultProcessValidator();
        //验证失败信息的封装ValidationError，如果size为0说明，bpmnmodel正确，大于0,说明自定义的bpmnmodel是错误的，不可以使用的。
        List<ValidationError> validate = defaultProcessValidator.validate(bpmnModel);
        System.out.println("验证bpmnModel:"+validate.size());
    }

    @Test
    public void test2() {
        BpmnModel bpmnModel = new BpmnModel();
        StartEvent startEvent = new StartEvent();
        startEvent.setId("start1shareniu");
        startEvent.setName("start1shareniu");

        UserTask userTask = new UserTask();
        userTask.setId("userTask1shareniu");
        userTask.setName("userTask1shareniu");

        EndEvent endEvent = new EndEvent();
        endEvent.setId("endEventshareniu");
        endEvent.setName("endEventshareniu");

        List<SequenceFlow> sequenceFlows = new ArrayList<SequenceFlow>();
        List<SequenceFlow> toEnd = new ArrayList<SequenceFlow>();

        SequenceFlow s1 = new SequenceFlow();
        s1.setId("starttouserTask");
        s1.setName("starttouserTask");
        s1.setSourceRef("start1shareniu");
        s1.setTargetRef("userTask1shareniu");
        sequenceFlows.add(s1);

        SequenceFlow s2 = new SequenceFlow();
        s2.setId("userTasktoend");
        s2.setName("userTasktoend");
        s2.setSourceRef("userTask1shareniu");
        s2.setTargetRef("endEventshareniu");
        toEnd.add(s2);

        startEvent.setOutgoingFlows(sequenceFlows);
        userTask.setOutgoingFlows(toEnd);
        userTask.setIncomingFlows(sequenceFlows);
        endEvent.setIncomingFlows(toEnd);

        Process process = new Process();
        process.setId("process1");
        process.addFlowElement(startEvent);
        process.addFlowElement(s1);
        process.addFlowElement(userTask);
        process.addFlowElement(s2);
        process.addFlowElement(endEvent);
        bpmnModel.addProcess(process);
        //3.生成图形信息
        new BpmnAutoLayout(bpmnModel).execute();
        //bpmnModel 转换为标准的bpmn xml文件
        BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
        byte[] convertToXML = bpmnXMLConverter.convertToXML(bpmnModel);
        String bytes = new String(convertToXML);
        System.out.println(bytes);
    }

    @Test
    public void addDeploy() throws IOException {
        BpmnModel bpmnModel = new BpmnModel();
        Process process = new Process();
        bpmnModel.addProcess(process);

        process.setId("demo");
        process.setName("动态");
        process.setDocumentation("动态测试");
        StartEvent startEvent = createStartEvent();
        EndEvent endEvent = createEndEvent();
        process.addFlowElement(startEvent);
        process.addFlowElement(createSequenceFlow(startEvent.getId(), "endEvent", "hao", ""));
        process.addFlowElement(endEvent);
        //3.生成图形信息
        new BpmnAutoLayout(bpmnModel).execute();
        //bpmnModel 转换为标准的bpmn xml文件
        BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
        byte[] convertToXML = bpmnXMLConverter.convertToXML(bpmnModel);
        String bytes = new String(convertToXML);
        System.out.println(bytes);

        Model modelData = repositoryService.newModel();
        ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, process.getName());
        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
        modelData.setMetaInfo(modelObjectNode.toString());
        modelData.setName(process.getName());
        modelData.setCategory("计划");

        repositoryService.saveModel(modelData);

        BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
        ObjectNode editorNode = jsonConverter.convertToJson(bpmnModel);

        repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
//        Deployment deployment = repositoryService.createDeployment().addString(process.getId()+".bpmn", bytes).name(process.getId()+"_deployment").deploy();
//        Deployment deployment = repositoryService.createDeployment().addBpmnModel(process.getId()+".bpmn", bpmnModel).name(process.getId()+"_deployment").deploy();
    }

    private UserTask createGroupTask(String id, String name, String candidateGroup) {
        List<String> candidateGroups = Arrays.asList(candidateGroup.split(","));
        UserTask userTask = new UserTask();
        userTask.setName(name);
        userTask.setId(id);
        userTask.setCandidateGroups(candidateGroups);
        return userTask;
    }

    private UserTask createUserTask(String id, String name) {
        UserTask userTask = new UserTask();
        userTask.setId(id);
        userTask.setName(name);
        return userTask;
    }

    private SequenceFlow createSequenceFlow(String form, String to, String name, String conditionExpression) {
        SequenceFlow sequenceFlow = new SequenceFlow();
        sequenceFlow.setSourceRef(form);
        sequenceFlow.setTargetRef(to);
        sequenceFlow.setName(name);
        if (StringUtils.isNotEmpty(conditionExpression)) {
            sequenceFlow.setConditionExpression(conditionExpression);
        }
        return sequenceFlow;
    }

    private ExclusiveGateway createExclusiveGateWay(String id, String name) {
        ExclusiveGateway exclusiveGateway = new ExclusiveGateway();
        exclusiveGateway.setId(id);
        exclusiveGateway.setName(name);
        return exclusiveGateway;
    }

    private StartEvent createStartEvent() {
        StartEvent startEvent = new StartEvent();
        startEvent.setId("startEvent");
        return startEvent;
    }

    private EndEvent createEndEvent() {
        EndEvent endEvent = new EndEvent();
        endEvent.setId("endEvent");
        return endEvent;
    }
}
