<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta charset="utf-8" />
        <title>Login Page - Tmall Admin</title>
        <base href="http://localhost:8080/tmall_ssm/resources/">
        <meta name="description" content="User login page" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

        <!-- bootstrap & fontawesome -->
        <link rel="stylesheet" href="assets/css/bootstrap.min.css" />
        <link rel="stylesheet" href="assets/font-awesome/4.5.0/css/font-awesome.min.css" />

        <!-- text fonts -->
        <link rel="stylesheet" href="assets/css/fonts.googleapis.com.css" />

        <!-- ace styles -->
        <link rel="stylesheet" href="assets/css/ace.min.css" />

        <!--[if lte IE 9]>
            <link rel="stylesheet" href="assets/css/ace-part2.min.css" />
        <![endif]-->
        <link rel="stylesheet" href="assets/css/ace-rtl.min.css" />

        <!--[if lte IE 9]>
          <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
        <![endif]-->

        <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

        <!--[if lte IE 8]>
        <script src="assets/js/html5shiv.min.js"></script>
        <script src="assets/js/respond.min.js"></script>
        <![endif]-->
        
        <style type="text/css">
            label.error {
                color: red;
                font-size: 12px;
            }
        </style>
    </head>

    <body class="login-layout">
        <div class="main-container">
            <div class="main-content">
                <div class="row">
                    <div class="col-sm-10 col-sm-offset-1">
                        <div class="login-container">
                            <div class="center">
                                <h1>
                                    <i class="ace-icon fa fa-leaf green"></i>
                                    <span class="red">Tmall</span>
                                    <span class="white" id="id-text2">Admin</span>
                                </h1>
                                <h4 class="blue" id="id-company-text">&copy; x_sherl</h4>
                            </div>

                            <div class="space-6"></div>

                            <div class="position-relative">
                                <div id="login-box" class="login-box visible widget-box no-border">
                                    <div class="widget-body">
                                        <div class="widget-main">
                                            <h4 class="header blue lighter bigger">
                                                <i class="ace-icon fa fa-coffee green"></i>
                                                请输入你的信息
                                            </h4>

                                            <div class="space-6"></div>

                                            <form id="loginForm" action="../admin/formLogin.do" method="post">
                                                <fieldset>
                                                    <label class="block clearfix">
                                                        <span class="block input-icon input-icon-right">
                                                            <input type="text" id="name" name="name" class="form-control" placeholder="账户名" autocomplete="off"/>
                                                            <i class="ace-icon fa fa-user"></i>
                                                        </span>
                                                    </label>

                                                    <label class="block clearfix">
                                                        <span class="block input-icon input-icon-right">
                                                            <input type="password" id="password" name="password" class="form-control" placeholder="密码" autocomplete="off"/>
                                                            <i class="ace-icon fa fa-lock"></i>
                                                        </span>
                                                    </label>

                                                    <div class="space"></div>

                                                    <div class="clearfix">
                                                        <label class="inline">
                                                            <input type="checkbox" class="ace" id="remember-me" name="remember-me"/>
                                                            <span class="lbl">记住我</span>
                                                        </label>

                                                        <button id="loginBtn" type="button" class="width-35 pull-right btn btn-sm btn-primary">
                                                            <i class="ace-icon fa fa-key"></i>
                                                            <span class="bigger-110">登录</span>
                                                        </button>
                                                    </div>

                                                    <div class="space-4"></div>
                                                </fieldset>
                                            </form>

                                          
                                        </div><!-- /.widget-main -->

                                    </div><!-- /.widget-body -->
                                </div><!-- /.login-box -->

                            </div><!-- /.position-relative -->
                        </div>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.main-content -->
        </div><!-- /.main-container -->

        <!-- basic scripts -->

        <!--[if !IE]> -->
        <script src="assets/js/jquery-2.1.4.min.js"></script>
        
        <!-- <![endif]-->

        <!--[if IE]>
<script src="assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
        <script type="text/javascript">
            if('ontouchstart' in document.documentElement) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
        </script>
        
        <script src="assets/js/jquery.validate.min.js"></script>
        
        <script type="text/javascript">
            $(function(){
            	
            	$('#loginForm').validate({
                    rules: {
                        name: {
                            required: true,
                            //rangelength: [4-16],
                            remote: {
                                url: "../nameValidate",
                                type: "POST",
                                data: {
                                    name: function(){
                                        return $('#name').val();
                                    }
                                }
                            }
                        }, password: {
                            required: true,
                            //rangelength: [8-16],
                            remote: {
                                url: "../passwordValidate",
                                type: "POST",
                                data: {
                                    name: function(){
                                        return $('#name').val();
                                    }, password: function(){
                                        return $('#password').val();
                                    }
                                }
                            }
                        }
                    }, messages: {
                        name: {
                            required: "账户名不能为空",
                            //rangelength: "账户名为4到16个字",
                            remote: "账户名不存在"
                        }, password: {
                            required: "密码不能为空",
                            //rangelength: "密码为8到16个字",
                            remote: "密码或账户名错误"
                        }
                    }, 
                    onkeyup: false,
                    onfocusout: false
                })
            	
            	$("#loginBtn").click(function(){
            		if ($('#loginForm').validate()){
            			$('#loginForm').submit();
            		}
            	});
            	
            	$("#loginForm input").keyup(function(e){
            		var code = e.charCode || e.keyCode;
            		if (code == 13 && $('#loginForm').validate()){
                       $('#loginForm').submit();
            		}
            	});
            	
            	$('#name').blur(function(){
            		$('#loginForm').validate().element($('#name'))
            	});
            	$('#name').keyup(function(){
                    $('#loginForm').validate().element($('#name'))
                });
            	
            });
        
        </script>
    </body>
</html>
    