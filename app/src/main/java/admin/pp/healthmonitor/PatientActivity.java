package admin.pp.healthmonitor;
import java.io.File;

public interface PatientActivity {

    interface View{
        String getFirstName();
        String getLastName();
        String getHistory();
        int getPesel();
        void setFirstName(String firstName);
        void setLastName(String lastName);
    }
    interface Model {
        void createPatient(String name, String lastName);
        User getPatient();
    }
    interface Presenter{
        void RegisterButtonClicked();
        File writeCsvFile(String name, String lastName, String history);
        void Print(File file);
    }

}
