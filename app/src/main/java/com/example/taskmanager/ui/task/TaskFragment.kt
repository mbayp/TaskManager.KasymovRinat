package com.example.taskmanager.ui.task

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentTaskBinding
import com.example.taskmanager.model.Task
import com.example.taskmanager.ui.home.HomeFragment

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editTask = arguments?.getSerializable(HomeFragment.EDIT_TASK_KEY) as Task?
        editTask?.let{
                binding.etTitle.setText(it.title)
                binding.etDesc.setText(it.desc)
                binding.btnSave.text="Update"
        }
        val slideUp = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)

        binding.btnSave.setOnClickListener {
            if (editTask==null){
               save()
            } else{
                update(editTask)
            }

            findNavController().navigateUp()
        }

        binding.etTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrBlank()) {
                    binding.tilTitle.startAnimation(slideUp)
                    binding.tilTitle.hint = "Title"
                } else {
                    binding.tilTitle.hint = ""
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.etDesc.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrBlank()) {
                    binding.tilDesc.startAnimation(slideUp)
                    binding.tilDesc.hint = "Description"
                } else {
                    binding.tilDesc.hint = ""
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun update(editTask: Task) {
        App.db.taskDao().update(
            editTask.copy(
                title = binding.etTitle.text.toString(),
                desc = binding.etDesc.text.toString()
            )
        )
    }

    private fun save(){
        val task = Task(
            title = binding.etTitle.text.toString(),
            desc = binding.etDesc.text.toString()
        )
        App.db.taskDao().insert(task)
    }
}