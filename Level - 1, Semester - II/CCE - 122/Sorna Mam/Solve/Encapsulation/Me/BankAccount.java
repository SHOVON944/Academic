package Me;

public class BankAccount {
    // (i) ব্যালেন্স ভেরিয়েবলকে private করে রাখা হলো (Encapsulation এর মূল অংশ)
    private double balance;

    // (ii) Constructor: একাউন্ট তৈরি হলে শুরুতে কত টাকা থাকবে তা সেট করা হয়
    public BankAccount(double initialBalance) {
        if (initialBalance > 0) {
            balance = initialBalance; // Valid হলে সেট করা হয়
        } else {
            balance = 0; // invalid হলে 0 করে দেওয়া হয়
        }
    }

    // (iii) Getter method: ব্যালেন্স রিটার্ন করে
    public double getBalance() {
        return balance;
    }

    // (iv) Deposit method: ব্যালেন্সে টাকা যোগ করে
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount; // টাকার পরিমাণ যোগ করা হচ্ছে
            System.out.println(amount + " টাকা জমা হয়েছে।");
        } else {
            System.out.println("ভুল পরিমাণ জমা দেওয়া হয়েছে!");
        }
    }

    // (v) Withdraw method: ব্যালেন্স থেকে টাকা কমায়
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount; // টাকার পরিমাণ বাদ দেওয়া হচ্ছে
            System.out.println(amount + " টাকা উত্তোলন হয়েছে।");
        } else {
            System.out.println("ভুল অথবা পর্যাপ্ত ব্যালেন্স নেই!");
        }
    }
}

// Main class
class Main {
    public static void main(String[] args) {
        // (vi) নতুন একটি BankAccount object তৈরি করা হলো এবং constructor কল হলো
        BankAccount myAccount = new BankAccount(1000.0);

        // (vii) ব্যালেন্স দেখা হচ্ছে getBalance() এর মাধ্যমে
        System.out.println("বর্তমান ব্যালেন্স: " + myAccount.getBalance());

        // (viii) 500 টাকা জমা দেওয়া হচ্ছে deposit() মেথড দিয়ে
        myAccount.deposit(500.0);

        // (ix) নতুন ব্যালেন্স আবার দেখা হচ্ছে
        System.out.println("নতুন ব্যালেন্স: " + myAccount.getBalance());

        // (x) 300 টাকা উত্তোলন করা হচ্ছে withdraw() দিয়ে
        myAccount.withdraw(300.0);

        // (xi) সর্বশেষ ব্যালেন্স আবার দেখা হচ্ছে
        System.out.println("সর্বশেষ ব্যালেন্স: " + myAccount.getBalance());
    }
}
