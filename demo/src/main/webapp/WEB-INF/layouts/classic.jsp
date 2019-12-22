<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
        <meta charset="utf-8">
        <title><tiles:getAsString name="title" /></title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <style type="text/css">
            td {
                vertical-align: top;
               }
            nav{
                }
                body{
                    background-image: url("${pageContext.request.contextPath}/images/bg.jpg");
                    }
            ul.my {
              list-style-type: none;
              margin: 0;
              padding: 0;
              width: 270px;
              background-color: #f1f1f1;
              border: 1px solid #555;
            }

            li.my a {
              display: block;
              color: #000;
              padding: 8px 16px;
              text-decoration: none;
            }

            li.my {
              text-align: left;
              border-bottom: 1px solid #555;
            }

            li.my:last-child {
              border-bottom: none;
            }

            li.my a.active {
              background-color: #9ae890;
              color: white;
            }

            li.my a:hover:not(.active) {
              background-color: #63e851;
              color: white;
            }

        </style>


</head>

<body>
    <table width="100%">
        <tr>
            <td colspan="2">
                <tiles:insertAttribute name="header" />
            </td>
        </tr>
        <tr>
            <td width="20%" nowrap="nowrap" >
                 <tiles:insertAttribute name="menu" />
             </td>
            <td width="80%">
                 <tiles:insertAttribute name="body" />
             </td>
        </tr>
        <tr>
            <td colspan="2">
                 <tiles:insertAttribute name="footer" />
            </td>
        </tr>
    </table>
</body>
</html>