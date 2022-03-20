package com.atarious.userdetails

import android.location.Address
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.atarious.userdetails.Model.User
import com.atarious.userdetails.api.UserApiServices
import com.atarious.userdetails.databinding.FragmentDemoBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class UserDetailsFragment : Fragment() {


    private var _binding: FragmentDemoBinding? = null
    private var UserID :Number = 0
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val userApiServices = UserApiServices.create()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.findViewById<TextView>(R.id.custom_title)?.setText("User Details Hub")
        _binding = FragmentDemoBinding.inflate(inflater, container, false)
      return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.layoutsWrongID.visibility = View.INVISIBLE
        binding.layoutSIDNOTFOUND.setVisibility(View.INVISIBLE)
        binding.layouts.setVisibility(View.INVISIBLE)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_DemoFragment_to_HomeFragment)
        }
        binding.Search.setOnClickListener{
            if(binding.TextFieldForID.getText().toString().trim().length <= 0){

                binding.layoutsWrongID.setVisibility(View.VISIBLE)
                binding.layoutSIDNOTFOUND.setVisibility(View.INVISIBLE)
                binding.layouts.setVisibility(View.INVISIBLE)

            }else{
                UserID=binding.TextFieldForID.getText().toString().toInt()

                if((UserID as Int) <=0|| UserID as Int >10){

                    binding.layoutSIDNOTFOUND.setVisibility(View.VISIBLE)
                    binding.layouts.setVisibility(View.INVISIBLE)
                    binding.layoutsWrongID.setVisibility(View.INVISIBLE)

                }else{

                    val user = userApiServices.GetUser(UserID.toString())
                    user.enqueue(object : Callback<User>{
                        override fun onResponse(call: Call<User>, response: Response<User>) {
                            val body = response.body()
                            body?.let {
                                binding.IDForUSer.text = "ID : ${it.id}"
                                binding.UserName.text = "Name :${it.name}"
                                binding.UserUserName.text = "UserName : ${it.username}"
                                binding.UserEmail.text = "Email : ${it.email}"
                                binding.city.text = "City : ${it.address.city}"
                                binding.Phone.text = "Phone: ${it.phone}"
                                binding.Website.text = "WebSite : ${it.website}"
                                binding.company.text = "Company : ${it.company.name}"
                            }
                        }

                        override fun onFailure(call: Call<User>, t: Throwable) {
                           Log.i("DemoFragment ",t.message!!)
                        }

                    })
                    binding.IDForUSer.setText("ID : $UserID")
                    binding.layoutsWrongID.setVisibility(View.INVISIBLE)
                    binding.layoutSIDNOTFOUND.setVisibility(View.INVISIBLE)
                    binding.layouts.setVisibility(View.VISIBLE)

                }
            }


        }
        binding.Clear.setOnClickListener{
            binding.layoutsWrongID.setVisibility(View.INVISIBLE)
            binding.layoutSIDNOTFOUND.setVisibility(View.INVISIBLE)
            binding.layouts.setVisibility(View.INVISIBLE)
            binding.TextFieldForID.text = null
        }
    }
override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


