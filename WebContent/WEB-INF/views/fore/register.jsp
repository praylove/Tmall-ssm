<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ include file="../include/fore/header.jsp"%>

<title>天猫tmall.com--注册</title>

<%@ include file="../include/fore/navigator.jsp"%>
<%@ include file="../include/fore/search.jsp"%>
<%@ include file="../include/fore/separator.jsp"%>

<div class="g-register">
	<form id="registerForm" method="post" class="form-horizontal">
		<fieldset>
			<div class="form-group">
				<label for="name" class="col-sm-4 control-label">登陆名</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="name" name="name"
						autocomplete="off" placeholder="会员名一旦设置成功，无法修改" />
				</div>
			</div>
			<div class="form-group">
				<label for="password1" class="col-sm-4 control-label">登陆密码</label>
				<div class="col-sm-4">
					<input type="password" class="form-control" id="password1" name="password1"
						placeholder="设置你的登陆密码" />
				</div>
			</div>
			<div class="form-group">
				<label for="password2" class="col-sm-4 control-label">密码确认</label>
				<div class="col-sm-4">
					<input type="password" class="form-control" id="password2" name="password2"
						placeholder="请再次输入你的密码" />
				</div>
			</div>
			<div class="space-4"></div>
		</fieldset>
		<div class="col-sm-6 control-label">
                        <button id="register-btn" class="btn btn-danger">
                            注册
                        </button>
                    </div>
	</form>
</div>

<script src="assets/js/jquery.validate.min.js"></script>
<script src="js/jquery/jquery.form.min.js"></script>
<script type="text/javascript">
	jQuery(function($){
		
		$("#register-btn").click(function(e){
			e.preventDefault();
			if($('#registerForm').validate().form()){
				$('#registerForm').ajaxSubmit({
                    url: '../users',
                    type: 'post',
                    success: function(data) {
                        if(data === "success"){
                        	$(location).attr('href', '../login');
                        }
                    },
                    clearForm: true
                });
			}
        });
		
		$.validator.addMethod("nameValidate", function(value, element, params){
			var name = /^\w{2,10}$/;
			return this.optional(element) || (name.test(value));
		});
		
		$.validator.addMethod("pwdValidate", function(value, element, params){
            var name = /^\w{6,16}$/;
            return this.optional(element) || (name.test(value));
        });
		
		$('#registerForm').validate({
            rules: {
                name: {
                    required: true,
                    nameValidate: true,
                    remote: {
                        url: "../nameExistValidate",
                        type: "POST",
                        data: {
                            name: function(){
                                return $('#name').val();
                            }
                        }
                    }
                }, password1: {
                    required: true,
                    pwdValidate: true
                }, password2: {
                    required: true,
                    equalTo: password1
                }
            }, messages: {
                name: {
                    required: "*账户名不能为空",
                    nameValidate: "*只允许英文字母、数字和下画线，长度为2-10位",
                    remote: "*账户名已存在"
                }, password1: {
                    required: "*密码不能为空",
                    pwdValidate: "*只允许6-16位英文字母、数字和下画线"
                }, password2: {
                    required: "*密码不能为空",
                    equalTo: "*两次输入的密码不一致"
                }
            }, 
                onkeyup: false,
                onfocusout: false,
                errorClass: "m-error-class",
                errorElement: "div",
        });
	});
</script>

<%@ include file="../include/fore/separator.jsp"%>
<%@ include file="../include/fore/footer.jsp"%>