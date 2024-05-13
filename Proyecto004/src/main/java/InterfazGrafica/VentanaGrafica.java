package InterfazGrafica;

import javax.swing.*;
import DAO.GenericDAO;
import models.Student;
import models.Subject;
import models.Teacher;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaGrafica {
    private JPanel panel;
    private CardLayout cardLayout;

    public VentanaGrafica() {
        panel = new JPanel();
        cardLayout = new CardLayout();
        panel.setLayout(cardLayout);

        JPanel mainPanel = new JPanel();
        JButton ingresarButton = new JButton("Ingresar");
        JButton consultarButton = new JButton("Consultar");
        JButton actualizarButton = new JButton("Actualizar");
        JButton eliminarButton = new JButton("Eliminar");
        JButton salirButton = new JButton("Salir");
        
        mainPanel.add(ingresarButton);
        mainPanel.add(consultarButton);
        mainPanel.add(actualizarButton);
        mainPanel.add(eliminarButton);
        mainPanel.add(salirButton);

        JPanel ingresarPanel = new JPanel();
        JButton alumnoButton = new JButton("Alumno");
        JButton profesorButton = new JButton("Profesor");
        JButton materiaButton = new JButton("Materia");
        JButton horarioButton = new JButton("Horario");
        JButton regresarButton = new JButton("Regresar");
        ingresarPanel.add(alumnoButton);
        ingresarPanel.add(profesorButton);
        ingresarPanel.add(materiaButton);
        ingresarPanel.add(horarioButton);
        ingresarPanel.add(regresarButton);

        panel.add(mainPanel, "mainPanel");
        panel.add(ingresarPanel, "ingresarPanel");

        

        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JTextArea textArea = new JTextArea(15, 30);
                JScrollPane scrollPane = new JScrollPane(textArea); 
                textArea.setEditable(false);
                // Crear una instancia de tu DAO para cada tipo de entidad
                GenericDAO<Student> alumnoDAO = new GenericDAO<>(Student.class, new Class<?>[]{Student.class});
                GenericDAO<Teacher> profesorDAO = new GenericDAO<>(Teacher.class, new Class<?>[]{Teacher.class});
                GenericDAO<Subject> materiaDAO = new GenericDAO<>(Subject.class, new Class<?>[]{Subject.class});

                // Llamar al método query para cada DAO
                List<Student> alumnos = alumnoDAO.query();
                List<Teacher> profesores = profesorDAO.query();
                List<Subject> materias = materiaDAO.query();

                // Mostrar los resultados en el JTextArea
                textArea.setText(""); // Limpiar el área de texto antes de agregar nuevos datos
                textArea.append("Alumnos:\n");
                for (Student alumno : alumnos) {
                    textArea.append(alumno.toString() + "\n");
                }
                textArea.append("\nProfesores:\n");
                for (Teacher profesor : profesores) {
                    textArea.append(profesor.toString() + "\n");
                }
                textArea.append("\nMaterias:\n");
                for (Subject materia : materias) {
                    textArea.append(materia.toString() + "\n");
                }
                mainPanel.add(scrollPane);
            }
        });

        
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "ingresarPanel");
            }
        });
        
        alumnoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student alumno = new Student();
                GenericDAO<Student> alumnoDAO = new GenericDAO<>(Student.class, new Class<?>[]{Student.class});
                agregarEntidad(alumnoDAO, alumno);
            }
        });
        
        profesorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Teacher profesor = new Teacher();
                GenericDAO<Teacher> profesorDAO = new GenericDAO<>(Teacher.class, new Class<?>[]{Teacher.class});
                agregarEntidad(profesorDAO, profesor);
            }
        });
        
        materiaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Subject materia = new Subject();
                GenericDAO<Subject> materiaDAO = new GenericDAO<>(Subject.class, new Class<?>[]{Subject.class});
                agregarMateria(materiaDAO, materia);
            }
        });
        
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.previous(panel);
            }
        });


        
    }
    
    
    private <T> void agregarEntidad(GenericDAO<T> dao, T entidad) {
        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        JTextField nameField = new JTextField();
        JTextField lastnameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField levelField = new JTextField();

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String lastname = lastnameField.getText();
                String description = descriptionField.getText();
                String ageText = ageField.getText();
                int age = 0;
                if (!ageText.isEmpty()) {
                    try {
                        age = Integer.parseInt(ageText);
                    } catch (NumberFormatException a) {
                        JOptionPane.showMessageDialog(null, "Por favor, ingresa un número válido para la edad.");
                        return;
                    }
                }

                String levelText = levelField.getText();
                int level = 0;
                if (!levelText.isEmpty()) {
                    try {
                        level = Integer.parseInt(levelText);
                    } catch (NumberFormatException a) {
                        JOptionPane.showMessageDialog(null, "Por favor, ingresa un número válido para el nivel.");
                        return;
                    }
                }

                if (entidad instanceof Student) {
                    Student student = (Student) entidad;
                    student.setName(name);
                    student.setLastname(lastname);
                    student.setAge(age);
                }
                
                if (entidad instanceof Teacher) {
                    Teacher teacher = (Teacher) entidad;
                    teacher.setName(name);
                    teacher.setLastname(lastname);
                    teacher.setAge(age);
                }
                
                if (entidad instanceof Subject) {
                    Subject subject = (Subject) entidad;
                    subject.setName(name);
                    subject.setDescription(description);
                    subject.setLevel(level);
                }
                
                dao.save(entidad);

                cardLayout.show(panel, "mainPanel");
            }
        });

        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Apellido:"));
        formPanel.add(lastnameField);
        formPanel.add(new JLabel("Edad:"));
        formPanel.add(ageField);
        formPanel.add(saveButton);

        panel.add(formPanel, "formPanel");

        cardLayout.show(panel, "formPanel");
    }
    
    private <T> void agregarMateria(GenericDAO<T> dao, T entidad) {
        JPanel formPanel = new JPanel(new GridLayout(4, 2));

        JTextField nameField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField levelField = new JTextField();

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String description = descriptionField.getText();
                String levelText = levelField.getText();
                int level = 0;
                if (!levelText.isEmpty()) {
                    try {
                        level = Integer.parseInt(levelText);
                    } catch (NumberFormatException a) {
                        JOptionPane.showMessageDialog(null, "Por favor, ingresa un número válido para el nivel.");
                        return;
                    }
                }
                
                if (entidad instanceof Subject) {
                    Subject subject = (Subject) entidad;
                    subject.setName(name);
                    subject.setDescription(description);
                    subject.setLevel(level);
                }
                
                dao.save(entidad);

               
                cardLayout.show(panel, "mainPanel");
            }
        });

        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Descipcion:"));
        formPanel.add(descriptionField);
        formPanel.add(new JLabel("Nivel:"));
        formPanel.add(levelField);
        formPanel.add(saveButton);
        
        panel.add(formPanel, "formPanel");

        cardLayout.show(panel, "formPanel");
    }



    public JPanel getPanel() {
        return panel;
    }
}


