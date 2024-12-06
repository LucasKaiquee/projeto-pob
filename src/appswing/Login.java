package appswing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import modelo.Candidato;
import modelo.Recrutador;
import regras_negocio.Fachada;

public class Login {

    private String tipoUsuario;

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public Login(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;

        JFrame frame = new JFrame("ContratAe - Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        JLabel lblCPF = new JLabel("CPF:");
        JTextField txtCPF = new JTextField();
        JButton btnEntrar = new JButton("Entrar");
        JLabel lblMensagem = new JLabel("", JLabel.CENTER);

        lblMensagem.setForeground(Color.RED);

        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = txtCPF.getText().trim();
                if (cpf.isEmpty()) {
                    lblMensagem.setText("CPF não pode estar vazio!");
                } else {
                    try {
                        boolean loginBemSucedido = false;

                        if (getTipoUsuario().equals("r")) {
                            try {
                                Recrutador r = Fachada.localizarRecrutador(cpf);
                                if (r != null) {
                                    new GerenciarVaga(r);
                                    loginBemSucedido = true;
                                }
                            } catch (Exception ex) {
                                lblMensagem.setText("Erro ao localizar recrutador: " + ex.getMessage());
                            }
                        } else if (getTipoUsuario().equals("c")) {
                            try {
                                Candidato c = Fachada.localizarCandidato(cpf);
                                if (c != null) {
                                    new Candidatura();
                                    loginBemSucedido = true;
                                }
                            } catch (Exception ex) {
                                lblMensagem.setText("Erro ao localizar candidato: " + ex.getMessage());
                            }
                        }

                        if (loginBemSucedido) {
                            frame.dispose(); // Fecha a janela de login
                        } else if (lblMensagem.getText().isEmpty()) {
                            lblMensagem.setText("CPF não está cadastrado!");
                        }
                    } catch (Exception err) {
                        lblMensagem.setText("Erro inesperado ao tentar fazer login!");
                        err.printStackTrace(); // Log para debugging
                    }
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
