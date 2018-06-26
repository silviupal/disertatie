package finalproject.silviupal.ro.myfinale.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Silviu Pal on 6/26/2018.
 */
public class KeyModel implements Parcelable {
    String uid;
    String key;

    public KeyModel() {
    }

    protected KeyModel(Parcel in) {
        uid = in.readString();
        key = in.readString();
    }

    public static final Creator<KeyModel> CREATOR = new Creator<KeyModel>() {
        @Override
        public KeyModel createFromParcel(Parcel in) {
            return new KeyModel(in);
        }

        @Override
        public KeyModel[] newArray(int size) {
            return new KeyModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(key);
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
