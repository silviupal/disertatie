package finalproject.silviupal.ro.myfinale.model;

/**
 * Created by Silviu Pal on 6/27/2018.
 */
public class MyVoteModel {
    private String categoryName;
    private String subcategoryName;

    public MyVoteModel(String categoryName, String subcategoryName) {
        this.categoryName = categoryName;
        this.subcategoryName = subcategoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }
}
