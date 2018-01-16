<%--
  Created by IntelliJ IDEA.
  User: milko.mitropolitsky
  Date: 11/29/17
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
   <h1>Welcome to the Application...</h1>
   <button onclick="window.location.href='/api/weapons/download'">Download</button><br><br>

   <form action="/api/weapons/upload" enctype="multipart/form-data" name="upload-form" method="post">
     File: <input type="file" name="csv"/>
     <input type="submit" value="Upload"/>
   </form>
  </body>
</html>
