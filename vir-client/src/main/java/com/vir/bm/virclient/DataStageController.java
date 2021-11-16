package com.vir.bm.virclient;

import com.vir.bm.virwebclient.ResourceLoad;
import com.vir.bm.virwebclient.WebClientResourceClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tornadofx.control.DateTimePicker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class DataStageController {

    @FXML
    public LineChart<String, Double> cpu;
    @FXML
    public LineChart<String, Double> memory;
    @FXML
    public LineChart<String, Double> disk;
    @FXML
    public DateTimePicker firstDatePicker;
    @FXML
    public DateTimePicker secondDatePicker;
    @FXML
    public Label noRecordLabel;
    private WebClientResourceClient webClientResourceClient;
    private ObservableList<XYChart.Data<String, Double>> cpuData = FXCollections.observableArrayList();
    private ObservableList<XYChart.Data<String, Double>> ramData = FXCollections.observableArrayList();
    private ObservableList<XYChart.Data<String, Double>> diskData = FXCollections.observableArrayList();

    public DataStageController() {}

    @Autowired
    public DataStageController(WebClientResourceClient webClientResourceClient) {
        this.webClientResourceClient = webClientResourceClient;
    }

    @FXML
    public void initialize() {
        firstDatePicker.setDateTimeValue(LocalDateTime.now().minusDays(1));
        secondDatePicker.setDateTimeValue(LocalDateTime.now());

    }

    public void fetchData(MouseEvent mouseEvent) {
        redoCharts();
        LocalDateTime firstDate = firstDatePicker.getDateTimeValue();
        ObservableList<XYChart.Series<String, Double>> cpuSeries = FXCollections.observableArrayList();
        ObservableList<XYChart.Series<String, Double>> ramSeries = FXCollections.observableArrayList();
        ObservableList<XYChart.Series<String, Double>> diskSeries = FXCollections.observableArrayList();
        cpuSeries.add(new XYChart.Series<>("CPU", this.cpuData));
        ramSeries.add(new XYChart.Series<>("RAM", this.ramData));
        diskSeries.add(new XYChart.Series<>("Disk", this.diskData));
        LocalDateTime secondDate = secondDatePicker.getDateTimeValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String firstDateFormatted = firstDate.format(formatter);
        String secondDateFormatted = secondDate.format(formatter);
        List<ResourceLoad> list = webClientResourceClient.getAllBetween(firstDateFormatted, secondDateFormatted).collectList().block();
        if (list != null && list.size() > 0) {
            list.forEach(resourceLoad -> {
                cpuData.add(new XYChart.Data<>(resourceLoad.getTime().format(formatter), resourceLoad.getCpu()));
                ramData.add(new XYChart.Data<>(resourceLoad.getTime().format(formatter), resourceLoad.getRam()));
                diskData.add(new XYChart.Data<>(resourceLoad.getTime().format(formatter), resourceLoad.getDisk()));
            });
            cpu.setData(cpuSeries);
            memory.setData(ramSeries);
            disk.setData(diskSeries);
        }
    }

    private void redoCharts() {
        cpuData.clear();
        ramData.clear();
        diskData.clear();
    }
}

