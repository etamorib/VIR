package com.vir.bm.virclient;

import com.vir.bm.virclient.utils.LoadCollector;
import com.vir.bm.virwebclient.ResourceLoad;
import com.vir.bm.virwebclient.WebClientResourceClient;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ResourceBundle;
import java.util.function.Consumer;

@Component
public class DashboardStageController implements Initializable, Consumer<ResourceLoad> {

    @FXML
    public ComboBox timeCombo;
    @FXML
    public ProgressBar progressBar;
    @FXML
    public Button btnApply;
    @FXML
    public LineChart<String, Double> resourceChart;

    private final WebClientResourceClient webClientResourceClient;
    private static boolean measure = false;
    private Timeline timeline;
    private final LoadCollector loadCollector;
    private ObservableList<XYChart.Data<String, Double>> cpuData = FXCollections.observableArrayList();
    private ObservableList<XYChart.Data<String, Double>> ramData = FXCollections.observableArrayList();
    private ObservableList<XYChart.Data<String, Double>> memoryData = FXCollections.observableArrayList();


    public DashboardStageController(WebClientResourceClient webClientResourceClient, LoadCollector loadCollector) {
        this.webClientResourceClient = webClientResourceClient;
        this.loadCollector = loadCollector;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (!measure){
            progressBar.setVisible(false);
            resourceChart.setVisible(false);
        }
        ObservableList<XYChart.Series<String, Double>> data = FXCollections.observableArrayList();
        data.add(new XYChart.Series<>("CPU", cpuData));
        data.add(new XYChart.Series<>("RAM", ramData));
        data.add(new XYChart.Series<>("Disk", memoryData));

        resourceChart.setData(data);
    }

    @Override
    public void accept(ResourceLoad resourceLoad) {
        if (!resourceChart.isVisible()) {
            resourceChart.setVisible(true);
        }
        Platform.runLater(() -> {
            String dateString = resourceLoad.getTime().format(DateTimeFormatter.ofPattern("yyyy. MM. dd. - hh:mm:ss"));
            cpuData.add(new XYChart.Data<>(dateString, resourceLoad.getCpu()));
            ramData.add(new XYChart.Data<>(dateString, resourceLoad.getRam()));
            memoryData.add(new XYChart.Data<>(dateString, resourceLoad.getDisk()));
        });
    }

    public void btnApply(MouseEvent mouseEvent) {
        measure = !measure;
        if (measure) {
            btnApply.setText("Stop");
            Double second = (Double) timeCombo.getValue();
            progressBar.setVisible(true);
            setProgressBarInterval(second);
            timeline.play();
        } else {
            btnApply.setText("Start");
            progressBar.setVisible(false);
            resourceChart.setData(null);
            resourceChart.setVisible(false);
            timeline.stop();
        }
    }

    private void setProgressBarInterval(double second) {
        if (timeline != null) {
            timeline.stop();
        }
        timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(progressBar.progressProperty(), 0)),
                new KeyFrame(Duration.seconds(second), e -> {
                    new Thread(() -> {
                        ResourceLoad resourceLoad = loadCollector.loadResource();
                        System.out.println(resourceLoad);
                        saveResourceLoad(resourceLoad);
                    }).start();

                }, new KeyValue(progressBar.progressProperty(), 1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);

    }

    private void saveResourceLoad(ResourceLoad resourceLoad) {
        webClientResourceClient.saveResourceLoad(resourceLoad).subscribe(this);
        //Mono<ResourceLoad> rs= webClientResourceClient.saveResourceLoad(resourceLoad);

    }

}
