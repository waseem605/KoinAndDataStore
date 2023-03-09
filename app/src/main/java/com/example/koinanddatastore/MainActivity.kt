package com.example.koinanddatastore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.asLiveData
import com.example.koinanddatastore.retrofitWithKoin.Component
import com.example.koinanddatastore.retrofitWithKoin.MainViewModel
import com.example.koinanddatastore.retrofitWithKoin.PostActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.qualifier.named
import org.koin.android.ext.android.inject

//@KoinApiExtension
class MainActivity : AppCompatActivity() {

    lateinit var etName: EditText
    lateinit var etAge: EditText
    lateinit var tvName: TextView
    lateinit var tvAge: TextView
    lateinit var saveButton: Button
    lateinit var mPreferenceManagerClass : PreferenceManagerClass
    lateinit var userManager: UserManager
    var age = 0
    var name = ""
    val mainViewModel :MainViewModel by viewModels()


    val mComponent = Component()

    private val hello by inject<String>(named("bye"))


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(this,PostActivity::class.java))

//        mComponent.car.getCar()
//        mComponent.main.getMain()

//        val viewModel = getViewModel<MainViewModel>()
//        mainViewModel.doNetworkCall()


mPreferenceManagerClass = PreferenceManagerClass(this)
        etName = findViewById(R.id.et_name)
        etAge = findViewById(R.id.et_age)
        tvName = findViewById(R.id.tv_name)
        tvAge = findViewById(R.id.tv_age)
        saveButton = findViewById(R.id.btn_save)

        // Get reference to our userManager class
        userManager = UserManager(this)

        // this function saves the data to
        // preference data store on click of
        // save Button
        buttonSave()

        // this function retrieves the saved data
        // as soon as they are stored and even
        // after app is closed and started again
        observeData()
    }

    private fun buttonSave() {
        // Gets the user input and saves it
        saveButton.setOnClickListener {
            name = etName.text.toString()
            age = etAge.text.toString().toInt()
            val list = java.util.ArrayList<String>()
            list.add("ali" )
            list.add("ali 0" )
            list.add("ali 1" )

            //
            mPreferenceManagerClass.putStringArrayList("Array",list)
            // Stores the values
            // Since the storeUser function of UserManager
            // class is a suspend function
            // So this has to be done in a coroutine scope
            GlobalScope.launch {
                userManager.storeUser(age, name)
            }
        }
    }

    private fun observeData() {
        // Updates age
        // every time user age changes it will be observed by userAgeFlow
        // here it refers to the value returned from the userAgeFlow function
        // of UserManager class
        this.userManager.userAgeFlow.asLiveData().observe(this) {
            age = it
            tvAge.text = it.toString()
        }

        // Updates name
        // every time user name changes it will be observed by userNameFlow
        // here it refers to the value returned from the usernameFlow function
        // of UserManager class
        userManager.userNameFlow.asLiveData().observe(this) {
            name = it
            tvName.text = it.toString()
        }
    }
}