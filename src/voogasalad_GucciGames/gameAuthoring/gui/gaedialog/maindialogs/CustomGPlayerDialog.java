package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.CheckBoxField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.GameSettingParams;

public class CustomGPlayerDialog extends GaeDialog{

    private Stage gameSettingDialog = new Stage();
    private VBox myContent = new VBox();
    private VBox parent = new VBox();
    private Properties prop;
    private IDialogGaeController dialogGaeController;
    private IGuiGaeController guiGaeController;
    private DialogElements dialogElements;
    private GameSettingParams gameSettingParams = new GameSettingParams();
    private Button saveButton;
    private CheckBoxField actionDisplay;
    private Integer propIndexer;
    private List<String> guiList = new ArrayList<String>();
    private List<List<CheckBoxField>> checkBoxFields = new ArrayList<List<CheckBoxField>>();
    private List<List<String>> allCheckedBoxes = new ArrayList<List<String>>(); 
    private int numBars;
    private int maxBars=3; //assume there are only 3 bars: left, bottom, right
    private int maxComponents;

    public CustomGPlayerDialog(IDialogGaeController dialogGaeController, IGuiGaeController guiGaeController){
        this.dialogGaeController = dialogGaeController;
        this.guiGaeController = guiGaeController;
        for (int i=0; i<maxBars; i++) {
            checkBoxFields.add(new ArrayList<CheckBoxField>());
            allCheckedBoxes.add(new ArrayList<String>());
        }
        prop = super.loadProperties("/voogasalad_GucciGames/gameAuthoring/gui/gaedialog/maindialogs/dialogproperties/customgplayerdialog.properties");
        dialogElements = new DialogElements(prop, null, dialogGaeController);
        saveButton = new Button(prop.getProperty("save"));
        setSaveAction();
        prop.forEach((key,value) -> {
            String skey=(String)key;
            if (skey.startsWith(prop.getProperty("componentprefix")+prop.getProperty("componentdelimiter"))) {
                guiList.add(prop.getProperty(skey));
            }
        });
        maxComponents=guiList.size();
        myContent.getChildren().addAll(this.initializeDialog(), saveButton);
        Scene gameSettingDialogScene = new Scene(myContent, Integer.parseInt(prop.getProperty("width")), Integer.parseInt(prop.getProperty("height")));
        gameSettingDialogScene.getStylesheets().add("voogasalad_GucciGames/gameAuthoring/gui/gaedialog/stylesheets/dialogstylesheet.css");
        gameSettingDialog.setScene(gameSettingDialogScene);
    }

    protected void setSaveAction(){
        saveButton.setOnAction(e -> {
            for (List<CheckBoxField> cb: checkBoxFields) {
                for (CheckBoxField cbf: cb) {
                    if (cbf.getCheckBox().isSelected()) {
                        if (!allCheckedBoxes.get(Integer.parseInt((String) cbf.getUserData())).contains(cbf.getPropKey())) {
                            allCheckedBoxes.get(Integer.parseInt((String) cbf.getUserData())).add(cbf.getPropKey());
                        }
                    }
                }
            }
            System.out.println(allCheckedBoxes);
            guiGaeController.setCustomGamePlayerLeftComponents(allCheckedBoxes.get(0));
            guiGaeController.setCustomGamePlayerBottomComponents(allCheckedBoxes.get(1));
            guiGaeController.setCustomGamePlayerRightComponents(allCheckedBoxes.get(2));
            this.gameSettingDialog.close();
        });
    }

    @Override
    protected VBox initializeDialog() {
        GridPane content = new GridPane();				
        Text titleElement = new Text();
        titleElement.setText(prop.getProperty("title"));
        Text label = new Text(prop.getProperty("leftlabel"));
        GridPane.setConstraints(label,1,0);
        content.getChildren().add(label);
        label = new Text(prop.getProperty("bottomlabel"));
        GridPane.setConstraints(label,2,0);
        content.getChildren().add(label);
        label = new Text(prop.getProperty("rightlabel"));
        GridPane.setConstraints(label,3,0);
        content.getChildren().add(label);
        initializeCheckBoxes(content);
        //setCheckBoxListeners();
        content.getChildren().forEach(hbox->hbox.setId("hbox-element"));		
        titleElement.setId("title");
        content.setId("vbox-element");	
        parent.getChildren().add(content);
        return parent;
    }

    protected void initializeCheckBoxes(GridPane content) {
        for (propIndexer=0;propIndexer<maxComponents;propIndexer++) {
            Text label = new Text(guiList.get(propIndexer));
            GridPane.setConstraints(label,0,propIndexer+1);
            content.getChildren().add(label);
            for (numBars=0;numBars<maxBars;numBars++) {
                prop.forEach(((key,value)->{
                    String skey = (String)key;
                    String svalue = (String)value;
                    if (svalue.equals(guiList.get(propIndexer))) {
                        actionDisplay = new CheckBoxField(dialogElements,(skey.split(prop.getProperty("componentdelimiter")))[1]);
                    }
                }));
                actionDisplay.setUserData(""+numBars);
                checkBoxFields.get(numBars).add(actionDisplay);
                GridPane.setConstraints(actionDisplay,numBars+1,propIndexer+1);
                content.getChildren().add(actionDisplay);
            }
        }
        for (numBars=0;numBars<maxBars;numBars++) {
            loadCheckBoxState(numBars,checkBoxFields.get(numBars));
        }
    }

    private void loadCheckBoxState (int index, List<CheckBoxField> boxes) {
        switch (index) {
            case 0:
                for (CheckBoxField c: boxes) {
                    if (guiGaeController.getCustomGamePlayerLeftComponents().contains(c.getPropKey())) {
                        c.getCheckBox().setSelected(true);
                    }
                }
                break;
            case 1:
                for (CheckBoxField c: boxes) {
                    if (guiGaeController.getCustomGamePlayerBottomComponents().contains(c.getPropKey())) {
                        c.getCheckBox().setSelected(true);
                    }
                }
                break;
            case 2:
                for (CheckBoxField c: boxes) {
                    if (guiGaeController.getCustomGamePlayerRightComponents().contains(c.getPropKey())) {
                        c.getCheckBox().setSelected(true);
                    }
                }
                break;
            default:
                break;
        }
    }

    public void showCustomGPlayerDialog(){
        super.showDialog(gameSettingDialog);
    }

//    private void setCheckBoxListeners(){
//        for (numBars=0; numBars<maxBars; numBars++){
//            for(int i=0; i<checkBoxFields.get(numBars).size(); i++){
//                CheckBoxField currCheckBoxField = checkBoxFields.get(numBars).get(i);
//                currCheckBoxField.getCheckBox().selectedProperty().addListener((new ChangeListener<Boolean>() {
//                    public void changed(ObservableValue<? extends Boolean> ov,
//                                        Boolean old_val, Boolean new_val) {
//                        if(new_val){
//                            allCheckedBoxes.get(Integer.parseInt((String) currCheckBoxField.getUserData())).add(currCheckBoxField.getPropKey());
//                        }else{
//                            allCheckedBoxes.get(Integer.parseInt((String) currCheckBoxField.getUserData())).remove(currCheckBoxField.getPropKey());
//                        }
//                    }
//                }));
//            }
//        }
//    }
}
