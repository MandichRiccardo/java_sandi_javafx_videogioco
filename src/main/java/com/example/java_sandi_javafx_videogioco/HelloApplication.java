package com.example.java_sandi_javafx_videogioco;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.beans.binding.StringBinding;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Map;

public class HelloApplication extends GameApplication {
    @Override
    protected void initSettings(GameSettings settings){
        settings.setWidth(1600);
        settings.setHeight(700);
        settings.setTitle("Videogioco");
        settings.setVersion("0.1");
    }
    private Entity player, ball;

    @Override
    protected void initGame(){
        player = FXGL.entityBuilder()
                .at(500, 700)
                .view(new Rectangle(30, 30, Color.RED))
                .buildAndAttach();
        ball = FXGL.entityBuilder()
                .at(200, 150)
                .view(new Circle(20, Color.BLACK))
                .buildAndAttach();
    }

    @Override
    protected void initInput(){
        FXGL.onKey(KeyCode.UP, () -> {
            player.translateY(-10);
            FXGL.inc("numeroPassi", 1);
        });
        FXGL.onKey(KeyCode.DOWN, () -> {
            player.translateY(10);
            FXGL.inc("numeroPassi", 1);
        });
        FXGL.onKey(KeyCode.LEFT, () -> {
            player.translateX(-10);
            FXGL.inc("numeroPassi", 1);
        });
        FXGL.onKey(KeyCode.RIGHT, () -> {
            player.translateX(10);
            FXGL.inc("numeroPassi", 1);
        });
    }

    @Override
    protected void initGameVars(Map<String, Object> vars){
        vars.put("numeroPassi", 0);
    }

    @Override
    protected void initUI(){
        Text textPixels = new Text();
        textPixels.setTranslateX(550);
        textPixels.setTranslateY(100);
        textPixels.setFont(new Font(100));
        FXGL.getGameScene().addUINode(textPixels);
        StringBinding numeroPassiStringBinding = FXGL.getWorldProperties().intProperty("numeroPassi").asString();
        textPixels.textProperty().bind(numeroPassiStringBinding);
        int numeroPassiInt = Integer.parseInt(String.valueOf(numeroPassiStringBinding));
        if(numeroPassiInt >300){
            player.setX((double) numeroPassiInt / 10);
            player.setY((double) numeroPassiInt / 10);
        } else{
            player.setX(30);
            player.setY(30);
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}