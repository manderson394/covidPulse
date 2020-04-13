<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="json" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ include file="template/header.jsp" %>

<section class="row align-items-center h-100">

    <c:choose>

        <c:when test="${fn:length(cases) gt 0}">

            <c:out value="${fn:length(cases[0])}" />
            ${cases[0]['county']}
            <c:out value="${fn:length(cases[0]['data'])}" />

            ${cases[0]['data'][0]['date']}

            <c:forEach var="data" items="${cases[0]['data']}" varStatus="loopCount">
                <tr>

                </tr>
            </c:forEach>

        </c:when>
        <c:otherwise>
            No One Found
        </c:otherwise>

    </c:choose>


</section>




<%@ include file="template/footer.jsp" %>