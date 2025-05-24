/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.fichas_medicas.views;

import static com.fichas_medicas.components.Cadenas.validateString;
import com.fichas_medicas.components.Calculos;
import com.fichas_medicas.components.FechaComponente;
import com.fichas_medicas.components.TablasTabSummary;
import com.fichas_medicas.dao.CrudArea;
import com.fichas_medicas.dao.CrudCorreo;
import com.fichas_medicas.dao.CrudEstadoCivil;
import com.fichas_medicas.dao.CrudExamen;
import com.fichas_medicas.dao.CrudFichaMedica;
import com.fichas_medicas.dao.CrudGrupoSanguineo;
import com.fichas_medicas.dao.CrudPersona;
import com.fichas_medicas.domain.Area;
import com.fichas_medicas.domain.Correo;
import com.fichas_medicas.domain.EstadoCivil;
import com.fichas_medicas.domain.Examen;
import com.fichas_medicas.domain.FichaMedica;
import com.fichas_medicas.domain.GrupoSanguineo;
import com.fichas_medicas.domain.Persona;
import com.fichas_medicas.domain.Usuario;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import static java.lang.System.err;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @Corregido 14 : 01 2025 cambio final
 */
public class EditarFichas extends javax.swing.JDialog {

    TablasTabSummary tblF = null;
    List<FichaMedica> lista_fichas = null;
    String rutaimagen = "C://Fichas_Medicas//img//logofoto.png";
    private String var1;
    int gbr_persona = 0;
    int gbr_ficha_examen = 0;

    private CrudArea crudA = null;
    /// grabar
    private CrudPersona crudP = null;
    private CrudFichaMedica crudFM = null;
    private CrudExamen crudEx = null;
    //
    private CrudEstadoCivil crudEcl = null;
    private CrudGrupoSanguineo crudGrupo = null;
    private Usuario objU = null;
    private CrudCorreo crudCo = null;
    private List<Area> areas = null;
    private List<EstadoCivil> estados_civiles = null;
    private List<GrupoSanguineo> lista_grupo = null;
    private List<Correo> lista_correos = null;
    private String error1 = "";
    private Date fecha_nac = null;
    private int pst_1_error = 0;
    private int pst_2_error = 0;
    private int pst_3_error = 0;
    private Integer frk_id_ficha_medica_grb = -1;
    int frk_id_examen_upd = -1;
    //// valores examen
    private Integer vr_frecuencia_cardiaca;
    private Integer vr_presion_arterial;  // doble valor
    private Integer vr_saturacion;
    private Integer vr_sistolica;
    private Integer vr_diastolica;
    private Double vr_peso;
    private Double vr_estatura;
    private Double vr_temperatura;
    private Double vr_imc;
    private String vr_estado_actual;
    private String vr_habitos;
    private Persona grb_objP;
    private FichaMedica grb_objF;
    private Examen grb_objE;
    private FichaMedica fillObjFM;

    /**
     * Creates new form Fichas
     *
     * @param parent
     * @param modal
     */
    public EditarFichas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setSize(797, 685);
        cargarImagen();
        crudA = new CrudArea();
        areas = crudA.getAll();
        // grabar
        crudP = new CrudPersona();
        crudFM = new CrudFichaMedica();
        crudEx = new CrudExamen();
        //
        crudEcl = new CrudEstadoCivil();
        crudCo = new CrudCorreo();
        crudGrupo = new CrudGrupoSanguineo();
        System.out.println("lista " + areas.size());
        fillAreas();
        fillEstadoCivil();
        fillCorreo();
        fillGrupoSanguineo();
        activar(false);
        antecedentes.setEnabledAt(1, false);
        antecedentes.setEnabledAt(2, false);
        this.objU = new Usuario("JTAPIA", "4444", "JOSE", "LINO", "lino@gmail.com", 3, "SOFIA24", "A");
        lista_fichas = new ArrayList<>();
        tblF = new TablasTabSummary();
    }

    public EditarFichas(java.awt.Frame parent, boolean modal, Usuario obj) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setSize(797, 685);
        this.objU = obj;
        System.out.println("Usuario Modulo Fichas " + objU.getUsuario());
        cargarImagen();
        crudA = new CrudArea();
        areas = crudA.getAll();
        // grabar
        crudP = new CrudPersona();
        crudFM = new CrudFichaMedica();
        crudEx = new CrudExamen();
        //
        crudEcl = new CrudEstadoCivil();
        crudCo = new CrudCorreo();
        crudGrupo = new CrudGrupoSanguineo();
        System.out.println("lista " + areas.size());
        fillAreas();
        fillEstadoCivil();
        fillCorreo();
        fillGrupoSanguineo();
        activar(false);
        antecedentes.setEnabledAt(1, false);
        antecedentes.setEnabledAt(2, false);
        lista_fichas = new ArrayList<>();
        tblF = new TablasTabSummary();
    }

    public EditarFichas(java.awt.Frame parent, boolean modal, FichaMedica objFM, Usuario obj) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setSize(797, 685);
        this.objU = obj;
        this.fillObjFM = objFM;
        System.out.println("hhh  " + objFM.getId_persona());
        System.out.println("ID FICHA pilas " + fillObjFM.getId_fichaMedica() + " " + fillObjFM.getId_persona());
        System.out.println("Usuario Modulo Fichas " + this.fillObjFM.getId_persona());
        //cargarImagen();
        crudA = new CrudArea();
        areas = crudA.getAll();
        // grabar
        crudP = new CrudPersona();
        crudFM = new CrudFichaMedica();
        crudEx = new CrudExamen();
        //
        crudEcl = new CrudEstadoCivil();
        crudCo = new CrudCorreo();
        crudGrupo = new CrudGrupoSanguineo();
        System.out.println("lista " + areas.size());
        fillAreas();
        fillEstadoCivil();
        fillCorreo();
        fillGrupoSanguineo();
        //System.out.println("fillData Editar Ficha:" + objFM.getId_persona());

        fillData(objFM);
        TXT_CEDULA.setEditable(false);
        //activar(false);
        //antecedentes.setEnabledAt(1, false);
        ///antecedentes.setEnabledAt(2, false);
    }

    private void fillData(FichaMedica obj) {
        System.out.println("fillData Editar Ficha:" + obj.getId_persona());
        TXT_CEDULA.setText(obj.getId_persona());
        var objPer = crudP.getOne(obj.getId_persona());
        TXT_NOMBRE.setText(objPer.getNombre());
        TXT_APELLIDO.setText(objPer.getApellidos());
        TXT_L_NACIMIENTO.setText(objPer.getLugar_nacimiento());
        TXT_N_HIJOS.setText(objPer.getN_hijos().toString());
        direccion.setText(objPer.getDireccion());
        telefono_emergencia.setText(objPer.getTelefono_emergencia());
        telefono.setText(objPer.getTelefono());
//        // Combo Box Persona
        Persona objP = crudP.getOne(obj.getId_persona());
        var resA = crudA.cadenaArea(objP.getId_area());
        area.setSelectedItem(resA);
//
        var resEC = crudEcl.cadenaEstadoCivil(objP.getId_estado_civil());
        estado_civil.setSelectedItem(resEC);

        var resGS = crudGrupo.cadenaGrupoSanguineo(objP.getId_grupo_sanguineo());
        grupito.setSelectedItem(resGS);

        var fech1 = FechaComponente.getStringFecha(objP.getFecha_nacimiento());
        TXT_F_NACIMIENTO.setText(fech1);
//
        cargarImagen(objP.getFoto());
//        //  System.out.println("Id_Rol " + objP.getId_area() + " " + resA);
//        // FICHA
       TXT_A_P_FAMILIARES.setText(obj.getAnt_patologicos_fam());
        TXT_A_P_PERSONALES.setText(obj.getAnt_patologicos_per());
        System.out.println("Exame "+obj.getId_fichaMedica());
       var obEx = crudEx.getOneByIdFicha(obj.getId_fichaMedica());
       
       TXT_HABITOS.setText(obEx.getHabitos());
        TXT_E_ACTUAL.setText(obEx.getEstadoActual());
        muestra_fecha.setText(FechaComponente.getStringFecha(obEx.getFechaRegistro()));
        // EXAMEN
        TEMPERATURA.setText(obEx.getTemperatura()+"");
        PESO.setText(obEx.getPesoKg().toString());
        SATURACION.setText(obEx.getSaturacion().toString());
        ESTATURA.setText(obEx.getEstaturaCm().toString());
        SISTOLICA.setText(obEx.getSistolica().toString());
        DIASTOLICA.setText(obEx.getDiastolica().toString());
        IMC.setText(obEx.getImc().toString());
        FRECUENCIA_CARDIACA.setText(obEx.getFrecuenciaCardiaca().toString());
        CON_FIS.setText(obEx.getCondiciones_fisicas());
    }

    private void cargarImagen() {

        String file = rutaimagen;
        foto.setIcon(new ImageIcon(file));
        ImageIcon icon = new ImageIcon(file);
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(310, 270, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcono = new ImageIcon(newimg);
        foto.setIcon(newIcono);
        foto.setSize(350, 299);
    }

    private void cargarImagen(String ruta) {
        String file = ruta;
        foto.setIcon(new ImageIcon(file));
        ImageIcon icon = new ImageIcon(file);
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(310, 270, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcono = new ImageIcon(newimg);
        foto.setIcon(newIcono);
        foto.setSize(350, 299);
        rutaimagen = file;
    }

    private void fillAreas() {
        areas = crudA.getAll();
        area.removeAllItems();
        area.addItem("Elija una Opción...");
        for (int i = 0; i < areas.size(); i++) {
            area.addItem(areas.get(i).getNombre_area());
        }
    }

    private void fillGrupoSanguineo() {
        lista_grupo = crudGrupo.getAll();
        grupito.removeAllItems();
        grupito.addItem("Elija una Opción...");
        for (int i = 0; i < lista_grupo.size(); i++) {
            grupito.addItem(lista_grupo.get(i).getNombre());
        }
    }

    private void fillEstadoCivil() {
        estados_civiles = crudEcl.getAll();
        estado_civil.removeAllItems();
        estado_civil.addItem("Elija una Opción...");
        for (int i = 0; i < estados_civiles.size(); i++) {
            estado_civil.addItem(estados_civiles.get(i).getNombreEstadoCivil());
        }
    }

    private void fillCorreo() {
        lista_correos = crudCo.getPersonMail(TXT_CEDULA.getText());
        correo.removeAllItems();
        correo.addItem("Elija una Opción...");
        for (int i = 0; i < lista_correos.size(); i++) {
            correo.addItem(lista_correos.get(i).getCorreo());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel22 = new javax.swing.JLabel();
        F_CARDIACA4 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        F_CARDIACA3 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        F_CARDIACA2 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        F_CARDIACA = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        F_CARDIACA1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        F_CARDIACA5 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        F_CARDIACA7 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        F_CARDIACA6 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        F_CARDIACA8 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        F_CARDIACA9 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        F_CARDIACA10 = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        F_CARDIACA11 = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        F_CARDIACA12 = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        F_CARDIACA13 = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        antecedentes = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        TXT_NOMBRE = new javax.swing.JTextField();
        TXT_APELLIDO = new javax.swing.JTextField();
        TXT_F_NACIMIENTO = new javax.swing.JTextField();
        TXT_L_NACIMIENTO = new javax.swing.JTextField();
        TXT_N_HIJOS = new javax.swing.JTextField();
        telefono_emergencia = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        TXT_CEDULA = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        foto = new javax.swing.JLabel();
        FOTO = new javax.swing.JButton();
        btn_validar_datos = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        estado_civil = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        correo = new javax.swing.JComboBox<>();
        btn_cal = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        direccion = new javax.swing.JTextArea();
        area = new javax.swing.JComboBox<>();
        jLabel69 = new javax.swing.JLabel();
        btn_correo = new javax.swing.JButton();
        grupito = new javax.swing.JComboBox<>();
        g_sanguineo = new javax.swing.JLabel();
        EMERGENCIA = new javax.swing.JLabel();
        telefono = new javax.swing.JTextField();
        btn_eliminar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        TXT_A_P_FAMILIARES = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        TXT_A_P_PERSONALES = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        TXT_HABITOS = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        TXT_E_ACTUAL = new javax.swing.JTextField();
        muestra_fecha = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        lbl_peso = new javax.swing.JLabel();
        lbl_temperatura = new javax.swing.JLabel();
        lbl_altura = new javax.swing.JLabel();
        lbl_saturacion = new javax.swing.JLabel();
        lbl_condi_fisicas = new javax.swing.JLabel();
        PESO = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        lbl_fre_cardiaca = new javax.swing.JLabel();
        FRECUENCIA_CARDIACA = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        SISTOLICA = new javax.swing.JTextField();
        lbl_pre_arterial = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        SATURACION = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        TEMPERATURA = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        ESTATURA = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        IMC = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btn_guarda_ficha = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        lbl_imc = new javax.swing.JLabel();
        DIASTOLICA = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        sistolicatxt = new javax.swing.JLabel();
        diastolicatxt = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        CON_FIS = new javax.swing.JTextArea();
        lblResultado = new javax.swing.JLabel();
        lblresultado1 = new javax.swing.JLabel();
        lblresultado3 = new javax.swing.JLabel();

        jLabel22.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/file (3).png"))); // NOI18N

        F_CARDIACA4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        F_CARDIACA4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F_CARDIACA4ActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(242, 242, 242));
        jLabel35.setText("PESO (KG)");

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/file (1).png"))); // NOI18N

        F_CARDIACA3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        F_CARDIACA3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F_CARDIACA3ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(242, 242, 242));
        jLabel19.setText("FRECUANCIA CARDIACA");
        jLabel19.setDoubleBuffered(true);

        F_CARDIACA2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        F_CARDIACA2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F_CARDIACA2ActionPerformed(evt);
            }
        });

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/file.png"))); // NOI18N
        jLabel33.setText("jLabel9");

        jLabel18.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(242, 242, 242));
        jLabel18.setText("PRESION ARTERIAL");

        F_CARDIACA.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        F_CARDIACA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F_CARDIACAActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(242, 242, 242));
        jLabel20.setText("SATURACION ");

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/file (2).png"))); // NOI18N

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/file (5).png"))); // NOI18N

        F_CARDIACA1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        F_CARDIACA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F_CARDIACA1ActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(242, 242, 242));
        jLabel23.setText("TEMPERATURA");

        F_CARDIACA5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        F_CARDIACA5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F_CARDIACA5ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/file (4).png"))); // NOI18N

        jLabel37.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(242, 242, 242));
        jLabel37.setText("ESTATURA (CM)");

        jLabel24.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/file (6).png"))); // NOI18N

        F_CARDIACA7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        F_CARDIACA7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F_CARDIACA7ActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(242, 242, 242));
        jLabel38.setText("IMC");

        jLabel39.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/file (3).png"))); // NOI18N

        F_CARDIACA6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        F_CARDIACA6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F_CARDIACA6ActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(242, 242, 242));
        jLabel40.setText("PESO (KG)");

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/file (1).png"))); // NOI18N

        F_CARDIACA8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        F_CARDIACA8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F_CARDIACA8ActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(242, 242, 242));
        jLabel42.setText("FRECUANCIA CARDIACA");
        jLabel42.setDoubleBuffered(true);

        F_CARDIACA9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        F_CARDIACA9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F_CARDIACA9ActionPerformed(evt);
            }
        });

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/file.png"))); // NOI18N
        jLabel43.setText("jLabel9");

        jLabel44.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(242, 242, 242));
        jLabel44.setText("PRESION ARTERIAL");

        F_CARDIACA10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        F_CARDIACA10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F_CARDIACA10ActionPerformed(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(242, 242, 242));
        jLabel45.setText("SATURACION ");

        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/file (2).png"))); // NOI18N

        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/file (5).png"))); // NOI18N

        F_CARDIACA11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        F_CARDIACA11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F_CARDIACA11ActionPerformed(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(242, 242, 242));
        jLabel48.setText("TEMPERATURA");

        F_CARDIACA12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        F_CARDIACA12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F_CARDIACA12ActionPerformed(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/file (4).png"))); // NOI18N

        jLabel50.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(242, 242, 242));
        jLabel50.setText("ESTATURA (CM)");

        jLabel51.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/file (6).png"))); // NOI18N

        F_CARDIACA13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        F_CARDIACA13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F_CARDIACA13ActionPerformed(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(242, 242, 242));
        jLabel52.setText("IMC");

        jButton9.setBackground(new java.awt.Color(0, 153, 204));
        jButton9.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Eliminar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(0, 153, 204));
        jButton7.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Guardar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(74, 159, 181));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPanel1KeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("NOMBRES");
        jLabel1.setAlignmentX(1.0F);
        jLabel1.setAlignmentY(2.0F);

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("APELLIDOS");

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("FECHA ");

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("LUGAR DE ");

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ESTADO CIVIL");

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("N HIJOS");

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("TELÉFONO");

        TXT_NOMBRE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        TXT_NOMBRE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_NOMBREActionPerformed(evt);
            }
        });
        TXT_NOMBRE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TXT_NOMBREKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_NOMBREKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXT_NOMBREKeyTyped(evt);
            }
        });

        TXT_APELLIDO.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        TXT_APELLIDO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_APELLIDOActionPerformed(evt);
            }
        });
        TXT_APELLIDO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_APELLIDOKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXT_APELLIDOKeyTyped(evt);
            }
        });

        TXT_F_NACIMIENTO.setEditable(false);
        TXT_F_NACIMIENTO.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        TXT_F_NACIMIENTO.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TXT_F_NACIMIENTO.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        TXT_F_NACIMIENTO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXT_F_NACIMIENTOKeyTyped(evt);
            }
        });

        TXT_L_NACIMIENTO.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        TXT_L_NACIMIENTO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_L_NACIMIENTOActionPerformed(evt);
            }
        });
        TXT_L_NACIMIENTO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_L_NACIMIENTOKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXT_L_NACIMIENTOKeyTyped(evt);
            }
        });

        TXT_N_HIJOS.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        TXT_N_HIJOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_N_HIJOSActionPerformed(evt);
            }
        });
        TXT_N_HIJOS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXT_N_HIJOSKeyTyped(evt);
            }
        });

        telefono_emergencia.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        telefono_emergencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefono_emergenciaActionPerformed(evt);
            }
        });
        telefono_emergencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                telefono_emergenciaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                telefono_emergenciaKeyTyped(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("CÉDULA");

        TXT_CEDULA.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TXT_CEDULA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TXT_CEDULA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        TXT_CEDULA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TXT_CEDULAFocusLost(evt);
            }
        });
        TXT_CEDULA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_CEDULAActionPerformed(evt);
            }
        });
        TXT_CEDULA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_CEDULAKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXT_CEDULAKeyTyped(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("ÁREA");

        jLabel30.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("NACIMIENTO");

        jLabel31.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("NACIMIENTO");

        foto.setBackground(new java.awt.Color(255, 255, 255));
        foto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foto.setIcon(new javax.swing.ImageIcon("C:\\Fichas_Medicas\\img\\logofoto.png")); // NOI18N
        foto.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        FOTO.setBackground(new java.awt.Color(106, 176, 193));
        FOTO.setFont(new java.awt.Font("Myanmar Text", 1, 14)); // NOI18N
        FOTO.setIcon(new javax.swing.ImageIcon("C:\\Fichas_Medicas\\img\\carne-de-identidad.png")); // NOI18N
        FOTO.setText("  Foto");
        FOTO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FOTOActionPerformed(evt);
            }
        });

        btn_validar_datos.setBackground(new java.awt.Color(0, 153, 204));
        btn_validar_datos.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        btn_validar_datos.setForeground(new java.awt.Color(255, 255, 255));
        btn_validar_datos.setIcon(new javax.swing.ImageIcon("C:\\Fichas_Medicas\\img\\aceptar.png")); // NOI18N
        btn_validar_datos.setText("Guardar");
        btn_validar_datos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_validar_datos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_validar_datosActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 153, 204));
        jButton3.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon("C:\\Fichas_Medicas\\img\\cancelar.png")); // NOI18N
        jButton3.setText("Cerrar");
        jButton3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        estado_civil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Escoger", "Casado", "Divorciado", "Soltero" }));
        estado_civil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estado_civilActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("CORREO");

        correo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...." }));
        correo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                correoActionPerformed(evt);
            }
        });

        btn_cal.setText("....");
        btn_cal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_calActionPerformed(evt);
            }
        });

        direccion.setColumns(20);
        direccion.setRows(5);
        direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                direccionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                direccionKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(direccion);

        area.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        area.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                areaActionPerformed(evt);
            }
        });

        jLabel69.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 255, 255));
        jLabel69.setText("DOMICILIO");

        btn_correo.setText("....");
        btn_correo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_correoActionPerformed(evt);
            }
        });

        g_sanguineo.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        g_sanguineo.setForeground(new java.awt.Color(255, 255, 255));
        g_sanguineo.setText("GRUPO SANGUÍNEO");

        EMERGENCIA.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        EMERGENCIA.setForeground(new java.awt.Color(255, 255, 255));
        EMERGENCIA.setText("EMERGENCIA");

        telefono.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefonoActionPerformed(evt);
            }
        });
        telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                telefonoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                telefonoKeyTyped(evt);
            }
        });

        btn_eliminar.setBackground(new java.awt.Color(0, 153, 204));
        btn_eliminar.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        btn_eliminar.setForeground(new java.awt.Color(255, 255, 255));
        btn_eliminar.setIcon(new javax.swing.ImageIcon("C:\\Fichas_Medicas\\img\\ic_eliminar.png")); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TXT_NOMBRE, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXT_CEDULA, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXT_APELLIDO, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel31)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addGap(18, 18, 18)))
                                    .addComponent(jLabel5)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel6))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TXT_N_HIJOS, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel3))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(59, 59, 59)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(TXT_L_NACIMIENTO)
                                            .addComponent(estado_civil, 0, 188, Short.MAX_VALUE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(58, 58, 58)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(TXT_F_NACIMIENTO, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(area, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(correo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_correo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(FOTO, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(g_sanguineo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(grupito, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(foto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(EMERGENCIA)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(telefono_emergencia))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel69)
                                        .addGap(26, 26, 26))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(11, 11, 11))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(btn_validar_datos, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TXT_CEDULA, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TXT_NOMBRE, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TXT_APELLIDO, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(area, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel30))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(TXT_F_NACIMIENTO, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_cal))))
                    .addComponent(foto, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TXT_L_NACIMIENTO, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel31)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(estado_civil, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel69))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TXT_N_HIJOS, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(FOTO, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(grupito, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(g_sanguineo))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(correo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_correo)
                            .addComponent(EMERGENCIA)
                            .addComponent(telefono_emergencia, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_validar_datos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(315, 315, 315))
        );

        antecedentes.addTab("DATOS ", jPanel1);

        jPanel2.setBackground(new java.awt.Color(74, 159, 181));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 204), 2, true));
        jPanel2.setForeground(new java.awt.Color(0, 153, 255));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("ANTECENDETES ");

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("PATOLOGICOS");

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("FAMILIARES");

        TXT_A_P_FAMILIARES.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        TXT_A_P_FAMILIARES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_A_P_FAMILIARESActionPerformed(evt);
            }
        });
        TXT_A_P_FAMILIARES.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_A_P_FAMILIARESKeyReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("ANTECENDETES ");

        jLabel15.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("PATOLOGICOS");

        jLabel16.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("PERSONALES");

        TXT_A_P_PERSONALES.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        TXT_A_P_PERSONALES.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        TXT_A_P_PERSONALES.setDisabledTextColor(new java.awt.Color(0, 204, 255));
        TXT_A_P_PERSONALES.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        TXT_A_P_PERSONALES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_A_P_PERSONALESActionPerformed(evt);
            }
        });
        TXT_A_P_PERSONALES.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_A_P_PERSONALESKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("HABITOS");

        TXT_HABITOS.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 2, true));
        TXT_HABITOS.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        TXT_HABITOS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_HABITOSKeyReleased(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("ESTADO ACTUAL ");

        TXT_E_ACTUAL.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        TXT_E_ACTUAL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_E_ACTUALActionPerformed(evt);
            }
        });
        TXT_E_ACTUAL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_E_ACTUALKeyReleased(evt);
            }
        });

        muestra_fecha.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        muestra_fecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        muestra_fecha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(13, 13, 13))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addGap(18, 18, 18)))
                                .addComponent(TXT_A_P_PERSONALES, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel13)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TXT_A_P_FAMILIARES))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel10))
                            .addComponent(TXT_HABITOS, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel17)
                            .addComponent(TXT_E_ACTUAL, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(55, 55, 55))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(muestra_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(283, 283, 283))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(TXT_A_P_FAMILIARES, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(TXT_A_P_PERSONALES, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TXT_HABITOS, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TXT_E_ACTUAL, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(muestra_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(337, Short.MAX_VALUE))
        );

        antecedentes.addTab("ANTECEDENTES", jPanel2);

        jPanel3.setBackground(new java.awt.Color(74, 159, 181));

        lbl_condi_fisicas.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        PESO.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        PESO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PESOActionPerformed(evt);
            }
        });
        PESO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PESOKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PESOKeyTyped(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(242, 242, 242));
        jLabel54.setText("PESO (KG)");

        FRECUENCIA_CARDIACA.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        FRECUENCIA_CARDIACA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FRECUENCIA_CARDIACAActionPerformed(evt);
            }
        });
        FRECUENCIA_CARDIACA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                FRECUENCIA_CARDIACAKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                FRECUENCIA_CARDIACAKeyTyped(evt);
            }
        });

        jLabel56.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(242, 242, 242));
        jLabel56.setText("FRECUENCIA CARDIACA");
        jLabel56.setDoubleBuffered(true);

        SISTOLICA.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        SISTOLICA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SISTOLICAActionPerformed(evt);
            }
        });
        SISTOLICA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SISTOLICAKeyTyped(evt);
            }
        });

        lbl_pre_arterial.setText("jLabel9");

        jLabel58.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(242, 242, 242));
        jLabel58.setText("PRESION ARTERIAL");

        SATURACION.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        SATURACION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SATURACIONActionPerformed(evt);
            }
        });
        SATURACION.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SATURACIONKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SATURACIONKeyTyped(evt);
            }
        });

        jLabel59.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(242, 242, 242));
        jLabel59.setText("SATURACION ");

        TEMPERATURA.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        TEMPERATURA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TEMPERATURAActionPerformed(evt);
            }
        });
        TEMPERATURA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TEMPERATURAKeyTyped(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(242, 242, 242));
        jLabel62.setText("TEMPERATURA");

        ESTATURA.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        ESTATURA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ESTATURAActionPerformed(evt);
            }
        });
        ESTATURA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ESTATURAKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ESTATURAKeyTyped(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(242, 242, 242));
        jLabel64.setText("ESTATURA (M)");

        IMC.setEditable(false);
        IMC.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        IMC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        IMC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IMCActionPerformed(evt);
            }
        });
        IMC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                IMCKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                IMCKeyTyped(evt);
            }
        });

        jLabel66.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(242, 242, 242));
        jLabel66.setText("IMC");

        jLabel67.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(242, 242, 242));
        jLabel67.setText("CONDICIONES FÍSICAS");

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        jPanel4.setBackground(new java.awt.Color(113, 183, 202));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_guarda_ficha.setBackground(new java.awt.Color(0, 153, 204));
        btn_guarda_ficha.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        btn_guarda_ficha.setForeground(new java.awt.Color(255, 255, 255));
        btn_guarda_ficha.setIcon(new javax.swing.ImageIcon("C:\\Fichas_Medicas\\img\\guardar.png")); // NOI18N
        btn_guarda_ficha.setText("Guardar");
        btn_guarda_ficha.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_guarda_ficha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guarda_fichaActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(0, 153, 204));
        jButton11.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setIcon(new javax.swing.ImageIcon("C:\\Fichas_Medicas\\img\\cancelar.png")); // NOI18N
        jButton11.setText("Cancelar");
        jButton11.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_guarda_ficha, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_guarda_ficha)
                .addGap(18, 18, 18)
                .addComponent(jButton11)
                .addGap(23, 23, 23))
        );

        DIASTOLICA.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        DIASTOLICA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DIASTOLICAActionPerformed(evt);
            }
        });
        DIASTOLICA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DIASTOLICAKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                DIASTOLICAKeyTyped(evt);
            }
        });

        jLabel72.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(242, 242, 242));
        jLabel72.setText("/");

        sistolicatxt.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        sistolicatxt.setForeground(new java.awt.Color(242, 242, 242));
        sistolicatxt.setText("Sistólica");

        diastolicatxt.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        diastolicatxt.setForeground(new java.awt.Color(242, 242, 242));
        diastolicatxt.setText("Diastólica");

        CON_FIS.setColumns(20);
        CON_FIS.setRows(5);
        CON_FIS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CON_FISKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(CON_FIS);

        lblResultado.setBackground(new java.awt.Color(73, 157, 178));
        lblResultado.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N

        lblresultado1.setBackground(new java.awt.Color(73, 157, 178));
        lblresultado1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        lblresultado3.setBackground(new java.awt.Color(73, 157, 178));
        lblresultado3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_saturacion)
                            .addComponent(lbl_temperatura)
                            .addComponent(lbl_pre_arterial, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_fre_cardiaca))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel58)
                            .addComponent(jLabel62)
                            .addComponent(TEMPERATURA, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel59)
                            .addComponent(SATURACION, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(FRECUENCIA_CARDIACA, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(SISTOLICA, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                            .addGap(25, 25, 25)
                                            .addComponent(sistolicatxt)))
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(DIASTOLICA, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGap(47, 47, 47)
                                            .addComponent(diastolicatxt)))))
                            .addComponent(jLabel56)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(lblresultado1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(lblresultado3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lbl_peso)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(PESO, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel54)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lbl_imc)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(IMC, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_condi_fisicas)
                                    .addComponent(lbl_altura))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel64)
                                            .addComponent(ESTATURA, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel67)
                                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addContainerGap(63, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(96, 96, 96))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lbl_peso)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(PESO, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbl_altura))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(23, 23, 23)
                                    .addComponent(jLabel64)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(ESTATURA, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 4, Short.MAX_VALUE))))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lbl_temperatura, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel62)
                                    .addGap(10, 10, 10)
                                    .addComponent(TEMPERATURA, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel59))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbl_saturacion)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(SATURACION, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel58)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(SISTOLICA, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(sistolicatxt))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(DIASTOLICA, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel72))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(diastolicatxt))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lbl_pre_arterial)
                                .addGap(7, 7, 7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblresultado3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_imc)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel66)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(IMC, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                                .addGap(6, 6, 6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel67)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_fre_cardiaca, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(FRECUENCIA_CARDIACA, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbl_condi_fisicas))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblresultado1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(334, 334, 334))
        );

        antecedentes.addTab("EXAMENES FISICO", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(antecedentes, javax.swing.GroupLayout.PREFERRED_SIZE, 785, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(antecedentes, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void F_CARDIACA4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F_CARDIACA4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_F_CARDIACA4ActionPerformed

    private void F_CARDIACA3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F_CARDIACA3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_F_CARDIACA3ActionPerformed

    private void F_CARDIACA2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F_CARDIACA2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_F_CARDIACA2ActionPerformed

    private void F_CARDIACAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F_CARDIACAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_F_CARDIACAActionPerformed

    private void F_CARDIACA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F_CARDIACA1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_F_CARDIACA1ActionPerformed

    private void F_CARDIACA5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F_CARDIACA5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_F_CARDIACA5ActionPerformed

    private void F_CARDIACA7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F_CARDIACA7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_F_CARDIACA7ActionPerformed

    private void F_CARDIACA6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F_CARDIACA6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_F_CARDIACA6ActionPerformed

    private void F_CARDIACA8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F_CARDIACA8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_F_CARDIACA8ActionPerformed

    private void F_CARDIACA9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F_CARDIACA9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_F_CARDIACA9ActionPerformed

    private void F_CARDIACA10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F_CARDIACA10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_F_CARDIACA10ActionPerformed

    private void F_CARDIACA11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F_CARDIACA11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_F_CARDIACA11ActionPerformed

    private void F_CARDIACA12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F_CARDIACA12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_F_CARDIACA12ActionPerformed

    private void F_CARDIACA13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F_CARDIACA13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_F_CARDIACA13ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed
    private void grabarAntecedentes() {

        var objF = new FichaMedica((Date) FechaComponente.FechaSql(),
                TXT_CEDULA.getText(),
                TXT_A_P_FAMILIARES.getText(),
                TXT_A_P_PERSONALES.getText(),
                objU.getUsuario(), "A");
        // Problemas con fecha de nacikiento
        System.out.println("Prueba grabar " + objF.toString());
        grb_objF = objF;
    }

    private String validarCamposAntecedentes() {
        var error2 = "";
        ///
        if (TXT_A_P_FAMILIARES.getText().length() < 4) {
            error2 = error2 + "Antecedentes Familiares\n";
        }

        if (TXT_A_P_PERSONALES.getText().length() < 4) {
            error2 = error2 + "Antecedentes Personales\n";
        }

        if (TXT_HABITOS.getText().length() < 3) {
            error2 = error2 + "Habitos\n";
        }
        if (TXT_E_ACTUAL.getText().length() < 3) {
            error2 = error2 + "Estado Actual\n";
        }

        return error2;

    }

    private void activarAntededentes(boolean valor) {
        TXT_A_P_FAMILIARES.setEditable(valor);
        TXT_A_P_PERSONALES.setEditable(valor);
        TXT_HABITOS.setEditable(valor);
        TXT_E_ACTUAL.setEditable(valor);
    }


    private void TXT_E_ACTUALActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_E_ACTUALActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_E_ACTUALActionPerformed

    private void TXT_A_P_PERSONALESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_A_P_PERSONALESActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_A_P_PERSONALESActionPerformed

    private void TXT_A_P_FAMILIARESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_A_P_FAMILIARESActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_A_P_FAMILIARESActionPerformed

    private void jPanel1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyTyped
        var var2 = validateString(TXT_NOMBRE.getText());
        if (var2 == null) {
            err.append("FUNCIONAL");
            int c = 0;
            c++;
        } else {
            String var1 = null;
            TXT_NOMBRE.setText(var1);
            System.out.println(err.toString());
        }
    }//GEN-LAST:event_jPanel1KeyTyped

    private void direccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_direccionKeyTyped
        var var10 = validateString(direccion.getText());
        if (var10 == null) {
            err.append("Direccion");
            int c = 0;
            c++;
        } else {
            // Only change the text of 'direccion' if 'var1' is valid (or if needed)
            if (var1 != null && !var1.isEmpty()) {
                direccion.setText(var1);
            }
        }
        System.out.println(err.toString());
    }//GEN-LAST:event_direccionKeyTyped

    private void btn_calActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_calActionPerformed
        var cld = new GetFecha(new JFrame(), true);
        cld.setVisible(true);
        var fec = cld.getStr_fecha();
        System.out.println(" fecha frm " + fec);
        TXT_F_NACIMIENTO.setText(fec);

        fecha_nac = (Date) cld.getDt_fecha();
        System.out.println("fecha grt 1 " + fecha_nac);
    }//GEN-LAST:event_btn_calActionPerformed

    private void correoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_correoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_correoActionPerformed

    private void estado_civilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estado_civilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estado_civilActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // btn_validar_datos.setEnabled(true);
        setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btn_validar_datosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_validar_datosActionPerformed
        var vali = validarCampos();
        if (vali.length() > 1) {
            JOptionPane.showMessageDialog(null, vali, "Datos Invalidos", JOptionPane.INFORMATION_MESSAGE);
            activar(true);
            //gbr_persona = 0;
        } else {
            //  btn_validar_datos.setEnabled(false);
            //  Dialogo.Mensaje("Validado", 90);
            editar();
        }
    }//GEN-LAST:event_btn_validar_datosActionPerformed
    /* Metodo grabar
    20/02/2025
    Hora:9:35 am
    Autor: Jose Luis Tapia
     */
    private int getIdArea() {
        var id_area = 0;
        int selectedIndex = area.getSelectedIndex();
        var posA = selectedIndex;
        System.out.println("posA ->" + posA);
        error1 = error1 + "Area\n";
        if (posA > 0) {
            id_area = areas.get(posA - 1).getId_area();
        }

        return id_area;
    }

    private int getIdAGrupoSanguineo() {
        var id_grupo = 0;
        int selectedIndex = grupito.getSelectedIndex();
        var posA = selectedIndex;
        error1 = error1 + "Grupo Sanguineo\n";
        if (posA > 0) {
            id_grupo = lista_grupo.get(posA - 1).getId_grupo_sanguineo();
        }
        return id_grupo;
    }

    private int getIdEstadoCivil() {
        var id_estado_civil = 0;
        int selectedIndex = estado_civil.getSelectedIndex();
        var posA = selectedIndex;
        if (posA > 0) {
            id_estado_civil = estados_civiles.get(posA - 1).getIdEstadoCivil();
        }
        return id_estado_civil;
    }

    private String validarCampos() {
        var error2 = "";
        ///
        if (TXT_NOMBRE.getText().length() < 2) {
            error2 = error2 + "Nombres\n";
        }

        if (TXT_APELLIDO.getText().length() < 2) {
            error2 = error2 + "Apellidos\n";
        }

        if (TXT_F_NACIMIENTO.getText().length() < 3) {
            error2 = error2 + "Fecha de nacimiento\n";
        }
        if (TXT_L_NACIMIENTO.getText().length() < 3) {
            error2 = error2 + "Lugar de nacimiento\n";
        }
        if (TXT_N_HIJOS.getText().length() < 1) {
            error2 = error2 + "Números de hijos\n";
        }
        if (telefono.getText().length() < 9) {
            error2 = error2 + "Telefono\n";
        }
        if (direccion.getText().length() < 5) {
            error2 = error2 + "Direccion de domicilio\n";
        }
        int posA = area.getSelectedIndex();
        //System.out.println("posA ->" + posA);
        if (posA < 1) {
            error2 = error2 + "Area\n";
        }
        posA = estado_civil.getSelectedIndex();
        if (posA < 1) {
            error2 = error2 + "Estado Civil\n";
        }

        posA = grupito.getSelectedIndex();
        if (posA < 1) {
            error2 = error2 + "Grupo Sanguinseo\n";
        }

        return error2;

    }

    private void editar() {
        var id_area = getIdArea();
        System.out.println("Area " + id_area);
        var nh = 0; // numero de hijos
        if (TXT_N_HIJOS.getText() == null || "".equals(TXT_N_HIJOS.getText())) {
            TXT_N_HIJOS.setText("0");
        }
        nh = Integer.parseInt(TXT_N_HIJOS.getText());
        var objP = new Persona(TXT_CEDULA.getText(), TXT_NOMBRE.getText(),
                TXT_APELLIDO.getText(),
                fecha_nac,
                TXT_L_NACIMIENTO.getText(), nh, direccion.getText(), telefono.getText(), telefono_emergencia.getText(),
                getIdAGrupoSanguineo(), getIdEstadoCivil(), getIdArea(), objU.getUsuario(), rutaimagen,
                (Date) FechaComponente.FechaSql(), "A");
        // Problemas con fecha de nacikiento
        System.out.println("Prueba grabar " + objP.toString());
        grb_objP = objP;
        var res = crudP.update(grb_objP);
        JOptionPane.showMessageDialog(null, res, "Datos Actualizados", JOptionPane.INFORMATION_MESSAGE);

    }
    private void FOTOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FOTOActionPerformed
        guardarImagen();
    }//GEN-LAST:event_FOTOActionPerformed

    private void TXT_CEDULAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_CEDULAKeyTyped
        char car = evt.getKeyChar();
        if (TXT_CEDULA.getText().length() >= 10) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_TXT_CEDULAKeyTyped

    private void TXT_CEDULAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_CEDULAKeyReleased
        //validarId();
    }//GEN-LAST:event_TXT_CEDULAKeyReleased

    private void TXT_CEDULAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_CEDULAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_CEDULAActionPerformed

    private void telefono_emergenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefono_emergenciaKeyTyped
        char car = evt.getKeyChar();
        if (telefono_emergencia.getText().length() == 12) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_telefono_emergenciaKeyTyped

    private void telefono_emergenciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefono_emergenciaKeyReleased
        validar();
    }//GEN-LAST:event_telefono_emergenciaKeyReleased

    private void telefono_emergenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefono_emergenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telefono_emergenciaActionPerformed

    private void TXT_N_HIJOSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_N_HIJOSKeyTyped

        char car = evt.getKeyChar();
        if (TXT_N_HIJOS.getText().length() == 2) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_TXT_N_HIJOSKeyTyped

    private void TXT_N_HIJOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_N_HIJOSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_N_HIJOSActionPerformed

    private void TXT_L_NACIMIENTOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_L_NACIMIENTOKeyTyped

    }//GEN-LAST:event_TXT_L_NACIMIENTOKeyTyped

    private void TXT_L_NACIMIENTOKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_L_NACIMIENTOKeyReleased
        String texto = TXT_L_NACIMIENTO.getText();
        String texto_corregido = texto.replaceAll("^\\s+", "");
        TXT_L_NACIMIENTO.setText(texto_corregido);
    }//GEN-LAST:event_TXT_L_NACIMIENTOKeyReleased

    private void TXT_L_NACIMIENTOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_L_NACIMIENTOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_L_NACIMIENTOActionPerformed

    private void TXT_F_NACIMIENTOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_F_NACIMIENTOKeyTyped

    }//GEN-LAST:event_TXT_F_NACIMIENTOKeyTyped

    private void TXT_APELLIDOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_APELLIDOKeyTyped
//        var var2 = validateString(TXT_APELLIDO.getText());
//        if (var2 == null) {
//            err.append("FUNCIONAL");
//            int c = 0;
//            c++;
//        } else {
//            String var1 = null;
//            TXT_APELLIDO.setText(var1);
//            System.out.println(err.toString());
//
//        }
    }//GEN-LAST:event_TXT_APELLIDOKeyTyped

    private void TXT_APELLIDOKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_APELLIDOKeyReleased
        String texto = TXT_APELLIDO.getText();
        String texto_corregido = texto.replaceAll("^\\s+", "");
        TXT_APELLIDO.setText(texto_corregido);

    }//GEN-LAST:event_TXT_APELLIDOKeyReleased

    private void TXT_APELLIDOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_APELLIDOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_APELLIDOActionPerformed

    private void TXT_NOMBREKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_NOMBREKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_NOMBREKeyTyped

    private void TXT_NOMBREKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_NOMBREKeyReleased
        String texto = TXT_NOMBRE.getText();
        String texto_corregido = texto.replaceAll("^\\s+", "");
        TXT_NOMBRE.setText(texto_corregido);
    }//GEN-LAST:event_TXT_NOMBREKeyReleased

    private void TXT_NOMBREKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_NOMBREKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_NOMBREKeyPressed

    private void TXT_NOMBREActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_NOMBREActionPerformed

    }//GEN-LAST:event_TXT_NOMBREActionPerformed

    private void direccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_direccionKeyReleased
        String texto = direccion.getText();
        String texto_corregido = texto.replaceAll("^\\s+", "");
        direccion.setText(texto_corregido);
    }//GEN-LAST:event_direccionKeyReleased

    private void TXT_A_P_FAMILIARESKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_A_P_FAMILIARESKeyReleased
        String texto = TXT_A_P_FAMILIARES.getText();
        String texto_corregido = texto.replaceAll("^\\s+", "");
        TXT_A_P_FAMILIARES.setText(texto_corregido);
    }//GEN-LAST:event_TXT_A_P_FAMILIARESKeyReleased

    private void TXT_A_P_PERSONALESKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_A_P_PERSONALESKeyReleased
        String texto = TXT_A_P_PERSONALES.getText();
        String texto_corregido = texto.replaceAll("^\\s+", "");
        TXT_A_P_PERSONALES.setText(texto_corregido);
    }//GEN-LAST:event_TXT_A_P_PERSONALESKeyReleased

    private void TXT_HABITOSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_HABITOSKeyReleased
        String texto = TXT_HABITOS.getText();
        String texto_corregido = texto.replaceAll("^\\s+", "");
        TXT_HABITOS.setText(texto_corregido);
    }//GEN-LAST:event_TXT_HABITOSKeyReleased

    private void TXT_E_ACTUALKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_E_ACTUALKeyReleased
        String texto = TXT_E_ACTUAL.getText();
        String texto_corregido = texto.replaceAll("^\\s+", "");
        TXT_E_ACTUAL.setText(texto_corregido);
    }//GEN-LAST:event_TXT_E_ACTUALKeyReleased

    private void btn_correoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_correoActionPerformed
        new NewMail(new JFrame(), true, TXT_CEDULA.getText(), this.objU).setVisible(true);
        fillCorreo();
    }//GEN-LAST:event_btn_correoActionPerformed

    private void DIASTOLICAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DIASTOLICAKeyTyped
        char car = evt.getKeyChar();
        if (DIASTOLICA.getText().length() == 3) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_DIASTOLICAKeyTyped

    private void DIASTOLICAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DIASTOLICAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DIASTOLICAActionPerformed

    private void IMCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IMCKeyTyped

    }//GEN-LAST:event_IMCKeyTyped

    private void IMCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IMCKeyReleased

    }//GEN-LAST:event_IMCKeyReleased

    private void IMCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IMCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IMCActionPerformed

    private void ESTATURAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ESTATURAKeyTyped
        char car = evt.getKeyChar();
        if (ESTATURA.getText().length() == 4) {
            evt.consume();
        }
        if ((car < '0' || car > '9') && car != '.' && car != ',') {
            evt.consume(); // Ignorar el carácter no permitido
        }
        if ((car == '.' || car == ',') && ESTATURA.getText().contains(".")) {
            evt.consume(); // Ignorar si ya hay un punto
        }
        if ((car == '.' || car == ',') && ESTATURA.getText().contains(",")) {
            evt.consume(); // Ignorar si ya hay una coma
        }
    }//GEN-LAST:event_ESTATURAKeyTyped

    private void ESTATURAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ESTATURAKeyReleased
        calcular();
    }//GEN-LAST:event_ESTATURAKeyReleased

    private void ESTATURAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ESTATURAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ESTATURAActionPerformed

    private void TEMPERATURAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TEMPERATURAKeyTyped
        char car = evt.getKeyChar();
        if (TEMPERATURA.getText().length() == 2) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_TEMPERATURAKeyTyped

    private void TEMPERATURAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TEMPERATURAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TEMPERATURAActionPerformed

    private void SATURACIONKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SATURACIONKeyTyped
        char car = evt.getKeyChar();
        if (SATURACION.getText().length() == 3) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_SATURACIONKeyTyped

    private void SATURACIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SATURACIONActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SATURACIONActionPerformed

    private void SISTOLICAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SISTOLICAKeyTyped
        char car = evt.getKeyChar();
        if (SISTOLICA.getText().length() == 3) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_SISTOLICAKeyTyped

    private void SISTOLICAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SISTOLICAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SISTOLICAActionPerformed

    private void FRECUENCIA_CARDIACAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FRECUENCIA_CARDIACAKeyTyped
        char car = evt.getKeyChar();
        if (FRECUENCIA_CARDIACA.getText().length() == 2) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
    }//GEN-LAST:event_FRECUENCIA_CARDIACAKeyTyped
    }
    private void FRECUENCIA_CARDIACAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FRECUENCIA_CARDIACAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FRECUENCIA_CARDIACAActionPerformed

    private void PESOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PESOKeyTyped
        char car = evt.getKeyChar();
        if (PESO.getText().length() == 5) {
            evt.consume();
        }
        if ((car < '0' || car > '9') && car != '.' && car != ',') {
            evt.consume(); // Ignorar el carácter no permitido
        }
        if ((car == '.' || car == ',') && PESO.getText().contains(".")) {
            evt.consume(); // Ignorar si ya hay un punto
        }
        if ((car == '.' || car == ',') && PESO.getText().contains(",")) {
            evt.consume(); // Ignorar si ya hay una coma
        }
    }//GEN-LAST:event_PESOKeyTyped

    private void PESOKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PESOKeyReleased
        calcular();
    }//GEN-LAST:event_PESOKeyReleased

    private void PESOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PESOActionPerformed

    }//GEN-LAST:event_PESOActionPerformed

    private void CON_FISKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CON_FISKeyReleased
        String texto = CON_FIS.getText();
        String texto_corregido = texto.replaceAll("^\\s+", "");
        CON_FIS.setText(texto_corregido);
    }//GEN-LAST:event_CON_FISKeyReleased

    private void TXT_CEDULAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXT_CEDULAFocusLost

    }//GEN-LAST:event_TXT_CEDULAFocusLost

    private void SATURACIONKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SATURACIONKeyReleased
        var sat = Integer.parseInt(SATURACION.getText());
        if (sat > 100) {
            SATURACION.setText("0");
        }
    }//GEN-LAST:event_SATURACIONKeyReleased

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telefonoActionPerformed

    private void telefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefonoKeyReleased
        validar();
    }//GEN-LAST:event_telefonoKeyReleased

    private void telefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefonoKeyTyped
        char car = evt.getKeyChar();
        if (telefono.getText().length() == 12) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_telefonoKeyTyped

    private void areaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_areaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_areaActionPerformed

    private void btn_guarda_fichaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guarda_fichaActionPerformed
        validarGrabar();
    }//GEN-LAST:event_btn_guarda_fichaActionPerformed

    private void validarGrabar() {
        var men = "";
        String vali = validarCamposAntecedentes();
        String vali2 = validarCamposExamenes();

        // Validación de antecedentes
        if (vali.length() > 0) {
            activarAntededentes(true);
            men = men + "Antecedentes Inválidos..\n";
        } else {
            grabarAntecedentes();
        }

        // Validación de exámenes
        if (vali2.length() > 0) {
            men = men + vali2 + "\nExámenes Inválidos";
        } else {
            grabarExamenes();
        }
        if (vali.length() == 0 && vali2.length() == 0) {
//            if (gbr_ficha_examen == 0) {
//                men = saveData();
//                gbr_ficha_examen = 1;
//            } else {
            men = updateData();
//            }
        }
        JOptionPane.showMessageDialog(null, men, "Validación", JOptionPane.INFORMATION_MESSAGE);
    }

//    private void validarGrabar() {
//        var vali = validarCamposAntecedentes();
//        if (vali.length() > 1) {
//            activarAntededentes(true);
//            JOptionPane.showMessageDialog(null, vali, "Antecedentes Invalidos", JOptionPane.INFORMATION_MESSAGE);
//        } else {
//            activarAntededentes(false);
//            //btn_validar_antecedentes.setEnabled(false);
//            JOptionPane.showMessageDialog(null, vali, "Antecedentes validados", JOptionPane.INFORMATION_MESSAGE);
//            // Dialogo.Mensaje("Validado", 90);
//            grabarAntecedentes();
//            var vali2 = validarCamposExamenes();
//            if (vali2.length() > 1) {
//                JOptionPane.showMessageDialog(null, vali2, "Datos Invalidos", JOptionPane.INFORMATION_MESSAGE);
//            } else {
//                gbr_ficha_examen++;
//                grabarExamenes();
//                if (gbr_ficha_examen == 1) {
//                    saveData();
//                }
//                if (gbr_ficha_examen > 1) {
//                  updateData();
//                }
//                //hgjhg
//
//                // borrarDatos();
//            }
//        }
//
//    }

    private void FRECUENCIA_CARDIACAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FRECUENCIA_CARDIACAKeyReleased
        try {

            int frecuencia = Integer.parseInt(FRECUENCIA_CARDIACA.getText());
            lblresultado1.setVisible(true);

            if (frecuencia > 80) {
                lblresultado1.setText("Taquicardia");
                lblresultado1.setOpaque(true);
                lblresultado1.setForeground(Color.RED);
            } else if (frecuencia < 70) {
                lblresultado1.setText("Bradicardia");
                lblresultado1.setOpaque(true);
                lblresultado1.setForeground(Color.ORANGE);
            } else {
                lblresultado1.setText("Normal");
                lblresultado1.setOpaque(true);
                lblresultado1.setForeground(Color.BLACK);
            }

            lblresultado1.setOpaque(true);

        } catch (NumberFormatException e) {
            lblresultado1.setVisible(false);
        }

    }//GEN-LAST:event_FRECUENCIA_CARDIACAKeyReleased

    private void DIASTOLICAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DIASTOLICAKeyReleased
        try {
            String textoSistolica = SISTOLICA.getText().trim();
            String textoDiastolica = DIASTOLICA.getText().trim();

            int sistolica = Integer.parseInt(textoSistolica);
            int diastolica = Integer.parseInt(textoDiastolica);

            lblresultado3.setVisible(true);

            if (sistolica > 120 || diastolica > 80) {
                lblresultado3.setText("Hipertensión");
                lblresultado3.setOpaque(true);
                lblresultado3.setForeground(Color.RED);

            } else if (sistolica < 90 || diastolica < 60) {
                lblresultado3.setText("Hipotensión");
                lblresultado3.setOpaque(true);
                lblresultado3.setForeground(Color.YELLOW);
            } else {
                lblresultado3.setText("Normal");
                lblresultado3.setOpaque(true);
                lblresultado3.setForeground(Color.BLACK);
            }

            // Mostrar el resultado
            lblresultado3.setOpaque(true);
        } catch (NumberFormatException ex) {
            lblresultado3.setVisible(false);
        }
//gdsfgdfgfdgdfgdf
    }//GEN-LAST:event_DIASTOLICAKeyReleased
    private void getOperationIMC() {
        try {
            String textoSistolica = SISTOLICA.getText().trim();
            String textoDiastolica = DIASTOLICA.getText().trim();

            int sistolica = Integer.parseInt(textoSistolica);
            int diastolica = Integer.parseInt(textoDiastolica);

            lblresultado3.setVisible(true);

            if (sistolica > 120 || diastolica > 80) {
                lblresultado3.setText("Hipertensión");
                lblresultado3.setOpaque(true);
                lblresultado3.setForeground(Color.RED);

            } else if (sistolica < 90 || diastolica < 60) {
                lblresultado3.setText("Hipotensión");
                lblresultado3.setOpaque(true);
                lblresultado3.setForeground(Color.YELLOW);
            } else {
                lblresultado3.setText("Normal");
                lblresultado3.setOpaque(true);
                lblresultado3.setForeground(Color.BLACK);
            }

            // Mostrar el resultado
            lblresultado3.setOpaque(true);
        } catch (NumberFormatException ex) {
            lblresultado3.setVisible(false);
        }
    }
    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        crudP.delete(TXT_CEDULA.getText());
        crudFM.deleteByIdPerson(TXT_CEDULA.getText());
        JOptionPane.showMessageDialog(null, "Datos eliminados", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        btn_validar_datos.setEnabled(false);
        btn_eliminar.setEnabled(false);
        btn_guarda_ficha.setEnabled(false);
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void grabarExamenes() {
        vr_frecuencia_cardiaca = Integer.parseInt(FRECUENCIA_CARDIACA.getText());
        vr_sistolica = Integer.parseInt(SISTOLICA.getText());
        vr_diastolica = Integer.parseInt(DIASTOLICA.getText());
        vr_saturacion = Integer.parseInt(SATURACION.getText());
        vr_peso = Double.valueOf(PESO.getText());
        vr_estatura = Double.valueOf(ESTATURA.getText());
        vr_temperatura = Double.valueOf(TEMPERATURA.getText());
        Double.valueOf(IMC.getText());
        vr_estado_actual = TXT_E_ACTUAL.getText();
        vr_habitos = TXT_HABITOS.getText();
        System.out.println(frk_id_examen_upd + " PRUEBA GRABA 1 :" + frk_id_ficha_medica_grb);
        System.out.println("IMC " + vr_imc);
        Examen objE = new Examen(TXT_CEDULA.getText(), frk_id_ficha_medica_grb, (Date) FechaComponente.FechaSql(),
                vr_frecuencia_cardiaca, vr_sistolica, vr_diastolica,
                vr_saturacion, vr_peso, vr_estatura, vr_temperatura,
                vr_imc, vr_estado_actual, vr_habitos, CON_FIS.getText(), "A");
        // Problemas con fecha de nacikiento
        System.out.println("Prueba grabar " + objE.toString());
        grb_objE = objE;
    }

    private String validarCamposExamenes() {
        var error2 = "";
        ///
        if (TEMPERATURA.getText().length() < 1) {
            error2 = error2 + "Temperatura\n";
        }

        if (PESO.getText().length() < 1) {
            error2 = error2 + "Peso\n";
        }

        if (SATURACION.getText().length() < 1) {
            error2 = error2 + "Saturacion\n";
        }
        if (ESTATURA.getText().length() < 1) {
            error2 = error2 + "Estatura\n";
        }

        if (SISTOLICA.getText().length() < 1) {
            error2 = error2 + "Sistolica\n";
        }
        if (DIASTOLICA.getText().length() < 1) {
            error2 = error2 + "Diastolica\n";
        }
        if (IMC.getText().length() < 1) {
            error2 = error2 + "IMC\n";
        }
        if (FRECUENCIA_CARDIACA.getText().length() < 1) {
            error2 = error2 + "Frecuencia Cardioca\n";
        }
        if (CON_FIS.getText().length() < 1) {
            error2 = error2 + "Condiciones Fisicas\n";
        }
        return error2;

    }

    private void calcular() {
        try {

            var peso = Double.parseDouble(PESO.getText());
            var estatura = Double.parseDouble(ESTATURA.getText());
            var rs = Calculos.getImc(peso, estatura);

            double imcRedondeado = Math.round(rs * 100.0) / 100.0;
            IMC.setText("" + imcRedondeado);
            vr_imc = imcRedondeado;
            lblResultado.setVisible(true);

            if (imcRedondeado < 18.5) {
                lblResultado.setText("Bajo peso");
                lblResultado.setOpaque(true);
                lblResultado.setForeground(Color.YELLOW);
            } else if (imcRedondeado < 25) {
                lblResultado.setText("Peso normal");
                lblResultado.setOpaque(true);
                lblResultado.setForeground(Color.BLACK);
            } else if (imcRedondeado < 30) {
                lblResultado.setText("Sobre peso");
                lblResultado.setOpaque(true);
                lblResultado.setForeground(Color.ORANGE);
            } else {
                lblResultado.setText("Obesidad");
                lblResultado.setOpaque(true);
                lblResultado.setForeground(Color.RED);
            }

            lblResultado.setOpaque(true);

        } catch (NumberFormatException e) {
            lblResultado.setVisible(false);
        }
    }

    private void guardarImagen() {
        String pass = "\\Fichas_Medicas\\fotos\\";
        //String pass = "\\Users\\TapiaSoftware\\Documents\\NetBeansProjects\\HermanosJesus\\src\\pictures\\";
        JFileChooser dlg = new JFileChooser(pass);
        dlg.setFileFilter(new FileNameExtensionFilter("Archivos de imagen", "tif", "jpg", "jpeg", "png", "gif"));
        int opcion = dlg.showOpenDialog(this);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            String fil = dlg.getSelectedFile().getPath();
            foto.setIcon(new ImageIcon(fil));
            ImageIcon icon = new ImageIcon(fil);
            Image img = icon.getImage();
            Image newimg = img.getScaledInstance(350, 299, java.awt.Image.SCALE_SMOOTH);
            ImageIcon newIcono = new ImageIcon(newimg);
            foto.setIcon(newIcono);
            rutaimagen = dlg.getSelectedFile().getPath();
            rutaimagen = FechaComponente.devuelvePathMsql(dlg.getSelectedFile().getPath());
            System.out.println("ruta " + rutaimagen);
            System.out.println(fil + " Foto  " + foto.getWidth() + " " + foto.getHeight());
        }

    }

    private void guardarImagen1() {
        // Definir la ruta inicial
        String pass = "C://Fichas_Medicas//fotos//"; // Asegúrate de usar barras invertidas en Windows

        // Crear un JFileChooser con filtro de extensiones válidas
        JFileChooser dlg = new JFileChooser(pass);
        dlg.setFileFilter(new FileNameExtensionFilter("Archivos de imagen", "jpg", "jpeg", "png", "gif"));

        // Mostrar el diálogo de apertura de archivo
        int opcion = dlg.showOpenDialog(this);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            try {
                // Obtener el archivo seleccionado
                String fil = dlg.getSelectedFile().getPath();

                // Usar ImageIO para cargar la imagen
                File archivoImagen = new File(fil);
                Image img = ImageIO.read(archivoImagen);  // Cargar la imagen usando ImageIO
                if (img != null) {
                    // Redimensionar la imagen
                    Image newimg = img.getScaledInstance(350, 299, Image.SCALE_SMOOTH);
                    ImageIcon newIcono = new ImageIcon(newimg);
                    foto.setIcon(newIcono);  // Establecer la imagen redimensionada en el componente

                    // Guardar la ruta de la imagen seleccionada
                    rutaimagen = fil;
                    rutaimagen = FechaComponente.devuelvePathMsql(rutaimagen);  // Procesar la ruta para MySQL (si es necesario)

                    // Imprimir información de la imagen
                    System.out.println("Ruta de la imagen: " + rutaimagen);
                    System.out.println(fil + " Foto  " + foto.getWidth() + " " + foto.getHeight());
                } else {
                    System.out.println("No se pudo cargar la imagen.");
                }
            } catch (IOException ex) {
                Logger.getLogger(EditarFichas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private String validar() {
        int c = 0;
        StringBuilder err = new StringBuilder();

        var var5 = validateString(TXT_N_HIJOS.getText());
        if (var5 == null) {
            err.append(" cedula");
            c++;
        } else {
            TXT_N_HIJOS.setText(var1);
        }

        System.out.println(err.toString());
        return err.toString();
    }

    private void validarId() {
        if (TXT_CEDULA.getText().length() == 10) {
            var per = crudP.getOne(TXT_CEDULA.getText());
            if (per != null) {
                activar(false);
                JOptionPane.showMessageDialog(null, "Cedula ya Existe!");
            } else {
                System.out.println("Cedula disponible!");
                activar(true);
            }
        } else {
            activar(false);
        }

    }

    private void activar(boolean valor) {

        TXT_NOMBRE.setEditable(valor);
        TXT_APELLIDO.setEditable(valor);
        area.setEnabled(valor);
        //TXT_F_NACIMIENTO.setEditable(valor);
        TXT_L_NACIMIENTO.setEditable(valor);
        estado_civil.setEnabled(valor);
        grupito.setEnabled(valor);
        TXT_N_HIJOS.setEditable(valor);
        correo.setEnabled(valor);
        FOTO.setEnabled(valor);
        direccion.setEditable(valor);
        telefono_emergencia.setEditable(valor);
        //antecedentes.setEnabledAt(1, valor);
        //antecedentes.setEnabledAt(2, valor);
        btn_correo.setEnabled(valor);
        btn_cal.setEnabled(valor);
        btn_validar_datos.setEnabled(valor);
    }

    private String saveData() {
        var msg = "Datos de ficha medica guardada....";
        crudFM.save(grb_objF);
        frk_id_ficha_medica_grb = crudFM.getId_ficha_medica();
        grb_objE.setId_ficha_medica(frk_id_ficha_medica_grb);
        crudEx.save(grb_objE);
        frk_id_examen_upd = crudEx.getId_examen();
        //  System.out.println(frk_id_examen_upd + " grabacion edicion en tabla EXAMEN: " + frk_id_ficha_medica_grb);
        lista_fichas = crudFM.getAllTabSummary(TXT_CEDULA.getText());
        tblF.getTabSummary(lista_fichas, tabla);
        return msg;
    }

    private String updateData() {
        //System.out.println("ACTUALIZAR FICHA MEDICA ID: " + frk_id_ficha_medica_grb);
        grb_objF.setId_fichaMedica(frk_id_ficha_medica_grb);
        crudFM.update(grb_objF);  // Actualiza Ficha Médica (DATOS + ANTECEDENTES)
        grb_objE.setId_ficha_medica(frk_id_ficha_medica_grb);
        grb_objE.setIdExamen(frk_id_examen_upd);
        //System.out.println("EDITADO  " + grb_objE.getIdExamen());
        crudEx.update(grb_objE); // Actualiza EXAMEN
        String msg = "Datos de ficha medica actualizados....";
        // Refrescar tabla con los datos actualizados
        lista_fichas = crudFM.getAllTabSummary(TXT_CEDULA.getText());
        tblF.getTabSummary(lista_fichas, tabla);
        return msg;
    }

//     private void updateData() {
//        var res = false;
//        var msg = "";
////        if (!"Datos guardados...".equals(res)) {
////            msg = "Falta llenar campos en la seccion de DATOS.\n";
////        }
//        res = crudFM.update(grb_objF);
//       // frk_id_ficha_medica_grb = crudFM.getId_ficha_medica();
//       // System.out.println("grabacion en tabla EXAMEN: " + frk_id_ficha_medica_grb);
//        if (!res) {
//            msg = msg + "Falta llenar campos en la seccion de ANTECEDENTES.\n";
//        }
//       // grb_objE.setId_ficha_medica(frk_id_ficha_medica_grb);
//        var res1 = crudEx.update(grb_objE);
//        if (res1 == false) {
//            msg = msg + "Falta llenar campos en la seccion de EXAMENES.\n";
//        }
//         if (res==true  && res1==true) {
//             msg="Datos han sido actualixados!";
//         }else{
//              msg="Verifique que todos los compos esten correctos....";
//         }
//        lista_fichas = crudFM.getAllTabSummary(TXT_CEDULA.getText());
//        tblF.getTabSummary(lista_fichas, tabla);
//        JOptionPane.showMessageDialog(null, msg);
//    }
    private void borrarDatos() {
        TXT_CEDULA.setText("");
        TXT_NOMBRE.setText("");
        TXT_APELLIDO.setText("");
        TXT_F_NACIMIENTO.setText("");
        TXT_L_NACIMIENTO.setText("");
        TXT_N_HIJOS.setText("");
        // foto
        direccion.setText("");
        telefono_emergencia.setText("");
        area.setSelectedIndex(0);
        estado_civil.setSelectedIndex(0);
        grupito.setSelectedIndex(0);
        cargarImagen();
        // antecedentes
        TXT_A_P_FAMILIARES.setText("");
        TXT_A_P_PERSONALES.setText("");
        TXT_HABITOS.setText("");
        TXT_E_ACTUAL.setText("");
        // 
        TEMPERATURA.setText("");
        PESO.setText("");
        SATURACION.setText("");
        ESTATURA.setText("");
        SISTOLICA.setText("");
        DIASTOLICA.setText("");
        IMC.setText("");
        FRECUENCIA_CARDIACA.setText("");
        CON_FIS.setText("");
        activar(true);
        activarAntededentes(true);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EditarFichas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarFichas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarFichas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarFichas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            EditarFichas dialog = new EditarFichas(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea CON_FIS;
    private javax.swing.JTextField DIASTOLICA;
    private javax.swing.JLabel EMERGENCIA;
    private javax.swing.JTextField ESTATURA;
    private javax.swing.JButton FOTO;
    private javax.swing.JTextField FRECUENCIA_CARDIACA;
    private javax.swing.JTextField F_CARDIACA;
    private javax.swing.JTextField F_CARDIACA1;
    private javax.swing.JTextField F_CARDIACA10;
    private javax.swing.JTextField F_CARDIACA11;
    private javax.swing.JTextField F_CARDIACA12;
    private javax.swing.JTextField F_CARDIACA13;
    private javax.swing.JTextField F_CARDIACA2;
    private javax.swing.JTextField F_CARDIACA3;
    private javax.swing.JTextField F_CARDIACA4;
    private javax.swing.JTextField F_CARDIACA5;
    private javax.swing.JTextField F_CARDIACA6;
    private javax.swing.JTextField F_CARDIACA7;
    private javax.swing.JTextField F_CARDIACA8;
    private javax.swing.JTextField F_CARDIACA9;
    private javax.swing.JTextField IMC;
    private javax.swing.JTextField PESO;
    private javax.swing.JTextField SATURACION;
    private javax.swing.JTextField SISTOLICA;
    private javax.swing.JTextField TEMPERATURA;
    private javax.swing.JTextField TXT_APELLIDO;
    private javax.swing.JTextField TXT_A_P_FAMILIARES;
    private javax.swing.JTextField TXT_A_P_PERSONALES;
    private javax.swing.JTextField TXT_CEDULA;
    private javax.swing.JTextField TXT_E_ACTUAL;
    private javax.swing.JTextField TXT_F_NACIMIENTO;
    private javax.swing.JTextField TXT_HABITOS;
    private javax.swing.JTextField TXT_L_NACIMIENTO;
    private javax.swing.JTextField TXT_NOMBRE;
    private javax.swing.JTextField TXT_N_HIJOS;
    private javax.swing.JTabbedPane antecedentes;
    private javax.swing.JComboBox<String> area;
    private javax.swing.JButton btn_cal;
    private javax.swing.JButton btn_correo;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_guarda_ficha;
    private javax.swing.JButton btn_validar_datos;
    private javax.swing.JComboBox<String> correo;
    private javax.swing.JLabel diastolicatxt;
    private javax.swing.JTextArea direccion;
    private javax.swing.JComboBox<String> estado_civil;
    private javax.swing.JLabel foto;
    private javax.swing.JLabel g_sanguineo;
    private javax.swing.JComboBox<String> grupito;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblResultado;
    private javax.swing.JLabel lbl_altura;
    private javax.swing.JLabel lbl_condi_fisicas;
    private javax.swing.JLabel lbl_fre_cardiaca;
    private javax.swing.JLabel lbl_imc;
    private javax.swing.JLabel lbl_peso;
    private javax.swing.JLabel lbl_pre_arterial;
    private javax.swing.JLabel lbl_saturacion;
    private javax.swing.JLabel lbl_temperatura;
    private javax.swing.JLabel lblresultado1;
    private javax.swing.JLabel lblresultado3;
    private javax.swing.JTextField muestra_fecha;
    private javax.swing.JLabel sistolicatxt;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField telefono;
    private javax.swing.JTextField telefono_emergencia;
    // End of variables declaration//GEN-END:variables
}
