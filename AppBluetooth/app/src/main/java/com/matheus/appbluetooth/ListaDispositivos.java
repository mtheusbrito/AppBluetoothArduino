package com.matheus.appbluetooth;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

/**
 * Created by matheus on 16/04/18.
 */

public class ListaDispositivos extends ListActivity {
    private BluetoothAdapter meuBluetoothAdapter = null;
    static  String ENDERECO_MAC = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> ArrayBluetooth = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        meuBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        Set<BluetoothDevice> dispositivosPareados = meuBluetoothAdapter.getBondedDevices();
        if(dispositivosPareados.size()> 0){
            for (BluetoothDevice dispositivo: dispositivosPareados){
                String nomeBt = dispositivo.getName();
                String macBt = dispositivo.getAddress();
                ArrayBluetooth.add(nomeBt + "\n" +macBt);


            }

        }
        setListAdapter(ArrayBluetooth);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String informacaoGeral = ((TextView)v).getText().toString();
        //Toast.makeText(this, "Info:" + informacaoGeral, Toast.LENGTH_SHORT).show();



        String enderecoMac = informacaoGeral.substring(informacaoGeral.length() - 17);
      //  Toast.makeText(this, ""+enderecoMac, Toast.LENGTH_SHORT).show();
        Intent retornaMac = new Intent();
        retornaMac.putExtra(ENDERECO_MAC, enderecoMac);
        setResult(RESULT_OK,retornaMac);
        finish();
    }
}
