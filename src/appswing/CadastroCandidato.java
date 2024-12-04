package appswing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import modelo.Candidato;
import regras_negocio.Fachada;
import daodb4o.*;

public class CadastroCandidato {
    public CadastroCandidato() {
        JFrame frame = new JFrame("ContratAe - Cadastro do Candidato");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel lblCPF = new JLabel("CPF:");
        JTextField txtCPF = new JTextField();

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField();

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();

        JLabel lblArea = new JLabel("Área de Atuação:");
        JTextField txtArea = new JTextField();

        JLabel lblHabilidades = new JLabel("Habilidades (separadas por vírgula):");
        JTextField txtHabilidades = new JTextField();

        JButton btnCadastrar = new JButton("Cadastrar");

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aqui você pode capturar os dados e salvar
                String cpf = txtCPF.getText().trim();
                String nome = txtNome.getText().trim();
                String email = txtEmail.getText().trim();
                String area = txtArea.getText().trim();
                String habilidades = txtHabilidades.getText().trim();

                DAO.begin();

                String[] palavras = habilidades.split(","); // Separa a string por vírgulas
                ArrayList<String> Myhabilidades = new ArrayList<>();

                // Adiciona cada palavra à lista
                for (String palavra : palavras) {
                    Myhabilidades.add(palavra);
                }
                try {
                    Fachada.criarCandidato(nome, cpf, email, Myhabilidades, area);
                } catch (Exception ae) {
                    DAO.rollback();
                    System.out.println(ae.getMessage());
                }
                DAO.commit();

                if (cpf.isEmpty() || nome.isEmpty() || email.isEmpty() || area.isEmpty() || habilidades.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor, preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Cadastro realizado com sucesso!");
                    frame.dispose(); // Fecha a tela após o cadastro
                }
            }
        });

        panel.add(lblCPF);
        panel.add(txtCPF);
        panel.add(lblNome);
        panel.add(txtNome);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblArea);
        panel.add(txtArea);
        panel.add(lblHabilidades);
        panel.add(txtHabilidades);
        panel.add(new JLabel());
        panel.add(btnCadastrar);

        frame.add(panel);
        frame.setVisible(true);
    }
}
