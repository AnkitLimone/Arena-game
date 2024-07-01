public class Arena {
    private final Player player1;
    private final Player player2;
    private final Dice attackDice;
    private final Dice defenseDice;

    public Arena(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.attackDice = new Dice(6);
        this.defenseDice = new Dice(6);
    }

    public Player startFight() {
        Player attacker = player1.getHealth() < player2.getHealth() ? player1 : player2;
        Player defender = attacker == player1 ? player2 : player1;

        while (attacker.isAlive() && defender.isAlive()) {
            executeTurn(attacker, defender);
            if (defender.isAlive()) {
                // Swap roles
                Player temp = attacker;
                attacker = defender;
                defender = temp;
            }
        }

        return attacker.isAlive() ? attacker : defender;
    }

    private void executeTurn(Player attacker, Player defender) {
        int attackRoll = attackDice.roll();
        int defenseRoll = defenseDice.roll();
        int damage = attacker.getAttack() * attackRoll - defender.getStrength() * defenseRoll;
        if (damage > 0) {
            defender.reduceHealth(damage);
        }
        System.out.printf("%s attacks %s: Attack Roll = %d, Defense Roll = %d, Damage = %d, %s Health = %d%n",
                attacker.getName(), defender.getName(), attackRoll, defenseRoll, damage > 0 ? damage : 0, defender.getName(), defender.getHealth());
    }
}
