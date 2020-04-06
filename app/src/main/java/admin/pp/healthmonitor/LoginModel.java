package admin.pp.healthmonitor;

public class LoginModel implements LoginActivity.Model {
    private LoginRepository repository;
    public LoginModel(LoginRepository repository) {
        this.repository = repository;
    }
    @Override
    public void createUser(String name, String lastName) {
        repository.saveUser(new User(name, lastName));
    }
    @Override
    public User getUser() {
        return repository.getUser();
    }
}
