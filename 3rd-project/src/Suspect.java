public class Suspect {

    private int AFM;            // 5-digits AFM
    private String firstName;   // name
    private String lastName;    // surname
    private double savings;     // total savings in other countries
    private double taxedIncome; // total taxed income in Italy, the last 3 years


    public Suspect (int AFM, String firstName, String lastName, double savings, double taxedIncome) {
        this.AFM = AFM;
        this.firstName = firstName;
        this.lastName = lastName;
        this.savings = savings;
        this.taxedIncome = taxedIncome;
    }

    public Suspect() {}

    public void setAFM(int AFM) {this.AFM = AFM;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setSavings(double savings) {this.savings = savings;}
    public void setTaxedIncome(double taxedIncome) {this.taxedIncome = taxedIncome;}

    public int key() {return AFM;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public double getSavings() {return savings;}
    public double getTaxedIncome() {return taxedIncome;}

    public boolean less(int AFM) {
        return this.AFM < AFM;
    }

    public boolean greater(int AFM) {
        return  this.AFM > AFM;
    }

    public boolean equals(int AFM) {
        return this.AFM == AFM;
    }

    public String toString() {
        return AFM + " " + firstName + " " + lastName + " " + savings + " " + taxedIncome;
    }

    public boolean compareTo(Suspect item) {
        if (item.savings < 9000) {
            if (savings >= 9000)
                return true;
            else {
                if (savings >= item.savings)
                    return true;
                else
                    return false;
            }
        } else {
            if (savings < 9000)
                return false;
            else {
                if (savings - taxedIncome >= item.savings - item.taxedIncome)
                    return true;
                else
                    return false;
            }
        }
    }
}
