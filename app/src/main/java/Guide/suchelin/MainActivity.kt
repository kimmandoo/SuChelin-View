package Guide.suchelin

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import Guide.suchelin.List.ListFragment
import Guide.suchelin.Map.MapsFragment
import Guide.suchelin.Vote.VoteFragment
import Guide.suchelin.config.BaseActivity
import Guide.suchelin.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

        auth.signInAnonymously()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Log.d("MainActivity", user!!.uid)
                    //로그인됐으면 uid값 찍힘 !!는 not null임을 명시하는 것.
                } else {
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }

        //bottom_navigaion
        val bottomMenu = findViewById<BottomNavigationView>(R.id.bottomTabBar)

        bottomMenu.setOnItemSelectedListener {
            getFragment(it)
        }
        bottomMenu.setOnItemReselectedListener {
            Log.d("Main","Menu Reselected")
        }
    }

    private fun getFragment(menuItem: MenuItem): Boolean {

        when(menuItem.itemId){
            R.id.menuList -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, ListFragment()).commit()
            }
            R.id.menuMap -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, MapsFragment()).commit()
            }
            else -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, VoteFragment()).commit()
            }
        }
        return true
    }
    private var doubleClicked = false

    override fun onBackPressed() {
        Log.d("Main", "BackPressed")
        if (doubleClicked == true){
            finish()
        }
        doubleClicked = true
        Toast.makeText(this, "한 번 더 뒤로가면 종료됩니다",Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed({
            doubleClicked = false
        }, 1500)
    }
}
