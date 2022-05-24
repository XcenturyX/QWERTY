package com.example.coursework;


import com.example.ibuff.Buffer;
import com.example.ifiltrs.MFilter;
import com.example.logic.Logica;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.image.*;

import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
//import javafx.embed.swing.SwingFXUtils;


public class MainController implements Initializable {
    private FileChooser fileChooser;
    private Stage stage;
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Logica logica;
    private MFilter filter;
   // private Mask imask;
    private ImageView MaskImage;
    private int countB;
    private int countM;
    private Image[] mask;
    private ImageView iBlack,iBlue,iGreen,iOrange,iRed,iWhite,iPur,iYellow;
    private javafx.scene.paint.Color colorCho;
    private Buffer buffer;





    @FXML
    private VBox VBoxMain;
    @FXML
    private HBox hBoxViewImage;
    @FXML
    private ToolBar MainFunToolBar;
    @FXML
    private AnchorPane AncPane,PaneTools;
    @FXML
    private ListView<String> ListFilters,ListMask;
    @FXML
    private Slider SliderRed,SliderBlue,SliderGreen,SliderLight,SliderSaturation,SliderWarm,size;
    @FXML
    private ToolBar FotoToolBar;
    @FXML
    private ImageView iPlus, iMinus;
    @FXML
    private Button FilterBut,CorBut,ScaleBut,SafeBut,ReturneFoto;
    @FXML
    private MenuButton DrowBut;







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileChooser=new FileChooser();
        stage=new Stage();
        logica=new Logica();
        filter=new MFilter();
        buffer=new Buffer();
        //imask=new Mask();

        VBoxMain.setPrefHeight(MainClassApplication.heightScene);
        VBoxMain.setPrefWidth(MainClassApplication.widthScene);


        hBoxViewImage.setPrefWidth(MainClassApplication.widthScene);
        hBoxViewImage.setPrefHeight(MainClassApplication.heightScene-MainFunToolBar.getPrefHeight()-10);

        AncPane.setPrefHeight(hBoxViewImage.getPrefHeight());
        AncPane.setPrefWidth(hBoxViewImage.getPrefWidth()-150);
        AncPane.setLayoutX(150);
        AncPane.setLayoutY(0);

        ListFilters.getItems().add("Запад");
        ListFilters.getItems().add("Пальма");
        ListFilters.getItems().add("Луг");
        ListFilters.getItems().add("Румянец");
        ListFilters.getItems().add("Кинолента");
        ListFilters.getItems().add("Черно-Белый");
        ListFilters.getItems().add("Негатив");

        InputStream Stream=getClass().getResourceAsStream("useFoto/MTool/minus.png");
        iMinus.setImage(new Image(Stream));
        Stream=getClass().getResourceAsStream("useFoto/MTool/plus.png");
        iPlus.setImage(new Image(Stream));

        mask=new Image[4];
        Stream=getClass().getResourceAsStream("useFoto/MMask/Fan.png");
        mask[0]=new Image(Stream);
        Stream=getClass().getResourceAsStream("useFoto/MMask/Net.png");
        mask[1]=new Image(Stream);
        Stream=getClass().getResourceAsStream("useFoto/MMask/Qw.png");
        mask[2]=new Image(Stream);
        Stream=getClass().getResourceAsStream("useFoto/MMask/Xexe.png");
        mask[3]=new Image(Stream);

        ObservableList<String> items = FXCollections.observableArrayList (
                "Веселый", "Нетральный", "????", "Хехехе)))");
        ListMask.setItems(items);

        ListMask.setCellFactory(listView -> new ListCell<String>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    imageView.setFitWidth(30);
                    imageView.setFitHeight(30);
                    if(name.equals("Веселый"))
                        imageView.setImage(mask[0]);
                    else if(name.equals("Нетральный"))
                        imageView.setImage(mask[1]);
                    else if(name.equals("????"))
                        imageView.setImage(mask[2]);
                    else if(name.equals("Хехехе)))"))
                        imageView.setImage(mask[3]);
                    setText(name);
                    setGraphic(imageView);
                }
            }
        });

        iMinus.setFitWidth(30);
        iMinus.setFitHeight(30);
        iMinus.setLayoutY(200);
        iMinus.setLayoutX(10);
        iPlus.setFitWidth(30);
        iPlus.setFitHeight(30);
        iPlus.setLayoutY(200);
        iPlus.setLayoutX(50);


        iBlack=new ImageView();
        iBlue=new ImageView();
        iGreen=new ImageView();
        iOrange=new ImageView();
        iRed=new ImageView();
        iWhite=new ImageView();
        iPur=new ImageView();
        iYellow=new ImageView();
        Stream=getClass().getResourceAsStream("useFoto/MColors/Blac.png");
        iBlack.setImage(new Image(Stream));
        Stream=getClass().getResourceAsStream("useFoto/MColors/Blue.png");
        iBlue.setImage(new Image(Stream));
        Stream=getClass().getResourceAsStream("useFoto/MColors/Green.png");
        iGreen.setImage(new Image(Stream));
        Stream=getClass().getResourceAsStream("useFoto/MColors/Orange.png");
        iOrange.setImage(new Image(Stream));
        Stream=getClass().getResourceAsStream("useFoto/MColors/Pur.png");
        iPur.setImage(new Image(Stream));
        Stream=getClass().getResourceAsStream("useFoto/MColors/Red.png");
        iRed.setImage(new Image(Stream));
        Stream=getClass().getResourceAsStream("useFoto/MColors/White.png");
        iWhite.setImage(new Image(Stream));
        Stream=getClass().getResourceAsStream("useFoto/MColors/Yellow.png");
        iYellow.setImage(new Image(Stream));
        iBlack.setFitWidth(18);
        iBlack.setFitHeight(18);
        iBlack.setX(0);
        iBlack.setY((int)(MainClassApplication.heightScene/2));
        iBlack.setVisible(false);
        iWhite.setFitWidth(18);
        iWhite.setFitHeight(18);
        iWhite.setX(18);
        iWhite.setY((int)(MainClassApplication.heightScene/2));
        iWhite.setVisible(false);
        iRed.setFitWidth(18);
        iRed.setFitHeight(18);
        iRed.setX(36);
        iRed.setY((int)(MainClassApplication.heightScene/2));
        iRed.setVisible(false);
        iOrange.setFitWidth(18);
        iOrange.setFitHeight(18);
        iOrange.setX(54);
        iOrange.setY((int)(MainClassApplication.heightScene/2));
        iOrange.setVisible(false);
        iYellow.setFitWidth(18);
        iYellow.setFitHeight(18);
        iYellow.setX(72);
        iYellow.setY((int)(MainClassApplication.heightScene/2));
        iYellow.setVisible(false);
        iGreen.setFitWidth(18);
        iGreen.setFitHeight(18);
        iGreen.setX(90);
        iGreen.setY((int)(MainClassApplication.heightScene/2));
        iGreen.setVisible(false);
        iBlue.setFitWidth(18);
        iBlue.setFitHeight(18);
        iBlue.setX(108);
        iBlue.setY((int)(MainClassApplication.heightScene/2));
        iBlue.setVisible(false);
        iPur.setFitWidth(18);
        iPur.setFitHeight(18);
        iPur.setX(126);
        iPur.setY((int)(MainClassApplication.heightScene/2));
        iPur.setVisible(false);
        PaneTools.getChildren().addAll(iBlack,iWhite,iRed,iOrange,iYellow,iGreen,iBlue,iPur);
        dontClick();

    }
    public void dontClick(){
        FilterBut.setVisible(false);
        CorBut.setVisible(false);
        ScaleBut.setVisible(false);
        DrowBut.setVisible(false);
        SafeBut.setVisible(false);
        ReturneFoto.setVisible(false);
    }
    public void okClick(){
        FilterBut.setVisible(true);
        CorBut.setVisible(true);
        ScaleBut.setVisible(true);
        DrowBut.setVisible(true);
        SafeBut.setVisible(true);
    }
    public void clean(){
        ListFilters.setVisible(false);
        FotoToolBar.setVisible(false);
        size.setVisible(false);
        offListMask();
        offChooceColor();
    }
    public void setValueSlider(){
        SliderWarm.setValue(0);
        SliderSaturation.setValue(0);
        SliderLight.setValue(0);
        SliderGreen.setValue(0);
        SliderRed.setValue(0);
        SliderBlue.setValue(0);
    }
    public void setListMask(){
        ListMask.setVisible(true);
        iMinus.setVisible(true);
        iPlus.setVisible(true);


    }
    public void offListMask(){
        ListMask.setVisible(false);
        iMinus.setVisible(false);
        iPlus.setVisible(false);

    }
    public void setChooserColor(){
        iBlack.setVisible(true);
        iWhite.setVisible(true);
        iRed.setVisible(true);
        iOrange.setVisible(true);
        iYellow.setVisible(true);
        iGreen.setVisible(true);
        iBlue.setVisible(true);
        iPur.setVisible(true);
    }
    public void offChooceColor(){
        iBlack.setVisible(false);
        iWhite.setVisible(false);
        iRed.setVisible(false);
        iOrange.setVisible(false);
        iYellow.setVisible(false);
        iGreen.setVisible(false);
        iBlue.setVisible(false);
        iPur.setVisible(false);
    }

    public void LoudFoto(ActionEvent actionEvent) {
        clean();
        if(canvas!=null) {
            AncPane.getChildren().remove(canvas);
        }
        try {
            fileChooser.setTitle("Выбор изображения");
            File FileImage = fileChooser.showOpenDialog(stage);
            Image image = new Image(FileImage.getAbsolutePath());
                if(image.getHeight()<image.getWidth()){
                    canvas=new Canvas(630,MainClassApplication.heightScene-54);
                    logica.moveCanvas(canvas,40,0);
                    graphicsContext=canvas.getGraphicsContext2D();
                    graphicsContext.drawImage(image,0,0,630,MainClassApplication.heightScene-54);
                }
                else {
                    canvas=new Canvas(330,MainClassApplication.heightScene-54);
                    logica.moveCanvas(canvas,180,0);
                    graphicsContext=canvas.getGraphicsContext2D();
                    graphicsContext.drawImage(image,0,0,330,MainClassApplication.heightScene-54);
                }
                AncPane.getChildren().add(canvas);
                setValueSlider();
                okClick();
        }
        catch (Exception e){
        }

    }



    public void SafeFoto(ActionEvent actionEvent) {
        try {
            WritableImage image=logica.getWritableImageFromCanvas(canvas);
            fileChooser.setInitialFileName("newImage.jpg");
            File safeFile = fileChooser.showSaveDialog(stage);
           //ImageIO.write(SwingFXUtils.fromFXImage(image, null), "jpg", safeFile);






        }
        catch (Exception e){

        }
    }


    public void setFiltrs(ActionEvent actionEvent) {
        clean();
        ListFilters.setVisible(true);
        WritableImage wImage=logica.getWritableImageFromCanvas(canvas);
        ListFilters.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                WritableImage writableImage=filter.setFiltrs(ListFilters.getSelectionModel().getSelectedItem(),wImage);
                graphicsContext.drawImage(writableImage,0,0, canvas.getWidth(), canvas.getHeight());
            }
        });
    }

    public void parametersOn(ActionEvent actionEvent) {
        clean();
        FotoToolBar.setVisible(true);

    }

    public void CustomRed(MouseEvent mouseEvent) {
        WritableImage wImage=logica.getWritableImageFromCanvas(canvas);
        WritableImage image=logica.customColor("Red",wImage,SliderRed.getValue());
        graphicsContext.drawImage(image,0,0,canvas.getWidth(),canvas.getHeight());
    }

    public void CustomBlue(MouseEvent mouseEvent) {
        WritableImage wImage=logica.getWritableImageFromCanvas(canvas);
        WritableImage image=logica.customColor("Blue",wImage,SliderBlue.getValue());
        graphicsContext.drawImage(image,0,0,canvas.getWidth(),canvas.getHeight());
    }

    public void CustomGreen(MouseEvent mouseEvent) {
        WritableImage wImage=logica.getWritableImageFromCanvas(canvas);
        WritableImage image=logica.customColor("Green",wImage,SliderGreen.getValue());
        graphicsContext.drawImage(image,0,0,canvas.getWidth(),canvas.getHeight());
    }

    public void CustomLight(MouseEvent mouseEvent) {
        WritableImage wImage=logica.getWritableImageFromCanvas(canvas);
        WritableImage image=logica.customColor("Light",wImage,SliderLight.getValue());
        graphicsContext.drawImage(image,0,0,canvas.getWidth(),canvas.getHeight());
    }

    public void CustomSaturation(MouseEvent mouseEvent) {
        WritableImage wImage=logica.getWritableImageFromCanvas(canvas);
        WritableImage image=logica.customColor("Sut",wImage,SliderSaturation.getValue());
        graphicsContext.drawImage(image,0,0,canvas.getWidth(),canvas.getHeight());
    }

    public void CustomWarm(MouseEvent mouseEvent) {
        WritableImage wImage=logica.getWritableImageFromCanvas(canvas);
        WritableImage image=logica.customColor("Warm",wImage,SliderWarm.getValue());
        graphicsContext.drawImage(image,0,0,canvas.getWidth(),canvas.getHeight());
    }

    public void setMaskOnFoto(ActionEvent actionEvent) {
        clean();
        setListMask();
        countB=0;
        countM=0;
        AnchorPane pane=AncPane;
        MaskImage=new ImageView();
        MaskImage.setLayoutX(canvas.getLayoutX());
        MaskImage.setLayoutY(canvas.getLayoutY());
        MaskImage.setFitHeight(100);
        MaskImage.setFitWidth(100);
        ListMask.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                int i=ListMask.getSelectionModel().getSelectedIndex();
                MaskImage.setImage(mask[i]);
            }
        });
        iMinus.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(countB>-10) {
                    MaskImage.setFitWidth(MaskImage.getFitWidth()*0.9);
                    MaskImage.setFitHeight(MaskImage.getFitHeight()*0.9);
                    countB--;
                }
            }
        });
        iPlus.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(countB<10) {
                    MaskImage.setFitWidth(MaskImage.getFitWidth()*1.1);
                    MaskImage.setFitHeight(MaskImage.getFitHeight()*1.1);
                    countB++;
                }
            }
        });
        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(countM%2==0) {
                    MaskImage.setLayoutX(mouseEvent.getX());
                    MaskImage.setLayoutY(mouseEvent.getY());


                    countM++;
                }
                else {
                    graphicsContext.drawImage(MaskImage.getImage(),MaskImage.getX(),MaskImage.getY(),MaskImage.getFitWidth(),MaskImage.getFitHeight());
                    AncPane.getChildren().remove(MaskImage);
                    countM++;
                }
            }
        });
        pane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if(countM%2==1&&
                        mouseEvent.getY()<MainClassApplication.heightScene-MaskImage.getFitHeight()-54&&
                        mouseEvent.getX()<canvas.getLayoutX()+canvas.getWidth()-MaskImage.getFitWidth()&&
                        mouseEvent.getX()>canvas.getLayoutX()){
                    MaskImage.setLayoutY(mouseEvent.getY());
                    MaskImage.setLayoutX(mouseEvent.getX());
                }
            }
        });
        AncPane.getChildren().add(MaskImage);
    }

    public void DrowAction(ActionEvent actionEvent) {
        clean();
        setChooserColor();
        size.setVisible(true);
        countM=0;
        AnchorPane pn=AncPane;
        pn.setLayoutX(canvas.getLayoutX());
        pn.setLayoutY(canvas.getLayoutY());
        pn.setPrefWidth(canvas.getWidth());
        pn.setPrefHeight(canvas.getHeight());


        iBlack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                colorCho=new javafx.scene.paint.Color(0,0,0,1);
            }
        });
        iBlue.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                colorCho=new javafx.scene.paint.Color(0,0,1,1);
            }
        });
        iGreen.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                colorCho=new javafx.scene.paint.Color(0.1,1,0.1,1);
            }
        });
        iOrange.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                colorCho=new javafx.scene.paint.Color(1,0.5,0.1,1);
            }
        });
        iPur.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                colorCho=new javafx.scene.paint.Color(0.6,0,1,1);
            }
        });
        iRed.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                colorCho=new javafx.scene.paint.Color(1,0,0,1);
            }
        });
        iWhite.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                colorCho=new javafx.scene.paint.Color(1,1,1,1);
            }
        });
        iYellow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                colorCho=new Color(1,0.9,0.1,1);
            }
        });

        WritableImage writableImage=logica.getWritableImageFromCanvas(canvas);
        PixelWriter writer=writableImage.getPixelWriter();
        pn.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(countM%2==1&&colorCho!=null) {
                    if (mouseEvent.getY() < MainClassApplication.heightScene - 55&&
                            mouseEvent.getX()<canvas.getWidth()) {
                        try {
                            logica.drow(writer,(int) mouseEvent.getX()-22,(int) mouseEvent.getY(), (int) size.getValue(),colorCho);
                        }
                        catch (Exception e){

                        }
                        graphicsContext.drawImage(writableImage,0,0,canvas.getWidth(),canvas.getHeight());
                    }
                }
            }
        });

        pn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(countM%2==0) {
                    countM++;
                }
                else {
                    countM++;
                }
            }
        });
    }

    public void SkaleFoto(ActionEvent actionEvent) {
        clean();
        countM=0;
        javafx.scene.shape.Rectangle rect=new javafx.scene.shape.Rectangle();
        rect.setStroke(Color.BLUE);
        rect.setStrokeWidth(1);
        rect.setStrokeLineCap(StrokeLineCap.ROUND);
        rect.setFill(Color.LIGHTBLUE.deriveColor(0, 1.2, 1, 0.6));
        AnchorPane pane=AncPane;
        pane.setLayoutX(canvas.getLayoutX());
        pane.setLayoutY(canvas.getLayoutY());
        pane.setPrefWidth(canvas.getWidth());
        pane.setPrefHeight(canvas.getHeight());
        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(countM%2==0) {
                    AncPane.getChildren().add(rect);
                    rect.setX(mouseEvent.getX());
                    rect.setY(mouseEvent.getY());
                    countM++;
                }
                else {
                    WritableImage image=logica.getWritableImageFromCanvas(canvas);
                    int width=(int)(rect.getWidth());
                    int height=(int) (rect.getHeight());
                    PixelReader reader=image.getPixelReader();
                    WritableImage wImage= new WritableImage(reader, (int) rect.getX(), (int) rect.getY(), (int) width , (int) height);


                    AncPane.getChildren().remove(rect);

                    graphicsContext.drawImage(wImage,0,0, canvas.getWidth(), canvas.getHeight());
                    countM++;
                }
            }
        });
        pane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(countM%2==1){
                    rect.setHeight(mouseEvent.getY()-rect.getY());
                    rect.setWidth(mouseEvent.getX()-rect.getX());
                }
            }
        });

    }


}
