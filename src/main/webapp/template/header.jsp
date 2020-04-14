<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>COVID Pulse</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap/4.4.1/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome/css/all.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css" />

</head>
<body class="bg-light">



<nav class="navbar navbar-expand-xl navbar-light fixed-top">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">COVID Pulse</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon mb-1"></span>
    </button>
    <!--
    <div class="row collapse navbar-collapse my-2" id="navbarsExampleDefault">
        <div class="col-md-2 d-flex justify-content-start align-items-center"></div>
        <div id="searchDiv" class="col-md-8">
            <form action="./" class="row my-2 mx-2 form-inline" method="post">
                <label for="geographic">Geographic: </label>
                <select id="geographic" name="geographic">
                    <option value="counties">County</option>
                    <option value="states">State</option>
                </select>

                <input class="form-control" id="searchBox" name="searchBox" type="text" placeholder="Enter County FIPS or State Abbreviation" value="53061" aria-label="Search">

                <label for="startDate">Start Date: </label>
                <input type="date" class="form-control" id="startDate" name="startDate" value="2020-03-01" min="2019-01-01" max="2020-12-31" />
                <label for="endDate">End Date: </label>
                <input type="date" class="form-control" id="endDate" name="endDate" value="2020-03-30" min="2019-01-01" max="2020-12-31" />

                <button class="form-control btn btn-secondary" type="submit">Search</button>

            </form>
        </div>
        <div class="col-md-2 d-flex justify-content-end align-items-center"></div>
    </div>
    -->
</nav>


<main class="container p-2">