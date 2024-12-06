package appswing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import regras_negocio.Fachada;

public class CadastroCandidato {

    public CadastroCandidato() {
        JFrame frame = new JFrame("ContratAe - Cadastro do Candidato");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));

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

        // Ajuste para validar o CPF, nome, e-mail, e outros campos antes de salvar
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Captura os dados dos campos
                String cpf = txtCPF.getText().trim();
                String nome = txtNome.getText().trim();
                String email = txtEmail.getText().trim();
                String area = txtArea.getText().trim();
                String habilidades = txtHabilidades.getText().trim();
                
                // Verificar se algum campo está vazio
                if (cpf.isEmpty() || nome.isEmpty() || email.isEmpty() || area.isEmpty() || habilidades.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor, preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Separar habilidades por vírgula
                String[] palavras = habilidades.split(",");
                ArrayList<String> myHabilidades = new ArrayList<>();
                for (String palavra : palavras) {
                    myHabilidades.add(palavra.trim());  // Remover espaços extras
                }

                // Iniciar o processo de cadastro
                try {
                    // Chama a Fachada para criar o Candidato
                    Fachada.criarCandidato(nome, cpf, email, myHabilidades, area);

                    // Exibe a tela do candidato (em vez de criar uma nova instância sem exibir)
                    new TelaCandidato(Fachada.localizarCandidato(cpf)).setVisible(true);

                    // Exibe mensagem de sucesso
                    JOptionPane.showMessageDialog(frame, "Cadastro realizado com sucesso!");
                    frame.dispose();  // Fecha a tela de cadastro
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao cadastrar o candidato: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
        panel.add(lblArea);
        panel.add(txtArea);
        panel.add(lblHabilidades);
        panel.add(txtHabilidades);
        panel.add(new JLabel());  // Espaço vazio para alinhar o botão
        panel.add(btnCadastrar);

        // Configura o painel no frame
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new CadastroCandidato();  // Inicia a tela de cadastro do candidato
    }
}
