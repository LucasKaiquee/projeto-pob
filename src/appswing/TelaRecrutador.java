package appswing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Candidato;
import modelo.Recrutador;
import modelo.Vaga;
import regras_negocio.Fachada;

public class TelaRecrutador extends JFrame {
    private JLabel lblBemVindo, lblVagaInfo;
    private JButton btnCriarVaga, btnEditarVaga, btnExcluirVaga, btnExcluirConta, btnSair, btnVerCandidatos;
    private JTable tabelaCandidatos;
    private DefaultTableModel modeloTabela;
    private Recrutador recrutador;

    public TelaRecrutador(Recrutador recrutador) {
        this.recrutador = recrutador;

        // Configurações da Janela
        setTitle("Área do Recrutador");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel Principal
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Label de boas-vindas
        lblBemVindo = new JLabel("Bem-vindo, Recrutador!");
        lblBemVindo.setBounds(20, 20, 300, 25);
        panel.add(lblBemVindo);

        // Informações da vaga
        lblVagaInfo = new JLabel();
        lblVagaInfo.setBounds(20, 50, 400, 80);
        panel.add(lblVagaInfo);

        // Botões
        btnCriarVaga = new JButton("Criar Vaga");
        btnCriarVaga.setBounds(20, 150, 150, 30);
        panel.add(btnCriarVaga);

        btnEditarVaga = new JButton("Editar Vaga");
        btnEditarVaga.setBounds(180, 150, 150, 30);
        panel.add(btnEditarVaga);

        btnExcluirVaga = new JButton("Excluir Vaga");
        btnExcluirVaga.setBounds(340, 150, 150, 30);
        panel.add(btnExcluirVaga);

        btnExcluirConta = new JButton("Excluir Conta");
        btnExcluirConta.setBounds(500, 150, 150, 30);
        panel.add(btnExcluirConta);

        btnVerCandidatos = new JButton("Ver Candidatos");
        btnVerCandidatos.setBounds(20, 200, 150, 30);
        panel.add(btnVerCandidatos);

        btnSair = new JButton("Sair");
        btnSair.setBounds(180, 200, 150, 30);
        panel.add(btnSair);


        // Tabela de Candidatos
        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("Email");
        modeloTabela.addColumn("Área");
        modeloTabela.addColumn("Habilidades");

        tabelaCandidatos = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaCandidatos);
        scrollPane.setBounds(20, 340, 750, 200);
        panel.add(scrollPane);

        // Adiciona o painel ao JFrame
        add(panel);

        // Configura eventos
        configurarEventos();

        // Exibe informações da vaga (se houver)
        exibirInformacoesVaga();
    }

    private void configurarEventos() {

        btnCriarVaga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verifica se o recrutador já tem uma vaga ativa
                if (recrutador.getVagaGerenciada() != null) {
                    JOptionPane.showMessageDialog(null, "Você já tem uma vaga ativa.");
                    return;
                }

                String descricao = JOptionPane.showInputDialog("Descrição da vaga:");
                String area = JOptionPane.showInputDialog("Área da vaga:");
                String salarioStr = JOptionPane.showInputDialog("Salário da vaga:");
                String requisitos = JOptionPane.showInputDialog("Requisitos da vaga (separados por vírgula):");

                try {
                    double salario = Double.parseDouble(salarioStr);

                    String[] palavras = requisitos.split(",");
                    ArrayList<String> requisitosVaga = new ArrayList<>();
                    for (String palavra : palavras) {
                        requisitosVaga.add(palavra.trim());
                    }
                
                    Fachada.criarVaga(descricao, salario, area, requisitosVaga, recrutador);
                    JOptionPane.showMessageDialog(null, "Vaga criada com sucesso!");
                    exibirInformacoesVaga(); // Atualiza as informações da vaga
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao criar vaga: " + ex.getMessage());
                }
            }
        });

        // Botão Ver Candidatos
        btnVerCandidatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vaga vaga = recrutador.getVagaGerenciada();
                if (vaga != null) {
                    // Obtém a lista de candidatos da vaga
                    List<Candidato> candidatos = vaga.getCandidaturas();

                    // Limpa a tabela antes de adicionar novos dados
                    modeloTabela.setRowCount(0);

                    if (candidatos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Não há candidatos para esta vaga.");
                    } else {
                        // Preenche a tabela com informações dos candidatos
                        for (Candidato candidato : candidatos) {
                            modeloTabela.addRow(new Object[] {
                                candidato.getNome(),
                                candidato.getEmail(),
                                candidato.getArea(),
                                String.join(", ", candidato.getHabilidades())
                            });
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Você não tem uma vaga ativa.");
                }
            }
        });

        btnEditarVaga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vaga vaga = recrutador.getVagaGerenciada();
                    if (vaga == null) {
                        JOptionPane.showMessageDialog(null, "Você não tem uma vaga ativa para editar.");
                        return;
                    }

                    String novaDescricao = JOptionPane.showInputDialog("Nova descrição (ou deixe em branco):");
                    String novaArea = JOptionPane.showInputDialog("Nova área (ou deixe em branco):");
                    String novoSalarioStr = JOptionPane.showInputDialog("Novo salário (ou deixe em branco):");
                    String novosRequisitos = JOptionPane.showInputDialog("Novos requisitos (ou deixe em branco):");

                    Double novoSalario = novoSalarioStr.isEmpty() ? null : Double.parseDouble(novoSalarioStr);
                    List<String> listaRequisitos = novosRequisitos.isEmpty() ? null : List.of(novosRequisitos.split(","));

                    Fachada.alterarVaga(vaga.getId(), novaDescricao, novoSalario, novaArea, listaRequisitos);
                    JOptionPane.showMessageDialog(null, "Vaga editada com sucesso!");
                    exibirInformacoesVaga(); // Atualiza as informações da vaga
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao editar vaga: " + ex.getMessage());
                }
            }
        });

        btnExcluirVaga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vaga vaga = recrutador.getVagaGerenciada();
                if (vaga != null) {
                    try {
                        Fachada.removerVaga(vaga.getId());
                        JOptionPane.showMessageDialog(null, "Vaga excluída com sucesso!");
                        exibirInformacoesVaga(); // Atualiza as informações da vaga
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao excluir vaga: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Você não tem uma vaga ativa para excluir.");
                }
            }
        });

        btnExcluirConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir sua conta?");
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        Fachada.removerRecrutador(recrutador.getCpf());
                        JOptionPane.showMessageDialog(null, "Conta excluída com sucesso!");
                        dispose();
                        new TelaLogin();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao excluir conta: " + ex.getMessage());
                    }
                }
            }
        });

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TelaLogin().setVisible(true);
            }
        });
    }

    private void exibirInformacoesVaga() {
        Vaga vaga = recrutador.getVagaGerenciada();
        if (vaga != null) {
            lblVagaInfo.setText("<html><b>Vaga Ativa:</b><br>" +
                    "Descrição: " + vaga.getDescricao() + "<br>" +
                    "Área: " + vaga.getArea() + "<br>" +
                    "Salário: " + vaga.getSalario() + "<br>" +
                    "Requisitos: " + String.join(", ", vaga.getRequisitos()) + "</html>");
        } else {
            lblVagaInfo.setText("Você não tem uma vaga ativa.");
        }
    }
}

