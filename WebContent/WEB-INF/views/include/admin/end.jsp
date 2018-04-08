<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
        <script>
            function clearDataDom() {
                $('#simple-table tbody').empty();
                $('.pagination').empty();
            }

            function pageDom(data) {
                var pageDom = "";
                var disableClass = ' class="disabled"';
                var activeClass = ' class="active"';
                pageDom = '<li';
                if(!data.hasPreviousPage)
                    pageDom += disableClass;
                pageDom += '><a href="?p=1"><span aria-hidden="true">&laquo;</span></a></li><li'
                if(!data.hasPreviousPage)
                    pageDom += disableClass;
                pageDom += '><a href="?p=' + data.prePage + '" aria-label="end"><span aria-hidden="true">&lsaquo;</span></a></li>';
                for(var i in data.navigatepageNums) {
                    var n = data.navigatepageNums[i];
                    pageDom += '<li';
                    if(n == data.pageNum)
                        pageDom += activeClass;
                    pageDom += '><a href="?p=' + n + '">' + n + '</a></li>'
                }
                pageDom += '<li';
                if(!data.hasNextPage)
                    pageDom += disableClass;
                pageDom += '><a href="?p=' + data.nextPage + '" aria-label="end"><span aria-hidden="true">&rsaquo;</span></a></li><li'
                if(!data.hasNextPage)
                    pageDom += disableClass;
                pageDom += '><a href="?p=' + data.pages + '" aria-label="end"><span aria-hidden="true">&raquo;</span></a></li>';

                $('.pagination').append(pageDom);
            }

            $.validator.setDefaults({
                submitHandler: function() {}
            });

            
            function addValidate() {
                return $('#add-form').validate(validateParam).form();
            }
                function editValidate() {
                    return $('#edit-form').validate(validateParam).form();
            }
        </script>
    </body>

</html>