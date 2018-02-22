package ru.moneta.pft.mantis.appmanager;

public class NavigationHelper extends HelperBase{

    // constructor
    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    // methods
    public void loginPage() {
        wd.get(app.getProperty("web.baseUrl")+"login_page.php");
    }
    public void manageUserPage() {wd.get(app.getProperty("web.baseUrl") + "manage_user_page.php");}
}
