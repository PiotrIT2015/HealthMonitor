package admin.pp.healthmonitor;

public interface HospitalRepository {
    Patient getPatient();
    void savePatient(Patient patient);
}
