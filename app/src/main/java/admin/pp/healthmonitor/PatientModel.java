package admin.pp.healthmonitor;

import android.os.Environment;

public class PatientModel implements PatientActivity.Model{

    private LoginRepository repository;
    public PatientModel(LoginRepository repository) {
        this.repository = repository;
    }
    public void createPatient(String name, String lastName) {
        repository.saveUser(new User(name, lastName));
    }
    public User getPatient() {
        return repository.getUser();
    }
}
