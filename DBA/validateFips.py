import csv

fips = []

with open('county_fips.csv') as fipsTable:
    fipsReader = csv.reader(fipsTable)
    for row in fipsReader:
        fips.append(row[0])

with open('us-counties.csv') as covidData:
    covidReader = csv.reader(covidData)
    for row in covidReader:
        fip = row[3]
        if fip not in fips:
            print("Row contains invalid FIPS code: \n")
            print(row)