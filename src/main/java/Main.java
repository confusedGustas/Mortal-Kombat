import GUI.*;

public class Main {
    public static void main(String[] args) {
        InitialScreen.initializeFrame();
        PlayerWithPlayerInput.initializationPlayerWithPlayerInputUI();
        CurrentGameHistory.initializationCurrentGameHistory();
        PlayerWithAIInput.initializationPlayerWithAIInput();
    }
}