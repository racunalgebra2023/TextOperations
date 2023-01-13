package hr.algebra.textoperations

import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import hr.algebra.textoperations.data.Dummy
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.listView
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.*


class MainActivity2 : AppCompatActivity() {

    val TAG = "MainActivity2"
    val adapterList = Dummy.PROGRAMMING_LANGUAGES.toMutableList( )
    private lateinit var searchView : SearchView
    private lateinit var clipboard : ClipboardManager

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_main2 )

        clipboard = getSystemService( Context.CLIPBOARD_SERVICE ) as ClipboardManager

        listView.adapter =
            ArrayAdapter( this, android.R.layout.simple_list_item_1, adapterList )

        setupCopy( )
    }

    override fun onOptionsItemSelected( item: MenuItem): Boolean {
        when( item.itemId ) {
            R.id.paste -> {
                val x = clipboard.primaryClip?.getItemAt( 0 )?.text.toString( )
                searchView.setQuery( x, true )
            }
        }
        return super.onOptionsItemSelected( item )
    }



    private fun setupCopy( ) {
        eText.setOnLongClickListener {
            val text = ( it as EditText ).text
            val clip = ClipData.newPlainText( "moj text", text )
            clipboard.setPrimaryClip( clip )
            true
        }
    }


    override fun onCreateOptionsMenu( menu: Menu? ): Boolean {
        menuInflater.inflate( R.menu.main_menu_2, menu )
        val searchItem = menu?.findItem( R.id.action_search )
        searchView = searchItem?.actionView as  SearchView
        setupSearch( )
        return true
    }

    private fun setupSearch( ) {
        searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit( query: String? ): Boolean = true

                override fun onQueryTextChange( newText: String? ): Boolean {
                    adapterList.clear( )
                    if( newText.isNullOrEmpty( ) )
                        adapterList.addAll( Dummy.PROGRAMMING_LANGUAGES )
                    else
                        Dummy.PROGRAMMING_LANGUAGES.forEach {
                            if( it.lowercase( ).contains( newText.lowercase( ) ) )
                                adapterList.add( it )
                        }
                    ( listView.adapter as ArrayAdapter< String > ).notifyDataSetChanged( )
                    return true
                }
            }
        )
    }
}