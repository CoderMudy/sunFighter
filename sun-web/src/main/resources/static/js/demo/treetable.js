layui.config({
    base: 'plugins/'
}).extend({
    treetable: 'treetable/treetable'
}).use(['layer', 'table', 'treetable'], function () {
    var $ = layui.jquery;
    var table = layui.table;
    var layer = layui.layer;
    var treetable = layui.treetable;

    // 渲染表格
    var renderTable = function () {
        layer.load(2);
        treetable.render({
            treeColIndex: 1,
            treeSpid: -1,
            treeIdName: 'id',
            treePidName: 'pid',
            treeDefaultClose: true,
            treeLinkage: false,
            elem: '#table1',
            url: 'json/data.json',
            page: true,
            cols: [[
                {type: 'numbers'},
                {field: 'name', title: 'name'},
                {field: 'id', title: 'id'},
                {field: 'sex', title: 'sex'},
                {field: 'pid', title: 'pid'},
                {templet: '#oper-col', title: 'oper'}
            ]],
            done: function () {
                layer.closeAll('loading');
            }
        });
    };

    renderTable();

    $('#btn-expand').click(function () {
        treetable.expandAll('#table1');
    });

    $('#btn-fold').click(function () {
        treetable.foldAll('#table1');
    });

    $('#btn-refresh').click(function () {
        renderTable();
    });

    //监听工具条
    table.on('tool(table1)', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'del') {
            layer.msg('删除' + data.id);
        } else if (layEvent === 'edit') {
            layer.msg('修改' + data.id);
        }
    });
});