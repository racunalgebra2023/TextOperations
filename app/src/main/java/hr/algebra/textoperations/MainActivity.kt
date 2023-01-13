package hr.algebra.textoperations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import hr.algebra.textoperations.data.Dummy
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity( ) {
    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_main )

        listView.adapter = ArrayAdapter( this, android.R.layout.simple_list_item_1, Dummy.PROGRAMMING_LANGUAGES )

        swiper.setOnRefreshListener {
            Toast.makeText( this, "Potvrda da radi!", Toast.LENGTH_SHORT ).show( )
        }
    }
}