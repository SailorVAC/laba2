// Импортируются классы, используемые в приложении
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
// Главный класс приложения, он же класс фрейма
public class MainFrame extends JFrame {
    // Размеры окна приложения в виде констант
    private static final int WIDTH = 550;
    private static final int HEIGHT = 320;

    // Текстовые поля для считывания значений переменных
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;

    private JLabel formulaLabel1;
    private JLabel formulaLabel2 ;

    // Текстовое поле для отображения результата
    private JTextField textFieldResult;

    // Группа радио-кнопок для обеспечения уникальности выделения в группе
    private ButtonGroup radioButtons = new ButtonGroup();

    // Контейнер для отображения радио-кнопок
    private Box hboxFormulaType = Box.createHorizontalBox();

    // Выбранная формула
    private int formulaId = 1;

    public static double formula1(double x, double y, double z) {
            double part1 = Math.sin(y) + Math.pow(y, 2) + Math.exp(Math.cos(y));
            double part2 = Math.pow(part1 * (Math.log(z) + Math.sin(Math.PI * Math.pow(x, 2))), 0.25);
            return part2;
    }

    public static double formula2(double x, double y, double z) {
        double numerator = Math.pow(y + Math.pow(x, 3), 1 / z);
        double denominator = Math.log(z);
        return numerator / denominator;
    }

    // Вспомогательный метод для добавления кнопок на панель
    private void addRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.formulaId = formulaId;
                image();
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }

    private static int getResult(JRadioButton rb1, JRadioButton rb2, JRadioButton rb3) {
        if (rb1.isSelected()) {
            return 1;
        } else if (rb2.isSelected()) {
            return 2;
        } else if (rb3.isSelected()) {
            return 3;
        }
        return 0; // Если ни одна кнопка не выбрана
    }
    private void image() {
        if (formulaId == 1) {
            formulaLabel1.setVisible(true);
            formulaLabel2.setVisible(false);
        } else {
            formulaLabel1.setVisible(false);
            formulaLabel2.setVisible(true);
        }

    }




    // Конструктор класса
    public MainFrame() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();

        // Отцентрировать окно приложения на экране
        setLocation((kit.getScreenSize().width - WIDTH) / 2,
                (kit.getScreenSize().height - HEIGHT) / 2);


        ImageIcon formula1Image = new ImageIcon("фун 1.png");
        ImageIcon formula2Image = new ImageIcon("фун 2.png");
        formulaLabel1 = new JLabel(formula1Image);
        formulaLabel2 = new JLabel(formula2Image);
        formulaLabel2.setVisible(false);
        Box imagebox= Box.createHorizontalBox();
        imagebox.add(formulaLabel1);
        imagebox.add(formulaLabel2);
        imagebox.add(Box.createHorizontalStrut(40));

        hboxFormulaType.add(Box.createHorizontalGlue());

        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(
                radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());


        // Создать область с полями ввода для X и Y
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());


        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());

        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10); // Исправлено на JTextField
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());


;
        JRadioButton radioButton1 = new JRadioButton();
        JRadioButton radioButton2 = new JRadioButton();
        JRadioButton radioButton3 = new JRadioButton();

        // Группировка радиокнопок
        ButtonGroup group = new ButtonGroup();
        group.add(radioButton1);
        group.add(radioButton2);
        group.add(radioButton3);

        // Создаем вертикальные боксы для каждого поля и радиокнопки
        Box vboxX = Box.createVerticalBox();
        vboxX.add(labelForX);
        vboxX.add(textFieldX);
        vboxX.add(radioButton1);

        Box vboxY = Box.createVerticalBox();
        vboxY.add(labelForY);
        vboxY.add(textFieldY);
        vboxY.add(radioButton2);

        Box vboxZ = Box.createVerticalBox();
        vboxZ.add(labelForZ);
        vboxZ.add(textFieldZ);
        vboxZ.add(radioButton3);

        // Горизонтальный бокс, чтобы расположить все три вертикальных бокса в строку
        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.add(vboxX);
        hboxVariables.add(Box.createHorizontalStrut(10)); // Отступ между блоками
        hboxVariables.add(vboxY);
        hboxVariables.add(Box.createHorizontalStrut(10)); // Отступ между блоками
        hboxVariables.add(vboxZ);


        // Создать область для вывода результата
        JLabel labelForResult = new JLabel("Результат:");
        textFieldResult = new JTextField("0", 10);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());

        // Создать область для кнопок
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    String result;
                    if (formulaId == 1)
                        result = String.format("%.3f",formula1(x,y,z));
                    else
                        result = String.format("%.3f",formula2(x,y,z));
                    textFieldResult.setText(result);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой",
                            "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });

        JButton MC = new JButton("MC");
        MC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if(MainFrame.getResult(radioButton1,radioButton2,radioButton3)==1)
                    textFieldX.setText("0");
                if(MainFrame.getResult(radioButton1,radioButton2,radioButton3)==2)
                    textFieldY.setText("0");
                if(MainFrame.getResult(radioButton1,radioButton2,radioButton3)==3)
                    textFieldZ.setText("0");
            }
        });
        JButton M_plus = new JButton("M_plus");
        M_plus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if(MainFrame.getResult(radioButton1,radioButton2,radioButton3)==1) {
                    Double x = Double.parseDouble(textFieldX.getText());
                    x=x+x;
                    textFieldX.setText(x.toString());
                }
                if(MainFrame.getResult(radioButton1,radioButton2,radioButton3)==2) {
                    Double y = Double.parseDouble(textFieldY.getText());
                    y = y + y;
                    textFieldY.setText(y.toString());
                }
                if(MainFrame.getResult(radioButton1,radioButton2,radioButton3)==3) {
                    Double z = Double.parseDouble(textFieldZ.getText());
                    z = z + z;
                    textFieldZ.setText(z.toString());
                }
            }
        });


        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(10));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalStrut(10));
        hboxButtons.add(MC);
        hboxButtons.add(Box.createHorizontalStrut(10));
        hboxButtons.add(M_plus);
        hboxButtons.add(Box.createHorizontalGlue());

        // Связать области воедино в компоновке BoxLayout
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(imagebox);
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
