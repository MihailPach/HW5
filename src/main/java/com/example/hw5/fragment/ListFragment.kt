package com.example.hw5.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hw5.*
import com.example.hw5.adapter.CountAdapter
import com.example.hw5.databinding.FragmentListBinding
import com.example.hw5.retrofit.GitHubService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val adapter by lazy {
        CountAdapter {
            findNavController().navigate(
                ListFragmentDirections.details(it.id.toString())
            )
        }
    }

    private val userDao by lazy(LazyThreadSafetyMode.NONE) {
        requireContext().appDatabase.userDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentListBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            swipeLayout.setOnRefreshListener {
                swipeLayout.isRefreshing = false
            }
            recyclerView.adapter = adapter
            recyclerView.addSpaceDecoration(SPACE_SIZE)

            with(binding) {
                recyclerView.adapter = adapter
                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                    adapter.submitList(userDao.getUsers())
                    val userList = GitHubService.githubApi.getUsers(0, 50)
                    userDao.insertUsers(userList)
                    adapter.submitList(userList)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val SPACE_SIZE = 50
    }
}





