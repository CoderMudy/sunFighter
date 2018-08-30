layui.use('table', function(){
    var table = layui.table;

    table.render({
        elem: '#userList'
        ,url:'/json/userList.json'
        ,height: 'full-70'
        ,page: true
        ,toolbar: true
        , skin: 'row'
        , even: true
        ,defaultToolbar: ['filter']
        ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        ,cols: [[
            {checkbox: true, fixed: true}
            ,{field:'id', width:80, title: 'ID', sort: true}
            ,{field:'username', width:80, title: '用户名'}
            ,{field:'sex', width:80, title: '性别', sort: true}
            ,{field:'city', width:80, title: '城市'}
            ,{field:'sign', title: '签名', width: '30%', minWidth: 100} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
            ,{field:'experience', title: '积分', sort: true}
            ,{field:'score', title: '评分', sort: true}
            ,{field:'classify', title: '职业'}
            ,{field:'wealth', width:137, title: '财富', sort: true}
            ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
        ]]
    });

    //监听行工具事件
    table.on('tool(userList)', function(obj){
        var data = obj.data;
        //console.log(obj)
        if(obj.event === 'del'){
            layer.confirm('真的删除行么', function(index){
                obj.del();
                layer.close(index);
            });
        } else if(obj.event === 'edit'){
            layer.prompt({
                formType: 2
                ,value: data.email
            }, function(value, index){
                obj.update({
                    email: value
                });
                layer.close(index);
            });
        }
    });
});