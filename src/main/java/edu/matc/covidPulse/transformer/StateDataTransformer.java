package edu.matc.covidPulse.transformer;

import edu.matc.covidPulse.entity.StateCovidRecord;
import edu.matc.covidPulse.entity.StateDataItem;
import edu.matc.covidPulse.entity.StateResponse;
import lombok.extern.log4j.Log4j2;

import javax.swing.plaf.nimbus.State;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Converts a List of database records into the data structure that can be mapped to our JSON response,
 * Which is a List of individual response objects (each of which represents the data for a particular state),
 */
@Log4j2
public class StateDataTransformer {
    /**
     * Main transformation method.
     *
     * @param stateCovidRecords the state covid records
     * @return the list
     */
    public static List<StateResponse> from(List<StateCovidRecord> stateCovidRecords) {
        Map<String, StateResponse> responseItems = mapRecordsToStateResponseItems(stateCovidRecords);

        return stateResponseMapToList(responseItems);
    }

    private static Map<String, StateResponse> mapRecordsToStateResponseItems(List<StateCovidRecord> records) {
        Map<String, StateResponse> responseItems = new HashMap<>();

        for (StateCovidRecord record : records) {
            StateDataItem dataItem = generateDataItem(record);
            String currentRecordState = record.getState();

            // Map it to its State (key) in the responseItems
            if (responseItems.containsKey(currentRecordState)) {
                // Add it to that state's list
                StateResponse responseItem = responseItems.get(currentRecordState);
                addDataItemToResponse(dataItem, responseItem);

                responseItems.replace(currentRecordState, responseItem);
            } else {
                // That state isn't in the map yet, so create a new responseItem, set it's data, add it to Map
                StateResponse newResponseItem = new StateResponse();
                addDataItemToResponse(dataItem, newResponseItem);
                newResponseItem.setState(currentRecordState);
                newResponseItem.setStateFips(record.getFipsCode());
                responseItems.put(currentRecordState, newResponseItem);
            }
        }

        log.debug(responseItems);
        return responseItems;
    }

    private static void addDataItemToResponse(StateDataItem dataItem, StateResponse responseItem) {
        log.debug(dataItem);
        log.debug(responseItem);
        List<StateDataItem> currentStateData = responseItem.getData();
        currentStateData.add(dataItem);
        responseItem.setData(currentStateData);
    }

    private static StateDataItem generateDataItem(StateCovidRecord record) {
        StateDataItem dataItem = new StateDataItem();
        dataItem.setDate(record.getDate());
        dataItem.setCases(record.getCases());
        dataItem.setDeaths(record.getDeaths());
        return dataItem;
    }

    private static List<StateResponse> stateResponseMapToList(Map<String, StateResponse> responseItems) {
        List<StateResponse> list = new ArrayList<>();
        for (Map.Entry<String, StateResponse> stateResponse : responseItems.entrySet()) {
            list.add(stateResponse.getValue());
        }
        return list;
    }
}
