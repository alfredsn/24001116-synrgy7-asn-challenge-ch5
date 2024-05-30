package com.example.triad.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.triad.databinding.FragmentMainBinding
import com.example.triad.factory.ViewModelFactory
import com.example.triad.modelview.MainViewModel
import com.example.triad.repository.EmailData

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels { ViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvAircraft.setOnClickListener {
            val emailData = EmailData(
                sendTo = binding.etRecepient.text.toString(),
                name = "Triad Mail",
                replyTo = "alfred.nainggolan00@gmail.com",
                isHtml = false,
                title = binding.etSubject.text.toString(),
                body = binding.etMessage.text.toString()
            )
            viewModel.sendEmail(emailData)
        }

        viewModel.sendEmailResult.observe(viewLifecycleOwner, Observer { result ->
            result.onSuccess {
                Toast.makeText(context, "Email sent successfully", Toast.LENGTH_SHORT).show()
            }.onFailure {
                Toast.makeText(context, "Failed to send email", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}