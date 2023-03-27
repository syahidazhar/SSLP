package com.example.splashscreenlastproject.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.splashscreenlastproject.databinding.FragmentDashboardBinding
import com.example.splashscreenlastproject.helper.DBHelper
import com.example.splashscreenlastproject.model.Article
import com.google.android.material.textfield.TextInputEditText

class DashboardFragment : Fragment() {
    lateinit var title : TextInputEditText
    lateinit var creator : TextInputEditText
    lateinit var description : EditText
    lateinit var image : TextInputEditText
    lateinit var button : Button
    lateinit var dbHelper: DBHelper

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        val intent: Intent = Intent(requireActivity(), DashboardFragment::class.java)

        title = binding.ipTitle
        creator = binding.ipUser
        description = binding.descInput
        image = binding.ipImg
        button = binding.button

        button.setOnClickListener {
            val db = DBHelper(requireActivity())
            val title = title.text.toString()
            val creator = creator.text.toString()
            val description = description.text.toString()
            val image = image.text.toString()

            if (title == "" || creator == "" || description == "" || image == "") {
                Toast.makeText(getActivity(), "Please Insert the fields", Toast.LENGTH_SHORT).show();
            } else {
                db.insertData(Article())
                startActivity(intent)
            }


        }

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}