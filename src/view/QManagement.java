package view;

import control.SysData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import model.Player;
import model.Question;
import model.QuestionLevel;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class QManagement implements Initializable {

    @FXML
    Button btnDelete;
    @FXML
    ComboBox<String> ComboDelete;


    @FXML
    Button btnUpdate;
    @FXML
    ComboBox<String> ComboChooseQuestion;
    @FXML
    ComboBox<String> ComboUpdateTeam;
    @FXML
    ComboBox<String> ComboUpdateLevel;
    @FXML
    TextField UpdateQuestionBody;
    @FXML
    TextField UpdateAnswer1;
    @FXML
    TextField UpdateAnswer2;
    @FXML
    TextField UpdateAnswer3;
    @FXML
    TextField UpdateAnswer4;
    @FXML
    ComboBox<Integer> ComboUpdateCorrectAns;
    @FXML
    TextArea dlt_question_body;
    @FXML
    Button btnInsert;
    @FXML
    TextField InsertQuestionBody;
    @FXML
    ComboBox<String> CoInsertteams;
    @FXML
    ComboBox<String> CoInsertlevel;
    @FXML
    TextField InsertAnswer1;
    @FXML
    TextField InsertAnswer2;
    @FXML
    TextField InsertAnswer3;
    @FXML
    TextField InsertAnswer4;
    @FXML
    ComboBox<Integer> CoInsertCorrectAnswer;

    public void glow(MouseEvent mouse) {
        BorderPane b = (BorderPane) mouse.getSource();
        DropShadow borderGlow = new DropShadow();
        borderGlow.setColor(Color.GREEN);
        borderGlow.setHeight(83.52);
        borderGlow.setRadius(43.6075);
        borderGlow.setSpread(0.6);
        borderGlow.setWidth(92.91);
        b.setEffect(borderGlow);

    }


    @FXML
    public void clearGlow(MouseEvent mouse) {
        BorderPane b = (BorderPane) mouse.getSource();
        b.setEffect(null);
    }


    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        //  ComboDelete.add
        ArrayList<Question> ArrQ = SysData.getInstance().getQuestions();
        for (Question Q : ArrQ) {
            String s = Q.getQuestion();
            ComboDelete.getItems().add(s);
            ComboChooseQuestion.getItems().add(s);
        }

        String[] teams = {"Chimp", "Crocodile", "Scorpion", "Giraffe", "Spider", "Viper", "Panther", "Wolf", "Sloth", "Lion", "Panda", "Piranha", "Rabbit", "Shark", "Hawk", "Husky"};
        ComboUpdateTeam.getItems().addAll(teams);
        CoInsertteams.getItems().addAll(teams);

        ArrayList<String> levels = new ArrayList<>();
        QuestionLevel[] values = QuestionLevel.values();
        for (QuestionLevel ql : values)
            levels.add(ql.toString());
        ComboUpdateLevel.getItems().addAll(levels);
        CoInsertlevel.getItems().addAll(levels);


        ArrayList<Integer> CoreectAns = new ArrayList<>();
        CoreectAns.add(1);
        CoreectAns.add(2);
        CoreectAns.add(3);
        CoreectAns.add(4);

        CoInsertCorrectAnswer.getItems().addAll(CoreectAns);
        ComboUpdateCorrectAns.getItems().addAll(CoreectAns);

        ComboDelete.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    dlt_question_body.setText(SysData.getInstance().getQuestion(newValue).toString());
                }
        );
    }


    @FXML
    public void UpdateQ(ActionEvent event) {
        if (event.getSource().equals(btnUpdate)) {
            System.out.println("x");
            String UpDateThisValue = ComboChooseQuestion.getValue();
            if (UpDateThisValue != null) {
                Question UpdateThisQ = SysData.getInstance().ifExists(UpDateThisValue);
                if (UpdateThisQ != null) {
                    ArrayList<String> UpdatedAnswers = new ArrayList<>();
                    String GetTeamValue = ComboUpdateTeam.getValue();
                    String GetLevelValue = ComboUpdateLevel.getValue();
                    String UpdateBody = UpdateQuestionBody.getText();
                    String UpdateA1 = UpdateAnswer1.getText();
                    String UpdateA2 = UpdateAnswer2.getText();
                    String UpdateA3 = UpdateAnswer3.getText();
                    String UpdateA4 = UpdateAnswer4.getText();
                    int CorrectAns = ComboUpdateCorrectAns.getValue();

                    if (UpdateBody != null && UpdateA1 != null && UpdateA2 != null && UpdateA3 != null && UpdateA4 != null && GetTeamValue != null && GetLevelValue != null && CorrectAns != 0)
                        UpdatedAnswers.add(UpdateA1);
                    UpdatedAnswers.add(UpdateA2);
                    UpdatedAnswers.add(UpdateA3);
                    UpdatedAnswers.add(UpdateA4);
                    String CorrectAnswer = UpdatedAnswers.get(CorrectAns);
                    SysData.getInstance().updateQuestion(UpDateThisValue, UpdateBody, UpdatedAnswers, CorrectAnswer, GetLevelValue, GetTeamValue);

                }


            }
        }

    }


    @FXML
    public void DeleteQuestion(ActionEvent event) {

        if (event.getSource().equals(btnDelete)) {
            System.out.println("yes");
            String OnDeleteQ = ComboDelete.getValue();
            if (OnDeleteQ != null) {
                System.out.println(OnDeleteQ);
                Question Q = SysData.getInstance().ifExists(OnDeleteQ);
                if (Q != null) {
                    SysData.getInstance().deleteQuestion(OnDeleteQ);

                    //    SysData.getInstance().DeleteFromJson(OnDeleteQ);
                    ComboDelete.notifyAll();
                    for (Question q : SysData.getInstance().getQuestions()) {
                        String s = q.getQuestion();
                        ComboDelete.getItems().add(s);

                    }

                }


            }


        }
    }


    @FXML
    public void InsertQ(ActionEvent event) {
        if (event.getSource().equals(btnInsert)) {
            String InsertThisQuestion = InsertQuestionBody.getText();
            if (InsertThisQuestion != null && !InsertThisQuestion.equals("")) {
                String T = CoInsertteams.getValue();
                String L = CoInsertlevel.getValue();
                String An1 = InsertAnswer1.getText();
                String An2 = InsertAnswer2.getText();
                String An3 = InsertAnswer3.getText();
                String An4 = InsertAnswer4.getText();
                String CorrectAnsNum = CoInsertCorrectAnswer.getValue().toString();


                if (T != null && L != null && An1 != null && An2 != null && An3 != null && An4 != null && CorrectAnsNum != "") {
                    ArrayList<String> newAnsInsert = new ArrayList<>();  //answers array
                    newAnsInsert.add(An1);
                    newAnsInsert.add(An2);
                    newAnsInsert.add(An3);
                    newAnsInsert.add(An4);

                    QuestionLevel level = null;
                    switch (L) {
                        case "1":
                            level = QuestionLevel.ONE;
                        case "2":
                            level = QuestionLevel.TWO;
                        case "3":
                            level = QuestionLevel.THREE;
                    }


                    Question InserQuestion = new Question(InsertThisQuestion, newAnsInsert, CorrectAnsNum, level, T);
                    SysData.getInstance().insertQuestion(InserQuestion);
                    System.out.println(SysData.getInstance().getQuestions());
                }

            }
        }
    }


}















