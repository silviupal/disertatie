package finalproject.silviupal.ro.myfinale.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Silviu Pal on 6/7/2018.
 */

public class Subcategory implements Parcelable {

    long id;
    String name;
    boolean isSelected = false;

    public Subcategory() {
    }

    protected Subcategory(Parcel in) {
        id = in.readLong();
        name = in.readString();
        isSelected = in.readByte() != 0;
    }

    public static final Creator<Subcategory> CREATOR = new Creator<Subcategory>() {
        @Override
        public Subcategory createFromParcel(Parcel in) {
            return new Subcategory(in);
        }

        @Override
        public Subcategory[] newArray(int size) {
            return new Subcategory[size];
        }
    };

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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }
}
