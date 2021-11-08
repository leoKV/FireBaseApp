package mx.edu.utng.appfirebasegds0341;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private TextView tvMensaje;
    private EditText etTexto;

    DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
    DatabaseReference mensajeRef= ref.child("mimensaje");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMensaje= (TextView) findViewById(R.id.tvMensaje);
        etTexto= (EditText)  findViewById(R.id.etTexto);
    }

    public void modificar(View view) {
        String mensaje= etTexto.getText().toString();
        mensajeRef.setValue(mensaje); //se cambia el valor en la base de datos
        etTexto.setText(""); //para limpiar el contenedor de texto
    }

    @Override
    protected void onStart() {
        super.onStart();

        mensajeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value= snapshot.getValue(String.class);
                tvMensaje.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}