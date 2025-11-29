// Ten
public class Fraction {
    int numerator;
    int denominator;


    Fraction(int n, int d) {
        numerator = n;
        denominator = d;
    }

    int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    void simplify() {
        int g = gcd(numerator, denominator);
        numerator /= g;
        denominator /= g;
    }

    Fraction add(Fraction f) {
        int newNumerator = numerator * f.denominator + f.numerator * denominator;
        int newDenominator = denominator * f.denominator;

        Fraction result = new Fraction(newNumerator, newDenominator);
        result.simplify();
        return result;
    }

    void display() {
        System.out.println(numerator + "/" + denominator);
    }

    public static void main(String[] args) {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(1, 3);

        Fraction sum = f1.add(f2);
        sum.display();
    }
}
