package edu.matc.covidPulse.transformer;

import edu.matc.covidPulse.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StateDataTransformerTest {
    private StateCovidRecord data;
    private StateResponse response;
    private StateDataItem item;
    private List<StateCovidRecord> stateRecordList = new ArrayList<>();
    private List<StateResponse> responses = new ArrayList<>();

    /**
     * Sets up unit tests.
     */
    @BeforeEach
    void setUp() {
        data = new StateCovidRecord();
        data.setId(1);
        data.setState("WI");
        data.setFipsCode("55");
        data.setDate("2020-01-21");
        data.setCases(1);
        data.setDeaths(0);

        stateRecordList.add(data);

        item = new StateDataItem();
        item.setDate("2020-01-21");
        item.setDeaths(0);
        item.setCases(1);

        List<StateDataItem> itemList = new ArrayList<>();
        itemList.add(item);
        response = new StateResponse();
        response.setStateFips("55");
        response.setState("WI");
        response.setData(itemList);
        responses.add(response);

    }

    /**
     * Validates successful list conversion.
     */
    @Test
    void convertFromSuccess() {
        assertEquals(response, StateDataTransformer.from(stateRecordList).get(0));
    }
}
