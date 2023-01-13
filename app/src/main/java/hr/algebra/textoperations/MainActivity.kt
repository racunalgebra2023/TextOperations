package hr.algebra.textoperations

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hr.algebra.textoperations.data.Dummy
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random
import kotlin.random.nextInt


class MainActivity : AppCompatActivity( ) {

    val TAG = "MainActivity"
    val adapterList = Dummy.PROGRAMMING_LANGUAGES.toMutableList( )

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_main )

        listView.adapter =
            ArrayAdapter( this, android.R.layout.simple_list_item_1, adapterList )

        swiper.setOnRefreshListener {
            val newList = getNewList( )
            adapterList.clear( )
            adapterList.addAll( newList )
            ( listView.adapter as ArrayAdapter< String > ).notifyDataSetChanged( )
            swiper.isRefreshing = false
        }
    }

    private fun getNewList( ) : List< String > {
        val brojElemenata = Random.nextInt( 0 until Dummy.PROGRAMMING_LANGUAGES.size-1 )
        Log.i( TAG, "Broj elemenata: $brojElemenata" )
        val newList = ArrayList< String >( )
        val indexi = HashSet< Int >( )
        while ( indexi.size<brojElemenata ) {
            val index = Random.nextInt( 0 until Dummy.PROGRAMMING_LANGUAGES.size-1 )
            if( indexi.add( index ) )
                newList.add( Dummy.PROGRAMMING_LANGUAGES[index] )
        }
        return newList
    }


    override fun onCreateOptionsMenu( menu: Menu? ): Boolean {
        menuInflater.inflate( R.menu.main_menu, menu )
        return true
    }

    override fun onOptionsItemSelected( item: MenuItem ): Boolean {
        when( item.itemId ) {
            R.id.pozdrav -> Toast
                                .makeText( this, "Pozdrav iz menu-a", Toast.LENGTH_SHORT )
                                .show()
            R.id.drugi -> startActivity( Intent( this, MainActivity2::class.java ) )
        }
        return super.onOptionsItemSelected( item )
    }

}