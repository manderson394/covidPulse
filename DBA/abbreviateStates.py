import csv


def appendAbbreviation(row):
    if row[1].lower() == 'alabama':
        row.append(",AL")
    elif row[1].lower() == 'alaska':
        row.append(",AK")
    elif row[1].lower() == 'arizona':
        row.append(",AZ")
    elif row[1].lower() == 'arkansas':
        row.append(",AR")
    elif row[1].lower() == 'california':
        row.append(",CA")
    elif row[1].lower() == 'colorado':
        row.append(",CO")
    elif row[1].lower() == 'connecticut':
        row.append(",CT")
    elif row[1].lower() == 'delaware':
        row.append(",DE")
    elif row[1].lower() == 'district of columbia':
        row.append(",DC")
    elif row[1].lower() == 'florida':
        row.append(",FL")
    elif row[1].lower() == 'georgia':
        row.append(",GA")
    elif row[1].lower() == 'hawaii':
        row.append(",HI")
    elif row[1].lower() == 'idaho':
        row.append(",ID")
    elif row[1].lower() == 'illinois':
        row.append(",IL")
    elif row[1].lower() == 'indiana':
        row.append(",IN")
    elif row[1].lower() == 'iowa':
        row.append(",IA")
    elif row[1].lower() == 'kansas':
        row.append(",KS")
    elif row[1].lower() == 'kentucky':
        row.append(",KY")
    elif row[1].lower() == 'louisiana':
        row.append(",LA")
    elif row[1].lower() == 'maine':
        row.append(",ME")
    elif row[1].lower() == 'maryland':
        row.append(",MD")
    elif row[1].lower() == 'massachusetts':
        row.append(",MA")
    elif row[1].lower() == 'michigan':
        row.append(",MI")
    elif row[1].lower() == 'minnesota':
        row.append(",MN")
    elif row[1].lower() == 'mississippi':
        row.append(",MS")
    elif row[1].lower() == 'missouri':
        row.append(",MO")
    elif row[1].lower() == 'montana':
        row.append("MT")
    elif row[1].lower() == 'nebraska':
        row.append(",NE")
    elif row[1].lower() == 'nevada':
        row.append(",NV")
    elif row[1].lower() == 'new hampshire':
        row.append(",NH")
    elif row[1].lower() == 'new jersey':
        row.append(",NJ")
    elif row[1].lower() == 'new mexico':
        row.append(",NM")
    elif row[1].lower() == 'new york':
        row.append(",NY")
    elif row[1].lower() == 'north carolina':
        row.append(",NC")
    elif row[1].lower() == 'north dakota':
        row.append(",ND")
    elif row[1].lower() == 'ohio':
        row.append(",OH")
    elif row[1].lower() == 'oklahoma':
        row.append(",OK")
    elif row[1].lower() == 'oregon':
        row.append(",OR")
    elif row[1].lower() == 'pennsylvania':
        row.append(",PA")
    elif row[1].lower() == 'rhode island':
        row.append(",RI")
    elif row[1].lower() == 'south carolina':
        row.append(",SC")
    elif row[1].lower() == 'south dakota':
        row.append(",SD")
    elif row[1].lower() == 'tennessee':
        row.append(",TN")
    elif row[1].lower() == 'texas':
        row.append(",TX")
    elif row[1].lower() == 'utah':
        row.append(",UT")
    elif row[1].lower() == 'vermont':
        row.append(",VT")
    elif row[1].lower() == 'virginia':
        row.append(",VA")
    elif row[1].lower() == 'washington':
        row.append(",WA")
    elif row[1].lower() == 'west virginia':
        row.append(",WV")
    elif row[1].lower() == 'wisconsin':
        row.append(",WI")
    elif row[1].lower() == 'wyoming':
        row.append(",WY")
    elif row[1].lower() == 'american samoa':
        row.append(",AS")
    elif row[1].lower() == 'puerto rico':
        row.append(",PR")
    elif row[1].lower() == 'guam':
        row.append(",GU")
    elif row[1].lower() == 'northern mariana islands':
        row.append(",MP")
    elif row[1].lower() == 'virgin islands':
        row.append(",VI")

rows = []

with open('us-states.csv') as states:
    statesReader = csv.reader(states)
    for row in statesReader:
        rows.append(row)

with open("us-states-abbrv.csv", mode='w') as abbrvStates:
    statesWriter = csv.writer(abbrvStates)
    for row in rows:
        appendAbbreviation(row)
        statesWriter.writerow(row)