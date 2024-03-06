
public class TennisGame1 implements TennisGame {

    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            m_score1 += 1;
        else
            m_score2 += 1;
    }

    public String getScore() {
        String score = "";
        boolean isEqualScore = m_score1 == m_score2;
        if (isEqualScore) {
            score = calculateScoreWhenEquals(m_score1);
        } else if (isGreaterOrEqualThanFour(m_score1) || isGreaterOrEqualThanFour(m_score2)) {
            score = calculateScoreWhenGreaterThanFour(m_score1, m_score2);
        } else {
            int tempScore = 0;
            for (int i = 1; i < 3; i++) {
                if (i == 1) tempScore = m_score1;
                else {
                    score += "-";
                    tempScore = m_score2;
                }
                switch (tempScore) {
                    case 0:
                        score += "Love";
                        break;
                    case 1:
                        score += "Fifteen";
                        break;
                    case 2:
                        score += "Thirty";
                        break;
                    case 3:
                        score += "Forty";
                        break;
                }
            }
        }
        return score;
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
        int minusResult = m_score1 - m_score2;
        if (minusResult == 1) return "Advantage player1";
        if (minusResult == -1) return "Advantage player2";
        if (minusResult >= 2) return "Win for player1";
        return "Win for player2";
    }
    
}
