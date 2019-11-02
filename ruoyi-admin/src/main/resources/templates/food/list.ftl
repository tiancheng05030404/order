<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav-fu.ftl">

    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <tr>
                            <td>
                                <#list dishTypeList as dishType>
                                    <a href="/menu/type?shopId=${shopId}&typeId=${dishType.id}">${dishType.dishType}</a>
                                </#list>
                            </td>
                        </tr>
                    </table>
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>食品名称</th>
                            <th>单价</th>
                            <th>图片</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list menus as menu>
                        <tr>
                            <td>${menu.dishName}</td>
                            <td>${menu.dishPrice}</td>
                            <td><img src="${menu.dishImage}"></td>
                            <td><a href="/menu/goUpdate?dishId=${menu.id}&shopId=${shopId}">修改</a></td>
                            <td><a href="/menu/delete?dishId=${menu.id}&shopId=${shopId}">删除</a></td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>