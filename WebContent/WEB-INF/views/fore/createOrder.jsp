<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../include/fore/header.jsp" %>

<title>天猫tmall.com--创建订单</title>

<%@ include file="../include/fore/navigator.jsp" %>
<%@ include file="../include/fore/search.jsp" %>
<%@ include file="../include/fore/separator.jsp" %>

<div class="g-mid">
	<div style="height: 70px;">
		<a href="foreindex" style="float: left;"><img src="image/site/simpleLogo.png" alt="tmall天猫" /></a>
		<img src="image/site/buyflow.png" style="float: right;" />
	</div>
	<form id="orderInfo" action="../s-order/create" method="post">
	<table border="0" class="m-recipt-info">
		
		<tr>
			<td><strong style="font-size: 18px;">输入收货地址</strong></td>
			<td></td>
		</tr>
		<tr>
			<td>详细地址<sup style="color: #FF0036;">*</sup></td>
			<td>
				<input type="text" id="address" name="address" style="width: 300px;" placeholder="请输入地址" />
				<span class="tips">
							建议您如实填写详细收货地址，例如街道名称，门牌号码，楼层和房间号等信息
						</span>
			</td>
		</tr>
		<tr>
			<td>邮政编码</td>
			<td>
				<input type="text" id="post" name="post" placeholder="请输入邮政区号" />
				<span class="tips">
							如果您不清楚邮递区号，请填写000000
						</span>
			</td>
		</tr>
		<tr>
			<td>收货人姓名<sup style="color: #FF0036;">*</sup></td>
			<td>
				<input type="text" id="receiver" name="receiver" placeholder="请输入收货人姓名" />
				<span class="tips">
							长度不超过25个字符
						</span>
			</td>
		</tr>
		<tr>
			<td>手机号码 <sup style="color: #FF0036;">*</sup></td>
			<td>
				<input type="text" id="mobile" name="mobile" placeholder="请输入收货手机号" />
				<span class="tips">
					请输入11位手机号码
				</span>
			</td>
		</tr>
		
	</table>
	<div>
		<strong style="font-size: 18px;">确认订单信息</strong>
		<table class="m-myorder-details">
			<thead>
				<tr>
					<th width="640px" colspan="2" style="text-align: left;">
						<img src="image/site/orderItemTmall.png" width="20px" />
						<a href="javascript:;">店铺：天猫店铺</a>
						<span class="m-aLiWangWang-active"></span>
					</th>
					<th width="85px">单价</th>
					<th width="40px">数量</th>
					<th width="85px">小计</th>
					<th width="250px">配送方式</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sessionScope.ois}" var="oi" varStatus="status">
				<tr>
					<td>
						<img src="image/product/${oi.product.id}/${oi.product.firstProductImage.id}.jpg" width="80px" height="80px" />
					</td>
					<td>
						<a href="../product/${oi.product.id}">${oi.product.name}</a>
						<div>
							<img src="image/site/creditcard.png" />
							<img src="image/site/7day.png" />
							<img src="image/site/promise.png" />
						</div>
					</td>
					<td>
						￥<fmt:formatNumber value="${oi.product.promotePrice}" pattern="#,#00.00#" />
					</td>
					<td>
						${oi.number}
					</td>
					<td>
						<span class="s-redColor"><fmt:formatNumber value="${oi.prices}" pattern="#,#00.00#" /></span>
					</td>
					<c:if test="${status.index == 0}">
						<td>
						<input type="radio" checked="" /> 普通配送
							<select name="" style="padding: 2px;">
							<option value="快递 免邮费">快递 免邮费</option>
						</select>
					</td>
					</c:if>
				</tr>
				</c:forEach>
				
			</tbody>
		</table>
		
		<div class="m-myorder-bottom">
			<div style="float: left;">给卖家留言:</div>
			<span class="tips">还可以输入<span class="s-redColor" id="leftWord">200</span>字</span>
			<textarea id="userMessage" name="userMessage" rows="1" cols="50" style="font-size: 14px;color: #000000;margin-left: 20px;"></textarea>
			<div style="float: right;">店铺合计(含运费): ￥<fmt:formatNumber value="${total}" pattern="#,#00.00#" /></div>
		</div>
		
		<%@ include file="../include/fore/separator.jsp" %>
		
		<div class="pull-right col-sm-5">
			<button id="orderSubmit" class="btn btn-default pull-right">
				提交订单
			</button>
			<div class="pull-right" style="margin-right: 50px;">
                             实付款： <strong class="s-redColor" style="font-size: 20px;">￥<fmt:formatNumber value="${total}" pattern="#,#00.00#" /></strong>
            </div>
		</div>
	</div>
	</form>
</div>
<%@ include file="../include/fore/separator.jsp" %>
<script src="assets/js/jquery.validate.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".m-recipt-info td input").focus(function() {
			$(this).parent().find(".tips").show();
		})
		$(".m-recipt-info td input").blur(function() {
			$(this).parent().find(".tips").hide();
		})
		$(".m-myorder-bottom textarea").focus(function() {
			$(this).prop("rows", "5");
			$(this).parent().css("height", "120px");
			$(this).parent().find(".tips").show();
		})
		$(".m-myorder-bottom textarea").blur(function() {
			$(this).prop("rows", "1");
			$(this).parent().css("height", "50px");
			$(this).parent().find(".tips").hide();
		})
		$(".m-myorder-bottom textarea").keyup(function() {
			var len = $(this).val().length;
			$("#leftWord").text((200 - len) + "");
		})
		$("#orderSubmit").click(function(e){
			e.preventDefault();
			
			if ($('#orderInfo').validate().form())
			    $("#orderInfo").submit();
		})
		
		$.validator.addMethod("postValidate", function(value, element, params){
            var name = /^\w{6,16}$/;
            return this.optional(element) || (name.test(value));
        });
		
		$.validator.addMethod("mobileValidate", function(value, element, params){
            var name = /^1[3|5|7]\d{9}$/;
            return this.optional(element) || (name.test(value));
        });
		
		$('#orderInfo').validate({
			rules:{
				address: {
					required: true
				}, post: {
					required: true,
					postValidate: true
				}, receiver: {
					required: true
				}, mobile: {
					required: true,
					mobileValidate: true
				}, userMessage: {
					maxlength: 200
				}
			}, messages: {
				address: {
                    required: "地址不能为空"
                }, post: {
                    required: "邮编不能为空",
                    postValidate: "请输入正确的邮编"
                }, receiver: {
                    required: "地址不能为空"
                }, mobile: {
                    required: "地址不能为空",
                    mobileValidate: "请输入正确的手机号码"
                }, userMessage: {
                    maxlength: "用户备注信息不得超过200字"
                }
			},
                onkeyup: false,
                onfocusout: false,
                focusCleanup: true,
                errorClass: "m-error-class",
                errorElement: "div",
		});
	})
</script>

<%@ include file="../include/fore/separator.jsp" %>
<%@ include file="../include/fore/footer.jsp" %>