package com.example.taskmanager.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentHomeBinding
import com.example.taskmanager.model.Task
import com.example.taskmanager.ui.home.adapter.TaskAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private val adapter = TaskAdapter(this:: onLongClick, this:: onClick)

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
        setData()
        fab.setOnClickListener{
findNavController().navigate(R.id.taskFragment2)
        }
    }

    private fun onLongClick(task : Task){
        val alert = AlertDialog.Builder(requireContext())
        alert.setTitle(getString(R.string.delete))

        alert.setPositiveButton(getString(R.string.yes)) { d, _ ->
            App.db.taskDao().delete(task)
           setData()
        }
        alert.setNegativeButton(getString(R.string.no)) { d, _ ->
            d.cancel()
        }
        alert.create().show()
    }

    private fun onClick(task : Task){
findNavController().navigate(R.id.taskFragment2, bundleOf(EDIT_TASK_KEY to task))
    }

    private fun setData(){
    val tasks = App.db.taskDao().getAll()
    adapter.addTasks(tasks)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object{
        const val EDIT_TASK_KEY = "edit.task.key"
    }
}