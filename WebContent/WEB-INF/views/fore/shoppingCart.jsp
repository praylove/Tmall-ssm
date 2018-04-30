<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../include/fore/header.jsp" %>

<link rel="stylesheet" href="assets/font-awesome/4.5.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="assets/css/jquery-ui.custom.min.css" />
<style>
    .table tbody tr td{
        vertical-align: middle;
    }
</style>
<title>天猫tmall.com--购物车</title>

<%@ include file="../include/fore/navigator.jsp" %>
<%@ include file="../include/fore/search.jsp" %>
<%@ include file="../include/fore/separator.jsp" %>

<div style="padding: 20px;"></div>
<div id="shopping-car" class="g-mid">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>
                        <div class="checkAll">
                                <span>
                                <img class="unselectAll" src="image/site/cartNotSelected.png" alt="" />
                                <img class="selectAll" src="image/site/cartSelected.png" alt=""/>
                            </span>
                            全选
                        </div>
                    </th>
                    <th>商品信息</th>
                    <th>单价</th>
                    <th>数量</th>
                    <th>金额</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                    
                    
                </tbody>
            </table>
        </div>

<div class="modal fade" id="myModa2" role="dialog" aria-labelledby="myModalLabel">
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
        	<a id="deleteConfirm" style="color: white;">继续删除</a>
        </button>
        <input id="pid" type="hidden" value="" />
      </div>
    </div>
  </div>
</div>

<script src="assets/js/spinbox.min.js"></script>
<script src="assets/js/ace-elements.min.js"></script>
<script src="assets/js/ace.min.js"></script>

<script type="text/javascript">
jQuery(function($){
	
	getCarInfo();
    
});
</script>
<script type="text/javascript">
function getCarInfo(){
	$.get("../s-shoppingcars", function(cars){
        var html = "";
         for (var car of cars){
             html += '<tr>' +
             '<td>' +
                 '<div class="selection">' +
                     '<span class="g-check">' +
                         '<img class="unselect" src="image/site/cartNotSelected.png" alt="" />' +
                         '<img class="select" src="image/site/cartSelected.png" alt=""/>' +
                     '</span>' +
                     '<img class="item-img" src="image/product/' + car.product.id + '/' + car.product.firstProductImage.id + '.jpg" height="80px" width="80px"/>' +
                     '<input type="checkbox" style="display: none;">' +
                 '</div>' +
             '</td>' +
             '<td>' +
                 '<a href="../product/' + car.product.id + '">' + car.product.name + '</a>' +
                 '<div>' +
                     '<img src="image/site/creditcard.png" />' +
                     '<img src="image/site/7day.png" />' +
                     '<img src="image/site/promise.png" />' +
                 '</div>' +
             '</td>' +
             '<td>' +
                 '<div style="color: #8C8C8C; text-decoration: line-through;">' + accounting.formatMoney(car.product.orignalPrice, "￥", 2, ",", ".") + '</div>' +
                 '<div class="s-redColor unit-price">' + accounting.formatMoney(car.product.promotePrice, "￥", 2, ",", ".") + '</div>' +
             '</td>' +
             '<td>' +
                 '<input type="text" id="spinner' + car.id +'" class="spinner" rel="' + car.id +'"/>' +
             '</td>' +
             '<td>' +
                 '<div class="price">' + accounting.formatMoney(car.prices, "￥", 2, ",", ".") + '</div>' +
             '</td>' +
             '<td>' +
                 '<a class="delete" href="javascript:;" rel="' + car.id +'">删除</a>' +
             '</td>' +
         '</tr>';
         
         }
         html += '<tr><td><div class="checkAll"><span><img class="unselectAll" src="image/site/cartNotSelected.png" alt="" />' +
             '<img class="selectAll" src="image/site/cartSelected.png" alt=""/></span>全选</div></td><td colspan="3" >' +
            '<span class="pull-left">已选商品<span id="count" style="color: #FF0036; font-size: 16px;"> 0 </span> 件' +
            '</span><span class="pull-right"> 合计(不含运费)：<span id="total" style="color: #FF0036; font-size: 18px;"> ￥0.00</span></span></td><td colspan="2"><button id="buyButton" class="btn btn-danger">结算</button></td></tr>';
         
         $('#shopping-car tbody').html(html);
         
         for (var car of cars){
        	 $('#spinner' + car.id).ace_spinner({value:car.number,min:1,max:car.product.stock,step:1, on_sides: true, icon_up:'ace-icon fa fa-plus bigger-110', icon_down:'ace-icon fa fa-minus bigger-110', btn_up_class:'btn-success' , btn_down_class:'btn-danger'});
         }
         
         $('.spinner').closest('.ace-spinner')
            .on('changed.fu.spinbox', function(){
            var $input = $(this).find('.spinner');
             console.log($(this).find('.spinner').val());
             $.ajax({
            	 url: "../s-shoppingcars/" + $input.attr('rel'),
                 type: "POST",
                 data: {
                	 number: $input.val(),
                	 _method: "PUT"
                 },
                 success: function(){
                	 getCarInfo();
                 }
             });
         });
         
         $('.selection').click(function(){
             var $checkbox = $(this).find(':checkbox');
             if (!$checkbox.prop('checked')){
                 boxChecked($(this));
             } else{
                 boxUnchecked($(this));
             }
             allCheckJustify();
         });
         
         $('.checkAll').click(function(){
             if ($('.selectAll').css('display') === "none"){
                 $('.selection').each(function(){
                     boxChecked($(this));
                 });
                 allCheck();
             } else{
                 $('.selection').each(function(){
                     boxUnchecked($(this));
                 });
                 allUncheck();
             }
         });
         
         $('.delete').click(function(){
        	 $('#deleteConfirm').prop('rel', $(this).attr('rel'));
        	 $('#myModa2').modal();
         });
         
         $('#deleteConfirm').click(function(){
        	 $.ajax({
                 url: "../s-shoppingcars/" + $(this).attr('rel') + "/delete",
                 type: "POST",
                 success: function(){
                     getCarInfo();
                 }
             });
         });
         
         $('#buyButton').click(function(){
        	 var param = "";
        	 $('.selection :checkbox').each(function(){
        		 if ($(this).prop('checked')){
        			 var id = $(this).closest('tr').find('.spinner').attr('rel');
        			 if (param === ""){
        				 param = "carids=" + id;
        			 } else {
        				 param += "&carids=" + id; 
        			 }
        		 }
        	 });
        	 if (param !== ""){
        		 window.location.href = '../s-buy-car?' + param;
        	 }
         });
    });
}
function boxChecked($box){
    $box.find('.select').show();
    $box.find('.unselect').hide();
    $box.find(':checkbox').prop('checked', true);
    $box.closest('tr').addClass("warning");
}
function boxUnchecked($box){
    $box.find('.select').hide();
    $box.find('.unselect').show();
    $box.find(':checkbox').prop('checked', false);
    $box.closest('tr').removeClass("warning");
}
function allCheck(){
    $('.unselectAll').hide();
    $('.selectAll').show();
}
function allUncheck(){
    $('.unselectAll').show();
    $('.selectAll').hide();
}
function allCheckJustify(){
    var count = 0;
    var checkCount = 0;
    var totalPrice = 0;
    $(".selection :checkbox").each(function(){
        count++;
        if ($(this).prop('checked')){
            checkCount++;
            var money = $(this).closest('tr').find('.price').text();
            totalPrice += accounting.unformat(money);
        } 
    });
    if (checkCount < count){
        allUncheck();
    } else{
        allCheck();
    }
    $('#count').text(checkCount);
    $('#total').text(accounting.formatMoney(totalPrice, "￥", 2, ",", "."));
   }
</script>

<%@ include file="../include/fore/separator.jsp" %>
<%@ include file="../include/fore/footer.jsp" %>