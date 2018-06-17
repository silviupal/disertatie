package finalproject.silviupal.ro.myfinale.main;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import finalproject.silviupal.ro.myfinale.FirebaseController;
import finalproject.silviupal.ro.myfinale.Keys;
import finalproject.silviupal.ro.myfinale.R;
import finalproject.silviupal.ro.myfinale.base.BaseActivity;
import finalproject.silviupal.ro.myfinale.data.UserProfile;
import finalproject.silviupal.ro.myfinale.helper.DialogHelper;
import finalproject.silviupal.ro.myfinale.model.MainCategory;
import finalproject.silviupal.ro.myfinale.subcategories.SubcategoriesActivity;

public class MainActivity extends BaseActivity implements ItemClickListener {

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

    private ValueEventListener valueEventListener = new ValueEventListener() {
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

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        FirebaseController.getInstance().getMainCategories(valueEventListener);
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
        DialogHelper.showUnderConstructionDialog(this);
    }

    @OnClick(R.id.logout_layout)
    public void handleLogout() {
        DialogHelper.showUnderConstructionDialog(this);
    }
}
