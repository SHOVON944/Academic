package attendance;

public class RegistrationFee {

    public enum FeeType {
        HALL_FEE,
        FACULTY_FEE,
        CSE_CLUB_FEE,
        PROGRAM_FEE,
        OTHER
    }

    private Semester semester;
    private FeeType feeType;
    private String description;
    private double amount;

    public RegistrationFee(Semester semester, FeeType feeType, String description, double amount) {
        this.semester = semester;
        this.feeType = feeType;
        this.description = description;
        this.amount = amount;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public FeeType getFeeType() {
        return feeType;
    }

    public void setFeeType(FeeType feeType) {
        this.feeType = feeType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegistrationFee that = (RegistrationFee) o;

        if (Double.compare(that.amount, amount) != 0) return false;
        if (semester != that.semester) return false;
        if (feeType != that.feeType) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = semester != null ? semester.hashCode() : 0;
        result = 31 * result + (feeType != null ? feeType.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "RegistrationFee{"
                + "semester=" + semester + ", "
                + "feeType=" + feeType + ", "
                + "description='" + description + '\'' + ", "
                + "amount=" + amount + 
                '}';
    }
}