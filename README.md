# spring-r2dbc-master


localhost:8080/timemetric (POST)


Request Body 

{
    "temperature": 18,
    "datetime": "2019-11-11 11:11"
}

Response Body  200 Ok


localhost:8080/timemetric/rangohora?fechaIni=2019-10-13 11:11&fechaFin=2019-12-25 23:11 (GET)


Response Body 
[
    {
        "time": "11:11 - 23:11",
        "min": 12,
        "max": 18,
        "average": 14.666666666666666
    }
]



localhost:8080/timemetric/rangodia?fecha=2019-10-13  (GET)
Response Body
{
    "date": "2019-10-13",
    "min": 18,
    "max": 40,
    "average": 29.0
}
