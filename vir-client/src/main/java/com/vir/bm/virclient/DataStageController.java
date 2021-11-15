package com.vir.bm.virclient;

import com.vir.bm.virwebclient.ResourceLoad;
import com.vir.bm.virwebclient.WebClientResourceClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class DataStageController implements Consumer<ResourceLoad> {

    @FXML
    public LineChart<String, Double> cpu;
    @FXML
    public LineChart<String, Double> memory;
    @FXML
    public LineChart<String, Double> disk;
    private WebClientResourceClient webClientResourceClient;
    private ObservableList<XYChart.Data<String, Double>> seriesData = FXCollections.observableArrayList();

    public DataStageController() {}

    @Autowired
    public DataStageController(WebClientResourceClient webClientResourceClient) {
        this.webClientResourceClient = webClientResourceClient;
    }

    @FXML
    public void initialize() {
        ObservableList<XYChart.Series<String, Double>> cpuData = FXCollections.observableArrayList();
        cpuData.add(new XYChart.Series<>(seriesData));
        cpu.setData(cpuData);
        System.out.println(webClientResourceClient);

    }

    @Override
    public void accept(ResourceLoad resourceLoad) {

    }
}

