# Team Project - COVID Pulse


### Problem Statement
With the current COVID-19 crisis engulfing the world and rapidly spreading across the US,
there is a lot of data available surrounding case counts and outcomes. That said, 
a lot of this data currently comes only in a raw form. With this API, we aim to expose endpoints
that software developers can use to programmatically access COVID-19 case counts by county and state
in the US, so that this crucial data can be easily integrated into any application.

### Project Plan


### Endpoint Documentation

#### Specific County


  Use this endpoint to obtain COVID-19 case data for a specific county. 

* **URL**

    /api/counties/{fipsCountyId}?startDate={startDate}&endDate={endDate}

* **Method:**
  
  `GET` 
  
*  **URL Params**

   **Optional:**
 
   `fipsCountyId=[integer FIPS ID]`
   `startDate=[MM-dd-yyyy]`
   `endDate=[MM-dd-yyyy]`


* **Success Response:**

  * **Code:** 200 <br />
    **Content:** <br />
        {"55025": "name": "Dane", "state": "WI", [{"date": "2020-03-30", "cases": "100"}]}]}
 
* **Error Response:**

  * **Code:** 422 UNPROCESSABLE ENTRY <br />
    **Content:** `{ error : "Invalid FIPS ID" }`

* **Sample Call:**

  /api/counties/55025?startDate=03-30-2020&endDate=03-31-2020