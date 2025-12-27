import java.util.HashSet;
import java.util.Scanner;

class UnderageVoterException extends Exception {
    public UnderageVoterException(String msg) {
        super(msg);
    }
}

class DuplicateVoteException extends Exception {
    public DuplicateVoteException(String msg) {
        super(msg);
    }
}

public class P_26 {
    private HashSet<String> votedVoters = new HashSet<>();

    public void castVote(int age, String voterID)
            throws UnderageVoterException, DuplicateVoteException {

        if (age < 18) {
            throw new UnderageVoterException("Underage voter: " + age);
        }

        if (votedVoters.contains(voterID)) {
            throw new DuplicateVoteException("Duplicate vote for ID: " + voterID);
        }

        votedVoters.add(voterID);
        System.out.println("Vote cast successfully by voter ID: " + voterID);
    }

    public static void main(String[] args) {
        P_26 vs = new P_26();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of voters: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        for (int i = 1; i <= n; i++) {
            System.out.println("Voter " + i + ":");
            System.out.print("Enter Voter ID: ");
            String voterID = sc.nextLine();

            System.out.print("Enter Age: ");
            int age = sc.nextInt();
            sc.nextLine(); // consume newline

            try {
                vs.castVote(age, voterID);
            } catch (UnderageVoterException | DuplicateVoteException e) {
                System.out.println("Voting Error: " + e.getMessage());
            }

            System.out.println(); // extra line for readability
        }

        sc.close();
    }
}
