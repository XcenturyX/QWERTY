package com.example.coursework;


import com.example.MMask.Mask;
import com.example.ibuff.Buffer;
import com.example.ifiltrs.MFilter;
import com.example.logic.Logica;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
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


import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;



public class MainController implements Initializable {
    private FileChooser fileChooser;
    private Stage stage;
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Logica logica;
    private MFilter filter;
    private Mask imask;
    private ImageView MaskImage;
    private int countB;
    private int countM;
    private javafx.scene.paint.Color colorCho;
    private Buffer buffer;
    private int delta;
    private WritableImage writablImage;
    private PixelWriter writr;


    @FXML
    private AnchorPane AncPane;
    @FXML
    private ListView<String> ListFilters,ListMask;
    @FXML
    private Slider SliderRed,SliderBlue,SliderGreen,SliderLight,SliderSaturation,SliderWarm,size;
    @FXML
    private ToolBar FotoToolBar;
    @FXML
    private ImageView iPlus, iMinus,iBlack,iBlue,iGreen,iOrange,iRed,iWhite,iPur,iYellow;
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
        imask=new Mask();

        ListFilters.getItems().addAll(filter.arrFiltrs);

        InputStream Stream=getClass().getResourceAsStream("useFoto/MTool/minus.png");
        iMinus.setImage(new Image(Stream));
        Stream=getClass().getResourceAsStream("useFoto/MTool/plus.png");
        iPlus.setImage(new Image(Stream));
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
                        imageView.setImage(imask.getMask(0));
                    else if(name.equals("Нетральный"))
                        imageView.setImage(imask.getMask(1));
                    else if(name.equals("????"))
                        imageView.setImage(imask.getMask(2));
                    else if(name.equals("Хехехе)))"))
                        imageView.setImage(imask.getMask(3));
                    setText(name);
                    setGraphic(imageView);
                }
            }
        });

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
                    canvas=new Canvas(630,MainClassApplication.heightScene-56);
                    delta=40;
                    logica.moveCanvas(canvas,delta,0);
                    graphicsContext=canvas.getGraphicsContext2D();
                    graphicsContext.drawImage(image,0,0,630,MainClassApplication.heightScene-56);
                }
                else {
                    canvas=new Canvas(330,MainClassApplication.heightScene-56);
                    delta=180;
                    logica.moveCanvas(canvas,delta,0);
                    graphicsContext=canvas.getGraphicsContext2D();
                    graphicsContext.drawImage(image,0,0,330,MainClassApplication.heightScene-56);
                }
                AncPane.getChildren().add(canvas);
                buffer.PutImeginn(logica.getWritableImageFromCanvas(canvas));
                setValueSlider();
                okClick();
        }
        catch (Exception e){
        }

    }

    public void SafeFoto(ActionEvent actionEvent) {
        try {
            WritableImage image=logica.getWritableImageFromCanvas(canvas);
            fileChooser.setInitialFileName("newImage");
            File file = fileChooser.showSaveDialog(stage);
            File safeFile=new File(file.getAbsolutePath()+".png");
            safeFile.createNewFile();
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", safeFile);

        }
        catch (Exception e){

        }
    }

    public void setFiltrs(ActionEvent actionEvent) {
        clean();
        ListFilters.setVisible(true);
        ListFilters.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                graphicsContext.drawImage(filter.setFiltrs(ListFilters.getSelectionModel().getSelectedItem(),
                        new WritableImage(buffer.ReturnNotDelet().getPixelReader(),(int)buffer.ReturnNotDelet().getWidth(),(int)buffer.ReturnNotDelet().getHeight()))
                        ,0,0, canvas.getWidth(), canvas.getHeight());

            }
        });
    }

    public void parametersOn(ActionEvent actionEvent) {
        clean();
        FotoToolBar.setVisible(true);

    }

    public void CustomRed(MouseEvent mouseEvent) {
        WritableImage image=logica.customColor("Red",
                new WritableImage(buffer.ReturnNotDelet().getPixelReader(), (int) buffer.ReturnNotDelet().getWidth(), (int) buffer.ReturnNotDelet().getHeight()),
                SliderRed.getValue());
        graphicsContext.drawImage(image,0,0,canvas.getWidth(),canvas.getHeight());
    }

    public void CustomBlue(MouseEvent mouseEvent) {
        WritableImage image=logica.customColor("Blue",
                new WritableImage(buffer.ReturnNotDelet().getPixelReader(), (int) buffer.ReturnNotDelet().getWidth(), (int) buffer.ReturnNotDelet().getHeight())
                ,SliderBlue.getValue());
        graphicsContext.drawImage(image,0,0,canvas.getWidth(),canvas.getHeight());
    }

    public void CustomGreen(MouseEvent mouseEvent) {
        WritableImage image=logica.customColor("Green",
                new WritableImage(buffer.ReturnNotDelet().getPixelReader(), (int) buffer.ReturnNotDelet().getWidth(), (int) buffer.ReturnNotDelet().getHeight())
                ,SliderGreen.getValue());
        graphicsContext.drawImage(image,0,0,canvas.getWidth(),canvas.getHeight());
    }

    public void CustomLight(MouseEvent mouseEvent) {
        WritableImage image=logica.customColor("Light",
                new WritableImage(buffer.ReturnNotDelet().getPixelReader(), (int) buffer.ReturnNotDelet().getWidth(), (int) buffer.ReturnNotDelet().getHeight())
                ,SliderLight.getValue());
        graphicsContext.drawImage(image,0,0,canvas.getWidth(),canvas.getHeight());
    }

    public void CustomSaturation(MouseEvent mouseEvent) {
        WritableImage image=logica.customColor("Sut",
                new WritableImage(buffer.ReturnNotDelet().getPixelReader(), (int) buffer.ReturnNotDelet().getWidth(), (int) buffer.ReturnNotDelet().getHeight())
                ,SliderSaturation.getValue());
        graphicsContext.drawImage(image,0,0,canvas.getWidth(),canvas.getHeight());
    }

    public void CustomWarm(MouseEvent mouseEvent) {
        WritableImage image=logica.customColor("Warm",
                new WritableImage(buffer.ReturnNotDelet().getPixelReader(), (int) buffer.ReturnNotDelet().getWidth(), (int) buffer.ReturnNotDelet().getHeight())
                ,SliderWarm.getValue());
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

                MaskImage.setImage(imask.getMask(i));
                try {
                    AncPane.getChildren().add(MaskImage);
                } catch (Exception e){
                    System.out.println("Возникла ошибка, но ничего страшного");
                }

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
                    buffer.PutImeginn(new WritableImage(logica.getWritableImageFromCanvas(canvas).getPixelReader(), (int) canvas.getWidth(), (int) canvas.getHeight()));
                    graphicsContext.drawImage(MaskImage.getImage(), MaskImage.getLayoutX()-delta, MaskImage.getLayoutY(), MaskImage.getFitWidth(), MaskImage.getFitHeight());
                    AncPane.getChildren().remove(MaskImage);
                    ReturneFoto.setVisible(true);
                    countM++;
                }
            }
        });
        pane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                    if (countM % 2 == 1 &&
                            mouseEvent.getY() < MainClassApplication.heightScene - MaskImage.getFitHeight() - 56 &&
                            mouseEvent.getX() < delta + canvas.getWidth() - MaskImage.getFitWidth() &&
                            mouseEvent.getX() > delta) {
                        MaskImage.setLayoutY(mouseEvent.getY());
                        MaskImage.setLayoutX(mouseEvent.getX());
                    }

            }
        });
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
        writablImage=logica.getWritableImageFromCanvas(canvas);
        buffer.PutImeginn(new WritableImage(logica.getWritableImageFromCanvas (canvas).getPixelReader(),(int) canvas.getWidth(), (int) canvas.getHeight()));
        writr=writablImage.getPixelWriter();
        pn.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(countM%2==1&&colorCho!=null) {
                    try {
                            if (mouseEvent.getY() < MainClassApplication.heightScene - 56 &&
                                    mouseEvent.getX() < canvas.getWidth() + delta &&
                                    mouseEvent.getX() > delta) {
                                logica.drow(writr, (int) mouseEvent.getX() - delta, (int) mouseEvent.getY(), (int) size.getValue(), colorCho);
                                graphicsContext.drawImage(writablImage, 0, 0, canvas.getWidth(), canvas.getHeight());
                                ReturneFoto.setVisible(true);
                                ReturneFoto.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent mouseEvent) {
                                        writablImage=null;
                                        writablImage=new WritableImage(buffer.ReturnNotDelet().getPixelReader(), (int) canvas.getWidth(), (int) canvas.getHeight());
                                        buffer.PutImeginn(new WritableImage(writablImage.getPixelReader(),(int) canvas.getWidth(), (int) canvas.getHeight()));
                                        writr=writablImage.getPixelWriter();
                                    }
                                });

                            }

                    }catch (Exception e){
                        System.out.println("Произошла ошибка, но ничего страшного, ТЯУ ТЯУ ТЯУ");
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
        if(canvas.getWidth()>canvas.getHeight()) {
            pane.setLayoutX(canvas.getLayoutX()+40);
        }
        else {
            pane.setLayoutX(canvas.getLayoutX()+180);
        }
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
                    WritableImage wImage = new WritableImage(reader, (int) rect.getX()-delta, (int) rect.getY(), width, height);
                    AncPane.getChildren().remove(rect);
                    buffer.PutImeginn(logica.getWritableImageFromCanvas(canvas));
                    graphicsContext.drawImage(wImage,0,0,canvas.getWidth(), canvas.getHeight());


                    countM++;
                }
            }
        });
        pane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(countM%2==1){
                    if(mouseEvent.getY()-rect.getY()>0&&mouseEvent.getX()-rect.getX()>0) {
                        rect.setHeight(mouseEvent.getY() - rect.getY());
                        rect.setWidth(mouseEvent.getX() - rect.getX());
                    }
                }
            }
        });

    }

    public void returnLastFoto(ActionEvent actionEvent) {
            WritableImage image = buffer.ReturnImeginn();
            graphicsContext.drawImage(image,0,0,canvas.getWidth(), canvas.getHeight());
            if(!buffer.isNullImageStack()) {
                ReturneFoto.setVisible(false);
            }
    }

}
