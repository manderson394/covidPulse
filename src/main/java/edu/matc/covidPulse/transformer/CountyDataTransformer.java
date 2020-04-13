package edu.matc.covidPulse.transformer;

import edu.matc.covidPulse.entity.CountyCovidData;
import edu.matc.covidPulse.entity.CountyDataItem;
import edu.matc.covidPulse.entity.CountyResponse;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountyDataTransformer {

    public static List<CountyResponse> convertFrom(List<CountyCovidData> countyCovidDataList) {
        Map<String, CountyResponse> responseMap = generateCountyResponse(countyCovidDataList);

        return convertMapToList(responseMap);
    }

    public static Map<String, CountyResponse> generateCountyResponse(List<CountyCovidData> countyDataList) {
        Map<String, CountyResponse> map = new HashMap<>();

        for (CountyCovidData data : countyDataList) {
            CountyDataItem countyItem = createCountyDataItem(data);
            String countyName = data.getCountyName();

            if (map.containsKey(countyName)) {
                CountyResponse countyResponse = map.get(countyName);
                addCountyDataItemToMap(countyItem, countyResponse);
                map.replace(countyName, countyResponse);

            } else {
                CountyResponse newResponse = new CountyResponse(data.getFipsCode().getFips(), countyName);
                addCountyDataItemToMap(countyItem, newResponse);
                map.put(countyName, newResponse);
            }
        }
        return map;

    }

    public static CountyDataItem createCountyDataItem(CountyCovidData countyData) {
        CountyDataItem newItem =
                new CountyDataItem(countyData.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE), countyData.getCases(),
                        countyData.getDeaths());

        return newItem;
    }

    public static void addCountyDataItemToMap(CountyDataItem dataItem, CountyResponse responseItem) {
        List<CountyDataItem> countyDataList = responseItem.getCountyData();
        countyDataList.add(dataItem);
        responseItem.setCountyData(countyDataList);
    }

    public static List<CountyResponse> convertMapToList(Map<String, CountyResponse> inputMap) {
        ArrayList<CountyResponse> resultList = new ArrayList<>();

        for(Map.Entry<String, CountyResponse> mapItem : inputMap.entrySet()) {
            resultList.add(mapItem.getValue());
        }

        return  resultList;
    }
}
