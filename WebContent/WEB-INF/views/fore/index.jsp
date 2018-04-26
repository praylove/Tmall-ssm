<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ include file="../include/fore/header.jsp" %>
<%@ include file="../include/fore/navigator.jsp" %>
<%@ include file="../include/fore/search.jsp" %>
<%@ include file="../include/fore/separator.jsp" %>

<title>天猫tmall.com--理想生活上天猫</title>

<div style="position: relative; background-color: #FF0036;">
			<div class="g-mid">
				<div class="m-nav-2">
					<span class="u-nav2-title">商品分类</span>
					<ul class="u-nav2-list">
						<li>
							<a class="link" href="javascript:;">服装服饰</a>
						</li>
						<li>
							<a class="link" href="javascript:;">电器家装</a>
						</li>
						<li>
							<a class="link" href="javascript:;">医药健康</a>
						</li>
						<li>
							<a class="link" href="javascript:;">苏宁易购</a>
						</li>
						<li>
							<a class="link" href="javascript:;">手机会场</a>
						</li>
						<li>
							<a class="link" href="javascript:;">车品配件</a>
						</li>
						<li>
							<a class="link" href="javascript:;">全球尖货</a>
						</li>
						<li>
							<a class="link" href="javascript:;">飞猪旅行</a>
						</li>
						
					</ul>
				</div>
			</div>
		<div style="position: relative;">
            <div class="g-slider">
                <div id="carousel" class="carousel slide g-mid">
                    <ol class="carousel-indicators">
                        <li data-target="#carousel" data-slide-to="0" class="active"></li>
                        <li data-target="#carousel" data-slide-to="1"></li>
                        <li data-target="#carousel" data-slide-to="2"></li>
                        <li data-target="#carousel" data-slide-to="3"></li>
                    </ol>
                    <div class="carousel-inner">
                        <div class="item active">
                            <a herf="#1"><img src="image/site/1.jpg" alt="pic1" /></a>
                        </div>
                        <div class="item">
                            <a herf="#2"><img src="image/site/2.jpg" alt="pic2" /></a>
                        </div>
                        <div class="item">
                            <a herf="#3"><img src="image/site/3.jpg" alt="pic3" /></a>
                        </div>
                        <div class="item">
                            <a herf="#4"><img src="image/site/4.jpg" alt="pic4" /></a>
                        </div>
                    </div>
                </div>
            </div>
			<div class="g-mid">
			   <ul class="g-class-list m-class-list"></ul>
                <div class="m-class-items"></div>
			</div>
		</div>
</div>

<div class="g-mid m-types">
</div>
<script type="text/javascript">
    jQuery(function($){
    	getIndexInfo();
    });
</script>

<script type="text/javascript">
    function getIndexInfo(){
    	// get category with products
    	$.get('../categorys', function(cs){
    		var infoHtml = "";
            for (var i in cs){
            	if (i >= 17) break;
                infoHtml += '<li><a href="../category/' + cs[i].id +'" rel=' + cs[i].id + '>' + cs[i].name +'</a></li>';
            }
            $('.m-class-list').html(infoHtml);
            
            var typesHtml = "";
            for (var i in cs){
            	if (cs[i].products.length === 0) continue;
                var html = '<div class="u-type"><div class="g-title"><div class="left-ico"></div><a href="href="../category/' + cs[i].id +'" rel=' + cs[i].id + '"><h4 style="color: #333;"><strong>' + cs[i].name + '</strong></h4></a>';
                for (var p of cs[i].products){
                    html += '<div class="items"><div style="width: 180px; height: 180px"><a href="../product/' + p.id + '"><img src="image/product/' + p.id +'/' + p.firstProductImage.id + '.jpg" alt="商品" width="180px" height="180px"/></a></div>' +
                        '<br /><div><a href="../product/' + p.id + '"><span class="name">[热销] ' + p.name.substring(0, 20) + '</span></a>' +
                        '<div class="s-redColor price">¥ ' + p.promotePrice + '</div></div></div>';
                }
                html += '</div></div>';
                
                typesHtml += html;
            }
            $('.m-types').html(typesHtml);
            
            $('.m-class-list li').hover(function(){
                var cid = parseInt($(this).find('a').attr('rel'));
                getItems(cid);
                $('.m-class-list li').addClass('s-white-bg');
                $('.m-class-items').show();
            }, function(){
                $('.m-class-list li').removeClass('s-white-bg');
                $('.m-class-items').hide();
            });
            $('.m-class-items').hover(function(){
                $('.m-class-list li').addClass('s-white-bg');
                $('.m-class-items').show();
                }, function(){
                $('.m-class-list li').removeClass('s-white-bg');
                $('.m-class-items').hide();
            });
    	});
    	
    	
    }
    
    function getItems(cid){
    	 $.get('../' + cid + '/products/subtitle' , function(data){
    		 var itemHtml = "";
             for (var i in data){
                 var textHtml = '<div class="m-item-text">';
                 for (var j in data[i]){
                	 var title = data[i][j].subTitle.trim();
                	 var index = title.indexOf(' ');
                	 if (index !== -1){
                		 title = title.substring(0, index);
                	 }
                	 console.log(title);
                     textHtml += '<a href="../product/' + data[i][j].id +'">' + title + '</a>';
                 }
                 textHtml += '</div>';
                 itemHtml += textHtml;
             }
             
             $('.m-class-items').html(itemHtml);
    	 });
    }
</script>

<%@ include file="../include/fore/separator.jsp" %>
<%@ include file="../include/fore/footer.jsp" %>