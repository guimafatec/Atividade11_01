package br.edu.fateczl.atividade11_01;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.edu.fateczl.atividade11_01.controller.TimeController;
import br.edu.fateczl.atividade11_01.persistence.JogadorDao;
import br.edu.fateczl.atividade11_01.persistence.TimeDao;


public class TimeFragment extends Fragment {
    /*
     * @author: Gustavo Guimarães de Oliveira
     */
    private View view;
    private EditText etCodigoTime, etNomeTime, etCidadeTime;
    private Button btnBuscarTime, btnInserirTime, btnListarTimes, btnModificarTime, btnExcluirTime;
    private TextView tvListarTimes;
    private TimeController timeCtrl;


    public TimeFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_time, container, false);
        etCodigoTime = view.findViewById(R.id.etCodigoTime);
        etNomeTime = view.findViewById(R.id.etNomeTime);
        etCidadeTime = view.findViewById(R.id.etCidadeTime);
        btnBuscarTime = view.findViewById(R.id.btnBuscarTime);
        btnInserirTime = view.findViewById(R.id.btnInserirTime);
        btnModificarTime = view.findViewById(R.id.btnModificarTime);
        btnExcluirTime = view.findViewById(R.id.btnExcluirTime);
        btnListarTimes = view.findViewById(R.id.btnListarTimes);
        tvListarTimes = view.findViewById(R.id.tvListarTimes);

        TimeController timeController = new TimeController(new TimeDao(view.getContext()));
        return view;
    }
}