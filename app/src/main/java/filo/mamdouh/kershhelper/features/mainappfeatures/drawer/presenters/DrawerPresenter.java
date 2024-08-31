package filo.mamdouh.kershhelper.features.mainappfeatures.drawer.presenters;

import filo.mamdouh.kershhelper.contracts.DrawerContract;
import filo.mamdouh.kershhelper.logic.auth.Authentication;
import filo.mamdouh.kershhelper.models.Repostiry;

public class DrawerPresenter {
    private Authentication auth;
    private DrawerContract.View view;
    private Repostiry repo;
    public DrawerPresenter(DrawerContract.View view, Repostiry repo)
    {
        this.view = view;
        this.repo = repo;
        auth = new Authentication();
    }
    public void logOut(){
        auth.signOut();
        repo.logout();
        view.logOut();
    }
}
