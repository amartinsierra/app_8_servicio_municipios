package graficos.curso.ejercicios.app_8_servicio_municipios;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import modelo.ConexionServicio;

public class MainActivity extends AppCompatActivity {
    ListView lstMunicipios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void municipios(View v){
        EditText edCodigo=this.findViewById(R.id.edCodigo);
        Conexion con=new Conexion();
        con.execute(edCodigo.getText().toString());
    }

    private class Conexion extends AsyncTask<String,Void, List<String>>{
        @Override
        protected void onPostExecute(List<String> strings) {
            super.onPostExecute(strings);
            lstMunicipios=MainActivity.this.findViewById(R.id.lstMunicipios);
            ArrayAdapter<String> adp=new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,strings);
            lstMunicipios.setAdapter(adp);
        }

        @Override
        protected List<String> doInBackground(String... params) {
            ConexionServicio con=new ConexionServicio();
            return con.obtenerMunicipios(Integer.parseInt(params[0]));
        }
    }
}
