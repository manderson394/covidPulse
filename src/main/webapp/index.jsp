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

        <p>
            The data from this service is in JSON format (examples below). Each object in the
            "data" array represents the cumulative confirmed cases and deaths reported on that date.
        </p>

        <p>
            Huge thanks to the NYTimes for providing the raw data. It can be found <a href="https://github.com/nytimes/covid-19-data">here</a>.
        </p>

        <p>
            <b>Base URL: </b><kbd>http://3.21.91.2:8080/covidPulse/api</kbd>
        </p>

        <h2>County Lookups</h2>

        <h3>All Cases</h3>
        <p>Use this endpoint to obtain COVID-19 case data from US counties.</p>
        <p>Geographic Exceptions</p>
        <ul>
            <li>Boroughs of New York City, NY are not included, as these were lumped together and have no FIPS code</li>
            <li>Cass, Clay, Jackson, and Platte counties (MO) have counts exclusive of Kansas City.</li>
            <li>Counts for Alameda County, CA include cases and deaths from Berkeley and the Grand Princess cruise ship</li>
            <li>Counts for Douglas County, NE include cases brought to the state from the Diamond Princess cruise ship.</li>
            <li>Chicago counts are included as part of Cook county</li>
            <li>Any cases reported with an Unknown FIPS code are not included</li>
        </ul>

        <h4>URL</h4>

        <kbd>/counties?startDate=<var>startDate</var>&endDate=<var>endDate</var></kbd>

        <h4>Request Method:</h4>

        <code>GET</code>

        <h4>URL Params</h4>

        <strong>Optional:</strong><br />

        <var>startDate</var> = <code>String</code> - <em class="example">REQUIRED FORMAT: yyyy-MM-dd</em><br />
        <var>endDate</var> = <code>String</code> - <em class="example">REQUIRED FORMAT: yyyy-MM-dd</em>


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
        <strong>Status Code:</strong> <code>500 Internal Server Error</code><br />
        <strong>Response Type:</strong> <code>JSON</code>

        <code>
            {
            error : "Unable to process your request at this time."
            }
        </code>


        <h4>Sample Call:</h4>

        <kbd>/counties</kbd><br />
        <kbd>/counties/startDate=2020-03-30&endDate=2020-03-31</kbd>


        <h3 class="mt-5">Specific County</h3>
        <p>
            Use this endpoint to obtain COVID-19 case data for a specific county.
            Counties must be fetched by their 5-digit FIPS code (2-digit state + 3-digit county).
            See <a href="https://www.census.gov/geographies/reference-files/2018/demo/popest/2018-fips.html">2018 FIPS Codes</a>
            for more information
        </p>

        <h4>URL</h4>

        <kbd>/counties/<var>fipsCountyId</var>?startDate=<var>startDate</var>&endDate=<var>endDate</var></kbd>

        <h4>Request Method:</h4>

        <code>GET</code>

        <h4>URL Params</h4>

        <strong>Optional:</strong><br />

        <var>fipsCountyId</var> = <code>Integer</code> - <em class="example">REQUIRED FORMAT: 12345</em><br />
        <var>startDate</var> = <code>String</code> - <em class="example">REQUIRED FORMAT: yyyy-MM-dd</em><br />
        <var>endDate</var> = <code>String</code> - <em class="example">REQUIRED FORMAT: yyyy-MM-dd</em>


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
        <strong>Status Code:</strong> <code>500 Internal Server Error</code><br />
        <strong>Response Type:</strong> <code>JSON</code>

        <code>
            {
            error : "Unable to process your request at this time."
            }
        </code>


        <h4>Sample Call:</h4>

        <kbd>/counties/55025?startDate=2020-03-30&endDate=2020-03-31</kbd>



        <h2 class="mt-5">State Lookups</h2>

        <h3>All Cases</h3>
        <p>Use this endpoint to obtain COVID-19 case data from every state.</p>

        <h4>URL</h4>

        <kbd>/states?startDate=<var>startDate</var>&endDate=<var>endDate</var></kbd>

        <h4>Request Method:</h4>

        <code>GET</code>

        <h4>URL Params</h4>

        <strong>Optional:</strong><br />

        <var>startDate</var> = <code>String</code> - <em class="example">REQUIRED FORMAT: yyyy-MM-dd</em><br />
        <var>endDate</var> = <code>String</code> - <em class="example">REQUIRED FORMAT: yyyy-MM-dd</em>


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
        <strong>Status Code:</strong> <code>500 Internal Server Error</code><br />
        <strong>Response Type:</strong> <code>JSON</code>

        <code>
            {
            error : "Unable to process your request at this time."
            }
        </code>


        <h4>Sample Call:</h4>

        <kbd>/states</kbd><br />
        <kbd>/states?startDate=2020-03-30&endDate=2020-03-31</kbd>


        <h3 class="mt-5">Specific State</h3>
        <p>Use this endpoint to obtain COVID-19 case data for a specific state. States must be referenced
        by their 2-letter abbreviation, in capital letters.</p>

        <h4>URL</h4>

        <kbd>/states/<var>state</var>?startDate=<var>startDate</var>&endDate=<var>endDate</var></kbd>

        <h4>Request Method:</h4>

        <code>GET</code>

        <h4>URL Params</h4>

        <strong>Optional:</strong><br />

        <var>state</var> = <code>String</code> - <em class="example">REQUIRED FORMAT: WI</em><br />
        <var>startDate</var> = <code>String</code> - <em class="example">REQUIRED FORMAT: yyyy-MM-dd</em><br />
        <var>endDate</var> = <code>String</code> - <em class="example">REQUIRED FORMAT: yyyy-MM-dd</em>


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
        <strong>Status Code:</strong> <code>500 Internal Server Error</code><br />
        <strong>Response Type:</strong> <code>JSON</code>

        <code>
            {
            error : "Unable to process your request at this time."
            }
        </code>


        <h4>Sample Call:</h4>

        <kbd>/states/WI?startDate=2020-03-30&endDate=2020-03-31</kbd>

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