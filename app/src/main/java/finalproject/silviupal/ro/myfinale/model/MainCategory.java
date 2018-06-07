package finalproject.silviupal.ro.myfinale.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Silviu Pal on 6/6/2018.
 */

public class MainCategory implements Parcelable {

    private long id;

    private String name;

    private List<Subcategory> subcategories;

    public MainCategory() {
    }

    public MainCategory(long id, String name) {
        this.id = id;
        this.name = name;
    }

    protected MainCategory(Parcel in) {
        id = in.readLong();
        name = in.readString();
        subcategories = in.createTypedArrayList(Subcategory.CREATOR);
    }

    public static final Creator<MainCategory> CREATOR = new Creator<MainCategory>() {
        @Override
        public MainCategory createFromParcel(Parcel in) {
            return new MainCategory(in);
        }

        @Override
        public MainCategory[] newArray(int size) {
            return new MainCategory[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeTypedList(subcategories);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }
}
