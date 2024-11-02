package View;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import Model.College;
import Model.Lecturer.academicDegree;
import Model.PermanentLecturer.committee;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class View {

	// Texts and TextFields:
	private TextField tf1 = new TextField();
	private TextField tf2 = new TextField();
	private TextField tf3 = new TextField();
	private TextField tf4 = new TextField();
	private TextField tf5 = new TextField();
	private TextArea textArea = new TextArea();
	private Text text1 = new Text();
	private Text text2 = new Text();
	private Text print = new Text();

	// Main Menu screen:
	private Button btColMenu = new Button("College Menu");
	private Button btOrgMenu = new Button("Organization Menu");
	private Button btFinish = new Button("Save and Finish");

	// Organization Menu
	private Button btSetChair = new Button("Set Chairman");
	private Button btCompare = new Button("Compare the Organization");
	private Button btAddOrRm = new Button("Add/Remove Lecturer from Org");
	private Button btAddToOrg = new Button("Add to Org");
	private Button btRemoveFromOrg = new Button("Remove from Org");
	private Button btApply = new Button("Apply");
	private Button btUndo = new Button("Undo");

	// College Menu
	private Button btAddCol = new Button("Add New College");
	private Button btInsertCol = new Button("Insert College");
	private Button btMessage = new Button("Send Message");
	private Button btGift = new Button("Holiday Gifts");
	private Button btPrint = new Button("Print Lecturers List");
	private Button btSalSim = new Button("Salary Simulation");

	// Add Lecturer:
	public ComboBox<College> cbCol = new ComboBox<>();
	private Button btAddLec = new Button("Add lecturer");
	private RadioButton rbPerm = new RadioButton("Permanent");
	private RadioButton rbExt = new RadioButton("External");
	private ToggleGroup lecToggle = new ToggleGroup();

	private ComboBox<committee> cbCom = new ComboBox<>();
	private RadioButton rbFirst = new RadioButton("First");
	private RadioButton rbSecond = new RadioButton("Second");
	private RadioButton rbDoctor = new RadioButton("Doctor");
	private RadioButton rbProfessor = new RadioButton("Professor");
	private ToggleGroup degToggle = new ToggleGroup();

	// Salary Simulator 
	//Memento Design Pattern
	public ComboBox<String> cbLec = new ComboBox<>();
	public ComboBox<Integer> cbYear = new ComboBox<>();

	// Accessory buttons:
	private Button btSet = new Button("Set New ChairMan");
	private Button btBack = new Button("Back");
	private Button btBack2 = new Button("Back");
	private Button btReturn = new Button("Return to Main Menu");
	private Button btSendMsg = new Button("Send Message");
	private Button btSendGift = new Button("Send Gift");
	private Button btInsertLec = new Button("Insert New Lecturer");
	private Button btContinue = new Button("Continue");
	private Button btRefresh = new Button("Refresh");

	private boolean exFlag = false;

	public View(Stage primaryStage) {
		// Menu screen:
		VBox menuBox = new VBox(20, btColMenu, btOrgMenu, btFinish);
		menuBox.setAlignment(Pos.TOP_LEFT);
		menuBox.setPadding(new Insets(30));
		Scene mainMenu = new Scene(menuBox, 350, 200);
		primaryStage.setScene(mainMenu);
		primaryStage.setTitle("Main Menu");

		HBox colChooseBox = new HBox(30, new Label("Select College: "), cbCol);
		colChooseBox.setAlignment(Pos.CENTER);

		// Memento Design Pattern
		HBox YearChooseBox = new HBox(30, new Label("Select Year: "), cbYear);
		YearChooseBox.setAlignment(Pos.CENTER_LEFT);

		// Memento Design Pattern
		for (int i = 2021; i <= 2025; i++) {
			cbYear.getItems().add(i);
		}
		HBox LecChooseBox = new HBox(30, new Label("Select Lecturer: "), cbLec);
		LecChooseBox.setAlignment(Pos.CENTER_LEFT);

		HBox lecTypeBox = new HBox(30, rbPerm, rbExt);
		rbPerm.setToggleGroup(lecToggle);
		rbExt.setToggleGroup(lecToggle);
		lecToggle.selectToggle(null);
		lecTypeBox.setAlignment(Pos.CENTER);

		// College Menu
		btColMenu.setOnMouseClicked(e -> {
			VBox colMenu = new VBox(30, btAddCol, btAddLec, btMessage, btGift, btPrint, btSalSim, btReturn);
			colMenu.setAlignment(Pos.CENTER);
			colMenu.setPadding(new Insets(30));
			Scene colMenScene = new Scene(colMenu, 500, 450);
			primaryStage.setTitle("College Menu");
			primaryStage.setScene(colMenScene);

			// Adding new College
			btAddCol.setOnMouseClicked(a -> {
				GridPane addColPane = new GridPane();
				addColPane.add(new Label("College Name: "), 0, 0);
				addColPane.add(tf1, 1, 0);
				addColPane.add(new Label("College City: "), 0, 1);
				addColPane.add(tf2, 1, 1);
				addColPane.add(new Label("College Type: "), 0, 2);
				addColPane.add(tf3, 1, 2);
				addColPane.add(btInsertCol, 1, 3);
				addColPane.add(btBack2, 2, 3);
				addColPane.setPadding(new Insets(30));
				addColPane.setAlignment(Pos.CENTER);
				addColPane.setVgap(20);
				Scene addColScene = new Scene(addColPane, 500, 300);
				primaryStage.setTitle("Adding new College");
				primaryStage.setScene(addColScene);

				btInsertCol.setOnMouseClicked(b -> {
					if (exFlag == false) {
						primaryStage.setScene(colMenScene);
					}
				});
			});

			// Simulating Perm Lecturer Salary
			// Memento Design Pattern
			btSalSim.setOnMouseClicked(a -> {
				try {
					if (cbLec.getItems().isEmpty()) {
						throw new Exception("Please add a new Permanent Lecturers first!");
					} else {
						print.setText("");
						BorderPane simPane = new BorderPane();
						GridPane detBox = new GridPane();
						detBox.setAlignment(Pos.CENTER_LEFT);
						detBox.add(text1, 0, 1);
						detBox.add(print, 1, 2);
						HBox accBox = new HBox(30, btBack2);
						accBox.setAlignment(Pos.CENTER);
						GridPane.setHalignment(text1, HPos.LEFT);
						GridPane chooseBox = new GridPane();
						chooseBox.setAlignment(Pos.CENTER);
						chooseBox.add(LecChooseBox, 0, 1);
						chooseBox.add(YearChooseBox, 0, 3);
						simPane.setTop(chooseBox);
						simPane.setLeft(detBox);
						simPane.setBottom(accBox);
						simPane.setPadding(new Insets(30));
						cbYear.setOnMouseClicked(b -> {
							simPane.getChildren().addAll();
						});
						Scene simScene = new Scene(simPane, 500, 350);
						text1.setText("The list content:");
						primaryStage.setScene(simScene);
						primaryStage.setTitle("Salary Simulation");
					}
				} catch (Exception ex) {
					noCollege(ex.getMessage());
				}
			});

			// Adding new Lecturer
			btAddLec.setOnMouseClicked(a -> {
				try {
					if (cbCol.getItems().isEmpty()) {
						throw new Exception("Please add a new College first!");
					} else {
						BorderPane addLecPane = new BorderPane();
						HBox conBox = new HBox(30, btContinue, btBack2);
						conBox.setAlignment(Pos.CENTER);
						addLecPane.setTop(colChooseBox);
						addLecPane.setCenter(lecTypeBox);
						addLecPane.setBottom(conBox);
						addLecPane.setPadding(new Insets(30));
						Scene addLecScene = new Scene(addLecPane, 600, 200);
						primaryStage.setTitle("Adding new Lecturer");
						primaryStage.setScene(addLecScene);
					}
				} catch (Exception ex) {
					noCollege(ex.getMessage());
				}
				btContinue.setOnMouseClicked(b -> {
					try {
						if (!rbExt.isSelected() && !rbPerm.isSelected()) {
							throw new Exception();
						} else {
							// Data of Lecturer:
							GridPane gPane = new GridPane();
							Scene dataScene = new Scene(gPane, 600, 400);
							gPane.add(new Label("Enter name:"), 0, 0);
							gPane.add(tf1, 1, 0);
							gPane.add(new Label("Enter id:"), 0, 1);
							gPane.add(tf2, 1, 1);

							if (rbPerm.isSelected()) {
								gPane.add(new Label("Enter seniority:"), 0, 2);
								gPane.add(new Label("Enter committee"), 0, 5);
								cbCom.getItems().addAll(committee.Exceptions, committee.Improvement, committee.Teaching,
										committee.Other);
								cbCom.getSelectionModel().select(0);
								gPane.add(cbCom, 1, 5);
							} else if (rbExt.isSelected()) {
								gPane.add(new Label("Enter hours of work:"), 0, 2);
							}

							gPane.add(tf3, 1, 2);
							gPane.add(new Label("Highest degree"), 0, 3);
							HBox degree = new HBox(20, rbFirst, rbSecond, rbDoctor, rbProfessor);
							rbFirst.setToggleGroup(degToggle);
							rbSecond.setToggleGroup(degToggle);
							rbDoctor.setToggleGroup(degToggle);
							rbProfessor.setToggleGroup(degToggle);
							degToggle.selectToggle(null);
							degree.setPadding(new Insets(10));
							degree.setAlignment(Pos.CENTER);
							gPane.add(degree, 1, 3);
							gPane.add(new Label("Enter area of Expertise:"), 0, 4);
							gPane.add(tf4, 1, 4);
							gPane.add(btInsertLec, 1, 6);
							gPane.setAlignment(Pos.CENTER);
							GridPane.setHalignment(btInsertLec, HPos.RIGHT);
							gPane.setPadding(new Insets(12));
							gPane.setHgap(20);
							gPane.setVgap(20);
							primaryStage.setScene(dataScene);
							primaryStage.setTitle("Insert New Lecturer");

							btInsertLec.setOnMouseClicked(c -> {
								if (exFlag == false) {
									primaryStage.setScene(colMenScene);
								}
							});
						}
					} catch (Exception exc) {
						chooseException();
					}
				});
			});

			btPrint.setOnMouseClicked(a -> {
				try {
					if (cbCol.getItems().isEmpty()) {
						throw new Exception("Please add a new College first!");
					} else {
						print.setText("");
						BorderPane printPane = new BorderPane();
						GridPane printBox = new GridPane();
						printBox.setAlignment(Pos.CENTER_LEFT);
						printBox.add(text1, 0, 1);
						printBox.add(print, 1, 2);
						HBox conBox = new HBox(30, btRefresh, btBack2);
						conBox.setAlignment(Pos.CENTER);
						GridPane.setHalignment(text1, HPos.LEFT);
						printPane.setTop(colChooseBox);
						printPane.setLeft(printBox);
						printPane.setBottom(conBox);
						printPane.setPadding(new Insets(30));
						Scene printScene = new Scene(printPane, 500, 600);
						text1.setText("The list content:");
						primaryStage.setScene(printScene);
						primaryStage.setTitle("Print Lecturers List\n");
					}
				} catch (Exception ex) {
					noCollege(ex.getMessage());
				}
			});

			// Sending Messages to Lecturers
			btMessage.setOnMouseClicked(a -> {
				try {
					if (cbCol.getItems().isEmpty()) {
						throw new Exception("Please add a new College first!");
					} else {
						BorderPane emailPane = new BorderPane();
						HBox conBox = new HBox(30, btSendMsg, btBack2);
						conBox.setAlignment(Pos.CENTER);
						GridPane emailGridPane = new GridPane();
						emailGridPane.setAlignment(Pos.CENTER_LEFT);
						emailGridPane.setPadding(new Insets(20));
						emailGridPane.add(new Label("Email Subject:"), 0, 0);
						emailGridPane.add(tf1, 1, 0);
						emailGridPane.add(new Label("Email Content:"), 0, 1);
						emailGridPane.add(textArea, 1, 1);
						emailGridPane.setHgap(20);
						emailGridPane.setVgap(20);
						emailPane.setTop(colChooseBox);
						emailPane.setLeft(emailGridPane);
						emailPane.setBottom(conBox);
						emailPane.setPadding(new Insets(30));
						primaryStage.setTitle("Sending E-mails");
						Scene emailScene = new Scene(emailPane, 800, 400);
						primaryStage.setScene(emailScene);
					}
				} catch (Exception ex) {
					noCollege(ex.getMessage());
				}
			});

			btGift.setOnMouseClicked(a -> {
				try {
					if (cbCol.getItems().isEmpty()) {
						throw new Exception("Please add a new College first!");
					} else {
						BorderPane giftPane = new BorderPane();
						HBox conBox = new HBox(30, btSendGift, btBack2);
						conBox.setAlignment(Pos.CENTER);
						giftPane.setTop(colChooseBox);
						giftPane.setCenter(lecTypeBox);
						giftPane.setBottom(conBox);
						giftPane.setPadding(new Insets(30));
						Scene giftScene = new Scene(giftPane, 600, 200);
						primaryStage.setTitle("Gifts for the Holidays");
						primaryStage.setScene(giftScene);
					}
				} catch (Exception ex) {
					noCollege(ex.getMessage());
				}
			});
			btBack2.setOnMouseClicked(a -> {
				primaryStage.setScene(colMenScene);
			});
		});

		btOrgMenu.setOnMouseClicked(e -> {
			FlowPane orgMenu = new FlowPane();
			orgMenu.setVgap(20);
			orgMenu.setHgap(20);
			orgMenu.getChildren().add(btAddOrRm);
			orgMenu.getChildren().add(btSetChair);
			orgMenu.getChildren().add(btCompare);
			orgMenu.getChildren().add(btReturn);
			orgMenu.setAlignment(Pos.CENTER);
			orgMenu.setPadding(new Insets(30));
			Scene orgMenuScene = new Scene(orgMenu, 650, 150);
			primaryStage.setTitle("Organization Menu");
			primaryStage.setScene(orgMenuScene);

			// Adding or Removing Lecturer from Organization
			// Command Design Pattern
			btAddOrRm.setOnMouseClicked(a -> {
				GridPane addOrRmPane = new GridPane();
				addOrRmPane.add(new Label("Please enter Lecturer ID:"), 0, 0);
				addOrRmPane.add(tf2, 1, 0);
				HBox accessBox = new HBox(30, btAddToOrg, btRemoveFromOrg, btBack);
				addOrRmPane.add(accessBox, 1, 1);
				HBox applyUndoBox = new HBox(30, btApply, btUndo);
				addOrRmPane.add(applyUndoBox, 1, 2);
				applyUndoBox.setAlignment(Pos.CENTER);
				addOrRmPane.setPadding(new Insets(30));
				addOrRmPane.setAlignment(Pos.CENTER);
				addOrRmPane.setVgap(20);
				addOrRmPane.setHgap(20);
				Scene addOrRmScene = new Scene(addOrRmPane, 600, 150);
				primaryStage.setTitle("Adding Or Removing Lecturers from Organizations");
				primaryStage.setScene(addOrRmScene);
			});

			btSetChair.setOnMouseClicked(a -> {
				GridPane setChairPane = new GridPane();
				setChairPane.add(new Label("Please enter Lecturer ID:"), 0, 0);
				setChairPane.add(tf2, 1, 0);
				setChairPane.add(new Label("Select Organization Type:"), 0, 1);
				setChairPane.add(lecTypeBox, 1, 1);
				setChairPane.add(btSet, 1, 2);
				setChairPane.add(btBack, 1, 2);
				GridPane.setHalignment(btBack, HPos.RIGHT);
				setChairPane.setPadding(new Insets(30));
				setChairPane.setAlignment(Pos.CENTER);
				setChairPane.setVgap(20);
				setChairPane.setHgap(20);
				Scene addOrRmScene = new Scene(setChairPane, 600, 150);
				primaryStage.setTitle("Setting new Chairman");
				primaryStage.setScene(addOrRmScene);
			});

			btCompare.setOnMouseClicked(a -> {
				GridPane comparePane = new GridPane();
				Scene compareScene = new Scene(comparePane, 600, 200);
				comparePane.setPadding(new Insets(30));
				comparePane.setAlignment(Pos.TOP_LEFT);
				comparePane.add(new Label("Permanent Organization members:"), 0, 0);
				comparePane.add(text1, 1, 0);
				comparePane.add(new Label("External Organization members:"), 0, 1);
				comparePane.add(text2, 1, 1);
				comparePane.add(new Label("Comparing result: "), 0, 2);
				comparePane.add(print, 1, 2);
				comparePane.add(btBack, 1, 3);
				comparePane.setHgap(20);
				comparePane.setVgap(20);
				GridPane.setHalignment(btBack, HPos.RIGHT);
				primaryStage.setTitle("Comparing the two Organizations");
				primaryStage.setScene(compareScene);
			});
			btBack.setOnMouseClicked(a -> {
				primaryStage.setScene(orgMenuScene);
			});

		});

		btFinish.setOnMouseClicked(e -> {
			primaryStage.close();
		});

		btReturn.setOnMouseClicked(e -> {
			primaryStage.setScene(mainMenu);
		});

		primaryStage.show();
	}

	public boolean isExFlag() {
		return exFlag;
	}

	public void setExFlag(boolean exFlag) {
		this.exFlag = exFlag;
	}

	public void setText1(String text1) {
		this.text1.setText(text1);
	}

	public void setText2(String text2) {
		this.text2.setText(text2);
	}

	public void setPrint(String print) {
		this.print.setText(print);
	}

	public void clearFields() {
		textArea.clear();
		tf1.clear();
		tf2.clear();
		tf3.clear();
		tf4.clear();
		tf5.clear();
		cbCom.getItems().clear();
	}

	// Memento Design Pattern
	public void simAction(EventHandler<ActionEvent> e) {
		cbYear.setOnAction(e);
	}

	public void printAction(EventHandler<ActionEvent> e) {
		btRefresh.setOnAction(e);
	}

	public void setChairAction(EventHandler<ActionEvent> e) {
		btSet.setOnAction(e);
	}

	// Command Design Pattern
	public void applyAction(EventHandler<ActionEvent> e) {
		btApply.setOnAction(e);
	}

	// Command Design Pattern
	public void undoAction(EventHandler<ActionEvent> e) {
		btUndo.setOnAction(e);
	}

	public void addToOrgAction(EventHandler<ActionEvent> e) {
		btAddToOrg.setOnAction(e);
	}

	public void removeFromOrgAction(EventHandler<ActionEvent> e) {
		btRemoveFromOrg.setOnAction(e);
	}

	public void colInsertAction(EventHandler<ActionEvent> e) {
		btInsertCol.setOnAction(e);
	}

	public void lecInsertAction(EventHandler<ActionEvent> e) {
		btInsertLec.setOnAction(e);
	}

	public void compareAction(EventHandler<ActionEvent> e) {
		btCompare.setOnAction(e);
	}

	public void sendMsgAction(EventHandler<ActionEvent> e) {
		btSendMsg.setOnAction(e);
	}

	public void sendGiftAction(EventHandler<ActionEvent> e) {
		btSendGift.setOnAction(e);
	}

	public void setAction(EventHandler<ActionEvent> e) {
		btSet.setOnAction(e);
	}

	public void finishAction(EventHandler<ActionEvent> e) {
		btFinish.setOnAction(e);
	}

	public String getText1() {
		return tf1.getText();
	}

	public String getText2() {
		return tf2.getText();
	}

	public String getText3() {
		return tf3.getText();
	}

	public String getText4() {
		return tf4.getText();
	}

	public String getText5() {
		return tf5.getText();
	}

	public char lecUpdate() throws Exception {
		if (rbPerm.isSelected()) {
			return 'P';
		} else if (rbExt.isSelected()) {
			return 'E';
		}
		throw new Exception();
	}

	public academicDegree degreeUpdate() {
		if (rbFirst.isSelected()) {
			return academicDegree.First;
		} else if (rbSecond.isSelected()) {
			return academicDegree.Second;
		} else if (rbDoctor.isSelected()) {
			return academicDegree.Doctor;
		} else if (rbProfessor.isSelected()) {
			return academicDegree.Professor;
		}
		return null;
	}

	public committee commUpdate() {
		if (cbCom.getValue() == committee.Exceptions) {
			return committee.Exceptions;
		} else if (cbCom.getValue() == committee.Improvement) {
			return committee.Improvement;
		} else if (cbCom.getValue() == committee.Teaching) {
			return committee.Teaching;
		} else if (cbCom.getValue() == committee.Other) {
			return committee.Other;
		}
		return null;
	}

	public College collUpdate() {
		return cbCol.getValue();
	}

	// Memento Design Pattern
	public String lecSimUpdate() {
		return cbLec.getValue();
	}

	// Memento Design Pattern
	public int yearUpdate() {
		return cbYear.getValue();
	}

	public void invalidID() {
		tf2.clear();
		JOptionPane.showMessageDialog(null, "Your id must be 9 digits!");
	}

	public void negativeNumber() {
		tf3.clear();
		tf4.clear();
		JOptionPane.showMessageDialog(null, "The intput has to be at least 0!");
	}

	public void invalidFormat() {
		tf2.clear();
		tf3.clear();
		tf4.clear();
		JOptionPane.showMessageDialog(null, "Invalid input, integer is needed!");
	}

	public void hoursException(String msg) {
		tf4.clear();
		JOptionPane.showMessageDialog(null, "You can`t work more than 14 hours");
	}

	public void chooseException() {
		JOptionPane.showMessageDialog(null, "Please Choose Lecturer Type!");
	}

	public void sendEmail() {
		JOptionPane.showMessageDialog(null, "Your email was sent successfully!");
	}

	public void addRmSuccess(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	public void welcomeToCollege(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	public void noCollege(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	public void chairmanMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	public void sendGift(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	public void insertNewCol(ArrayList<College> coll) {
		cbCol.getItems().clear();
		cbCol.getItems().addAll(coll);
		cbCol.getSelectionModel().select(0);
	}

	// Memento Design Pattern
	public void insertNewLec(String name) {
		cbLec.getItems().addAll(name);
		cbLec.getSelectionModel().select(0);
	}
}
