package x.acecil


import com.sun.glass.events.KeyEvent

import javax.imageio.ImageIO
import javax.swing.JLabel
import javax.swing.JOptionPane
import javax.swing.JPanel
import java.awt.Rectangle
import java.awt.Robot
import java.awt.Toolkit
import java.awt.event.InputEvent
import java.awt.event.MouseEvent

def codes = new File ('C:\\Users\\alyce\\Documents\\GitHub\\workpad\\src\\main\\groovy\\pokecode')
def robby = Robot.newInstance()

def area = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())
def screenshot= {
    def bufferedImage = robby.createScreenCapture(area)

    ImageIO.write(bufferedImage, "png", new File("Screen-${System.currentTimeMillis()}.png"))
}



// Capture the whole screen
screenshot(area)

ok()


int i = 0
codes.eachLine {
    code -> i++

        if(!code){/*empty line skip*/return}



        robby.setAutoWaitForIdle(true)
        robby.setAutoDelay(66)

        println "------------------------------------------"
        println "Code(#$i) : $code"
        println ""
        println ""
        println ""




        ///click to enter
        robby.mouseMove(850,1850)

        robby.mousePress InputEvent.BUTTON1_MASK
        robby.mouseRelease InputEvent.BUTTON1_MASK


        code.toCharArray().each{

            def press = it.charValue() as Integer
            print it
            robby.keyPress(press)
            robby.keyRelease(press)

        }

        robby.keyPress(KeyEvent.VK_ENTER)
        robby.keyRelease(KeyEvent.VK_ENTER)

        //done

        println ""

        if(i%10==0){
            println "Waiting for done"

            ok()
        }

}



private void ok(String msg) {
    String[] options = {"OK"}
    JPanel panel = new JPanel()
    JLabel lbl = new JLabel(msg?:'')
    panel.add(lbl);
    int selectedOption = JOptionPane.showOptionDialog(null, panel, "Pokemon Code Enterer", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    if (selectedOption == 0) {
        println "Okay!"
    } else {
        println "what?"
    }
}




