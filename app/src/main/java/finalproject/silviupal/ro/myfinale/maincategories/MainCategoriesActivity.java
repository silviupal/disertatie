package finalproject.silviupal.ro.myfinale.maincategories;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AdapterViewAnimator;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import butterknife.BindView;
import butterknife.OnClick;
import finalproject.silviupal.ro.myfinale.FirebaseController;
import finalproject.silviupal.ro.myfinale.Keys;
import finalproject.silviupal.ro.myfinale.LoginActivity;
import finalproject.silviupal.ro.myfinale.R;
import finalproject.silviupal.ro.myfinale.base.BaseActivity;
import finalproject.silviupal.ro.myfinale.crypt.CryptoTime;
import finalproject.silviupal.ro.myfinale.data.UserProfile;
import finalproject.silviupal.ro.myfinale.helper.DialogHelper;
import finalproject.silviupal.ro.myfinale.model.CryptoVote;
import finalproject.silviupal.ro.myfinale.model.KeyModel;
import finalproject.silviupal.ro.myfinale.model.MainCategory;
import finalproject.silviupal.ro.myfinale.model.User;
import finalproject.silviupal.ro.myfinale.model.Vote;
import finalproject.silviupal.ro.myfinale.myvotes.MyVotesActivity;
import finalproject.silviupal.ro.myfinale.subcategories.SubcategoriesActivity;

public class MainCategoriesActivity extends BaseActivity implements ItemClickListener {

    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_main_categories)
    RecyclerView recyclerView;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.drawer_email_tv)
    TextView userEmailTv;

    RvAdapter adapter;
    private ActionBarDrawerToggle mDrawerToggle;

    private ValueEventListener getMainCategoriesListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            List<MainCategory> mainCategories = new ArrayList<>();

            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                mainCategories.add(postSnapshot.getValue(MainCategory.class));
            }

            adapter.setList(mainCategories);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Toast.makeText(MainCategoriesActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    private ValueEventListener getKeyListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                KeyModel keyModel = snapshot.getValue(KeyModel.class);
                if (keyModel != null && UserProfile.getInstance().getUserId().equals(keyModel.getUid())) {
                    UserProfile.getInstance().setKey(keyModel.getKey());
                    return;
                }
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Toast.makeText(MainCategoriesActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    private ValueEventListener getVotesListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.getValue() == null) {
                return;
            }

            List<Vote> votes = new ArrayList<>();

            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                CryptoVote cryptoVote = snapshot.getValue(CryptoVote.class);
                if (cryptoVote != null) {
                    try {
                        votes.add(new Vote(
                                Integer.parseInt(CryptoTime.getInstance().decrypt(cryptoVote.getCategoryId())),
                                Integer.parseInt(CryptoTime.getInstance().decrypt(cryptoVote.getSubcategoryId()))));
                    } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | InvalidKeySpecException | IOException | InvalidAlgorithmParameterException e) {
                        e.printStackTrace();
                    }
                }
            }

            UserProfile.getInstance().setVoteList(votes);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Toast.makeText(MainCategoriesActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseController.getInstance().getKey(getKeyListener);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseController.getInstance().getVotes(getVotesListener);
            }
        }, 2000);

        initToolbar();
        initNavigationView();
        initRecyclerView();
        initUserDetails();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    private void initToolbar() {
        initToolbar(toolbar, tvToolbarTitle, getString(R.string.title_main_activity), true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initNavigationView() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, 0, 0);
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    private void initRecyclerView() {
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
        adapter = new RvAdapter(this);
        recyclerView.setAdapter(adapter);
        FirebaseController.getInstance().getMainCategories(getMainCategoriesListener);
    }

    private void initUserDetails() {
        userEmailTv.setText(UserProfile.getInstance().getUser().getEmail());
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onItemClickListener(MainCategory item) {
        Intent intent = new Intent(this, SubcategoriesActivity.class);
        intent.putExtra(Keys.KEY_CATEGORY, item);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.my_profile_layout)
    public void handleMyProfile() {
        DialogHelper.showUnderConstructionDialog(this);
    }

    @OnClick(R.id.my_votes_layout)
    public void handleMyVotes() {
        //DialogHelper.showUnderConstructionDialog(this);

        Intent intent = new Intent(this, MyVotesActivity.class);
        intent.putParcelableArrayListExtra(Keys.KEY_EXTRA_CATEGORIES, new ArrayList<>(adapter.getList()));
        startActivity(intent);
    }

    @OnClick(R.id.logout_layout)
    public void handleLogout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
