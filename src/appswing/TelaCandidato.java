package appswing;

import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Candidato;
import modelo.Vaga;
import regras_negocio.Fachada;

public class TelaCandidato extends JFrame {
    private JLabel lblBemVindo, lblInformacoesCandidato, lblHabilidades;
    private JButton btnEditarInformacoes, btnExcluirConta, btnSair, btnCandidatar, btnCancelarCandidatura;
    private JTable tabelaVagas;
    private JScrollPane scrollPaneVagas;
    private Candidato candidato;

    public TelaCandidato(Candidato candidato) {
        this.candidato = candidato;
        setTitle("Área do Candidato");
        setSize(600, 600); // Aumentado para comportar a tabela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        lblBemVindo = new JLabel("Bem-vindo, " + candidato.getNome() + "!");
        lblBemVindo.setBounds(20, 20, 300, 25);
        panel.add(lblBemVindo);

        lblInformacoesCandidato = new JLabel("<html><strong>Informações do Candidato:</strong><br>" +
                                             "Nome: " + candidato.getNome() + "<br>" +
                                             "Email: " + candidato.getEmail() + "<br>" +
                                             "Área: " + candidato.getArea() + "</html>");
        lblInformacoesCandidato.setBounds(20, 60, 400, 60);
        panel.add(lblInformacoesCandidato);

        lblHabilidades = new JLabel("<html><strong>Habilidades:</strong><br>" + listarHabilidades(candidato) + "</html>");
        lblHabilidades.setBounds(20, 120, 400, 60);
        panel.add(lblHabilidades);

        btnEditarInformacoes = new JButton("Editar Informações");
        btnEditarInformacoes.setBounds(20, 200, 150, 30);
        panel.add(btnEditarInformacoes);

        btnExcluirConta = new JButton("Excluir Conta");
        btnExcluirConta.setBounds(200, 200, 150, 30);
        panel.add(btnExcluirConta);

        // Configurando a tabela
        tabelaVagas = new JTable(new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Descrição", "Salário", "Área"}));
        tabelaVagas.setEnabled(false); // Tornar a tabela somente leitura
        tabelaVagas.getTableHeader().setReorderingAllowed(false); // Impedir reorganização das colunas

        scrollPaneVagas = new JScrollPane(tabelaVagas);
        scrollPaneVagas.setBounds(20, 240, 540, 150);
        panel.add(scrollPaneVagas);

        btnCandidatar = new JButton("Candidatar-se");
        btnCandidatar.setBounds(20, 410, 150, 30);
        panel.add(btnCandidatar);

        btnCancelarCandidatura = new JButton("Cancelar Candidatura");
        btnCancelarCandidatura.setBounds(200, 410, 150, 30);
        panel.add(btnCancelarCandidatura);

        btnSair = new JButton("Sair");
        btnSair.setBounds(400, 410, 150, 30);
        panel.add(btnSair);

        add(panel);

        configurarEventos();

        // Carregar e exibir vagas automaticamente ao iniciar
        exibirVagasDisponiveis();
    }

    private void configurarEventos() {
        btnEditarInformacoes.addActionListener(e -> {
            try {
                String novoNome = JOptionPane.showInputDialog("Digite o novo nome:");
                String novoEmail = JOptionPane.showInputDialog("Digite o novo e-mail:");
                String novaArea = JOptionPane.showInputDialog("Digite a nova área");

                Fachada.alterarCandidato(candidato.getCpf(), novoNome, novoEmail, novaArea);
                JOptionPane.showMessageDialog(null, "Informações atualizadas com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar informações: " + ex.getMessage());
            }
        });

        btnExcluirConta.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir sua conta?");
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    Fachada.excluirCandidato(candidato.getCpf());
                    JOptionPane.showMessageDialog(null, "Conta excluída com sucesso!");
                    dispose();
                    new TelaLogin();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir conta: " + ex.getMessage());
                }
            }
        });

        btnCandidatar.addActionListener(e -> {
            String idVagaStr = JOptionPane.showInputDialog("Digite o ID da vaga para se candidatar:");
            try {
                if (idVagaStr != null && !idVagaStr.isEmpty()) {
                    int idVaga = Integer.parseInt(idVagaStr);
                    Vaga vaga = Fachada.localizarVaga(idVaga);
                    if (vaga != null) {
                        Fachada.candidatar(candidato, vaga);
                        JOptionPane.showMessageDialog(null, "Candidatura realizada com sucesso para a vaga ID: " + idVaga);
                    } else {
                        JOptionPane.showMessageDialog(null, "Vaga não encontrada!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ID da vaga inválido!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Erro: O ID da vaga deve ser um número válido.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao se candidatar: " + ex.getMessage());
            }
        });

        btnCancelarCandidatura.addActionListener(e -> {
            String idVagaStr = JOptionPane.showInputDialog("Digite o ID da vaga para cancelar sua candidatura:");
            try {
                if (idVagaStr != null && !idVagaStr.isEmpty()) {
                    int idVaga = Integer.parseInt(idVagaStr);
                    Vaga vaga = Fachada.localizarVaga(idVaga);
                    if (vaga != null) {
                        Fachada.cancelarCandidatura(candidato, vaga);
                        JOptionPane.showMessageDialog(null, "Candidatura cancelada com sucesso para a vaga ID: " + idVaga);
                    } else {
                        JOptionPane.showMessageDialog(null, "Vaga não encontrada!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ID da vaga inválido!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Erro: O ID da vaga deve ser um número válido.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao cancelar candidatura: " + ex.getMessage());
            }
        });

        btnSair.addActionListener(e -> {
            dispose();
            new TelaLogin().setVisible(true);
        });
    }

    private void exibirVagasDisponiveis() {
        try {
            List<Vaga> vagas = Fachada.listarVagas();
            DefaultTableModel model = (DefaultTableModel) tabelaVagas.getModel();
            model.setRowCount(0); // Limpar tabela

            for (Vaga v : vagas) {
                model.addRow(new Object[]{v.getId(), v.getDescricao(), v.getSalario(), v.getArea()});
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar vagas: " + ex.getMessage());
        }
    }

    private String listarHabilidades(Candidato candidato) {
        List<String> habilidades = candidato.getHabilidades();
        if (habilidades == null || habilidades.isEmpty()) {
            return "Nenhuma habilidade cadastrada.";
        } else {
            return String.join("<br>", habilidades);
        }
    }
}
