# data-tracker

 Tracking metrics over time.  

## Built With
- Java
- Spring Boot
- Spring Scheduler
- Spring Data JPA
- Maven

## Starting the app on localhost

Run:
    `./mvnw clean spring-boot:run`

App will be running in http://localhost:8080/

## Rest Api Methods

- Get list of metrics currently tracked:
    HTTP GET /api/metrics

- Get values for a specific metric:
    HTTP GET /api/metrics/{id}/quotes?from={timeInEpoch}&to={timeInEpoch}

- Get rank for a specific metric:
    HTTP GET /api/metrics/{id}/rank


# Project Structure

```
.
|-- src                      
|   |-- main                   
|   |   |-- ...                
|   |   |-- controller             
|   |   |   |-- MetricController.java            
|   |   |-- model
|   |   |   |-- Metric.java
|   |   |   |-- MetricValue.java
|   |   |-- repository                
|   |   |   |-- MetricRepository.java
|   |   |   |-- MetricValueRepository.java
|   |   |-- service                 
|   |   |   |-- MetricService.java           
|   |   |-- tasks        
|   |   |   |-- MetricTasks.java           
|   |-- test                    
|-- ...
|-- README.md
```

# Implemented solution and potential enhancements. 

The implementation is basically a service, exposed in a REST API. 

## The model
    - Metric
        name
        code
        
    - MetricValue
        MetricId
        Timestamp
        Value

## The database
In this case I just used a relational in memory database as a first approach to have soemthing to test, but that definitely needs to be improved.
A better approach could be to use a Time-series database because we are actually tracking how an asset changes over time.

## Reading metric every minute
I used Spring Scheduler as an easy way to run a job in a fixed interval of time.
In production environment we should probably be using Quartz or similar framework as a distributed job scheduler to better scaling support. Should also help in monitoring, reporting, clustering, etc.

## Calculating Rank.
Wasnt totally sure about the requirement...
Assumptions:
    - The stdev is calculated in a daily basis, based on the data of the past day.
    - The rank (1/3) is:
         Index of the metric ordering by stdev / Total of metrics.

Didn´t have time to finish this part, but the idea was to create a job that runs once a day, reads metric values for the day and persist the stdev for the metric.
Then when rank is requested, query the metrics ordered by stdev.


## Questions

### Scalability
what would you change if you needed to track many metrics? What if you needed to sample them more frequently?

- We'll need to run the sampling function in a way we can easily escalate and support sample metrics in parallel. Quartz or AWS Lambda Functions.

- We may want to improve how metric values are saved.
    - In nosql-db, maybe a document per metric and day.
    - If sqldb, maybe a table per metric or group of metrics. For instance a table for btc, saving in a row timestamp, price and volume. (2 metrics)
    - Define policies to archive data to cheap storage, maintainig recent data with faster access.
   (This is proably automatically supported in Timeseries Db)
 
 - what if you had many users accessing your dashboard to view metrics?
    We should probably cache metric values as they are the same for all users. 

### Testing
- how would you extend testing for an application of this kind (beyond what you implemented)?

We should have:
    * Unit Tests.
    * Repositories tests, validating data structure.
    * End to end tests, invoking controllers and mocking external services.
    * Load / Performance tests.


### Feature Request
- To help the user identify opportunities in real-time, the app will send an alert whenever a metric exceeds 3x the value of its average in the last 1 hour. For example, if the volume of GOLD/BTC averaged 100 in the last hour, the app would send an alert in case a new volume data point exceeds 300. Please write a short proposal on how you would implement this feature request.

When a new datapoint is generated an event is rised (or every few seconds if datapoints are too close in time). A process listening the event reads last hour values and once the case is identified, send a notification.

Timestream Db, Lambda functions and Amazon SNS could be a solution.


# TODOs

Some TODOs in case we want to polish current implementation a bit

- Move to configurable properies some constants currently hardcoded inside code.
 
- REST API:
    * Validate input 
    * Add error management
    * Implement Paging in get values api method.

