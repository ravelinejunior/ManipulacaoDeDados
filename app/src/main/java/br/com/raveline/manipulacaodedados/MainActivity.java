package br.com.raveline.manipulacaodedados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText textoNomeSharedPref;
    private TextView textoExibidoResultadoSharedPref;
    private Button botaoSalvarSharedPref;
    private static final String CONST_VALOR_ARQUIVO = "sharedPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carregarElementos();

        botaoSalvarSharedPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //criar um arquivo dentro do telefone do usuario apenas desse aplicativo
                SharedPreferences sharedPreferences = getSharedPreferences(CONST_VALOR_ARQUIVO,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit(); // permite editar o arquivo, retorna objeto do tipo EDITOR
                String nomeDigitado = textoNomeSharedPref.getText().toString();
                if (nomeDigitado.equalsIgnoreCase("")){
                    Snackbar.make(v,"Digite um nome!",Snackbar.LENGTH_LONG).show();
                }
                else{
                    //passando valores
                    editor.putString("nomeDigitado",nomeDigitado);
                   // editor.commit(); //salva os dados
                    editor.apply(); //salva os dados
                    textoExibidoResultadoSharedPref.setText("E ai "+nomeDigitado);
                    textoNomeSharedPref.setText("");

                }



            }
        });

        //recuperando os dados salvos
        SharedPreferences sharedPreferences = getSharedPreferences(CONST_VALOR_ARQUIVO,MODE_PRIVATE);

        //valida se tem dados de preferencia
        if (sharedPreferences.contains("nomeDigitado")){
                //validando a chave
                String nomeDigitado = sharedPreferences.getString("nomeDigitado","unknow");
                textoExibidoResultadoSharedPref.setText("E ai "+nomeDigitado);
        } else{
            Toast.makeText(this, "valor invalido", Toast.LENGTH_SHORT).show();
                }


    }

    public void carregarElementos(){
        textoNomeSharedPref = findViewById(R.id.input_editText_id);
        textoExibidoResultadoSharedPref = findViewById(R.id.texto_id_exibe_nome_sharedPref);
        botaoSalvarSharedPref = findViewById(R.id.botao_salvar_sharedPref);

    }
}
