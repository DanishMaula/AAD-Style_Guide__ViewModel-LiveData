package com.salim.android.viewmodel_livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.salim.android.viewmodel_livedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

     private lateinit var viewModel: GetScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(GetScoreViewModel::class.java)

        initView()

        binding.btnPlusScoreTeamA.setOnClickListener(this)
        binding.btnPlusScoreTeamB.setOnClickListener(this)
        binding.btnMinusScoreTeamA.setOnClickListener(this)
        binding.btnMinusScoreTeamB.setOnClickListener(this)
        binding.btnReset.setOnClickListener(this)
    }

    private fun initView() {
        viewModel.getScoreA()?.observe(this, {
            // menampilkan livedata agar
            // setiap perubahan yg ada di live data bisa di tampilkan
            if (it != null){
                binding.tvScoreTeamA.text = it.toString()
            }
        })

        viewModel.getScoreB()?.observe(this, {
            if (it != null){
                binding.tvScoreTeamB.text = it.toString()
            }
        })

    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btnPlusScoreTeamA -> {
                viewModel.addScoreA()
            }
            R.id.btnMinusScoreTeamA -> {
                viewModel.minScoreA()
            }
            R.id.btnPlusScoreTeamB -> {
                viewModel.addScoreB()
            }
            R.id.btnMinusScoreTeamB -> {
                viewModel.minScoreB()
            }
            R.id.btnReset -> {
                viewModel.resetScore()
            }
        }
    }



}