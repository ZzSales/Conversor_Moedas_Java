import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

/**
 * Classe principal para o Conversor de Moedas.
 */
public class ConversorDeMoedas {
    public static void main(String[] args) {
        // Criação do frame
        JFrame frame = new JFrame("Conversor de Moedas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 450);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS)); // Usando BoxLayout para organizar os componentes verticalmente

        // Tabela de taxas de câmbio
        Map<String, Double> taxas = new HashMap<>();
        taxas.put("USD para BRL", 5.15);
        taxas.put("EUR para BRL", 5.60);
        taxas.put("BRL para USD", 0.19);
        taxas.put("BRL para EUR", 0.18);

        // Componentes da interface gráfica
        JLabel labelMoeda = new JLabel("Selecione a conversão:");
        labelMoeda.setAlignmentX(Component.CENTER_ALIGNMENT); 
        JComboBox<String> comboMoeda = new JComboBox<>(taxas.keySet().toArray(new String[0]));
        comboMoeda.setMaximumSize(new Dimension(200, 30)); 
        comboMoeda.setAlignmentX(Component.CENTER_ALIGNMENT); 
        JLabel labelEntrada = new JLabel("Digite o valor:");
        labelEntrada.setAlignmentX(Component.CENTER_ALIGNMENT); 
        JTextField campoEntrada = new JTextField(10);
        campoEntrada.setMaximumSize(new Dimension(150, 25)); 
        campoEntrada.setAlignmentX(Component.CENTER_ALIGNMENT); 
        JButton botaoConverter = new JButton("Converter");
        botaoConverter.setPreferredSize(new Dimension(100, 70));
        botaoConverter.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel labelResultado = new JLabel("Resultado: ");
        labelResultado.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Personalização dos componentes
        botaoConverter.setBackground(new Color(34, 139, 34));
        botaoConverter.setForeground(new Color(240, 248, 255));
        frame.getContentPane().setBackground(new Color(128, 128, 128));
        labelEntrada.setForeground(new Color(240, 248, 255));
        labelMoeda.setForeground(new Color(240, 248, 255));
        labelResultado.setForeground(new Color(240, 248, 255));

        // Adicionando componentes ao frame
        frame.add(labelMoeda);
        frame.add(Box.createRigidArea(new Dimension(0, 10)));
        frame.add(comboMoeda);
        frame.add(Box.createRigidArea(new Dimension(0, 40))); 
        frame.add(labelEntrada);
        frame.add(Box.createRigidArea(new Dimension(0, 40)));
        frame.add(campoEntrada);
        frame.add(Box.createRigidArea(new Dimension(0, 20))); 
        frame.add(botaoConverter);
        frame.add(Box.createRigidArea(new Dimension(0, 20)));
        frame.add(labelResultado);

        // Método para realizar a conversão
        ActionListener converterAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double valor = Double.parseDouble(campoEntrada.getText());
                    String selecao = (String) comboMoeda.getSelectedItem();
                    double resultado = converterMoeda(valor, selecao, taxas);
                    labelResultado.setText(String.format("Resultado: %.2f", resultado));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, insira um valor numérico válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        // Ação do botão
        botaoConverter.addActionListener(converterAction);

        // Ação ao pressionar Enter no campo de texto
        campoEntrada.addActionListener(converterAction);

        // Tornando o frame visível
        frame.setVisible(true);
    }

    /**
     * Método para converter o valor da moeda.
     *
     * @param valor   O valor a ser convertido.
     * @param selecao A seleção da conversão (ex: "USD para BRL").
     * @param taxas   O mapa contendo as taxas de câmbio.
     * @return O valor convertido.
     */
    private static double converterMoeda(double valor, String selecao, Map<String, Double> taxas) {
        return valor * taxas.get(selecao);
    }
}