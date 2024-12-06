package appswing;

import javax.swing.*;

import modelo.Candidato;
import modelo.Vaga;
import regras_negocio.Fachada;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ConsultaVagasECandidatos extends JFrame {

    private JTextField areaField;
    private JTextField minCandidaturasField;
    private JTextArea resultArea;
    private JButton consultarButton;
    private JComboBox<String> consultaOptions;

    public ConsultaVagasECandidatos() {
        // Configurações básicas da janela
        setTitle("Consulta Vagas e Candidatos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new BorderLayout());

        // Painel de entrada de dados
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        // Adicionando componentes de entrada
        inputPanel.add(new JLabel("Área:"));
        areaField = new JTextField();
        inputPanel.add(areaField);

        inputPanel.add(new JLabel("Mínimo de Candidaturas:"));
        minCandidaturasField = new JTextField();
        inputPanel.add(minCandidaturasField);

        inputPanel.add(new JLabel("Tipo de Consulta:"));
        consultaOptions = new JComboBox<>(new String[] {"Por Área - Candidatos", "Por Área - Vagas", "Vagas com Mínimo de Candidaturas"});
        inputPanel.add(consultaOptions);

        // Botão para realizar a consulta
        consultarButton = new JButton("Consultar");
        inputPanel.add(consultarButton);

        // Área de texto para exibir os resultados
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Adicionando os painéis à janela
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Ação do botão
        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Limpar área de resultados
                resultArea.setText("");
                try {
                    String selectedOption = (String) consultaOptions.getSelectedItem();
                    String area = areaField.getText().trim();
                    String minCandidaturasStr = minCandidaturasField.getText().trim();
                    
                    // Executando a consulta baseada na opção selecionada
                    if (selectedOption != null) {
                        if (selectedOption.equals("Por Área - Candidatos") && !area.isEmpty()) {
                            List<Candidato> candidatos = Fachada.listarCandidatosPorArea(area);
                            for (Candidato candidato : candidatos) {
                                resultArea.append(candidato.toString() + "\n");
                            }
                        } else if (selectedOption.equals("Por Área - Vagas") && !area.isEmpty()) {
                            List<Vaga> vagas = Fachada.localizarVagasPorArea(area);
                            for (Vaga vaga : vagas) {
                                resultArea.append(vaga.toString() + "\n");
                            }
                        } else if (selectedOption.equals("Vagas com Mínimo de Candidaturas") && !minCandidaturasStr.isEmpty()) {
                            int minCandidaturas = Integer.parseInt(minCandidaturasStr);
                            List<Vaga> vagas = Fachada.localizarVagaComMinimoCandidaturas(minCandidaturas);
                            for (Vaga vaga : vagas) {
                                resultArea.append(vaga.toString() + "\n");
                            }
                        } else {
                            resultArea.append("Por favor, preencha os campos corretamente.\n");
                        }
                    }
                } catch (Exception ex) {
                    resultArea.append("Erro: " + ex.getMessage() + "\n");
                }
            }
        });
    }

    public static void main(String[] args) {
        // Exibir a janela
        SwingUtilities.invokeLater(() -> {
            Fachada.inicializar();
            new ConsultaVagasECandidatos().setVisible(true);
        });
    }
}
