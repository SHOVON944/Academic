class Distance {
    int feet;
    int inches;

    Distance(int f, int i) {
        feet = f;
        inches = i;
    }

    Distance addDistance(Distance d) {
        int totalInches = inches + d.inches;
        int totalFeet = feet + d.feet + (totalInches / 12);
        int remainingInches = totalInches % 12;

        return new Distance(totalFeet, remainingInches);
    }

    String toStringValue() {
        return feet + " feet " + inches + " inches";
    }
}

class Thirteen {
    public static void main(String[] args) {
        Distance d1 = new Distance(5, 8);
        Distance d2 = new Distance(3, 11);

        Distance sum = d1.addDistance(d2);
        System.out.println("Total Distance: " + sum.toStringValue());
    }
}
