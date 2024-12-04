package appswing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {

    private String tipoUsuario;

    public String getTipoUsuario() {
        return tipoUsuario;
    }
    
    public Login(String tipoUsuario) {

        this.tipoUsuario = tipoUsuario;

        JFrame frame = new JFrame("ContratAe - Login");
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
                    lblMensagem.setText("Login realizado com sucesso!");
                    lblMensagem.setForeground(Color.GREEN);

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

