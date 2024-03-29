package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;


public class ClientController extends Thread{
    public Label lblClientName;
    public JFXTextField txtType;
    public ScrollPane txtField;
    public AnchorPane context = new AnchorPane();



    public AnchorPane emojiPane;
    public JFXButton button_send;
    public VBox vbox_messages;


    BufferedReader reader;
    PrintWriter writer;
    Socket socket;

    private FileChooser fileChooser;
    private File filePath;


    public void initialize(){
        String userName=LoginFormController.name;
        lblClientName.setText(LoginFormController.name);


        try {
            socket = new Socket("localhost", 5000);
            System.out.println("Socket is connected with server!");
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void EmojiOnAction(MouseEvent mouseEvent) throws IOException {
        emojiPane.setVisible(true);

    }


    public void CamOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        this.filePath = fileChooser.showOpenDialog(stage);
        writer.println(lblClientName.getText() + " " + "img" + filePath.getPath());
    }

    public void sendOnAction(ActionEvent actionEvent) {
        String msg = txtType.getText();
        writer.println(lblClientName.getText() + ": " + msg);

        txtType.clear();

        if(msg.equalsIgnoreCase("BYE") || (msg.equalsIgnoreCase("logout"))) {
            System.exit(0);

        }
    }

@Override
    public void run() {
        try {
            while (true) {


                String msg = reader.readLine();
                String[] tokens = msg.split(" ");
                String cmd = tokens[0];


                StringBuilder fullMsg = new StringBuilder();
                for (int i = 1; i < tokens.length; i++) {
                    fullMsg.append(tokens[i]+" ");
                }


                String[] msgToAr = msg.split(" ");
                String st = "";
                for (int i = 0; i < msgToAr.length - 1; i++) {
                    st += msgToAr[i + 1] + " ";
                }


                Text text = new Text(st);
                String firstChars = "";
                if (st.length() > 3) {
                    firstChars = st.substring(0, 3);

                }


                if (firstChars.equalsIgnoreCase("img")) {
                    //for the Images

                    st = st.substring(3, st.length() - 1);


                    File file = new File(st);
                    Image image = new Image(file.toURI().toString());

                    ImageView imageView = new ImageView(image);

                    imageView.setFitHeight(150);
                    imageView.setFitWidth(200);


                    HBox hBox = new HBox(10);
                    hBox.setAlignment(Pos.BOTTOM_RIGHT);


                    if (!cmd.equalsIgnoreCase(lblClientName.getText())) {

                        vbox_messages.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);


                        Text text1 = new Text("  " + cmd + " :");
                        hBox.getChildren().add(text1);
                        hBox.getChildren().add(imageView);

                    } else {
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(imageView);
                        Text text1 = new Text(": Me ");
                        hBox.getChildren().add(text1);

                    }

                    Platform.runLater(() -> vbox_messages.getChildren().addAll(hBox));


                } else {

                    TextFlow tempFlow = new TextFlow();

                    if (!cmd.equalsIgnoreCase(lblClientName.getText() + ":")) {
                        Text txtName = new Text(cmd + " ");
                        txtName.getStyleClass().add("txtName");
                        tempFlow.getChildren().add(txtName);
                    }

                    tempFlow.getChildren().add(text);
                    tempFlow.setMaxWidth(200); //200

                    TextFlow flow = new TextFlow(tempFlow);

                    HBox hBox = new HBox(12); //12




                    if (!cmd.equalsIgnoreCase(lblClientName.getText() + ":")) {


                        vbox_messages.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);
                        hBox.getChildren().add(flow);

                    } else {

                        Text text2 = new Text(fullMsg + ": Me");
                        TextFlow flow2 = new TextFlow(text2);
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(flow2);
                    }

                    Platform.runLater(() -> vbox_messages.getChildren().addAll(hBox));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enterOnAction(ActionEvent actionEvent) {
        button_send.fire();
    }


    public void Heart(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(128525));
        txtType.setText(emoji);
        emojiPane.setVisible(false);
    }

    public void sadMood(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(128546));
        txtType.setText(emoji);
        emojiPane.setVisible(false);
    }

    public void normalMood(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars( 128522));
        txtType.setText(emoji);
        emojiPane.setVisible(false);
    }

    public void Hehe(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(128513));
        txtType.setText(emoji);
        emojiPane.setVisible(false);
    }

    public void ToungOut(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(128539));
        txtType.setText(emoji);
        emojiPane.setVisible(false);
    }

    public void sick(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(128560));
        txtType.setText(emoji);
        emojiPane.setVisible(false);
    }

    public void Hiks(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(128540));
        txtType.setText(emoji);
        emojiPane.setVisible(false);
    }

    public void soSad(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(128554));
        txtType.setText(emoji);
        emojiPane.setVisible(false);
    }

    public void haha(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(128514));
        txtType.setText(emoji);
        emojiPane.setVisible(false);
    }

    public void Emotional(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(128578));
        txtType.setText(emoji);
        emojiPane.setVisible(false);
    }

    public void bad(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(128543));
        txtType.setText(emoji);
        emojiPane.setVisible(false);
    }

    public void money(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(129297));
        txtType.setText(emoji);
        emojiPane.setVisible(false);
    }

    public void satisfied(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(128519));
        txtType.setText(emoji);
        emojiPane.setVisible(false);
    }

    public void ohh(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(128550));
        txtType.setText(emoji);
        emojiPane.setVisible(false);
    }

    public void wow(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(128559));
        txtType.setText(emoji);
        emojiPane.setVisible(false);
    }

}

