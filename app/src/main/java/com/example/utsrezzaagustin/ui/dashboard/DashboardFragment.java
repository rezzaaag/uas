package com.example.utsrezzaagustin.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.utsrezzaagustin.BookListAdapter;
import com.example.utsrezzaagustin.R;
import com.example.utsrezzaagustin.databinding.FragmentDashboardBinding;
import com.example.utsrezzaagustin.word.WordListAdapter;
import com.example.utsrezzaagustin.word.WordViewModel;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    private WordViewModel mWordViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.recyclerview;
        final WordListAdapter adapter = new WordListAdapter(new WordListAdapter.WordDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        mWordViewModel.getAllWords().observe(this, words -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(words);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}