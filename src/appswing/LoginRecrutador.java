package appswing;

import javax.swing.*;

import regras_negocio.Fachada;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginRecrutador {
    public LoginRecrutador() {
        JFrame frame = new JFrame("ContratAe - Login do Recrutador");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        JLabel lblCPF = new JLabel("CPF:");
        JTextField txtCPF = new JTextField();
        JButton btnEntrar = new JButton("Entrar");
        JLabel lblMensagem = new JLabel("", JLabel.CENTER);

        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = txtCPF.getText().trim();
                if (cpf.isEmpty()) {
                    lblMensagem.setText("CPF não pode estar vazio!");
                    lblMensagem.setForeground(Color.RED);
                } else {
                    try {
                        Fachada.localizarRecrutador(cpf);
                        new Vagas();

                    } catch (Exception ae) {
                        JOptionPane.showMessageDialog(frame, ae.getMessage());
                       System.out.println(ae.getMessage());
                       frame.dispose();
                    }
                    // Aqui você pode adicionar a lógica para validar o CPF
                }
            }
        });

        panel.add(lblCPF);
        panel.add(txtCPF);
        panel.add(btnEntrar);
        panel.add(lblMensagem);

        frame.add(panel);
        frame.setVisible(true);
    }
}
