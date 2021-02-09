package com.example.navigationservicephonebook.contactlist

import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigationservicephonebook.MainActivity
import com.example.navigationservicephonebook.R
import kotlinx.android.synthetic.main.fragment_contact_list.*

class ContactListFragment : Fragment() {

    private val contactList = mutableListOf<Contact>()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact_list, container, false)
    }

    override fun onStart() {
        super.onStart()

        val adapter = ContactListAdapter()

        reciclerView.adapter = adapter
        reciclerView.layoutManager =
            LinearLayoutManager((activity as MainActivity), LinearLayoutManager.VERTICAL, false)
        getContactList()
        adapter.setContactList(contactList)
    }

    private fun getContactList() {
        val cursor = (activity as MainActivity).contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        cursor?.let {
            while (it.moveToNext()) {
                contactList.add(
                    Contact(
                        id = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)),
                        name = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                        phone = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    )
                )
            }
        }
        cursor?.close()
    }
}