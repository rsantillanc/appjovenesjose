package rsantillanc.appjovenesjose.moldel;

/**
 * Created by RenzoD on 29/04/2015.
 */
public class NavDrawerModel {

    private String title;
    private int icon;

    public NavDrawerModel(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public NavDrawerModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
