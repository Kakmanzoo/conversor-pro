import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Conversor extends JFrame {

    private JTextField valorCop;
    private JComboBox<String> divisaPorSeleccionar;
    private JLabel textoResultado;
    private JPanel historialPanel;
    private JLabel banderaLabel;
    private JLabel graficoLabel;

    public Conversor() {
        setTitle("Conversor Pro");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializar etiquetas de imagen
        banderaLabel = new JLabel();
        graficoLabel = new JLabel();

        // Componentes
        valorCop = new JTextField(10);
        divisaPorSeleccionar = new JComboBox<>(new String[] { "USD", "EUR", "PEN", "JPY" });
        JButton botonConvertir = new JButton("Convertir");
        JButton botonLimpiarH = new JButton("Limpiar Historial");
        textoResultado = new JLabel("Resultado: ");
        historialPanel = new JPanel();
        historialPanel.setLayout(new BoxLayout(historialPanel, BoxLayout.Y_AXIS));
        historialPanel.setBackground(new Color(240, 248, 255));

        JScrollPane scrollHistorial = new JScrollPane(historialPanel);
        scrollHistorial.setPreferredSize(new Dimension(350, 200));

        // Panel de entrada y botones
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Valor en COP: "));
        panel.add(valorCop);
        panel.add(new JLabel("Convertir a: "));
        panel.add(divisaPorSeleccionar);
        panel.add(new JLabel(""));
        panel.add(botonConvertir);
        panel.add(new JLabel(""));
        panel.add(botonLimpiarH);
        panel.add(new JLabel(""));
        panel.add(textoResultado);

        // Panel para imágenes
        JPanel panelExtra = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        panelExtra.add(banderaLabel);
        panelExtra.add(graficoLabel);

        // Eventos
        botonConvertir.addActionListener(e -> convertirMoneda());
        botonLimpiarH.addActionListener(e -> {
            historialPanel.removeAll();
            historialPanel.revalidate();
            historialPanel.repaint();
        });
        divisaPorSeleccionar.addActionListener(e -> {
            String moneda = divisaPorSeleccionar.getSelectedItem().toString();
            actualizarImagenes(moneda);
        });

        // Layout general
        setLayout(new BorderLayout());
        add(panel, BorderLayout.NORTH);
        add(scrollHistorial, BorderLayout.CENTER);
        add(panelExtra, BorderLayout.SOUTH);

        // Mostrar ventana
        setVisible(true);
    }

    private void convertirMoneda() {
        try {
            double valor = Double.parseDouble(valorCop.getText());
            String moneda = divisaPorSeleccionar.getSelectedItem().toString();
            double resultadoFinal = switch (moneda) {
                case "USD" -> valor / 4179;
                case "EUR" -> valor / 4715;
                case "PEN" -> valor / 1138;
                case "JPY" -> valor / 29.01;
                default -> 0;
            };
            String resultadoTexto = String.format("%.2f", resultadoFinal) + " " + moneda;
            textoResultado.setText("Resultado:  " + resultadoTexto);

            // Fecha y hora
            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String fechahora = ahora.format(formato);

            String lineahistorial = valor + "COP → " + resultadoTexto + " [ " + fechahora + "]\n";

            // Tarjeta de historial
            JPanel tarjeta = new JPanel(new BorderLayout());
            tarjeta.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            tarjeta.setBackground(Color.WHITE);
            tarjeta.setMaximumSize(new Dimension(300, 80));
            tarjeta.setPreferredSize(new Dimension(300, 60));

            JLabel texto = new JLabel("<html><b>" + valor + "COP → " + resultadoTexto +
                    "</b><br><i>" + fechahora + "</i></html>");
            texto.setFont(new Font("SansSerif", Font.PLAIN, 13));
            tarjeta.add(texto, BorderLayout.CENTER);

            historialPanel.add(tarjeta);
            historialPanel.revalidate();
            historialPanel.repaint();

            guardarEnArchivo(lineahistorial);
            actualizarImagenes(moneda);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa un valor válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarImagenes(String moneda) {
        String rutaBandera = switch (moneda) {
            case "USD" -> "img/us.png";
            case "EUR" -> "img/ue.png";
            case "PEN" -> "img/pe.png";
            case "JPY" -> "img/jp.png";
            default -> "img/default.png";
        };

        String rutaGrafico = switch (moneda) {
            case "USD" -> "img/grafico_usd.png";
            case "EUR" -> "img/grafico_eur.png";
            case "PEN" -> "img/grafico_pen.png";
            case "JPY" -> "img/grafico_jpy.png";
            default -> "img/grafico_default.png";
        };

        banderaLabel.setIcon(cargarIcono(rutaBandera, 120, 100));
        graficoLabel.setIcon(cargarIcono(rutaGrafico, 150, 90));
    }

    private ImageIcon cargarIcono(String ruta, int ancho, int alto) {
        ImageIcon iconoOriginal = new ImageIcon(ruta);
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenEscalada);
    }

    private void guardarEnArchivo(String texto) {
        try (FileWriter writer = new FileWriter("Historial_conversiones.txt", true)) {
            writer.write(texto);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No se pudo guardar en el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Conversor::new);
    }
}

