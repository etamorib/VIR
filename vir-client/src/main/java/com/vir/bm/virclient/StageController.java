package com.vir.bm.virclient;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class StageController implements Initializable {
    @FXML
    public Pane contentPane;
    @FXML
    private AnchorPane opacityPane,drawerPane;
    private AnchorPane dashboard, data;

    @FXML
    private Label drawerImage;

    @Value("classpath:/dashboardStage.fxml")
    private Resource dashboardStage;

    @Value("classpath:/dataStage.fxml")
    private Resource dataStage;
    private ApplicationContext applicationContext;

    public StageController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dashboard = setStage(dashboardStage);
        data = setStage(dataStage);
        setContentResource(dashboard);

        opacityPane.setVisible(false);

        FadeTransition fadeTransition=new FadeTransition(Duration.seconds(0.5), opacityPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        TranslateTransition translateTransition=new TranslateTransition(Duration.seconds(0.5), drawerPane);
        translateTransition.setByX(-600);
        translateTransition.play();

        drawerImage.setOnMouseClicked(event -> {

            opacityPane.setVisible(true);

            FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(0.5), opacityPane);
            fadeTransition1.setFromValue(0);
            fadeTransition1.setToValue(0.15);
            fadeTransition1.play();

            TranslateTransition translateTransition1=new TranslateTransition(Duration.seconds(0.5), drawerPane);
            translateTransition1.setByX(+600);
            translateTransition1.play();
        });

        opacityPane.setOnMouseClicked(event -> {

            FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(0.5), opacityPane);
            fadeTransition1.setFromValue(0.15);
            fadeTransition1.setToValue(0);
            fadeTransition1.play();

            fadeTransition1.setOnFinished(event1 -> {
                opacityPane.setVisible(false);
            });

            TranslateTransition translateTransition1=new TranslateTransition(Duration.seconds(0.5),drawerPane);
            translateTransition1.setByX(-600);
            translateTransition1.play();
        });
    }

    private void setContentResource(AnchorPane content) {
        try {
            contentPane.getChildren().setAll(content);
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void btnDashboardEvent(ActionEvent actionEvent) {
        setContentResource(dashboard);
    }

    public void btnDataEvent(ActionEvent actionEvent) {
        setContentResource(data);
    }

    public void imgHomeClickEvent(MouseEvent mouseEvent) {
        setContentResource(dashboard);
    }

    public void imgDataClickedEvent(MouseEvent mouseEvent) {
        setContentResource(data);
    }

    private AnchorPane setStage(Resource resource) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(resource.getURL());
            fxmlLoader.setControllerFactory(aClass -> applicationContext.getBean(aClass));
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
