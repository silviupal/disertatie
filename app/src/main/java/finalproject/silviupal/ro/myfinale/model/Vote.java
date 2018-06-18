package finalproject.silviupal.ro.myfinale.model;

/**
 * Created by Silviu Pal on 6/8/2018.
 */

public class Vote {
    long categoryId;
    long subcategoryId;

    public Vote() {
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(long subcategoryId) {
        this.subcategoryId = subcategoryId;
    }
}
