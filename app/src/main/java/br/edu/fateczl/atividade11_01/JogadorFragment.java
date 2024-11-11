package br.edu.fateczl.atividade11_01;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.time.LocalDate;

import br.edu.fateczl.atividade11_01.controller.JogadorController;
import br.edu.fateczl.atividade11_01.model.Jogador;
import br.edu.fateczl.atividade11_01.persistence.JogadorDao;


public class JogadorFragment extends Fragment {
    /*
     * @author: Gustavo Guimarães de Oliveira
     */
    private View view;
    private EditText etCodigoJogador, etNomeJogador, etDataNascimento, etPesoJogador, etAlturaJogador;
    private Button btnBuscarJogador, btnInserirJogador, btnListarJogadores, btnModificarJogador, btnExcluirJogador;
    private Spinner spJogadorTime;
    private TextView tvListarJogador;
    private JogadorController jogadorCtrl;

    public JogadorFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_jogador, container, false);
        etCodigoJogador = view.findViewById(R.id.etCodigoJogador);
        etNomeJogador = view.findViewById(R.id.etNomeJogador);
        etDataNascimento = view.findViewById(R.id.etDataNascimento);
        etPesoJogador = view.findViewById(R.id.etPesoJogador);
        etAlturaJogador = view.findViewById(R.id.etAlturaJogador);
        btnBuscarJogador = view.findViewById(R.id.btnBuscarJogador);
        btnInserirJogador = view.findViewById(R.id.btnInserirJogador);
        btnModificarJogador = view.findViewById(R.id.btnModificarJogador);
        btnExcluirJogador = view.findViewById(R.id.btnExcluirJogador);
        btnListarJogadores = view.findViewById(R.id.btnListarJogadores);
        tvListarJogador = view.findViewById(R.id.tvListarJogador);
        tvListarJogador.setMovementMethod(new ScrollingMovementMethod());

        jogadorCtrl = new JogadorController(new JogadorDao(view.getContext()));
        btnInserirJogador.setOnClickListener(op -> acaoInserir());
        btnModificarJogador.setOnClickListener(op -> acaoModificar());
        btnExcluirJogador.setOnClickListener(op -> acaoExcluir());
        btnListarJogadores.setOnClickListener(op -> acaoListar());
        return view;
    }

    private void acaoInserir() {
    }

    private void acaoModificar() {
    }

    private void acaoExcluir() {
    }

    private void acaoListar() {
        
    }

    private void preencheSpinner(){
        Jogador jog = new Jogador();
        jog.setId(0);;
        jog.setNome("Selecione um jogador");
        jog.setAltura(0f);
        jog.setPeso(0f);
        jog.setDataNasc(null);
    }
    private Jogador montaJogador(){
        Jogador jogador = new Jogador();
        jogador.setId(Integer.parseInt(etCodigoJogador.getText().toString()));
        jogador.setNome(etNomeJogador.getText().toString());
        jogador.setDataNasc(LocalDate.parse(etDataNascimento.getText().toString()));
        jogador.setPeso(Float.parseFloat(etPesoJogador.getText().toString()));
        jogador.setAltura(Float.parseFloat(etAlturaJogador.getText().toString()));
        return null;
    }
}