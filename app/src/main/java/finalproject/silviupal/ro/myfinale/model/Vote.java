package finalproject.silviupal.ro.myfinale.model;

/**
 * Created by Silviu Pal on 6/8/2018.
 */

public class Vote {
    long id;
    String category;
    String subcategory;

    public Vote() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }
}
