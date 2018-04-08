<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<body class="no-skin">
        <div id="navbar" class="navbar navbar-default navbar-collapse  h-navbar ace-save-state">
            <div class="navbar-container ace-save-state container" id="navbar-container">
                <div class="navbar-header pull-left">
                    <a href="../admin/" class="navbar-brand">
                        <small>
                            <i class="fa fa-leaf"></i>
                            Tmall Admin
                        </small>
                    </a>

                </div>

                <div class="navbar-buttons navbar-header pull-right  collapse navbar-collapse" role="navigation">
                    <ul class="nav ace-nav">

                        <li class="light-blue dropdown-modal">
                            <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                                <img class="nav-user-photo" src="assets/images/avatars/user.jpg" alt="Jason's Photo">
                                <span class="user-info">
                                    <small>欢迎,</small>
                                    Jason
                                </span>

                                <i class="ace-icon fa fa-caret-down"></i>
                            </a>

                            <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                                <li>
                                    <a href="#">
                                        <i class="ace-icon fa fa-cog"></i> Settings
                                    </a>
                                </li>

                                <li>
                                    <a href="profile.html">
                                        <i class="ace-icon fa fa-user"></i> Profile
                                    </a>
                                </li>

                                <li class="divider"></li>

                                <li>
                                    <a href="#">
                                        <i class="ace-icon fa fa-power-off"></i> Logout
                                    </a>
                                </li>
                            </ul>
                        </li>

                    </ul>
                </div>

                <nav role="navigation" class="navbar-menu pull-left collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="../admin/category">
                                <!--<i class="ace-icon fa fa-envelope"></i>-->
                                                                      分类管理
                            </a>
                        </li>
                        <li>
                            <a href="../admin/user">
                                                                        用户管理
                            </a>
                        </li>
                        <li>
                            <a href="../admin/order">
                                                                        订单管理
                            </a>
                        </li>
                    </ul>

                </nav>
            </div>
            <!-- /.navbar-container -->
        </div>
        <br />
        <div class="main-container ace-save-state container" id="main-container">