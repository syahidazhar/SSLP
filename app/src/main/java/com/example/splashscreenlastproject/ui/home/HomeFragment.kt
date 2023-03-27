package com.example.splashscreenlastproject.ui.home

import ArticleAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.splashscreenlastproject.databinding.FragmentHomeBinding
import com.example.splashscreenlastproject.helper.DBHelper
import com.example.splashscreenlastproject.model.Article

class HomeFragment : Fragment() {
    lateinit var dbHelper: DBHelper
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!! //?????????????????

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        dbHelper = DBHelper(requireActivity())

        val rvArticle : RecyclerView = binding.rvArticle //????????????????

        rvArticle.layoutManager = LinearLayoutManager(requireActivity(),
            LinearLayoutManager.HORIZONTAL, false)
        rvArticle.setHasFixedSize(true)
        getArticleData {
            rvArticle.adapter = ArticleAdapter(it)
        }

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getArticleData(callback : (List<Article>)->Unit) {
        val db = dbHelper.readData()

        return callback (db)
    }
}