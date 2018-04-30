<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../include/fore/header.jsp" %>
<%@ include file="../include/fore/navigator.jsp" %>
<%@ include file="../include/fore/search.jsp" %>
<%@ include file="../include/fore/separator.jsp" %>

<title>天猫tmall.com--${c.name}</title>

<div class="g-mid">
	<div class="m-sort">
		<a href="../category/${c.id}">
			<img src="image/category/${c.id}.jpg" alt="title" />
		</a>
		<br />
		<ul class="methods">
			<li>
				<a href="#1" class="active">综合<span class="glyphicon glyphicon-arrow-down"></span></a>
			</li>
			<li>
				<a href="">人气<span class="glyphicon glyphicon-arrow-down"></span></a>
			</li>
			<li>
				<a href="">新品<span class="glyphicon glyphicon-arrow-down"></span></a>
			</li>
			<li>
				<a href="">销量<span class="glyphicon glyphicon-arrow-down"></span></a>
			</li>
			<li>
				<a href="">价格<span class="glyphicon glyphicon-sort"></span></a>
			</li>
		</ul>
		<div class="input">
			<input type="text" placeholder="请输入" />
			<span>-</span>
			<input type="text" placeholder="请输入" />
		</div>
	</div>
	<br />
	<div class="m-category">
		<table border="0">
		</table>
	</div>

</div>

<script type="text/javascript">

    jQuery(function($){
    	var col = "time";
    	var seq = "desc";
    	getProductInfo(col, seq);
    });
    
    function getProductInfo(col, seq){
    	$.get("../${c.id}/products?col=" + col + "&seq=" + seq, function(ps){
    		var html = "";
    		for (var i in ps){
    			var innerhtml = "";
    			if (i % 5 === 0){
    				innerhtml += '<tr>';
    			}
    			innerhtml += '<td><div class="items"><a href="../product/' + ps[i].id + '"><img src="image/product/' + ps[i].id + '/' + ps[i].firstProductImage.id + '.jpg" alt="商品" width="177px" height="180px" /></a>' +
                    '<br /><div><a class="name" href="../product' + ps[i].id + '">' + ps[i].name.substring(0, 30) + '</a>' +
                    '<div class="s-redColor price">¥ ' + accounting.formatMoney(ps[i].promotePrice, "￥", 2, ".", ",") + '</div></div></div></td>';
                if (i % 5 === 4){
                	innerhtml += '</tr>';
                }
                
                html += innerhtml;
    		}
    		
    		$('.m-category table').html(html);
    	});
    }

</script>

<%@ include file="../include/fore/separator.jsp" %>
<%@ include file="../include/fore/footer.jsp" %>