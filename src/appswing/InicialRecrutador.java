package appswing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicialRecrutador {
    public InicialRecrutador() {
        JFrame frame = new JFrame("ContratAe - Recrutador");
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
                new LoginRecrutador();
                frame.dispose();
            }
        });

        // Botão Criar Conta
        btnCriarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroRecrutador();
                frame.dispose();
            }
        });

        panel.add(btnEntrar);
        panel.add(btnCriarConta);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new InicialRecrutador();
    }
}

