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
    <link rel="stylesheet" type="text/css" href="./css/bootstrap/4.4.1/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="./css/font-awesome/css/all.css" />
    <link rel="stylesheet" type="text/css" href="./css/main.css" />

</head>
<body>

<nav class="navbar navbar-expand-xl navbar-light fixed-top">
    <a class="navbar-brand" href="./">COVID Pulse</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon mb-1"></span>
    </button>

    <a href="./javadoc/index.html" class="btn btn-secondary" type="submit"><img src="./images/Java.png" alt="JavaDoc" style="height: 30px; width: auto; margin-right: 0.5em" />JavaDoc</a>

</nav>

<main class="container bg-light">

    <section class="h-100">

        <h1>COVID Pulse Endpoint Documentation</h1>

        <p>With the current COVID-19 crisis engulfing the world and rapidly spreading across the US, there is a lot of
            data available surrounding case counts and outcomes. That said, a lot of this data currently comes only in
            a raw form. With this API, we aim to expose endpoints that software developers can use to programmatically
            access COVID-19 case counts by county and state in the US, so that this crucial data can be easily integrated
            into any application.</p>

        <h2>County Lookups</h2>

        <h3>All Cases</h3>
        <p>Use this endpoint to obtain COVID-19 case data from all states and counties.</p>

        <h4>URL</h4>

        <kbd>/api/counties?startDate=<var>startDate</var>&endDate=<var>endDate</var></kbd>

        <h4>Request Method:</h4>

        <code>GET</code>

        <h4>URL Params</h4>

        <strong>Optional:</strong><br />

        <var>startDate</var> = <code>String</code> - <em class="example">Example: yyyy-MM-dd</em><br />
        <var>endDate</var> = <code>String</code> - <em class="example">Example: yyyy-MM-dd</em>


        <h4>Success Response:</h4>
        <strong>Status Code:</strong> <code>200</code><br />
        <strong>Response Type:</strong> <code>JSON</code><br />
        <code>
            [
                {
                    "county_fips": "53061",
                    "county": "Snohomish",
                    "data": [
                        {
                            "date": "2020-02-29",
                            "cases": 2,
                            "deaths": 0
                        }
                    ]
                },
                {
                    "county_fips": "53719",
                    "county": "Dane",
                    "data": [
                        {
                            "date": "2020-03-01",
                            "cases": 3,
                            "deaths": 0
                        }
                    ]
                }
            ]
        </code>

        <h4>Error Response:</h4>
        <strong>Status Code:</strong> <code>422 UNPROCESSABLE ENTRY</code><br />
        <strong>Response Type:</strong> <code>JSON</code>

        <code>
            {
            error : "Unable to process your request at this time."
            }
        </code>


        <h4>Sample Call:</h4>

        <kbd>/api/counties</kbd><br />
        <kbd>/api/counties/startDate=03-30-2020&endDate=03-31-2020</kbd>


        <h3 class="mt-5">Specific County</h3>
        <p>Use this endpoint to obtain COVID-19 case data for a specific county.</p>

        <h4>URL</h4>

        <kbd>/api/counties/<var>fipsCountyId</var>?startDate=<var>startDate</var>&endDate=<var>endDate</var></kbd>

        <h4>Request Method:</h4>

        <code>GET</code>

        <h4>URL Params</h4>

        <strong>Optional:</strong><br />

        <var>fipsCountyId</var> = <code>Integer</code> - <em class="example">Example: 123</em><br />
        <var>startDate</var> = <code>String</code> - <em class="example">Example: yyyy-MM-dd</em><br />
        <var>endDate</var> = <code>String</code> - <em class="example">Example: yyyy-MM-dd</em>


        <h4>Success Response:</h4>
        <strong>Status Code:</strong> <code>200</code><br />
        <strong>Response Type:</strong> <code>JSON</code><br />
        <code>
            [
                {
                    "county_fips": "53061",
                    "county": "Snohomish",
                    "data": [
                        {
                            "date": "2020-02-29",
                            "cases": 2,
                            "deaths": 0
                        },
                        {
                            "date": "2020-03-01",
                            "cases": 3,
                            "deaths": 0
                        }
                    ]
                }
            ]
        </code>

        <h4>Error Response:</h4>
        <strong>Status Code:</strong> <code>422 UNPROCESSABLE ENTRY</code><br />
        <strong>Response Type:</strong> <code>JSON</code>

        <code>
            {
            error : "Invalid FIPS ID"
            }
        </code>


        <h4>Sample Call:</h4>

        <kbd>/api/counties/55025?startDate=03-30-2020&endDate=03-31-2020</kbd>



        <h2 class="mt-5">State Lookups</h2>

        <h3>All Cases</h3>
        <p>Use this endpoint to obtain COVID-19 case data from all states.</p>

        <h4>URL</h4>

        <kbd>/api/states?startDate=<var>startDate</var>&endDate=<var>endDate</var></kbd>

        <h4>Request Method:</h4>

        <code>GET</code>

        <h4>URL Params</h4>

        <strong>Optional:</strong><br />

        <var>startDate</var> = <code>String</code> - <em class="example">Example: yyyy-MM-dd</em><br />
        <var>endDate</var> = <code>String</code> - <em class="example">Example: yyyy-MM-dd</em>


        <h4>Success Response:</h4>
        <strong>Status Code:</strong> <code>200</code><br />
        <strong>Response Type:</strong> <code>JSON</code><br />
        <code>
            [
                {
                    "state": "HI",
                    "state_fips": "15",
                    "data": [
                        {
                            "date": "2020-03-06",
                            "cases": 1,
                            "deaths": 0
                        },
                        {
                            "date": "2020-02-05",
                            "cases": 1,
                            "deaths": 0
                        }
                    ]
                },
                {
                    "state": "WI",
                    "state_fips": "55",
                    "data": [
                        {
                            "date": "2020-02-05",
                            "cases": 1,
                            "deaths": 0
                        },
                        {
                            "date": "2020-03-06",
                            "cases": 1,
                            "deaths": 0
                        }
                    ]
                }
            ]
        </code>

        <h4>Error Response:</h4>
        <strong>Status Code:</strong> <code>422 UNPROCESSABLE ENTRY</code><br />
        <strong>Response Type:</strong> <code>JSON</code>

        <code>
            {
            error : "Unable to process your request at this time."
            }
        </code>


        <h4>Sample Call:</h4>

        <kbd>/api/states</kbd><br />
        <kbd>/api/states/startDate=03-30-2020&endDate=03-31-2020</kbd>


        <h3 class="mt-5">Specific State</h3>
        <p>Use this endpoint to obtain COVID-19 case data for a specific state.</p>

        <h4>URL</h4>

        <kbd>/api/states/<var>state</var>?startDate=<var>startDate</var>&endDate=<var>endDate</var></kbd>

        <h4>Request Method:</h4>

        <code>GET</code>

        <h4>URL Params</h4>

        <strong>Optional:</strong><br />

        <var>state</var> = <code>String</code> - <em class="example">Example: WI</em><br />
        <var>startDate</var> = <code>String</code> - <em class="example">Example: yyyy-MM-dd</em><br />
        <var>endDate</var> = <code>String</code> - <em class="example">Example: yyyy-MM-dd</em>


        <h4>Success Response:</h4>
        <strong>Status Code:</strong> <code>200</code><br />
        <strong>Response Type:</strong> <code>JSON</code><br />
        <code>
            [
                {
                    "state": "WI",
                    "state_fips": "55",
                    "data": [
                        {
                            "date": "2020-02-05",
                            "cases": 1,
                            "deaths": 0
                        }
                    ]
                }
            ]
        </code>

        <h4>Error Response:</h4>
        <strong>Status Code:</strong> <code>422 UNPROCESSABLE ENTRY</code><br />
        <strong>Response Type:</strong> <code>JSON</code>

        <code>
            {
            error : "State must be identified by it's two-character abbreviation"
            }
        </code>


        <h4>Sample Call:</h4>

        <kbd>/api/states/WI?startDate=03-30-2020&endDate=03-31-2020</kbd>

    </section>

</main>

<footer class="container-fluid fixed-bottom">

    <div class="row d-flex justify-content-around align-items-center">

        <p>COVID Pulse</p>

    </div>

</footer>

<script src="./js/jquery-3.4.1.min.js" type="text/javascript"></script>
<script src="./js/popper/popper.js" type="text/javascript"></script>
<script src="./css/bootstrap/4.4.1/js/bootstrap.min.js" type="text/javascript"></script>

</body>
</html>