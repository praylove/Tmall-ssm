<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/admin/header.jsp"%>
<%@ include file="../include/admin/navigator.jsp"%>

<title>用户管理</title>

<div class="page-header">
    <h1>
            用户管理 
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
                            <th width="200px">用户名</th>
                            <th width="200px">用户密码</th>
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

<%@ include file="../include/admin/page.jsp"%>

<div id="delete-div" class="hide">
    <div class="col-sm-9">确认删除吗？</div>
</div>

<%@ include file="../include/admin/footer.jsp"%>

<script type="text/javascript">
            jQuery(function($) {

                getData();

                $(".pagination").on('click', 'li a', function() {
                    var parentDom = $(this).closest('li');
                    if(!parentDom.hasClass('disabled') && !parentDom.hasClass('active')) {
                        console.log('reload');
                        $.cookie('u-pageParam', $(this).attr('href'));
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


                $("#simple-table").on('click', '.m-delete-btn', function(e) {
                    e.preventDefault();
                    
                    var id = $(this).closest('tr').find('.id').text();
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
                                            url: '../admin/users/' + id,
                                            type: 'DELETE',
//                                          data: {
//                                              _method: 'DELETE'
//                                          },
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
            });
        </script>
<script type="text/javascript"> 
    function getData() {
        clearDataDom();
        var param = $.cookie('u-pageParam');
        if (!param)  param =  "";
        $.get('../admin/users' + param, function(data, status) {
            var list = data.list;
            for(var i in list) {
                $('#simple-table tbody').append('<tr> <td class="id">' +
                    list[i].id +'</td>' +
                    '<td>' + list[i].name + '</td>' +
                    '<td>' + list[i].password + '</td>' +
                    '<td><div class="hidden-sm hidden-xs btn-group">' +
                    '<button class="btn btn-xs btn-danger m-delete-btn"><i class="ace-icon fa fa-trash-o bigger-120"></i></button>' +
                    '</div>' +
                    '</td></tr>');
                }
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