<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ include file="../include/fore/header.jsp"%>

<title>天猫tmall.com--登录</title>

<%@ include file="../include/fore/navigator.jsp"%>
<%@ include file="../include/fore/separator.jsp"%>


<div class="g-mid">
	<a href="/tmall/" style="margin: 20px;"><img
		src="image/site/simpleLogo.png" /></a>
		<div class="m-sign-in">
		<form id="loginForm" action="../formLogin.do" method="post"
            class="m-login-input">
	<%@ include file="../include/fore/loginModel.jsp"%>
	</form>
	</div>
</div>


<%@ include file="../include/fore/separator.jsp"%>
<%@ include file="../include/fore/footer.jsp"%>