<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>

        <style>
            .error {
               color: red;
            }
        </style>
            <strong style="font-size: 18px;">账户登录</strong>
            <div class="input">
                <span class="glyphicon glyphicon-user"></span> <input type="text"
                    id="name" name="name" placeholder="手机/会员名/邮箱" autocomplete="off" />
            </div>
            <div class="input">
                <span class="glyphicon glyphicon-lock"></span> <input
                    type="password" id="password" name="password" placeholder="密码" />
            </div>
            <div style="height: 30px;">
                <div class="forgetPWD">
                    <a href="#1">忘记登录密码</a>
                </div>
                <div class="freeRegist">
                    <a href="#1">免费注册</a>
                </div>

            </div>
            <button id="loginBtn" class="login-btn">登录</button>
        
        
        <script src="assets/js/jquery.validate.min.js"></script>
        <script type="text/javascript">
            jQuery(function($) {
                $('#loginForm').validate({
                    rules : {
                        name : {
                            required : true,
                            //rangelength: [4-16],
                            remote : {
                                url : "../nameValidate",
                                type : "POST",
                                data : {
                                    name : function() {
                                        return $('#name').val();
                                    }
                                }
                            }
                        },
                        password : {
                            required : true,
                            //rangelength: [8-16],
                            remote : {
                                url : "../passwordValidate",
                                type : "POST",
                                data : {
                                    name : function() {
                                        return $('#name').val();
                                    },
                                    password : function() {
                                        return $('#password').val();
                                    }
                                }
                            }
                        }
                    },
                    messages : {
                        name : {
                            required : "账户名不能为空",
                            //rangelength: "账户名为4到16个字",
                            remote : "账户名不存在"
                        },
                        password : {
                            required : "密码不能为空",
                            //rangelength: "密码为8到16个字",
                            remote : "密码或账户名错误"
                        }
                    },
                    onkeyup : false,
                    onfocusout : false
                })

                $("#loginBtn").click(function() {
                    if ($('#loginForm').validate()) {
                        $('#loginForm').submit();
                    }
                });

                $("#loginForm input").keyup(function(e) {
                    var code = e.charCode || e.keyCode;
                    if (code == 13 && $('#loginForm').validate()) {
                        $('#loginForm').submit();
                    }
                });

                $('#name').blur(function() {
                    $('#loginForm').validate().element($('#name'))
                });
                $('#name').keyup(function() {
                    $('#loginForm').validate().element($('#name'))
                });
            });
        </script>
    