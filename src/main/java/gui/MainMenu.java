package gui;

import entities.Characteristics;
import entities.Plants;
import entities.Plants_Characteristics;
import services.CharacteristicsService;
import services.PlantsService;
import services.Plants_CharacteristicsService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainMenu implements ActionListener {

    private Plants_CharacteristicsService plantsCharacteristicsService = new Plants_CharacteristicsService();
    private PlantsService plantsService = new PlantsService();
    private CharacteristicsService characteristicsService = new CharacteristicsService();

    private JFrame mainFrame = new JFrame("Ion Luca PAI 2020");
    private JFrame openedFrame = new JFrame("Database Actions");
    private String whatToDo;

    private JButton ViewPlantsBtn = new JButton("View Plants");
    private JButton ModifyPlantsBtn = new JButton("Modify Plants");
    private JButton AddButton = new JButton("Add Plant");
    private JButton RemoveEntryBtn = new JButton("Remove Entry");
    private JButton submitButton = new JButton();

    private Map<String, JTextField> plantsData = new HashMap<>();
    private ArrayList<JCheckBox> sameAsFirstCheck_p = new ArrayList<>();
    private ArrayList<JCheckBox> sameAsFirstCheck_c = new ArrayList<>();

    private ArrayList<Plants> plantsToInsert = new ArrayList<>();
    private ArrayList<Characteristics> characteristicsToInsert = new ArrayList<>();

    private JRadioButton showPlantRB = new JRadioButton("Search Plant by name");
    private JTextField searchPlantByName = new JTextField();
    private JRadioButton showCharacteristicRB = new JRadioButton("Show characteristic by name");
    private JTextField searchCharacteristicByName = new JTextField();
    private JRadioButton showAllPlantsRB = new JRadioButton("Show all plants");
    private JRadioButton showAllCharacteristicsRB = new JRadioButton("Show all characteristics");

    private JRadioButton removePlantByNameRB = new JRadioButton("Remove Plant by name");
    private JTextField removePlant = new JTextField();
    private JRadioButton removeCharacteristicByNameRB = new JRadioButton("Remove Characteristic by name");
    private JTextField removeCharacteristic = new JTextField();

    private JRadioButton chooseModifyPlant = new JRadioButton("Modify Plants");
    private JRadioButton chooseModifyCharacteristic = new JRadioButton("Modify Characteristics");

    private ArrayList<JTextField> plantToModify = new ArrayList<>();
    private ArrayList<JTextField> characteristicToModify = new ArrayList<>();

    private static JTable viewTable = new JTable();
    Font defaultLabelFont = new Font("Arial", Font.PLAIN, 14);

    public MainMenu() {
        initFrame();
    }

    private void CenterJFrameOnScreen(JFrame frame) {
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //int height = screenSize.height;
        //int width = screenSize.width;
        //frame.setSize(width/2, height/2);
        frame.setLocationRelativeTo(null);
    }

    private void initFrame() {
        //With the next 2 line the Gui will look windows styled like gui
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //JFrame.setDefaultLookAndFeelDecorated(true);

        mainFrame.setSize(400, 300);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CenterJFrameOnScreen(mainFrame);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1));

        mainPanel.add(AddButton);
        mainPanel.add(ViewPlantsBtn);
        mainPanel.add(ModifyPlantsBtn);
        mainPanel.add(RemoveEntryBtn);

        mainFrame.add(BorderLayout.CENTER, mainPanel);

        addActionEvent();
        mainFrame.setVisible(true);
    }

    private void initSecondFrame() {
        mainFrame.setVisible(false);

        openedFrame = new JFrame("Database Actions");
        openedFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        openedFrame.setSize(800, 600);
        openedFrame.setBounds(300, 90, 900, 600);
        openedFrame.setResizable(false);
        openedFrame.setVisible(true);

        openedFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                mainFrame.setVisible(true);
            }
        });

        CenterJFrameOnScreen(openedFrame);

    }

    private void initAddEntryFrame() {
        JLabel titleLabel = new JLabel("Insert into Database", SwingConstants.CENTER);
        titleLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 25));


        JPanel plantsPanel = new JPanel();
        plantsPanel.setLayout(new GridBagLayout());
        plantsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel characteristicsPanel = new JPanel();
        characteristicsPanel.setLayout(new GridBagLayout());
        characteristicsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc_c = new GridBagConstraints();
        gbc_c.fill = GridBagConstraints.HORIZONTAL;
        int row_c = 0;

        GridBagConstraints gbc_p = new GridBagConstraints();
        gbc_p.fill = GridBagConstraints.HORIZONTAL;
        int row_p = 0;

        for (int i = 0; i < 3; i++) {
            JCheckBox sameAsAbove_p = new JCheckBox("Same as first plant");
            JCheckBox sameAsAbove_c = new JCheckBox("Same as first characteristic");

            JLabel plantNameLabel = new JLabel("Plant name " + (i + 1));
            plantNameLabel.setFont(defaultLabelFont);
            JLabel scientificNameLabel = new JLabel("Scientific name " + (i + 1));
            scientificNameLabel.setFont(defaultLabelFont);
            JLabel specialCharacteristicLabel = new JLabel("Characteristic " + (i + 1));
            scientificNameLabel.setFont(defaultLabelFont);
            JLabel colorLabel = new JLabel("Color " + (i + 1));
            scientificNameLabel.setFont(defaultLabelFont);
            JLabel heightLabel = new JLabel("Height " + (i + 1));
            scientificNameLabel.setFont(defaultLabelFont);
            JLabel seasonLabel = new JLabel("Harvesting season " + (i + 1));
            scientificNameLabel.setFont(defaultLabelFont);

            JTextField plantName = new JTextField();
            JTextField plantScientificName = new JTextField();
            plantsData.put("name " + (i + 1), plantName);
            plantsData.put("scientific name " + (i + 1), plantScientificName);

            JTextField plantCharacteristic = new JTextField();
            JTextField plantColor = new JTextField();
            JTextField plantHeight = new JTextField();
            JTextField plantSeason = new JTextField();
            plantsData.put("characteristic " + (i + 1), plantCharacteristic);
            plantsData.put("color " + (i + 1), plantColor);
            plantsData.put("height " + (i + 1), plantHeight);
            plantsData.put("season " + (i + 1), plantSeason);

            gbc_c.ipadx = 100;
            gbc_c.gridwidth = 2;
            gbc_p.ipadx = 100;
            gbc_p.gridwidth = 2;

            gbc_c.gridx = 0;
            gbc_c.gridy = row_c;
            characteristicsPanel.add(specialCharacteristicLabel, gbc_c);

            gbc_c.gridx = 2;
            gbc_c.gridy = row_c;
            characteristicsPanel.add(plantCharacteristic, gbc_c);

            gbc_c.gridx = 0;
            gbc_c.gridy = row_c + 1;
            characteristicsPanel.add(colorLabel, gbc_c);

            gbc_c.gridx = 2;
            gbc_c.gridy = row_c + 1;
            characteristicsPanel.add(plantColor, gbc_c);

            gbc_c.gridx = 0;
            gbc_c.gridy = row_c + 2;
            characteristicsPanel.add(heightLabel, gbc_c);

            gbc_c.gridx = 2;
            gbc_c.gridy = row_c + 2;
            characteristicsPanel.add(plantHeight, gbc_c);

            gbc_c.gridx = 0;
            gbc_c.gridy = row_c + 3;
            characteristicsPanel.add(seasonLabel, gbc_c);

            gbc_c.gridx = 2;
            gbc_c.gridy = row_c + 3;
            characteristicsPanel.add(plantSeason, gbc_c);

            if (i > 0) {
                gbc_c.gridx = 1;
                gbc_c.gridy = row_c + 4;
                sameAsFirstCheck_c.add(sameAsAbove_c);
                characteristicsPanel.add(sameAsAbove_c, gbc_c);
            } else {
                JLabel sep = new JLabel("-----------------");
                gbc_c.gridx = 1;
                gbc_c.gridy = row_c + 4;
                characteristicsPanel.add(sep, gbc_c);
            }

            gbc_p.gridx = 0;
            gbc_p.gridy = row_p;
            plantsPanel.add(plantNameLabel, gbc_p);

            gbc_p.gridx = 2;
            gbc_p.gridy = row_p;
            plantsPanel.add(plantName, gbc_p);

            gbc_p.gridx = 0;
            gbc_p.gridy = row_p + 1;
            plantsPanel.add(scientificNameLabel, gbc_p);

            gbc_p.gridx = 2;
            gbc_p.gridy = row_p + 1;
            plantsPanel.add(plantScientificName, gbc_p);

            if (i > 0) {
                gbc_p.gridx = 0;
                gbc_p.gridy = row_p + 2;
                sameAsFirstCheck_p.add(sameAsAbove_p);
                plantsPanel.add(sameAsAbove_p, gbc_p);
            } else {
                JLabel sep = new JLabel("-----------------");
                gbc_p.gridx = 0;
                gbc_p.gridy = row_p + 2;
                plantsPanel.add(sep, gbc_p);
            }

            row_c += 5;
            row_p += 3;
        }

        JPanel submitBtnContainer = new JPanel();
        submitBtnContainer.setLayout(new BorderLayout());
        submitBtnContainer.setBorder(new EmptyBorder(10, 250, 50, 250));
        submitButton.setText("Insert");
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitBtnContainer.add(submitButton);

        openedFrame.add(BorderLayout.NORTH, titleLabel);
        openedFrame.add(BorderLayout.WEST, plantsPanel);
        openedFrame.add(BorderLayout.EAST, characteristicsPanel);
        openedFrame.add(BorderLayout.SOUTH, submitBtnContainer);

    }

    private void initViewEntriesFrame() {
        JLabel titleLabel = new JLabel("Search in Database", SwingConstants.CENTER);
        titleLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 25));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        ButtonGroup buttonGroup = new ButtonGroup();

        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(showPlantRB, gbc);

        gbc.ipadx = 150;
        gbc.gridwidth = 2;
        gbc.gridx = 2;
        gbc.gridy = 0;
        contentPanel.add(searchPlantByName, gbc);

        gbc.ipadx = 0;
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPanel.add(showAllPlantsRB, gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        contentPanel.add(showCharacteristicRB, gbc);

        gbc.ipadx = 150;
        gbc.gridx = 6;
        gbc.gridy = 0;
        contentPanel.add(searchCharacteristicByName, gbc);

        gbc.ipadx = 0;
        gbc.gridx = 4;
        gbc.gridy = 1;
        contentPanel.add(showAllCharacteristicsRB, gbc);

        JPanel BtnContainer = new JPanel();
        BtnContainer.setLayout(new BorderLayout());
        BtnContainer.setBorder(new EmptyBorder(10, 250, 50, 250));
        submitButton.setText("Search");
        BtnContainer.add(submitButton);

        buttonGroup.add(showPlantRB);
        buttonGroup.add(showCharacteristicRB);
        buttonGroup.add(showAllPlantsRB);
        buttonGroup.add(showAllCharacteristicsRB);

        openedFrame.add(BorderLayout.NORTH, titleLabel);
        openedFrame.add(BorderLayout.CENTER, contentPanel);
        openedFrame.add(BorderLayout.SOUTH, BtnContainer);
    }

    private void initModifyEntryFrame() {
        JLabel titleLabel = new JLabel("Modify entry from Database", SwingConstants.CENTER);
        titleLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 25));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;

        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(chooseModifyPlant, gbc);
        gbc.gridx = 6;
        contentPanel.add(chooseModifyCharacteristic, gbc);
        gbc.ipadx = 100;

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(chooseModifyCharacteristic);
        buttonGroup.add(chooseModifyPlant);

        JLabel lp1 = new JLabel("Plant name");
        JLabel lp2 = new JLabel("Scientific name");
        JTextField name = new JTextField();
        JTextField scientificName = new JTextField();
        plantToModify.add(name);
        plantToModify.add(scientificName);

        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPanel.add(lp1, gbc);
        gbc.gridx = 2;
        contentPanel.add(name, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(lp2, gbc);
        gbc.gridx = 2;
        contentPanel.add(scientificName, gbc);

        JLabel lc1 = new JLabel("Characteristic");
        JLabel lc2 = new JLabel("Color");
        JLabel lc3 = new JLabel("Height");
        JLabel lc4 = new JLabel("Season");
        JTextField characteristic = new JTextField();
        JTextField color = new JTextField();
        JTextField height = new JTextField();
        JTextField season = new JTextField();
        characteristicToModify.add(characteristic);
        characteristicToModify.add(color);
        characteristicToModify.add(height);
        characteristicToModify.add(season);

        gbc.gridx = 4;
        gbc.gridy = 1;
        contentPanel.add(lc1, gbc);
        gbc.gridx = 6;
        contentPanel.add(characteristic, gbc);
        gbc.gridx = 4;
        gbc.gridy = 2;
        contentPanel.add(lc2, gbc);
        gbc.gridx = 6;
        contentPanel.add(color, gbc);
        gbc.gridx = 4;
        gbc.gridy = 3;
        contentPanel.add(lc3, gbc);
        gbc.gridx = 6;
        contentPanel.add(height, gbc);
        gbc.gridx = 4;
        gbc.gridy = 4;
        contentPanel.add(lc4, gbc);
        gbc.gridx = 6;
        contentPanel.add(season, gbc);

        JPanel BtnContainer = new JPanel();
        BtnContainer.setLayout(new BorderLayout());
        BtnContainer.setBorder(new EmptyBorder(10, 250, 50, 250));
        submitButton.setText("Modify");
        BtnContainer.add(submitButton);

        addDocumentListenerForModifyTextFields();

        openedFrame.add(BorderLayout.NORTH, titleLabel);
        openedFrame.add(BorderLayout.CENTER, contentPanel);
        openedFrame.add(BorderLayout.SOUTH, BtnContainer);
    }

    private void initRemoveEntryFrame() {
        JLabel titleLabel = new JLabel("Remove from Database", SwingConstants.CENTER);
        titleLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 25));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;

        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(removePlantByNameRB, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.ipadx = 150;
        contentPanel.add(removePlant, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 0;
        contentPanel.add(removeCharacteristicByNameRB, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.ipadx = 150;
        contentPanel.add(removeCharacteristic, gbc);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(removePlantByNameRB);
        buttonGroup.add(removeCharacteristicByNameRB);

        JPanel BtnContainer = new JPanel();
        BtnContainer.setLayout(new BorderLayout());
        BtnContainer.setBorder(new EmptyBorder(10, 250, 50, 250));
        submitButton.setText("Remove");
        BtnContainer.add(submitButton);

        openedFrame.add(BorderLayout.NORTH, titleLabel);
        openedFrame.add(BorderLayout.CENTER, contentPanel);
        openedFrame.add(BorderLayout.SOUTH, BtnContainer);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == AddButton) {
            initSecondFrame();
            this.whatToDo = "add plant";
            initAddEntryFrame();
            addCheckBoxFunctionality();
        }
        if (e.getSource() == ViewPlantsBtn) {
            initSecondFrame();
            this.whatToDo = "view database";
            initViewEntriesFrame();
        }
        if (e.getSource() == RemoveEntryBtn) {
            initSecondFrame();
            this.whatToDo = "remove entry";
            initRemoveEntryFrame();
        }
        if (e.getSource() == ModifyPlantsBtn) {
            initSecondFrame();
            this.whatToDo = "modify entry";
            initModifyEntryFrame();
        }

        if (e.getSource() == submitButton) {
            switch (this.whatToDo) {
                case "add plant":
                    extractDataToInsert();
                    try {
                        addPlantsToDatabase();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(openedFrame, "Success");
                    break;
                case "view database":
                    try {
                        showTableData();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "remove entry":
                    try {
                        removePlants();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(openedFrame, "Success");
                    break;
                case "modify entry":
                    try {
                        modifyPlant();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(openedFrame, "Success");
                    break;
            }
        }

    }

    private void addCheckBoxFunctionality() {
        for (int i = 0; i < sameAsFirstCheck_p.size(); i++) {
            int finalI = i;
            sameAsFirstCheck_p.get(i).addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    plantsData.get("name " + (finalI + 2)).setText(plantsData.get("name 1").getText());
                    plantsData.get("scientific name " + (finalI + 2)).setText(plantsData.get("scientific name 1").getText());
                } else {
                    plantsData.get("name " + (finalI + 2)).setText("");
                    plantsData.get("scientific name " + (finalI + 2)).setText("");
                }
            });

            sameAsFirstCheck_c.get(i).addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    plantsData.get("characteristic " + (finalI + 2)).setText(plantsData.get("characteristic 1").getText());
                    plantsData.get("color " + (finalI + 2)).setText(plantsData.get("color 1").getText());
                    plantsData.get("height " + (finalI + 2)).setText(plantsData.get("height 1").getText());
                    plantsData.get("season " + (finalI + 2)).setText(plantsData.get("season 1").getText());
                } else {
                    plantsData.get("characteristic " + (finalI + 2)).setText("");
                    plantsData.get("color " + (finalI + 2)).setText("");
                    plantsData.get("height " + (finalI + 2)).setText("");
                    plantsData.get("season " + (finalI + 2)).setText("");
                }
            });
        }
    }

    private void addActionEvent() {
        ViewPlantsBtn.addActionListener(this);
        ModifyPlantsBtn.addActionListener(this);
        AddButton.addActionListener(this);
        RemoveEntryBtn.addActionListener(this);
        submitButton.addActionListener(this);
    }

    private void extractDataToInsert() {
        plantsToInsert.add(new Plants(plantsData.get("name 1").getText(), plantsData.get("scientific name 1").getText()));
        plantsToInsert.add(new Plants(plantsData.get("name 2").getText(), plantsData.get("scientific name 2").getText()));
        plantsToInsert.add(new Plants(plantsData.get("name 3").getText(), plantsData.get("scientific name 3").getText()));

        characteristicsToInsert.add(new Characteristics(plantsData.get("characteristic 1").getText(),
                plantsData.get("color 1").getText(),
                Float.parseFloat(plantsData.get("height 1").getText()),
                plantsData.get("season 1").getText()));
        characteristicsToInsert.add(new Characteristics(plantsData.get("characteristic 2").getText(),
                plantsData.get("color 2").getText(),
                Float.parseFloat(plantsData.get("height 2").getText()),
                plantsData.get("season 2").getText()));
        characteristicsToInsert.add(new Characteristics(plantsData.get("characteristic 3").getText(),
                plantsData.get("color 3").getText(),
                Float.parseFloat(plantsData.get("height 3").getText()),
                plantsData.get("season 3").getText()));
    }

    public Integer getNumberOfEmptyPlantsInArray(ArrayList<Plants> plants) {
        int number_of_empty_plants = 0;
        for (Plants plant : plants) {
            if (plant.plantIsEmpty())
                number_of_empty_plants++;
        }

        return number_of_empty_plants;
    }

    public Integer getNumberOfEmptyCharacteristicsInArray(ArrayList<Characteristics> characteristics) {
        int number_of_empty_characteristics = 0;
        for (Characteristics characteristic : characteristics) {
            if (characteristic.characteristicIsEmpty())
                number_of_empty_characteristics++;
        }

        return number_of_empty_characteristics;
    }

    private void addPlantsToDatabase() throws SQLException {
        //TODO: Incomplete. Finish this function.
        int nrOfEmptyPlants = getNumberOfEmptyPlantsInArray(plantsToInsert);
        int nrOfEmptyCharacteristics = getNumberOfEmptyCharacteristicsInArray(characteristicsToInsert);

        if (nrOfEmptyPlants != nrOfEmptyCharacteristics)
            JOptionPane.showMessageDialog(openedFrame, "Nr. of plants introduced not equal to nr." +
                    " of characteristics introduced");

        else if (nrOfEmptyPlants == 0) {
            if (plantsToInsert.get(0).plantsAreEqual(plantsToInsert.get(1)) &&
                    plantsToInsert.get(1).plantsAreEqual(plantsToInsert.get(2))) {

                plantsService.insertPlant(plantsToInsert.get(0));
                Long last_plant_id = plantsService.getPlantId(plantsToInsert.get(0).getPlant_name());

                for (Characteristics characteristic : characteristicsToInsert) {
                    characteristicsService.insertCharacteristic(characteristic);
                    Long last_characteristic_id = characteristicsService.getCharacteristicId(characteristic.getSpecial_characteristics());
                    Plants_Characteristics plants_characteristics = new Plants_Characteristics(last_plant_id, last_characteristic_id);
                    plantsCharacteristicsService.insertPlantsCharacteristics(plants_characteristics);
                }
            } else if (characteristicsToInsert.get(0).characteristicsAreEqual(characteristicsToInsert.get(1)) &&
                    characteristicsToInsert.get(1).characteristicsAreEqual(characteristicsToInsert.get(2))) {

                characteristicsService.insertCharacteristic(characteristicsToInsert.get(0));
                Long last_characteristic_id = characteristicsService.getCharacteristicId(characteristicsToInsert.get(0).getSpecial_characteristics());

                for (Plants plant : plantsToInsert) {
                    plantsService.insertPlant(plant);
                    Long last_plant_id = plantsService.getPlantId(plant.getPlant_name());
                    Plants_Characteristics plants_characteristics = new Plants_Characteristics(last_plant_id, last_characteristic_id);
                    plantsCharacteristicsService.insertPlantsCharacteristics(plants_characteristics);
                }
            }
        }
    }


    private void showTableData() throws SQLException {
        JFrame resultFrame = new JFrame("Database Search Result");
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultFrame.setLayout(new BorderLayout());

        String[] columnNames;
        DefaultTableModel model = new DefaultTableModel();

        if (showAllPlantsRB.isSelected() || showPlantRB.isSelected()) {
            columnNames = new String[]{"ID", "Plant Name", "Scientific Name"};
            model.setColumnIdentifiers(columnNames);

            if (showAllPlantsRB.isSelected()) {
                ArrayList<Plants> plants = plantsService.selectAllPlants();
                for (Plants p : plants) {
                    Long id = p.getPlant_id();
                    String name = p.getPlant_name();
                    String scientificName = p.getScientific_name();
                    model.addRow(new Object[]{id, name, scientificName});
                }
            } else {
                Plants p = plantsService.getPlantByName(searchPlantByName.getText());
                Long id = p.getPlant_id();
                String name = p.getPlant_name();
                String scientificName = p.getScientific_name();
                model.addRow(new Object[]{id, name, scientificName});
            }
        } else if (showAllCharacteristicsRB.isSelected() || showCharacteristicRB.isSelected()) {
            columnNames = new String[]{"ID", "Characteristic", "Color", "Height", "Season"};
            model.setColumnIdentifiers(columnNames);

            if (showAllCharacteristicsRB.isSelected()) {
                ArrayList<Characteristics> characteristics = characteristicsService.selectAllCharacteristics();
                for (Characteristics c : characteristics) {
                    Long id = c.getCharacteristic_id();
                    String name = c.getSpecial_characteristics();
                    String color = c.getColor();
                    Float height = c.getHeight();
                    String season = c.getSeason();
                    model.addRow(new Object[]{id, name, color, height, season});
                }
            } else {
                Characteristics c = characteristicsService.getCharacteristicByName(searchCharacteristicByName.getText());
                Long id = c.getCharacteristic_id();
                String name = c.getSpecial_characteristics();
                String color = c.getColor();
                Float height = c.getHeight();
                String season = c.getSeason();
                model.addRow(new Object[]{id, name, color, height, season});
            }
        }

        viewTable.setModel(model);
        viewTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        viewTable.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(viewTable);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        resultFrame.setBounds(300, 90, 900, 600);
        resultFrame.setSize(400, 300);
        resultFrame.add(scroll);
        CenterJFrameOnScreen(resultFrame);
        resultFrame.setVisible(true);
    }

    private void addDocumentListenerForModifyTextFields () {
        plantToModify.get(0).getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    Plants foundPlant = plantsService.getPlantByName(plantToModify.get(0).getText());
                    plantToModify.get(1).setText(foundPlant.getScientific_name());
                } catch (NullPointerException | SQLException ignored) {
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    Plants foundPlant = plantsService.getPlantByName(plantToModify.get(0).getText());
                    plantToModify.get(1).setText(foundPlant.getScientific_name());
                } catch (NullPointerException | SQLException ignored) {
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    Plants foundPlant = plantsService.getPlantByName(plantToModify.get(0).getText());
                    plantToModify.get(1).setText(foundPlant.getScientific_name());
                } catch (NullPointerException | SQLException ignored) {
                }
            }
        });

        characteristicToModify.get(0).getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    Characteristics foundCharacteristic = characteristicsService.getCharacteristicByName(characteristicToModify.get(0).getText());
                    characteristicToModify.get(1).setText(foundCharacteristic.getColor());
                    characteristicToModify.get(2).setText(foundCharacteristic.getHeight().toString());
                    characteristicToModify.get(3).setText(foundCharacteristic.getSeason());
                } catch (NullPointerException | SQLException ignored) {
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }

    private void modifyPlant () throws SQLException {
        if (chooseModifyPlant.isSelected()) {
            plantsService.modifyPlantByName(plantToModify.get(0).getText(), plantToModify.get(1).getText());
        } else if (chooseModifyCharacteristic.isSelected()) {
            characteristicsService.modifyCharacteristicByName(characteristicToModify.get(0).getText(),
                    characteristicToModify.get(1).getText(),
                    Float.parseFloat(characteristicToModify.get(2).getText()),
                    characteristicToModify.get(3).getText());
        }
    }

    private void removePlants () throws SQLException {
        if (removePlantByNameRB.isSelected()) {
            plantsService.removePlantByName(removePlant.getText());
        } else if (removeCharacteristicByNameRB.isSelected()) {
            characteristicsService.removeCharacteristicFromTable(removeCharacteristic.getText());
        }
    }

}
