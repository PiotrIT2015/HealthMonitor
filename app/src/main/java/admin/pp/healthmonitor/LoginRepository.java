package admin.pp.healthmonitor;

public interface LoginRepository {
    User getUser();
    void saveUser(User user);
}
