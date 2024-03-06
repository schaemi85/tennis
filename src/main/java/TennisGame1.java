import java.text.MessageFormat;

public class TennisGame1 implements TennisGame {

    private int playerOneScore = 0;
    private int playerTwoScore = 0;
    private final String player1Name;
    private String player2Name;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(this.player1Name)) {
            playerOneScore = incrementScore(playerOneScore);
        } else {
            playerTwoScore = incrementScore(playerTwoScore);
        }
    }

    public String getScore() {
        if (playerOneScore == playerTwoScore) {
            return calculateScoreWhenEquals(playerOneScore);
        } else if (isGreaterOrEqualThanFour(playerOneScore) || isGreaterOrEqualThanFour(playerTwoScore)) {
            return calculateScoreWhenGreaterThanFour(playerOneScore, playerTwoScore);
        } else {
            return calculateDefault(playerOneScore, playerTwoScore);
        }
    }

    private int incrementScore(int score) {
        return score + 1;
    }

    private boolean isGreaterOrEqualThanFour(int score) {
        return score >= 4;
    }

    private String calculateScoreWhenEquals(int score) {
        return switch (score) {
            case 0 -> "Love-All";
            case 1 -> "Fifteen-All";
            case 2 -> "Thirty-All";
            default -> "Deuce";
        };
    }

    private String calculateScoreWhenGreaterThanFour(int playerOneScore, int playerTwoScore) {
        int minusResult = playerOneScore - playerTwoScore;
        if (minusResult == 1) return "Advantage player1";
        if (minusResult == -1) return "Advantage player2";
        if (minusResult >= 2) return "Win for player1";
        return "Win for player2";
    }

    private String calculateDefault(int playerOneScore, int playerTwoScore) {
        return MessageFormat.format("{0}-{1}", scoreAsName(playerOneScore), scoreAsName(playerTwoScore));
    }

    private String scoreAsName(int playerScore) {
        return switch (playerScore) {
            case 0 -> "Love";
            case 1 -> "Fifteen";
            case 2 -> "Thirty";
            case 3 -> "Forty";
            default -> throw new RuntimeException();
        };
    }

}
