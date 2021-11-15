package com.vir.bm.virclient;

import com.vir.bm.virclient.utils.LoadCollector;
import com.vir.bm.virwebclient.ResourceLoad;
import com.vir.bm.virwebclient.WebClientResourceClient;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class DashboardStageController implements Initializable {

    @FXML
    public ComboBox timeCombo;
    @FXML
    public ProgressBar progressBar;
    @FXML
    public Button btnApply;

    private final WebClientResourceClient webClientResourceClient;
    private static boolean measure = false;
    private Timeline timeline;
    private final LoadCollector loadCollector;


    public DashboardStageController(WebClientResourceClient webClientResourceClient, LoadCollector loadCollector) {
        this.webClientResourceClient = webClientResourceClient;
        this.loadCollector = loadCollector;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        progressBar.setVisible(false);
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
                    // do anything you need here on completion...
                    ResourceLoad resourceLoad = loadCollector.loadResource();
                    System.out.println(resourceLoad);
                    saveResourceLoad(resourceLoad);
                }, new KeyValue(progressBar.progressProperty(), 1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);

    }

    private void saveResourceLoad(ResourceLoad resourceLoad) {
        Mono<ResourceLoad> rs= webClientResourceClient.saveResourceLoad(resourceLoad);
        System.out.println(rs.block());
    }
}
