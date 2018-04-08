<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/admin/header.jsp"%>
<%@ include file="../include/admin/navigator.jsp"%>

<title>产品图片管理</title>
<style>
    .ace-file-multiple .ace-file-container{
        width: 150px;
        height: 150px;
        border: none;
    }
</style>

<div class="page-header">
	<h1>
	   <a class="m-product-title"></a>
		<small> <i class="ace-icon fa fa-angle-double-right"></i>
			产品展示图
		</small>
	</h1>
</div>
<!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<div>
			<ul id="single" class="ace-thumbnails clearfix">
        
				<!-- <li><div>
						<img width="150" height="150" alt="150x150" src="assets/images/gallery/thumb-5.jpg" />
						<div class="text">
							<div class="inner">
								<span>Some Title!</span> <br /> 
								<a href="assets/images/gallery/image-5.jpg" data-rel="colorbox"><i class="ace-icon fa fa-search-plus"></i></a> 
								<a href="#"> <i class="ace-icon fa fa-times red"></i></a> 
							</div>
						</div>
				</div></li> -->
			</ul>
		</div>
		<!-- PAGE CONTENT ENDS -->
	</div>
	<!-- /.col -->
</div>
<!-- /.row -->

<div class="page-header">
    <h1>
       <a class="m-product-title"></a>
        <small> <i class="ace-icon fa fa-angle-double-right"></i>
                            产品详情图
        </small>
    </h1>
</div>
<!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <div>
            <ul id="details" class="ace-thumbnails clearfix">
        
                <!-- <li><div>
                        <img width="150" height="150" alt="150x150" src="assets/images/gallery/thumb-5.jpg" />
                        <div class="text">
                            <div class="inner">
                                <span>Some Title!</span> <br /> 
                                <a href="assets/images/gallery/image-5.jpg" data-rel="colorbox"><i class="ace-icon fa fa-search-plus"></i></a> 
                                <a href="#"> <i class="ace-icon fa fa-times red"></i></a> 
                            </div>
                        </div>
                </div></li> -->
            </ul>
        </div>
        <!-- PAGE CONTENT ENDS -->
    </div>
    <!-- /.col -->
</div>
<!-- /.row -->
<div id="delete-div" class="hide">
    <div class="col-sm-9">确认删除吗？</div>
</div>

<%@ include file="../include/admin/footer.jsp"%>

<script type="text/javascript">
	jQuery(function($) {

		url = window.location.href;
		url = url.substring(0, url.lastIndexOf("/") + 1);
		getData();

		var $overflow = '';
	    var colorbox_params = {
	        rel: 'colorbox',
	        reposition:true,
	        scalePhotos:true,
	        scrolling:false,
	        previous:'<i class="ace-icon fa fa-arrow-left"></i>',
	        next:'<i class="ace-icon fa fa-arrow-right"></i>',
	        close:'&times;',
	        current:'{current} of {total}',
	        maxWidth:'100%',
	        maxHeight:'100%',
	        onOpen:function(){
	            $overflow = document.body.style.overflow;
	            document.body.style.overflow = 'hidden';
	        },
	        onClosed:function(){
	            document.body.style.overflow = $overflow;
	        },
	        onComplete:function(){
	            $.colorbox.resize();
	        }
	    }
	    
	    //$('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
	    //console.log($('.ace-thumbnails [data-rel="colorbox"]').attr('href'));
	   // $("#cboxLoadingGraphic").html("<i class='ace-icon fa fa-spinner orange fa-spin'></i>");//let's add a custom loading icon
	    
	    /* $(document).one('ajaxloadstart.page', function(e) {
	        $('#colorbox, #cboxOverlay').remove();
	    }); */
		
	  //override dialog's title function to allow for HTML titles
	    $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
	        _title: function(title) {
	            var $title = this.options.title || '&nbsp;'
	            if(("title_html" in this.options) && this.options.title_html == true)
	                title.html($title);
	            else title.text($title);
	        }
	    }));
	    
		$(".ace-thumbnails").on("click", '.m-delete', function(e){
			e.preventDefault();
	        var link = $(this).attr("href");
	        var id = link.substring(link.indexOf("?")+1);
	        var dialog = $("#delete-div").removeClass('hide').dialog({
	            modal: true,
	            width: 450,
	            title: "<div class='widget-header widget-header-small'><h4 class='smaller'>删除</h4></div>",
	            title_html: true,
	            buttons: [{
	                    text: "取消",
	                    "class": "btn btn-minier",
	                    click: function() {
	                        $(this).dialog("close");
	                    }
	                },
	                {
	                    text: "确认",
	                    "class": "btn btn-primary btn-minier",
	                    click: function() {
	                            $.ajax({
	                                url: url + id,
	                                type: 'DELETE',
//	                              data: {
//	                                  _method: 'DELETE'
//	                              },
	                                success: function(){
	                                    getData();
	                                }
	                            });
	                            $(this).dialog("close");
	                    }
	                }
	            ]
	        });
		});
	    
	    $('#id-input-file-3').ace_file_input({
            style: 'well',
            btn_choose: '',
            btn_change: null,
            no_icon: 'glyphicon glyphicon-plus',
            droppable: true,
            thumbnail: 'small'//large | fit
            
        });
	});
</script>
<script type="text/javascript">
function getData() {
	$(".ace-thumbnails").empty();
    $.get(url, function(data, status){
        $('.m-product-title').text(data.name);
        $('.m-product-title').attr("href", url.substring(0, url.indexOf("s/")));
    })
    
    var param = $.cookie('pp-pageParam');
    if (!param)  param =  "";
    $.get(url + "singles" + param, function(list, status) {
        for(var i in list) {
            $('#single').append(
            		'<li><div>' +
                    '<img width="150" height="150" alt="150x150" src="image/product/' + list[i].product.id + "/" + list[i].id + '.jpg" />' +
                    '<div class="text"><div class="inner">' +
                    '<a href="image/product/' + list[i].product.id + "/" + list[i].id + '.jpg" data-rel="colorbox"><i class="ace-icon fa fa-search-plus"></i></a>' +
                    '<a class="m-delete" href="delete?'+ list[i].id +'"> <i class="ace-icon fa fa-times red"></i></a>' +
                    '</div>' +
                    '</div></div></li>'
            );
        }
        
        $('#single').append('<li><div><form id="m-singleImage" enctype="multipart/form-data"><input type="file" accept="image/jpeg" multiple="" id="id-input-file-3" name="image" /></form></div></li>');
        //multiple=""
        var $overflow = '';
        var colorbox_params = {
            rel: 'colorbox',
            reposition:true,
            scalePhotos:true,
            scrolling:false,
            previous:'<i class="ace-icon fa fa-arrow-left"></i>',
            next:'<i class="ace-icon fa fa-arrow-right"></i>',
            close:'&times;',
            current:'{current} of {total}',
            maxWidth:'700px',
            maxHeight:'700px',
            onOpen:function(){
                $overflow = document.body.style.overflow;
                document.body.style.overflow = 'hidden';
            },
            onClosed:function(){
                document.body.style.overflow = $overflow;
            },
            onComplete:function(){
                $.colorbox.resize();
            }
        }
        $('#single [data-rel="colorbox"]').colorbox(colorbox_params);
        $("#cboxLoadingGraphic").html("<i class='ace-icon fa fa-spinner orange fa-spin'></i>");//let's add a custom loading icon
        
        $('#id-input-file-3').ace_file_input({
            style: 'well',
            btn_choose: '上传图片',
            btn_change: null,
            no_icon: 'glyphicon glyphicon-plus',
            //droppable: true,
            thumbnail: "small",//large | fit | small
            allowExt: ["jpeg", "jpg"],
            //allowMime: ["image/jpg", "image/jpeg"],
        }).on('change', function(){
        	$("#m-singleImage").ajaxSubmit({
        		url: url + "singles",
        		type: "POST",
        		success: function(){
        			getData();
        		},
        		clearForm: true
        	});
        });
    });
    
    $.get(url + "details" + param, function(list, status) {
        for(var i in list) {
            $('#details').append(
                    '<li><div>' +
                    '<img width="150" height="150" alt="150x150" src="image/product/' + list[i].product.id + "/" + list[i].id + '.jpg" />' +
                    '<div class="text"><div class="inner">' +
                    '<a href="image/product/' + list[i].product.id + "/" + list[i].id + '.jpg" data-rel="colorbox"><i class="ace-icon fa fa-search-plus"></i></a>' +
                    '<a class="m-delete" href="delete?'+ list[i].id +'"> <i class="ace-icon fa fa-times red"></i></a>' +
                    '</div>' +
                    '</div></div></li>'
            );
        }
        
        $('#details').append('<li><div><form id="m-detail" enctype="multipart/form-data" method="post"><input type="file" id="id-input-file-4" accept="image/jpeg" name="image" /></form></div></li>');
        //multiple=""
        var $overflow = '';
        var colorbox_params = {
            rel: 'colorbox',
            reposition:true,
            scalePhotos:true,
            scrolling:false,
            previous:'<i class="ace-icon fa fa-arrow-left"></i>',
            next:'<i class="ace-icon fa fa-arrow-right"></i>',
            close:'&times;',
            current:'{current} of {total}',
            maxWidth:'700px',
            maxHeight:'700px',
            onOpen:function(){
                $overflow = document.body.style.overflow;
                document.body.style.overflow = 'hidden';
            },
            onClosed:function(){
                document.body.style.overflow = $overflow;
            },
            onComplete:function(){
                $.colorbox.resize();
            }
        }
        $('#details [data-rel="colorbox"]').colorbox(colorbox_params);
        $("#cboxLoadingGraphic").html("<i class='ace-icon fa fa-spinner orange fa-spin'></i>");//let's add a custom loading icon
        
        $('#id-input-file-4').ace_file_input({
            style: 'well',
            btn_choose: '上传图片',
            btn_change: null,
            no_icon: 'glyphicon glyphicon-plus',
            //droppable: true,
            thumbnail: "small",//large | fit | small
            allowExt: ["jpeg", "jpg"],
            //allowMime: ["image/jpg", "image/jpeg"],
        }).on('change', function(){
            $("#m-detail").ajaxSubmit({
                url: url + "details",
                type: "post",
                success: function(){
                    getData();
                },
                clearForm: true
            });
        });
    });
}
</script>

<%@ include file="../include/admin/end.jsp"%>