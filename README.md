In the company's e-commerce database, we have the "PRICES" table, which reflects the final price (retail price) and the rate that applies to a product of a brand within certain date ranges. Below is an example of the table with the relevant fields:

### PRICES TABLE

BRAND_ID  | START_DATE  | END_DATE | PRICE_LIST   | PRODUCT_ID | PRIORITY  | PRICE | CURR
--------- | ----------- | -------- | ----------   | ---------- | --------  | ----- | --------  | 
1 | 2020-06-14-00.00.00 |  2020-12-31-23.59.59  | 1  | 35455  | 0 |  35.50  | EUR
1 | 2020-06-14-15.00.00  | 2020-06-14-18.30.00  | 2  | 35455  | 1  | 25.45  | EUR
1 | 2020-06-15-00.00.00  | 2020-06-15-11.00.00  | 3  | 35455  | 1  | 30.50  | EUR
1 | 2020-06-15-16.00.00  | 2020-12-31-23.59.59  | 4  | 35455  | 1  | 38.95  | EUR


### Fields

- BRAND_ID: A foreign key representing the brand group.
- START_DATE , END_DATE: Date range during which the specified rate applies.
- PRICE_LIST: Identifier of the applicable price rate.
- PRODUCT_ID: Product code identifier.
- PRIORITY: Priority of prices.
- PRICE: Final retail price.
- CURR: ISO currency code.

## Requirements

Build a Spring Boot application/service that provides a REST endpoint for querying as follows:

Accepts the following input parameters: application date, product identifier, brand identifier.
Returns the following output data: product identifier, brand identifier, applicable rate, application dates, and final price to be applied.

You should use an in-memory database (such as H2) and initialize it with the example data (you can change the field names and add new ones if necessary, choose the appropriate data types).

Develop tests for the REST endpoint to validate the following requests with the example data:

-  Test 1: Request at 10:00 on day 14 for product 35455 for brand 1
-  Test 2: Request at 16:00 on day 14 for product 35455 for brand 1
-  Test 3: Request at 21:00 on day 14 for product 35455 for brand 1
-  Test 4: Request at 10:00 on day 15 for product 35455 for brand 1
-  Test 5: Request at 21:00 on day 16 for product 35455 for brand 1


## Guides
The following guides show how to use some features concretely:
* Use postman file and Postman App to call the application (Prices.postman_collection.json file)
* Use this url to access to Swagger and test the API: [Swagger-ui](http://localhost:8080/swagger-ui/index.html)
* Use this url to access to H2 Database: [h2_database](http://localhost:8080/h2)
