layui.config({
    base: 'plugins/'
}).extend({
    treetable: 'treetable/treetable',
    iconPicker: 'iconPicker/iconPicker'
}).use(['table', 'treetable','iconPicker'], function () {
    var $ = layui.jquery;
    var table = layui.table;
    var treetable = layui.treetable;
    var iconPicker = layui.iconPicker;

    iconPicker.render({
        // 选择器，推荐使用input
        elem: '#iconPicker',
        // 数据类型：fontClass/unicode，推荐使用fontClass
        type: 'fontClass',
        // 是否开启搜索：true/false
        search: true,
        // 点击回调
        click: function (data) {
            console.log(data);
        }
    });

    /**
     * 选中图标 （常用于更新时默认选中图标）
     * @param filter lay-filter
     * @param iconName 图标名称，自动识别fontClass/unicode
     */
    iconPicker.checkIcon('iconPicker', 'layui-icon-star-fill');

    // 渲染表格
    layer.load(2);
    treetable.render({
        treeColIndex: 1,
        treeSpid: 0,
        treeIdName: 'menuId',
        treePidName: 'parentId',
        elem: '#menuTable',
        height: 'full-60',
        url: '/sysMenu/list',
        page: false,
        cols: [[
            {type: 'numbers'},
            {field: 'name', minWidth: 200, title: '菜单名称'},
            {
                field: 'isMenu', width: 80, align: 'center', templet: function (d) {
                    if (d.type== 0) {
                        return '<span class="layui-badge layui-bg-blue">目录</span>';
                    }
                    if (d.type== 1) {
                        return '<span class="layui-badge layui-bg-green">菜单</span>';
                    }
                    if (d.type== 2) {
                        return '<span class="layui-badge layui-bg-orange">按钮</span>';
                    }
                }, title: '类型'
            },
            {field: 'icon', width: 80 , align: 'center', title: '图标',templet:function (rowData) {
                    return '<i class="layui-icon '+rowData.icon+'"></i> ';
                }},
            {field: 'url', title: '菜单url'},
            {field: 'perms', title: '权限标识'},
            {field: 'orderNum', width: 80, align: 'center', title: '排序号'},
            {templet: '#menuOperator', width: 120, align: 'center', title: '操作'}
        ]],
        done: function () {
            layer.closeAll('loading');
        }
    });

    $('#btn-expand').click(function () {
        treetable.expandAll('#auth-table');
    });

    $('#btn-fold').click(function () {
        treetable.foldAll('#auth-table');
    });
});