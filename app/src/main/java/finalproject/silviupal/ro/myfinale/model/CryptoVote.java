package finalproject.silviupal.ro.myfinale.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import finalproject.silviupal.ro.myfinale.crypt.CryptoTime;

/**
 * Created by Silviu Pal on 6/26/2018.
 */
public class CryptoVote implements Parcelable {
    private String categoryId;
    private String subcategoryId;

    public CryptoVote() {
    }

    public CryptoVote(String categoryId, String subcategoryId) {
        this.categoryId = categoryId;
        this.subcategoryId = subcategoryId;
    }

    protected CryptoVote(Parcel in) {
        categoryId = in.readString();
        subcategoryId = in.readString();
    }

    public static final Creator<CryptoVote> CREATOR = new Creator<CryptoVote>() {
        @Override
        public CryptoVote createFromParcel(Parcel in) {
            return new CryptoVote(in);
        }

        @Override
        public CryptoVote[] newArray(int size) {
            return new CryptoVote[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(categoryId);
        dest.writeString(subcategoryId);
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getSubcategoryId() {
        return subcategoryId;
    }
}
