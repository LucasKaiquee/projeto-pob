package appswing;

import daodb4o.DAO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import regras_negocio.Fachada;

public class CadastroRecrutador {

    public CadastroRecrutador() {

        JFrame frame = new JFrame("ContratAe - Cadastro do Recrutador");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));  // Aumentei para 6 linhas para deixar mais espaçado

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
                // Captura os dados
                String cpf = txtCPF.getText().trim();
                String nome = txtNome.getText().trim();
                String email = txtEmail.getText().trim();
                String empresa = txtEmpresa.getText().trim();

                // Validação dos campos
                if (cpf.isEmpty() || nome.isEmpty() || email.isEmpty() || empresa.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor, preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validação do nome (permitir espaços no nome)
                if (!nome.matches("[a-zA-Z\\s]+")) {
                    JOptionPane.showMessageDialog(frame, "Por favor, preencha o campo 'Nome' apenas com caracteres válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validação do email (básica para formato de email)
                if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                    JOptionPane.showMessageDialog(frame, "Por favor, insira um e-mail válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Inicia a transação de banco de dados
                DAO.begin();
                try {
                    // Chama a Fachada para cadastrar o recrutador
                    Fachada.criarRecrutador(cpf, nome, email, empresa);

                    // Exibe a tela do recrutador após cadastro
                    new TelaRecrutador(Fachada.localizarRecrutador(cpf)).setVisible(true);

                    // Mensagem de sucesso e fechamento da tela de cadastro
                    JOptionPane.showMessageDialog(frame, "Cadastro realizado com sucesso!");
                    frame.dispose();
                } catch (Exception ex) {
                    // Exibe erro em caso de falha na criação do recrutador
                    JOptionPane.showMessageDialog(frame, "Erro ao cadastrar recrutador: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Adiciona os componentes ao painel
        panel.add(lblCPF);
        panel.add(txtCPF);
        panel.add(lblNome);
        panel.add(txtNome);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblEmpresa);
        panel.add(txtEmpresa);
        panel.add(new JLabel());  // Espaço vazio para alinhar o botão
        panel.add(btnCadastrar);

        // Adiciona o painel no frame e exibe
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new CadastroRecrutador();  // Inicia a tela de cadastro do recrutador
    }
}
