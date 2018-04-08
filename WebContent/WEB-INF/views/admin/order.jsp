<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/admin/header.jsp"%>
<%@ include file="../include/admin/navigator.jsp"%>

<title>订单管理</title>

<div class="page-header">
    <h1>
        分类管理 <small>
            <button id="addButton" class="btn btn-minier  btn-success">
                <i class="glyphicon glyphicon-plus"></i> 新增
            </button>
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
                            <th width="41px">ID</th>
                            <th class="detail-col">订单详情</th>
                            <th width="90px">订单编号</th>
                            <th width="90px">地址</th>
                            <th width="90px">邮编</th>
                            <th width="90px">收件人</th>
                            <th width="90px">电话</th>
                            <th width="90px">用户</th>
                            <th width="90px">用户消息</th>
                            <th width="90px">状态</th>
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

<%@ include file="../include/admin/page.jsp"%>

<div id="delivery-div" class="hide">
    <div class="col-sm-9">确认发货？</div>
</div>

<%@ include file="../include/admin/footer.jsp"%>

<script type="text/javascript">
            jQuery(function($) {

                getData();

                $(".pagination").on('click', 'li a', function() {
                    var parentDom = $(this).closest('li');
                    if(!parentDom.hasClass('disabled') && !parentDom.hasClass('active')) {
                        $.cookie('o-pageParam', $(this).attr('href'));
                        getData();
                        
                    }
                    return false;
                })

                //override dialog's title function to allow for HTML titles
                $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
                    _title: function(title) {
                        var $title = this.options.title || '&nbsp;'
                        if(("title_html" in this.options) && this.options.title_html == true)
                            title.html($title);
                        else title.text($title);
                    }
                }));

                $("#simple-table").on('click', '.m-delivery-btn', function(e) {
                    e.preventDefault();
                    
                    var id = $(this).attr("rel");
                    
                    var dialog = $("#delivery-div").removeClass('hide').dialog({
                        modal: true,
                        width: 450,
                        title: "<div class='widget-header widget-header-small'><h4 class='smaller'>发货</h4></div>",
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
                                            url: '../admin/orders/' + id + '/delivery',
                                            type: 'POST',
//                                           data: {
//                                                _method: 'PUT'
//                                           },
                                            success:　function(){
                                                getData();
                                            }
                                        });
                                        $(this).dialog("close");
                                }
                            }
                        ]
                    });

                });
                
                $("#simple-table").on('click', '.show-details-btn', function(e) {
                	e.preventDefault();
                	var $detailTr = $(this).closest('tr').next();
                	var id =  $(this).attr('rel');
                	if (!$detailTr.hasClass('open')){
                		$.get('../admin/orders/' + id + '/items', function(list, status) {
                			var detail = "";
                            for(var i in list) {
                               detail += '<tr>' +
                                    '<td>' + list[i].id + '</td>' +
                                    '<td>' + list[i].product.name + '</td>' +
                                    '<td>' + list[i].product.promotePrice + '</td>' +
                                    '<td>' + list[i].number + '</td>' +
                                    '<td>' + list[i].prices + '</td>' +
                                    '</tr>'
                            }
                            $detailTr.find('#detail-table tbody').html(detail);
                        });
                	}
                	console.log($detailTr.find('#detail-table tbody').html());
                	$detailTr.toggleClass('open');
                    $(this).find(ace.vars['.icon']).toggleClass('fa-angle-double-down').toggleClass('fa-angle-double-up');
                });
                
            })
        </script>
<script type="text/javascript"> 
    function getData() {
        clearDataDom();
        var param = $.cookie('o-pageParam');
        if (!param)  param =  "";
        $.get('../admin/orders' + param, function(data, status) {
            var list = data.list;
            var $dataDom = "";
            for(var i in list) {
                $dataDom +='<tr>' +
                    '<td>' + list[i].id + '</td>' +
                    '<td class="center"><div class="action-buttons"><a href="#" class="green bigger-140 show-details-btn" rel="'+ list[i].id +'" title="Show Details"><i class="ace-icon fa fa-angle-double-down"></i><span class="sr-only">Details</span></a></div> </td>' +
                    '<td>' + list[i].orderCode + '</td>' +
                    '<td>' + list[i].address + '</td>' +
                    '<td>' + list[i].post + '</td>' +
                    '<td>' + list[i].receiver + '</td>' +
                    '<td>' + list[i].mobile + '</td>' +
                    '<td>' + list[i].user.name + '</td>' +
                    '<td>' + list[i].userMessage + '</td>' +
                    '<td>' + list[i].status + 
                    ((list[i].status === "待发货") ? ('<button class="btn btn-xs btn-info m-delivery-btn" rel="'+ list[i].id +'">发货</button>') : '') +
                    '</td></tr>' +
                    '<tr class="detail-row"><td colspan="14">' + 
                    '<div class="row"><div class="col-sm-8 col-sm-offset-1"><div class="tabbable">' +
                    '<ul class="nav nav-tabs" id="myTab">' +
                    '<li class="active"><a data-toggle="tab" href="#time' + list[i].id + '">' +
                    '时间 </a></li><li><a data-toggle="tab" href="#detail' + list[i].id + '">订单项' +
                    '</a></li></ul><div class="tab-content">' +
                    '<div id="time' + list[i].id + '" class="tab-pane fade in active">' +
                    '<table class="table table-bordered table-hover">' +
                    '<tr><td width="100px">创建时间</td><td>' + new Date(list[i].createDate).format("yyyy-MM-dd hh :mm:ss") + '</td></tr>' +
                    '<tr><td width="100px">支付时间</td><td>' + new Date(list[i].payDate).format("yyyy-MM-dd hh :mm:ss") + '</td></tr>' +
                    '<tr><td width="100px">发货时间</td><td>' + new Date(list[i].deliveryDate).format("yyyy-MM-dd hh :mm:ss") + '</td></tr>' +
                    '<tr><td width="100px">收货时间</td><td>' + new Date(list[i].confirmDate).format("yyyy-MM-dd hh :mm:ss") + '</td></tr>' +
                    '<tr><td width="100px">评论时间</td><td>' + new Date(list[i].reviewDate).format("yyyy-MM-dd hh :mm:ss") + '</td></tr>' +
                    '</table></div><div id="detail' + list[i].id + '" class="table-detail tab-pane fade">' +
                    '<table id="detail-table" class="table  table-bordered table-hover">' +
                    '<thead><tr><th width="41px">ID</th><th width="90px">产品名称</th><th width="90px">产品价格</th><th width="90px">数量</th><th width="90px">总价格</th></tr></thead>' +
                    '<tbody></tbody></table></div></div></div></div>';
             }
            $('#simple-table tbody').html($dataDom);
            pageDom(data);
        });
    }
    

        var validateParam = {
                rules: {
                },
                messages: {
                }
        }
        </script>

<%@ include file="../include/admin/end.jsp"%>