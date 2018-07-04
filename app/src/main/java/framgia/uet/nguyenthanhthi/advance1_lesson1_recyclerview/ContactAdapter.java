package framgia.uet.nguyenthanhthi.advance1_lesson1_recyclerview;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by thanhthi on 26/10/2017.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private List<Contact> mLsContacts;
    private LayoutInflater mLayoutInflater;

    public ContactAdapter(List<Contact> lsContacts) {
        mLsContacts = lsContacts;
    }

    public List<Contact> getLsContacts() {
        return mLsContacts;
    }

    public void setLsContacts(List<Contact> lsContacts) {
        mLsContacts = lsContacts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(parent.getContext());
        }
        View view = mLayoutInflater.inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(mLsContacts.get(position));
    }

    @Override
    public int getItemCount() {
        return mLsContacts != null ? mLsContacts.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        private ImageView mImage_avatar;
        private TextView mText_name;
        private TextView mText_phone;
        private TextView mText_address;

        public ViewHolder(View itemView) {
            super(itemView);
            mImage_avatar = itemView.findViewById(R.id.image_avatar);
            mText_name = itemView.findViewById(R.id.text_name);
            mText_phone = itemView.findViewById(R.id.text_phone);
            mText_address = itemView.findViewById(R.id.text_address);

            itemView.setOnLongClickListener(this);
        }

        public void bindData(Contact contact) {
            if (contact == null) return;
            mImage_avatar.setImageResource(contact.getAvatar());
            mText_name.setText(contact.getName());
            mText_phone.setText(contact.getPhone());
            mText_address.setText(contact.getAddress());
        }

        @Override
        public boolean onLongClick(final View v) {
            //hàm bắt sự kiện long click trên item view để thực hiện xoá contact
            //show confirm dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Delete")
                    .setIcon(R.drawable.ic_dialog_delete)
                    .setMessage("Do you want to delete this contact?")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mLsContacts.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                            Toast.makeText(v.getContext(), "Delete contact successfully", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .create()
                    .show();
            return false;
        }
    }

}
