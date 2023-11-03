public class TennisGameImp implements TennisGame {

    private int firstPlayerScore = 0;
    private int secondPlayerScore = 0;
    private final String firstPlayer;
    private final String secondPlayer;

    public TennisGameImp(final String player1Name, final String player2Name) {
        this.firstPlayer = player1Name;
        this.secondPlayer = player2Name;
    }

    public void wonPoint(String playerName) {
        if (firstPlayer.equals(playerName)) {
            firstPlayerScore += 1;
            return;
        } else if (secondPlayer.equals(playerName)) {
            secondPlayerScore += 1;
            return;
        }
        throw new RuntimeException("Player does not exists");
    }

    public String getScore() {
        if (firstPlayerScore == secondPlayerScore) {
            return getScorePlayersEven();
        }
        if (firstPlayerScore >= 4 || secondPlayerScore >= 4) {
            return getScorePlayersGreaterOrEqualFour();
        }

        return getStringForScore(firstPlayerScore) + "-" + getStringForScore(secondPlayerScore);
    }

    private static String getStringForScore(int score) {
        // This could be an enum with an integer value and an associated string
        return switch (score) {
            case 0:
                yield "Love";
            case 1:
                yield "Fifteen";
            case 2:
                yield "Thirty";
            case 3:
                yield "Forty";
            default:
                throw new RuntimeException("Unexpected score number");
        };
    }

    private String getScorePlayersGreaterOrEqualFour() {
        int minusResult = firstPlayerScore - secondPlayerScore;
        return switch (minusResult) {
            case 1 -> "Advantage player1";
            case -1 -> "Advantage player2";
            default -> minusResult >= 2 ? "Win for player1" : "Win for player2";
        };
    }

    private String getScorePlayersEven() {
        return switch (firstPlayerScore) {
            case 0 -> "Love-All";
            case 1 -> "Fifteen-All";
            case 2 -> "Thirty-All";
            default -> "Deuce";
        };
    }
}
