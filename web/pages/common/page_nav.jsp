<%--
  Created by IntelliJ IDEA.
  User: DCH
  Date: 7/8/2020
  Time: 10:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--paging--%>
<div id="page_nav">
    <%--more than 1 page will show--%>
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=1">First Page</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">Previous Page</a>
    </c:if>


    <%--bigin of page--%>
    <c:choose>
        <%--case1：if pagetotal is less than 5，range：1-pagetotal--%>
        <c:when test="${requestScope.page.pageTotal <= 5}">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${requestScope.page.pageTotal}"/>
            <%--                    <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">--%>
            <%--                        <c:if test="${i == requestScope.page.pageNo}">--%>
            <%--                            【${i}】--%>
            <%--                        </c:if>--%>
            <%--                        <c:if test="${i != requestScope.page.pageNo}">--%>
            <%--                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>--%>
            <%--                        </c:if>--%>
            <%--                    </c:forEach>--%>
        </c:when>

        <%--case2：pagetotal > 5--%>
        <c:when test="${requestScope.page.pageTotal > 5}">
            <c:choose>
                <%--subcase1：current page：1，2，3，range：1-5--%>
                <c:when test="${requestScope.page.pageNo <= 3}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:when>

                <%--subcase2：current page:8，9，10，range：pagetotal-4 - pagetotal--%>
                <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
                    <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                    <c:set var="end" value="${requestScope.page.pageTotal}"/>
                </c:when>

                <%--subcase3：4，5，6，7，range：currentpage-2 - currentpage+2--%>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo-2}"/>
                    <c:set var="end" value="${requestScope.page.pageNo+2}"/>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i == requestScope.page.pageNo}">
            【${i}】
        </c:if>
        <c:if test="${i != requestScope.page.pageNo}">
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>



    <%--page--%>

    <%--<a href="#">3</a>--%>
    <%--【${requestScope.page.pageNo}】--%>
    <%--<a href="#">5</a>--%>

    <%--if it's the last page，no next page or last page--%>
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">Next Page</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">Last Page</a>
    </c:if>

    Total${ requestScope.page.pageTotal }Pages，${ requestScope.page.pageTotalCount }Auction Items
    To<input value="${param.pageNo}" name="pn" id="pn_input"/>Page
    <%--requestScope.page.pageNo--%>

    <input id="searchPageBtn" type="button" value="Confirm">

    <script type="text/javascript">
        $(function () {
            //jump to the page
            $("#searchPageBtn").click(function () {
                var pageNo = $("#pn_input").val();

                //var pageTotal = ${requestScope.page.pageTotal};
                //if (pageNo <= pageTotal && pageNo >=1){
                //javaScript apply location addr attr
                //href attr is available for reading & writing
                location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;
                //}
            })
        });
    </script>

</div>

