package framgia.uet.nguyenthanhthi.advance1_lesson1_recyclerview;

import java.util.Random;

/**
 * Created by thanhthi on 26/10/2017.
 */

public class Contact {

    private int mId = 0;
    private int mAvatar;
    private String mName;
    private String mPhone;
    private String mAddress;

    public Contact(String name, String phone, String address) {
        mId++;
        mName = name;
        mPhone = phone;
        mAddress = address;
    }

    public Contact(int avatar, String name, String phone, String address) {
        mId++;
        mAvatar = avatar;
        mName = name;
        mPhone = phone;
        mAddress = address;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getAvatar() {
        return mAvatar;
    }

    public void setAvatar(int avatar) {
        this.mAvatar = avatar;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String sPhone) {
        mPhone = sPhone;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public static Contact getNewContact() {
        //hàm tự động tạo đối tượng contact mới
        Random rd = new Random();
        int rdContact = rd.nextInt(100) + 1;
        if (rdContact % 2 == 1) {
            return new Contact(R.drawable.image_avatar_boy, "Contact " + rdContact,
                    "0123 456 7" + rd.nextInt(10) + rd.nextInt(10), "Ha Noi");
        } else {
            return new Contact(R.drawable.image_avatar_girl, "Contact " + rdContact,
                    "0123 456 7" + rd.nextInt(10) + rd.nextInt(10), "Ha Noi");
        }
    }
}
