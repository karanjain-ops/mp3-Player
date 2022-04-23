package Mp3player.pk;
import jaco.mp3.player.MP3Player;
import java.awt.Color;
import static java.awt.Color.black;
import static java.awt.Color.white;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Majey
 */
public class PlayerFrame extends javax.swing.JFrame {

    public static int count;
    // Define MP3Player Class From JACO MP3Player Lib
    MP3Player player;
    // Define File For Song
    File songFile;
    // Define Current Directory Like If We Use JFileChooser then where it statys.
    String currentDirectory = "home.user"; // I am using home.user this will call file chooser in user documents.
    // Here I define currentPath of the running app class
    String currentPath;
    // This String Will Be For Image Name or Path
    String imagePath;
    // We need to set app title as string
    String appName = "BEATS - (MP3 Player)";

    // Now Check If Repeat Button is Enabled or not
    boolean repeat = false;

    // Here I am making a boolean for windowCollapsed
    boolean windowCollapsed = false;

    // Here I need to define mouse x and y for to get mouse position on screen.
    int xMouse, yMouse;
    int theme = 1;
    int playing = 1;
    double duration;
    int i;
    String email=Login_Page.em;
    String username=Login_Page.un;
   
    boolean filechosen = false;
    private String getLyrics(String song) throws IOException {
        song = song.replaceAll(" ", "+").replace(".mp3","").toLowerCase();
        String initialurl = "http://search.azlyrics.com/search.php?q="+song;
        System.out.println(initialurl);
        Document site = null;
         try {
            site=org.jsoup.Jsoup.connect(initialurl).get();
        } catch (IOException e) {
            return "Cannot connect to Internet";
        }
        Elements lyricsTable = site.select("div.panel");
        ArrayList<String> songNames = new ArrayList<>();
        ArrayList<String> urls = new ArrayList<>();
        if (lyricsTable.isEmpty()){
            return "Lyrics not Found";
        }
        else{
        for (Element elm : lyricsTable){
            if (elm.text().contains("Album")) {
                continue;
            }
            //System.out.println(elm);
            Elements table = elm.select("table > tbody > tr");
            for (Element elms : table) {
                if (elms.text().contains("More Song Results")) {
                    continue;
                }
                elms.select("small").html("");
                songNames.add(elms.text());
                urls.add(elms.select("a").attr("href"));

            }
        }
        for (int j = 0; j < urls.size(); j++) {
            lrcA.append(songNames.get(j)+"\n");
        }
        UIManager.put("OptionPane.background",new Color(51, 51, 51));
        String choiceS= JOptionPane.showInputDialog("Choose an Option");
        int choice=Integer.parseInt(choiceS);
        choice--;
        Document lyricPage = org.jsoup.Jsoup.connect(urls.get(choice)).get();
        Elements lyricTags = lyricPage.select("div[class='col-xs-12 col-lg-8 text-center']>div");
        String lyrics = " ";
        for (Element elm : lyricTags) {
            if(elm.attr("class").equals("div-share noprint")||elm.attr("class").equals("collapse noprint")||elm.attr("class").equals("panel album-panel noprint")||elm.attr("class").equals("noprint")||elm.attr("class").equals("smt")||elm.attr("class").equals("hidden")||elm.attr("class").equals("smt noprint")||elm.attr("class").equals("div-share")||elm.attr("class").equals("lyricsh")||elm.attr("class").equals("ringtone")) {
                continue;
            }
            lyrics = elm.text();
            break;
        }
        return lyrics;
    }
    }

    
    public PlayerFrame() {
        initComponents();
        // Set App Title Here
        appTitle.setText(appName);
        // Here I am going to set a default song file. It's not necessary.
        songFile = new File("E:\\New-Data-From-Sony\\Music-Downloads\\PlayMusic.mp3");
        // Now make a string to get file name
        String fileName = songFile.getName();
        // Here set our song name label with this name
        songNameDisplay.setText(fileName);
        System.out.println("2"+email+" "+username);
        // Add our method to player variable
        player = mp3Player();
        // Now add song to player as playlist.
        player.addToPlayList(songFile);

        // Here get current path and images path in strings
        currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
        imagePath = "C:\\Users\\class12.DCCHIREC\\Downloads\\mp3-Player(1)\\mp3-Player\\src\\Mp3player\\pk\\images\\";

        this.setIconImage(new ImageIcon(getClass().getResource("logoD.PNG")).getImage());

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        settings = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        SettingTitle = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        profile = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        SettingTitle1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel15 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        appTitle = new javax.swing.JLabel();
        quitBtn = new javax.swing.JLabel();
        settingsBtn = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        minBtn = new javax.swing.JLabel();
        songNameMainPanel = new javax.swing.JPanel();
        songNameSubPanel = new javax.swing.JPanel();
        songNameDisplay = new javax.swing.JLabel();
        controlPanel = new javax.swing.JPanel();
        repeatBtn = new javax.swing.JLabel();
        pauseBtn = new javax.swing.JLabel();
        playBtn = new javax.swing.JLabel();
        stopBtn = new javax.swing.JLabel();
        openBtn = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        volumeDownBtn = new javax.swing.JLabel();
        volumeUpBtn = new javax.swing.JLabel();
        volumeFullBtn = new javax.swing.JLabel();
        muteBtn = new javax.swing.JLabel();
        Pic = new javax.swing.JPanel();
        plypic = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        lrcP = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lrcA = new javax.swing.JTextArea();
        lrcL = new javax.swing.JLabel();

        settings.setMinimumSize(new java.awt.Dimension(258, 304));
        settings.setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(79, 176, 147), 3));
        jPanel1.setForeground(new java.awt.Color(79, 176, 147));
        jPanel1.setRequestFocusEnabled(false);

        SettingTitle.setFont(new java.awt.Font("Trebuchet MS", 1, 15)); // NOI18N
        SettingTitle.setForeground(new java.awt.Color(79, 176, 147));
        SettingTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        SettingTitle.setText("SETTINGS");

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Theme:");

        jComboBox1.setBackground(new java.awt.Color(0, 0, 0));
        jComboBox1.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dark", "White" }));
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(79, 176, 147));
        jButton1.setText("Done");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });
        jPanel2.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Terms and Conditions");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(0, 0, 160, 15);

        jLabel3.setFont(new java.awt.Font("Nirmala UI", 0, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("All the stuff you need to know.");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(0, 10, 135, 23);

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("About");

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel3MousePressed(evt);
            }
        });
        jPanel3.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Privacy Policy");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(0, 0, 110, 15);

        jLabel6.setFont(new java.awt.Font("Nirmala UI", 0, 10)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("Important for both of us.");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(0, 10, 110, 23);

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Display Options");

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 10)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(79, 176, 147));
        jLabel8.setText("LOG OUT");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel8MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel8MousePressed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel4MousePressed(evt);
            }
        });
        jPanel4.setLayout(null);

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Username");
        jPanel4.add(jLabel10);
        jLabel10.setBounds(0, 0, 110, 15);

        jLabel11.setFont(new java.awt.Font("Nirmala UI", 0, 10)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 204, 204));
        jLabel11.setText("View Profile");
        jPanel4.add(jLabel11);
        jLabel11.setBounds(0, 10, 50, 23);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(SettingTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 22, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(96, 96, 96))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(SettingTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout settingsLayout = new javax.swing.GroupLayout(settings.getContentPane());
        settings.getContentPane().setLayout(settingsLayout);
        settingsLayout.setHorizontalGroup(
            settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        settingsLayout.setVerticalGroup(
            settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        profile.setMinimumSize(new java.awt.Dimension(241, 260));
        profile.setUndecorated(true);

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(79, 176, 147), 3));
        jPanel5.setForeground(new java.awt.Color(79, 176, 147));
        jPanel5.setRequestFocusEnabled(false);

        SettingTitle1.setFont(new java.awt.Font("Trebuchet MS", 1, 15)); // NOI18N
        SettingTitle1.setForeground(new java.awt.Color(79, 176, 147));
        SettingTitle1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        SettingTitle1.setText("PROFILE");

        jButton2.setBackground(new java.awt.Color(51, 51, 51));
        jButton2.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(79, 176, 147));
        jButton2.setText("Done");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));
        jPanel6.setLayout(null);

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Username");
        jPanel6.add(jLabel12);
        jLabel12.setBounds(0, 0, 160, 15);

        jTextField1.setEditable(false);
        jTextField1.setEnabled(false);
        jPanel6.add(jTextField1);
        jTextField1.setBounds(0, 20, 190, 20);

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));
        jPanel7.setLayout(null);

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Email");
        jPanel7.add(jLabel13);
        jLabel13.setBounds(0, 0, 160, 15);

        jTextField2.setEditable(false);
        jTextField2.setEnabled(false);
        jPanel7.add(jTextField2);
        jTextField2.setBounds(0, 20, 190, 20);

        jPanel8.setBackground(new java.awt.Color(51, 51, 51));
        jPanel8.setLayout(null);

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Password");
        jPanel8.add(jLabel14);
        jLabel14.setBounds(0, 0, 160, 15);

        jPasswordField1.setEditable(false);
        jPasswordField1.setText("jPasswordField1");
        jPasswordField1.setEchoChar('*');
        jPasswordField1.setEnabled(false);
        jPanel8.add(jPasswordField1);
        jPasswordField1.setBounds(0, 20, 190, 20);

        jLabel15.setFont(new java.awt.Font("Nirmala UI", 0, 10)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 204, 204));
        jLabel15.setText("Change Password");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel15MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel15MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel15MousePressed(evt);
            }
        });
        jPanel8.add(jLabel15);
        jLabel15.setBounds(0, 40, 78, 14);

        jButton3.setBackground(new java.awt.Color(51, 51, 51));
        jButton3.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(79, 176, 147));
        jButton3.setText("Back");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SettingTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(50, 50, 50))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SettingTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addGap(1, 1, 1)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        javax.swing.GroupLayout profileLayout = new javax.swing.GroupLayout(profile.getContentPane());
        profile.getContentPane().setLayout(profileLayout);
        profileLayout.setHorizontalGroup(
            profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profileLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(134, Short.MAX_VALUE))
        );
        profileLayout.setVerticalGroup(
            profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        mainPanel.setBackground(new java.awt.Color(51, 51, 51));
        mainPanel.setForeground(new java.awt.Color(79, 176, 147));
        mainPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        headerPanel.setBackground(new java.awt.Color(51, 51, 51));
        headerPanel.setForeground(new java.awt.Color(102, 255, 153));

        appTitle.setBackground(new java.awt.Color(51, 51, 51));
        appTitle.setFont(new java.awt.Font("Nirmala UI", 1, 24)); // NOI18N
        appTitle.setForeground(new java.awt.Color(79, 176, 147));
        appTitle.setText("BEATS");
        appTitle.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                appTitleMouseDragged(evt);
            }
        });
        appTitle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                appTitleMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                appTitleMousePressed(evt);
            }
        });

        quitBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quitBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mp3player/pk/images/Dark/ExitD.PNG"))); // NOI18N
        quitBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        quitBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quitBtnMouseClicked(evt);
            }
        });

        settingsBtn.setBackground(new java.awt.Color(51, 51, 51));
        settingsBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        settingsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mp3player/pk/images/Dark/SettingsD.PNG"))); // NOI18N
        settingsBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        settingsBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingsBtnMouseClicked(evt);
            }
        });

        logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mp3player/pk/images/Dark/logoD.PNG"))); // NOI18N

        minBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mp3player/pk/images/Dark/minD.PNG"))); // NOI18N
        minBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(appTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(settingsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(minBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(appTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(quitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(settingsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(minBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        songNameMainPanel.setBackground(new java.awt.Color(51, 51, 51));
        songNameMainPanel.setForeground(new java.awt.Color(79, 176, 147));

        songNameSubPanel.setBackground(new java.awt.Color(51, 51, 51));
        songNameSubPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(79, 176, 147), 1, true));
        songNameSubPanel.setForeground(new java.awt.Color(79, 176, 147));

        songNameDisplay.setBackground(new java.awt.Color(51, 51, 51));
        songNameDisplay.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        songNameDisplay.setForeground(new java.awt.Color(79, 176, 147));
        songNameDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        songNameDisplay.setText("PLAYING");

        javax.swing.GroupLayout songNameSubPanelLayout = new javax.swing.GroupLayout(songNameSubPanel);
        songNameSubPanel.setLayout(songNameSubPanelLayout);
        songNameSubPanelLayout.setHorizontalGroup(
            songNameSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(songNameSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(songNameSubPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(songNameDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        songNameSubPanelLayout.setVerticalGroup(
            songNameSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
            .addGroup(songNameSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(songNameDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout songNameMainPanelLayout = new javax.swing.GroupLayout(songNameMainPanel);
        songNameMainPanel.setLayout(songNameMainPanelLayout);
        songNameMainPanelLayout.setHorizontalGroup(
            songNameMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(songNameMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(songNameSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        songNameMainPanelLayout.setVerticalGroup(
            songNameMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(songNameMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(songNameSubPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        controlPanel.setBackground(new java.awt.Color(51, 51, 51));
        controlPanel.setForeground(new java.awt.Color(102, 255, 153));

        repeatBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        repeatBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mp3player/pk/images/Dark/RepeatD.PNG"))); // NOI18N
        repeatBtn.setToolTipText("Repeat");
        repeatBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        repeatBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                repeatBtnMouseClicked(evt);
            }
        });

        pauseBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pauseBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mp3player/pk/images/Dark/PauseD.PNG"))); // NOI18N
        pauseBtn.setToolTipText("Pause");
        pauseBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pauseBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pauseBtnMouseClicked(evt);
            }
        });

        playBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mp3player/pk/images/Dark/PlayD.PNG"))); // NOI18N
        playBtn.setToolTipText("Play");
        playBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        playBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                playBtnMouseClicked(evt);
            }
        });

        stopBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stopBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mp3player/pk/images/Dark/RemaneD.PNG"))); // NOI18N
        stopBtn.setToolTipText("Stop");
        stopBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        stopBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stopBtnMouseClicked(evt);
            }
        });

        openBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        openBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mp3player/pk/images/Dark/MusicfileD.PNG"))); // NOI18N
        openBtn.setToolTipText("Music File");
        openBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        openBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openBtnMouseClicked(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        volumeDownBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        volumeDownBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mp3player/pk/images/Dark/VoldownD.PNG"))); // NOI18N
        volumeDownBtn.setToolTipText("Volume Down");
        volumeDownBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        volumeDownBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                volumeDownBtnMouseClicked(evt);
            }
        });

        volumeUpBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        volumeUpBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mp3player/pk/images/Dark/VolupD.PNG"))); // NOI18N
        volumeUpBtn.setToolTipText("Volume Up");
        volumeUpBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        volumeUpBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                volumeUpBtnMouseClicked(evt);
            }
        });

        volumeFullBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        volumeFullBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mp3player/pk/images/Dark/FullVolD.PNG"))); // NOI18N
        volumeFullBtn.setToolTipText("Full Volume");
        volumeFullBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        volumeFullBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                volumeFullBtnMouseClicked(evt);
            }
        });

        muteBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        muteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mp3player/pk/images/Dark/MuteD.PNG"))); // NOI18N
        muteBtn.setToolTipText("Mute");
        muteBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        muteBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                muteBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(repeatBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pauseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stopBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(openBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(volumeDownBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(volumeUpBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(volumeFullBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(muteBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pauseBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(playBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(openBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(volumeDownBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(volumeUpBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(volumeFullBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(muteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stopBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(repeatBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        Pic.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout PicLayout = new javax.swing.GroupLayout(Pic);
        Pic.setLayout(PicLayout);
        PicLayout.setHorizontalGroup(
            PicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PicLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(plypic, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PicLayout.setVerticalGroup(
            PicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(plypic, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jProgressBar1.setBackground(new java.awt.Color(51, 51, 51));
        jProgressBar1.setForeground(new java.awt.Color(79, 176, 147));
        jProgressBar1.setBorderPainted(false);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(songNameMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Pic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(songNameMainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Pic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        lrcP.setBackground(new java.awt.Color(51, 51, 51));
        lrcP.setToolTipText("");

        lrcA.setEditable(false);
        lrcA.setBackground(new java.awt.Color(51, 51, 51));
        lrcA.setColumns(20);
        lrcA.setFont(new java.awt.Font("Nirmala UI", 0, 13)); // NOI18N
        lrcA.setForeground(new java.awt.Color(79, 176, 147));
        lrcA.setLineWrap(true);
        lrcA.setRows(5);
        lrcA.setToolTipText("");
        lrcA.setWrapStyleWord(true);
        lrcA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(79, 176, 147)));
        lrcA.setFocusable(false);
        jScrollPane1.setViewportView(lrcA);

        lrcL.setFont(new java.awt.Font("Nirmala UI", 1, 13)); // NOI18N
        lrcL.setForeground(new java.awt.Color(79, 176, 147));
        lrcL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lrcL.setText("LYRICS");
        lrcL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(79, 176, 147)));

        javax.swing.GroupLayout lrcPLayout = new javax.swing.GroupLayout(lrcP);
        lrcP.setLayout(lrcPLayout);
        lrcPLayout.setHorizontalGroup(
            lrcPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lrcPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lrcPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(lrcL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        lrcPLayout.setVerticalGroup(
            lrcPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lrcPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lrcL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lrcP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lrcP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void appTitleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appTitleMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_appTitleMousePressed

    private void appTitleMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appTitleMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_appTitleMouseDragged

    private void quitBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quitBtnMouseClicked
        if(theme==1){
            UIManager.put("OptionPane.background",new Color(51, 51, 51));
            UIManager.put("Panel.background",new Color(51, 51, 51));
            UIManager.put("Button.background",new Color(51, 51, 51));        
        }
        else{
            UIManager.put("OptionPane.background",white);
            UIManager.put("Panel.background",white);
            UIManager.put("Button.background",white);
        }
        ImageIcon icon;
        if(theme==1){
            icon = new ImageIcon(imagePath+"Dark\\logoD.PNG");
        }
        else{
            icon = new ImageIcon(imagePath+"White\\logoW.png");
        }
        int answer = JOptionPane.showConfirmDialog(null,"Do you want to leave ?",
                "BEATS - (MP3 Player)", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,icon);
        switch (answer) {
            case JOptionPane.OK_OPTION:
                System.exit(0);
                break;
            case JOptionPane.CANCEL_OPTION:
                break;
        }
    }//GEN-LAST:event_quitBtnMouseClicked

    private void settingsBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsBtnMouseClicked
        xMouse = evt.getX();
        yMouse = evt.getY();
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        settings.setLocation(x - xMouse, y - yMouse);
        settings.setVisible(true);
        jLabel10.setText(username);
    }//GEN-LAST:event_settingsBtnMouseClicked

    private void appTitleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appTitleMouseClicked
        if (evt.getClickCount() == 2) {
            if (windowCollapsed == false) {
                windowCollapsed = true;
                this.setSize(new Dimension(mainPanel.getSize().width, 60));

                appTitle.setFont(new Font("Nirmala UI", 1, 14));
                appTitle.setText("Playing Now... | " + songFile.getName().replace(".mp3",""));
            } else if (windowCollapsed == true) {
                windowCollapsed = false;
                this.setSize(new Dimension(947, mainPanel.getHeight()));

                appTitle.setFont(new Font("Nirmala UI", 1, 24));
                appTitle.setText(appName);
            }
        }
    }//GEN-LAST:event_appTitleMouseClicked

    private void muteBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_muteBtnMouseClicked
        volumeControl(0.0);
    }//GEN-LAST:event_muteBtnMouseClicked

    private void volumeFullBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volumeFullBtnMouseClicked
        volumeControl(1.0);
    }//GEN-LAST:event_volumeFullBtnMouseClicked

    private void volumeUpBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volumeUpBtnMouseClicked
        volumeUpControl(0.1);
    }//GEN-LAST:event_volumeUpBtnMouseClicked

    private void volumeDownBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volumeDownBtnMouseClicked
        volumeDownControl(0.1);
    }//GEN-LAST:event_volumeDownBtnMouseClicked

    private void openBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_openBtnMouseClicked
        JFileChooser openFileChooser = new JFileChooser(currentDirectory);
        openFileChooser.setFileFilter(new FileTypeFilter(".mp3", "Open MP3 Files Only!"));
        int result = openFileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            lrcA.setText("");
            songFile = openFileChooser.getSelectedFile();
            player.addToPlayList(songFile);
            player.skipForward();
            currentDirectory = songFile.getAbsolutePath();
            songNameDisplay.setText("Playing Now... | " + songFile.getName());
            playing = 2;
            filechosen = true;
            i = 0;
            if (theme == 1) {
                plypic.setIcon(new ImageIcon(imagePath+"Dark\\visualiserD.gif"));
            } else if (theme == 2) {
                plypic.setIcon(new ImageIcon(imagePath+"White\\equalizerW.gif"));
            }
        try {
            AudioFile audioFile = AudioFileIO.read(new File(songFile.getAbsolutePath()));
            MP3AudioHeader dur = (MP3AudioHeader) audioFile.getAudioHeader();
            duration = dur.getPreciseTrackLength();
            System.out.println(duration);
            jProgressBar1.setMaximum((int) duration);
            jProgressBar1.setMinimum(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Thread ProgressUpdate = new Thread() {
            public void run() {
                int time = (int) duration;
                while (i <=time) {
                    try {
                        if (playing == 2) {
                            Thread.sleep(1000);
                            i++;
                        } else if (playing == 1 || playing == 3) {
                            Thread.sleep(500);
                            continue;
                        }
                    } catch (Exception ex) {
                    }
                    jProgressBar1.setValue(i);
                    jProgressBar1.setToolTipText(i+"s/"+time+"s");
                }
            }
        };
        ProgressUpdate.start();
        Thread t= new Thread(() -> {
            try {
                lrcA.setText("Searching lyrics...\n");
                lrcA.setText(getLyrics(songFile.getName()));
            } catch (IOException ex) {
                Logger.getLogger(PlayerFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        t.start();
        }
    }//GEN-LAST:event_openBtnMouseClicked

    private void stopBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stopBtnMouseClicked
        if (filechosen == true) {
            player.stop();
            playing = 1;
            plypic.setIcon(new ImageIcon(""));
            i=-1;
        }
    }//GEN-LAST:event_stopBtnMouseClicked

    private void playBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playBtnMouseClicked
        if(playing!=2){
        if (filechosen == true) {        
            player.play();
            playing = 2;
            if (theme == 1) {
                plypic.setIcon(new ImageIcon(imagePath+"Dark\\visualiserD.gif"));
            
            } else if (theme == 2) {
                plypic.setIcon(new ImageIcon(imagePath+"White\\equalizerW.gif"));
            }
        }
        if(playing==1){
            i=0;
        }     
        }
    }//GEN-LAST:event_playBtnMouseClicked

    private void pauseBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pauseBtnMouseClicked
        if (filechosen == true) {
            player.pause();
            playing = 3;
            if (theme == 1) {
                plypic.setIcon(new ImageIcon(imagePath+"Dark\\visualiserDpause.png"));
            } else if (theme == 2) {
                plypic.setIcon(new ImageIcon(imagePath+"White\\equalizerWpause.png"));
            }
        }
    }//GEN-LAST:event_pauseBtnMouseClicked

    private void repeatBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_repeatBtnMouseClicked
        player.play();
        i=0;
    }//GEN-LAST:event_repeatBtnMouseClicked

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        String thee = (String) jComboBox1.getSelectedItem();
        if (thee.equals("Dark")) {
            jPanel1.setBackground(new Color(51, 51, 51));
            jPanel2.setBackground(new Color(51, 51, 51));
            jPanel3.setBackground(new Color(51, 51, 51));
            jPanel4.setBackground(new Color(51, 51, 51));
            jPanel5.setBackground(new Color(51, 51, 51));
            jPanel6.setBackground(new Color(51, 51, 51));
            jPanel7.setBackground(new Color(51, 51, 51));
            jPanel8.setBackground(new Color(51, 51, 51));
            mainPanel.setBackground(new Color(51, 51, 51));
            jLabel3.setForeground(new Color(204,204,204));
            jLabel15.setForeground(new Color(204,204,204));
            jLabel6.setForeground(new Color(204,204,204));
            jLabel11.setForeground(new Color(204,204,204));
            jLabel1.setForeground(white);        
            jLabel2.setForeground(white);
            jLabel4.setForeground(white);
            jLabel12.setForeground(white);
            jLabel13.setForeground(white);
            jLabel14.setForeground(white);
            jLabel5.setForeground(white);
            jLabel9.setForeground(white);
            jLabel10.setForeground(white);
            lrcP.setBackground(new Color(51, 51, 51));
            lrcA.setBackground(new Color(51, 51, 51));
            lrcL.setBackground(new Color(51, 51, 51));
            headerPanel.setBackground(new Color(51, 51, 51));
            songNameMainPanel.setBackground(new Color(51, 51, 51));
            songNameSubPanel.setBackground(new Color(51, 51, 51));
            SettingTitle.setForeground(new Color(79, 176, 147));
            controlPanel.setBackground(new Color(51, 51, 51));
            Pic.setBackground(new Color(51, 51, 51));
            jComboBox1.setBackground(new Color(51, 51, 51));
            jComboBox1.setForeground(new Color(79, 176, 147));
            jButton1.setBackground(new Color(51, 51, 51));
            jButton2.setBackground(new Color(51, 51, 51));
            jButton3.setBackground(new Color(51, 51, 51));
            jProgressBar1.setBackground(new Color(51, 51, 51));
            jProgressBar1.setForeground(new Color(79, 176, 147));
            theme = 1;
            playBtn.setIcon(new ImageIcon(imagePath+"Dark\\PlayD.PNG"));
            volumeUpBtn.setIcon(new ImageIcon(imagePath+"Dark\\VolupD.PNG"));
            volumeDownBtn.setIcon(new ImageIcon(imagePath+"Dark\\VoldownD.PNG"));
            volumeFullBtn.setIcon(new ImageIcon(imagePath+"Dark\\FullVolD.PNG"));
            muteBtn.setIcon(new ImageIcon(imagePath+"Dark\\MuteD.PNG"));
            pauseBtn.setIcon(new ImageIcon(imagePath+"Dark\\PauseD.PNG"));
            stopBtn.setIcon(new ImageIcon(imagePath+"Dark\\RemaneD.PNG"));
            openBtn.setIcon(new ImageIcon(imagePath+"Dark\\MusicfileD.PNG"));
            logo.setIcon(new ImageIcon(imagePath+"Dark\\logoD.PNG"));
            minBtn.setIcon(new ImageIcon(imagePath+"Dark\\minD.PNG"));
            settingsBtn.setIcon(new ImageIcon(imagePath+"Dark\\SettingsD.PNG"));
            quitBtn.setIcon(new ImageIcon(imagePath+"Dark\\ExitD.PNG"));          
            if (playing == 2) {
                plypic.setIcon(new ImageIcon(imagePath+"Dark\\visualiserD.gif"));
            } else if (playing == 3) {
                plypic.setIcon(new ImageIcon(imagePath+"Dark\\visualiserDpause.png"));
            }
            repeatBtn.setIcon(new ImageIcon(imagePath+"Dark\\RepeatD.PNG"));
        } else if (thee.equals("White")) {
            jPanel1.setBackground(white);
            jPanel2.setBackground(white);
            jPanel3.setBackground(white);
            jPanel4.setBackground(white);
            jPanel5.setBackground(white);
            jPanel6.setBackground(white);
            jPanel7.setBackground(white);
            jPanel8.setBackground(white);
            mainPanel.setBackground(white);
            jLabel3.setForeground(new Color(51, 51, 51));
            jLabel15.setForeground(new Color(51, 51, 51));
            jLabel6.setForeground(new Color(51, 51, 51));
            jLabel11.setForeground(new Color(51, 51, 51));
            jLabel15.setForeground(new Color(51, 51, 51));
            headerPanel.setBackground(white);
            jLabel1.setForeground(black);    
            jLabel12.setForeground(black);   
            jLabel13.setForeground(black);   
            jLabel14.setForeground(black);   
            jLabel2.setForeground(black);
            jLabel12.setForeground(black);
            jLabel13.setForeground(black);
            jLabel14.setForeground(black);
            jLabel4.setForeground(black);
            jLabel5.setForeground(black);
            jLabel9.setForeground(black);
            jLabel10.setForeground(black);
            SettingTitle.setForeground(new Color(79, 176, 147));
            lrcP.setBackground(white);
            lrcA.setBackground(white);
            lrcL.setBackground(white);
            songNameMainPanel.setBackground(white);
            songNameSubPanel.setBackground(white);
            controlPanel.setBackground(white);
            Pic.setBackground(white);
            jComboBox1.setBackground(white);
            jComboBox1.setForeground(new Color(79, 176, 147));
            jProgressBar1.setBackground(white);
            jProgressBar1.setForeground(new Color(79, 176, 147));
            theme = 2;
            jButton1.setBackground(white);
            jButton2.setBackground(white);
            jButton3.setBackground(white);
            playBtn.setIcon(new ImageIcon(imagePath+"White\\playW.png"));
            volumeUpBtn.setIcon(new ImageIcon(imagePath+"White\\volupW.png"));
            volumeDownBtn.setIcon(new ImageIcon(imagePath+"White\\voldownW.png"));
            volumeFullBtn.setIcon(new ImageIcon(imagePath+"White\\FullvolW.png"));
            muteBtn.setIcon(new ImageIcon(imagePath+"White\\muteW.png"));
            pauseBtn.setIcon(new ImageIcon(imagePath+"White\\pauseW.png"));
            stopBtn.setIcon(new ImageIcon(imagePath+"White\\stopW.png"));
            openBtn.setIcon(new ImageIcon(imagePath+"White\\musicfileW.png"));
            logo.setIcon(new ImageIcon(imagePath+"White\\logoW.png"));
            minBtn.setIcon(new ImageIcon(imagePath+"White\\minW.png"));
            settingsBtn.setIcon(new ImageIcon(imagePath+"White\\SettingsW.png"));
            quitBtn.setIcon(new ImageIcon(imagePath+"White\\PowerW.png"));
            if (playing == 2) {
                plypic.setIcon(new ImageIcon(imagePath+"White\\equalizerW.gif"));
            } else if (playing == 3) {
                plypic.setIcon(new ImageIcon(imagePath+"White\\equalizerWpause.png"));
            }
            repeatBtn.setIcon(new ImageIcon(imagePath+"White\\repeatW.png"));
            
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        settings.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        ImageIcon icon = new ImageIcon("imagePath+\"Dark\\logoD.PNG");
        setIconImage(icon.getImage());
    }//GEN-LAST:event_formWindowActivated

    private void minBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minBtnMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_minBtnMouseClicked

    private void jLabel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseEntered
        jLabel8.setForeground(new Color(30, 215, 96));
    }//GEN-LAST:event_jLabel8MouseEntered

    private void jLabel8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseExited
        jLabel8.setForeground(new Color(79, 176, 147));
    }//GEN-LAST:event_jLabel8MouseExited

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(theme==1){
            UIManager.put("OptionPane.background",new Color(51, 51, 51));
            UIManager.put("Panel.background",new Color(51, 51, 51));
            UIManager.put("Button.background",new Color(51, 51, 51));  
        }
        else{
            UIManager.put("OptionPane.background",white);
            UIManager.put("Panel.background",white);
            UIManager.put("Button.background",white);  
        }
        int answer = JOptionPane.showConfirmDialog(null, "Do you want to save changes ?",
                "Change Password", JOptionPane.OK_CANCEL_OPTION);
        switch (answer) {
            case JOptionPane.OK_OPTION:
                String newpass= new String (jPasswordField1.getPassword());
                try{
                    Class.forName("java.sql.Driver");
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/beats","root","");
                    Statement stmt=con.createStatement();
                    String query="Update users set password='"+newpass+"' where username='"+username+"' and email='"+email+"';";
                    int rows= stmt.executeUpdate(query);           
                    con.close();
                    profile.setVisible(false);
                } catch (Exception ex) {
            Logger.getLogger(PlayerFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
                break;
            case JOptionPane.CANCEL_OPTION:
                jPasswordField1.setEditable(false);
                jPasswordField1.setEnabled(false);
                try{
                    Class.forName("java.sql.Driver");
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/beats","root","");
                    Statement stmt=con.createStatement();
                    String query = "SELECT * FROM users WHERE email = " + "'" + email + "'" + " AND username = " + "'" +username+ "';";
                    ResultSet rs = stmt.executeQuery(query);   
                    jPasswordField1.setText(rs.getString(3));
                    con.close();                   
                } catch (Exception ex) {
            Logger.getLogger(PlayerFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
                break;
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseEntered
        jLabel15.setForeground(new Color(30, 215, 96));
    }//GEN-LAST:event_jLabel15MouseEntered

    private void jLabel15MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MousePressed
        jPasswordField1.setEnabled(true);
        jPasswordField1.setEditable(true);
    }//GEN-LAST:event_jLabel15MousePressed

    private void jPanel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        profile.setLocation(x - xMouse, y - yMouse);
        profile.setVisible(true);
        try{
                    Class.forName("java.sql.Driver");
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/beats","root","");
                    Statement stmt=con.createStatement();
                    String query = "SELECT * FROM users WHERE email = " + "'" + email + "'" + " AND username = " + "'" +username+ "';";
                    ResultSet rs = stmt.executeQuery(query);      
                    rs.next();
                    jTextField1.setText(rs.getString(1));
                    jTextField2.setText(rs.getString(2));
                    jPasswordField1.setText(rs.getString(3));
                    con.close();
                } catch (Exception ex) {
            Logger.getLogger(PlayerFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jPanel4MousePressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        profile.setVisible(false);   
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        try {
            Desktop d = Desktop.getDesktop();
            d.browse(new URI("file:///C:/Users/class12.DCCHIREC/Desktop/TnC/tc.html"));
        } catch (Exception e) {
            System.out.println(e);

        }
    }//GEN-LAST:event_jPanel2MousePressed

    private void jPanel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MousePressed
        try {
            Desktop d = Desktop.getDesktop();
            d.browse(new URI("file:///C:/Users/class12.DCCHIREC/Desktop/TnC/privacy.html"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jPanel3MousePressed

    private void jLabel15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseExited
        jLabel15.setForeground(new Color(204,204,204));
    }//GEN-LAST:event_jLabel15MouseExited

    private void jLabel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MousePressed
        if(theme==1){
            UIManager.put("OptionPane.background",new Color(51, 51, 51));
            UIManager.put("Panel.background",new Color(51, 51, 51));
            UIManager.put("Button.background",new Color(51, 51, 51));        
        }
        else{
            UIManager.put("OptionPane.background",white);
            UIManager.put("Panel.background",white);
            UIManager.put("Button.background",white);    
        }
        int answer = JOptionPane.showConfirmDialog(null, "Do you want to log out ?",
                "BEATS - (MP3 Player) ", JOptionPane.OK_CANCEL_OPTION);
        switch (answer) {
            case JOptionPane.OK_OPTION:
                Home_Page hp= new Home_Page();
                hp.setVisible(true);
                this.dispose();
                settings.setVisible(false);
                break;
            case JOptionPane.CANCEL_OPTION:
                break;
        }
    }//GEN-LAST:event_jLabel8MousePressed

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
            java.util.logging.Logger.getLogger(PlayerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlayerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlayerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlayerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlayerFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Pic;
    private javax.swing.JLabel SettingTitle;
    private javax.swing.JLabel SettingTitle1;
    private javax.swing.JLabel appTitle;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel logo;
    private javax.swing.JTextArea lrcA;
    private javax.swing.JLabel lrcL;
    private javax.swing.JPanel lrcP;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel minBtn;
    private javax.swing.JLabel muteBtn;
    private javax.swing.JLabel openBtn;
    private javax.swing.JLabel pauseBtn;
    private javax.swing.JLabel playBtn;
    private javax.swing.JLabel plypic;
    private javax.swing.JDialog profile;
    private javax.swing.JLabel quitBtn;
    private javax.swing.JLabel repeatBtn;
    private javax.swing.JDialog settings;
    private javax.swing.JLabel settingsBtn;
    private javax.swing.JLabel songNameDisplay;
    private javax.swing.JPanel songNameMainPanel;
    private javax.swing.JPanel songNameSubPanel;
    private javax.swing.JLabel stopBtn;
    private javax.swing.JLabel volumeDownBtn;
    private javax.swing.JLabel volumeFullBtn;
    private javax.swing.JLabel volumeUpBtn;
    // End of variables declaration//GEN-END:variables

    // I am going to create a custom MP3Player Method
    private MP3Player mp3Player() {
        MP3Player mp3Player = new MP3Player();
        return mp3Player;
    }

    // Let's Set Volume Down Method It's not necessary to remeber this code.
    private void volumeDownControl(Double valueToPlusMinus) {
        // Get Mixer Information From AudioSystem
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        // Now use a for loop to list all mixers
        for (Mixer.Info mixerInfo : mixers) {
            // Get Mixer
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            // Now Get Target Line
            Line.Info[] lineInfos = mixer.getTargetLineInfo();
            // Here again use for loop to list lines
            for (Line.Info lineInfo : lineInfos) {
                // Make a null line
                Line line = null;
                // Make a boolean as opened
                boolean opened = true;
                // Now use try exception for opening a line
                try {
                    line = mixer.getLine(lineInfo);
                    opened = line.isOpen() || line instanceof Clip;
                    // Now Check If Line Is not Opened
                    if (!opened) {
                        // Open Line
                        line.open();
                    }
                    // Make a float control
                    FloatControl volControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                    // Get Current Volume Now
                    float currentVolume = volControl.getValue();
                    // Make a temp double variable and store valuePlusMinus
                    Double volumeToCut = valueToPlusMinus;
                    // Make a float and calculate the addition or subtraction in volume
                    float changedCalc = (float) ((float) currentVolume - (double) volumeToCut);
                    // Now Set This Changed Value Into Volume Line.
                    volControl.setValue(changedCalc);

                } catch (LineUnavailableException lineException) {
                } catch (IllegalArgumentException illException) {
                } finally {
                    // Close Line If it opened
                    if (line != null && !opened) {
                        line.close();
                    }
                }
            }
        }
    }

    // Let's Set Volume Up Method It's not necessary to remeber this code.
    private void volumeUpControl(Double valueToPlusMinus) {
        // Get Mixer Information From AudioSystem
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        // Now use a for loop to list all mixers
        for (Mixer.Info mixerInfo : mixers) {
            // Get Mixer
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            // Now Get Target Line
            Line.Info[] lineInfos = mixer.getTargetLineInfo();
            // Here again use for loop to list lines
            for (Line.Info lineInfo : lineInfos) {
                // Make a null line
                Line line = null;
                // Make a boolean as opened
                boolean opened = true;
                // Now use try exception for opening a line
                try {
                    line = mixer.getLine(lineInfo);
                    opened = line.isOpen() || line instanceof Clip;
                    // Now Check If Line Is not Opened
                    if (!opened) {
                        // Open Line
                        line.open();
                    }
                    // Make a float control
                    FloatControl volControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                    // Get Current Volume Now
                    float currentVolume = volControl.getValue();
                    // Make a temp double variable and store valuePlusMinus
                    Double volumeToCut = valueToPlusMinus;
                    // Make a float and calculate the addition or subtraction in volume
                    float changedCalc = (float) ((float) currentVolume + (double) volumeToCut);
                    // Now Set This Changed Value Into Volume Line.
                    volControl.setValue(changedCalc);

                } catch (LineUnavailableException lineException) {
                } catch (IllegalArgumentException illException) {
                } finally {
                    // Close Line If it opened
                    if (line != null && !opened) {
                        line.close();
                    }
                }
            }
        }
    }

    // Let's Set Volume Method It's not necessary to remeber this code.
    private void volumeControl(Double valueToPlusMinus) {
        // Get Mixer Information From AudioSystem
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        // Now use a for loop to list all mixers
        for (Mixer.Info mixerInfo : mixers) {
            // Get Mixer
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            // Now Get Target Line
            Line.Info[] lineInfos = mixer.getTargetLineInfo();
            // Here again use for loop to list lines
            for (Line.Info lineInfo : lineInfos) {
                // Make a null line
                Line line = null;
                // Make a boolean as opened
                boolean opened = true;
                // Now use try exception for opening a line
                try {
                    line = mixer.getLine(lineInfo);
                    opened = line.isOpen() || line instanceof Clip;
                    // Now Check If Line Is not Opened
                    if (!opened) {
                        // Open Line
                        line.open();
                    }
                    // Make a float control
                    FloatControl volControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                    // Get Current Volume Now
                    float currentVolume = volControl.getValue();
                    // Make a temp double variable and store valuePlusMinus
                    Double volumeToCut = valueToPlusMinus;
                    // Make a float and calculate the addition or subtraction in volume
                    float changedCalc = (float) ((double) volumeToCut);
                    // Now Set This Changed Value Into Volume Line.
                    volControl.setValue(changedCalc);

                } catch (LineUnavailableException lineException) {
                } catch (IllegalArgumentException illException) {
                } finally {
                    // Close Line If it opened
                    if (line != null && !opened) {
                        line.close();
                    }
                }
            }
        }
    }
}
