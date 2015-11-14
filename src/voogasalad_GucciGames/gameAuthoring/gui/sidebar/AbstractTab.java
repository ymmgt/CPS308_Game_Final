package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.TileMaker;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.UnitMaker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class AbstractTab extends Tab implements ITab{
	protected List<String> allImagePaths;
	protected List<ImageView> allImageViews;
	protected VBox myVBox = new VBox(30);
	protected Button myAddButton = new Button("Add Custom");
	protected GridPane myGridPane = new GridPane();
	protected ContextMenu myContextMenu = new ContextMenu();

	AbstractTab(Stage stage) {
		allImageViews = new ArrayList<ImageView>();
		this.setClosable(false);
		createEmptyContent(stage);
		createMenu();
	}
	
	protected void createMenu(){
		MenuItem item1 = new MenuItem("Edit");
		item1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println("Edit");
			}
		});
		MenuItem item2 = new MenuItem("Duplicate");
		item2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println("Duplicate");
			}
		});
		MenuItem item3 = new MenuItem("Remove");
		item3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println("Remove");
			}
		});
		myContextMenu.getItems().addAll(item1, item2, item3);
	}

	protected void createEmptyContent(Stage stage) {
		myVBox.setMinWidth(stage.getWidth()/4);

		myGridPane.setMinWidth(stage.getWidth()/4);
		myGridPane.setVgap(10);
		myGridPane.setHgap(10);
		myGridPane.setPadding(new Insets(10, 10, 10, 10));

		myAddButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				//dialog
//				TileMaker tileMaker = new TileMaker();
//				tileMaker.showTileMakerDialog();
				UnitMaker unitMaker = new UnitMaker();
				unitMaker.showUnitMakerDialog();
			}
		});
		
		myVBox.getChildren().addAll(myGridPane, myAddButton);
		
		this.setContent(myVBox);
	}

	protected void addImages() {
		int i=0;
		int j=0;
		for(String path : allImagePaths){
			Image image = new Image(getClass().getClassLoader().getResourceAsStream(path));
			ImageView imageView = new ImageView(image);
			allImageViews.add(imageView);

			imageView.setFitHeight(30);
			imageView.setFitWidth(30);

			myGridPane.add(imageView, i, j);
			if(i>4){ i=0;j++;}
			else{ i++;}
		}
	}

	protected void addImageHandler(){
		for(ImageView imageview : allImageViews) {
			imageview.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent e) {
	                if (e.getButton() == MouseButton.SECONDARY) {
	                	myContextMenu.show(imageview, e.getScreenX(), e.getScreenY());
	                } else {
	                    System.out.println("No right click");
	                }
	            }
	        });
		}
	}
	
	protected void addDragDropListener(){
		for(ImageView imageview : allImageViews) {
			imageview.setOnDragDetected(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
			        /* drag was detected, start a drag-and-drop gesture*/
			        /* allow any transfer mode */
			        Dragboard db = imageview.startDragAndDrop(TransferMode.ANY);
			        
			        /* Put a image on a dragboard */
			        ClipboardContent content = new ClipboardContent();
			        content.putImage(imageview.getImage());
			        db.setContent(content);
			        
			        event.consume();
			    }
			});

		}
	}

}
