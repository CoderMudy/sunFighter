layui.use(['element', 'layer', 'form', 'upload', 'treeGrid'], function () {
    var treeGrid = layui.treeGrid; //很重要
    var treeTable =treeGrid.render({
        elem: '#treeTable'
        ,url:'json/data1.json'
        ,height: 'full-20'
        ,cellMinWidth: 100
        ,treeId:'sd_id'//树形id字段名称
        ,treeUpId:'sd_up_id'//树形父id字段名称
        ,treeShowName:'sd_name'//以树形式显示的字段
        ,cols: [[
            {type:'checkbox'}
            ,{field:'sd_name', edit:'text',width:'400', title: '水果'}
            ,{field:'sd_id', edit:'text',width:'300', title: 'id'}
            ,{field:'sd_up_id', edit:'text',width:'300', title: 'pId'}
        ]]
        ,page:true
    });
});