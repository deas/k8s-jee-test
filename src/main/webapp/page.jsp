<%@ page import="java.util.Date" %>
<%@ page session = "false" %>
<%
    String responseTime = System.getenv("TEST_REPONSE_TIME");
    if (responseTime != null) {
        Long delay = Long.parseLong(responseTime);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    System.out.println(new Date() + " page done waiting " + responseTime + "ms");
%>
<html>
<body>
<h2>Hello World in <%= responseTime%>ms</h2>
</body>
</html>
