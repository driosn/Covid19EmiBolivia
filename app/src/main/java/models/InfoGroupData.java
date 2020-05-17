package models;

public class InfoGroupData {

    protected String mGroupName;
    protected String mCasesNumber;
    protected int mInfoGroupImage;

    public InfoGroupData(String groupName, String casesNumber, int groupImage) {
        this.mGroupName = groupName;
        this.mCasesNumber = casesNumber;
        this.mInfoGroupImage = groupImage;
    }

    public String getmGroupName() {
        return mGroupName;
    }

    public void setmGroupName(String mGroupName) {
        this.mGroupName = mGroupName;
    }

    public String getmCasesNumber() {
        return mCasesNumber;
    }

    public void setmCasesNumber(String mCasesNumber) {
        this.mCasesNumber = mCasesNumber;
    }

    public int getmInfoGroupImage() {
        return mInfoGroupImage;
    }

    public void setmInfoGroupImage(int mInfoGroupImage) {
        this.mInfoGroupImage = mInfoGroupImage;
    }
}
