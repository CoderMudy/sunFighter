var editObj=null,ptable=null,treeGrid=null,tableId='treeTable',layer=null;
layui.config({
    base: '/plugins/layui/extends/'
}).extend({
    treeGrid:'treeGrid'
}).use(['jquery','treeGrid','layer'], function(){
    var $=layui.jquery;
    treeGrid = layui.treeGrid;//很重要
    layer=layui.layer;
    ptable=treeGrid.render({
        id:tableId
        ,elem: '#'+tableId
        ,idField:'id'
        ,url:'/json/treeData.json'
        ,cellMinWidth: 100
        ,treeId:'id'//树形id字段名称
        ,treeUpId:'pId'//树形父id字段名称
        ,treeShowName:'name'//以树形式显示的字段
        ,height:'200'
        ,page:false
        ,cols: [[
            {type:'numbers'}
            ,{type:'checkbox'}
            ,{width:100,title: '操作', align:'center'/*toolbar: '#barDemo'*/
                ,templet: function(d){
                    var html='';
                    var addBtn='<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="add">添加</a>';
                    var delBtn='<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>';
                    return addBtn+delBtn;
                }
            }
            ,{field:'name', edit:'text',width:300, title: '水果名称'}
            ,{field:'id',width:100, title: 'id'}
            ,{field:'pId', title: 'pid'}
        ]]

    });

    treeGrid.on('tool('+tableId+')',function (obj) {
        if(obj.event === 'del'){//删除行
            del(obj);
        }else if(obj.event==="add"){//添加行
            add(obj);
        }
    });
})