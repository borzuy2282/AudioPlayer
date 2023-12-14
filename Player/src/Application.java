import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Application extends JFrame {
    JPanel menu = new JPanel(new BorderLayout());

    public Application(){
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("My player");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dm = toolkit.getScreenSize();
        setBounds(dm.width/2 - 600, dm.height/2 - 360, 1200, 720 );

        menu.setBackground(Color.BLACK);
        add(menu);
        //TODO
        //come up with another part of an app
        menu.add(getPlaylist(), BorderLayout.WEST);

    }

    JPanel getPlaylist(){
        DefaultListModel<JButton>listModel = new DefaultListModel<>();
        //puts all the buttons with the playing song to the JList
        for(File song : getSongs()) {
            listModel.addElement(getSongButton(song));
        }

        JList<JButton> list = new JList<>(listModel);
        list.setBackground(Color.YELLOW);

        //TODO
        //Make a look for a panel and list
        JScrollPane jScrollPane = new JScrollPane(list);
        JPanel panel = new JPanel();
        panel.add(jScrollPane);
        panel.setVisible(true);
        panel.setBackground(Color.BLUE);

        return panel;
    }

    private ArrayList<File> getSongs(){
        String road = "Songs";
        ArrayList<File> files = new ArrayList<>();

        //gets all songs from directory "Songs" to the arrayList
        File directory = new File(road);
        if(directory.isDirectory()){
            File [] files1 = directory.listFiles();

            if(files1 != null){
                for(File file : files1){
                    if(file.isFile()){
                        files.add(file);
                    }
                }
            }
        }

        return files;

    }

    private JButton getSongButton(File song){
        JButton btn = new JButton(song.getName());

        try {
            //making click on button play song
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(song);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            btn.addActionListener(e -> clip.start());
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }

        //TODO
        //Add button look
        return btn;
    }

}
