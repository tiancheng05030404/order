<nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
    <ul class="nav sidebar-nav">
        <li class="sidebar-brand">
            <a href="#">
                服务员餐厅管理系统
            </a>
        </li>
        <li>
            <a href="/order/list?shopId=${shopId}"><i class="fa fa-fw fa-list-alt"></i>订单</a>
        </li>
        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                <i class="fa fa-fw fa-plus"></i> 菜品管理 <span class="caret"></span>
            </a>
            <ul class="dropdown-menu" role="menu">
                <li><a href="/menu/list?shopId=${shopId}">菜品列表</a></li>
                <li><a href="/menu/findDishType?shopId=${shopId}">新增菜品</a></li>
            </ul>
        </li>
        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                <i class="fa fa-fw fa-plus"></i> 菜品类目管理 <span class="caret"></span>
            </a>
            <ul class="dropdown-menu" role="menu">
                <li><a href="${shopId}">菜品类目列表</a></li>
                <li><a href="${shopId}">新增菜品类目</a></li>
            </ul>
        </li>

        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                <i class="fa fa-fw fa-plus"></i> 店铺管理 <span class="caret"></span>
            </a>
            <ul class="dropdown-menu" role="menu">
                <li><a href="${shopId}">店铺信息</a></li>
                <li><a href="${shopId}">座位管理</a></li>
            </ul>
        </li>
    </ul>
</nav>