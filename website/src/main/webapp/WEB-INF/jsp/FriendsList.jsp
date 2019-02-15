<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="c" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ally_World</title>
</head>
<body>
<jsp:include page="navigation.jsp" />
 <c:form action="update" modelAttribute="profile">
        <h1>${message}</h1>
        <table>
            
            <jstl:forEach var="friendsList" items="${friendsList}">
                <tr>
                <td><c:input type="text" path="profileId" value="${friendsList.fullName}" readOnly="readonly"/></td>
                   
                </tr>
            </jstl:forEach>
        </table>
        <input type="submit" value="Submit">
        <input type="reset" value="reset">
        <br>
    </c:form>
</body>
</html>