package com.example;

import java.util.Random;
import java.util.Arrays;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application{    

    private TextField nField;
    private TextField kField;
    private ScatterChart<Number, Number> grafikas;
    private Label klaidosPranesimas;
    private Button mygtukasGeneruoti;

    @Override
    public void start(Stage stage)
    {
        int n = 10;
        int k = 20;
        int[] A = masyvoSukurimas(n, k);

        System.out.println(Arrays.toString(A));
        NumberAxis xAxis = new NumberAxis(0, n-1, 1);
        NumberAxis yAxis = new NumberAxis(0, k ,1);
        grafikas = new ScatterChart<>(xAxis, yAxis);

        nField = new TextField();
        nField.setPromptText("Įveskite n");
        kField = new TextField();
        kField.setPromptText("Įveskite k");

        mygtukasGeneruoti = new Button("Generuoti");
        klaidosPranesimas = new Label();

        mygtukasGeneruoti.setOnAction(e -> atnaujintiGrafika());

        VBox layout = new VBox();
        layout.getChildren().addAll(nField, kField, mygtukasGeneruoti, klaidosPranesimas, grafikas);
        layout.setSpacing(10);

        grafikoSukurimas(A, grafikas, k);
        Scene scena = new Scene(layout, 600, 400);
        stage.setScene(scena);
        stage.setTitle("Scatterplot testas");
        stage.show();
    }

    public int[] masyvoSukurimas(int n, int k)
    {
        int[] A = new int[n];
        Random random = new Random();
        for(int i=0;i<n;i++)
        {
            A[i]=random.nextInt(k) + 1;
        }
        return A;
    }
    public void grafikoSukurimas(int[] A, ScatterChart<Number, Number> grafikas, int k)
    {
        NumberAxis xAxis = new NumberAxis(0, A.length-1 ,1);
        NumberAxis yAxis = new NumberAxis(0, k ,1);
        XYChart.Series<Number, Number> taskuSerija = new XYChart.Series<>();
        for(int i=0;i<A.length;i++)
        {
            taskuSerija.getData().add(new XYChart.Data<>(i, A[i]));
        }
        grafikas.getData().add(taskuSerija);
    }
    public void atnaujintiGrafika()
    {
        String nText = nField.getText();
        String kText = kField.getText();
        int n,k;
        try{
            n = Integer.parseInt(nText);
            k = Integer.parseInt(kText);
        }
        catch(NumberFormatException ex){
            klaidosPranesimas.setText("Įveskite teisingus duomenis");
            return;
        }
        int[] A = masyvoSukurimas(n, k);
        grafikas.getData().clear();
        grafikoSukurimas(A, grafikas, k);
    }
    public static void main(String[] args) {
        launch();
    }
}
