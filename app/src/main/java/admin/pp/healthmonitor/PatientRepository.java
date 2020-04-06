package admin.pp.healthmonitor;

public class PatientRepository implements HospitalRepository{
    private Patient patient;
    @Override
    public Patient getPatient() {
        if (patient == null) {
            User user = new User("Dinesh", "Kumar");
            user.setId(0);
            return patient;
        } else {
            return patient;
        }
    }
    @Override
    public void savePatient(Patient patient) {
        if (patient == null) {
            patient = getPatient();
        }
        this.patient = patient;
    }
}
