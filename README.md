# Scraper

## Purpose of this project
This project is a scraper for the efa bw "Abfahrtsmonitor". There you can get all departures for a station. This backend service scrapes the data from the following website:
https://www.efa-bw.de/nvbw/XSLT_DM_REQUEST?language=de&itdLPxx_frames=&itdLPxx_origin=

## Documentation
To get all departures of today for a specific station use the following URL of the REST-API:

http://localhost:5001/departures/{stationName}

{stationName} - Type in the station in form of a String

The result is a JSON Object with the following structure:

        [
            {
                "time": "11:50",
                "actualTime": "11:50",
                "transportationType": "Bus",
                "transportationNumber": "5",
                "destination": "Pforzheim ZOB / Hbf"
            }
        [
        
time:                   Planned time of departure
actualTime:             Actual time of departure
transportationType:     Type of transportation, for example bus, train, ...
transportationNumber    Number of the bus, train, ...
destination:            Place of the destination

## Run the project
This project is implemented using Spring. To run this project install java and maven. Afterwards you can run the project with *mvn spring-boot:run*
