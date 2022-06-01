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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;


/**
 * Класс контроллер, в котором реализованы некоторые функции обработки изображения.
 */
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
    private Button FilterBut,CorBut,ScaleBut,SafeBut,returneScale,returneDrow,returneLast,apply,returneCust;
    @FXML
    private MenuButton DrowBut;


    /**
     * Функция первичной инциализации.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image file (*.jpg)","*.jpg"),
                new FileChooser.ExtensionFilter("Image file (*.png)","*.png"));
        stage=new Stage();
        logica=new Logica();
        filter=new MFilter();
        buffer=new Buffer();
        imask=new Mask();




        ListFilters.getItems().addAll(filter.getArrFiltrs());
        try {
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
        Stream.close();
        } catch (IOException e) {
            System.out.println("Не удалось инциализировать выбор цвета");
        }

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



        dontClick();

    }

    /**
     * Функция, которая делает основные кнопки невидимыми.
     */
    public void dontClick(){
        FilterBut.setVisible(false);
        CorBut.setVisible(false);
        ScaleBut.setVisible(false);
        DrowBut.setVisible(false);
        SafeBut.setVisible(false);
    }

    /**
     * Функция, которая делает основные кнопки видимыми.
     */
    public void okClick(){
        FilterBut.setVisible(true);
        CorBut.setVisible(true);
        ScaleBut.setVisible(true);
        DrowBut.setVisible(true);
        SafeBut.setVisible(true);
    }

    /**
     * Функция, которая убирает все виджеты с экрана.
     */
    public void clean(){
        returneDrow.setVisible(false);
        returneScale.setVisible(false);
        returneLast.setVisible(false);
        returneCust.setVisible(false);
        apply.setVisible(false);
        ListFilters.setVisible(false);
        FotoToolBar.setVisible(false);
        size.setVisible(false);
        setValueSlider();
        offListMask();
        offChooceColor();
        buffer.clean();
        AncPane.setOnMouseClicked(null);
        AncPane.setOnMouseMoved(null);
        if(MaskImage!=null){
            MaskImage.setVisible(false);
        }
    }

    /**
     * Функция, которая устанавливает значения Slider в 0.
     */
    public void setValueSlider(){
        SliderWarm.setValue(0);
        SliderSaturation.setValue(0);
        SliderLight.setValue(0);
        SliderGreen.setValue(0);
        SliderRed.setValue(0);
        SliderBlue.setValue(0);
    }

    /**
     * Функция, которая делает видимым виджеты для работы с масками.
     */
    public void setListMask(){
        ListMask.setVisible(true);
        iMinus.setVisible(true);
        iPlus.setVisible(true);

    }

    /**
     * Функция, которая делает невидимым виджеты для работы с масками.
     */
    public void offListMask(){
        ListMask.setVisible(false);
        iMinus.setVisible(false);
        iPlus.setVisible(false);

    }

    /**
     * Функция, которая делает видимым виджет для выбора цвета.
     */
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

    /**
     * Функция, которая делает невидимым виджет для выбора цвета.
     */
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

    /**
     * Функция обработчик нажатия на кнопку "Загрузить".
     * Загружает выбранное пользователем изображение в рабочую область.
     * @param actionEvent - действие нажатие на кнопку.
     */
    public void LoudFoto(ActionEvent actionEvent) {
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
                clean();
                okClick();
        }
        catch (Exception e){
            System.out.println("Не удалось загрузить фотографию");
        }

    }

    /**
     * Функция обработчик нажатия на кнопку "Сохранить".
     * Сохраняет изображение.
     * @param actionEvent - действие нажатие на кнопку.
     */
    public void SafeFoto(ActionEvent actionEvent) {
        try {
            WritableImage image=logica.getWritableImageFromCanvas(canvas);
            fileChooser.setInitialFileName("newImage");
            File safeFile = fileChooser.showSaveDialog(stage);
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", safeFile);
        }
        catch (Exception e){
        System.out.println("Не удалось создать файл");
        }
    }

    /**
     * Функция обработчик нажатия на кнопку "Фильтры".
     * Функция, для установки фильтра на изображение.
     * @param actionEvent - действие нажатие на кнопку.
     */
    public void setFiltrs(ActionEvent actionEvent) {
        clean();
        buffer.PutImeginn(logica.getWritableImageFromCanvas(canvas));
        ListFilters.setVisible(true);
        ListFilters.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                graphicsContext.drawImage(filter.setFiltrs(ListFilters.getSelectionModel().getSelectedItem(),
                        new WritableImage(buffer.ReturnNotDelet().getPixelReader(),(int)buffer.ReturnNotDelet().getWidth(),(int)buffer.ReturnNotDelet().getHeight()))
                        ,0,0, canvas.getWidth(), canvas.getHeight());
                        apply.setVisible(true);

            }
        });
    }

    /**
     * Функция обработчик нажатия на кнопку "Коррекция".
     * Делает видимыми виджеты коррекции на экран.
     * @param actionEvent - действие нажатие на кнопку.
     */
    public void parametersOn(ActionEvent actionEvent) {
        clean();
        buffer.PutImeginn(logica.getWritableImageFromCanvas(canvas));
        FotoToolBar.setVisible(true);

    }

    /**
     * Функция обработчик нажатия на SliderRed.
     * Изменяет параметр rgb Red изображения.
     * @param mouseEvent - действие нажатие на кнопку.
     */
    public void CustomRed(MouseEvent mouseEvent) {
        WritableImage image=logica.customColor("Red",
                new WritableImage(buffer.ReturnNotDelet().getPixelReader(), (int) buffer.ReturnNotDelet().getWidth(), (int) buffer.ReturnNotDelet().getHeight()),
                SliderRed.getValue());
        graphicsContext.drawImage(image,0,0,canvas.getWidth(),canvas.getHeight());
        apply.setVisible(true);
    }

    /**
     * Функция обработчик нажатия на SliderBlue.
     * Изменяет параметр rgb Blue изображения.
     * @param mouseEvent - действие нажатие на кнопку.
     */
    public void CustomBlue(MouseEvent mouseEvent) {
        WritableImage image=logica.customColor("Blue",
                new WritableImage(buffer.ReturnNotDelet().getPixelReader(), (int) buffer.ReturnNotDelet().getWidth(), (int) buffer.ReturnNotDelet().getHeight())
                ,SliderBlue.getValue());
        graphicsContext.drawImage(image,0,0,canvas.getWidth(),canvas.getHeight());
        apply.setVisible(true);
    }

    /**
     * Функция обработчик нажатия на SliderGreen.
     * Изменяет параметр rgb Green изображения.
     * @param mouseEvent - действие нажатие на кнопку.
     */
    public void CustomGreen(MouseEvent mouseEvent) {
        WritableImage image=logica.customColor("Green",
                new WritableImage(buffer.ReturnNotDelet().getPixelReader(), (int) buffer.ReturnNotDelet().getWidth(), (int) buffer.ReturnNotDelet().getHeight())
                ,SliderGreen.getValue());
        graphicsContext.drawImage(image,0,0,canvas.getWidth(),canvas.getHeight());
        apply.setVisible(true);
    }

    /**
     * Функция обработчик нажатия на SliderLight.
     * Изменяет яркость изображения.
     * @param mouseEvent - действие нажатие на кнопку.
     */
    public void CustomLight(MouseEvent mouseEvent) {
        WritableImage image=logica.customColor("Light",
                new WritableImage(buffer.ReturnNotDelet().getPixelReader(), (int) buffer.ReturnNotDelet().getWidth(), (int) buffer.ReturnNotDelet().getHeight())
                ,SliderLight.getValue());
        graphicsContext.drawImage(image,0,0,canvas.getWidth(),canvas.getHeight());
        apply.setVisible(true);
    }

    /**
     * Функция обработчик нажатия на SliderSaturation.
     * Изменяет насыщенность изображения.
     * @param mouseEvent - действие нажатие на кнопку.
     */
    public void CustomSaturation(MouseEvent mouseEvent) {
        WritableImage image=logica.customColor("Sut",
                new WritableImage(buffer.ReturnNotDelet().getPixelReader(), (int) buffer.ReturnNotDelet().getWidth(), (int) buffer.ReturnNotDelet().getHeight())
                ,SliderSaturation.getValue());
        graphicsContext.drawImage(image,0,0,canvas.getWidth(),canvas.getHeight());
        apply.setVisible(true);
    }

    /**
     * Функция обработчик нажатия на SliderWarm.
     * Изменяет теплоту изображения.
     * @param mouseEvent - действие нажатие на кнопку.
     */
    public void CustomWarm(MouseEvent mouseEvent) {
        WritableImage image=logica.customColor("Warm",
                new WritableImage(buffer.ReturnNotDelet().getPixelReader(), (int) buffer.ReturnNotDelet().getWidth(), (int) buffer.ReturnNotDelet().getHeight())
                ,SliderWarm.getValue());
        graphicsContext.drawImage(image,0,0,canvas.getWidth(),canvas.getHeight());
        apply.setVisible(true);
    }

    /**
     * Функция обработчик нажатия на кнопку "Маски".
     * Содержит алгоритм для установки маски на изображение.
     * @param actionEvent - действие нажатие на кнопку.
     */
    public void setMaskOnFoto(ActionEvent actionEvent) {
        clean();
        setListMask();
        countB=0;
        countM=0;
        MaskImage=new ImageView();
        MaskImage.setLayoutX(delta);
        MaskImage.setLayoutY(0);
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
        AncPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(countM%2==0) {
                    MaskImage.setLayoutX(mouseEvent.getX());
                    MaskImage.setLayoutY(mouseEvent.getY());
                    countM++;
                }
                else {
                    if(MaskImage.getImage()!=null) {
                        buffer.PutImeginn(logica.getWritableImageFromCanvas(canvas));
                        graphicsContext.drawImage(MaskImage.getImage(), MaskImage.getLayoutX() - delta, MaskImage.getLayoutY(), MaskImage.getFitWidth(), MaskImage.getFitHeight());
                        returneLast.setVisible(true);
                        AncPane.getChildren().remove(MaskImage);
                        MaskImage.setImage(null);
                    }
                    countM++;
                }
            }
        });
        AncPane.setOnMouseMoved(new EventHandler<MouseEvent>() {
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

    /**
     * Функция обработчик нажатия на кнопку "Рисование".
     * Содержит алгоритм для рисование на изображении.
     * @param actionEvent - действие нажатие на кнопку.
     */
    public void DrowAction(ActionEvent actionEvent) {
        clean();
        setChooserColor();
        size.setVisible(true);
        countM=0;
        writablImage=logica.getWritableImageFromCanvas(canvas);
        buffer.PutImeginn(writablImage);
        writr=writablImage.getPixelWriter();
        AncPane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(countM%2==1&&colorCho!=null) {
                    try {
                            if (mouseEvent.getY() < MainClassApplication.heightScene - 56 &&
                                    mouseEvent.getX() < canvas.getWidth() + delta &&
                                    mouseEvent.getX() > delta) {
                                logica.drow(writr, (int) mouseEvent.getX() - delta, (int) mouseEvent.getY(), (int) size.getValue(), colorCho);
                                graphicsContext.drawImage(writablImage, 0, 0, canvas.getWidth(), canvas.getHeight());
                                returneDrow.setVisible(true);
                            }

                    }catch (Exception e){
                        System.out.println("Произошла ошибка, но это не критично");
                    }
                }
            }
        });

        AncPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                    countM++;
            }
        });
    }

    /**
     * Функция обработчик нажатия на кнопку "Обрезать".
     * Содержит алгоритм для обрезания изображения.
     * @param actionEvent - действие нажатие на кнопку.
     */
    public void SkaleFoto(ActionEvent actionEvent) {
        clean();
        countM=0;
        javafx.scene.shape.Rectangle rect=new javafx.scene.shape.Rectangle();
        rect.setStroke(Color.BLUE);
        rect.setStrokeWidth(1);
        rect.setStrokeLineCap(StrokeLineCap.ROUND);
        rect.setFill(Color.LIGHTBLUE.deriveColor(0, 1.2, 1, 0.6));
        AncPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getX()>delta&&mouseEvent.getX()<delta+canvas.getWidth()&&mouseEvent.getY()< canvas.getHeight()) {
                    if (countM % 2 == 0) {
                        AncPane.getChildren().add(rect);
                        rect.setX(mouseEvent.getX());
                        rect.setY(mouseEvent.getY());
                        countM++;
                    } else {
                        WritableImage image = logica.getWritableImageFromCanvas(canvas);
                        int width = (int) (rect.getWidth());
                        int height = (int) (rect.getHeight());
                        PixelReader reader = image.getPixelReader();
                        WritableImage wImage = new WritableImage(reader, (int) rect.getX() - delta, (int) rect.getY(), width, height);
                        AncPane.getChildren().remove(rect);
                        buffer.PutImeginn(logica.getWritableImageFromCanvas(canvas));
                        if (wImage.getWidth() < wImage.getHeight() && delta == 40) {
                            AncPane.getChildren().remove(canvas);
                            canvas = new Canvas(330, MainClassApplication.heightScene - 56);
                            delta = 180;
                            logica.moveCanvas(canvas, delta, 0);
                            graphicsContext = canvas.getGraphicsContext2D();
                            AncPane.getChildren().add(canvas);
                        }
                        if (wImage.getWidth() > wImage.getHeight() && delta == 180) {
                            AncPane.getChildren().remove(canvas);
                            canvas = new Canvas(630, MainClassApplication.heightScene - 56);
                            delta = 40;
                            logica.moveCanvas(canvas, delta, 0);
                            graphicsContext = canvas.getGraphicsContext2D();
                            AncPane.getChildren().add(canvas);
                        }
                        graphicsContext.drawImage(wImage, 0, 0, canvas.getWidth(), canvas.getHeight());
                        returneScale.setVisible(true);
                        countM++;
                    }
                }
            }
        });
        AncPane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getY()-rect.getY()>0&&mouseEvent.getX()-rect.getX()>0&&countM%2==1&&mouseEvent.getX()>delta&&mouseEvent.getX()<delta+canvas.getWidth()&&mouseEvent.getY()< canvas.getHeight()){

                        rect.setHeight(mouseEvent.getY() - rect.getY());
                        rect.setWidth(mouseEvent.getX() - rect.getX());

                }
            }
        });

    }

    /**
     * Функция обработчик нажатия на кнопку "Отмена".
     * Устанавливает на холст последнюю картинку из буффера.
     * @param actionEvent - действие нажатие на кнопку.
     */
    public void ReturnScale(ActionEvent actionEvent) {
        if(canvas.getWidth()>canvas.getHeight()&&buffer.ReturnNotDelet().getWidth()<buffer.ReturnNotDelet().getHeight()){
            AncPane.getChildren().remove(canvas);
            canvas=new Canvas(330,MainClassApplication.heightScene-56);
            delta=180;
            logica.moveCanvas(canvas,delta,0);
            graphicsContext=canvas.getGraphicsContext2D();
            AncPane.getChildren().add(canvas);
        }
        if(canvas.getWidth()<canvas.getHeight()&&buffer.ReturnNotDelet().getWidth()>buffer.ReturnNotDelet().getHeight()){
            AncPane.getChildren().remove(canvas);
            canvas=new Canvas(630,MainClassApplication.heightScene-56);
            delta=40;
            logica.moveCanvas(canvas,delta,0);
            graphicsContext=canvas.getGraphicsContext2D();
            AncPane.getChildren().add(canvas);
        }
        graphicsContext.drawImage(buffer.ReturnImeginn(),0,0, canvas.getWidth(), canvas.getHeight());
        if(buffer.isNullImageStack()){
            returneScale.setVisible(false);
        }
    }

    /**
     * Функция обработчик нажатия на кнопку "Отмена".
     * Устанавливает на холст последнюю картинку из буффера.
     * @param actionEvent - действие нажатие на кнопку.
     */
    public void ReturnDrow(ActionEvent actionEvent) {
        writablImage=new WritableImage(buffer.ReturnNotDelet().getPixelReader(), (int) canvas.getWidth(), (int) canvas.getHeight());
        buffer.PutImeginn(writablImage);
        writr=writablImage.getPixelWriter();
        graphicsContext.drawImage(buffer.ReturnImeginn(),0,0, canvas.getWidth(), canvas.getHeight());
        if (buffer.isNullImageStack()){
            returneScale.setVisible(false);
        }
    }

    /**
     * Функция обработчик нажатия на кнопку "Отмена".
     * Устанавливает на холст последнюю картинку из буффера.
     * @param actionEvent - действие нажатие на кнопку.
     */
    public void ReturnLast(ActionEvent actionEvent) {
        graphicsContext.drawImage(buffer.ReturnImeginn(),0,0, canvas.getWidth(), canvas.getHeight());
        if (buffer.isNullImageStack()){
            returneLast.setVisible(false);
        }
    }

    /**
     * Функция обработчик нажатия на кнопку "Set".
     * Помещает в буффер изображение с холста.
     * @param actionEvent - действие нажатие на кнопку.
     */
    public void Apply(ActionEvent actionEvent) {
        buffer.PutImeginn(logica.getWritableImageFromCanvas(canvas));
        apply.setVisible(false);
        returneCust.setVisible(true);
    }

    /**
     * Функция обработчик нажатия на кнопку "Отмена".
     * Устанавливает на холст последнюю картинку из буффера.
     * @param actionEvent - действие нажатие на кнопку.
     */
    public void ReturnCust(ActionEvent actionEvent) {
        buffer.delete();
        graphicsContext.drawImage(buffer.ReturnNotDelet(),0,0, canvas.getWidth(), canvas.getHeight());
        if (buffer.getSize()==1){
            returneCust.setVisible(false);
        }

    }
}
