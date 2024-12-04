package appswing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inicial {

    private String tipoUsuario;

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public Inicial(String tipoUsuario) {

        this.tipoUsuario = tipoUsuario;

        JFrame frame = new JFrame("ContratAe - Candidato");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));

        JButton btnEntrar = new JButton("Entrar");
        JButton btnCriarConta = new JButton("Criar conta");

        // Botão Entrar
        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {      
                new Login(getTipoUsuario());
                frame.dispose();
            }
        });

        // Botão Criar Conta
        btnCriarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getTipoUsuario().equals("c")){
                    new CadastroCandidato(getTipoUsuario());
                    frame.dispose();
                } else if (getTipoUsuario().equals("r")){
                    new CadastroRecrutador(getTipoUsuario());
                    frame.dispose();
                }
            }
        });

        panel.add(btnEntrar);
        panel.add(btnCriarConta);

        frame.add(panel);
        frame.setVisible(true);
    }




}

