package appswing;

import javax.swing.*;

import daodb4o.DAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import regras_negocio.Fachada;

public class CadastroRecrutador {
    public CadastroRecrutador() {
        JFrame frame = new JFrame("ContratAe - Cadastro do Recrutador");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel lblCPF = new JLabel("CPF:");
        JTextField txtCPF = new JTextField();

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField();

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();

        JLabel lblEmpresa = new JLabel("Empresa:");
        JTextField txtEmpresa = new JTextField();

        JButton btnCadastrar = new JButton("Cadastrar");

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aqui você pode capturar os dados e salvar
                String cpf = txtCPF.getText().trim();
                String nome = txtNome.getText().trim();
                String email = txtEmail.getText().trim();
                String empresa = txtEmpresa.getText().trim();

                if (cpf.isEmpty() || nome.isEmpty() || email.isEmpty() || empresa.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor, preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    DAO.begin();
                    try {
                        Fachada.criarRecrutador(cpf, nome, email, empresa);
                        new Vagas();
                        
                    } catch (Exception ae) {
                        DAO.rollback();
                        System.out.println(ae.getMessage());
                    }
                    DAO.commit();
                    JOptionPane.showMessageDialog(frame, "Cadastro realizado com sucesso!");
                    frame.dispose();
                }


            }
        });

        panel.add(lblCPF);
        panel.add(txtCPF);
        panel.add(lblNome);
        panel.add(txtNome);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblEmpresa);
        panel.add(txtEmpresa);
        panel.add(new JLabel());
        panel.add(btnCadastrar);

        frame.add(panel);
        frame.setVisible(true);
    }
}
