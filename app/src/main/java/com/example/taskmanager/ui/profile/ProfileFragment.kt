package com.example.taskmanager.ui.profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentNotificationsBinding
import com.example.taskmanager.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var editText: EditText
    private lateinit var profileImageView: de.hdodenhof.circleimageview.CircleImageView
    private val preferences by lazy {
        requireActivity().getPreferences(Context.MODE_PRIVATE)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        editText = binding.editText
        profileImageView = binding.profileImageView

        val savedName = preferences.getString(NAME_KEY, "")
        editText.setText(savedName)

        val savedImageUriString = preferences.getString(IMAGE_URI_KEY, "")
        if (savedImageUriString != null) {
            if (savedImageUriString.isNotEmpty()) {
                val savedImageUri = Uri.parse(savedImageUriString)
                profileImageView.setImageURI(savedImageUri)
            }
        }

        val saveButton: Button = binding.button
        saveButton.setOnClickListener {
            saveNameAndImage(editText.text.toString(), profileImageUri)
        }
            binding.exit.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.phoneFragment)
        }

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
    }

    private var profileImageUri: Uri? = null


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri = data.data
            profileImageUri = selectedImageUri
            profileImageView.setImageURI(selectedImageUri)
        }
    }

    private fun saveNameAndImage(name: String, imageUri: Uri?) {
        val editor = preferences.edit()
        editor.putString(NAME_KEY, name)
        if (imageUri != null) {
            editor.putString(IMAGE_URI_KEY, imageUri.toString())
        }
        editor.apply()
    }


    companion object {
        private const val GALLERY_REQUEST_CODE = 1001
        private const val NAME_KEY = "nameKey"
        private const val IMAGE_URI_KEY = "imageUriKey"
    }
}