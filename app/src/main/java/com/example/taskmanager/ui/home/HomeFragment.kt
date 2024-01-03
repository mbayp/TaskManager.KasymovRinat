package com.example.taskmanager.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentHomeBinding
import com.example.taskmanager.model.Task
import com.example.taskmanager.ui.home.adapter.TaskAdapter
import com.example.taskmanager.ui.task.TaskFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private val adapter = TaskAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding){
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter=adapter
        val tasks = App.db.taskDao().getAll()
        adapter.addTasks(tasks)
        fab.setOnClickListener{
findNavController().navigate(R.id.taskFragment2)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}