package com.montagat.montagatDetail

import android.annotation.SuppressLint
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.montagat.databinding.FragmentMontagatDetailsBinding
import com.montagat.model.MontagModel

class MontagatDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMontagatDetailsBinding

    private lateinit var montagDetails: MontagModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMontagatDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchMontagat()
        viewViews()
    }

    private fun fetchMontagat() {
        montagDetails = requireArguments().getSerializable("details") as MontagModel
    }

    @SuppressLint("SetTextI18n")
    private fun viewViews() {
        Glide
            .with(requireContext())
            .load(montagDetails.image)
            .into(binding.imgMontag)

        binding.txtNameMontag.text = montagDetails.name
        binding.txtDescriptionMontag.text = montagDetails.description
        binding.txtPriceMontag.text = "${montagDetails.price} EGP"
        binding.txtOldPriceMontag.text = "${montagDetails.oldPrice} EGP"
        binding.txtOldPriceMontag.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

    }

}