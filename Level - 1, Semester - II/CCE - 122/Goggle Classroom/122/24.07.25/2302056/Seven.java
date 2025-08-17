class Complex {
    double real;
    double imaginary;

    Complex(double r, double i) {
        real = r;
        imaginary = i;
    }

    Complex add(Complex c) {
        double newReal = real + c.real;
        double newImaginary = imaginary + c.imaginary;
        return new Complex(newReal, newImaginary);
    }

    void display() {
        System.out.println(real + " + " + imaginary + "i");
    }
}

class Seven {
    public static void main(String[] args) {
        Complex c1 = new Complex(3.5, 2.5);
        Complex c2 = new Complex(1.5, 4.5);

        Complex result = c1.add(c2);
        result.display();
    }
}