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
                    <form role="form" method="post" action="/menu/addMenu">
                        <div class="form-group">
                            <label>菜品名称</label>
                            <input name="dishName" type="text" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label>单价</label>
                            <input name="dishPrice" type="text" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label>图片</label>
                            <input name="dishImage" type="text" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>菜品类目</label>
                            <select name="dishType">
                            <#list TypeList as types>
                                    <option value="${(types.id)}">${(types.dishType)}</option>
                            </#list>
                            </select>
                        </div>
                        <input hidden type="text" name="shopId" value="${shopId}">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>