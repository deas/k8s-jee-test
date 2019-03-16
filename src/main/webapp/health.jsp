<%@ page import="java.util.Date" %>
<%@ page session = "false" %>
<%
    String healthString = request.getParameter("set");
    Boolean healthy;
    if (healthString != null) {
        healthy = Boolean.parseBoolean(healthString);
        application.setAttribute("healthy", healthy);
        System.out.println(new Date() + " Set healthy " + healthy);
    } else {
        healthy = (Boolean) application.getAttribute("healthy");
    }
    if (!healthy) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
%>
<html>
<body>
<h2>Hello World! sleep =  <%= request.getParameter("sleep")%>, healthy = <%= healthy %></h2>
</body>
</html>
