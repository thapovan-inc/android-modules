package com.thapovan.android.commonutils.contacts;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContactUtil {

    private ContactUtil() {
    }

    public static List<Contact> getContacts(Context ctx){
        ContentResolver cr = ctx.getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null,ContactsContract.Contacts.DISPLAY_NAME+" ASC");
        if (cur!=null&&cur.getCount() > 0) {
            final List<Contact> contactList=new ArrayList<>();
            while (cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                Contact contact=new Contact();
                contact.addEmail(getEmail(cr,cur,id));
                contact.addAddress(getAddress(cr,id));
                contact.setPhoneList(getNumber(cr,cur,id));
                contact.setDisplayName(name);
                contact.setId(id);
                contactList.add(contact);
            }
            return contactList;
        }else{
            if(cur!=null){
                cur.close();
            }
            return Collections.emptyList();
        }
    }


    private static List<Contact.Phone> getNumber(ContentResolver cr, Cursor cur, String id) {
        if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) <= 0) {
            return Collections.emptyList();
        }

            Cursor pCur = cr.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                    new String[]{id}, null);

            if(pCur == null){
                return Collections.emptyList();
            }

            final List<Contact.Phone> mPhoneList=new ArrayList<>();

            while (pCur.moveToNext()) {
                String number=pCur.getString(pCur
                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                mPhoneList.add(new Contact.Phone(number,""));
            }
            pCur.close();
            return mPhoneList;
    }

    private static Contact.Email getEmail(ContentResolver cr, Cursor cur, String id){
        Cursor emailCur = cr.query(
                ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                new String[]{id}, null);

        if(emailCur==null){
            return null;
        }

        if (emailCur.moveToNext()) {
            String emailStr = emailCur.getString(
                    emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
            emailCur.close();
            return new Contact.Email(emailStr,"");
        }else{
            emailCur.close();
            return null;
        }
    }

    private static Contact.Address getAddress(ContentResolver cr, String id){
        String addrWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?";
        String[] addrWhereParams = new String[]{id,
                ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE};
        Cursor addrCur = cr.query(ContactsContract.Data.CONTENT_URI,
                null, addrWhere, addrWhereParams, null);

        if(addrCur==null){
            return null;
        }

        if(addrCur.moveToNext()){
            String street = addrCur.getString(
                    addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET));
            String city = addrCur.getString(
                    addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
            String state = addrCur.getString(
                    addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.REGION));
            String postalCode = addrCur.getString(
                    addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE));
            String country = addrCur.getString(
                    addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY));
            return new Contact.Address("",street,city,state,postalCode,country);
        }else{
            addrCur.close();
            return null;
        }
    }
}
