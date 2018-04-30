<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../include/fore/header.jsp" %>

<title>天猫tmall.com--我的订单</title>

<%@ include file="../include/fore/navigator.jsp" %>
<%@ include file="../include/fore/search.jsp" %>
<%@ include file="../include/fore/separator.jsp" %>

<div class="g-mid">
	<ul class="m-order-title">
		<li class="selected">
			<a href="javascript:;" rel="all">所有订单</a>
		</li>
		<li class="divider">|</li>
		<li>
			<a href="javascript:;" rel="unpay">待付款</a>
		</li>
		<li class="divider">|</li>
		<li>
			<a href="javascript:;" rel="unship">待发货</a>
		</li>
		<li class="divider">|</li>
		<li>
			<a href="javascript:;" rel="unconfirm">待收货</a>
		</li>
		<li class="divider">|</li>
		<li>
			<a href="javascript:;" rel="unreview">待评价</a>
		</li>
		<li class="divider">|</li>
        <li>
            <a href="javascript:;" rel="success">已完成</a>
        </li>
	</ul>
	<div class="m-order-head">
		<span style="width: 60%;">宝贝</span>
		<span style="width: 10%;">单价</span>
		<span style="width: 8%;">数量</span>
		<span style="width: 12%;">实付款</span>
		<span style="width: 10%;">交易操作</span>
	</div>
	
	<div id="order-details">
	
	</div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">删除分类</h4>
      </div>
      <div class="modal-body">
       <p>删除操作不可逆转</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消删除</button>
        <button type="button" class="btn btn-primary">
        	<a id="deleteConfirm" href="" style="color: white;">继续删除</a>
        </button>
        <input id="oid" type="hidden" value="" />
      </div>
    </div>
  </div>
</div>
	<script type="text/javascript">
		$(document).ready(function() {
			getInfo();
			
			$(".m-order-title li").click(function() {
				$(".m-order-title li").removeClass("selected");
				$(this).addClass("selected");
				var s = $(this).find("a").prop("rel");
				switch(s) {
					case "unpay":
						$(".m-order").hide();
						$(".order-unpay").show();
						break;
					case "unship":
						$(".m-order").hide();
						$(".order-unship").show();
						break;
					case "unconfirm":
						$(".m-order").hide();
						$(".order-unrecive").show();
						break;
					case "unreview":
						$(".m-order").hide();
						$(".order-unreview").show();
						break;
					case "success":
                        $(".m-order").hide();
                        $(".order-success").show();
                        break;
					default:
						$(".m-order").show();
				}
			})
			$("#deleteConfirm").click(function(){
				$.ajax({
					url: $("#deleteConfirm").attr('href'),
					type: "DELETE",
					success: function(){
	                    getInfo();
	                }
				});
				$("#myModal").modal('hide');
				return false;
			})
			
		})
	</script>
    <script>
    function getInfo(){
    	$.get("../s-orders", function(orders){
            var html = "";
            for (var o of orders){
                var innerHtml = "";
                console.log(o.status);
                if (o.status === '待发货'){
                    innerHtml += '<div class="m-order order-unship">';
                } else if (o.status == '待付款'){
                    innerHtml += '<div class="m-order order-unpay">';
                } else if (o.status == '待收货'){
                    innerHtml += '<div class="m-order order-unrecive">';
                } else if (o.status == '待评价'){
                    innerHtml += '<div class="m-order order-unreview">';
                } else {
                    innerHtml += '<div class="m-order order-success">';
                }
                
                innerHtml += '<div class="u-orderId">'+
                    '<div style="width: 60%;"><strong>' + timeFormat(o.createDate) + '</strong> 订单号: ' + o.orderCode + '</div>'+
                    '<div><img src="image/site/tmallbuy.png" />天猫商场</div>'+
                    '<a href="javascript:;" rel="' + o.id +'">'+
                    ' <i class="delete glyphicon glyphicon-trash"></i>'+
                    ' </a>'+
                    '</div>';
                    
                innerHtml += '<div class="u-orderName"><table border="1" cellpadding="10px">';
                
                var i = 0;
                for (var oi of o.orderItems){
                    i++;
                    innerHtml += '<tr>' +
                    '<td style="width: 10%;"><img class="item-img" src="image/product/' + oi.product.id + '/' + oi.product.firstProductImage.id + '.jpg" /></td>' +
                    '<td class="name" style="width: 50%;">' +
                        '<a href="../product/' + oi.product.id + '">' + oi.product.name + '</a>' +
                        '<div>' +
                            '<img src="image/site/creditcard.png" />' +
                            '<img src="image/site/7day.png" />' +
                            '<img src="image/site/promise.png" />' +
                        '</div>' +
                    '</td>' +
                    '<td style="width: 10%;">' +
                        '<span style="color: #8C8C8C; text-decoration: line-through;">' + accounting.formatMoney(oi.product.orignalPrice, "￥", 2, ",", ".") +
                    '</span> <br />' + accounting.formatMoney(oi.product.promotePrice, "￥", 2, ",", ".") +
                    '</td>' +
                    '<td style="width: 8%;border-left: 1px solid #E6E6E6;">' + oi.number + '</td>' +
                    '<td style="width: 12%;border-left: 1px solid #E6E6E6;">' +
                        '<strong>' + accounting.formatMoney(oi.prices, "￥", 2, ",", ".") + '</strong><br />' +
                        '<span style="font-size: 12px;">(含运费:￥0.00)</span>' +
                    '</td>' +
                    '<td style="width: 10%;border-left: 1px solid #E6E6E6;">';
                    
                    if (i === 1){
                        if (o.status === '待发货'){
                            innerHtml += '<span>待发货</span>';
                        } else if (o.status === '待付款'){
                            innerHtml += '<button class="btn btn-info btn-payment"><a href="../s-order/' + o.id + '/pay" style="color: white;">付款</a></button>';
                        } else if (o.status === '待收货'){
                            innerHtml += '<button class="btn btn-info btn-confirm"><a href="../s-order/' + o.id + '/confirm" style="color: white;">确认收货</a></button>';
                        } else if (o.status === '待评价'){
                            innerHtml += '<button class="btn btn-default btn-review"><a href="../s-order/' + o.id + '/review" style="color: black;">评价</a></button>';
                        } else {
                            innerHtml += '<span>已完成</span>';
                        }
                    }
                    innerHtml += '</td></tr>';
                }
                innerHtml += '</table></div></div>';
                
                html += innerHtml;
            }
            
            $('#order-details').html(html);
            
            $(".delete").click(function(){
                var oid = $(this).closest('a').prop("rel");
                $("#deleteConfirm").attr('href', '../s-order/' + oid +'/delete')              
                $("#myModal").modal("show");
                return false;
            })
            
        });
    }
    </script>
	<%@ include file="../include/fore/separator.jsp" %>
	<%@ include file="../include/fore/footer.jsp" %>