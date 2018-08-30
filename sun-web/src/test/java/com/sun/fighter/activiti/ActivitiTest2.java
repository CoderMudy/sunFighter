package com.sun.fighter.activiti;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.io.Files;
import com.mxgraph.view.mxGraph;
import com.sun.fighter.SunFighterApplication;
import org.activiti.bpmn.BPMNLayout;
import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.explorer.util.XmlUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
    public void test5() throws IOException {
        System.out.println("………start…");

        // 1. Build up the model from scratch
        BpmnModel model = new BpmnModel();
        Process process=new Process();
        model.addProcess(process);
        final String PROCESSID ="process01";
        final String PROCESSNAME ="測試01";
        process.setId(PROCESSID);
        process.setName(PROCESSNAME);

        process.addFlowElement(createStartEvent());
        process.addFlowElement(createGroupTask("task1", "節點01", "candidateGroup1"));
        process.addFlowElement(createExclusiveGateWay("createExclusiveGateway1","createExclusiveGateway1"));
        process.addFlowElement(createGroupTask("task2", "節點02", "candidateGroup2"));
        process.addFlowElement(createExclusiveGateWay("createExclusiveGateway2","createExclusiveGateway2"));
        process.addFlowElement(createGroupTask("task3", "節點03", "candidateGroup3"));
        process.addFlowElement(createExclusiveGateWay("createExclusiveGateway3","createExclusiveGateway3"));
        process.addFlowElement(createGroupTask("task4", "節點04", "candidateGroup4"));
        process.addFlowElement(createEndEvent());

        process.addFlowElement(createSequenceFlow("startEvent", "task1", "", ""));
        process.addFlowElement(createSequenceFlow("task1", "task2", "", ""));
        process.addFlowElement(createSequenceFlow("task2", "createExclusiveGateway1", "", ""));
        process.addFlowElement(createSequenceFlow("createExclusiveGateway1", "task1", "不通過", "${pass==’2′}"));
        process.addFlowElement(createSequenceFlow("createExclusiveGateway1", "task3", "通過", "${pass==’1′}"));
        process.addFlowElement(createSequenceFlow("task3", "createExclusiveGateway2", "", ""));
        process.addFlowElement(createSequenceFlow("createExclusiveGateway2", "task2", "不通過", "${pass==’2′}"));
        process.addFlowElement(createSequenceFlow("createExclusiveGateway2", "task4", "通過", "${pass==’1′}"));
        process.addFlowElement(createSequenceFlow("task4", "createExclusiveGateway3", "", ""));
        process.addFlowElement(createSequenceFlow("createExclusiveGateway3", "task3", "不通過", "${pass==’2′}"));
        process.addFlowElement(createSequenceFlow("createExclusiveGateway3", "endEvent", "通過", "${pass==’1′}"));

        // 2. Generate graphical information
        new BpmnAutoLayout(model).execute();

        // 3. Deploy the process to the engine
        Deployment deployment = repositoryService.createDeployment().addBpmnModel("deployments.bpmn", model).name(PROCESSID+ "_deployment").deploy();

        // 4. Start a process instance
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESSID);

        // 5. Check if task is available
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
        Assert.assertEquals(1, tasks.size());

        // 6. Save process diagram to a file
        InputStream processDiagram = repositoryService.getProcessDiagram(processInstance.getProcessDefinitionId());
        FileUtils.copyInputStreamToFile(processDiagram, new File("d:/deployments.png"));

        // 7. Save resulting BPMN xml to a file
        InputStream processBpmn = repositoryService.getResourceAsStream(deployment.getId(), PROCESSID+ ".bpmn");
        FileUtils.copyInputStreamToFile(processBpmn,new File("d:/deployments.bpmn"));

        System.out.println("………end…");
    }


    @Test
    public void test4(){
        try {
            List<String> contents = Files.readLines(new File("d:/a.bpmn"), Charsets.UTF_8);
            StringBuffer sb = new StringBuffer();
            for(String content : contents){
                sb.append(content);
            }
            byte[] bytes = sb.toString().getBytes();
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            XMLInputFactory xif = XmlUtil.createSafeXmlInputFactory();
            XMLStreamReader xtr = xif.createXMLStreamReader(bis, "UTF-8");
            BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
            BpmnModel bpmnModel = xmlConverter.convertToBpmnModel(xtr);
            Map<String,GraphicInfo> locationMap = bpmnModel.getLocationMap();
            for(String key : locationMap.keySet()){
                GraphicInfo graphicInfo = locationMap.get(key);
                System.out.println("key:"+key+",X:"+graphicInfo.getX()+",Y:"+graphicInfo.getY());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() throws IOException {
        String processId = "dynamic";
        String processName = "动态创建流程";
        String processDesc = "测试动态创建流程";

        String datas = "[{\"taskKey\":\"storekeeper\",\"taskName\":\"待仓库人员提交\",\"roles\":\"管理员,仓库人员\",\"returnTaskKey\":\"\",\"messagePass\":\"\",\"messageNoPass\":\"由{0}对计划单{1}进行审核，{2}审核不通过，请您及时核对并修正重新提交，谢谢！\",\"buttonName\":\"\",\"buttonIcon\":\"\",\"permission\":\"\",\"variable\":\"\",\"pcUrl\":\"\",\"appUrl\":\"\",\"remark\":\"医院计划医院仓库人员\"},{\"taskKey\":\"hospitalPurchaseBuyer\",\"taskName\":\"待医院采购审核\",\"roles\":\"管理员,采购主管,采购专员\",\"returnTaskKey\":\"storekeeper\",\"messagePass\":\"您有一笔计划单由{0}提交，计划单号为{1},需要您分析商品并采购，请及时处理，谢谢！\",\"messageNoPass\":\"由{0}对计划单{1}进行审核，{2}审核不通过，请您及时核对并修正重新提交，谢谢！\",\"buttonName\":\"采购审核\",\"buttonIcon\":\"fa-print\",\"permission\":\"buyer:buyPlan:buyerAudit\",\"variable\":\"hospitalPurchaseBuyerApprove\",\"pcUrl\":\"buy_plan_confirm_n.jsp?buy_plan_id={buyPlanId}\",\"appUrl\":\"handle-model-5\",\"remark\":\"医院计划医院采购\"},{\"taskKey\":\"hospitalFinancial\",\"taskName\":\"待医院财务审核\",\"roles\":\"管理员,财务主管,财务专员\",\"returnTaskKey\":\"hospitalPurchaseBuyer\",\"messagePass\":\"您有一笔计划单由{0}提交，计划单号为{1},需要您审核下，请及时处理，谢谢！\",\"messageNoPass\":\"\",\"buttonName\":\"财务审核\",\"buttonIcon\":\"fa-print\",\"permission\":\"buyer:buyPlan:financeAudit\",\"variable\":\"hospitalFinancialApprove\",\"pcUrl\":\"buy_plan_audit.jsp?action=buyerAudit&buy_plan_id={buyPlanId}\",\"appUrl\":\"auditForAll\",\"remark\":\"医院计划医院财务\"},{\"taskKey\":\"hospitalLeaderBuyer\",\"taskName\":\"待医院领导审核\",\"roles\":\"管理员,院领导\",\"returnTaskKey\":\"hospitalPurchaseBuyer\",\"messagePass\":\"您有一笔计划单由{0}提交，计划单号为{1},需要您审核下，请及时处理，谢谢！\",\"messageNoPass\":\"\",\"buttonName\":\"院领导审核\",\"buttonIcon\":\"fa-print\",\"permission\":\"buyer:buyPlan:leaderAudit\",\"variable\":\"hospitalLeaderBuyerApprove\",\"pcUrl\":\"buy_plan_audit.jsp?action=buyerAudit&buy_plan_id={buyPlanId}\",\"appUrl\":\"auditForAll\",\"remark\":\"医院计划医院领导\"},{\"taskKey\":\"hospitalPurchaseOrder\",\"taskName\":\"待生成订单\",\"roles\":\"管理员,采购主管,采购专员\",\"returnTaskKey\":\"\",\"messagePass\":\"您有一笔计划单由{0}审核通过，计划单号为{1}，需要采购下单，请及时处理，谢谢！\",\"messageNoPass\":\"\",\"buttonName\":\"生成订单\",\"buttonIcon\":\"fa-gavel\",\"permission\":\"buyer:buyPlan:toOrder\",\"variable\":\"\",\"pcUrl\":\"plan_to_order.jsp?buyPlanId={buyPlanId}\",\"appUrl\":\"hospitalCreateOrder\",\"remark\":\"医院计划医院采购下单\"}]";
        if (org.apache.commons.lang.StringUtils.isNotBlank(datas)) {
            JSONArray stepDatas = JSONArray.parseArray(datas);
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
            startEvent.setXmlColumnNumber(650);
            process.addFlowElement(startEvent);
            flowElements.add(startEvent);

            //3.生成流程基础配置信息
            createBase(stepDatas, process, flowElements);

            //结束流程
            EndEvent endEvent = createEndEvent();
            process.addFlowElement(endEvent);
            flowElements.add(endEvent);
            //4.连线
            createSequence(flowElements, process, startEvent);

            bpmnModel.addProcess(process);
            //5.流程图自动布局（位于activiti-bpmn-layout模块）

            new BpmnAutoLayout(bpmnModel).execute();

            //部署
            Deployment deployment = repositoryService.createDeployment().addBpmnModel("process.bpmn",bpmnModel)
                    .name(process.getName()).deploy();

            //启动流程
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(process.getId());

            //导出流程图
            InputStream inputStream = repositoryService.getProcessDiagram(processInstance.getProcessDefinitionId());
            FileUtils.copyInputStreamToFile(inputStream,new File("d:/process.png"));
        }
    }

    //基础信息赋值
    private void createBase(JSONArray stepDatas, Process process, List<FlowElement> flowElements) {
        int[] columns = {727,839,1112,1249,1656};
        for (int i = 0; i < stepDatas.size(); i++) {
            JSONObject step = stepDatas.getJSONObject(i);
            String taskKey = step.getString("taskKey");
            String taskName = step.getString("taskName");
            String roles = step.getString("roles");
            String returnTaskKey = step.getString("returnTaskKey");
            String variable = step.getString("variable");
            if(StringUtils.isBlank(taskKey) || StringUtils.isBlank(taskName) || StringUtils.isBlank(roles)){
                Preconditions.checkState(false,"节点信息配置异常");
            }
            UserTask userTask = createGroupTask(taskKey, taskName, roles);
            userTask.setXmlColumnNumber(columns[i]);
            process.addFlowElement(userTask);
            flowElements.add(userTask);
            if (org.apache.commons.lang.StringUtils.isNotBlank(returnTaskKey)) {
                ExclusiveGateway exclusiveGateway = createExclusiveGateWay(variable, returnTaskKey);
                process.addFlowElement(exclusiveGateway);
                flowElements.add(exclusiveGateway);
            }
        }
    }

    //生成所有连线
    private void createSequence(List<FlowElement> flowElements, Process process, StartEvent startEvent) {
        List<SequenceFlow> sequenceFlows = new ArrayList<>();
        List<SequenceFlow> exFlows = new ArrayList<>();
        FlowElement from = null;
        FlowElement to = null;
        SequenceFlow sequenceFlow = null;
        int size = flowElements.size();
        int columnIndex = 1400;
        for (int i = 0; i < size; i++) {

            if (i == size - 1) {
                continue;
            }
            sequenceFlows.clear();
            from = flowElements.get(i);
            to = flowElements.get(i + 1);
            if (from instanceof ExclusiveGateway) {
                exFlows.clear();
                ExclusiveGateway fromGateway = (ExclusiveGateway) from;
                //通过
                sequenceFlow = createSequenceFlow(from.getId(), to.getId(), "通过", "${" + fromGateway.getId() + "=='true'}");
                process.addFlowElement(sequenceFlow);
                sequenceFlows.add(sequenceFlow);
                exFlows.add(sequenceFlow);
//                if (to instanceof UserTask) {
//                    ((UserTask) to).setIncomingFlows(sequenceFlows);
//                }
                //不通过
                for (FlowElement flowElement : flowElements) {
                    if (flowElement instanceof UserTask) {
                        UserTask returnTask = (UserTask) flowElement;
                        if (fromGateway.getName().equals(returnTask.getId())) {
                            columnIndex +=100;
                            sequenceFlow = createSequenceFlow(from.getId(), returnTask.getId(), "不通过", "${" + fromGateway.getId() + "=='false'}");
                            sequenceFlow.setXmlColumnNumber(columnIndex);
                            process.addFlowElement(sequenceFlow);
                            exFlows.add(sequenceFlow);
//                            fromGateway.setOutgoingFlows(exFlows);
                            List<SequenceFlow> returnFlows = returnTask.getIncomingFlows();
                            returnFlows.add(sequenceFlow);
//                            returnTask.setIncomingFlows(returnFlows);
                            break;
                        }
                    }
                }
            } else {
                sequenceFlow = createSequenceFlow(from.getId(), to.getId(), "", "");
                process.addFlowElement(sequenceFlow);
                sequenceFlows.add(sequenceFlow);
//                if (from instanceof StartEvent) {
//                    startEvent.setOutgoingFlows(sequenceFlows);
//                }
//                if (from instanceof UserTask) {
//                    ((UserTask) from).setIncomingFlows(sequenceFlows);
//                }
//                if (to instanceof UserTask) {
//                    ((UserTask) to).setIncomingFlows(sequenceFlows);
//                }
//                if (to instanceof ExclusiveGateway) {
//                    ((ExclusiveGateway) to).setIncomingFlows(sequenceFlows);
//                }
            }
        }
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
