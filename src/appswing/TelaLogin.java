package appswing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import regras_negocio.Fachada;

public class TelaLogin extends JFrame {
    private JTextField txtCpf;
    private JButton btnEntrar;
    private JButton btnCadastrar;

    public TelaLogin() {
        setTitle("Login");
        setSize(300, 220);  // Aumenta a altura da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    
        JPanel panel = new JPanel();
        panel.setLayout(null);
    
        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(20, 20, 80, 25);
        panel.add(lblCpf);
    
        txtCpf = new JTextField();
        txtCpf.setBounds(100, 20, 150, 25);
        panel.add(txtCpf);
    
        btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(100, 80, 150, 30);
        panel.add(btnEntrar);
    
        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = txtCpf.getText();
                if (cpf.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Digite um CPF!");
                    return;
                }
        
                // Criação de um diálogo para escolher entre Candidato ou Recrutador
                String[] options = {"Candidato", "Recrutador"};
                int escolha = JOptionPane.showOptionDialog(null, 
                    "Selecione o tipo de usuário", 
                    "Escolha a opção de login", 
                    JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.INFORMATION_MESSAGE, 
                    null, options, options[0]);
        
                // Se o usuário escolheu Candidato
                if (escolha == 0) {
                    try {
                        if (Fachada.localizarCandidato(cpf) != null) {
                            new TelaCandidato(Fachada.localizarCandidato(cpf)).setVisible(true);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Candidato não encontrado!");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao localizar Candidato: " + ex.getMessage());
                    }
                } 
                // Se o usuário escolheu Recrutador
                else if (escolha == 1) {
                    try {
                        if (Fachada.localizarRecrutador(cpf) != null) {
                            new TelaRecrutador(Fachada.localizarRecrutador(cpf)).setVisible(true);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Recrutador não encontrado!");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao localizar Recrutador: " + ex.getMessage());
                    }
                } 
                // Caso não tenha escolha (cancelamento ou fechamento da janela)
                else {
                    JOptionPane.showMessageDialog(null, "Operação cancelada.");
                }
            }
        });
        
    
        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(100, 120, 150, 30);  // Ajusta a posição para dentro da tela
        panel.add(btnCadastrar);
    
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exibe um diálogo com duas opções: Candidato ou Recrutador
                String[] options = {"Candidato", "Recrutador"};
                int escolha = JOptionPane.showOptionDialog(
                    null, 
                    "Escolha o tipo de cadastro", 
                    "Tipo de Cadastro", 
                    JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.INFORMATION_MESSAGE, 
                    null, 
                    options, 
                    options[0]
                );
        
                if (escolha == 0) { // Se o usuário escolher "Candidato"
                    new CadastroCandidato();
                    dispose(); // Fecha a tela de login
                } else if (escolha == 1) { // Se o usuário escolher "Recrutador"
                    new CadastroRecrutador();
                    dispose(); // Fecha a tela de login
                }
            }
        });
    
        add(panel);
    }

    public static void main(String[] args) {
        new TelaLogin().setVisible(true);
        Fachada.inicializar();
    }
}