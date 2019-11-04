package mx.edu.ittepic.tpdm_u3_practica2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.ErrnoException
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.net.URL

class MainActivity : AppCompatActivity() {

    var descripcion : EditText?= null
    var monto : EditText?= null
    var fechaVencimiento : EditText?= null
    var pagado : EditText?= null
    var insertar : Button?= null
    var cargar : Button?= null
    var consulta : TextView?= null

    var jsonRegreso = ArrayList<org.json.JSONObject>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        descripcion = findViewById(R.id.descripcion)
        monto = findViewById(R.id.monto)
        fechaVencimiento = findViewById(R.id.fechaVencimiento)
        pagado = findViewById(R.id.pagado)
        insertar = findViewById(R.id.insertar)
        cargar = findViewById(R.id.cargar)
        consulta = findViewById(R.id.consulta)

        insertar?.setOnClickListener {
            var conexionWeb = WebConnect(this)
            conexionWeb.agregarVariables("descripcion",descripcion?.text.toString())
            conexionWeb.agregarVariables("monto",monto?.text.toString())
            conexionWeb.agregarVariables("fecha",fechaVencimiento?.text.toString())
            conexionWeb.agregarVariables("pagado",pagado?.text.toString())
            conexionWeb.execute(URL("http://mysterious-sierra-80681.herokuapp.com/insertar.html"))
        }
        cargar?.setOnClickListener {
            try{

                var conexionWeb = WebConnect(this)
                Toast.makeText(this, "click",Toast.LENGTH_SHORT).show()
                conexionWeb.execute(URL("http://mysterious-sierra-80681.herokuapp.com/consultagenerica.php"))

            }catch ( err: Exception  ){

            }
        }
    }
    fun mostrarRespuesta(cadena: String){
        var jsonArray = org.json.JSONArray(cadena)
        var total=jsonArray.length()
        try{
            (0..total).forEach {
                jsonRegreso.add((jsonArray.getJSONObject(it)))
            }
        }catch (err: Error){

        }


    }
}
