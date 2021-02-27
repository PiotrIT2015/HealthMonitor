package admin.pp.healthmonitor;

public class Patient {

    String firstName;
    String lastName;
    int pesel;
    String history;

    public Patient(String firstName, String lastName, int pesel,
                   String history) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.history = history;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * @return the history
     */
    public String getHistory() {
        return history;
    }
    /**
     * @param history the history to set
     */
    public void setGender(String history) {
        this.history = history;
    }
    /**
     * @return the pesel
     */
    public int getPesel() {
        return pesel;
    }
    /**
     * @param pesel the pesel to set
     */
    public void setPesel(int pesel) {
        this.pesel = pesel;
    }

    @Override
    public String toString() {
        return "Student [firstName=" + firstName + ", lastName=" + lastName + ", history=" + history + ", pesel="
                + pesel + "]";
    }
}

