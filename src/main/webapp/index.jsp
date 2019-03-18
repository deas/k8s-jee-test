<%@ page import="de.contentreich.ApplicationFilter" %>
<%@ page session = "false" %>
<html>
<body>
<h2><%= request.getAttribute(ApplicationFilter.ATTR_MESSAGE) %></h2>
</body>
</html>
