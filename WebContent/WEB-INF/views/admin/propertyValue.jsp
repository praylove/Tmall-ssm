<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/admin/header.jsp"%>
<%@ include file="../include/admin/navigator.jsp"%>

<title>属性值管理</title>

<div class="page-header">
	<h1>
	   <a class="m-category-name"></a>
        
        <small> <i class="ace-icon fa fa-angle-double-right"></i>
                    <a class="m-product-title"></a>
        </small>
        <small> <i class="ace-icon fa fa-angle-double-right"></i>
                    属性值管理
        </small>
    </h1>
</div>

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<div class="row">
			<div class="col-xs-12">
				<table id="simple-table" class="table  table-bordered table-hover">
					<thead>
						<tr>
							<th width="41px">属性名称</th>
							<th width="90px">属性值</th>
							<th width="94px"></th>
						</tr>
					</thead>
					<tbody>

					</tbody>
				</table>
			</div>
			<!-- /.span -->
		</div>
		<!-- /.row -->
	</div>
	<!-- /.span -->
</div>

<div id="edit-div" class="hide">
	<form id="edit-form" class="form-horizontal" role="form"
		enctype="multipart/form-data" method="post">
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-input-readonly"> 属性名称： </label>

			<div class="col-sm-9">
				<input readonly="" type="text" class="col-xs-10 col-sm-5"
					id="form-input-readonly" value="" /> </span>
			</div>
		</div>
		<div class="form-group has-success">
			<label class="col-sm-3 control-label no-padding-right" for="name">
				属性值： </label>
			<div class="col-sm-9">
				<span class="block input-icon input-icon-right"> <input
					type="text" id="value" name="value" class="form-control" autocomplete="off"/> <i
					class="ace-icon fa fa-times-circle"
					style="display: none; color: red;"></i> <input type="hidden"
					name="_method" value="PUT" />
				</span>
			</div>
		</div>
	</form>
</div>

<%@ include file="../include/admin/footer.jsp"%>

<script type="text/javascript">
jQuery(function($) {
    
    url = window.location.href + 's';
    getData();

    //override dialog's title function to allow for HTML titles
    $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
        _title: function(title) {
            var $title = this.options.title || '&nbsp;'
            if(("title_html" in this.options) && this.options.title_html == true)
                title.html($title);
            else title.text($title);
        }
    }));

    $("#simple-table").on('click', '.m-edit-btn', function(e) {
        e.preventDefault();
        var id = $(this).attr("rel");
        $("#edit-form input[readonly]").prop('value', id);
        var dialog = $("#edit-div").removeClass('hide').dialog({
            modal: true,
            width: 450,
            title: "<div class='widget-header widget-header-small'><h4 class='smaller'>编辑</h4></div>",
            title_html: true,
            buttons: [{
                    text: "取消",
                    "class": "btn btn-minier",
                    click: function() {
                        $("#edit-form").clearForm();
                        $(this).dialog("close");
                    }
                },
                {
                    text: "提交",
                    "class": "btn btn-primary btn-minier",
                    click: function() {
                        if(editValidate()) {

                            $("#edit-form .form-group").removeClass('has-error').addClass('has-success');
                            $("#edit-form").ajaxSubmit({
                                url: url + "/" + id,
                                type: 'post',
                                success: function(data) {
                                    getData();
                                },
                                clearForm: true
                            });
                            
                            $(this).dialog("close");
                        } else {
                            $("#edit-form .form-group").removeClass('has-success').addClass('has-error');
                            return false;
                        }
                    }
                }
            ]
        });

    });
})
</script>
<script type="text/javascript"> 
	function getData() {
		var productUrl = url.substring(0, url.lastIndexOf("/"));
		$.get(productUrl, function(data, status){
	        $('.m-product-title').text(data.name);
	        $('.m-product-title').attr("href", productUrl.substring(0, url.indexOf("s/")));
	    })
		
	    clearDataDom();
	    var cid = window.location.pathname.match(/[0-9]+/i)
	    $.get('../admin/categorys/' + cid, function(data, status){
	        $('.m-category-name').text(data.name);
	    })
	    
	    $.get(url, function(list) {
	        for(var i in list) {
	            $('#simple-table tbody').append('<tr> <td>' +
	                list[i].property.name + '</td>' +
	                '<td>' + list[i].value + '</td>' +
	                '<td><div class="hidden-sm hidden-xs btn-group">' +
	                '<button class="btn btn-xs btn-info m-edit-btn" rel="' + list[i].id + '"><i class="ace-icon fa fa-pencil bigger-120"></i></button>' +
	                '</div>' +
	                '</td></tr>');
	
	        }
	    });
	}
	 var validateParam = {
	            rules: {
	                name: {
	                    required: true,
	                    rangelength: [2, 10]
	                }
	            },
	            messages: {
	                name: {
	                    required: "属性名不能为空",
	                    rangelength: "请输入 2 到 10 个 字符"
	                }
	            }
	    }
</script>

<%@ include file="../include/admin/end.jsp"%>