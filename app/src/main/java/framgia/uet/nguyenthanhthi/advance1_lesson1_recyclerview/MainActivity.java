package framgia.uet.nguyenthanhthi.advance1_lesson1_recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static framgia.uet.nguyenthanhthi.advance1_lesson1_recyclerview.Contact.getNewContact;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycler_contacts;
    private ContactAdapter mAdapter;

    private List<Contact> mLsContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData(getNewContact());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //hàm khởi tạo Option Menu
        getMenuInflater().inflate(R.menu.menu_option_contact, menu);

        MenuItem menu_search = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menu_search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, "Đang tìm kiếm...", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String nameNeedFound) {
                List<Contact> lsResultSearch = new ArrayList<>();
                for (Contact contact : mLsContacts) {
                    if (nameNeedFound.toLowerCase().contains(contact.getName().toLowerCase())) {
                        lsResultSearch.add(contact);
                    }
                }

                //Vẽ lại giao diện recyclerview với danh sách kết quả tìm kiếm
                mAdapter = new ContactAdapter(lsResultSearch);
                mRecycler_contacts.setAdapter(mAdapter);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //hàm control sự kiện khi click menu item trên Option Menu
        switch (item.getItemId()) {
            case R.id.menu_add:
                initData(getNewContact());
                Toast.makeText(this, "Add contact successfully", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        //hàm khởi tạo giao diện
        getSupportActionBar().setTitle("List Contacts");
        mRecycler_contacts = (RecyclerView) findViewById(R.id.recycler_contacts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler_contacts.setLayoutManager(layoutManager);

        mLsContacts = new ArrayList<>();
    }

    private void initData(Contact contact) {
        //hàm khởi tạo dữ liệu
        if (contact != null) mLsContacts.add(contact);
        else Toast.makeText(this, "Add contact failed!", Toast.LENGTH_SHORT).show();

        mAdapter = new ContactAdapter(mLsContacts);
        mRecycler_contacts.setAdapter(mAdapter);
    }


}
