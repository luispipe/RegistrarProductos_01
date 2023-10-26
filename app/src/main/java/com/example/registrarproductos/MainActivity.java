package com.example.registrarproductos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText name,quantity,prize;
    Button register, close;
    TableLayout table;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name= findViewById(R.id.editTextProducto);
        quantity=findViewById(R.id.editTextCantidad);
        prize=findViewById(R.id.editTextPrecio);

        register=findViewById(R.id.buttonRegistrar);
        close= findViewById(R.id.buttonFinalizar);
        table= findViewById(R.id.tableProducts);

        ArrayList<Producto> canasta= new ArrayList<>();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre= name.getText().toString();
                String cantidad= quantity.getText().toString();
                String precio= prize.getText().toString();
                if(nombre.isEmpty() || cantidad.isEmpty() || precio.isEmpty()){
                    Toast.makeText(getApplicationContext(),
                "Todos los campos deben llenarse",Toast.LENGTH_LONG).show();
                }else{
                    int cantidadNew= Integer.parseInt(cantidad);
                    int precioNew= Integer.parseInt(precio);
                    Producto nuevo= new Producto(nombre,cantidadNew,precioNew);
                    canasta.add(nuevo);
                    total(canasta);
                    llenarTabla(nuevo);
                    limpiarRegistro();
                }
            }
        });



    }

    public void llenarTabla(Producto product){
        int valor= product.getPrecio()*product.getCantidad();
        TableRow fila= new TableRow(this);

        TextView cell1= new TextView(this);
        cell1.setText(product.getNombre());
        cell1.setWidth(110);
        cell1.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
        fila.addView(cell1);

        TextView cell2= new TextView(this);
        cell2.setText(product.getCantidad()+"");
        cell2.setWidth(85);
        cell2.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
        fila.addView(cell2);

        TextView cell3= new TextView(this);
        cell3.setText(product.getPrecio()+"");
        cell3.setWidth(80);
        cell3.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
        fila.addView(cell3);

        TextView cell4= new TextView(this);
        cell4.setText(valor+"");
        cell4.setWidth(85);
        cell4.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
        fila.addView(cell4);

        table.addView(fila);

    }

    public void limpiarRegistro(){
        name.setText("");
        quantity.setText("");
        prize.setText("");
    }

    public void total(ArrayList<Producto>compra){
        int total=0;
        for (Producto i:compra){
            total+= i.getPrecio()*i.getCantidad();
        }
        System.out.println(total);
    }


}