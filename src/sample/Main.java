package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.InputStream;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Stack;
import javafx.scene.text.Font;

public class Main extends Application {
    private VBox rollpane;
    private VBox scoreSheet;
    private VBox scoreValues;
    private int rolls;
    private int turns;

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox root = new HBox(20);
        root.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY, Insets.EMPTY)));
        root.setPadding(new Insets(20));
        rollpane = new VBox();
        primaryStage.setTitle("Yahtzee");
        root.getChildren().add(rollpane);
        Scene scene = new Scene(root, 630, 630);
        primaryStage.setScene(scene);
        diceObject d1 = new diceObject((int)(Math.random() * 5) + 1);
        diceObject d2 = new diceObject((int)(Math.random() * 5) + 1);
        diceObject d3 = new diceObject((int)(Math.random() * 5) + 1);
        diceObject d4 = new diceObject((int)(Math.random() * 5) + 1);
        diceObject d5 = new diceObject((int)(Math.random() * 5) + 1);
        ToggleButton roll = new ToggleButton("Roll");
        Player player = new Player();
        rolls = 4;
        turns = 0;
        Label displayTurnNumber = new Label(turns + "/13 Turns");
        Label displayedScore = new Label("Score: " + player.getScore());
        Label rollCount = new Label("Rolls left: " + rolls);
        rollpane.getChildren().addAll(d1.getTbutton(), d2.getTbutton(), d3.getTbutton(), d4.getTbutton(), d5.getTbutton(), roll, displayedScore, rollCount, displayTurnNumber);
        Stack<diceObject> saved = new Stack<diceObject>();
        d1.getTbutton().setOnAction(event -> {
            d1.getTbutton().setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
            if(d1.getTbutton().isSelected()) {
                d1.getTbutton().setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                saved.add(d1);
            }
            if(!d1.getTbutton().isSelected()) {
                d1.getTbutton().setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
                saved.pop();
            }
            System.out.println(saved);
        });
        d2.getTbutton().setOnAction(event -> {
            d2.getTbutton().setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
            if(d2.getTbutton().isSelected()) {
                d2.getTbutton().setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                saved.add(d2);
            }
            if(!d2.getTbutton().isSelected()) {
                d2.getTbutton().setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
                saved.pop();
            }
        });
        d3.getTbutton().setOnAction(event -> {
            d3.getTbutton().setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
            if(d3.getTbutton().isSelected()) {
                d3.getTbutton().setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                saved.add(d3);
            }
            if(!d3.getTbutton().isSelected()) {
                d3.getTbutton().setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
                saved.pop();
            }
        });
        d4.getTbutton().setOnAction(event -> {
            d4.getTbutton().setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
            if(d4.getTbutton().isSelected()) {
                d4.getTbutton().setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                saved.add(d4);
            }
            if(!d4.getTbutton().isSelected()) {
                d4.getTbutton().setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
                saved.pop();
            }
        });
        d5.getTbutton().setOnAction(event -> {
            d5.getTbutton().setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
            if(d5.getTbutton().isSelected()) {
                d5.getTbutton().setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                saved.add(d1);
            }
            if(!d5.getTbutton().isSelected()) {
                d5.getTbutton().setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
                saved.pop();
            }
        });
        roll.setOnAction(ActionEvent -> {
            runDice(saved);
            d1.getTbutton().setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
            d2.getTbutton().setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
            d3.getTbutton().setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
            d4.getTbutton().setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
            d5.getTbutton().setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
            rolls -= 1;
            updateCalculations(chosenDice(d1,d2,d3,d4,d5), player, displayedScore, d1, d2, d3, d4, d5, rollCount, roll, displayTurnNumber);
            System.out.println(Arrays.toString(chosenDice(d1,d2,d3,d4,d5)));
            checkRolls( d1, d2 ,d3 ,d4, d5, roll);
            rollCount.setText("Rolls Left: " + rolls);
            updateLabels(chosenDice(d1,d2,d3,d4,d5));
        });

        scoreSheet = new VBox(10);
        root.getChildren().add(scoreSheet);
        int[] diceInfo = chosenDice(d1, d2, d3, d4, d5);
        int[] ub = {calculateSingles(diceInfo, 1), calculateSingles(diceInfo, 2), calculateSingles(diceInfo, 3), calculateSingles(diceInfo, 4), calculateSingles(diceInfo, 5), calculateSingles(diceInfo, 6)};
        ToggleButton Ones = new ToggleButton("Ones ");
        Ones.setMaxWidth(150);
        ToggleButton Twos = new ToggleButton("Twos ");
        Twos.setMaxWidth(150);
        ToggleButton Threes = new ToggleButton("Threes ");
        Threes.setMaxWidth(150);
        ToggleButton Fours = new ToggleButton("Fours ");
        Fours.setMaxWidth(150);
        ToggleButton Fives = new ToggleButton("Fives ");
        Fives.setMaxWidth(150);
        ToggleButton Sixes = new ToggleButton("Sixes ");
        Sixes.setMaxWidth(150);
        ToggleButton threeOfAKind = new ToggleButton("Three of a kind ");
        threeOfAKind.setMaxWidth(150);
        ToggleButton fourOfAKind = new ToggleButton("Four of a kind ");
        fourOfAKind.setMaxWidth(150);
        ToggleButton fullHouse = new ToggleButton("Full House ");
        fullHouse.setMaxWidth(150);
        ToggleButton smallStraight = new ToggleButton("Small Straight ");
        smallStraight.setMaxWidth(150);
        ToggleButton largeStraight = new ToggleButton("Large Straight ");
        largeStraight.setMaxWidth(150);
        ToggleButton Yahtzee = new ToggleButton(("Yahtzee "));
        Yahtzee.setMaxWidth(150);
        ToggleButton chance = new ToggleButton("Chance ");
        chance.setMaxWidth(150);
        Text UpperBonus = new Text("Upper Bonus: ");
        UpperBonus.setFont(new Font(15));
        Text YahtzeeBonus = new Text("Yahtzee Bonus: ");
        YahtzeeBonus.setFont(new Font(15));
        scoreSheet.getChildren().addAll(Ones, Twos, Threes, Fours, Fives, Sixes, UpperBonus, threeOfAKind, fourOfAKind, fullHouse, smallStraight, largeStraight, Yahtzee, chance, YahtzeeBonus);
        System.out.println(Arrays.toString(diceInfo));
        scoreValues = new VBox(10);
        root.getChildren().add(scoreValues);
        Text displayOnes = new Text(calculateSingles(diceInfo, 1) + "");
        Text displayTwos = new Text(calculateSingles(diceInfo,2) + "");
        Text displayThrees = new Text(calculateSingles(diceInfo,3) + "");
        Text displayFours = new Text(calculateSingles(diceInfo,4) + "");
        Text displayFives = new Text(calculateSingles(diceInfo,5) + "");
        Text displaySixes = new Text(calculateSingles(diceInfo,6) + "");
        Text display3OfAKind = new Text(calculateThreeOrFourOfAKind(diceInfo, 3) + "");
        Text display4OfAKind = new Text(calculateThreeOrFourOfAKind(diceInfo,4) + "");
        Text displayFullHouse = new Text(calculateFullhouses(diceInfo) + "");
        Text displayUpperBonus = new Text(calculateUpperBonus(ub) + "");
        Text displaySmallStraight = new Text(calculateSmallOrLargeStraights(diceInfo, 3) + "");
        Text displayLargeStraight = new Text(calculateSmallOrLargeStraights(diceInfo,4) + "");
        Text displayYahtzee = new Text(calculateYahtzee(diceInfo) + "");
        Text displayChance = new Text(calculateChance(diceInfo) + "");
        Text displayYahtzeeBonus = new Text(calculateYahtzeeBonus(diceInfo) + "");
        scoreValues.getChildren().addAll(displayOnes, displayTwos, displayThrees, displayFours, displayFives, displaySixes, displayUpperBonus,  display3OfAKind, display4OfAKind, displayFullHouse, displaySmallStraight, displayLargeStraight, displayYahtzee, displayChance, displayYahtzeeBonus);
        scoreValues.setSpacing(19);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public ToggleButton setDice(int num) {
        InputStream input = getClass().getResourceAsStream("/images/Alea_" + num + ".png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        ToggleButton label = new ToggleButton();
        label.setGraphic(imageView);
        return label;
    }

    public ToggleButton resetDice(int num, ToggleButton tButton) {
        InputStream input = getClass().getResourceAsStream("/images/Alea_" + num + ".png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        tButton.setGraphic(imageView);
        return tButton;
    }

    public void runDice(Stack<diceObject> stack) throws EmptyStackException {
        try {
            for (int i = 0; i < 5; i++) {
                int random = (int)(Math.random() * 6) + 1;
                stack.peek().setNumber(random);
                stack.peek().setTbutton(random, stack.peek().getTbutton());
                stack.pop();
            }
            stack.empty();
        } catch (EmptyStackException e) {

        }
    }

    public int calculateUpperBonus(int[] arr){
        boolean bool = false;
        int values = arraySum(arr);
        if(values > 62){
            bool = true;
        }
        if(bool){
            return 35;
        }
        return 0;
    }

    public int arraySum(int[] arr){
        int sum = 0;
        for(int x : arr){
            sum += x;
        }
        return sum;
    }

    public int calculateSingles(int[] arr, int num){
        int sum = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == num){
                sum+= num;
            }
        }
        return sum;
    }

    public int calculateFullhouses(int[] arr){
        if(calculateThreeOrFourOfAKind(arr, 3) > 0){
            if(arr[0] == arr[4]){
                return 25;
            }
            if(arr[0] == arr[1]){
                return 25;
            }
            if(arr[3] == arr[4]){
                return 25;
            }
        }
        return 0;
    }

    public int calculateThreeOrFourOfAKind(int[] arr, int style){
        Arrays.sort(arr);
        int value = 0;
        int sum = 0;
        for(int i = 0; i < arr.length; i++){
            sum += arr[i];
        }
        if(style == 3){
            if(arr[0] == arr[1] && arr[1] == arr[2]){
                return sum;
            }
            else if(arr[1] == arr[2] && arr[2] == arr[3]){
                return sum;
            }
            else if(arr[2] == arr[3] && arr[3] == arr[4]){
                return sum;
            }
        }
        else if(style == 4){
            if(arr[0] == arr[1] && arr[1] == arr[2] && arr[2] == arr[3]){
                return sum;
            }
            else if(arr[1] == arr[2] && arr[2] == arr[3] && arr[3] == arr[4]){
                return sum;
            }
        }

        return value;
    }

    public int calculateSmallOrLargeStraights(int[] arr, int style){
        Arrays.sort(arr);
        int ones = calculateOccurences(arr,1);
        int twos = calculateOccurences(arr, 2);
        int threes = calculateOccurences(arr, 3);
        int fours = calculateOccurences(arr, 4);
        int fives = calculateOccurences(arr, 5);
        int sixes = calculateOccurences(arr, 6);
        if (style == 4){
            if(ones != 0 && twos != 0 && threes != 0 && fours != 0 || twos != 0 && threes != 0 && fours != 0 && fives != 0 ||  threes != 0 && fours != 0 && fives != 0 && sixes != 0){
                return 30;
            }
        } else if(style == 5){
            if(ones != 0 && twos != 0 && threes != 0 && fours != 0 && fives != 0 || twos != 0 && threes != 0 && fours != 0 && fives != 0 && sixes != 0){
                return 40;
            }
        }
        return 0;
    }

    private int calculateOccurences(int[] arr, int num){
        int occurences = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == num){
                occurences++;
            }
        }
        return occurences;
    }

    private int calculateYahtzee(int[] arr){
        if(arr[0] == arr[1] && arr[1] == arr[2] && arr[3] == arr[4]){
            return 50;
        }

        return 0;
    }

    private int calculateYahtzeeBonus(int[] arr){
        if(arr[0] == arr[1] && arr[1] == arr[2] && arr[3] == arr[4] && arr[4] == arr[0] && arr[0] == 6){
            return 100;
        }
        return 0;
    }

    public int calculateChance(int[] arr){
        int sum = 0;
        for(int i = 0; i < arr.length; i++){
            sum += arr[i];
        }
        return sum;
    }

    public int[] chosenDice(diceObject one, diceObject two, diceObject three, diceObject four, diceObject five) {
        int[] diceValues = new int[5];
        diceValues[0] = one.getNumber();
        diceValues[1] = two.getNumber();
        diceValues[2] = three.getNumber();
        diceValues[3] = four.getNumber();
        diceValues[4] = five.getNumber();
        return diceValues;
    }

    public void updateCalculations(int[] diceInfo, Player player, Label displayedScore, diceObject d1, diceObject d2, diceObject d3, diceObject d4, diceObject d5, Label rCount, ToggleButton roll, Label tCount){
        int[] ubb = new int[6];
        ToggleButton O = new ToggleButton("Ones ");
        O.setMaxWidth(150);
        ToggleButton T = new ToggleButton("Twos ");
        T.setMaxWidth(150);
        ToggleButton Th = new ToggleButton("Threes ");
        Th.setMaxWidth(150);
        ToggleButton F = new ToggleButton("Fours ");
        F.setMaxWidth(150);
        ToggleButton Fi = new ToggleButton("Fives ");
        Fi.setMaxWidth(150);
        ToggleButton S = new ToggleButton("Sixes ");
        S.setMaxWidth(150);
        ToggleButton thOfAKind = new ToggleButton("Three of a kind ");
        thOfAKind.setMaxWidth(150);
        ToggleButton fOfAKind = new ToggleButton("Four of a kind ");
        fOfAKind.setMaxWidth(150);
        ToggleButton fllHouse = new ToggleButton("Full House ");
        fllHouse.setMaxWidth(150);
        ToggleButton sStraight = new ToggleButton("Small Straight ");
        sStraight.setMaxWidth(150);
        ToggleButton lStraight = new ToggleButton("Large Straight ");
        lStraight.setMaxWidth(150);
        ToggleButton y = new ToggleButton("Yahtzee ");
        y.setMaxWidth(150);
        ToggleButton ch = new ToggleButton("Chance ");
        ch.setMaxWidth(150);
        Text ub = new Text ("Upper Bonus: ");
        ub.setFont(new Font(15));
        Text yb = new Text("Yahtzee Bonus: ");
        yb.setFont(new Font(15));
        boolean[] disabled = new boolean[13];
        scoreSheet.getChildren().setAll(O, T, Th, F, Fi, S, ub, thOfAKind, fOfAKind, fllHouse, sStraight, lStraight, y, ch, yb);
        if(calculateUpperBonus(ubb) > 34){
            player.setScore(calculateUpperBonus(ubb));
            displayedScore.setText("Score: " + player.getScore());
        }
        if(calculateYahtzeeBonus(diceInfo) > 99){
            player.setScore(calculateYahtzeeBonus(diceInfo));
            displayedScore.setText("Score: " + player.getScore());
        }
        /*
        Text displayOnes = new Text(calculateSingles(diceInfo, 1) + "");
        Text displayTwos = new Text(calculateSingles(diceInfo,2) + "");
        Text displayThrees = new Text(calculateSingles(diceInfo,3) + "");
        Text displayFours = new Text(calculateSingles(diceInfo,4) + "");
        Text displayFives = new Text(calculateSingles(diceInfo,5) + "");
        Text displaySixes = new Text(calculateSingles(diceInfo,6) + "");
        Text display3OfAKind = new Text(calculateThreeOrFourOfAKind(diceInfo, 3) + "");
        Text display4OfAKind = new Text(calculateThreeOrFourOfAKind(diceInfo,4) + "");
        Text displayFullHouse = new Text(calculateFullhouses(diceInfo) + "");
        Text displayUpperBonus = new Text(calculateUpperBonus(ubb) + "");
        Text displaySmallStraight = new Text(calculateSmallOrLargeStraights(diceInfo, 3) + "");
        Text displayLargeStraight = new Text(calculateSmallOrLargeStraights(diceInfo,4) + "");
        Text displayYahtzee = new Text(calculateYahtzee(diceInfo) + "");
        Text displayChance = new Text(calculateChance(diceInfo) + "");
        Text displayYahtzeeBonus = new Text(calculateYahtzeeBonus(diceInfo) + "");
        scoreValues.getChildren().setAll(displayOnes, displayTwos, displayThrees, displayFours, displayFives, displaySixes, display3OfAKind, display4OfAKind, displayFullHouse, displayUpperBonus, displaySmallStraight, displayLargeStraight, displayYahtzee, displayChance, displayYahtzeeBonus);
        scoreValues.setSpacing(19);*/
        O.setOnAction(ActionEvent  -> {
            player.setScore(calculateSingles(diceInfo, 1));
            ubb[0] = calculateSingles(diceInfo,1);
            resetDice(((int)(Math.random() * 6) + 1), d1.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d2.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d3.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d4.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d5.getTbutton());
            d1.getTbutton().setDisable(false);
            d2.getTbutton().setDisable(false);
            d3.getTbutton().setDisable(false);
            d4.getTbutton().setDisable(false);
            d5.getTbutton().setDisable(false);
            roll.setDisable(false);
            rolls = 3;
            turns += 1;
            updateLabels(diceInfo);
            tCount.setText(turns + "/13 Turns");
            rCount.setText("Rolls Left: " + rolls);
            displayedScore.setText("Score: " + player.getScore());
        });
        T.setOnAction(ActionEvent -> {
            player.setScore(calculateSingles(diceInfo, 2));
            ubb[1] = calculateSingles(diceInfo,2);
            resetDice(((int)(Math.random() * 6) + 1), d1.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d2.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d3.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d4.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d5.getTbutton());
            d1.getTbutton().setDisable(false);
            d2.getTbutton().setDisable(false);
            d3.getTbutton().setDisable(false);
            d4.getTbutton().setDisable(false);
            d5.getTbutton().setDisable(false);
            roll.setDisable(false);
            rolls = 3;
            turns += 1;
            System.out.println("waffles");
            //updateLabels(diceInfo);
            tCount.setText(turns + "/13 Turns");
            rCount.setText("Rolls Left: " + rolls);
            displayedScore.setText("Score: " + player.getScore());
        });
        Th.setOnAction(ActionEvent -> {
            player.setScore(calculateSingles(diceInfo, 3));
            ubb[2] = calculateSingles(diceInfo,3);
            resetDice(((int)(Math.random() * 6) + 1), d1.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d2.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d3.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d4.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d5.getTbutton());
            d1.getTbutton().setDisable(false);
            d2.getTbutton().setDisable(false);
            d3.getTbutton().setDisable(false);
            d4.getTbutton().setDisable(false);
            d5.getTbutton().setDisable(false);
            roll.setDisable(false);
            rolls = 3;
            turns += 1;
            //updateLabels(diceInfo);
            tCount.setText(turns + "/13 Turns");
            rCount.setText("Rolls Left: " + rolls);
            displayedScore.setText("Score: " + player.getScore());
        });
        F.setOnAction(ActionEvent -> {
            player.setScore(calculateSingles(diceInfo, 4));
            ubb[3] = calculateSingles(diceInfo,4);
            resetDice(((int)(Math.random() * 6) + 1), d1.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d2.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d3.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d4.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d5.getTbutton());
            d1.getTbutton().setDisable(false);
            d2.getTbutton().setDisable(false);
            d3.getTbutton().setDisable(false);
            d4.getTbutton().setDisable(false);
            d5.getTbutton().setDisable(false);
            roll.setDisable(false);
            rolls = 3;
            turns += 1;
            //updateLabels(diceInfo);
            tCount.setText(turns + "/13 Turns");
            rCount.setText("Rolls Left: " + rolls);
            displayedScore.setText("Score: " + player.getScore());
        });
        Fi.setOnAction(ActionEvent -> {
            player.setScore(calculateSingles(diceInfo, 5));
            ubb[4] = calculateSingles(diceInfo,5);
            resetDice(((int)(Math.random() * 6) + 1), d1.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d2.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d3.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d4.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d5.getTbutton());
            d1.getTbutton().setDisable(false);
            d2.getTbutton().setDisable(false);
            d3.getTbutton().setDisable(false);
            d4.getTbutton().setDisable(false);
            d5.getTbutton().setDisable(false);
            roll.setDisable(false);
            rolls = 3;
            turns += 1;
            //updateLabels(diceInfo);
            tCount.setText(turns + "/13 Turns");
            rCount.setText("Rolls Left: " + rolls);
            displayedScore.setText("Score: " + player.getScore());
        });
        S.setOnAction(ActionEvent -> {
            player.setScore(calculateSingles(diceInfo, 6));
            ubb[5] = calculateSingles(diceInfo,6);
            resetDice(((int)(Math.random() * 6) + 1), d1.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d2.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d3.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d4.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d5.getTbutton());
            d1.getTbutton().setDisable(false);
            d2.getTbutton().setDisable(false);
            d3.getTbutton().setDisable(false);
            d4.getTbutton().setDisable(false);
            d5.getTbutton().setDisable(false);
            roll.setDisable(false);
            rolls = 3;
            turns += 1;
            //updateLabels(diceInfo);
            tCount.setText(turns + "/13 Turns");
            rCount.setText("Rolls Left: " + rolls);
            displayedScore.setText("Score: " + player.getScore());
        });
        thOfAKind.setOnAction(ActionEvent -> {
            player.setScore(calculateThreeOrFourOfAKind(diceInfo, 3));
            resetDice(((int)(Math.random() * 6) + 1), d1.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d2.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d3.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d4.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d5.getTbutton());
            d1.getTbutton().setDisable(false);
            d2.getTbutton().setDisable(false);
            d3.getTbutton().setDisable(false);
            d4.getTbutton().setDisable(false);
            d5.getTbutton().setDisable(false);
            roll.setDisable(false);
            rolls = 3;
            turns += 1;
            //updateLabels(diceInfo);
            tCount.setText(turns + "/13 Turns");
            rCount.setText("Rolls Left: " + rolls);
            displayedScore.setText("Score: " + player.getScore());
        });
        fOfAKind.setOnAction(ActionEvent -> {
            player.setScore(calculateThreeOrFourOfAKind(diceInfo, 3));
            resetDice(((int)(Math.random() * 6) + 1), d1.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d2.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d3.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d4.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d5.getTbutton());
            d1.getTbutton().setDisable(false);
            d2.getTbutton().setDisable(false);
            d3.getTbutton().setDisable(false);
            d4.getTbutton().setDisable(false);
            d5.getTbutton().setDisable(false);
            roll.setDisable(false);
            rolls = 3;
            turns += 1;
            //updateLabels(diceInfo);
            tCount.setText(turns + "/13 Turns");
            rCount.setText("Rolls Left: " + rolls);
            displayedScore.setText("Score: " + player.getScore());
        });
        sStraight.setOnAction(ActionEvent -> {
            player.setScore(calculateSmallOrLargeStraights(diceInfo,4));
            resetDice(((int)(Math.random() * 6) + 1), d1.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d2.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d3.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d4.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d5.getTbutton());
            d1.getTbutton().setDisable(false);
            d2.getTbutton().setDisable(false);
            d3.getTbutton().setDisable(false);
            d4.getTbutton().setDisable(false);
            d5.getTbutton().setDisable(false);
            roll.setDisable(false);
            rolls = 3;
            turns += 1;
            //updateLabels(diceInfo);
            tCount.setText(turns + "/13 Turns");
            rCount.setText("Rolls Left: " + rolls);
            displayedScore.setText("Score: " + player.getScore());
        });
        lStraight.setOnAction(ActionEvent -> {
            player.setScore(calculateSmallOrLargeStraights(diceInfo,5));
            resetDice(((int)(Math.random() * 6) + 1), d1.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d2.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d3.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d4.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d5.getTbutton());
            d1.getTbutton().setDisable(false);
            d2.getTbutton().setDisable(false);
            d3.getTbutton().setDisable(false);
            d4.getTbutton().setDisable(false);
            d5.getTbutton().setDisable(false);
            roll.setDisable(false);
            rolls = 3;
            turns += 1;
            //updateLabels(diceInfo);
            tCount.setText(turns + "/13 Turns");
            rCount.setText("Rolls Left: " + rolls);
            displayedScore.setText("Score: " + player.getScore());
        });
        fllHouse.setOnAction(ActionEvent -> {
            player.setScore(calculateFullhouses(diceInfo));
            resetDice(((int)(Math.random() * 6) + 1), d1.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d2.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d3.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d4.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d5.getTbutton());
            d1.getTbutton().setDisable(false);
            d2.getTbutton().setDisable(false);
            d3.getTbutton().setDisable(false);
            d4.getTbutton().setDisable(false);
            d5.getTbutton().setDisable(false);
            roll.setDisable(false);
            rolls = 3;
            turns += 1;
            //updateLabels(diceInfo);
            tCount.setText(turns + "/13 Turns");
            rCount.setText("Rolls Left: " + rolls);
            displayedScore.setText("Score: " + player.getScore());
        });
        ch.setOnAction(ActionEvent -> {
            player.setScore(calculateChance(diceInfo));
            resetDice(((int)(Math.random() * 6) + 1), d1.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d2.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d3.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d4.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d5.getTbutton());
            d1.getTbutton().setDisable(false);
            d2.getTbutton().setDisable(false);
            d3.getTbutton().setDisable(false);
            d4.getTbutton().setDisable(false);
            d5.getTbutton().setDisable(false);
            roll.setDisable(false);
            rolls = 3;
            turns += 1;
            //updateLabels(diceInfo);
            tCount.setText(turns + "/13 Turns");
            rCount.setText("Rolls Left: " + rolls);
            displayedScore.setText("Score: " + player.getScore());
        });
        y.setOnAction(ActionEvent -> {
            player.setScore(calculateYahtzee(diceInfo));
            resetDice(((int)(Math.random() * 6) + 1), d1.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d2.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d3.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d4.getTbutton());
            resetDice(((int)(Math.random() * 6) + 1), d5.getTbutton());
            d1.getTbutton().setDisable(false);
            d2.getTbutton().setDisable(false);
            d3.getTbutton().setDisable(false);
            d4.getTbutton().setDisable(false);
            d5.getTbutton().setDisable(false);
            roll.setDisable(false);
            rolls = 3;
            turns += 1;
            //updateLabels(diceInfo);
            tCount.setText(turns + "/13 Turns");
            rCount.setText("Rolls Left: " + rolls);
            displayedScore.setText("Score: " + player.getScore());
        });
        Text dOnes = new Text(calculateSingles(diceInfo, 1) + "");
        Text dTwos = new Text(calculateSingles(diceInfo,2) + "");
        Text dThrees = new Text(calculateSingles(diceInfo,3) + "");
        Text dFours = new Text(calculateSingles(diceInfo,4) + "");
        Text dFives = new Text(calculateSingles(diceInfo,5) + "");
        Text dSixes = new Text(calculateSingles(diceInfo,6) + "");
        Text d3OfAKind = new Text(calculateThreeOrFourOfAKind(diceInfo, 3) + "");
        Text d4OfAKind = new Text(calculateThreeOrFourOfAKind(diceInfo,4) + "");
        Text dFullHouse = new Text(calculateFullhouses(diceInfo) + "");
        Text dUpperBonus = new Text(calculateUpperBonus(ubb) + "");
        Text dSmallStraight = new Text(calculateSmallOrLargeStraights(diceInfo, 3) + "");
        Text dLargeStraight = new Text(calculateSmallOrLargeStraights(diceInfo,4) + "");
        Text dYahtzee = new Text(calculateYahtzee(diceInfo) + "");
        Text dChance = new Text(calculateChance(diceInfo) + "");
        Text dYahtzeeBonus = new Text(calculateYahtzeeBonus(diceInfo) + "");
        scoreValues.getChildren().setAll(dOnes, dTwos, dThrees, dFours, dFives, dSixes, d3OfAKind, d4OfAKind, dFullHouse, dUpperBonus, dSmallStraight, dLargeStraight, dYahtzee, dChance, dYahtzeeBonus);
        scoreValues.setSpacing(19);
      // return disabled;
    }

    public void checkRolls( diceObject d1, diceObject d2, diceObject d3, diceObject d4, diceObject d5, ToggleButton roll) {
        if(rolls < 0){
            d1.getTbutton().setDisable(true);
            d2.getTbutton().setDisable(true);
            d3.getTbutton().setDisable(true);
            d4.getTbutton().setDisable(true);
            d5.getTbutton().setDisable(true);
            roll.setDisable(true);
            rolls = 3;
        }
    }

    public void updateLabels(int[] diceInfo){
        int[] ubb = {calculateSingles(diceInfo, 1), calculateSingles(diceInfo, 2), calculateSingles(diceInfo, 3), calculateSingles(diceInfo, 4), calculateSingles(diceInfo, 5), calculateSingles(diceInfo, 6)};
        Text displayOnes = new Text(calculateSingles(diceInfo, 1) + "");
        Text displayTwos = new Text(calculateSingles(diceInfo,2) + "");
        Text displayThrees = new Text(calculateSingles(diceInfo,3) + "");
        Text displayFours = new Text(calculateSingles(diceInfo,4) + "");
        Text displayFives = new Text(calculateSingles(diceInfo,5) + "");
        Text displaySixes = new Text(calculateSingles(diceInfo,6) + "");
        Text display3OfAKind = new Text(calculateThreeOrFourOfAKind(diceInfo, 3) + "");
        Text display4OfAKind = new Text(calculateThreeOrFourOfAKind(diceInfo,4) + "");
        Text displayFullHouse = new Text(calculateFullhouses(diceInfo) + "");
        Text displayUpperBonus = new Text(calculateUpperBonus(ubb) + "");
        Text displaySmallStraight = new Text(calculateSmallOrLargeStraights(diceInfo, 3) + "");
        Text displayLargeStraight = new Text(calculateSmallOrLargeStraights(diceInfo,4) + "");
        Text displayYahtzee = new Text(calculateYahtzee(diceInfo) + "");
        Text displayChance = new Text(calculateChance(diceInfo) + "");
        Text displayYahtzeeBonus = new Text(calculateYahtzeeBonus(diceInfo) + "");
        scoreValues.getChildren().setAll(displayOnes, displayTwos, displayThrees, displayFours, displayFives, displaySixes, display3OfAKind, display4OfAKind, displayFullHouse, displayUpperBonus, displaySmallStraight, displayLargeStraight, displayYahtzee, displayChance, displayYahtzeeBonus);
        scoreValues.setSpacing(19);
    } //*/

    public class Player {
        private int score;

        public Player() {
            score = 0;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score += score;
        }

        @Override
        public String toString() {
            return "Player{" +
                    "score=" + score +
                    '}';
        }
    }

    public class diceObject {
        private int number;
        private ToggleButton tbutton;

        public diceObject() {
            number = 1;
            tbutton = setDice(1);
            tbutton.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
        }

        public diceObject(int num) {
            number = num;
            tbutton = setDice(num);
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public ToggleButton getTbutton() {
            return tbutton;
        }

        public void setTbutton(int number, ToggleButton tbutton) {
            this.tbutton = resetDice(number, tbutton);
        }

        @Override
        public String toString() {
            return "diceObject{" +
                    "number=" + number +
                    ", tbutton=" + tbutton +
                    '}';
        }
    }

}
