package com.example.mobilefinalproject;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.example.mobilefinalproject.databinding.FragmentFirstBinding;
import com.example.mobilefinalproject.databinding.FragmentSecondBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    VideoView videoView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment using FragmentFirstBinding
        binding = FragmentFirstBinding.inflate(getLayoutInflater());

        // Set up VideoView to play a video from resources
        videoView = (VideoView) binding.vvHomeBackground;
        Uri uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.mp_video);
        videoView.setVideoURI(uri);
        videoView.start(); // Start playing the video

        // Loop the video when it's prepared
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true); // Set looping to true
            }
        });

        return binding.getRoot(); // Return the root view of the binding
    }

    @Override
    public void onResume() {
        videoView.resume(); // Resume video playback
        super.onResume();
    }

    @Override
    public void onPause() {
        videoView.suspend(); // Suspend video playback
        super.onPause();
    }

    @Override
    public void onDestroy() {
        videoView.stopPlayback(); // Stop video playback
        super.onDestroy();
    }

    // Handle click events for buttons in the fragment
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the second fragment when the login button is clicked
                NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_firstFragment_to_secondFragment);
            }
        });

        binding.btnRegisterPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the third fragment when the register button is clicked
                NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_firstFragment_to_thirdFragment);
            }
        });
    }
}