package rpgIsen.Class.gameMechanics;

import java.util.Scanner;

public interface Interaction {
    /**
     * Method to interact with a player
     */
    default String getAnswer(String question, String[] availableAnswers) {
        // Display question
        System.out.println(question);
        Scanner sc = new Scanner(System.in);
        // Get the answer
        String answer = sc.nextLine();
        // Check if the answer is available
        for (String availableAnswer : availableAnswers) {
            if (answer.equals(availableAnswer)) {
                return answer;
            }
        }
        return null;
    }
}
