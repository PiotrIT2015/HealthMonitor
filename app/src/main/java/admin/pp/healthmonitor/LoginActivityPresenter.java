package admin.pp.healthmonitor;

public class LoginActivityPresenter implements LoginActivity.Presenter {
    private LoginActivity.View view;
    private LoginActivity.Model model;
    public LoginActivityPresenter(LoginActivity.Model model) {
        this.model = model;
    }
    @Override
    public void setView(LoginActivity.View view) {
        this.view = view;
    }
    @Override
    public void loginButtonClicked() {
        if (view != null) {
            if (view.getFirstName().trim().equals("") || view.getLastName().trim().equals("")) {
                view.showInputError();
            } else {
                model.createUser(view.getFirstName(), view.getLastName());
                view.showUserSavedMessage();
            }
        }
    }
    @Override
    public void getCurrentUser() {
        User user = model.getUser();
        if (user != null) {
            if (view != null) {
                view.setFirstName(user.getFirstName());
                view.setLastName(user.getLastName());
            }
        }
    }
}
