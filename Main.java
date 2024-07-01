public class Main {
    public static void main(String[] args) {
        Player playerA = new Player("Player A", 50, 5, 10);
        Player playerB = new Player("Player B", 100, 10, 5);

        Arena arena = new Arena(playerA, playerB);
        Player winner = arena.startFight();

        System.out.printf("The winner is %s with %d health remaining!%n", winner.getName(), winner.getHealth());
    }
}
