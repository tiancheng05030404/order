<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav-fu.ftl">

    <#--主要内容content-->
    <div class="check-div form-inline" >

        <div style="display: inline-block">
            <button class="btn btn-yellow btn-xs" data-toggle="modal" data-target="#addlouceng">添加楼层 </button>
        </div>

        <div style="display: inline-block">
            <button class="btn btn-yellow btn-xs" data-toggle="modal" data-target="#addquyu">添加区域 </button>
        </div>

        <div style="display: inline-block">
            <button class="btn btn-yellow btn-xs" data-toggle="modal" data-target="#addguige">规格 </button>
        </div>

        <div style="display: inline-block">
            <button class="btn btn-yellow btn-xs" data-toggle="modal" data-target="#addBuilding?ashdjash=categoryList">添加座位 </button>
        </div>

    </div>
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>楼层</th>
                            <th>区域</th>
                            <th>桌号</th>
                            <th>规格</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list categoryList as category>
                            <tr>
                                <td>${category.categoryId}</td>
                                <td>${category.categoryId}</td>
                                <td>${category.categoryName}</td>
                                <td>${category.categoryId}</td>
                                <td>${category.categoryName}</td>
                                <td><a href="?categoryId=${category.categoryId}">修改</a></td>
                                <td><a href="?categoryId=${category.categoryId}">删除</a></td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="addlouceng" role="dialog" aria-labelledby="gridSystemModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">添加楼层</h4>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <form class="form-horizontal" method="get" action="/table/addFloor" >
                            <div class="form-group ">
                                <label for="sName" class="col-xs-3 control-label">名称</label>
                                <div class="col-xs-8 ">
                                    <input type="email"  name="floorld" >
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-xs btn-white" data-dismiss="modal">取 消</button>
                    <button type="button" class="btn btn-xs btn-green">保 存</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="addquyu" role="dialog" aria-labelledby="gridSystemModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" >添加区域</h4>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">

                        <form class="form-horizontal"  method="get" action="/table/area/add">
                            <label for="sName" >楼层：</label>
                            <select >
                                <option value="" name="floorld">1</option>

                            </select>

                            <div class="form-group" >
                                <label for="sName" class="col-xs-3 control-label">区域：</label>
                                <div class="col-xs-8 ">
                                    <input type="email" class="form-control input-sm duiqi" name="area">
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-xs btn-white" data-dismiss="modal">取 消</button>
                    <button type="button" class="btn btn-xs btn-green">保存</button>
                </div>
            </div>
        </div>
    </div>

    <!--                添加规格-->

    <div class="modal fade" id="addguige" role="dialog" aria-labelledby="gridSystemModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" >添加规格</h4>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">

                        <form class="form-horizontal" action="/table/specification/add">
                            <div class="form-group ">
                                <label for="sName" class="col-xs-3 control-label">规格</label>
                                <div class="col-xs-8 ">
                                    <input type="email" class="form-control input-sm duiqi" id="GGgame" placeholder="">
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-xs btn-white" data-dismiss="modal">取 消</button>
                    <button type="button" class="btn btn-xs btn-green">保 存</button>
                </div>
            </div>
        </div>
    </div>



    <!--弹出添加信息-->
    <div class="modal fade" id="addBuilding" role="dialog" aria-labelledby="gridSystemModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <!--                              添加信息id-->
                    <h4 class="modal-title" id="grid">添加信息</h4>
                </div>


                <div class="modal-body">
                    <div class="container-fluid">
                        <form class="form-horizontal" action="/table/add" method="get">
                            <div class="form-group ">
                                <label for="sName" >楼层：</label>
                                <#list orderDTOPage.content as orderDTO>
                                    <select  name="floorld">
                                        <option value=""> ? </option>
                                    </select>
                                </#list>
                                <br>
                                <label for="scho" >区域：</label>
                                <#list orderDTOPage.content as orderDTO>
                                <select id="">
                                    <option value=""> ? </option>
                                </select>
                                </#list>
                                <br>
                            </div>
                            <div class="form-group">
                                <label for="sOrd" class="col-xs-3 control-label">桌名：</label>
                                <div class="col-xs-8">
                                    <input type="" class="form-control input-sm duiqi" name="tableName">
                                </div>
                            </div>
                            <div class="form-group ">
                                <label for="sName" class="col-xs-3 control-label">规格：</label>
                                <div class="col-xs-8 ">
                                    <input type="email" class="form-control input-sm duiqi"name="specification">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-xs btn-white" data-dismiss="modal">取 消</button>
                    <button type="button" class="btn btn-xs btn-green">保 存</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>

</div>
</body>
</html>